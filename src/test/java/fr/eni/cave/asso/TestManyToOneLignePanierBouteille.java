package fr.eni.cave.asso;

import fr.eni.cave.bo.vin.Bouteille;
import fr.eni.cave.bo.vin.Couleur;
import fr.eni.cave.bo.client.LignePanier;
import fr.eni.cave.bo.vin.Region;
import fr.eni.cave.dal.BouteilleRepository;
import fr.eni.cave.dal.LignePanierRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.util.List;
import java.util.Optional;

@Slf4j
@DataJpaTest
public class TestManyToOneLignePanierBouteille {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	LignePanierRepository repository;

	@Autowired
	BouteilleRepository bouteilleRepository;
	

	private Bouteille b1;

	@BeforeEach
	public void initDB() {
		final Couleur blanc = Couleur
				.builder()
				.nom("Blanc")
				.build();

		entityManager.persist(blanc);

		final Region paysDeLaLoire = Region
				.builder()
				.nom("Pays de la Loire")
				.build();

		entityManager.persist(paysDeLaLoire);
		entityManager.flush();

		b1 = Bouteille
				.builder()
				.nom("DOMAINE ENI Ecole")
				.millesime("2022")
				.prix(23.95f)
				.quantite(1298)
				.region(paysDeLaLoire)
				.couleur(blanc)
				.build();
		entityManager.persist(b1);
		entityManager.flush();
	}


	@Test
	void test_save() {

		// Création de la ligne panier associée à la bouteille b1
		LignePanier ligne = LignePanier.builder()
				.quantite(4)
				.prix(23.95f)
				.bouteille(b1)
				.build();

		// Sauvegarde via le repository
		LignePanier ligneDB = repository.save(ligne);

		// Vérification que la ligne a bien été enregistrée
		Assertions.assertThat(ligneDB.getId()).isGreaterThan(0);
		Assertions.assertThat(ligneDB.getQuantite()).isEqualTo(4);
		Assertions.assertThat(ligneDB.getPrix()).isEqualTo(23.95f);

		// Vérification de la relation ManyToOne avec la bouteille
		Assertions.assertThat(ligneDB.getBouteille()).isNotNull();
		Assertions.assertThat(ligneDB.getBouteille().getId()).isEqualTo(b1.getId());
		Assertions.assertThat(ligneDB.getBouteille().getNom()).isEqualTo("DOMAINE ENI Ecole");
		Assertions.assertThat(ligneDB.getBouteille().getMillesime()).isEqualTo("2022");
	}

	@Test
	void test_Delete() {
		LignePanier ligne = LignePanier.builder()
				.quantite(4)
				.prix(23.95f)
				.bouteille(b1)
				.build();

		LignePanier ligneDB = repository.save(ligne);
		repository.flush();
		entityManager.clear();

		log.info(ligneDB.toString());
		Assertions.assertThat(ligneDB.getId()).isGreaterThan(0);

		Integer ligneID = ligneDB.getId();
		Integer bouteilleID = b1.getId();

		repository.deleteById(ligneID);
		repository.flush();
		entityManager.clear();

		Optional<LignePanier> optionalLignePanier = repository.findById(ligneID);
		Assertions.assertThat(optionalLignePanier).isEmpty();

		List<Bouteille> listeBouteille = bouteilleRepository.findAll();

		log.info(listeBouteille.toString());
		Assertions.assertThat(listeBouteille.size()).isEqualTo(1);

	}


}
