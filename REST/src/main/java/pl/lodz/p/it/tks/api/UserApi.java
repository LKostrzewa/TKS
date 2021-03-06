package pl.lodz.p.it.tks.api;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.tks.dto.UserDTO;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import pl.lodz.p.it.tks.useCases.userUseCase.AddUserUseCase;
import pl.lodz.p.it.tks.useCases.userUseCase.DeleteUserUseCase;
import pl.lodz.p.it.tks.useCases.userUseCase.UtilsUserUseCase;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserApi {

    private AddUserUseCase addUserUseCase;
    private UtilsUserUseCase utilsUserUseCase;
    private RabbitTemplate rabbitTemplate;
    private DeleteUserUseCase deleteUserUseCase;

    @Autowired
    public UserApi(RabbitTemplate rabbitTemplate, AddUserUseCase addUserUseCase, DeleteUserUseCase deleteUserUseCase, UtilsUserUseCase utilsUserUseCase) {
        this.rabbitTemplate = rabbitTemplate;
        this.addUserUseCase = addUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.utilsUserUseCase = utilsUserUseCase;
    }

    /*@RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "auth"),
            exchange = @Exchange(value = "master"),
            key = "auth.addUser"
    ))*/
    @RabbitListener(queues = "auth")
    public void addUser(Message message){
        UserDTO userDTO = (UserDTO) rabbitTemplate.getMessageConverter().fromMessage(message);
        if(!addUserUseCase.addUser(userDTO)){
            //rabbitTemplate.convertAndSend("master","app.delete",userDTO.getKey());
            rabbitTemplate.convertAndSend("app-delete", userDTO.getKey());
        }
    }

    /*@RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "auth"),
            exchange = @Exchange(value = "master"),
            key = "auth.delete"
    ))*/
    @RabbitListener(queues = "auth-delete")
    public void deleteUser(String message){
        UUID key = UUID.fromString(message);
        deleteUserUseCase.deleteUserByKey(key);
    }

    @GetMapping("/{key}")
    public UserDTO getUser(@PathVariable UUID key) {
        return utilsUserUseCase.getUserByKey(key);
    }
}
