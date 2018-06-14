package fr.mygms.sixkipren.builder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.mygms.sixkipren.modele.Carte;

@Service
@Scope("singleton")
public class CarteBuilder {
	
	private int getPenalite(int valeur) {
		int penalite = 1;
		if (valeur%5 == 0) {
			penalite = 2;
		}
		if (valeur%10 == 0) {
			penalite = 3;
		}
		if (valeur%11 == 0) {
			penalite = 5;
		}
		if (valeur == 55) {
			penalite = 7;
		}
		return penalite;
	}
	
	public Carte creerCarte(int valeur) {
		return new Carte(valeur, getPenalite(valeur));
	}
}
