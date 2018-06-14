package fr.mygms.sixkipren.modele.joueur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.mygms.sixkipren.exceptions.ActionImpossibleException;
import fr.mygms.sixkipren.modele.Carte;
import fr.mygms.sixkipren.service.PenaliteService;

public abstract class JoueurAbstract implements Joueur {

	protected String nom;
	protected List<Carte> main = new ArrayList<>();
	protected List<Carte> penalite = new ArrayList<>();

    @Autowired
    private PenaliteService penaliteService;
    
	@Override
	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public void setMain(List<Carte> main) {
		this.main = main;
		Collections.sort(this.main);
	}

	@Override
	public List<Carte> getMain() {
		return main;
	}

	@Override
	public void addListeCartesPenalites(List<Carte> cartes) {
		penalite.addAll(cartes);
	}

	@Override
	public void addCartePenalite(Carte carte) {
		penalite.add(carte);
	}

	@Override
	public Carte getCarte(int valeur) throws ActionImpossibleException {
		for (Carte carteMain : main) {
			if (carteMain.getValeur() == valeur) {
				Carte carte = carteMain;
				main.remove(carteMain);
				return carte;
			}
		}
		throw new ActionImpossibleException("La carte n'existe pas dans la main du joueur");
	}
	
	@Override
	public int getPenalites() {
		return penaliteService.calculePenalite(penalite);
	}

	@Override
	public String toString() {
		String mainCartes = "Main: ";
		for (Carte carte : main) {
			mainCartes += carte + " | ";
		}
		return nom + "(" + getPenalites() + ")\n" + mainCartes;
	}

	@Override
	public int compareTo(Joueur joueur) {
		return getPenalites() - joueur.getPenalites();
	}

	@Override
	public abstract Carte choixCarte();
	
	@Override
	public abstract int choixPile();
}
