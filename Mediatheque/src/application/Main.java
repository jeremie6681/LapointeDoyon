package application;
	
import application.modele.ListeDocuments;
import application.modele.TypeDocument;
import application.vue.InterfaceAjouterDocument;
import application.vue.InterfaceLoginAdherent;
import application.vue.InterfaceLoginPrepose;
import application.vue.InterfaceNouvelUtilisateur;
import application.vue.InterfacePrincipale;
import application.vue.InterfaceTypeConnection;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//InterfaceTypeConnection ifLogin = new InterfaceTypeConnection(primaryStage);
			//InterfaceLoginPrepose ifLogin = new InterfaceLoginPrepose();
			//InterfaceLoginAdherent ifLogin = new InterfaceLoginAdherent();
			//InterfaceNouvelUtilisateur ifLogin= new InterfaceNouvelUtilisateur();
			//InterfaceAjouterDocument ifLogin = new InterfaceAjouterDocument();
			InterfacePrincipale ifLogin = new InterfacePrincipale();
			primaryStage.setScene(ifLogin.getScene());
			
			primaryStage.setResizable(false);
			primaryStage.sizeToScene();
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
	
		ListeDocuments lstDocs = new ListeDocuments();
		lstDocs.serialisation();
		
		launch(args);
	}
	
}
