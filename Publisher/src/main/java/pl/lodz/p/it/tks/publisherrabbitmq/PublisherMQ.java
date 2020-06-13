package pl.lodz.p.it.tks.publisherrabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.tks.payloads.UserPayload;

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
        userPayload.setKey(UUID.randomUUID());
        rabbitTemplate.convertAndSend("add-user", userPayload);
    }

    @PostMapping("/delete")
    public void publishBusinessKey(@RequestParam String key) {
        rabbitTemplate.convertAndSend("delete-user", key);
    }
}
