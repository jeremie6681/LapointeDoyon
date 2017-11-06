package application.controleur;

import application.modele.Adherent;
import application.modele.ListePersonnes;
import application.modele.Personne;
import application.modele.TypePersonne;
import application.vue.InterfacePrincipale;
import javafx.stage.Stage;

public class GestionPersonnes {
	public static void connection(String strNomConnection,Stage primaryStage) {
		if (strNomConnection!=null && strNomConnection.trim()!="") {
			for (Personne personne : ListePersonnes.mapPersonne.get(TypePersonne.Adherent)) {
				if(personne.getStrNoPersonne().equalsIgnoreCase(strNomConnection.trim())) {
					//adherent peut se connecter
					primaryStage.setScene(new InterfacePrincipale().getScene());
				}
			}
		}
	}
	public static void connection(String strNomConnection,String strMotPasse,Stage primaryStage ) {
		if (strNomConnection!=null && strNomConnection.trim()!="") {
			if (strNomConnection.trim().equals(ListePersonnes.mapPersonne.get(TypePersonne.Admin).get(0).getStrNoPersonne())) {
				//checker si mot de passe correspondent
					//connecter
				
			}
			else {
			for (Personne personne : ListePersonnes.mapPersonne.get(TypePersonne.Adherent)) {
				if(personne.getStrNoPersonne().equalsIgnoreCase(strNomConnection.trim())) {
					//peut se connecter
					primaryStage.setScene(new InterfacePrincipale().getScene());
				}
			}
		}
	}
   }
	
}
