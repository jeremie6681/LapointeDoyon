package application.modele;

public enum TypeDocument {
	Livre("Livre","LIV"),Periodique("Périodique","Per"),Dvd("DVD","DVD");
	
	private String strNomType, strIndicateurType;
	
	private TypeDocument(String strNomType, String strIndicateurType) {
		this.strNomType = strNomType;
		this.strIndicateurType = strIndicateurType;
	}

	public String getStrNomType() {
		return strNomType;
	}

	public String getStrIndicateurType() {
		return strIndicateurType;
	}
}
