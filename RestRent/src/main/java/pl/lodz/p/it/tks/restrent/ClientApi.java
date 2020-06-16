package pl.lodz.p.it.tks.restrent;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.tks.dto.ClientDTO;
import pl.lodz.p.it.tks.useCases.clientUseCase.AddClientUseCase;
import pl.lodz.p.it.tks.useCases.clientUseCase.DeleteClientUseCase;
import pl.lodz.p.it.tks.useCases.clientUseCase.UpdateClientUseCase;
import pl.lodz.p.it.tks.useCases.clientUseCase.UtilsClientUseCase;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
public class ClientApi {

    private RabbitTemplate rabbitTemplate;
    private AddClientUseCase addClientUseCase;
    private UpdateClientUseCase updateClientUseCase;
    private DeleteClientUseCase deleteClientUseCase;
    private UtilsClientUseCase utilsClientUseCase;

    @Autowired
    public ClientApi(RabbitTemplate rabbitTemplate, AddClientUseCase addClientUseCase, UpdateClientUseCase updateClientUseCase, DeleteClientUseCase deleteClientUseCase, UtilsClientUseCase utilsClientUseCase) {
        this.rabbitTemplate = rabbitTemplate;
        this.addClientUseCase = addClientUseCase;
        this.updateClientUseCase = updateClientUseCase;
        this.deleteClientUseCase = deleteClientUseCase;
        this.utilsClientUseCase = utilsClientUseCase;
    }

    @RabbitListener(queues = "app")
    public void addClient(Message message) {

        //UserPayload userPayload = (UserPayload) rabbitTemplate.getMessageConverter().fromMessage(message);
        ClientDTO clientDto = (ClientDTO) rabbitTemplate.getMessageConverter().fromMessage(message);
        //ClientDTO clientDTO = new ClientDTO(userPayload.getKey(), userPayload.getName(), userPayload.getSurname(),
        //        userPayload.isActive());
        //addClientUseCase.addClient(clientDTO);
        /*UserDTO userDTO = new UserDTO(userPayload.getLogin(), userPayload.getPassword(), userPayload.getName(),
                userPayload.getSurname(), userPayload.isActive(), userPayload.getKey());*/
        addClientUseCase.addClient(clientDto);
    }

    @RabbitListener(queues = "delete-user")
    public void deleteClient(String message) {
        UUID uuid = UUID.fromString(message);
        deleteClientUseCase.deleteClientByKey(uuid);
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
