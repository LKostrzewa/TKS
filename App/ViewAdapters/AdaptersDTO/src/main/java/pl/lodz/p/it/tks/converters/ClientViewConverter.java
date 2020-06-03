package pl.lodz.p.it.tks.converters;

import pl.lodz.p.it.tks.dto.*;
import pl.lodz.p.it.tks.model.*;

public class ClientViewConverter {

    public Client convertClientDTO(ClientDTO clientDTO) {
        ClientType clientType;
        switch (clientDTO.getClientType()){
            case "Normal":
                clientType = new NormalClient();
                break;
            case "Regular":
                clientType = new RegularClient();
                break;
            default:
                clientType = new PremiumClient();
        }
        Client client = new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getSurname(), clientType, clientDTO.getKey());
        client.setActive(clientDTO.isActive());
        return client;
    }

    public ClientDTO convertClient(Client client){
        ClientDTO clientDTO = new ClientDTO(client.getId(), client.getName(), client.getSurname(), client.getType().toString());
        clientDTO.setActive(client.isActive());
        clientDTO.setKey(client.getKey());
        return clientDTO;
    }
}
