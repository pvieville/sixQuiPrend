package fr.mygms.sixkipren.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.mygms.sixkipren.builder.JoueurBuilder;
import fr.mygms.sixkipren.builder.PaquetBuilder;
import fr.mygms.sixkipren.exceptions.ActionImpossibleException;
import fr.mygms.sixkipren.exceptions.PaquetVideException;
import fr.mygms.sixkipren.exceptions.SixQuiPrendException;
import fr.mygms.sixkipren.modele.Carte;
import fr.mygms.sixkipren.modele.CoupleCarteJoueur;
import fr.mygms.sixkipren.modele.Paquet;
import fr.mygms.sixkipren.modele.Plateau;
import fr.mygms.sixkipren.modele.joueur.Joueur;
import fr.mygms.sixkipren.modele.joueur.JoueurConsole;

@Service
public class Cinematique {

	@Autowired
	private PaquetBuilder paquetBuilder;
	
	@Autowired
	private JoueurBuilder joueurBuilder;

	private List<Joueur> listeJoueurs;
	private Paquet paquet;
	private Plateau plateau;
	
	public void lancement() {
		
		paquet = paquetBuilder.donnePaquet();
		
		listeJoueurs = new ArrayList<>();
		try {
			listeJoueurs.add(joueurBuilder.creerJoueurConsole("Joueur1", paquet.distribue(10)));
			listeJoueurs.add(joueurBuilder.creerJoueurConsole("Joueur2", paquet.distribue(10)));
			listeJoueurs.add(joueurBuilder.creerJoueurConsole("Joueur3", paquet.distribue(10)));
			plateau = new Plateau(paquet.distribue(), paquet.distribue(), paquet.distribue(), paquet.distribue());
			
			for (int i = 0 ; i < 10 ; i++) {
				tourDeJeu();
			}
			
			Collections.sort(listeJoueurs);
			System.out.println();
			System.out.println("-------------");
			System.out.println("Score du tour");
			for (Joueur joueur : listeJoueurs) {
				System.out.println(joueur);
			}
			
		} catch (PaquetVideException e) {
			e.printStackTrace();
		}
	}
	
	
	public void tourDeJeu() {

		Scanner sc = new Scanner(System.in);
		boolean saisieCorrecte = false;
		
		System.out.println("Plateau :");
		System.out.println(plateau);
		
		List<CoupleCarteJoueur> listeCartesJoueurs = new ArrayList<>();
		for (Joueur joueur : listeJoueurs) {
			listeCartesJoueurs.add(new CoupleCarteJoueur(joueur.choixCarte(), joueur));
		}

		System.out.println();
		System.out.println("Plateau :");
		System.out.println(plateau);
		
		Collections.sort(listeCartesJoueurs);
		System.out.println();
		System.out.println("Dans l'ordre:");
		for (CoupleCarteJoueur coupleCarteJoueur : listeCartesJoueurs) {
			System.out.println(coupleCarteJoueur.getJoueur().getNom() + " - " + coupleCarteJoueur.getCarte());
		}
		
		for (CoupleCarteJoueur coupleCarteJoueur : listeCartesJoueurs) {

			System.out.println();
			System.out.println("Plateau :");
			System.out.println(plateau);
			
			try {
				if (!plateau.jouer(coupleCarteJoueur.getCarte())) {
					ramassePenalite(coupleCarteJoueur.getJoueur(), 
							coupleCarteJoueur.getJoueur().choixPile(), 
							coupleCarteJoueur.getCarte());
				}
			} catch (SixQuiPrendException e) {
				ramassePenalite(coupleCarteJoueur.getJoueur(), e.getPileCarte(), coupleCarteJoueur.getCarte());
			}
		}
	}
	
	private void ramassePenalite(Joueur joueur, int pileChoisie, Carte carte) {

		try {
			joueur.addListeCartesPenalites(plateau.jouerSurPile(pileChoisie, carte));
		} catch (ActionImpossibleException e) {
			// ne doit jamais arriver
			System.err.println(e.getMessage());
		}
	}
	
}
