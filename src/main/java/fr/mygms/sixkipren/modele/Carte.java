package fr.mygms.sixkipren.modele;

public class Carte implements Comparable<Carte> {

	private int valeur;
	private int penalite = 1;
	
	public Carte(int valeur, int penalite) {
		this.valeur = valeur;
		this.penalite = penalite;
	}
	
	public int getValeur() {
		return valeur;
	}

	public int getPenalite() {
		return penalite;
	}

	public Carte(int valeur) {
		this.valeur = valeur;
		this.penalite = 1;
	}

	@Override
	public int compareTo(Carte carte) {
		if (carte == null) {
			return -1;
		}
		return valeur - carte.getValeur();
	}
	
	public String toString() {
		return valeur + "(" + penalite + ")";
	}
}
