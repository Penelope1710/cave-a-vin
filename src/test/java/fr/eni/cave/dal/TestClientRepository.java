package fr.eni.cave.dal;

import fr.eni.cave.bo.Client;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

@Slf4j
@DataJpaTest
public class TestClientRepository {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void test_save() {
        Client c1 = Client.builder()
                .pseudo("jdoe")
                .password("secret123")
                .prenom("John")
                .nom("Doe")
                .build();

        clientRepository.save(c1);
        clientRepository.flush();
        entityManager.clear();

        Optional<Client> optionalClient = clientRepository.findById("jdoe");

        log.info(optionalClient.get().toString());

        Assertions.assertThat(optionalClient).isPresent();
    }

    @Test
    void test_delete() {
        Client c1 = Client.builder()
                .pseudo("jdoe")
                .password("secret123")
                .prenom("John")
                .nom("Doe")
                .build();

        clientRepository.save(c1);
        clientRepository.flush();
        entityManager.clear();

        clientRepository.delete(c1);
        clientRepository.flush();
        entityManager.clear();

        Optional<Client> optionalClient = clientRepository.findById("jdoe");

        Assertions.assertThat(optionalClient).isEmpty();
    }
}
