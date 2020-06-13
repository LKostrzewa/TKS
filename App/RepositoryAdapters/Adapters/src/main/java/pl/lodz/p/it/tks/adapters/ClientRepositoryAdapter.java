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
    public Client getClient(int id) {
        return converter.convertClientEnt(repository.getOne(id));
    }

    @Override
    public Client getClientByName(String name) {
        return converter.convertClientEnt(repository.getClientEntByName(name));
    }


    @Override
    public void addClient(Client client) {
        if(!repository.existsById(client.getId()))
            //to sie wywoluje ale nie dodaje do bazy -> czemu ?
            //zrobienie saveAndFlush daje wyjątek że nie ma transakcji otagowanie w @Transactional nie pomaga
            repository.save(converter.convertClient(client));
    }

    @Override
    public void deleteClient(int id) {
        repository.deleteById(id);
    }

    @Override
    public void updateClient(int id, Client client) {
        if(repository.existsById(id))
            repository.save(converter.convertClient(client));
    }
}
