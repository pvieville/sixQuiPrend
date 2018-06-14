package fr.mygms.sixkipren.modele.joueur;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.mygms.sixkipren.builder.CarteProbabiliteBuilder;
import fr.mygms.sixkipren.modele.Carte;
import fr.mygms.sixkipren.modele.CarteProbabilite;
import fr.mygms.sixkipren.modele.PileCarte;
import fr.mygms.sixkipren.modele.Plateau;
import fr.mygms.sixkipren.service.PenaliteService;

@Component
@Scope("prototype")
public class JoueurOrdinateur extends JoueurAbstract {
	
	private Plateau plateau;
	private int nbJoueurs;
	private int nbCartes;
	private int nbCartesEnJeu;
	private BigDecimal probaJeu;
	private BitSet ensembleCartesPassees = new BitSet();

	@Autowired
	private CarteProbabiliteBuilder carteProbabiliteBuilder;
	
	@Autowired
	private PenaliteService penaliteService;
	
	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	public void setConfigurationJeu(int nbCartes, int nbPiles, int nbJoueurs, int nbCartesParJoueur) {
		this.nbCartes = nbCartes;
		this.nbCartesEnJeu = nbPiles + (nbJoueurs * nbCartesParJoueur);
		this.nbJoueurs = nbJoueurs;
		this.probaJeu = new BigDecimal(this.nbCartesEnJeu).divide(new BigDecimal(this.nbCartes));
	}

	@Override
	public void setMain(List<Carte> main) {
		this.main = main;
		Collections.sort(this.main);
		// on enregistre notre main dans les cartes passees
		for(Carte carte : main) {
			ensembleCartesPassees.set(carte.getValeur(), true);
		}
	}
	
	public void notifierJoueur() {
		
		// enregistrement des cartes passees en memoire
		for (PileCarte pileCarte : plateau.getListePiles()) {
			for(Carte carte : pileCarte.getListeCartes()) {
				ensembleCartesPassees.set(carte.getValeur(), true);
			}
		}
	}
	
	private Carte getCartePlusPetitePlateau() {

		Carte cartePlusPetite = null;
		for (PileCarte pileCarte : plateau.getListePiles()) {
			if (cartePlusPetite == null || cartePlusPetite.compareTo(pileCarte.derniereCarte()) > 0) {
				cartePlusPetite = pileCarte.derniereCarte();
			}
		}
		return cartePlusPetite;
	}
	
	private boolean possibiliteRetirerColonne() {
		return nbCartesPossiblesEntre(0, getCartePlusPetitePlateau()) > 0;
	}
	
	private int nbCartesPossiblesEntre(Carte carteMin, Carte carteMax) {
		return nbCartesPossiblesEntre(carteMin.getValeur(), carteMax.getValeur());
	}
	
	private int nbCartesPossiblesEntre(int valeurCarteMin, int valeurCarteMax) {

		int valeurMin = Math.min(valeurCarteMin, valeurCarteMax);
		int valeurMax = Math.max(valeurCarteMin, valeurCarteMax);
		
		int nbCartesEntre = 0;
		for (int index = valeurMin + 1 ; index < valeurMax ; index++) {
			if (!ensembleCartesPassees.get(index)) {
				++nbCartesEntre;
			}
		}
		return nbCartesEntre;
	}
	
	private int nbCartesPossiblesEntre(int valeurCarteMin, Carte carteMax) {
		return nbCartesPossiblesEntre(valeurCarteMin, carteMax.getValeur());
	}
	
	private CarteProbabilite calculeProbabilite(Carte carte) {
		
		PileCarte pileCarteOuJouer = null;

		for (PileCarte pileCarte : plateau.getListePiles()) {
			if (pileCarte.peutJouer(carte)
					&& (pileCarteOuJouer == null
							|| pileCarteOuJouer.derniereCarte().compareTo(pileCarte.derniereCarte()) < 0)) {
				pileCarteOuJouer = pileCarte;
			}
		}
		
		if (pileCarteOuJouer == null) {
			// cas carte trop petite
			return carteProbabiliteBuilder.creerCarteProbabiliteTropPetite(carte);
		}

		int nbCartesEntre = nbCartesPossiblesEntre(pileCarteOuJouer.derniereCarte(), carte);
		
		// si aucun moyen de retirer la colonne (ou probabilite faible) alors on considère que la carte à jouer est sur
		//  si l'ecart ou le nombre de joueur a jouer n'atteint pas 6 sur la colonne
		if (((pileCarteOuJouer.nbCartes() + nbCartesEntre + 1) <= 5 || (pileCarteOuJouer.nbCartes() + nbJoueurs + 1) <= 5)
				&& (!possibiliteRetirerColonne() || !probaliliteRetirerColonne(pileCarteOuJouer))) {
			return carteProbabiliteBuilder.creerCarteProbabilite(carte, 100);
		}
		
		// sinon on construit un arbre de possibilite pour avoir une probabilite
		return carteProbabiliteBuilder.creerCarteProbabilite(carte, 0);
	}
	
	private boolean probaliliteRetirerColonne(PileCarte pileCarteOuJouer) {
		int penalitePlusBasse = Integer.MAX_VALUE;
		for (PileCarte pileCarte : plateau.getListePiles()) {
			penalitePlusBasse = Math.min(penalitePlusBasse, penaliteService.calculePenalite(pileCarteOuJouer));
		}
		
		// on va estimer que si on a la penalite la plus basse et quelle est < 5 alors il y a de fortes chances
		//  pour que la colonne soit mangée par quelqu'un
		return penaliteService.calculePenalite(pileCarteOuJouer) == penalitePlusBasse 
				&& penaliteService.calculePenalite(pileCarteOuJouer) < 5;
	}
	
	
	
	@Override
	public Carte choixCarte() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int choixPile() {
		// TODO Auto-generated method stub
		return 0;
	}

}
