package fr.mygms.sixkipren.modele.joueur;

import java.util.List;

import fr.mygms.sixkipren.exceptions.ActionImpossibleException;
import fr.mygms.sixkipren.modele.Carte;

public interface Joueur extends Comparable<Joueur> {

	Carte choixCarte();

	int choixPile();

	int getPenalites();

	Carte getCarte(int valeur) throws ActionImpossibleException;

	void addCartePenalite(Carte carte);

	void setNom(String nom);

	String getNom();

	void setMain(List<Carte> main);

	List<Carte> getMain();

	void addListeCartesPenalites(List<Carte> cartes);

}
