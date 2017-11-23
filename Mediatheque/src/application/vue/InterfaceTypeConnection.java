package application.vue;

import application.modele.ListeDocuments;
import application.modele.ListePersonnes;
import application.modele.Style;
import application.modele.TypePersonne;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InterfaceTypeConnection {
	
	private Scene scene;
	private Button btnAdherent;
	private Button btnPrepose;
	
	public InterfaceTypeConnection(Stage primaryStage) {
		Text txtAccueil = new Text("Médiatheque LapointeDoyon");
		Text txtPar= new Text("Par Jérémie Lapointe et Philippe Doyon");
		Font  fntBoutons = Font.font("Arial",FontWeight.BOLD,FontPosture.REGULAR, 20);
		Text txtInstruction = new Text("Veuillez choisir votre type de connexion");
		VBox vb = new VBox();
		HBox hbBoutons= new HBox(20);
		Image imgGG= new Image("CégepGéraldGodin_Logo.png");
		
		ImageView ivAmende = new ImageView(imgGG); 
		ivAmende.setFitWidth(200);
		ivAmende.setFitHeight(200);
		
		txtAccueil.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.REGULAR, 32));
		txtPar.setFont(Font.font("Arial",FontWeight.LIGHT,FontPosture.ITALIC, 12));
		primaryStage.setTitle("Choix de connexion");
		//modifications text
		txtInstruction.setFont(Font.font("Arial",FontWeight.NORMAL,FontPosture.REGULAR, 16));
		
		//modifications vb
		vb.setPadding(new Insets(15));
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(20);
		//modifications Hbox
		hbBoutons.setAlignment(Pos.CENTER);
		hbBoutons.setPadding(new Insets(20));
		
		//modifications Bouton Adherent
		btnAdherent= new Button("Connexion Adhérent");
		btnAdherent.setFont(fntBoutons);
		
		//modifications Bouton préposé
		btnPrepose= new Button("Connexion Préposé/Administrateur");
		btnPrepose.setFont(fntBoutons);
		

		hbBoutons.getChildren().addAll(btnAdherent,btnPrepose);
		vb.getChildren().addAll(txtAccueil,txtPar,ivAmende,txtInstruction,hbBoutons);
		scene = new Scene(vb);
		primaryStage.setScene(scene);
		InterfacePrincipale inPrinc = new InterfacePrincipale(primaryStage, TypePersonne.Adherent, null);
		InterfaceLoginPrepose inLogPrep = new InterfaceLoginPrepose(primaryStage,scene);
		btnAdherent.setOnAction(e->primaryStage.setScene(inPrinc.getScene()));
		btnPrepose.setOnAction(e->primaryStage.setScene(inLogPrep.getScene()));
		
		primaryStage.getIcons().add(Style.imgAmende);
	}

	public Scene getScene() {
		return scene;
	}
	
	
}
