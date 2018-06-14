package fr.mygms.sixkipren.builder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.mygms.sixkipren.modele.Carte;
import fr.mygms.sixkipren.modele.Paquet;

@Service
public class PaquetBuilder {

	private static final int VALEUR_MAX_DEFAUT = 104;
	
	@Autowired
	private CarteBuilder carteBuilder;

	public Paquet donnePaquet() {
		return donnePaquet(VALEUR_MAX_DEFAUT);
	}
	
	public Paquet donnePaquet(int valeurMax) {
		
		List<Carte> listeCarte = creeListeCartes(valeurMax);
		melangeListeCartes(listeCarte);
		
		return new Paquet(listeCarte);
	}

	private void melangeListeCartes(List<Carte> listeCarte) {
		Collections.shuffle(listeCarte);
	}

	private List<Carte> creeListeCartes(int valeurMax) {
		
		List<Carte> listeCarte = new ArrayList<>(valeurMax - 1);
		
		for (int valeur = 1 ; valeur <= valeurMax ; valeur++) {
			listeCarte.add(carteBuilder.creerCarte(valeur));
		}
		
		return listeCarte;
	}
}
