package application;

import application.modele.ListeDocuments;
import application.modele.TypePersonne;
import application.vue.InterfacePrincipale;
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
			InterfacePrincipale ifLogin = new InterfacePrincipale(primaryStage,TypePersonne.Prepose,null);
			//InterfaceAjouterDocument ifLogin = new InterfaceAjouterDocument();
			primaryStage.setScene(ifLogin.getScene());
			
			primaryStage.setResizable(false);
			primaryStage.sizeToScene();
			primaryStage.show();
			
		
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
	
		ListeDocuments lstDocs = ListeDocuments.getInstance();
		lstDocs.serialisation();

		launch(args);
	}
	
}
