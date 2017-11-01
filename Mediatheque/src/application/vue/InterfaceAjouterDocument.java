package application.vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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

public class InterfaceAjouterDocument {
	private Scene scene;
	private RadioButton rbDVD;
	private RadioButton rbPeriodique;
	private RadioButton rbLivre;
	private Button btnConfirmer;
	private Button btnRetour;
	private GridPane gpLivre;
	private GridPane gpPeriodique;
	private GridPane gpDVD;
	private VBox vb;
	
	public  InterfaceAjouterDocument(){
		vb= new VBox(10) ;
		rbDVD= new RadioButton("DVD");
		rbPeriodique= new RadioButton("Périodique");
		rbLivre= new RadioButton("Livre");
		ToggleGroup tgType =new ToggleGroup();
		btnConfirmer = new Button("Confirmer");
		VBox vbRadioButtons = new VBox(10);
		Text txtInstructionRadio = new Text("Choisir le type de document");
		Text txtInstruction= new Text("Ajout de document");
		btnRetour = new Button("retour");
		Font fntTitre=(Font.font("Arial",FontWeight.BOLD,FontPosture.REGULAR, 14));
		//pour la box infos générals
		GridPane gpInfos= new GridPane();
		Label lblTitre=new Label("Titre:");
		TextField tfTitre = new TextField();
		Label lblDate=new Label("Date de Parution :");
		DatePicker tfDate = new DatePicker();
		Text txtInfos = new Text("Informations sur le document  ");
		//pour la box DVD
		gpDVD= new GridPane();
		Label lblNumDisque=new Label("No de periodique:");
		TextField tfNumDisque = new TextField();
		Label lblRealisateur=new Label("Auteur :");
		TextField tfRealisateur = new TextField();
		Text txtInfosDVD= new Text("Informations sur le DVD ");
		//pour la Box Perriodique
		gpPeriodique= new GridPane();
		Label lblNoPeriodique=new Label("No de periodique:");
		TextField tfNoPeriodique = new TextField();
		Label lblNoVolume=new Label("No De Volume :");
		TextField tfNoVolume = new TextField();
		Text txtInfosPeriodique= new Text("Informations sur le Périodique ");
		//pour les livres
		gpLivre = new GridPane();
		Label lblAuteur=new Label("Auteur :");
		TextField tfAuteur = new TextField();
		Text txtInfosLivre = new Text("Informations sur le livre ");
		
		//texts
		txtInfos.setFont(fntTitre);
		txtInfosDVD.setFont(fntTitre);
		txtInfosLivre.setFont(fntTitre);
		txtInfosPeriodique.setFont(fntTitre);
		
		
		gpInfos.add(txtInfos, 1, 0);
		gpInfos.add(lblTitre,1, 2);
		gpInfos.add(lblDate,1, 3);
		gpInfos.add(tfTitre,2, 2);
		gpInfos.add(tfDate,2, 3);
		gpInfos.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),BorderWidths.DEFAULT)));
		gpInfos.setHgap(10);
		gpInfos.setVgap(10);
		gpInfos.setPadding(new Insets(20));
		
		gpDVD.add(txtInfosDVD, 1, 0);
		gpDVD.add(lblNumDisque,1, 2);
		gpDVD.add(lblRealisateur,1, 3);
		gpDVD.add(tfNumDisque,2, 2);
		gpDVD.add(tfRealisateur,2, 3);
		gpDVD.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),BorderWidths.DEFAULT)));
		gpDVD.setHgap(10);
		gpDVD.setVgap(10);
		gpDVD.setPadding(new Insets(20));
		
		
		gpPeriodique.add(txtInfosPeriodique, 1, 0);
		gpPeriodique.add(lblNoPeriodique,1, 2);
		gpPeriodique.add(lblNoVolume,1, 3);
		gpPeriodique.add(tfNoPeriodique,2, 2);
		gpPeriodique.add(tfNoVolume,2, 3);
		gpPeriodique.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),BorderWidths.DEFAULT)));
		gpPeriodique.setHgap(10);
		gpPeriodique.setVgap(10);
		gpPeriodique.setPadding(new Insets(20));
		
		
		gpLivre.add(txtInfosLivre, 1, 0);
		gpLivre.add(lblAuteur,1, 2);
		gpLivre.add(tfAuteur,2, 2);
		gpLivre.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),BorderWidths.DEFAULT)));
		gpLivre.setHgap(10);
		gpLivre.setVgap(10);
		gpLivre.setPadding(new Insets(20));
		
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
		rbDVD.setToggleGroup(tgType);
		rbPeriodique.setToggleGroup(tgType);
		rbLivre.setToggleGroup(tgType);
		//radio buttons 
		rbDVD.setSelected(true);
		rbDVD.setOnAction(gestionBoxCreationDocuments);
		rbLivre.setOnAction(gestionBoxCreationDocuments);
		rbPeriodique.setOnAction(gestionBoxCreationDocuments);
		vbRadioButtons.getChildren().addAll(txtInstructionRadio,rbDVD,rbPeriodique,rbLivre);
		vb.getChildren().addAll(txtInstruction,vbRadioButtons,gpInfos,gpDVD,btnConfirmer,btnRetour);
		
		scene = new Scene(vb);
	}
	public Scene getScene() {
		return scene;
	}
	EventHandler<ActionEvent>  gestionBoxCreationDocuments=  new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
			vb.getChildren().remove(3)	;
			
			if(rbDVD.isSelected()) {
				vb.getChildren().add(3, gpDVD);
			}
			else if(rbLivre.isSelected()) {
				vb.getChildren().add(3, gpLivre);
			}
			else {
				vb.getChildren().add(3,gpPeriodique);
			}
		}
	};
}

