package application;

import application.modele.Adherent;
import application.modele.ListeDocuments;
import application.modele.ListePersonnes;
import application.modele.Personne;
import application.modele.TypePersonne;
import application.vue.InterfaceAjouterDocument;
import application.vue.InterfaceLoginPrepose;
import application.vue.InterfacePrincipale;
import application.vue.InterfaceTypeConnection;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//InterfaceTypeConnection ifLogin = new InterfaceTypeConnection(primaryStage);
			//InterfaceLoginPrepose ifLogin = new InterfaceLoginPrepose();
			//InterfaceLoginAdherent ifLogin = new InterfaceLoginAdherent();
			//InterfaceNouvelUtilisateur ifLogin= new InterfaceNouvelUtilisateur();
			//InterfaceAjouterDocument ifLogin = new InterfaceAjouterDocument();
			Adherent pTest = new Adherent("strnom","strPrenom","stradr","strnotel","adh08"); 

			//InterfacePrincipale ifLogin = new InterfacePrincipale(primaryStage,TypePersonne.Prepose,pTest);

			InterfacePrincipale ifLogin = new InterfacePrincipale(primaryStage,TypePersonne.Adherent,pTest);


			//InterfaceAjouterDocument ifLogin = new InterfaceAjouterDocument();
			primaryStage.setScene(ifLogin.getScene());
			
			primaryStage.setResizable(false);
			primaryStage.sizeToScene();
			primaryStage.show();
			
		
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
	
		//ListeDocuments lstDocs = ListeDocuments.getInstance();
		
		//ListePersonnes listePersonnes = ListePersonnes.getInstance();

		launch(args);
	}
	
}
