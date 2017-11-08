package application.modele;

public enum TypeDocument {
	Livre("Livre","LIV",3,14),Periodique("Périodique","Per",1,3),Dvd("DVD","DVD",2,7);
	
	private String strNomType, strIndicateurType;
	private int intNbEmprunte, intNbJoursEmprunt;
	
	private TypeDocument(String strNomType, String strIndicateurType,int intNbEmprunte,int intNbJoursEmprunt) {
		this.strNomType = strNomType;
		this.strIndicateurType = strIndicateurType;
		this.intNbEmprunte=intNbEmprunte;
		this.intNbJoursEmprunt=intNbJoursEmprunt;
	}

	public String getStrNomType() {
		return strNomType;
	}

	public String getStrIndicateurType() {
		return strIndicateurType;
	}

	public int getIntNbEmprunte() {
		return intNbEmprunte;
	}

	public int getIntNbJoursEmprunt() {
		return intNbJoursEmprunt;
	}
}
