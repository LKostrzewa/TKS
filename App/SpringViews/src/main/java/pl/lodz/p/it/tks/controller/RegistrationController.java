package pl.lodz.p.it.tks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.lodz.p.it.tks.dto.ClientDTO;
import pl.lodz.p.it.tks.dto.UserDTO;
import pl.lodz.p.it.tks.model.Client;
import pl.lodz.p.it.tks.useCases.clientUseCase.AddClientUseCase;
import pl.lodz.p.it.tks.useCases.userUseCase.AddUserUseCase;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

//@RequestMapping("")
@Controller
public class RegistrationController {

    /*private AddUserUseCase addUserService;

    @Autowired
    public RegistrationController(AddUserUseCase userService) {
        this.addUserService = userService;
    }*/

    private AddClientUseCase addClientService;

    @Autowired
    public RegistrationController(AddClientUseCase addClientService) {
        this.addClientService = addClientService;
    }

    @RequestMapping
    public String showMainPage(ModelMap model) {
        model.addAttribute("message", "Welcome in our restaurant");
        return "index";
    }

    @RequestMapping("/error")
    public String showMainPage() {
        return "accessDenied";
    }

    @RequestMapping("/main")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ADMIN")) {
            return "adminMain";
        } else if (request.isUserInRole("MANAGER")) {
            return "redirect:/resources/";
        } else return "redirect:/reservations/";
    }

    @RequestMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping("/register")
    public ModelAndView showRegisterPage() {
        //User user = new User();
        //user.setActive(false);
        ClientDTO client = new ClientDTO();
        client.setActive(false);
        return new ModelAndView("register", "client", client);
    }

    @PostMapping("/register")
    public String addClient(@Valid @ModelAttribute("client") ClientDTO client, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            addClientService.addClient(client);
            //userService.addClientFromUser(client);
            return "redirect:/reservations/";
        }
        return "register";
    }
}
