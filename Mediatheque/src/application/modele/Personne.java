package application.modele;

import java.util.ArrayList;

public class Personne {
	private String strNoPersonne;
	private String strNom;
	private String strPrenom;
	private String strAdresse;
	private String strNoTelephone;
	private ArrayList<Pret> lstPrets=new ArrayList<Pret>();
	
	public Personne(String strNoPersonne, String strNom, String strPrenom, String strAdresse, String strNoTelephone) {
		//this.strNoPersonne = strNoPersonne;
		this.strNom = strNom;
		this.strPrenom = strPrenom;
		this.strAdresse = strAdresse;
		this.strNoTelephone = strNoTelephone;
	}

	public String getStrNoPersonne() {
		return strNoPersonne;
	}

	public ArrayList<Pret> getLstPrets() {
		return lstPrets;
	}

	public void setLstPrets(ArrayList<Pret> lstPrets) {
		this.lstPrets = lstPrets;
	}

	public String getStrNom() {
		return strNom;
	}

	public String getStrPrenom() {
		return strPrenom;
	}

	public void setStrNoPersonne(String strNoPersonne) {
		this.strNoPersonne = strNoPersonne;
	}

	public void setStrNom(String strNom) {
		this.strNom = strNom;
	}

	public void setStrPrenom(String strPrenom) {
		this.strPrenom = strPrenom;
	}

	public void setStrAdresse(String strAdresse) {
		this.strAdresse = strAdresse;
	}

	public void setStrNoTelephone(String strNoTelephone) {
		this.strNoTelephone = strNoTelephone;
	}

}
