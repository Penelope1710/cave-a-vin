package fr.eni.cave.bll;

import fr.eni.cave.bo.client.Client;

public interface ClientService {

    void ajouter(Client c);
    void delete(String id);
}
