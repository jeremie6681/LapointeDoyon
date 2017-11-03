package application.vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class InterfaceLoginAdherent {
	
	
	private Scene scene;
	private Button btnConfirmer;
	private Button btnRetour;
	
	public InterfaceLoginAdherent(Stage primaryStage,Scene scenePrecedente) {
		btnConfirmer= new Button("Se connecter");
		VBox vb =new VBox(10) ;
		HBox hbUtilisateur= new HBox(20);
		Label lblUtilisateur=new Label("Nom d'utilisateur :");
		TextField tfUtilisateur = new TextField();
		Text txtInstruction = new Text("veuillez entrer vous informations de conection");
		TextFlow tflInstruction;
		Font  fntBoutons = Font.font("Arial",FontWeight.BOLD,FontPosture.REGULAR, 20);
		
		btnRetour = new Button("Retour");
		btnRetour.setOnAction(e->primaryStage.setScene(scenePrecedente));
		//modifications vb
		vb.setPadding(new Insets(20));
		vb.setAlignment(Pos.CENTER);
		
		//modifications text
		txtInstruction.setFont(Font.font("Arial",FontWeight.NORMAL,FontPosture.REGULAR, 16));
		tflInstruction = new TextFlow(txtInstruction);
		
		//modifications hbUtilisateur
		hbUtilisateur.setAlignment(Pos.CENTER_LEFT);
		
		
		//modifications btnConfirmer
		btnConfirmer.setFont(fntBoutons);
		//btnConfirmer.setOnAction(e->primaryStage.setScene(.getScene()));
		
		hbUtilisateur.getChildren().addAll(lblUtilisateur,tfUtilisateur);

		vb.getChildren().addAll(tflInstruction,hbUtilisateur,btnConfirmer,btnRetour);
		scene = new Scene(vb);
		
	}

	public Scene getScene() {
		return scene;
	}

}
