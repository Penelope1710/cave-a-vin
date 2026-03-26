package fr.eni.cave.heritage;

import fr.eni.cave.bo.Proprio;
import fr.eni.cave.bo.Utilisateur;
import fr.eni.cave.bo.client.Client;
import fr.eni.cave.dal.ClientRepository;
import fr.eni.cave.dal.ProprioRepository;
import fr.eni.cave.dal.UtilisateurRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@DataJpaTest
public class TestHeritage {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	UtilisateurRepository utilisateurRepository;

	@Autowired
	ProprioRepository proprioRepository;

	@Autowired
	ClientRepository clientRepository;

	@BeforeEach
	public void initDB() {
		List<Utilisateur> utilisateurs = new ArrayList<>();
		utilisateurs.add(Utilisateur
				.builder()
				.pseudo("harrisonford@email.fr")
				.password("IndianaJones3")
				.nom("Ford")
				.prenom("Harrison")
				.build());

		utilisateurs.add(Proprio
				.builder()
				.pseudo("georgelucas@email.fr")
				.password("Réalisateur&Producteur")
				.nom("Lucas")
				.prenom("George")
				.siret("12345678901234")
				.build());

		utilisateurs.add(Client
				.builder()
				.pseudo("natalieportman@email.fr")
				.password("MarsAttacks!")
				.nom("Portman")
				.prenom("Natalie")
				.build());

		// Contexte de la DB
		utilisateurs.forEach(e -> {
			entityManager.persist(e);
		});
	}

	@Test
	void test_findAll_Utilisateur() {
		List<Utilisateur> listeUtilisateur = utilisateurRepository.findAll();

		log.info(listeUtilisateur.toString());

		Assertions.assertThat(listeUtilisateur).isNotNull();
		Assertions.assertThat(listeUtilisateur).isNotEmpty();
		Assertions.assertThat(listeUtilisateur.size()).isEqualTo(3);
	}

	@Test
	void test_findAll_Proprio() {

		List<Proprio> proprios = proprioRepository.findAll();

		log.info(proprios.toString());

		Assertions.assertThat(proprios).isNotNull();
		Assertions.assertThat(proprios).isNotEmpty();
		Assertions.assertThat(proprios.size()).isEqualTo(1);
	}

	@Test
	void test_findAll_Client() {
		List<Client> clients = clientRepository.findAll();

		log.info(clients.toString());

		Assertions.assertThat(clients).isNotNull();
		Assertions.assertThat(clients).isNotEmpty();
		Assertions.assertThat(clients.size()).isEqualTo(1);
	}
}
