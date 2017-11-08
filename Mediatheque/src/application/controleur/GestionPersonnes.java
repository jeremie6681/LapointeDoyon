package application.controleur;

import application.modele.Adherent;
import application.modele.ListePersonnes;
import application.modele.Personne;
import application.modele.Prepose;
import application.modele.TypePersonne;
import application.vue.InterfacePrincipale;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class GestionPersonnes {

	public static void connection(String strNomConnection,Stage primaryStage) {
		boolean booConnecter= false;
		Alert alerteConnection = new Alert(AlertType.WARNING,"votre identifiant n'est pas valide",ButtonType.OK);
		InterfacePrincipale interfacePrincipale ;
		if (strNomConnection!=null && strNomConnection.trim()!="") {
			for (Personne personne : ListePersonnes.mapPersonne.get(TypePersonne.Adherent)) {
				if(personne.getStrNoPersonne().equalsIgnoreCase(strNomConnection.trim())) {
					interfacePrincipale = new InterfacePrincipale(primaryStage,TypePersonne.Adherent,personne ); 
					primaryStage.setScene(interfacePrincipale.getScene());
					booConnecter=true;
				}
			}
		}
		if (!booConnecter) {
			alerteConnection.showAndWait();
		}
	}
	public static void connection(String strNomConnection,String strMotPasse,Stage primaryStage ) {
		boolean booConnecter= false;
		Alert alerteConnection = new Alert(AlertType.WARNING,"votre identifiant ou votre Mot de passe n'est pas valide",ButtonType.OK);
		Prepose temp ;
		
		if (strNomConnection!=null && strNomConnection.trim()!="") {
			if (strNomConnection.trim().equals(ListePersonnes.mapPersonne.get(TypePersonne.Admin).get(0).getStrNoPersonne())) {
				temp=(Prepose)ListePersonnes.mapPersonne.get(TypePersonne.Admin).get(0);
				if(strMotPasse.trim().equals(temp.getStrMotPasse())) {
					booConnecter=true;
					//connecter
				} 
			}
			else {
			for (Personne personne : ListePersonnes.mapPersonne.get(TypePersonne.Adherent)) {
				if(personne.getStrNoPersonne().equalsIgnoreCase(strNomConnection.trim())) {
					temp=(Prepose)personne;
					if (temp.getStrMotPasse().equals(strMotPasse)){
						//connecter
					}
				}
			}
		}
	}
   }
	
}
