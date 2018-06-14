package fr.mygms.sixkipren.modele;
import java.util.ArrayList;
import java.util.List;

import fr.mygms.sixkipren.exceptions.PaquetVideException;

public class Paquet {

	private List<Carte> listeCartes;
	
	public Paquet(List<Carte> listeCartes) {
		this.listeCartes = listeCartes;
	}
	
	public Carte distribue() throws PaquetVideException {
		if (listeCartes.isEmpty()) {
			throw new PaquetVideException("Le paquet est vide, impossible de distribuer une nouvelle carte");
		}
		return listeCartes.remove(0);
	}
	
	public List<Carte> distribue(int nombreCartes) throws PaquetVideException {
		List<Carte> cartes = new ArrayList<>();
		for (int nb = 0 ; nb < nombreCartes ; nb++) {
			cartes.add(distribue());
		}
		return cartes;
	}
}
