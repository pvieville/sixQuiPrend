package fr.mygms.sixkipren.modele.joueur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.mygms.sixkipren.exceptions.ActionImpossibleException;
import fr.mygms.sixkipren.modele.Carte;
import fr.mygms.sixkipren.service.ReadLine;

@Component
@Scope("prototype")
public class JoueurConsole extends JoueurAbstract {

	@Autowired
	private ReadLine readLine;
	
	public Carte choixCarte() {

		System.out.println();
		System.out.println("Joueur: " + toString());
		
		boolean saisieCorrecte = false;
		Carte carte = null;
		
		do {
			try {
				System.out.print("Quelle carte choisir : ");
				carte = getCarte(readLine.toInteger());
				saisieCorrecte = true;
			} catch (NumberFormatException e) {
				System.err.println("La valeur de la carte est incorrecte");
				saisieCorrecte = false;
			} catch (ActionImpossibleException e) {
				System.err.println(e.getMessage());
				saisieCorrecte = false;
			}
		} while (!saisieCorrecte);
		
		return carte;
	}
	
	public int choixPile() {

		int pileChoisie = -1;
		boolean saisieCorrecte = false;
		do {
			try {
				System.out.print(nom + " - Quelle pile choisir : ");
				pileChoisie = readLine.toInteger();
				if (!(1 <= pileChoisie && pileChoisie <= 4)) {
					throw new NumberFormatException();
				}
				saisieCorrecte = true;
			} catch (NumberFormatException e) {
				System.err.println("La valeur de la carte est incorrecte");
				saisieCorrecte = false;
			}
		} while (!saisieCorrecte);
		
		return pileChoisie;
	}
}
