package pl.lodz.p.it.tks.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
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
import java.util.UUID;

@Component
@Transactional
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
    @Transactional
    public void addClient(Client client) {
        repository.save(converter.convertClient(client));
        //sugestia sprawdzanie czy dodawanie przebiegło pomyślnie
        // moze jakis wyjatek ze nie ma polaczenia z baza tez ciezko powiedziec :(?
        repository.existsByKey(client.getKey());
    }

    @Override
    public void deleteClient(int id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByKey(UUID uuid) {
        repository.deleteByKey(uuid);
        repository.flush();
    }

    @Override
    public void updateClient(int id, Client client) {
        if(repository.existsById(id))
            repository.save(converter.convertClient(client));
    }
}
