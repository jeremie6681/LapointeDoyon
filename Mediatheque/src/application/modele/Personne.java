package application.modele;

public class Personne {
	private String strNoPersonne;
	private String strNom;
	private String strPrenom;
	private String strAdresse;
	private String strNoTelephone;
	
	public Personne(String strNom, String strPrenom, String strAdresse, String strNoTelephone,String strNoPersonne) {
		
		this.strNom = strNom;
		this.strPrenom = strPrenom;
		this.strAdresse = strAdresse;
		this.strNoTelephone = strNoTelephone;
	}

	public String getStrNoPersonne() {
		return strNoPersonne;
	}

}
