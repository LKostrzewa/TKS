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
    public void publishUser(@RequestBody UserDTO userDTO) {
        userDTO.setKey(UUID.randomUUID());
        rabbitTemplate.convertAndSend("auth", userDTO);
    }

    @PostMapping("/client")
    public void publishClient(@RequestBody ClientDTO clientDTO) {
        clientDTO.setKey(UUID.randomUUID());
        rabbitTemplate.convertAndSend("app", clientDTO);
    }

    @PostMapping("/delete")
    public void publishBusinessKey(@RequestParam String key) {
        rabbitTemplate.convertAndSend("delete-user", key);
    }
}
