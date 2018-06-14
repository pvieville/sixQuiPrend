package fr.mygms.sixkipren.modele;

public class CarteProbabilite implements Comparable<CarteProbabilite> {

	private Carte carte;
	private int probabilite = 0;
	private boolean tropPetite = false;
	
	public int getProbabilite() {
		return probabilite;
	}
	public void setProbabilite(int probabilite) {
		this.probabilite = probabilite;
	}
	public void setCarte(Carte carte) {
		this.carte = carte;
	}
	public void getCarte(Carte carte) {
		this.carte = carte;
	}
	public boolean isTropPetite() {
		return tropPetite;
	}
	public void setTropPetite(boolean tropPetite) {
		this.tropPetite = tropPetite;
	}
	
	@Override
	public int compareTo(CarteProbabilite carteProbabilite) {
		return getProbabilite() - carteProbabilite.getProbabilite();
	}
}
