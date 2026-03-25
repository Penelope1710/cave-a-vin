package fr.eni.cave.asso;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.stream.Collectors;

import fr.eni.cave.bo.LignePanier;
import fr.eni.cave.bo.Panier;
import fr.eni.cave.dal.LignePanierRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import fr.eni.cave.dal.PanierRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DataJpaTest
public class TestOneToManyUni {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    PanierRepository panierRepository;

    @Autowired
    LignePanierRepository lignePanierRepository;

    @Test
    void test_save_nouvelleLigne_nouveauPanier() {

        LignePanier lp = LignePanier
                .builder()
                .quantite(3)
                .prix(3 * 11.45f)
                .build();
        Panier panier = new Panier();

        panier.getLignesPanier().add(lp);
        panier.setPrixTotal(lp.getPrix());

        Panier panierDB = panierRepository.save(panier);
        panierRepository.flush();

        assertThat(panier.getId()).isGreaterThan(0);
    }

    @Test
    void test_save_nouvelleLigne_panier() {

        LignePanier lp1 = LignePanier.builder()
                .quantite(3)
                .prix(3 * 11.45f)
                .build();

        Panier panier = Panier.builder()
                .prixTotal(lp1.getPrix())
                .build();

        panier.getLignesPanier().add(lp1);

        lignePanierRepository.save(lp1);
        lignePanierRepository.flush();

        LignePanier lp2 = LignePanier.builder()
                .quantite(2)
                .prix(2 * 14.70f)
                .build();

        panier.getLignesPanier().add(lp2);
        panierRepository.save(panier);
        panierRepository.flush();

        Panier panierDB = panierRepository.findById(panier.getId()).get();
        assertThat(panierDB.getLignesPanier().size()).isEqualTo(2);
        assertThat(panierDB.getLignesPanier().get(1).getId()).isGreaterThan(0);
    }
}
