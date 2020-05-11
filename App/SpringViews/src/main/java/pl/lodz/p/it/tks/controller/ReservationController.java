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
import pl.lodz.p.it.tks.dto.ReservationDTO;
import pl.lodz.p.it.tks.useCases.clientUseCase.UtilsClientUseCase;
import pl.lodz.p.it.tks.useCases.reservationUseCase.DeleteReservationUseCase;
import pl.lodz.p.it.tks.useCases.reservationUseCase.EndReservationUseCase;
import pl.lodz.p.it.tks.useCases.reservationUseCase.StartReservationUseCase;
import pl.lodz.p.it.tks.useCases.reservationUseCase.UtilsReservationUseCase;
import pl.lodz.p.it.tks.useCases.resourceUseCase.UtilsResourceUseCase;

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
    private UtilsClientUseCase utilsClientService;
    private UtilsResourceUseCase utilsResourceService;

    @Autowired
    public ReservationController(StartReservationUseCase startReservationService, EndReservationUseCase endReservationService, DeleteReservationUseCase deleteReservationService, UtilsReservationUseCase utilsReservationService, UtilsClientUseCase utilsClientService, UtilsResourceUseCase utilsResourceService) {
        this.startReservationService = startReservationService;
        this.endReservationService = endReservationService;
        this.deleteReservationService = deleteReservationService;
        this.utilsReservationService = utilsReservationService;
        this.utilsClientService = utilsClientService;
        this.utilsResourceService = utilsResourceService;
    }

    @GetMapping("/add-reservation")
    public ModelAndView showReservationForm(Authentication authentication){
        ModelAndView modelAndView = new ModelAndView("reservationForm", "reservation", new ReservationDTO());
        //modelAndView.addObject("clients", userService.getAllActiveClients());
        modelAndView.addObject("resources", utilsResourceService.getAllResources());
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        if(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
            modelAndView.addObject("clients", utilsClientService.getAllActiveClients());
        } else {
            modelAndView.addObject("clients", utilsClientService.getClient(userDetails.getUsername()));
        }
        return modelAndView;
    }

    @PostMapping("/add-reservation")
    public String addReservation(@Valid @ModelAttribute ReservationDTO reservation, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("clients", utilsClientService.getAllActiveClients());
            model.addAttribute("resources", utilsResourceService.getAllResources());
            return "reservationForm";
        }
        try{
            reservation.setClient(utilsClientService.getClient(reservation.getClient().getId()));
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
                return new ModelAndView("allReservation", "reservations", utilsReservationService.getAllReservations());
                /*modelAndView.addObject("role", authentication.getAuthorities().toArray()[0]);
                modelAndView.addObject("login", request.getRemoteUser());
                return modelAndView;
                //return new ModelAndView("allReservation", "reservations", reservationService.getAllReservations());*/
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
