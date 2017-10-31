package application;
	
import application.vue.InterfaceLoginAdherent;
import application.vue.InterfaceLoginPrepose;
import application.vue.InterfaceNouvelUtilisateur;
import application.vue.InterfaceTypeConnection;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//InterfaceTypeConnection ifLogin = new InterfaceTypeConnection();
			//InterfaceLoginPrepose ifLogin = new InterfaceLoginPrepose();
			//InterfaceLoginAdherent ifLogin = new InterfaceLoginAdherent();
			InterfaceNouvelUtilisateur ifLogin= new InterfaceNouvelUtilisateur();
			
			primaryStage.setScene(ifLogin.getScene());
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("ca marche");
		launch(args);
	}
	
}
