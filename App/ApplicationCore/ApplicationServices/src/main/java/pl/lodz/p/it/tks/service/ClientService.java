package pl.lodz.p.it.tks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.tks.model.Client;
import pl.lodz.p.it.tks.model.NormalClient;
import pl.lodz.p.it.tks.model.User;
import pl.lodz.p.it.tks.ports.clientPort.AddClientPort;
import pl.lodz.p.it.tks.ports.clientPort.DeleteClientPort;
import pl.lodz.p.it.tks.ports.clientPort.GetClientPort;
import pl.lodz.p.it.tks.ports.clientPort.UpdateClientPort;

import java.util.List;

@Service
public class ClientService {

    private AddClientPort addClientPort;
    private GetClientPort getClientPort;
    private UpdateClientPort updateClientPort;
    private DeleteClientPort deleteClientPort;

    @Autowired
    public ClientService(AddClientPort addClientPort, GetClientPort getClientPort, UpdateClientPort updateClientPort, DeleteClientPort deleteClientPort) {
        this.addClientPort = addClientPort;
        this.getClientPort = getClientPort;
        this.updateClientPort = updateClientPort;
        this.deleteClientPort = deleteClientPort;

        addClient(new Client("romek", "password", "Roman", "Bialek", new NormalClient()));
    }

    public void addClient(Client client){
        client.setPassword(BCrypt.hashpw(client.getPassword(), BCrypt.gensalt()));
        addClientPort.addClient(client);
    }

    public Client getClient(String login) {
        return getClientPort.getClient(login);
    }

    public void deleteClient(String id) {
        deleteClientPort.deleteClient(id);
    }

    public void updateClient(String id, Client client) {
        updateClientPort.updateClient(id, client);
    }

    public List<Client> getAllClients(){
        return getClientPort.getAllClients();
    }

    public List<Client> getAllActiveClients(){
        return getClientPort.getAllActiveClients();
    }
}
