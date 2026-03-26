package fr.eni.cave.jpql;

import fr.eni.cave.bo.Bouteille;
import fr.eni.cave.bo.Couleur;
import fr.eni.cave.bo.LignePanier;
import fr.eni.cave.bo.Region;
import fr.eni.cave.dal.LignePanierRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.util.Optional;

@Slf4j
@DataJpaTest
public class TestManyToOneLignePanierBouteille {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	LignePanierRepository repository;
	

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

		LignePanier lignePanier = LignePanier.builder()
				.quantite(1)
				.prix(23.95f)
				.bouteille(b1)
				.build();

		LignePanier ligneDB = repository.save(lignePanier);

		//vérification de l'insertion
		log.info(ligneDB.toString());
		Assertions.assertThat(ligneDB.getId()).isGreaterThan(0);

		//vérification de l'association
		Assertions.assertThat(ligneDB.getBouteille()).isNotNull();
		Assertions.assertThat(ligneDB.getBouteille().getId()).isEqualTo(b1.getId());
	}

	@Test
	void test_delete() {

		LignePanier lignePanier = LignePanier.builder()
				.quantite(1)
				.prix(23.95f)
				.bouteille(b1)
				.build();

		LignePanier ligneDB = repository.save(lignePanier);
		repository.flush();
		entityManager.clear();

		log.info(ligneDB.toString());

		Assertions.assertThat(ligneDB.getId()).isGreaterThan(0);

		Integer idLignePanier = ligneDB.getId();

		repository.deleteById(idLignePanier);
		repository.flush();
		entityManager.clear();

		Optional<LignePanier> optionalLignePanier = repository.findById(idLignePanier);




	}
}
