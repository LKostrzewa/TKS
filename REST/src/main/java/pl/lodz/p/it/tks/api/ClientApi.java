package pl.lodz.p.it.tks.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.tks.dto.ClientDTO;
import pl.lodz.p.it.tks.useCases.clientUseCase.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientApi {

    //tutaj klienci po refaktor najak
    private AddClientUseCase addClientUseCase;
    private UpdateClientUseCase updateClientUseCase;
    private DeleteClientUseCase deleteClientUseCase;
    private UtilsClientUseCase utilsClientUseCase;

    @Autowired
    public ClientApi(AddClientUseCase addClientUseCase, UpdateClientUseCase updateClientUseCase, DeleteClientUseCase deleteClientUseCase, UtilsClientUseCase utilsClientUseCase) {
        this.addClientUseCase = addClientUseCase;
        this.updateClientUseCase = updateClientUseCase;
        this.deleteClientUseCase = deleteClientUseCase;
        this.utilsClientUseCase = utilsClientUseCase;
    }

    @GetMapping
    public List<ClientDTO> getAllClient(){
        return utilsClientUseCase.getAllClients();
    }

    @GetMapping("/active")
    public List<ClientDTO> getAllActiveClients() {
        return utilsClientUseCase.getAllActiveClients();
    }

    @GetMapping("/{id}")
    public ClientDTO getClient(@PathVariable int id) {
        return utilsClientUseCase.getClient(id);
    }

    @PostMapping
    public void addClient(@RequestBody ClientDTO clientDTO) {
        addClientUseCase.addClient(clientDTO);
    }

    @PutMapping
    public void updateClient(@RequestBody ClientDTO clientDTO) {
        updateClientUseCase.updateClient(clientDTO.getId(), clientDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable int id) {
        deleteClientUseCase.deleteClient(id);
    }
}
