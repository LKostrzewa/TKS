package pl.lodz.p.it.tks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.lodz.p.it.tks.dto.ClientDTO;
import pl.lodz.p.it.tks.model.Client;
import pl.lodz.p.it.tks.useCases.userUseCase.AddUserUseCase;
import pl.lodz.p.it.tks.useCases.userUseCase.UpdateUserUseCase;
import pl.lodz.p.it.tks.useCases.userUseCase.UtilsUserUseCase;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private AddUserUseCase addUserService;
    private UpdateUserUseCase updateUserService;
    private UtilsUserUseCase utilsUserService;

    @Autowired
    public UserController(AddUserUseCase addUserService,UpdateUserUseCase updateUserService,UtilsUserUseCase utilsUserService) {
        this.addUserService = addUserService;
        this.updateUserService = updateUserService;
        this.utilsUserService = utilsUserService;
    }

    @GetMapping("/add-client")
    public ModelAndView showClientForm(){
        return new ModelAndView("clientForm", "client", new ClientDTO());
    }

    @PostMapping("/add-client")
    public String addClient(@Valid @ModelAttribute("client") ClientDTO client, BindingResult bindingResult){
        if (!bindingResult.hasErrors()){
            //userService.addClientFromUser(user);
            addUserService.addUser(client);
            return "redirect:/users/";
        }
        return "clientForm";
    }

    @RequestMapping("/update-client/{login}")
    public ModelAndView showClientUpdateForm(@PathVariable String login){
        return new ModelAndView("clientUpdateForm", "client", utilsUserService.getUser(login));
    }

    @PostMapping("/update-client")
    public String updateClient(@Valid @ModelAttribute ClientDTO client, BindingResult bindingResult){
        if (!bindingResult.hasErrors()){
            updateUserService.updateUser(client.getLogin(), client);
            return "redirect:/users/";
        }
        return "clientUpdateForm";
    }

    @RequestMapping
    public ModelAndView showAllClients(){
        return new ModelAndView("allClient", "clients", utilsUserService.getAllClients());
    }
}
