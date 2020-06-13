package pl.lodz.p.it.tks.api;

import com.google.gson.Gson;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.tks.dto.ClientDTO;
import pl.lodz.p.it.tks.payloads.UserPayload;
import pl.lodz.p.it.tks.useCases.clientUseCase.*;

import java.util.List;
import java.util.UUID;

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

    @PostMapping
    public void addClient(@RequestBody ClientDTO clientDTO) {
        addClientUseCase.addClient(clientDTO);
    }
    //dziala
    @GetMapping("/queue")
    public ClientDTO addClient() {
        UserPayload userPayload = (UserPayload) rabbitTemplate.receiveAndConvert("add-user");
        ClientDTO clientDTO = new ClientDTO(userPayload.getKey(), userPayload.getName(), userPayload.getSurname(),
                userPayload.isActive());
        addClientUseCase.addClient(clientDTO);
        return clientDTO;
    }


    //to odczytuje automatycznie z kolejki taki wzorzec projektowy obserwator ale nie działa
    //(musi działać tak że leci rządanie na kolejke z potencjalnego front ten to odbiera i potem dodaje i do auth i do rent service
    //i tak że jak któryś serwis nei działa to musi być to cofnięcie dodania (tak jak w treści zad))
    /*@RabbitListener(queues = "add-user")
    public void addClient(Message message) throws InterruptedException {
        /*UserPayload userPayload = (UserPayload) rabbitTemplate.receiveAndConvert("add-user");
        return new ClientDTO(userPayload.getKey(), userPayload.getName(), userPayload.getSurname(),
                userPayload.isActive());*/
        /*System.out.println(message);
        UserPayload userPayload = (UserPayload) rabbitTemplate.getMessageConverter().fromMessage(message);
        System.out.println(userPayload.getName());
        ClientDTO clientDTO = new ClientDTO(userPayload.getKey(), userPayload.getName(), userPayload.getSurname(),
                userPayload.isActive());
        addClientUseCase.addClient(clientDTO);
        Thread.sleep(1000);
        System.out.println(clientDTO);
    }*/

    @GetMapping("/queue-delete")
    public void deleteClient() {
        String msg = (String)rabbitTemplate.receiveAndConvert("delete-user");
        //byte[] bytes = msg.getBody();
        //pobiera wiadomosc dobrze problem z transakcjami (TransactionRequiredException), tak jak przy uzywanie saveAndFlush
        UUID uuid = UUID.fromString(msg);
        deleteClientUseCase.deleteClientByKey(uuid);
    }

    //wzorzecz listnera do usuwania
    /*@RabbitListener(queues = "delete-user")
    public void deleteClient(String message) {
        UUID uuid = UUID.fromString(message);
        deleteClientUseCase.deleteClientByKey(uuid);
    }*/

    @PutMapping
    public void updateClient(@RequestBody ClientDTO clientDTO) {
        updateClientUseCase.updateClient(clientDTO.getId(), clientDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable int id) {
        deleteClientUseCase.deleteClient(id);
    }
}
