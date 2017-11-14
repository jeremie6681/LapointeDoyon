package application.vue;


import application.modele.TypePersonne;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.layout.GridPane;
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
	private VBox vb;
	private GridPane gpPrep;
	
	public  InterfaceNouvelUtilisateur(TypePersonne typeAjout){
		vb= new VBox(10) ;
		rbAdherent= new RadioButton("Adhérent");
		rbPrepose= new RadioButton("Préposé");
		ToggleGroup tgType =new ToggleGroup();
		btnConfirmer = new Button("Confirmer");
		VBox vbRadioButtons = new VBox(10);
		Text txtInstructionRadio = new Text("Choisir le type d'utilisateur");
		Text txtInstruction= new Text("Création d'utilisateur");
		btnRetour = new Button("retour");
		//pour la box de création de préposés
		gpPrep= new GridPane();
		Label lblPwd= new Label("Mot de passe :");
		PasswordField pfPwd= new PasswordField();
		Label lblConfirmerPwd= new Label("Confirmer Mot de passe :");
		PasswordField pfConfirmerPwd= new PasswordField();
		Text txtInstructionPrepose = new Text("Préposé");
		//pour la box infos générals
		GridPane gpInfos= new GridPane();
		Label lblNom=new Label("Nom :");
		TextField tfNom = new TextField();
		Label lblPrenom=new Label("Prénom :");
		TextField tfPrenom = new TextField();
		Label lblAdresse=new Label("Adresse :");
		TextField tfAdresse = new TextField();
		Label lblNoTel=new Label("Numéros de Téléphone :");
		TextField tfNoTel = new TextField();
		Text txtInfos = new Text("Infos Générales");
		
		txtInfos.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.REGULAR, 14));
		
		gpInfos.add(txtInfos,1,0 );
		gpInfos.add(lblNom,1, 2);
		gpInfos.add(lblPrenom,1, 3);
		gpInfos.add(lblAdresse,1, 4);
		gpInfos.add(lblNoTel,1, 5);
		gpInfos.add(tfNom,2, 2);
		gpInfos.add(tfPrenom,2, 3);
		gpInfos.add(tfAdresse,2, 4);
		gpInfos.add(tfNoTel,2, 5);
		gpInfos.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),BorderWidths.DEFAULT)));
		gpInfos.setHgap(10);
		gpInfos.setVgap(10);
		gpInfos.setPadding(new Insets(20));
		
		
		
		
		//modifications txtInstructionPrepose
		txtInstructionPrepose.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.REGULAR, 14));
		
		gpPrep.add(txtInstructionPrepose,1,0);
		gpPrep.add(lblPwd,1,2);
		gpPrep.add(lblConfirmerPwd,1,3);
		gpPrep.add(pfPwd,2,2);
		gpPrep.add(pfConfirmerPwd,2,3);
		gpPrep.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),BorderWidths.DEFAULT)));
		gpPrep.setHgap(10);
		gpPrep.setVgap(10);
		gpPrep.setPadding(new Insets(20));
		
		
		
		
		//Modifications vb	
		vb.setAlignment(Pos.CENTER);
		vb.setPadding(new Insets(20));
		
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
		
		//radio buttons 
		rbAdherent.setSelected(true);
		rbAdherent.setOnAction(gestionBoxCreationUtilisateur);
		rbPrepose.setOnAction(gestionBoxCreationUtilisateur);
		vbRadioButtons.getChildren().addAll(txtInstructionRadio,rbAdherent,rbPrepose);
		vb.getChildren().addAll(txtInstruction,vbRadioButtons,gpInfos,btnConfirmer,btnRetour);
		
		scene = new Scene(vb,400,600);
		
	}
	
	public Scene getScene() {
		return scene;
	}
	
	//eventHandlers
	EventHandler<ActionEvent>  gestionBoxCreationUtilisateur=  new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
			if(rbAdherent.isSelected()) {
				vb.getChildren().remove(3);
			}
			else {
				vb.getChildren().add(3, gpPrep);
				
			}
		}
	};
}
