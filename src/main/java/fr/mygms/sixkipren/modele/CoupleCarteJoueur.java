package fr.mygms.sixkipren.modele;

import fr.mygms.sixkipren.modele.joueur.Joueur;

public class CoupleCarteJoueur implements Comparable<CoupleCarteJoueur> {

	private Carte carte;
	private Joueur joueur;
	
	public CoupleCarteJoueur(Carte carte, Joueur joueur) {
		super();
		this.carte = carte;
		this.joueur = joueur;
	}
	
	public Carte getCarte() {
		return carte;
	}
	public Joueur getJoueur() {
		return joueur;
	}

	@Override
	public int compareTo(CoupleCarteJoueur coupleCarteJoueur) {
		return carte.compareTo(coupleCarteJoueur.getCarte());
	}
}
