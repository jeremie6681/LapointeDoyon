package application.vue;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import application.controleur.GestionDocuments;
import application.controleur.GestionInterface;
import application.modele.Document;
import application.modele.Etat;
import application.modele.ListeDocuments;
import application.modele.ListePersonnes;
import application.modele.Personne;
import application.modele.TypeDocument;
import application.modele.TypePersonne;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Pair;

public class InterfacePrincipale {
	private Scene scene;
	private TabPane tabPane = new TabPane();
	TypePersonne utilisateur = TypePersonne.Prepose;
	//private Stage primaryStage;
	
	//Liste Observable pour les tables dans les onglets
	public ObservableList<Document> donneeDoc, donneeLiv, donneePer, donneeDvd;
	
	@SuppressWarnings("static-access")
	public InterfacePrincipale(Stage primaryStage, TypePersonne TEST,Personne personne ) {
		
		
		Group root =new Group();
		
		BorderPane panneau = new BorderPane();
		
		scene = new Scene(root);
		//scene.setFill(Color.rgb(24, 25, 28));
		
		Label lblTitre = new Label("Médiathèque");
		lblTitre.setFont(Font.font("arial", FontWeight.BOLD	, 35));
		lblTitre.setPadding(new Insets(10,0,15,0));
		
		//lblTitre.setTextFill(Color.WHITE);
		
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tabPane.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),BorderWidths.DEFAULT)));
		
		
		TableView<Document>[] lstTable = tableau();
		
		//onglet document
		Tab ongletDoc = new Tab("Document");
		VBox panneauV = new VBox();
		panneauV.getChildren().add(lstTable[0]);
		ongletDoc.setContent(panneauV);
		tabPane.getTabs().add(ongletDoc);
		
		//Crée les onglets Livre,Periodique et Dvd
		for(int i =0; i< 3;i++) {
			Tab ongletType = new Tab(TypeDocument.values()[i].getStrNomType());
			VBox panneauVboxType = new VBox();
			ongletType.setContent(panneauVboxType);
			
			panneauVboxType.getChildren().add(lstTable[i+1]);
			
			tabPane.getTabs().add(ongletType);
		}
		
		
		//Recherche
		
		
		//panneau.prefWidthProperty().bind(sc);
		
		
		
		
		
		//Panneau de gauche
		
		//-----------------------------> Ajouter question et reinistialliser...
		
		
		
		//Affichage selon le type d'utilisateur
		
		if(utilisateur.equals(TypePersonne.Prepose)) {
			GridPane groupeRecherche = panneauCommunPreAdh(lstTable).getKey();
			VBox panneauTableauDoc = panneauCommunPreAdh(lstTable).getValue();
			
			BorderPane panOption = new BorderPane();
			panOption.setMargin(groupeRecherche, new Insets(15,0,0,0));
			panOption.setBottom(groupeRecherche);
			panOption.setPadding(new Insets(0,30,0,0));
			panOption.setTop(lblTitre);
			
			panneau.setLeft(panOption);
			
			panneau.setCenter(panneauTableauDoc);
			//Panneau préposer a gauche
			panOption.setCenter(optionPreposer());
			
			
			//panneau liste adherent
			VBox panneauGestionAdherent = panneauGestionAdherent();
			panneau.setRight(panneauGestionAdherent);
			panneau.setMargin(panneauGestionAdherent, new Insets(0,0,0,30));
		}
		else if(utilisateur.equals(TypePersonne.Adherent)) {
			GridPane groupeRecherche = panneauCommunPreAdh(lstTable).getKey();
			VBox panneauTableauDoc = panneauCommunPreAdh(lstTable).getValue();
			
			BorderPane panOption = new BorderPane();
			panOption.setMargin(groupeRecherche, new Insets(15,0,0,0));
			panOption.setBottom(groupeRecherche);
			panOption.setPadding(new Insets(0,30,0,0));
			panOption.setTop(lblTitre);
			
			panneau.setLeft(panOption);
			
			panneau.setCenter(panneauTableauDoc);
			// a faire
			//panOption.setCenter(Image);
			
			//Ajouter consulter son dossier
		}
		//Administarteur
		else {
			
		}
		
		
		
		panneau.setPadding(new Insets(20,30,30,30));
		root.getChildren().add(panneau);
		
		primaryStage.setTitle("Médiathèque");
	}
	
	@SuppressWarnings("unchecked")
	private TableView<Document>[] tableau() {
		TableView<Document>[] lstTable = new TableView[4];
		
		//Tableau Document
		TableView<Document> tableDoc = new TableView<Document>();
		TableView<Document> tableLiv = new TableView<Document>();
		TableView<Document> tablePer = new TableView<Document>();
		TableView<Document> tableDvd = new TableView<Document>();

		TableColumn<Document, String> colonneAuteur = new TableColumn<Document, String>("Auteur");
		TableColumn<Document, Integer> colonneVolume = new TableColumn<Document, Integer>("Numéro volume");
		TableColumn<Document, Integer> colonnePerio = new TableColumn<Document, Integer>("Numéro périodique");
		TableColumn<Document, Short> colonneNbDisque = new TableColumn<Document, Short>("Nb disque");
		TableColumn<Document, String> colonneRealisateur = new TableColumn<Document, String>("Réalisateur");
		
		colonneAuteur.setPrefWidth(150);
		colonneVolume.setPrefWidth(100);
		colonnePerio.setPrefWidth(120);
		colonneNbDisque.setPrefWidth(100);
		colonneRealisateur.setPrefWidth(125);
		
		colonneAuteur.setCellValueFactory(new PropertyValueFactory<>("strAuteur"));
		colonneVolume.setCellValueFactory(new PropertyValueFactory<>("intNoVolume"));
		colonnePerio.setCellValueFactory(new PropertyValueFactory<>("intNoPeriodique"));
		colonneNbDisque.setCellValueFactory(new PropertyValueFactory<>("shNbDisques"));
		colonneRealisateur.setCellValueFactory(new PropertyValueFactory<>("strResalisateur"));
		
		colonneTableauCommune(tableDoc);
		
		colonneTableauCommune(tableLiv);
		tableLiv.getColumns().add(colonneAuteur);
		
		colonneTableauCommune(tablePer);
		tablePer.getColumns().addAll(colonneVolume, colonnePerio);
		
		colonneTableauCommune(tableDvd);
		tableDvd.getColumns().addAll(colonneNbDisque, colonneRealisateur);
		
		donneeDoc = FXCollections.observableArrayList(
				ListeDocuments.getInstance().mapDocument.values().stream().flatMap(List::stream).collect(Collectors.toList()));
		donneeLiv = FXCollections.observableArrayList(ListeDocuments.getInstance().mapDocument.get(TypeDocument.Livre));
		donneePer = FXCollections.observableArrayList(ListeDocuments.getInstance().mapDocument.get(TypeDocument.Periodique));
		donneeDvd = FXCollections.observableArrayList(ListeDocuments.getInstance().mapDocument.get(TypeDocument.Dvd));
		
		//Si une recherche est faite, l'onglet conserner va être afficher
		GestionInterface.ecouteurDonneeOnglet(donneeDoc, donneeLiv, tabPane);
		
		tableDoc.setItems(donneeDoc);
		tableLiv.setItems(donneeLiv);
		tablePer.setItems(donneePer);
		tableDvd.setItems(donneeDvd);
		
		lstTable[0] = tableDoc;
		lstTable[1] = tableLiv;
		lstTable[2] = tablePer;
		lstTable[3] = tableDvd;
		
		return lstTable;	
	}
	
	@SuppressWarnings("unchecked")
	private void colonneTableauCommune(TableView<Document> table) {
		TableColumn<Document, String> colonneIdentifiant = new TableColumn<Document, String>("Identifiant");
		TableColumn<Document, String> colonneTitre = new TableColumn<Document, String>("Titre");
		TableColumn<Document, LocalDate> colonneDate = new TableColumn<Document, LocalDate>("Date parution");
		TableColumn<Document, Etat> colonneEtat = new TableColumn<Document, Etat>("Disponibilité");
		
		colonneIdentifiant.setPrefWidth(80);
		colonneTitre.setPrefWidth(275);
		colonneDate.setPrefWidth(100);
		colonneEtat.setPrefWidth(100);
		
		colonneIdentifiant.setCellValueFactory(new PropertyValueFactory<>("strCodeDocument"));
		colonneTitre.setCellValueFactory(new PropertyValueFactory<>("strTitre"));
		colonneDate.setCellValueFactory(new PropertyValueFactory<>("dateParution"));
		colonneEtat.setCellValueFactory(new PropertyValueFactory<>("etatDoc"));
		
		table.getColumns().addAll(colonneIdentifiant,colonneTitre,colonneDate,colonneEtat);
	}

	public Scene getScene() {
		return scene;
	}
	
	private Accordion optionPreposer() {	
		Font policeMenu = Font.font("arial",FontWeight.BOLD ,13);
		
		//gestion document
		Button btnAjouterDocument = new Button("Ajouter Document");
		Button btnSupprimerDocument = new Button("Supprimer Document");
		
		VBox panneauSeconGesDoc = new VBox(10,btnAjouterDocument,btnSupprimerDocument);
		TitledPane panneauGestionDoc = new TitledPane("Gestion Document", panneauSeconGesDoc);
		panneauGestionDoc.setFont(policeMenu);
		panneauSeconGesDoc.setAlignment(Pos.CENTER_LEFT);
		
		//gestion adhérent
		Button btnAjouterAdherent = new Button("Ajouter Adhérent");
		Button btnModifirerAdherent = new Button("Modifier Adhérent");
		Button btnSupprimerAdherent = new Button("Supprimer Adhérent");
		
		VBox panneauSeconGesAdh = new VBox(10,btnAjouterAdherent,btnModifirerAdherent,btnSupprimerAdherent);
		TitledPane panneauGestionAdh = new TitledPane("Gestion Adhérent", panneauSeconGesAdh);
		panneauGestionAdh.setFont(policeMenu);
		panneauSeconGesAdh.setAlignment(Pos.CENTER_LEFT);
		
		//gestion pret
		Button btnEmprunterDoc = new Button("Emprunter un document");
		Button btnRetournerDoc = new Button("Retourner un document");
		Button btnPayerAmende = new Button("Payer une amende");
		
		VBox panneauSeconGesPret = new VBox(10, btnEmprunterDoc,btnRetournerDoc,btnPayerAmende);
		TitledPane panneauGestionPret = new TitledPane("Gestion Prêt", panneauSeconGesPret);
		panneauGestionPret.setFont(policeMenu);
		panneauSeconGesPret.setAlignment(Pos.CENTER_LEFT);
		
		//Panneau des options latérals
		Accordion panneauOptionLateral = new Accordion(panneauGestionDoc, panneauGestionAdh, panneauGestionPret);
		panneauOptionLateral.setExpandedPane(panneauGestionPret);
		panneauOptionLateral.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),BorderWidths.DEFAULT)));
		
		return panneauOptionLateral;
	}
	
	@SuppressWarnings("unchecked")
	private VBox panneauGestionAdherent() {
		VBox panneauListePersonne =new VBox(10);
		panneauListePersonne.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),BorderWidths.DEFAULT)));
		
		Label lblTitrelistePersonne = new Label("Adhérents");
		lblTitrelistePersonne.setFont(Font.font("arial",FontWeight.BOLD ,16));
		
		TableView<Personne> tableAdherent = new TableView<Personne>();
		
		TableColumn<Personne, String> colonneNoPersonne = new TableColumn<Personne, String>("Identifiant");
		TableColumn<Personne, String> colonnePrenom = new TableColumn<Personne, String>("Prénom");
		TableColumn<Personne, String> colonneNom = new TableColumn<Personne, String>("Nom");
		TableColumn<Personne, String> colonneAdresse = new TableColumn<Personne, String>("Adresse");
		TableColumn<Personne, String> colonneTelephone = new TableColumn<Personne, String>("Numéro téléphone");
		
		colonneNoPersonne.setPrefWidth(100);
		colonnePrenom.setPrefWidth(100);
		colonneNom.setPrefWidth(100);
		colonneAdresse.setPrefWidth(120);
		colonneTelephone.setPrefWidth(120);
		
		colonneNoPersonne.setCellValueFactory(new PropertyValueFactory<>("strNoPersonne"));
		colonnePrenom.setCellValueFactory(new PropertyValueFactory<>("strPrenom"));
		colonneNom.setCellValueFactory(new PropertyValueFactory<>("strNom"));
		colonneAdresse.setCellValueFactory(new PropertyValueFactory<>("strAdresse"));
		colonneTelephone.setCellValueFactory(new PropertyValueFactory<>("strNoTelephone"));
		
		ObservableList<Personne> donneePersonne = FXCollections.observableList(ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent));
		
		tableAdherent.getColumns().addAll(colonneNoPersonne,colonnePrenom,colonneNom,colonneAdresse,colonneTelephone);
		tableAdherent.setItems(donneePersonne);
		tableAdherent.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),BorderWidths.DEFAULT)));
		
		panneauListePersonne.getChildren().addAll(lblTitrelistePersonne,tableAdherent);
		panneauListePersonne.setAlignment(Pos.CENTER);
		panneauListePersonne.setPadding(new Insets(15));
		
		VBox.setMargin(lblTitrelistePersonne, new Insets(0,0,10,0));
		
		return panneauListePersonne;
	}
	
	private  Pair<GridPane, VBox> panneauCommunPreAdh(TableView<Document>[] lstTable) {
		//Panneau recherche
		GridPane groupeRecherche = new GridPane();
		groupeRecherche.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),BorderWidths.DEFAULT)));
		groupeRecherche.setHgap(15);
		groupeRecherche.setVgap(15);
		
		groupeRecherche.setPrefSize(210, 100);
		
		Label lblRecherche = new Label("Recherche document");
		lblRecherche.setFont(Font.font("arial",FontWeight.BOLD ,15));
		
		ToggleGroup tg = new ToggleGroup();
		TextField tbRecherche = new TextField();
		RadioButton rbAuteur = new RadioButton("Auteur");
		RadioButton rbMotCle = new RadioButton("Mot clé");
		Button btnRecherche = new Button("Recherche");
		Button btnReinitialiseListe = new Button("Réinitialiser liste document");
		
		groupeRecherche.add(lblRecherche, 0, 0,2,1);
		groupeRecherche.add(tbRecherche, 0, 1);
		groupeRecherche.add(rbAuteur, 1, 1);
		groupeRecherche.add(rbMotCle, 1, 2);
		groupeRecherche.add(btnRecherche, 0, 2);
		
		btnRecherche.setOnAction(btn-> GestionDocuments.rechercherDocument(tbRecherche.getText(), rbMotCle.isSelected(), lstTable));
		btnReinitialiseListe.setOnAction(btn -> GestionInterface.rechargeDonnee(donneeDoc, donneeLiv, donneePer, donneeDvd));
		
		GridPane.setMargin(lblRecherche, new Insets(15,0,0,15));
		GridPane.setHalignment(lblRecherche, HPos.CENTER);
		GridPane.setMargin(tbRecherche, new Insets(10,0,5,15));
		GridPane.setHalignment(btnRecherche, HPos.CENTER);
		
		GridPane.setMargin(btnRecherche, new Insets(0,0,15,15));
		GridPane.setMargin(rbMotCle, new Insets(0,0,15,0));
		
		rbAuteur.setToggleGroup(tg);
		rbMotCle.setToggleGroup(tg);
		rbAuteur.setSelected(true);
		
		tbRecherche.setPromptText("Recherche");
		tbRecherche.setMaxWidth(100);
		
		//Tableau Document
		VBox panneauTableauDoc = new VBox(10);
		panneauTableauDoc.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),BorderWidths.DEFAULT)));
		
		Label lblTitreListeDoc = new Label("Documents");
		lblTitreListeDoc.setFont(Font.font("arial",FontWeight.BOLD ,15));
		
		panneauTableauDoc.getChildren().addAll(lblTitreListeDoc,tabPane);
		panneauTableauDoc.setPadding(new Insets(15));
		panneauTableauDoc.setAlignment(Pos.CENTER);
		
		
		return new Pair<GridPane, VBox>(groupeRecherche, panneauTableauDoc);
	}
}
