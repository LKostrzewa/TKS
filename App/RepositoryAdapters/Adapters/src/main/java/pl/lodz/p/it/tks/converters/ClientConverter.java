package pl.lodz.p.it.tks.converters;

import org.springframework.transaction.annotation.Transactional;
import pl.lodz.p.it.tks.data.*;
import pl.lodz.p.it.tks.model.*;

//tu dodalem z nadzieja ze pomoze w dodawaniu
//ale nie pomoglo to zakomentowuje
//@Transactional
public class ClientConverter {

    public ClientEnt convertClient(Client client) {
        ClientEnt clientEnt = new ClientEnt(client.getId(), client.getName(), client.getSurname(), client.getType().toString(), client.getKey());
        clientEnt.setActive(client.isActive());
        return clientEnt;
    }

    public Client convertClientEnt(ClientEnt clientEnt){
        ClientType clientType;
        if(clientEnt.getClientType().equals("Normal")) clientType = new NormalClient();
        else if(clientEnt.getClientType().equals("Regular")) clientType = new RegularClient();
        else clientType = new PremiumClient();
        Client client = new Client(clientEnt.getId(), clientEnt.getName(), clientEnt.getSurname(), clientType, clientEnt.getKey());
        client.setActive(clientEnt.isActive());
        return client;
    }
}
