package pl.lodz.p.it.tks.converters;

import pl.lodz.p.it.tks.dto.*;
import pl.lodz.p.it.tks.model.*;

public class ClientViewConverter {

    public ClientType convertClientTypeDTO(ClientTypeDTO clientTypeDTO){
        if(clientTypeDTO instanceof NormalClient) return new NormalClient();
        else if(clientTypeDTO instanceof RegularClient) return new RegularClient();
        else return new PremiumClient();
    }

    public Client convertClientDTO(ClientDTO clientDTO) {
        return new Client(clientDTO.getLogin(), clientDTO.getPassword(), clientDTO.getName(), clientDTO.getSurname(), convertClientTypeDTO((clientDTO).getType()));
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
}
