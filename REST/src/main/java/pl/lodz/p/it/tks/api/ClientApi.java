package pl.lodz.p.it.tks.api;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.tks.dto.ClientDTO;
import pl.lodz.p.it.tks.useCases.clientUseCase.*;

import java.util.List;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@RestController
@RequestMapping("/api/clients")
public class ClientApi {

    private AddClientUseCase addClientUseCase;
    private UpdateClientUseCase updateClientUseCase;
    private DeleteClientUseCase deleteClientUseCase;
    private UtilsClientUseCase utilsClientUseCase;
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public ClientApi(AddClientUseCase addClientUseCase, UpdateClientUseCase updateClientUseCase, DeleteClientUseCase deleteClientUseCase
            , UtilsClientUseCase utilsClientUseCase, RabbitTemplate rabbitTemplate) {
        this.addClientUseCase = addClientUseCase;
        this.updateClientUseCase = updateClientUseCase;
        this.deleteClientUseCase = deleteClientUseCase;
        this.utilsClientUseCase = utilsClientUseCase;
        this.rabbitTemplate = rabbitTemplate;
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

    /*@PostMapping
    public void addClient(@RequestBody ClientDTO clientDTO) {
        addClientUseCase.addClient(clientDTO);
    }*/

    @RabbitListener(queues = "add-user")
    public void addClient(Object clientDTO) {
        //ciekawe czy zadziała :)
        //ClientDTO clientDTO = (ClientDTO) rabbitTemplate.receiveAndConvert("add-user");
        //ClientDTO
        addClientUseCase.addClient((ClientDTO) clientDTO);
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
