package fr.mygms.sixkipren.builder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.mygms.sixkipren.modele.Carte;
import fr.mygms.sixkipren.modele.Plateau;
import fr.mygms.sixkipren.modele.joueur.Joueur;
import fr.mygms.sixkipren.modele.joueur.JoueurConsole;
import fr.mygms.sixkipren.modele.joueur.JoueurOrdinateur;

@Service
@Scope("singleton")
public class JoueurBuilder {

	@Autowired
    private ApplicationContext context;
	
	public Joueur creerJoueurConsole(String nom, List<Carte> main) {
		JoueurConsole joueur = context.getBean(JoueurConsole.class);
		joueur.setNom(nom);
		joueur.setMain(main);
		return joueur;
	}
	
	public Joueur creerJoueurOrdinateur(String nom, List<Carte> main, Plateau plateau) {
		JoueurOrdinateur joueur = context.getBean(JoueurOrdinateur.class);
		joueur.setNom(nom);
		joueur.setMain(main);
		joueur.setPlateau(plateau);
		return joueur;
	}
}
