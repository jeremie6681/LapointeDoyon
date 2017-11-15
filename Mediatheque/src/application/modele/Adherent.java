package application.modele;

import java.io.Serializable;

public class Adherent extends Personne implements Serializable {
	private static int intNbrAdh=1;
	private static final long serialVersionUID = 5978651855639753884L;

	public Adherent(String strNom, String strPrenom, String strAdresse, String strNoTelephone, String strNoPersonne) {
		super(strNoPersonne, strNom, strPrenom, strAdresse, strNoTelephone);
		intNbrAdh++;
	}
	public Adherent(String strNom, String strPrenom, String strAdresse, String strNoTelephone) {
		super(setNoPersonne(), strNom, strPrenom, strAdresse, strNoTelephone);
		intNbrAdh++;
	}
	
	private static String setNoPersonne() {
		String strNoPersonne="";
		if(intNbrAdh<10) {
		strNoPersonne=TypePersonne.Adherent.getStrIndicateurType()+"0"+intNbrAdh;
		}
		else {
		strNoPersonne=TypePersonne.Adherent.getStrIndicateurType()+intNbrAdh;
		}
		return strNoPersonne;
	}
	@Override
	public
	TypePersonne getTypePersonne() {
		return TypePersonne.Adherent;
	}

}
