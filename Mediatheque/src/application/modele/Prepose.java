package application.modele;

public class Prepose extends Personne {
	private int intNoEmploye;
	private String strMotPasse;
	
	public Prepose(String strNom, String strPrenom, String strAdresse, String strNoTelephone,  String strMotPasse, String strNoPersonne) {
		super(strNoPersonne, strNom, strPrenom, strAdresse, strNoTelephone);
		this.intNoEmploye=intNoEmploye;
		this.strMotPasse=strMotPasse;
	}

	public String getStrMotPasse() {
		return strMotPasse;
	}

}
