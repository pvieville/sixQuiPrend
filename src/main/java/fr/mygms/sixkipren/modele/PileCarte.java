package fr.mygms.sixkipren.modele;
import java.util.ArrayList;
import java.util.List;

import fr.mygms.sixkipren.exceptions.ActionImpossibleException;
import fr.mygms.sixkipren.exceptions.SixQuiPrendException;

public class PileCarte implements Comparable<PileCarte> {

	private List<Carte> listeCartes;
	
	public PileCarte(Carte carte) {
		listeCartes = new ArrayList<>();
		listeCartes.add(carte);
	}
	
	public List<Carte> getListeCartes() {
		return listeCartes;
	}

	public void ajouteCarte(Carte carte) throws ActionImpossibleException, SixQuiPrendException {
		if (derniereCarte().compareTo(carte) >= 0) {
			throw new ActionImpossibleException("La carte ne peut pas etre ajoutee : celle ci est plus petite que la carte au sommet");
		}
		if (listeCartes.size() >= 5) {
			throw new SixQuiPrendException("Le joueur doit ramasser la liste des cartes.");
		}
		listeCartes.add(carte);
	}
	
	public List<Carte> ramasseCartes(Carte carte) throws ActionImpossibleException {
		
		if (listeCartes.size() < 5 && derniereCarte().compareTo(carte) <= 0) {
			throw new ActionImpossibleException("Impossible de ramasser : cette colonne peut etre rempli et la carte au sommet est plus petite que la carte a ajouter");
		}
		
		List<Carte> pileARamasser = copieListeCartes();
		listeCartes = new ArrayList<>();
		listeCartes.add(carte);
		return pileARamasser;
	}
	
	private List<Carte> copieListeCartes() {
		List<Carte> pileARamasser = new ArrayList<>();
		for (Carte carteARamasser : listeCartes) {
			pileARamasser.add(carteARamasser);
		}
		return pileARamasser;
	}
	
	public Carte derniereCarte() {
		return listeCartes.get(listeCartes.size() - 1);
	}
	
	public int nbCartes() {
		return listeCartes.size();
	}
	
	public boolean peutJouer(Carte carte) {
		return derniereCarte().compareTo(carte) <= 0;
	}
	
	public String toString() {
		String cartes = "";
		for (Carte carte : listeCartes) {
			cartes += carte + " ";
		}
		return cartes;
	}

	@Override
	public int compareTo(PileCarte pileCarte) {
		return derniereCarte().compareTo(pileCarte.derniereCarte());
	}
}
