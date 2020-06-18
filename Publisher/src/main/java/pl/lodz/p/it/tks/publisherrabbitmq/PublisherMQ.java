package pl.lodz.p.it.tks.publisherrabbitmq;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pl.lodz.p.it.tks.dto.ClientDTO;
import pl.lodz.p.it.tks.dto.UserDTO;

import java.util.UUID;

@RestController
public class PublisherMQ {

    private final RabbitTemplate rabbitTemplate;
    private final RestTemplate restTemplate;

    @Autowired
    public PublisherMQ(RabbitTemplate rabbitTemplate, RestTemplate restTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public void publishUser(@RequestBody UserPayload userPayload) {
        UUID uuid = UUID.randomUUID();
        UserDTO userDTO = new UserDTO(userPayload.getLogin(), userPayload.getPassword(), userPayload.getName(), userPayload.getSurname(), userPayload.isActive(), uuid);
        ClientDTO clientDTO = new ClientDTO(uuid, userPayload.getName(), userPayload.getSurname(), userPayload.isActive());
        //rabbitTemplate.convertAndSend("master","app.addClient", clientDTO);
        //rabbitTemplate.convertAndSend("master","auth.addUser", userDTO);
        rabbitTemplate.convertAndSend("app", clientDTO);
        rabbitTemplate.convertAndSend("auth", userDTO);
    }

    @PostMapping("/delete")
    public void publishBusinessKey(@RequestParam String key) {
        //rabbitTemplate.convertAndSend("master","auth.delete", key);
        //rabbitTemplate.convertAndSend("master","app.delete", key);
        rabbitTemplate.convertAndSend("app-delete", key);
        rabbitTemplate.convertAndSend("auth-delete", key);
    }

    @GetMapping
    public UserPayload getUserData(@RequestParam UUID key) {
        String usersUrl = "http://localhost:8081/api/users";
        String clientsUrl = "http://localhost:8082/api/clients";
        UserDTO userDTO = restTemplate.getForObject(usersUrl + "/" + key.toString(), UserDTO.class);
        ClientDTO clientDTO = restTemplate.getForObject(clientsUrl + "/" + key.toString(), ClientDTO.class);

        return new UserPayload(userDTO.getLogin(), userDTO.getPassword(), userDTO.getName(), userDTO.getSurname()
                , userDTO.isActive(), userDTO.getKey(), clientDTO.getClientType());
    }
}
