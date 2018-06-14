package fr.mygms.sixkipren.modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.mygms.sixkipren.exceptions.ActionImpossibleException;
import fr.mygms.sixkipren.exceptions.SixQuiPrendException;

public class Plateau {

	private List<PileCarte> listePiles;
	
	public Plateau(Carte carte1, Carte carte2, Carte carte3, Carte carte4) {
		listePiles.add(new PileCarte(carte1));
		listePiles.add(new PileCarte(carte2));
		listePiles.add(new PileCarte(carte3));
		listePiles.add(new PileCarte(carte4));
	}
	
	
	public List<PileCarte> getListePiles() {
		return listePiles;
	}

	public PileCarte getPile(int pile) throws ActionImpossibleException {
		if (0 < pile && pile <= listePiles.size()) {
			return listePiles.get(pile-1);
		}
		throw new ActionImpossibleException("Pile inexistante");
	}
	
	public List<Carte> jouerSurPile(int pile, Carte carte) throws ActionImpossibleException {
		if (0 < pile && pile <= listePiles.size()) {
			return listePiles.get(pile-1).ramasseCartes(carte);
		}
		throw new ActionImpossibleException("Pile inexistante");
	}
	
	public boolean jouer(Carte carte) throws SixQuiPrendException {
		
		List<PileCarte> listeCartes = new ArrayList<>();
		for (PileCarte pileCarte : listePiles) {
			if (pileCarte.peutJouer(carte)) {
				listeCartes.add(pileCarte); 
			}
		}
		
		if (listeCartes.isEmpty()) {
			return false;
		}
		
		Collections.sort(listeCartes, Collections.reverseOrder());
		
		try {
			listeCartes.get(0).ajouteCarte(carte);
		} catch (ActionImpossibleException e) {
			// impossible que ca arrive
			System.out.println(e.getMessage());
		} catch (SixQuiPrendException e) {
			e.setPileCarte(numPileCarte(listeCartes.get(0)));
			throw e;
		}
		
		return true;
	}
	
	public int numPileCarte(PileCarte pileCarte) {
		for (int i = 1 ; i <= listePiles.size() ; i++) {
			if (pileCarte == listePiles.get(i-1)) {
				return i;
			}
		}
		return -1;
	}
	
	public String toString() {
		String retour = "";
		for (PileCarte pileCarte : listePiles) {
			retour += numPileCarte(pileCarte) + ": " + pileCarte + "\n";
		}
		return retour;
	}
}
