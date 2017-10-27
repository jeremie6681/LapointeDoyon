package application;
	
import application.vue.InterfaceTypeConnection;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			InterfaceTypeConnection IfLogin = new InterfaceTypeConnection();
			
			primaryStage.setScene(IfLogin.getScene());
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
