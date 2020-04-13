package pl.lodz.p.it.tks.converters;

import pl.lodz.p.it.tks.dto.*;
import pl.lodz.p.it.tks.model.*;

public class UserViewConverter {
    public ClientType convertClientTypeDTO(ClientTypeDTO clientTypeDTO){
        if(clientTypeDTO instanceof NormalClient) return new NormalClient();
        else if(clientTypeDTO instanceof RegularClient) return new RegularClient();
        else return new PremiumClient();
    }

    public User convertUserDTO(UserDTO userDTO){
        User user;
        if(userDTO instanceof ClientDTO){
            user = new Client(userDTO.getLogin(), userDTO.getPassword(), userDTO.getName(), userDTO.getSurname(), convertClientTypeDTO(((ClientDTO) userDTO).getType()));
        }
        else if(userDTO instanceof AdministratorDTO){
            user = new Administrator(userDTO.getLogin(), userDTO.getPassword(), userDTO.getName(), userDTO.getSurname());
        }
        else {
            user = new Manager(userDTO.getLogin(), userDTO.getPassword(), userDTO.getName(), userDTO.getSurname());
        }
        user.setActive(userDTO.isActive());
        user.setMatchingPassword(userDTO.getMatchingPassword());
        return user;
    }

    public ClientDTO convertClient(Client client){
        ClientTypeDTO clientTypeDTO;
        if(client.getType() instanceof NormalClient) clientTypeDTO = new NormalClientDTO();
        else if(client.getType() instanceof RegularClient) clientTypeDTO = new RegularClientDTO();
        else clientTypeDTO = new PremiumClientDTO();
        ClientDTO clientDTO = new ClientDTO(client.getLogin(), client.getPassword(), client.getName(), client.getSurname(), clientTypeDTO);
        clientDTO.setActive(client.isActive());
        clientDTO.setMatchingPassword(client.getMatchingPassword());
        return clientDTO;
    }

    public AdministratorDTO convertAdministrator(Administrator administrator){
        AdministratorDTO administratorDTO = new AdministratorDTO(administrator.getLogin(), administrator.getPassword(), administrator.getName(), administrator.getSurname());
        administratorDTO.setActive(administrator.isActive());
        administratorDTO.setMatchingPassword(administrator.getMatchingPassword());
        return administratorDTO;
    }

    public ManagerDTO convertManager(Manager manager){
        ManagerDTO managerDTO = new ManagerDTO(manager.getLogin(), manager.getPassword(), manager.getName(), manager.getSurname());
        managerDTO.setActive(manager.isActive());
        managerDTO.setMatchingPassword(manager.getMatchingPassword());
        return managerDTO;
    }
}
