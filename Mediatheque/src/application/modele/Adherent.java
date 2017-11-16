package application.modele;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

public class Adherent extends Personne implements Serializable {
	private static int intNbrAdh=ouRenduNoPersonnes();
	private static final long serialVersionUID = 5978651855639753884L;

	public Adherent(String strNom, String strPrenom, String strAdresse, String strNoTelephone, String strNoPersonne) {
		super(strNoPersonne, strNom, strPrenom, strAdresse, strNoTelephone);
	
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
	public TypePersonne getTypePersonne() {
		return TypePersonne.Adherent;
	}
	private static int ouRenduNoPersonnes() {
	
		int intNoPersonne=1;
		if (intNbrAdh!=1) {
		String strNo;
		Collections.sort(ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent));
		strNo =ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent).get(ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent).size()-1).getStrNoPersonne();
		strNo= strNo.toUpperCase().replace(TypePersonne.Adherent.getStrIndicateurType().toUpperCase(),"");
		intNoPersonne= Integer.parseInt(strNo)+1;
		}
		System.out.println(intNoPersonne);
		return intNoPersonne;
	}

}
