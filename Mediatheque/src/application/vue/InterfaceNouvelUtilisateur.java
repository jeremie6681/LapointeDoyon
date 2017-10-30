package application.vue;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class InterfaceNouvelUtilisateur {
	
	private Scene scene;
	private RadioButton rbAdherent;
	private RadioButton rbPrepose;
	private Button btnConfirmer;
	private Button btnRetour;
	
	public  InterfaceNouvelUtilisateur(){
		VBox vb= new VBox(10) ;
		rbAdherent= new RadioButton("Adhérent");
		rbPrepose= new RadioButton("Préposé");
		ToggleGroup tgType =new ToggleGroup();
		btnConfirmer = new Button("Confirmer");
		VBox vbRadioButtons = new VBox(10);
		Text txtInstructionRadio = new Text("Choisir le type d'utilisateur");
		Text txtInstruction= new Text("Création d'utilisateur");
		btnRetour = new Button("retour");
		//pour la box de création de préposés
		VBox vbPrepose =new VBox(10) ;
		HBox hbUtilisateur= new HBox(20);
		HBox hbPwd= new HBox(20);
		HBox hbConfirmerPwd= new HBox(20);
		Label lblUtilisateurPrepose=new Label("Nom d'utilisateur :");
		TextField tfUtilisateurPrepose = new TextField();
		Label lblPwd= new Label("Mot de passe :");
		PasswordField pfPwd= new PasswordField();
		Label lblConfirmerPwd= new Label("Confirmer Mot de passe :");
		PasswordField pfConfirmerPwd= new PasswordField();
		Text txtInstructionPrepose = new Text("Préposé");
		//pour la box de creation adherent
		VBox vbAdherent =new VBox(10) ;
		HBox hbUtilisateurAdherent= new HBox(20);
		Label lblUtilisateurAdherent=new Label("Nom d'utilisateur :");
		TextField tfUtilisateurAdherent = new TextField();
		Text txtInstructionAdherent = new Text("Adhérent");
		
		//Modifications FlowPane	
		vb.setAlignment(Pos.CENTER);
		vb.setPadding(new Insets(20));
		
		
		//modifications vbPrepose
		vbPrepose.setPadding(new Insets(20));
		vbPrepose.setAlignment(Pos.CENTER);
		vbPrepose.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),BorderWidths.DEFAULT)));
		
		//modifications txtInstructionPrepose
		txtInstructionPrepose.setFont(Font.font("Arial",FontWeight.NORMAL,FontPosture.REGULAR, 16));

		
		//modifications hbUtilisateur
		hbUtilisateur.setAlignment(Pos.CENTER_LEFT);
		
		//modification hbPwd
		hbPwd.setAlignment(Pos.CENTER_LEFT);
		
		//modification hbConfirmerPwd
		hbConfirmerPwd.setAlignment(Pos.CENTER_LEFT); 
		
		hbUtilisateur.getChildren().addAll(lblUtilisateurPrepose,tfUtilisateurPrepose);
		hbPwd.getChildren().addAll(lblPwd,pfPwd);
		hbConfirmerPwd.getChildren().addAll(lblConfirmerPwd,pfConfirmerPwd);
		vbPrepose.getChildren().addAll(txtInstructionPrepose,hbUtilisateur,hbPwd,hbConfirmerPwd);
		
		
		
		//modifications vbAdherent
		vbAdherent.setPadding(new Insets(20));
		vbAdherent.setAlignment(Pos.CENTER);
		vbAdherent.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),BorderWidths.DEFAULT)));
		
		//modifications txtInstructionAdherent
		txtInstructionAdherent.setFont(Font.font("Arial",FontWeight.NORMAL,FontPosture.REGULAR, 16));

		//modifications hbUtilisateurAdherent
		hbUtilisateurAdherent.setAlignment(Pos.CENTER_LEFT);


		hbUtilisateurAdherent.getChildren().addAll(lblUtilisateurAdherent,tfUtilisateurAdherent);
		vbAdherent.getChildren().addAll(txtInstructionAdherent,hbUtilisateurAdherent);
		
		
		
		//vbRadioButton
		vbRadioButtons.setPadding(new Insets(20));
		vbRadioButtons.setAlignment(Pos.CENTER_LEFT);
		vbRadioButtons.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),BorderWidths.DEFAULT)));
		vbRadioButtons.setMaxWidth(Double.MAX_VALUE);
		
		//btnConfirmer
		btnConfirmer.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.REGULAR, 20));
		
		//txtInstruction
		txtInstruction.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.REGULAR, 18));
		
		//group pour radioButtons
		rbAdherent.setToggleGroup(tgType);
		rbPrepose.setToggleGroup(tgType);
		
		vbRadioButtons.getChildren().addAll(txtInstructionRadio,rbAdherent,rbPrepose);
		vb.getChildren().addAll(txtInstruction,vbRadioButtons,vbPrepose,vbAdherent,btnConfirmer,btnRetour);
		scene = new Scene(vb);
		
	}
	public Scene getScene() {
		return scene;
	}
}
