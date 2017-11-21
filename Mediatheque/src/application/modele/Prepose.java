package application.modele;

import java.io.Serializable;
import java.util.Collections;

import javafx.collections.FXCollections;

public class Prepose extends Personne implements Serializable {

	private static final long serialVersionUID = -7225017568166257641L;
	private static int intNoEmploye;//=ouRenduNoPersonnes();
	private String strMotPasse;
	
	public Prepose(String strNom, String strPrenom, String strAdresse, String strNoTelephone,  String strMotPasse, String strNoPersonne) {
		super(strNoPersonne, strNom, strPrenom, strAdresse, strNoTelephone);
		
		this.strMotPasse=strMotPasse;
	}

	public Prepose(String strNom, String strPrenom, String strAdresse, String strNoTelephone, String strMotPasse) {
		super(setNoPersonne(), strNom, strPrenom, strAdresse, strNoTelephone);
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

	public static void ouRenduNoPersonnes() {
	
		if (intNoEmploye!=1) {
		String strNo;
		FXCollections.sort(ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Prepose));
		strNo =ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Prepose).get(ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Prepose).size()-1).getStrNoPersonne();
		strNo= strNo.toUpperCase().replace(TypePersonne.Prepose.getStrIndicateurType().toUpperCase(),"");
		intNoEmploye= Integer.parseInt(strNo)+1;
		}
		System.out.println(intNoEmploye);
		
	}

}
