package application.modele;

import java.io.Serializable;

public class Prepose extends Personne implements Serializable {

	private static final long serialVersionUID = -7225017568166257641L;
	private static int intNoEmploye=1;
	private String strMotPasse;
	
	public Prepose(String strNom, String strPrenom, String strAdresse, String strNoTelephone,  String strMotPasse, String strNoPersonne) {
		super(strNoPersonne, strNom, strPrenom, strAdresse, strNoTelephone);
		intNoEmploye++;
		this.strMotPasse=strMotPasse;
	}

	public Prepose(String strNom, String strPrenom, String strAdresse, String strNoTelephone, String strMotPasse) {
		super(setNoPersonne(), strNom, strPrenom, strAdresse, strNoTelephone);
		intNoEmploye++;
		this.strMotPasse=strMotPasse;
	}

	private static String setNoPersonne() {
       String strNoPersonne="";
		if(intNoEmploye<10) {
		strNoPersonne=TypePersonne.Prepose.getStrIndicateurType()+"0"+intNoEmploye;
		}
		else {
		strNoPersonne=TypePersonne.Prepose.getStrIndicateurType()+intNoEmploye;
		}
		return strNoPersonne;
	}

	public String getStrMotPasse() {
		return strMotPasse;
	}

	@Override
	public
	TypePersonne getTypePersonne() {
		return TypePersonne.Prepose;
	}

}
