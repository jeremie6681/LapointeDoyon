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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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
import javafx.stage.Stage;

import java.time.LocalDate;

import application.controleur.GestionDocuments;
import application.modele.Style;
import application.modele.TypeDocument;

public class InterfaceAjouterDocument {
	private Scene scene;
	private RadioButton rbDVD;
	private RadioButton rbPeriodique;
	private RadioButton rbLivre;
	private GridPane gpLivre;
	private GridPane gpPeriodique;
	private GridPane gpDVD;
	private VBox vb;

	private TextField tfAuteur;
	private TextField tfNoVolume;
	private TextField tfNoPeriodique;
	private TextField tfRealisateur;
	private DatePicker tfDate;
	private TextField tfTitre;
	private Spinner<Integer> spinner;
	private Stage stage;

	public InterfaceAjouterDocument(Stage stage) {
		this.stage = stage;
		vb = new VBox(10);
		rbDVD = new RadioButton("DVD");
		rbPeriodique = new RadioButton("Périodique");
		rbLivre = new RadioButton("Livre");
		ToggleGroup tgType = new ToggleGroup();
		Button btnConfirmer = new Button("Confirmer");
		VBox vbRadioButtons = new VBox(10);
		Text txtInstructionRadio = new Text("Choisir le type de document");
		Text txtInstruction = new Text("Ajout de document");
		Button btnRetour = new Button("retour");
		Font fntTitre = (Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 14));

		// pour la box infos générals
		GridPane gpInfos = new GridPane();
		Label lblTitre = new Label("Titre:");
		tfTitre = new TextField();
		Label lblDate = new Label("Date de Parution :");
		tfDate = new DatePicker();
		Text txtInfos = new Text("Informations sur le document  ");

		// pour la box DVD
		gpDVD = new GridPane();
		Label lblNumDisque = new Label("Nombre");

		// TextField tfNumDisque = new TextField();
		Label lblRealisateur = new Label("Réalisateur:");
		tfRealisateur = new TextField();
		Text txtInfosDVD = new Text("Informations sur le DVD ");

		// pour la Box Perriodique
		gpPeriodique = new GridPane();
		Label lblNoPeriodique = new Label("No de periodique:");
		tfNoPeriodique = new TextField();
		Label lblNoVolume = new Label("No De Volume :");
		tfNoVolume = new TextField();
		Text txtInfosPeriodique = new Text("Informations sur le Périodique ");

		// pour les livres
		gpLivre = new GridPane();
		Label lblAuteur = new Label("Auteur :");
		tfAuteur = new TextField();
		Text txtInfosLivre = new Text("Informations sur le livre ");

		// spinner DVD
		spinner = new Spinner<Integer>();
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 1);
		spinner.setValueFactory(valueFactory);

		// texts
		txtInfos.setFont(fntTitre);
		txtInfosDVD.setFont(fntTitre);
		txtInfosLivre.setFont(fntTitre);
		txtInfosPeriodique.setFont(fntTitre);

		gpInfos.add(txtInfos, 1, 0);
		gpInfos.add(lblTitre, 1, 2);
		gpInfos.add(lblDate, 1, 3);
		gpInfos.add(tfTitre, 2, 2);
		gpInfos.add(tfDate, 2, 3);
		gpInfos.setBorder(Style.styleBordure);
		gpInfos.setHgap(10);
		gpInfos.setVgap(10);
		gpInfos.setPadding(new Insets(20));

		gpDVD.add(txtInfosDVD, 1, 0);
		gpDVD.add(lblNumDisque, 1, 2);
		gpDVD.add(lblRealisateur, 1, 3);
		gpDVD.add(spinner, 2, 2);
		gpDVD.add(tfRealisateur, 2, 3);
		gpDVD.setBorder(Style.styleBordure);
		gpDVD.setHgap(10);
		gpDVD.setVgap(10);
		gpDVD.setPadding(new Insets(20));

		gpPeriodique.add(txtInfosPeriodique, 1, 0);
		gpPeriodique.add(lblNoPeriodique, 1, 2);
		gpPeriodique.add(lblNoVolume, 1, 3);
		gpPeriodique.add(tfNoPeriodique, 2, 2);
		gpPeriodique.add(tfNoVolume, 2, 3);
		gpPeriodique.setBorder(Style.styleBordure);
		gpPeriodique.setHgap(10);
		gpPeriodique.setVgap(10);
		gpPeriodique.setPadding(new Insets(20));

		gpLivre.add(txtInfosLivre, 1, 0);
		gpLivre.add(lblAuteur, 1, 2);
		gpLivre.add(tfAuteur, 2, 2);
		gpLivre.setBorder(Style.styleBordure);
		gpLivre.setHgap(10);
		gpLivre.setVgap(10);
		gpLivre.setPadding(new Insets(20));

		// Modifications vb
		vb.setAlignment(Pos.CENTER);
		vb.setPadding(new Insets(20));

		// vbRadioButton
		vbRadioButtons.setPadding(new Insets(20));
		vbRadioButtons.setAlignment(Pos.CENTER_LEFT);
		vbRadioButtons.setBorder(Style.styleBordure);
		vbRadioButtons.setMaxWidth(Double.MAX_VALUE);

		// btnConfirmer
		btnConfirmer.setFont(Style.fntBoutons);
		btnConfirmer.setOnAction(gestionAjouterDocuments);
		// txtInstruction
		txtInstruction.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 18));
		// btnRetour
		btnRetour.setOnAction(e -> stage.hide());

		// group pour radioButtons
		rbDVD.setToggleGroup(tgType);
		rbPeriodique.setToggleGroup(tgType);
		rbLivre.setToggleGroup(tgType);

		// radio buttons
		rbDVD.setSelected(true);
		rbDVD.setOnAction(gestionBoxCreationDocuments);
		rbLivre.setOnAction(gestionBoxCreationDocuments);
		rbPeriodique.setOnAction(gestionBoxCreationDocuments);
		vbRadioButtons.getChildren().addAll(txtInstructionRadio, rbDVD, rbPeriodique, rbLivre);
		vb.getChildren().addAll(txtInstruction, vbRadioButtons, gpInfos, gpDVD, btnConfirmer, btnRetour);

		scene = new Scene(vb);
		stage.setTitle("Ajout de document");
	}

	public Scene getScene() {
		return scene;
	}

	EventHandler<ActionEvent> gestionBoxCreationDocuments = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
			vb.getChildren().remove(3);

			if (rbDVD.isSelected()) {
				vb.getChildren().add(3, gpDVD);
			} else if (rbLivre.isSelected()) {
				vb.getChildren().add(3, gpLivre);
			} else {
				vb.getChildren().add(3, gpPeriodique);
			}
		}
	};
	EventHandler<ActionEvent> gestionAjouterDocuments = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
			String strTitre = tfTitre.getText();
			LocalDate dateParution = tfDate.getValue();
			String strRealisateur;
			String strNoVolume;
			String strNoPeriodique;
			String strAuteur;
			boolean booAjoute = false;
			TypeDocument type;
			if (rbDVD.isSelected()) {
				strRealisateur = tfRealisateur.getText();
				type = TypeDocument.Dvd;
				booAjoute = GestionDocuments.ajouterDVD(strTitre, dateParution, spinner.getValue(), strRealisateur);
			} else if (rbLivre.isSelected()) {
				strAuteur = tfAuteur.getText();
				type = TypeDocument.Livre;
				booAjoute = GestionDocuments.ajouterLivre(strTitre, dateParution, strAuteur);
			} else {
				strNoPeriodique = tfNoPeriodique.getText();
				strNoVolume = tfNoPeriodique.getText();
				type = TypeDocument.Periodique;
				booAjoute = GestionDocuments.ajouterPeriodique(strTitre, dateParution, strNoVolume, strNoPeriodique);
			}
			if (booAjoute) {
				stage.hide();
			}
		}
	};
}
