package application.modele;

/**
 * @Description Continent des information statics sur les différents type d'utilisateurs
 * 
 * @author Lapointe & Doyon
 *
 */
public enum TypePersonne {
	Admin("Administrateur","Adm"),Prepose("Prépose","Pre"),Adherent("Adhérent","Adh");
	
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