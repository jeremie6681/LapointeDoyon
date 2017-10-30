package application.vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class InterfaceTypeConnection {
	
	private Scene scene;
	private Button btnAdherent;
	private Button btnPrepose;
	//private Button btnNouvelUtilisateur;
	
	public InterfaceTypeConnection() {
		Font  fntBoutons = Font.font("Arial",FontWeight.BOLD,FontPosture.REGULAR, 20);
		Text txtInstruction = new Text("veuillez choisir Votre type de connection");
		TextFlow tflInstruction;
		VBox vb = new VBox();
		HBox hbBoutons= new HBox(20);
		
		
		//modifications text
		txtInstruction.setFont(Font.font("Arial",FontWeight.NORMAL,FontPosture.REGULAR, 16));
		tflInstruction = new TextFlow(txtInstruction);
		
		//modifications vb
		vb.setPadding(new Insets(15));
		vb.setAlignment(Pos.CENTER);
		
		//modifications Hbox
		hbBoutons.setAlignment(Pos.CENTER);
		hbBoutons.setPadding(new Insets(20));
		
		//modifications Bouton Adherent
		btnAdherent= new Button("Connection Adherent");
		btnAdherent.setFont(fntBoutons);
		
		//modifications Bouton préposé
		btnPrepose= new Button("Connection Préposé");
		btnPrepose.setFont(fntBoutons);
		
		//modifications bouton ajouter nouvel utilisateur
		//btnNouvelUtilisateur = new Button("Créer un nouvel Utilisateur");
		//btnNouvelUtilisateur.setFont(fntBoutons);  
		
		
		hbBoutons.getChildren().addAll(btnAdherent,btnPrepose);
		vb.getChildren().addAll(tflInstruction,hbBoutons);
		scene = new Scene(vb);
	}

	public Scene getScene() {
		return scene;
	}
}
