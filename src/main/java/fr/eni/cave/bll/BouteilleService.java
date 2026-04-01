package fr.eni.cave.bll;

import fr.eni.cave.bo.vin.Bouteille;

import java.util.List;

public interface BouteilleService {
	List<Bouteille> chargerToutesBouteilles();
	
	Bouteille chargerBouteilleParId(int idBouteille);

	List<Bouteille> chargerBouteillesParRegion(int idRegion);

	List<Bouteille> chargerBouteillesParCouleur(int idCouleur);
	
	Bouteille ajouter(Bouteille bouteille);
	
	void supprimer(int idBouteille);
}
