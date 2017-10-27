package application.vue;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
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
	
	public InterfaceTypeConnection() {
		Font  fntBoutons = Font.font("Arial",FontWeight.BOLD,FontPosture.REGULAR, 20);
		TextFlow tfInstruction = new TextFlow(new Text("veuillez choisir Votre type de connection"));
		VBox vb = new VBox();
		HBox HbBoutons= new HBox();
		
		
		//modifications Bouton Adherent
		btnAdherent= new Button("Connection Adherent");
		btnAdherent.setFont(fntBoutons);
		
		
		
		//modifications Bouton préposé
		btnPrepose= new Button("Connection Préposé");
		btnPrepose.setFont(fntBoutons);
		
		HbBoutons.getChildren().addAll(btnAdherent,btnPrepose);
		vb.getChildren().addAll(tfInstruction,HbBoutons);
		scene
	}

	public Scene getScene() {
		return scene;
	}
}
