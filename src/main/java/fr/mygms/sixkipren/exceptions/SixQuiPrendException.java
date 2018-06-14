package fr.mygms.sixkipren.exceptions;

public class SixQuiPrendException extends Exception {

	private static final long serialVersionUID = 1L;

	private int pileCarte;
	
	public SixQuiPrendException(String message) {
		super(message);
	}
	
	public void setPileCarte(int pileCarte) {
		this.pileCarte = pileCarte;
	}
	
	public int getPileCarte() {
		return pileCarte;
	}
}
