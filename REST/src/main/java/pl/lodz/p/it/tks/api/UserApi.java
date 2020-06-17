package pl.lodz.p.it.tks.api;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.tks.dto.UserDTO;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import pl.lodz.p.it.tks.useCases.userUseCase.AddUserUseCase;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserApi {

    private AddUserUseCase addUserUseCase;
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public UserApi(RabbitTemplate rabbitTemplate, AddUserUseCase addUserUseCase) {
        this.rabbitTemplate = rabbitTemplate;
        this.addUserUseCase = addUserUseCase;
    }

    @RabbitListener(queues = "auth")
    public void addClient(Message message) {

        //UserPayload userPayload = (UserPayload) rabbitTemplate.getMessageConverter().fromMessage(message);
        UserDTO userDTO = (UserDTO) rabbitTemplate.getMessageConverter().fromMessage(message);
        //ClientDTO clientDTO = new ClientDTO(userPayload.getKey(), userPayload.getName(), userPayload.getSurname(),
        //        userPayload.isActive());
        //addClientUseCase.addClient(clientDTO);
        /*UserDTO userDTO = new UserDTO(userPayload.getLogin(), userPayload.getPassword(), userPayload.getName(),
                userPayload.getSurname(), userPayload.isActive(), userPayload.getKey());*/
        if(addUserUseCase.addUser(userDTO)){
            System.out.println("success");
        } else {
            System.out.println("failure");
        }
    }
}
