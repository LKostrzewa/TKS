package pl.lodz.p.it.tks.converters;

import pl.lodz.p.it.tks.data.*;
import pl.lodz.p.it.tks.model.*;

public class ClientConverter {

    public ClientEnt convertClient(Client client) {
        return new ClientEnt(client.getId(), client.getName(), client.getSurname(), convertClientType((client).getType()));
    }

    public ClientTypeEnt convertClientType(ClientType clientType){
        if(clientType instanceof NormalClient) return new NormalClientEnt();
        else if(clientType instanceof RegularClient) return new RegularClientEnt();
        else return new PremiumClientEnt();
    }

    public Client convertClientEnt(ClientEnt clientEnt){
        ClientType clientType;
        if(clientEnt.getType() instanceof NormalClientEnt) clientType = new NormalClient();
        else if(clientEnt.getType() instanceof RegularClientEnt) clientType = new RegularClient();
        else clientType = new PremiumClient();
        Client client = new Client(clientEnt.getId(), clientEnt.getName(), clientEnt.getSurname(), clientType);
        client.setActive(clientEnt.isActive());
        return client;
    }
}
