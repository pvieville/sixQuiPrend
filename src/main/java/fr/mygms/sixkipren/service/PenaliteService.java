package fr.mygms.sixkipren.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.mygms.sixkipren.modele.Carte;
import fr.mygms.sixkipren.modele.PileCarte;

@Service
public class PenaliteService {

	public int calculePenalite(PileCarte pileCarte) {
		if (pileCarte == null) {
			return 0;
		}
		return calculePenalite(pileCarte.getListeCartes());
	}
	
	public int calculePenalite(List<Carte> listeCartes) {
		if (listeCartes == null) {
			return 0;
		}
		int penalites = 0;
		for (Carte carte : listeCartes) {
			penalites = penalites + carte.getPenalite();
		}
		return penalites;
	}
}
