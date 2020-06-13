package pl.lodz.p.it.tks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.tks.model.Client;
import pl.lodz.p.it.tks.model.NormalClient;
import pl.lodz.p.it.tks.ports.clientPort.AddClientPort;
import pl.lodz.p.it.tks.ports.clientPort.DeleteClientPort;
import pl.lodz.p.it.tks.ports.clientPort.GetClientPort;
import pl.lodz.p.it.tks.ports.clientPort.UpdateClientPort;

import javax.transaction.Transactional;
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

        //addClient(new Client("romek", "Roman", "Bialek", new NormalClient()));
    }

    public void addClient(Client client){
        addClientPort.addClient(client);
    }

    public Client getClient(int id) {
        return getClientPort.getClient(id);
    }

    public void deleteClient(int id) {
        deleteClientPort.deleteClient(id);
    }

    public void updateClient(int id, Client client) {
        updateClientPort.updateClient(id, client);
    }

    public List<Client> getAllClients(){
        return getClientPort.getAllClients();
    }

    public List<Client> getAllActiveClients(){
        return getClientPort.getAllActiveClients();
    }

    public Client getClientByName(String name) {
        return getClientPort.getClientByName(name);
    }
}
