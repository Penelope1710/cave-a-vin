//package fr.eni.cave.bll;
//
//import fr.eni.cave.bo.client.Client;
//import fr.eni.cave.dal.ClientRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//public class TestClientService {
//
//    @Autowired
//    private ClientService clientService;
//
//    @Autowired
//    private ClientRepository clientRepository;
//
//    // ===== Tests pour ajouter =====
//
//    @Test
//    void testAjouter_ClientValide() {
//        Client client = Client.builder()
//                .pseudo("jdoe")
//                .password("secret123")
//                .prenom("John")
//                .nom("Doe")
//                .build();
//
//        clientService.ajouter(client);
//
//        assertTrue(clientRepository.findById("jdoe").isPresent());
//    }
//
//    @Test
//    void testAjouter_ClientNull() {
//        RuntimeException ex = assertThrows(RuntimeException.class, () -> clientService.ajouter(null));
//        assertEquals("Le client n'est pas renseigné", ex.getMessage());
//    }
//
//    @Test
//    void testAjouter_PseudoNull() {
//        Client client = Client.builder()
//                .password("secret123")
//                .prenom("John")
//                .nom("Doe")
//                .build();
//
//        RuntimeException ex = assertThrows(RuntimeException.class, () -> clientService.ajouter(client));
//        assertEquals("Le pseudo du client doit être renseigné", ex.getMessage());
//    }
//
//    @Test
//    void testAjouter_PseudoBlank() {
//        Client client = Client.builder()
//                .pseudo("   ")
//                .password("secret123")
//                .prenom("John")
//                .nom("Doe")
//                .build();
//
//        RuntimeException ex = assertThrows(RuntimeException.class, () -> clientService.ajouter(client));
//        assertEquals("Le pseudo du client doit être renseigné", ex.getMessage());
//    }
//
//    @Test
//    void testAjouter_PasswordNull() {
//        Client client = Client.builder()
//                .pseudo("jdoe")
//                .prenom("John")
//                .nom("Doe")
//                .build();
//
//        RuntimeException ex = assertThrows(RuntimeException.class, () -> clientService.ajouter(client));
//        assertEquals("Le mot de passe du client doit être renseigné", ex.getMessage());
//    }
//
//    @Test
//    void testAjouter_PasswordBlank() {
//        Client client = Client.builder()
//                .pseudo("jdoe")
//                .password("  ")
//                .prenom("John")
//                .nom("Doe")
//                .build();
//
//        RuntimeException ex = assertThrows(RuntimeException.class, () -> clientService.ajouter(client));
//        assertEquals("Le mot de passe du client doit être renseigné", ex.getMessage());
//    }
//
//    @Test
//    void testAjouter_PrenomNull() {
//        Client client = Client.builder()
//                .pseudo("jdoe")
//                .password("secret123")
//                .nom("Doe")
//                .build();
//
//        RuntimeException ex = assertThrows(RuntimeException.class, () -> clientService.ajouter(client));
//        assertEquals("Le prénom du client doit être renseignée", ex.getMessage());
//    }
//
//    @Test
//    void testAjouter_PrenomBlank() {
//        Client client = Client.builder()
//                .pseudo("jdoe")
//                .password("secret123")
//                .prenom("   ")
//                .nom("Doe")
//                .build();
//
//        RuntimeException ex = assertThrows(RuntimeException.class, () -> clientService.ajouter(client));
//        assertEquals("Le prénom du client doit être renseignée", ex.getMessage());
//    }
//
//    @Test
//    void testAjouter_NomNull() {
//        Client client = Client.builder()
//                .pseudo("jdoe")
//                .password("secret123")
//                .prenom("John")
//                .build();
//
//        RuntimeException ex = assertThrows(RuntimeException.class, () -> clientService.ajouter(client));
//        assertEquals("Le nom du client doit être renseignée", ex.getMessage());
//    }
//
//    @Test
//    void testAjouter_NomBlank() {
//        Client client = Client.builder()
//                .pseudo("jdoe")
//                .password("secret123")
//                .prenom("John")
//                .nom("   ")
//                .build();
//
//        RuntimeException ex = assertThrows(RuntimeException.class, () -> clientService.ajouter(client));
//        assertEquals("Le nom du client doit être renseignée", ex.getMessage());
//    }
//
//    // ===== Tests pour delete =====
//
//    @Test
//    void testDelete_ClientExistant() {
//        Client client = Client.builder()
//                .pseudo("todelete")
//                .password("secret123")
//                .prenom("John")
//                .nom("Doe")
//                .build();
//        clientRepository.save(client);
//
//        clientService.delete("todelete");
//
//        assertFalse(clientRepository.findById("todelete").isPresent());
//    }
//}