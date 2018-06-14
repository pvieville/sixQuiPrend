package fr.mygms.sixkipren.modele;

public class CoupleCartePile implements Comparable<CoupleCartePile> {

	private Carte carte;
	private PileCarte pile;
	
	public CoupleCartePile(Carte carte, PileCarte pile) {
		super();
		this.carte = carte;
		this.pile = pile;
	}
	
	public Carte getCarte() {
		return carte;
	}
	public PileCarte getPile() {
		return pile;
	}

	@Override
	public int compareTo(CoupleCartePile coupleCartePile) {
		return carte.compareTo(coupleCartePile.getCarte());
	}

}
