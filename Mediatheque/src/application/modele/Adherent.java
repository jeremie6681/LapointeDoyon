package application.modele;

import java.io.Serializable;

import javafx.collections.FXCollections;

public class Adherent extends Personne implements Serializable {

	private static final long serialVersionUID = 5978651855639753884L;
	private static int intNbrAdh = 1;

	// constructeur utilis� pour la lecture de fichiers
	public Adherent(String strNom, String strPrenom, String strAdresse, String strNoTelephone, String strNoPersonne) {
		super(strNoPersonne, strNom, strPrenom, strAdresse, strNoTelephone);
		intNbrAdh++;
	}

	// constructeur utilis� pour la cr�ation d'adh�rents par formulaire
	public Adherent(String strNom, String strPrenom, String strAdresse, String strNoTelephone) {
		super(setNoPersonne(), strNom, strPrenom, strAdresse, strNoTelephone);
	}

	/*
	 * permet de transformer un Integer en num�ro de personne pour les adh�rents.
	 * 
	 * retoune: un string contenant un identificateur unique de l'adh�rent
	 */
	private static String setNoPersonne() {
		String strNoPersonne = "";
		if (intNbrAdh < 10) {
			strNoPersonne = TypePersonne.Adherent.getStrIndicateurType() + "0" + intNbrAdh;
		} else {
			strNoPersonne = TypePersonne.Adherent.getStrIndicateurType() + intNbrAdh;
		}
		return strNoPersonne;
	}

	@Override
	public TypePersonne getTypePersonne() {
		return TypePersonne.Adherent;
	}

	@Override
	public String toString() {
		String strRetour = "";
		strRetour += "Num�ro d'ad�rent: " + this.getStrNoPersonne();
		strRetour += "\nPr�nom: " + this.getStrPrenom();
		strRetour += "\nNom: " + this.getStrNom();
		strRetour += "\nAdresse: " + this.getStrAdresse();
		strRetour += "\nNum�ro de t�l�phone: " + this.getStrNoTelephone();
		strRetour += "\n\n=====Prets=====\n";
		if (this.getLstPrets().size() == 0) {
			strRetour += "aucun prets\n";
		} else {
			for (Pret pret : this.getLstPrets()) {
				strRetour += pret.toString();
			}
		}

		return strRetour;
	}

	/*
	 * Permet de touver le plus gros nombre composant un num�ro d'adh�rent afin de
	 * savoir quel num�ro d'adh�rent devrat �tre cr�er prochainement.
	 * 
	 * modifie:intNbrAdh
	 */
	public static void ouRenduNoPersonnes() {
		if (intNbrAdh != 1) {
			String strNo;
			FXCollections.sort(ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent));
			strNo = ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent)
					.get(ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent).size() - 1)
					.getStrNoPersonne();
			strNo = strNo.toUpperCase().replace(TypePersonne.Adherent.getStrIndicateurType().toUpperCase(), "");
			intNbrAdh = Integer.parseInt(strNo) + 1;
		}
	}
}