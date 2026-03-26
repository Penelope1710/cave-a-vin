package fr.eni.cave.asso;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.eni.cave.bo.Bouteille;
import fr.eni.cave.bo.Couleur;
import fr.eni.cave.bo.Region;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import fr.eni.cave.dal.BouteilleRepository;
import fr.eni.cave.dal.CouleurRepository;
import fr.eni.cave.dal.RegionRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DataJpaTest
public class TestManyToOne {


	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	BouteilleRepository bouteilleRepository;

	@Autowired
	CouleurRepository couleurRepository;
	
	@Autowired
	RegionRepository regionRepository;
	
	Couleur rouge;
	Couleur blanc;
	Couleur rose;
	
	Region grandEst;
	Region paysDeLaLoire;
	Region nouvelleAquitaine;

	@BeforeEach
	public void initDB() {
		rouge = Couleur.builder()
				.nom("Rouge")
				.build();
		
		blanc = Couleur.builder()
				.nom("Blanc")
				.build();
				
		rose = Couleur.builder()
				.nom("Rosé")
				.build();
				
		couleurRepository.save(rouge);
		couleurRepository.save(blanc);
		couleurRepository.save(rose);
				
		grandEst = 
				Region
				.builder()
				.nom("Grand Est")
				.build();
		
		paysDeLaLoire = 
				Region
				.builder()
				.nom("Pays de la Loire")
				.build();
		
		nouvelleAquitaine = 
				Region
				.builder()
				.nom("Nouvelle Aquitaine")
				.build();
		
		regionRepository.save(grandEst);
		regionRepository.save(paysDeLaLoire);
		regionRepository.save(nouvelleAquitaine);
	}

	@Test
	void test_save() {
		Bouteille bouteille = Bouteille.builder()
				.nom("Blanc du DOMAINE ENI Test")
				.millesime("2021")
				.prix(18.75f)
				.quantite(500)
				.region(grandEst)
				.couleur(blanc)
				.build();

		bouteille.setCouleur(blanc);
		bouteille.setRegion(grandEst);
		Bouteille bouteilleDB = bouteilleRepository.save(bouteille);

		log.info(bouteilleDB.toString());

		Assertions.assertThat(bouteilleDB.getId()).isGreaterThan(0);

		//Vérification des associations
		Assertions.assertThat(bouteilleDB.getCouleur()).isNotNull();
		Assertions.assertThat(bouteilleDB.getCouleur()).isEqualTo(blanc);
		Assertions.assertThat(bouteilleDB.getRegion()).isNotNull();
		Assertions.assertThat(bouteilleDB.getRegion()).isEqualTo(grandEst);
	}

	//ici on veut vérifier que le couleur et la région ne va pas se dupliquer en BDD
	@Test
	void test_bouteilles_regions_couleurs() {

		//Ma méthode
//		Bouteille bouteille1 = Bouteille.builder()
//				.nom("Rouge du DOMAINE ENI Test")
//				.millesime("2021")
//				.prix(18.75f)
//				.quantite(500)
//				.region(grandEst)
//				.couleur(rouge)
//				.build();
//
//		Bouteille bouteille2 = Bouteille.builder()
//				.nom("Rouge du DOMAINE ENI Ecole1")
//				.millesime("2018")
//				.prix(11.45f)
//				.quantite(987)
//				.region(grandEst)
//				.couleur(rouge)
//				.build();
//
//		Bouteille bouteille3 = Bouteille.builder()
//				.nom("Rouge du DOMAINE ENI Ecole2")
//				.millesime("2022")
//				.prix(23.95f)
//				.quantite(1298)
//				.region(grandEst)
//				.couleur(rouge)
//				.build();
//
//		//j'attribue la couleur
//		bouteille1.setCouleur(rouge);
//		bouteille2.setCouleur(rouge);
//		bouteille3.setCouleur(rouge);
//
//		//J'attribue la région
//		bouteille1.setRegion(grandEst);
//		bouteille2.setRegion(grandEst);
//		bouteille3.setRegion(grandEst);
//
//		//je save mes bouteilles
//		Bouteille bouteilleDB1 = bouteilleRepository.save(bouteille1);
//		Bouteille bouteilleDB2 = bouteilleRepository.save(bouteille2);
//		Bouteille bouteilleDB3 = bouteilleRepository.save(bouteille3);
//
//		log.info(bouteilleDB1.toString());
//		log.info(bouteilleDB2.toString());
//		log.info(bouteilleDB3.toString());
//
//		//je créé ma liste
//		List<Bouteille> listeBouteilles = bouteilleRepository.findAll();
//
//		log.info(listeBouteilles.toString());
//		//je vérifie que mes 3 bouteilles ont bien été créées
//		Assertions.assertThat(listeBouteilles.size()).isEqualTo(3);
//
//		//Je récupère mes couleurs puis je vérifie qu'il n'y a qu'une couleur rouge
//		 long nombreCouleurRouge = couleurRepository.findAll()
//				 .stream()
//				 .filter(c->c.getNom()
//						 .equals("Rouge"))
//				 .count();
//
//		 Assertions.assertThat(nombreCouleurRouge).isEqualTo(1);
//
//		//Je récupère mes couleurs puis je vérifie qu'il n'y a qu'une couleur rouge
//		long nombreRegionGrandEst = regionRepository.findAll()
//				.stream()
//				.filter(c->c.getNom()
//						.equals("Grand Est"))
//				.count();
//
//		Assertions.assertThat(nombreRegionGrandEst).isEqualTo(1);

		//AUTRE METHODE
		List<Bouteille> bouteilles = jeuDeDonnees();

		// sauver le jeu de données en base
		bouteilles.forEach(b ->{
			bouteilleRepository.save(b);
			assertThat(b.getId()).isGreaterThan(0);
			// Vérification des associations
			assertThat(b.getCouleur()).isNotNull();
			assertThat(b.getRegion()).isNotNull();
		});
		log.info(bouteilles.toString());


		List<Region> listeRegion = regionRepository.findAll();
		List<Couleur> listeCouleur = couleurRepository.findAll();

		assertThat(listeRegion.size()).isEqualTo(3);
		assertThat(listeCouleur.size()).isEqualTo(3);
	}

	@Test
	public void test_delete() {
		Bouteille bouteille = Bouteille
				.builder()
				.nom("Blanc du DOMAINE ENI Ecole")
				.millesime("2022")
				.prix(23.95f)
				.quantite(1298)
				.build();

		// Association ManyToOne
		bouteille.setRegion(paysDeLaLoire);
		bouteille.setCouleur(blanc);

		// Appel du comportement
		Bouteille bouteilleDB = bouteilleRepository.save(bouteille);
		bouteilleRepository.flush();
		// Vérification de l'identifiant
		assertThat(bouteilleDB.getId()).isGreaterThan(0);
		assertThat(bouteilleDB.getCouleur()).isNotNull();
		assertThat(bouteilleDB.getCouleur()).isEqualTo(blanc);
		assertThat(bouteilleDB.getRegion()).isNotNull();
		assertThat(bouteilleDB.getRegion()).isEqualTo(paysDeLaLoire);

		// Appel du comportement
		bouteilleRepository.delete(bouteilleDB);
		bouteilleRepository.flush();

		// Vérification que l'entité a été supprimée
		Optional<Bouteille> optionalBouteille = bouteilleRepository.findById(bouteilleDB.getId());
		assertThat(optionalBouteille).isEmpty();

		// Vérifier que les éléments associés sont toujours présents - PAS de cascade
		List<Region> regions = regionRepository.findAll();
		assertThat(regions).isNotNull();
		assertThat(regions).isNotEmpty();
		assertThat(regions.size()).isEqualTo(3);
		List<Couleur> couleurs = couleurRepository.findAll();
		assertThat(couleurs).isNotNull();
		assertThat(couleurs).isNotEmpty();
		assertThat(couleurs.size()).isEqualTo(3);
	}

	private List<Bouteille> jeuDeDonnees() {
		List<Bouteille> bouteilles = new ArrayList<>();
		bouteilles.add(Bouteille.builder()
				.nom("Blanc du DOMAINE ENI Ecole")
				.millesime("2022")
				.prix(23.95f)
				.quantite(1298)
				.region(paysDeLaLoire)
				.couleur(blanc)
				.build());
		bouteilles.add(Bouteille.builder()
				.nom("Rouge du DOMAINE ENI Ecole")
				.millesime("2018")
				.prix(11.45f)
				.quantite(987)
				.region(paysDeLaLoire)
				.couleur(rouge)
				.build());
		bouteilles.add(Bouteille.builder()
				.nom("Blanc du DOMAINE ENI Service")
				.millesime("2022")
				.prix(34)
				.petillant(true)
				.quantite(111)
				.region(grandEst)
				.couleur(blanc)
				.build());
		bouteilles.add(Bouteille.builder()
				.nom("Rouge du DOMAINE ENI Service")
				.millesime("2012")
				.prix(8.15f)
				.quantite(344)
				.region(paysDeLaLoire)
				.couleur(rouge)
				.build());
		bouteilles.add(Bouteille.builder()
				.nom("Rosé du DOMAINE ENI")
				.millesime("2020")
				.prix(33)
				.quantite(1987)
				.region(nouvelleAquitaine)
				.couleur(rose)
				.build());
		return bouteilles;
	}
}
