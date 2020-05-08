package pl.lodz.p.it.tks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.lodz.p.it.tks.dto.ClientDTO;
import pl.lodz.p.it.tks.useCases.clientUseCase.AddClientUseCase;
import pl.lodz.p.it.tks.useCases.clientUseCase.UpdateClientUseCase;
import pl.lodz.p.it.tks.useCases.clientUseCase.UtilsClientUseCase;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class ClientController {

    private AddClientUseCase addClientService;
    private UpdateClientUseCase updateClientService;
    private UtilsClientUseCase utilsClientService;

    @Autowired
    public ClientController(AddClientUseCase addClientService, UpdateClientUseCase updateClientService, UtilsClientUseCase utilsClientService) {
        this.addClientService = addClientService;
        this.updateClientService = updateClientService;
        this.utilsClientService = utilsClientService;
    }

    @GetMapping("/add-client")
    public ModelAndView showClientForm(){
        return new ModelAndView("clientForm", "client", new ClientDTO());
    }

    @PostMapping("/add-client")
    public String addClient(@Valid @ModelAttribute("client") ClientDTO client, BindingResult bindingResult){
        if (!bindingResult.hasErrors()){
            //userService.addClientFromUser(user);
            addClientService.addClient(client);
            return "redirect:/users/";
        }
        return "clientForm";
    }

    @RequestMapping("/update-client/{login}")
    public ModelAndView showClientUpdateForm(@PathVariable String login){
        return new ModelAndView("clientUpdateForm", "client", utilsClientService.getClient(login));
    }

    @PostMapping("/update-client")
    public String updateClient(@Valid @ModelAttribute ClientDTO client, BindingResult bindingResult){
        if (!bindingResult.hasErrors()){
            updateClientService.updateClient(client.getLogin(), client);
            return "redirect:/users/";
        }
        return "clientUpdateForm";
    }

    @RequestMapping
    public ModelAndView showAllClients(){
        return new ModelAndView("allClient", "clients", utilsClientService.getAllClients());
    }
}
