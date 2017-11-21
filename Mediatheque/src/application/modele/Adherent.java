package application.modele;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

import javafx.collections.FXCollections;

public class Adherent extends Personne implements Serializable {
	//private static int intNbrAdh;//=ouRenduNoPersonnes();
	private static final long serialVersionUID = 5978651855639753884L;
	private static int intNbrAdh ; //ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent).size() + 1;
	public Adherent(String strNom, String strPrenom, String strAdresse, String strNoTelephone, String strNoPersonne) {
		super(strNoPersonne, strNom, strPrenom, strAdresse, strNoTelephone);
		intNbrAdh++;
	}
	
	public Adherent(String strNom, String strPrenom, String strAdresse, String strNoTelephone) {
		super(setNoPersonne(), strNom, strPrenom, strAdresse, strNoTelephone);
		//intNbrAdh++;
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

	@Override
	public String toString() {
		String strRetour="";
		strRetour +="Numéro d'adérent: "+this.getStrNoPersonne();
		strRetour +="\nPrénom: "+this.getStrPrenom();
		strRetour +="\nNom: "+this.getStrNom();
		strRetour +="\nAdresse: "+this.getStrAdresse();
		strRetour +="\nNuméro de téléphone: "+this.getStrNoTelephone();
		strRetour +="\n\n=====Prets=====\n";
		if(this.getLstPrets().size()==0) {
			strRetour+="aucun prets\n";
		}
		else{
			for (Pret pret : this.getLstPrets()) {
				strRetour += pret.toString();
			}
		}
		
		return strRetour;
	}
	
	
	
	public static void  ouRenduNoPersonnes() {
	
		int intNoPersonne=1;
		if (intNbrAdh!=1) {
		String strNo;
		FXCollections.sort(ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent));
		strNo =ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent).get(ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent).size()-1).getStrNoPersonne();
		strNo= strNo.toUpperCase().replace(TypePersonne.Adherent.getStrIndicateurType().toUpperCase(),"");
		intNoPersonne= Integer.parseInt(strNo)+1;
		}
		System.out.println(intNoPersonne);
		 intNbrAdh =intNoPersonne;
	}
	
	

}