package pl.lodz.p.it.tks.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lodz.p.it.tks.converters.ClientConverter;
import pl.lodz.p.it.tks.data.ClientEnt;
import pl.lodz.p.it.tks.db.ClientDBRepository;
import pl.lodz.p.it.tks.model.Client;
import pl.lodz.p.it.tks.ports.clientPort.AddClientPort;
import pl.lodz.p.it.tks.ports.clientPort.DeleteClientPort;
import pl.lodz.p.it.tks.ports.clientPort.GetClientPort;
import pl.lodz.p.it.tks.ports.clientPort.UpdateClientPort;
import pl.lodz.p.it.tks.repository.ClientRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientRepositoryAdapter implements AddClientPort, GetClientPort, DeleteClientPort, UpdateClientPort {
    //private ClientRepository repository;
    private ClientDBRepository repository;
    private ClientConverter converter;

    @Autowired
    public ClientRepositoryAdapter(ClientDBRepository repository) {
        this.repository = repository;
        converter = new ClientConverter();
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        for (ClientEnt clientEnt : repository.findAll()){
            clients.add(converter.convertClientEnt(clientEnt));
        }
        return clients;
    }

    @Override
    public List<Client> getAllActiveClients() {
        List<Client> clients = new ArrayList<>();
        for (ClientEnt clientEnt : repository.getClientEntByActiveIsTrue()){
            clients.add(converter.convertClientEnt(clientEnt));
        }
        return clients;
    }

    @Override
    public Client getClient(String id) {
        return converter.convertClientEnt(repository.getOne(id));
    }


    @Override
    public void addClient(Client client) {
        if(!repository.existsById(client.getId()))
            repository.addClient(converter.convertClient(client));
    }

    @Override
    public void deleteClient(String id) {
        repository.deleteById(id);
    }

    @Override
    public void updateClient(String id, Client client) {
        if(repository.existsById(id))
            repository.save(converter.convertClient(client));
    }
}
