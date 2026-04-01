package fr.eni.cave.dal;

import fr.eni.cave.bo.vin.Bouteille;
import fr.eni.cave.bo.vin.Couleur;
import fr.eni.cave.bo.vin.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BouteilleRepository extends JpaRepository<Bouteille, Integer> {

    //recherche des bouteilles par région
    List<Bouteille> findByRegion(String region);

    //Recherche des bouteilles par couleur
    List<Bouteille> findByRegion(Region region);

    Bouteille findByNom(String nom);

    List<Bouteille> findByCouleur(Couleur couleur);

}
