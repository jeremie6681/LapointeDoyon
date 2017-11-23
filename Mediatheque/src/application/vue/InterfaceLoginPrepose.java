package application.vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import application.controleur.GestionPersonnes;

//Interface lorsqu'un préposé veut s'identifier
public class InterfaceLoginPrepose  {
	
	private Scene scene;
	private Button btnConfirmer;
	private Button btnRetour;
	private PasswordField pfPwd;
	private TextField tfUtilisateur;
	
	public InterfaceLoginPrepose(Stage primaryStage,Scene scenePrecedente) {
		btnConfirmer= new Button("Se connecter");
		VBox vb =new VBox(10) ;
		HBox hbUtilisateur= new HBox(20);
		HBox hbPwd= new HBox(20);
		Label lblUtilisateur=new Label("Nom d'utilisateur :");
		tfUtilisateur = new TextField();
		Label lblPwd= new Label("Mot de passe :");
		pfPwd= new PasswordField();
		Text txtInstruction = new Text("Veuillez entrer vos informations de connexion");
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
		
		//Aligement
		HBox.setMargin(pfPwd, new Insets(0,0,0,20));
		VBox.setMargin(tflInstruction, new Insets(0,0,20,0));
		VBox.setMargin(btnConfirmer, new Insets(20,0,0,0));
		
		//modifications btnConfirmer
		btnConfirmer.setFont(fntBoutons);
		btnConfirmer.setOnAction(e->GestionPersonnes.connection(tfUtilisateur.getText(),pfPwd.getText(),primaryStage));
		
		hbUtilisateur.getChildren().addAll(lblUtilisateur,tfUtilisateur);
		hbPwd.getChildren().addAll(lblPwd,pfPwd);
		vb.getChildren().addAll(tflInstruction,hbUtilisateur,hbPwd,btnConfirmer,btnRetour);
		scene = new Scene(vb);
	}

	public Scene getScene() {
		return scene;
	}

}
