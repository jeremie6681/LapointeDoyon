package application.modele;

public enum TypePersonne {
	Admin("Administrateur","Adm"),Prepose("Pr�pose","Pre"),Adherent("Adh�rent","Adh");
	
	private String strNomType, strIndicateurType;
	
	private TypePersonne(String strNomType, String strIndicateurType) {
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
