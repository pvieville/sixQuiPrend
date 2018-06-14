package fr.mygms.sixkipren.builder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.mygms.sixkipren.modele.Carte;
import fr.mygms.sixkipren.modele.CarteProbabilite;

@Service
@Scope("singleton")
public class CarteProbabiliteBuilder {

	public CarteProbabilite creerCarteProbabilite(Carte carte, int probabilite) {
		CarteProbabilite carteProbabilite = new CarteProbabilite();
		carteProbabilite.setCarte(carte);
		carteProbabilite.setProbabilite(probabilite);
		carteProbabilite.setTropPetite(false);
		return carteProbabilite;
	}
	
	public CarteProbabilite creerCarteProbabiliteTropPetite(Carte carte) {
		CarteProbabilite carteProbabilite = new CarteProbabilite();
		carteProbabilite.setCarte(carte);
		carteProbabilite.setProbabilite(0);
		carteProbabilite.setTropPetite(true);
		return carteProbabilite;
	}
}
