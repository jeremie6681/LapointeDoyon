package application.modele;

import java.io.Serializable;

public class Adherent extends Personne implements Serializable {

	private static final long serialVersionUID = 5978651855639753884L;

	public Adherent(String strNom, String strPrenom, String strAdresse, String strNoTelephone, String strNoPersonne) {
		super(strNoPersonne, strNom, strPrenom, strAdresse, strNoTelephone);
		
	}

}
