package pl.lodz.p.it.tks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.lodz.p.it.tks.model.Client;
import pl.lodz.p.it.tks.model.Reservation;
import pl.lodz.p.it.tks.service.ResourceService;
import pl.lodz.p.it.tks.service.UserService;
import pl.lodz.p.it.tks.useCases.ReservationUseCase.DeleteReservationUseCase;
import pl.lodz.p.it.tks.useCases.ReservationUseCase.EndReservationUseCase;
import pl.lodz.p.it.tks.useCases.ReservationUseCase.StartReservationUseCase;
import pl.lodz.p.it.tks.useCases.ReservationUseCase.UtilsReservationUseCase;
import pl.lodz.p.it.tks.useCases.ResourceUseCase.UtilsResourceUseCase;
import pl.lodz.p.it.tks.useCases.UserUseCase.UtilsUserUseCase;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    private StartReservationUseCase startReservationService;
    private EndReservationUseCase endReservationService;
    private DeleteReservationUseCase deleteReservationService;
    private UtilsReservationUseCase utilsReservationService;
    private UtilsUserUseCase utilsUserService;
    private UtilsResourceUseCase utilsResourceService;

    @Autowired
    public ReservationController(StartReservationUseCase startReservationService, EndReservationUseCase endReservationService, DeleteReservationUseCase deleteReservationService, UtilsReservationUseCase utilsReservationService, UtilsUserUseCase utilsUserService, UtilsResourceUseCase utilsResourceService) {
        this.startReservationService = startReservationService;
        this.endReservationService = endReservationService;
        this.deleteReservationService = deleteReservationService;
        this.utilsReservationService = utilsReservationService;
        this.utilsUserService = utilsUserService;
        this.utilsResourceService = utilsResourceService;
    }

    @GetMapping("/add-reservation")
    public ModelAndView showReservationForm(Authentication authentication){
        ModelAndView modelAndView = new ModelAndView("reservationForm", "reservation", new Reservation());
        //modelAndView.addObject("clients", userService.getAllActiveClients());
        modelAndView.addObject("resources", utilsResourceService.getAllResources());
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        if(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
            modelAndView.addObject("clients", utilsUserService.getAllActiveClients());
        } else {
            modelAndView.addObject("clients", utilsUserService.getUser(userDetails.getUsername()));
        }
        return modelAndView;
    }

    @PostMapping("/add-reservation")
    public String addReservation(@Valid @ModelAttribute Reservation reservation, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("clients", utilsUserService.getAllActiveClients());
            model.addAttribute("resources", utilsResourceService.getAllResources());
            return "reservationForm";
        }
        try{
            reservation.setClient((Client)utilsUserService.getUser(reservation.getClient().getLogin()));
            reservation.setResource(utilsResourceService.getResource(reservation.getResource().getId()));
            startReservationService.startReservation(reservation);
        }
        catch (NullPointerException e){
            model.addAttribute("link", "/reservations/add-reservation");
            model.addAttribute("msg", "requested object is not accessible right now");
            return "exception";
        }
        catch (RuntimeException e){
            model.addAttribute("link", "/reservations/add-reservation");
            model.addAttribute("msg", e.getMessage());
            return "exception";
        }
        return "redirect:/reservations/";
    }

    @RequestMapping
    public ModelAndView showAllReservations(Authentication authentication, HttpServletRequest request){
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
            if(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
                ModelAndView modelAndView = new ModelAndView("allReservation", "reservations", utilsReservationService.getAllReservations());
                modelAndView.addObject("role", authentication.getAuthorities().toArray()[0]);
                modelAndView.addObject("login", request.getRemoteUser());
                return modelAndView;
                //return new ModelAndView("allReservation", "reservations", reservationService.getAllReservations());
        }
        ModelAndView modelAndView = new ModelAndView("allReservation", "reservations", utilsReservationService.getAllClientReservations(userDetails.getUsername()));
        modelAndView.addObject("role", authentication.getAuthorities().toArray()[0]);
        modelAndView.addObject("login", request.getRemoteUser());
        return modelAndView;
        //return new ModelAndView("allReservation", "reservations", reservationService.getAllClientReservations(userDetails.getUsername()));
    }

    @RequestMapping("/delete-reservation/{id}")
    public String deleteReservation(@PathVariable String id){
        deleteReservationService.deleteReservation(id);
        return "redirect:/reservations/";
    }

    @RequestMapping("/end-reservation/{id}")
    public ModelAndView endReservation(@PathVariable String id){
        endReservationService.endReservation(id, LocalDateTime.now());
        ModelAndView model = new ModelAndView("endReservation", "reservation", utilsReservationService.getReservation(id));
        model.addObject("price", utilsReservationService.countReservationPrice(id));
        return model;
    }

}
