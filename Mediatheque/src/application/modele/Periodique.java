package application.modele;

import java.io.Serializable;
import java.time.LocalDate;
import javafx.collections.FXCollections;

public class Periodique extends Document implements Serializable {

	private static int intNoDocs;// ouRenduNoPersonnes();
	private static final long serialVersionUID = 3408058369694863163L;
	private int intNoVolume;
	private int intNoPeriodique;

	public Periodique(String strCodeDocument, String strTitre, LocalDate dateParution, Etat etatDoc, int intNoVolume,
			int intNoPeriodique) {
		super(strCodeDocument, strTitre, dateParution, etatDoc);
		this.intNoVolume = intNoVolume;
		this.intNoPeriodique = intNoPeriodique;

	}

	public Periodique(String strTitre, LocalDate dateParution, int intNoVolume, int intNoPeriodique) {
		super(setCodeDocument(), strTitre, dateParution, Etat.DISPONIBLE);
		this.intNoVolume = intNoVolume;
		this.intNoPeriodique = intNoPeriodique;
		intNoDocs++;
	}

	public int getIntNoVolume() {
		return intNoVolume;
	}

	public void setIntNoVolume(int intNoVolume) {
		this.intNoVolume = intNoVolume;
	}

	public int getIntNoPeriodique() {
		return intNoPeriodique;
	}

	public void setIntNoPeriodique(int intNoPeriodique) {
		this.intNoPeriodique = intNoPeriodique;
	}

	public TypeDocument getTypeDocument() {
		return TypeDocument.Periodique;
	}

	private static String setCodeDocument() {
		String strCodeDoc = "";
		if (intNoDocs < 10) {
			strCodeDoc = TypeDocument.Periodique.getStrIndicateurType() + "0" + intNoDocs;
		} else {
			strCodeDoc = TypeDocument.Periodique.getStrIndicateurType() + intNoDocs;
		}
		return strCodeDoc;
	}

	public static void ouRenduNo() {
		int intNoDoc = 1;
		if (intNoDocs != 1) {
			String strNo;
			FXCollections.sort(ListeDocuments.getInstance().mapDocument.get(TypeDocument.Periodique));
			strNo = ListeDocuments.getInstance().mapDocument.get(TypeDocument.Periodique)
					.get((ListeDocuments.getInstance().mapDocument.get(TypeDocument.Periodique)).size() - 1)
					.getStrCodeDocument();
			strNo = strNo.toUpperCase().replace(TypeDocument.Periodique.getStrIndicateurType().toUpperCase(), "");
			intNoDoc = Integer.parseInt(strNo) + 1;
		}
		intNoDocs = intNoDoc;
	}
}
