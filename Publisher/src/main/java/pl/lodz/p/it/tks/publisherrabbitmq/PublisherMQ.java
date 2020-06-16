package pl.lodz.p.it.tks.publisherrabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.tks.dto.ClientDTO;
import pl.lodz.p.it.tks.dto.UserDTO;

import java.util.UUID;

@RestController
public class PublisherMQ {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public PublisherMQ(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping
    public void publishUser(@RequestBody UserPayload userPayload) {
        UUID uuid = UUID.randomUUID();
        UserDTO userDTO = new UserDTO(userPayload.getLogin(), userPayload.getPassword(), userPayload.getName(), userPayload.getSurname(), userPayload.isActive(), uuid);
        ClientDTO clientDTO = new ClientDTO(uuid, userPayload.getName(), userPayload.getSurname(), userPayload.isActive());
        rabbitTemplate.convertAndSend("auth", userDTO);
        rabbitTemplate.convertAndSend("app", clientDTO);
    }

    @PostMapping("/delete")
    public void publishBusinessKey(@RequestParam String key) {
        rabbitTemplate.convertAndSend("delete-user", key);
    }
}
