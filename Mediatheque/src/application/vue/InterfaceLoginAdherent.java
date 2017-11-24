package application.vue;

import application.modele.Style;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

//Interface lorsqu'un adhérent veut s'identifier pour accéder à son dossier
public class InterfaceLoginAdherent {

	private Scene scene;
	private Button btnConfirmer;
	private TextField tfNoTell;
	private RadioButton rbTel;
	private RadioButton rbNom;
	private TextField tfNom;
	private TextField tfPrenom;
	private GridPane gpNom;
	private GridPane gpTell;
	private Stage stage;

	public InterfaceLoginAdherent(Stage stage) {
		this.stage = stage;

		VBox vb = new VBox(10);
		vb.setPadding(new Insets(20));
		vb.setAlignment(Pos.CENTER);
		// text pour instructions
		Text txtInstruction = new Text("Veuillez entrer vos informations de connexion");
		txtInstruction.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 16));
		TextFlow tflInstruction = new TextFlow(txtInstruction);

		// vbox pour choix de connection
		VBox vbRb = new VBox(10);
		ToggleGroup tg = new ToggleGroup();
		rbNom = new RadioButton("Connexion avec nom et prénom");
		rbTel = new RadioButton("Connexion avec le numéro de téléphone");
		rbNom.setToggleGroup(tg);
		rbTel.setToggleGroup(tg);
		rbNom.setOnAction(gestionRadioButtons);
		rbTel.setOnAction(gestionRadioButtons);
		rbNom.setSelected(true);
		vbRb.getChildren().addAll(rbNom, rbTel);

		// gpNom
		gpNom = new GridPane();
		Text txNom = new Text("Connexion par Nom & Prénom");
		Label lblNom = new Label("Nom :");
		tfNom = new TextField();
		Label lblPrenom = new Label("Prénom:");
		tfPrenom = new TextField();

		gpNom.add(txNom, 1, 0);
		gpNom.add(lblNom, 1, 1);
		gpNom.add(tfNom, 2, 1);
		gpNom.add(lblPrenom, 1, 2);
		gpNom.add(tfPrenom, 2, 2);
		gpNom.setBorder(Style.styleBordure);
		gpNom.setPadding(new Insets(20));
		gpNom.setHgap(10);
		gpNom.setVgap(10);

		// gpTell
		gpTell = new GridPane();
		Text txTell = new Text("Connexion par No. de téléphone");
		Label lblNotell = new Label("Numéro de téléphone :");
		tfNoTell = new TextField();

		gpTell.add(txTell, 1, 0);
		gpTell.add(lblNotell, 1, 1);
		gpTell.add(tfNoTell, 2, 1);
		gpTell.setBorder(Style.styleBordure);
		gpTell.setPadding(new Insets(20));
		gpTell.setHgap(10);
		gpTell.setVgap(10);
		gpTell.setDisable(true);

		// boutons de bas de page
		btnConfirmer = new Button("Se connecter");
		btnConfirmer.setFont(Style.fntBoutons);
		btnConfirmer.setOnAction(
				e -> application.controleur.GestionPersonnes.connectionAdh(tfNom.getText(), tfPrenom.getText(), stage));

		Button btnRetour;
		btnRetour = new Button("Retour");
		btnRetour.setOnAction(e -> stage.hide());

		// addition du tout dans un vbox final
		vb.getChildren().addAll(tflInstruction, vbRb, gpNom, gpTell, btnConfirmer, btnRetour);
		scene = new Scene(vb);

	}

	public Scene getScene() {
		return scene;
	}

	//Pour le choix de connexion entre Numéro de téléphone ou (nom et prénom)
	EventHandler<ActionEvent> gestionRadioButtons = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
			if (rbTel.isSelected()) {
				gpTell.setDisable(false);
				gpNom.setDisable(true);
				btnConfirmer.setOnAction(
						e -> application.controleur.GestionPersonnes.connectionAdh(tfNoTell.getText(), stage));
			} else {
				gpTell.setDisable(true);
				gpNom.setDisable(false);
				btnConfirmer.setOnAction(e -> application.controleur.GestionPersonnes.connectionAdh(tfNom.getText(),
						tfPrenom.getText(), stage));
			}
		}
	};
}
