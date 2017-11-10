package application.modele;

import java.io.Serializable;

public class Prepose extends Personne implements Serializable {

	private static final long serialVersionUID = -7225017568166257641L;
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
