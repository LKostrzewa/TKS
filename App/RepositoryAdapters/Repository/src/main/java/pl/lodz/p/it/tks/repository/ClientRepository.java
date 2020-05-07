package pl.lodz.p.it.tks.repository;

import org.springframework.stereotype.Repository;
import pl.lodz.p.it.tks.data.ClientEnt;
import pl.lodz.p.it.tks.data.UserEnt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ClientRepository extends RepositoryTemplate<ClientEnt> {

    public List<ClientEnt> getAllActiveClients(){
        return getAll().stream().filter(ClientEnt::isActive).collect(Collectors.toList());
    }}
