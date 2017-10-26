package application.modele;

public class Adherent extends Personne {
	
	private int intNoAdherent;
	
	public Adherent(String strNom, String strPrenom, String strAdresse, String strNoTelephone, int intNoAdherent) {
		super(strNom, strPrenom, strAdresse, strNoTelephone);
		this.intNoAdherent=intNoAdherent;
	}

}
