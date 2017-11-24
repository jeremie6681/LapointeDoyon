package application.modele;

import java.io.Serializable;
import javafx.collections.FXCollections;

//Classe de préposé et aussi d'administrateur
public class Prepose extends Personne implements Serializable {

	private static final long serialVersionUID = -7225017568166257641L;
	private static int intNoEmploye;
	private String strMotPasse;

	public Prepose(String strNom, String strPrenom, String strAdresse, String strNoTelephone, String strMotPasse,
			String strNoPersonne) {
		super(strNoPersonne, strNom, strPrenom, strAdresse, strNoTelephone);
		this.strMotPasse = strMotPasse;
	}

	public Prepose(String strNom, String strPrenom, String strAdresse, String strNoTelephone, String strMotPasse) {
		super(setNoPersonne(), strNom, strPrenom, strAdresse, strNoTelephone);
		this.strMotPasse = strMotPasse;
	}

	/*
	 * permet de transformer un Integer en numéro de personne pour les Préposés.
	 * 
	 * retoune: un string contenant un identificateur unique du préposé
	 */
	private static String setNoPersonne() {
		String strNoPersonne = "";
		if (intNoEmploye < 10) {
			strNoPersonne = TypePersonne.Prepose.getStrIndicateurType() + "0" + intNoEmploye;
		} else {
			strNoPersonne = TypePersonne.Prepose.getStrIndicateurType() + intNoEmploye;
		}
		return strNoPersonne;
	}

	public String getStrMotPasse() {
		return strMotPasse;
	}
	public void setStrMotPasse(String strMotPasse) {
		this.strMotPasse=strMotPasse;
	}

	@Override
	public TypePersonne getTypePersonne() {
		return TypePersonne.Prepose;
	}

	/*
	 * Permet de touver le plus gros nombre composant un numéro d'emplyé afin de
	 * savoir quel numéro d'adhérent devrat être créer prochainement.
	 * 
	 * modifie:intNbrEmploye
	 */
	public static void ouRenduNoPersonnes() {

		if (intNoEmploye != 1) {
			String strNo;
			FXCollections.sort(ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Prepose));
			strNo = ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Prepose)
					.get(ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Prepose).size() - 1)
					.getStrNoPersonne();
			strNo = strNo.toUpperCase().replace(TypePersonne.Prepose.getStrIndicateurType().toUpperCase(), "");
			intNoEmploye = Integer.parseInt(strNo) + 1;
		}

	}

}
