package application.vue;


import application.controleur.GestionPersonnes;
import application.modele.Adherent;
import application.modele.TypePersonne;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
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
import javafx.stage.Stage;


public class InterfaceNouvelUtilisateur {
	
	private Scene scene;
	private Button btnConfirmer;
	private VBox vb;
	private GridPane gpPrep;
	
	private TextField tfNom;
	private TextField tfPrenom;
	private TextField tfAdresse;
	private TextField tfNoTel;
	private Adherent adhAmodifier=null;
	
	public  InterfaceNouvelUtilisateur(TypePersonne typeAjout,Stage stage){
		vb= new VBox(10) ;
		btnConfirmer = new Button("Confirmer");
		Text txtInstruction= new Text("Création d'utilisateur");
		Button btnRetour = new Button("retour");
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
		tfNom = new TextField();
		Label lblPrenom=new Label("Prénom :");
		tfPrenom = new TextField();
		Label lblAdresse=new Label("Adresse :");
		tfAdresse = new TextField();
		Label lblNoTel=new Label("Numéros de Téléphone :");
		tfNoTel = new TextField();
		Text txtInfos = new Text("Informations de la personne ");
		//btnRetour
		btnRetour.setOnAction(e->stage.hide());
		
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
		
		//btnConfirmer
		btnConfirmer.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.REGULAR, 20));
		if(typeAjout==TypePersonne.Prepose) {
			btnConfirmer.setOnAction(e->GestionPersonnes.ajouterAdherent(tfNom.getText(), tfPrenom.getText(), tfAdresse.getText(), tfNoTel.getText(),stage));
		}
		else if (typeAjout==TypePersonne.Admin){
			btnConfirmer.setOnAction(e->GestionPersonnes.ajouterPrepose(tfNom.getText(), tfPrenom.getText(), tfAdresse.getText(), tfNoTel.getText(),pfPwd.getText(),pfConfirmerPwd.getText(),stage));
		}
		else if(typeAjout==null) {
			btnConfirmer.setOnAction(e->GestionPersonnes.modifierAdherent(adhAmodifier,tfAdresse.getText(),tfNoTel.getText(),stage));
		}
		
		//txtInstruction
		txtInstruction.setFont(Font.font("Arial",FontWeight.BOLD,FontPosture.REGULAR, 18));
		
		//typeAjout=TypePersonne.Admin;
		if(typeAjout==TypePersonne.Admin) {
			gpInfos.add(lblPwd, 1, 6);
			gpInfos.add(lblConfirmerPwd, 1, 7);
			gpInfos.add(pfPwd, 2, 6);
			gpInfos.add(pfConfirmerPwd, 2, 7);
		}
		
		vb.getChildren().addAll(txtInstruction,gpInfos,btnConfirmer,btnRetour);
		
		scene = new Scene(vb);
		
	}
	
	public Scene getScene() {
		return scene;
	}
	public void modifierAdherent(Adherent adh) {
		try {
		tfNom.setText(adh.getStrNom());
		tfPrenom.setText(adh.getStrPrenom());
		tfNoTel.setText(adh.getStrNoTelephone());
		tfAdresse.setText(adh.getStrAdresse());
		tfNom.setDisable(true);
		tfPrenom.setDisable(true);
		adhAmodifier=adh;
		
		}catch(NullPointerException n ) {
			Alert alertErreur = new Alert(AlertType.WARNING,"vous devez choisir une personne");
			alertErreur.showAndWait();
		}
	}

}
