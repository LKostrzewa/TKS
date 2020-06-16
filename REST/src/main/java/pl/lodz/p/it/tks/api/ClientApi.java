package pl.lodz.p.it.tks.api;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//import pl.lodz.p.it.tks.dto.ClientDTO;
import pl.lodz.p.it.tks.dto.UserDTO;
import pl.lodz.p.it.tks.payloads.UserPayload;
//import pl.lodz.p.it.tks.useCases.clientUseCase.*;

import java.util.List;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import pl.lodz.p.it.tks.useCases.userUseCase.AddUserUseCase;

@RestController
@RequestMapping("/api/clients")
public class ClientApi {

    //private AddClientUseCase addClientUseCase;
    private AddUserUseCase addUserUseCase;
    //private UpdateClientUseCase updateClientUseCase;
    //private DeleteClientUseCase deleteClientUseCase;
    //private UtilsClientUseCase utilsClientUseCase;
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public ClientApi(//AddClientUseCase addClientUseCase, UpdateClientUseCase updateClientUseCase, DeleteClientUseCase deleteClientUseCase
            //, UtilsClientUseCase utilsClientUseCase,
                     RabbitTemplate rabbitTemplate, AddUserUseCase addUserUseCase) {
        //this.addClientUseCase = addClientUseCase;
        //this.updateClientUseCase = updateClientUseCase;
        //this.deleteClientUseCase = deleteClientUseCase;
        //this.utilsClientUseCase = utilsClientUseCase;
        this.rabbitTemplate = rabbitTemplate;
        this.addUserUseCase = addUserUseCase;
    }

    /*@GetMapping
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
    }*/

    //dziala
    /*@GetMapping("/queue")
    public ClientDTO addClient() {
        UserPayload userPayload = (UserPayload) rabbitTemplate.receiveAndConvert("add-user");
        ClientDTO clientDTO = new ClientDTO(userPayload.getKey(), userPayload.getName(), userPayload.getSurname(),
                userPayload.isActive());
        addClientUseCase.addClient(clientDTO);
        return clientDTO;
    }*/


    //to odczytuje automatycznie z kolejki taki wzorzec projektowy obserwator ale nie działa
    //przy save wywoluje sie normalnie bez zadnego bledu tylko nie dodaje klienta do bazy
    //przy save and flush pokazuje ze nie ma transakcji ale nie wiadomo czy w tym problem
    //(musi działać tak że leci rządanie na kolejke z potencjalnego front ten to odbiera i potem dodaje i do auth i do rent service
    //i tak że jak któryś serwis nei działa to musi być to cofnięcie dodania (tak jak w treści zad))
    @RabbitListener(queues = "add-user")
    public void addClient(Message message) {

        UserPayload userPayload = (UserPayload) rabbitTemplate.getMessageConverter().fromMessage(message);
        //ClientDTO clientDTO = new ClientDTO(userPayload.getKey(), userPayload.getName(), userPayload.getSurname(),
        //        userPayload.isActive());
        //addClientUseCase.addClient(clientDTO);
        UserDTO userDTO = new UserDTO(userPayload.getLogin(), userPayload.getPassword(), userPayload.getName(),
                userPayload.getSurname(), "Normal");
        addUserUseCase.addUser(userDTO);
    }

    /*@GetMapping("/queue-delete")
    public void deleteClient() {
        String msg = (String)rabbitTemplate.receiveAndConvert("delete-user");
        UUID uuid = UUID.fromString(msg);
        deleteClientUseCase.deleteClientByKey(uuid);
    }*/

    //Listener nie działa tak samo ja ten do dodawania co jest jeszcze dziwniejsze.
    // Odpala się normalnie nie pokazuje zadnego bledu tylko nie usuwa kurwysyna z bazy
    /*@RabbitListener(queues = "delete-user")
    public void deleteClient(String message) {
        UUID uuid = UUID.fromString(message);
        deleteClientUseCase.deleteClientByKey(uuid);
        //Logger.getGlobal().info(message);
    }

    @PutMapping
    public void updateClient(@RequestBody ClientDTO clientDTO) {
        updateClientUseCase.updateClient(clientDTO.getId(), clientDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable int id) {
        deleteClientUseCase.deleteClient(id);
    }*/
}
