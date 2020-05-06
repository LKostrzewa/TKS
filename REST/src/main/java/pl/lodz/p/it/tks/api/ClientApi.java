package pl.lodz.p.it.tks.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.tks.dto.ClientDTO;
import pl.lodz.p.it.tks.useCases.userUseCase.AddUserUseCase;
import pl.lodz.p.it.tks.useCases.userUseCase.DeleteUserUseCase;
import pl.lodz.p.it.tks.useCases.userUseCase.UpdateUserUseCase;
import pl.lodz.p.it.tks.useCases.userUseCase.UtilsUserUseCase;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientApi {

    //tutaj klienci po refaktor najak
    private AddUserUseCase addUserUseCase;
    private UpdateUserUseCase updateUserUseCase;
    private DeleteUserUseCase deleteUserUseCase;
    private UtilsUserUseCase utilsUserUseCase;

    @Autowired
    public ClientApi(AddUserUseCase addUserUseCase, UpdateUserUseCase updateUserUseCase, DeleteUserUseCase deleteUserUseCase, UtilsUserUseCase utilsUserUseCase) {
        this.addUserUseCase = addUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.utilsUserUseCase = utilsUserUseCase;
    }

    @GetMapping
    public List<ClientDTO> getAllClient(){
        return utilsUserUseCase.getAllClients();
    }

    @GetMapping("/active")
    public List<ClientDTO> getAllActiveClients() {
        return utilsUserUseCase.getAllActiveClients();
    }

    @GetMapping("/{login}")
    public ClientDTO getClient(@PathVariable String login) {
        return (ClientDTO) utilsUserUseCase.getUser(login);
    }

    @PostMapping
    public void addClient(@RequestBody ClientDTO clientDTO) {
        addUserUseCase.addUser(clientDTO);
    }

    @PutMapping
    public void updateClient(@RequestBody ClientDTO clientDTO) {
        updateUserUseCase.updateUser(clientDTO.getLogin(), clientDTO);
    }

    @DeleteMapping("/{login}")
    public void deleteClient(@PathVariable String login) {
        deleteUserUseCase.deleteUser(login);
    }
}
