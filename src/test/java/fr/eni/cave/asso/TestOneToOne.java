package fr.eni.cave.asso;

import fr.eni.cave.bo.Adresse;
import fr.eni.cave.bo.Client;
import fr.eni.cave.dal.AdresseRepository;
import fr.eni.cave.dal.ClientRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@Slf4j
@DataJpaTest
public class TestOneToOne {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AdresseRepository adresseRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void test_create() {

        Client client = Client.builder()
                .pseudo("jdoe")
                .password("secret123")
                .prenom("John")
                .nom("Doe")
                .build();

        Adresse adresse = Adresse.builder()
                .rue("10 rue de la Paix")
                .codePostal("75001")
                .ville("Paris")
                .build();

        client.setAddress(adresse);
        adresseRepository.save(adresse);


        clientRepository.save(client);
    }
}
