package pl.lodz.p.it.tks.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lodz.p.it.tks.converters.ClientViewConverter;
import pl.lodz.p.it.tks.dto.ClientDTO;
import pl.lodz.p.it.tks.ports.clientPort.AddClientPort;
import pl.lodz.p.it.tks.service.ClientService;
import pl.lodz.p.it.tks.useCases.clientUseCase.AddClientUseCase;
import pl.lodz.p.it.tks.useCases.clientUseCase.DeleteClientUseCase;
import pl.lodz.p.it.tks.useCases.clientUseCase.UpdateClientUseCase;
import pl.lodz.p.it.tks.useCases.clientUseCase.UtilsClientUseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ClientViewAdapter implements AddClientUseCase, UpdateClientUseCase, DeleteClientUseCase, UtilsClientUseCase {

    private ClientService clientService;
    private ClientViewConverter clientViewConverter;

    @Autowired
    public ClientViewAdapter(ClientService clientService) {
        this.clientService = clientService;
        clientViewConverter = new ClientViewConverter();
    }

    @Override
    public boolean addClient(ClientDTO client) {
        return clientService.addClient(clientViewConverter.convertClientDTO(client));
    }

    @Override
    public void deleteClient(int id) {
        clientService.deleteClient(id);
    }

    @Override
    public void deleteClientByKey(UUID key) {
        clientService.deleteClientByKey(key);
    }

    @Override
    public void updateClient(int id, ClientDTO client) {
        clientService.updateClient(id, clientViewConverter.convertClientDTO(client));
    }

    @Override
    public ClientDTO getClient(int id) {
        return clientViewConverter.convertClient(clientService.getClient(id));
    }

    @Override
    public ClientDTO getClientByName(String name) {
        return clientViewConverter.convertClient(clientService.getClientByName(name));
    }

    @Override
    public List<ClientDTO> getAllClients() {
        List<ClientDTO> clientDTOS = new ArrayList<>();
        clientService.getAllClients().forEach(client -> clientDTOS.add(clientViewConverter.convertClient(client)));
        return clientDTOS;
    }

    @Override
    public List<ClientDTO> getAllActiveClients() {
        List<ClientDTO> clientDTOS = new ArrayList<>();
        clientService.getAllActiveClients().forEach(client -> clientDTOS.add(clientViewConverter.convertClient(client)));
        return clientDTOS;
    }
}
