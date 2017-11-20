package application.vue;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.sun.swing.internal.plaf.metal.resources.metal;

import application.controleur.GestionDocuments;
import application.controleur.GestionInterface;
import application.controleur.GestionPersonnes;
import application.controleur.GestionPrets;
import application.modele.Adherent;
import application.modele.Document;
import application.modele.Etat;
import application.modele.ListeDocuments;
import application.modele.ListePersonnes;
import application.modele.Personne;
import application.modele.Prepose;
import application.modele.TypeDocument;
import application.modele.TypePersonne;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;

public class InterfacePrincipale {
	private Scene scene;
	private TabPane tabPane = new TabPane();
	
	TypePersonne utilisateur = TypePersonne.Admin;
	private TableView<Document>[] lstTable; 
	private TableView<Personne> tableAdherent;
	//Liste Observable pour les tables dans les onglets
	public ObservableList<Document> donneeDoc, donneeLiv, donneePer, donneeDvd;
	public ObservableList<Personne> donneeAdherent;
	@SuppressWarnings("static-access")

	public InterfacePrincipale(Stage primaryStage, TypePersonne type,Personne personne ) {
		//utilisateur = type;
		
		Group root =new Group();
		
		BorderPane panneau = new BorderPane();
		
		scene = new Scene(root);
		//scene.setFill(Color.rgb(24, 25, 28));
		
		Label lblTitre = new Label("M�diath�que");
		lblTitre.setFont(Font.font("arial", FontWeight.BOLD	, 35));
		lblTitre.setPadding(new Insets(10,0,15,0));
		
		//lblTitre.setTextFill(Color.WHITE);
		
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tabPane.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),BorderWidths.DEFAULT)));
		
		
		lstTable = tableau();
		
		//onglet document
		Tab ongletDoc = new Tab("Document");
		VBox panneauV = new VBox();
		panneauV.getChildren().add(lstTable[0]);
		ongletDoc.setContent(panneauV);
		tabPane.getTabs().add(ongletDoc);
		
		//Cr�e les onglets Livre,Periodique et Dvd
		for(int i =0; i< 3;i++) {
			Tab ongletType = new Tab(TypeDocument.values()[i].getStrNomType());
			VBox panneauVboxType = new VBox();
			ongletType.setContent(panneauVboxType);
			
			panneauVboxType.getChildren().add(lstTable[i+1]);
			
			tabPane.getTabs().add(ongletType);
		}
		
		//-----------------------------> Ajouter question et reinistialliser... d�connexion
		
		ImageView ivDeconnection = new ImageView(new Image("image-test.png"));
		
		ImageView ivReinsialiseRecherche = new ImageView(new Image("image-test.png"));
		ImageView ivInformationLogiciel = new ImageView(new Image("image-test.png"));
		
		
		ivDeconnection.setFitHeight(30);
		ivDeconnection.setFitWidth(30);
		
		ivReinsialiseRecherche.setFitHeight(30);
		ivReinsialiseRecherche.setFitWidth(30);
		
		ivInformationLogiciel.setFitHeight(30);
		ivInformationLogiciel.setFitWidth(30);
		
		ivReinsialiseRecherche.setOnMouseClicked( m -> GestionInterface.rechargeDonneeDoc(donneeDoc, donneeLiv, donneePer, donneeDvd));
		
		
		
		HBox panneauBoutonIcone = new HBox(60);
		panneauBoutonIcone.setPadding(new Insets(10));
		
		
		
		
		
		//Affichage selon le type d'utilisateur
		
		if(utilisateur.equals(TypePersonne.Prepose)) {
			GridPane groupeRecherche = panneauCommunPreAdh(lstTable).getKey();
			
			panneauBoutonIcone.getChildren().addAll(ivDeconnection,ivReinsialiseRecherche,ivInformationLogiciel);
			VBox panneauCentreGauche = new VBox(10, optionPreposer(),panneauBoutonIcone);
			
			//Panneau pr�poser a gauche
			BorderPane panOption = new BorderPane();
			panOption.setMargin(groupeRecherche, new Insets(15,0,0,0));
			panOption.setBottom(groupeRecherche);
			panOption.setPadding(new Insets(0,30,0,0));
			panOption.setTop(lblTitre);
			panOption.setCenter(panneauCentreGauche);
			
			panneau.setLeft(panOption);
			panneau.setCenter(panneauCommunPreAdh(lstTable).getValue());
			
			
			//panneau liste adherent
			VBox panneauGestionAdherent = panneauGestionAdherent();
			panneau.setRight(panneauGestionAdherent);
			panneau.setMargin(panneauGestionAdherent, new Insets(0,0,0,30));
		}
		else if(utilisateur.equals(TypePersonne.Adherent)) {
			GridPane groupeRecherche = panneauCommunPreAdh(lstTable).getKey();
			BorderPane panOption = new BorderPane();
			panOption.setMargin(groupeRecherche, new Insets(15,0,0,0));
			panOption.setBottom(groupeRecherche);
			panOption.setPadding(new Insets(0,30,0,0));
			panOption.setTop(lblTitre);
			
			panneau.setCenter(panneauCommunPreAdh(lstTable).getValue());
			
			panneau.setLeft(panOption);
			// a faire
			//panOption.setCenter(Image);
			
			//Ajouter consulter son dossier
		}
		//Administarteur
		else {
			GridPane panneauGestionDesPrepose = panneauAdministrateur().getValue();
			
			panneau.setCenter(panneauAdministrateur().getKey());
			panneau.setBottom(panneauGestionDesPrepose);
			panneau.setTop(lblTitre);
			panneau.setAlignment(lblTitre, Pos.CENTER);
			
			panneau.setMargin(panneauGestionDesPrepose, new Insets(25,0,0,0));
		}
		
		
		
		panneau.setPadding(new Insets(20,30,30,30));
		root.getChildren().add(panneau);
		
		//Fait la s�rialisation lorsque l'on quitte l'application
		primaryStage.setOnCloseRequest(r -> {ListePersonnes.getInstance().serialisation();ListeDocuments.getInstance().serialisation();});
		
		primaryStage.setTitle("M�diath�que");
	}
	
	@SuppressWarnings("unchecked")
	private TableView<Document>[] tableau() {
		TableView<Document>[] lstTable = new TableView[4];
		
		//Tableau Document
		TableView<Document> tableDoc = new TableView<Document>(); ///jai gosser avec cela
		TableView<Document> tableLiv = new TableView<Document>();
		TableView<Document> tablePer = new TableView<Document>();
		TableView<Document> tableDvd = new TableView<Document>();

		TableColumn<Document, String> colonneAuteur = new TableColumn<Document, String>("Auteur");
		TableColumn<Document, Integer> colonneVolume = new TableColumn<Document, Integer>("Num�ro volume");
		TableColumn<Document, Integer> colonnePerio = new TableColumn<Document, Integer>("Num�ro p�riodique");
		TableColumn<Document, Short> colonneNbDisque = new TableColumn<Document, Short>("Nb disque");
		TableColumn<Document, String> colonneRealisateur = new TableColumn<Document, String>("R�alisateur");
		
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
		
		//Si une recherche est faite, l'onglet conserner va �tre afficher
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
	
	//Colonne commune pour les tableau des documents
	@SuppressWarnings("unchecked")
	private void colonneTableauCommune(TableView<Document> table) {
		TableColumn<Document, String> colonneIdentifiant = new TableColumn<Document, String>("Identifiant");
		TableColumn<Document, String> colonneTitre = new TableColumn<Document, String>("Titre");
		TableColumn<Document, LocalDate> colonneDate = new TableColumn<Document, LocalDate>("Date parution");
		TableColumn<Document, Etat> colonneEtat = new TableColumn<Document, Etat>("Disponibilit�");
		
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
		Stage secondaryStage = new Stage();
		secondaryStage.initModality(Modality.APPLICATION_MODAL);
		secondaryStage.sizeToScene();
		final InterfaceAjouterDocument interfaceAjouterDoc = new InterfaceAjouterDocument(secondaryStage);
		final InterfaceNouvelUtilisateur interfaceAjouterUtilisateur= new InterfaceNouvelUtilisateur(utilisateur,secondaryStage);
		final InterfaceNouvelUtilisateur intefaceModifUtilisateur= new InterfaceNouvelUtilisateur(null,secondaryStage);
		//gestion document
		Button btnAjouterDocument = new Button("Ajouter Document");
		//ajouter Document et mettre a jour l'affichage
		btnAjouterDocument.setOnAction(e->{secondaryStage.setScene(interfaceAjouterDoc.getScene());secondaryStage.showAndWait();
		GestionInterface.rechargeDonneeDoc(donneeDoc, donneeLiv, donneePer, donneeDvd);});
		
		Button btnSupprimerDocument = new Button("Supprimer Document");
		//supprimer un document et mettre l'affichage � jour
		btnSupprimerDocument.setOnAction(e->
		{GestionDocuments.supprimerDocuments(lstTable[tabPane.getSelectionModel().getSelectedIndex()].getSelectionModel().getSelectedItem());
		GestionInterface.rechargeDonneeDoc(donneeDoc, donneeLiv, donneePer, donneeDvd);});
		
		VBox panneauSeconGesDoc = new VBox(10,btnAjouterDocument,btnSupprimerDocument);
		TitledPane panneauGestionDoc = new TitledPane("Gestion Document", panneauSeconGesDoc);
		panneauGestionDoc.setFont(policeMenu);
		panneauSeconGesDoc.setAlignment(Pos.CENTER_LEFT);
		
		//gestion adh�rent
		Button btnAjouterAdherent = new Button("Ajouter Adh�rent");
		//Adherent test = new Adherent("gjhgjh", "gjhfuy", "jhfe", "htyt");
		btnAjouterAdherent.setOnAction(e->{secondaryStage.setScene(interfaceAjouterUtilisateur.getScene());secondaryStage.showAndWait();
		donneeAdherent.forEach(System.out::println);});
		
		Button btnModifirerAdherent = new Button("Modifier Adh�rent");
		btnModifirerAdherent.setOnAction(e->{intefaceModifUtilisateur.modifierAdherent((Adherent) tableAdherent.getSelectionModel().getSelectedItem());
		secondaryStage.setScene(intefaceModifUtilisateur.getScene());
		if ((Adherent) tableAdherent.getSelectionModel().getSelectedItem()!=null) {secondaryStage.showAndWait();};});
		
		//tableAdherent.
		Button btnSupprimerAdherent = new Button("Supprimer Adh�rent");
		btnSupprimerAdherent.setOnAction(e->{GestionPersonnes.supprimerPersonne((Adherent) tableAdherent.getSelectionModel().getSelectedItem());});
		
		VBox panneauSeconGesAdh = new VBox(10,btnAjouterAdherent,btnModifirerAdherent,btnSupprimerAdherent);
		TitledPane panneauGestionAdh = new TitledPane("Gestion Adh�rent", panneauSeconGesAdh);
		panneauGestionAdh.setFont(policeMenu);
		panneauSeconGesAdh.setAlignment(Pos.CENTER_LEFT);
		
		//gestion pret et amende
		Button btnEmprunterDoc = new Button("Emprunter un document");
		btnEmprunterDoc.setOnAction(e->{GestionPrets.emprunterDocument((Adherent) tableAdherent.getSelectionModel().getSelectedItem(),
				lstTable[tabPane.getSelectionModel().getSelectedIndex()].getSelectionModel().getSelectedItem());
		GestionInterface.rechargeDonneeDoc(donneeDoc, donneeLiv, donneePer, donneeDvd);});
		
		Button btnRetournerDoc = new Button("Retourner un document");
		btnRetournerDoc.setOnAction(e->{GestionPrets.retournerDocument(lstTable[tabPane.getSelectionModel().getSelectedIndex()].getSelectionModel().getSelectedItem());
		GestionInterface.rechargeDonneeDoc(donneeDoc, donneeLiv, donneePer, donneeDvd);});
		
		Button btnPayerAmende = new Button("Payer une amende");
		btnPayerAmende.setOnAction(e->{ListePersonnes.getInstance().miseAjourPrets();GestionPrets.payerAmande((Adherent) tableAdherent.getSelectionModel().getSelectedItem());
		GestionInterface.rechargeDonneeDoc(donneeDoc, donneeLiv, donneePer, donneeDvd);});
		
		Button btnVisualisePret = new Button("Visualise les pr�ts d'un adh�rent");
		btnVisualisePret.setOnAction(e->GestionPersonnes.afficherPrets((Adherent) tableAdherent.getSelectionModel().getSelectedItem()));
		
		VBox panneauSeconGesPret = new VBox(10, btnEmprunterDoc,btnRetournerDoc,btnPayerAmende,btnVisualisePret);
		TitledPane panneauGestionPret = new TitledPane("Gestion Pr�t", panneauSeconGesPret);
		panneauGestionPret.setFont(policeMenu);
		panneauSeconGesPret.setAlignment(Pos.CENTER_LEFT);
		
		//Panneau des options lat�rals
		Accordion panneauOptionLateral = new Accordion(panneauGestionDoc, panneauGestionAdh, panneauGestionPret);
		panneauOptionLateral.setExpandedPane(panneauGestionPret);
		panneauOptionLateral.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),BorderWidths.DEFAULT)));
		panneauOptionLateral.setMinHeight(240);
		
		return panneauOptionLateral;
	}
	
	@SuppressWarnings("unchecked")
	private VBox panneauGestionAdherent() {
		VBox panneauListePersonne =new VBox(10);
		panneauListePersonne.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),BorderWidths.DEFAULT)));
		
		Label lblTitrelistePersonne = new Label("Adh�rents");
		lblTitrelistePersonne.setFont(Font.font("arial",FontWeight.BOLD ,16));
		
		tableAdherent = new TableView<Personne>();
		
		TableColumn<Personne, String> colonneNoPersonne = new TableColumn<Personne, String>("Identifiant");
		TableColumn<Personne, String> colonnePrenom = new TableColumn<Personne, String>("Pr�nom");
		TableColumn<Personne, String> colonneNom = new TableColumn<Personne, String>("Nom");
		TableColumn<Personne, String> colonneAdresse = new TableColumn<Personne, String>("Adresse");
		TableColumn<Personne, String> colonneTelephone = new TableColumn<Personne, String>("Num�ro t�l�phone");
		
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
		
		donneeAdherent = FXCollections.observableList(ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent));
		
		tableAdherent.getColumns().addAll(colonneNoPersonne,colonnePrenom,colonneNom,colonneAdresse,colonneTelephone);
		tableAdherent.setItems(ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent));
		tableAdherent.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),BorderWidths.DEFAULT)));
		
		panneauListePersonne.getChildren().addAll(lblTitrelistePersonne,tableAdherent);
		panneauListePersonne.setAlignment(Pos.CENTER);
		panneauListePersonne.setPadding(new Insets(15));
		
		VBox.setMargin(lblTitrelistePersonne, new Insets(0,0,10,0));
		
		return panneauListePersonne;
	}

	//Les panneau commun au pr�poser et a l'adherent
	private Pair<GridPane, VBox> panneauCommunPreAdh(TableView<Document>[] lstTable) {
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
		RadioButton rbMotCle = new RadioButton("Mot cl�");
		Button btnRecherche = new Button("Recherche");
		//Button btnReinitialiseListe = new Button("R�initialiser liste document");
		
		groupeRecherche.add(lblRecherche, 0, 0,2,1);
		groupeRecherche.add(tbRecherche, 0, 1);
		groupeRecherche.add(rbAuteur, 1, 1);
		groupeRecherche.add(rbMotCle, 1, 2);
		groupeRecherche.add(btnRecherche, 0, 2);
		
		btnRecherche.setOnAction(btn-> GestionDocuments.rechercherDocument(tbRecherche.getText(), rbMotCle.isSelected(), lstTable));
		//btnReinitialiseListe.setOnAction(btn -> GestionInterface.rechargeDonneeDoc(donneeDoc, donneeLiv, donneePer, donneeDvd));
		
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
	
	@SuppressWarnings("unchecked")
	private Pair<VBox, GridPane> panneauAdministrateur() {
		//tableau pr�poser
		VBox panneauListePersonne =new VBox(10);
		panneauListePersonne.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),BorderWidths.DEFAULT)));
		
		Label lblTitrelistePersonne = new Label("Pr�pos�");
		lblTitrelistePersonne.setFont(Font.font("arial",FontWeight.BOLD ,16));
		
		TableView<Personne> tablePrepose = new TableView<Personne>();
		
		TableColumn<Personne, String> colonneNoPersonne = new TableColumn<Personne, String>("Identifiant");
		TableColumn<Personne, String> colonnePrenom = new TableColumn<Personne, String>("Pr�nom");
		TableColumn<Personne, String> colonneNom = new TableColumn<Personne, String>("Nom");
		TableColumn<Personne, String> colonneAdresse = new TableColumn<Personne, String>("Adresse");
		TableColumn<Personne, String> colonneTelephone = new TableColumn<Personne, String>("Num�ro t�l�phone");
		TableColumn<Personne, String> colonneMotdePasse = new TableColumn<Personne, String>("Mot de passe");
		
		colonneNoPersonne.setPrefWidth(100);
		colonnePrenom.setPrefWidth(100);
		colonneNom.setPrefWidth(100);
		colonneAdresse.setPrefWidth(120);
		colonneTelephone.setPrefWidth(120);
		colonneMotdePasse.setPrefWidth(120);
		
		colonneNoPersonne.setCellValueFactory(new PropertyValueFactory<>("strNoPersonne"));
		colonnePrenom.setCellValueFactory(new PropertyValueFactory<>("strPrenom"));
		colonneNom.setCellValueFactory(new PropertyValueFactory<>("strNom"));
		colonneAdresse.setCellValueFactory(new PropertyValueFactory<>("strAdresse"));
		colonneTelephone.setCellValueFactory(new PropertyValueFactory<>("strNoTelephone"));
		colonneMotdePasse.setCellValueFactory(new PropertyValueFactory<>("strMotPasse"));
		
		tablePrepose.getColumns().addAll(colonneNoPersonne,colonneMotdePasse,colonnePrenom,colonneNom,colonneAdresse,colonneTelephone);
		tablePrepose.setItems(ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Prepose));
		tablePrepose.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),BorderWidths.DEFAULT)));
		
		panneauListePersonne.getChildren().addAll(lblTitrelistePersonne,tablePrepose);
		panneauListePersonne.setAlignment(Pos.CENTER);
		panneauListePersonne.setPadding(new Insets(15));
		
		//Option
		Label lblTitreGestionAdmin = new Label("Gestion Pr�pos�");
		Button btnAjouterPrepose = new Button("Ajouter");
		Button btnModifierPrepose = new Button("Modifier");
		Button btnSupprimerPrepose = new Button("Supprimer");
		
		Stage stageSecondaire = new Stage();
		stageSecondaire.initModality(Modality.APPLICATION_MODAL);
		stageSecondaire.sizeToScene();
		
		final InterfaceNouvelUtilisateur interfaceAjouterUtilisateur= new InterfaceNouvelUtilisateur(utilisateur,stageSecondaire);
		final InterfaceNouvelUtilisateur intefaceModifUtilisateur= new InterfaceNouvelUtilisateur(null,stageSecondaire);
		
		btnAjouterPrepose.setOnAction(e -> {stageSecondaire.setScene(interfaceAjouterUtilisateur.getScene());stageSecondaire.showAndWait();});
		/*
		btnModifirerAdherent.setOnAction(e->{intefaceModifUtilisateur.modifierAdherent((Adherent) tableAdherent.getSelectionModel().getSelectedItem());
		secondaryStage.setScene(intefaceModifUtilisateur.getScene());*/
		
		//btnModifierPrepose.setOnAction(e -> intefaceModifUtilisateur.mo);
		
		btnSupprimerPrepose.setOnAction(a -> {GestionPersonnes.supprimerPersonne((Personne) tablePrepose.getSelectionModel().getSelectedItem());});
		
		lblTitreGestionAdmin.setFont(Font.font("arial",FontWeight.BOLD ,15));
		
		GridPane panneauGestionDesPrepose = new GridPane();
		panneauGestionDesPrepose.setHgap(15);
		panneauGestionDesPrepose.setVgap(10);
		panneauGestionDesPrepose.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),BorderWidths.DEFAULT)));
		panneauGestionDesPrepose.setPadding(new Insets(10));
		panneauGestionDesPrepose.setMaxSize(250, 75);
		panneauGestionDesPrepose.add(lblTitreGestionAdmin, 0, 0,3,1);
		panneauGestionDesPrepose.add(btnAjouterPrepose, 0, 1);
		panneauGestionDesPrepose.add(btnModifierPrepose, 1, 1);
		panneauGestionDesPrepose.add(btnSupprimerPrepose, 2, 1);
		
		GridPane.setHalignment(lblTitreGestionAdmin, HPos.CENTER);
		
		
		return  new Pair<VBox, GridPane>(panneauListePersonne, panneauGestionDesPrepose);
		
	}
	
}
