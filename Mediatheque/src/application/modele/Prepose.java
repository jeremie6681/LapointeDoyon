package application.modele;

public class Prepose extends Personne {
	private int intNoEmploye;
	private String strMotPasse;
	
	public Prepose(String strNom, String strPrenom, String strAdresse, String strNoTelephone,  String strMotPasse, String strNoPersonne) {
		super(strNom, strPrenom, strAdresse, strNoTelephone,strNoPersonne );
		//this.intNoEmploye=intNoEmploye;
		this.strMotPasse=strMotPasse;
	}

}
