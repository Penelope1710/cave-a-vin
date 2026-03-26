package fr.eni.cave.bll;

import fr.eni.cave.bo.client.Client;
import fr.eni.cave.dal.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Transactional
    @Override
    public void ajouter(Client c) {

        if (c == null) {
            throw new RuntimeException("Le client n'est pas renseigné");
        }
        if(c.getPseudo() == null || c.getPseudo().isBlank()){
            throw new RuntimeException("Le pseudo du client doit être renseigné");
        }

        if(c.getPassword()== null || c.getPassword().isBlank()){
            throw new RuntimeException("Le mot de passe du client doit être renseigné");
        }

        if(c.getPrenom() == null || c.getPrenom().isBlank()){
            throw new RuntimeException("Le prénom du client doit être renseignée");
        }

        if(c.getNom() == null || c.getNom().isBlank()){
            throw new RuntimeException("Le nom du client doit être renseignée");
        }

        clientRepository.save(c);
    }

    @Override
    public void delete(String id) {

        clientRepository.deleteById(id);

    }
}
