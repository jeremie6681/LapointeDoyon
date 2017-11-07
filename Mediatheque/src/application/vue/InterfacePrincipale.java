package application.vue;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import application.controleur.GestionDocuments;
import application.modele.Document;
import application.modele.Etat;
import application.modele.ListeDocuments;
import application.modele.Livre;
import application.modele.TypeDocument;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
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

public class InterfacePrincipale {
	private Scene scene;
	
	@SuppressWarnings("static-access")
	public InterfacePrincipale() {
		Group root =new Group();
		
		BorderPane panneau = new BorderPane();
		
		scene = new Scene(root);
		//scene.setFill(Color.rgb(24, 25, 28));
		
		Label lblTitre = new Label("Médiathèque");
		lblTitre.setFont(Font.font("arial", FontWeight.BOLD	, 35));
		lblTitre.setPadding(new Insets(20));
		
		//lblTitre.setTextFill(Color.WHITE);
		
		
		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		tabPane.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),BorderWidths.DEFAULT)));
		
		
		TableView<Document>[] lstTable = tableau();
		
		Tab ongletDoc = new Tab("Document");
		VBox panneauV = new VBox();
		panneauV.getChildren().add(lstTable[0]);
		ongletDoc.setContent(panneauV);
		tabPane.getTabs().add(ongletDoc);
		
		
		for(int i =0; i< 3;i++) {
			Tab ongletType = new Tab(TypeDocument.values()[i].getStrNomType());
			VBox panneauVboxType = new VBox();
			ongletType.setContent(panneauVboxType);
			
			panneauVboxType.getChildren().add(lstTable[i+1]);
			
			tabPane.getTabs().add(ongletType);
		}
		
		panneau.setPadding(new Insets(20,50,30,50));
		
		//Recherche
		HBox groupeRecherche = new HBox(10);
		groupeRecherche.setAlignment(Pos.CENTER);
		//AnchorPane groupeRecherche = new AnchorPane();
		//titled
		groupeRecherche.setPadding(new Insets(40,0,0,0));
		Label lblRecherche = new Label("Recherche");
		//groupeRecherche.setb
		
		//groupeRecherche.getChildren().add(lblRecherche);
		ToggleGroup tg = new ToggleGroup();
		TextField tbRecherche = new TextField();
		RadioButton rbAuteur = new RadioButton("Auteur");
		RadioButton rbMotCle = new RadioButton("Mot clé");
		Button btnRecherche = new Button("Recherche");
		Button btnReinitialiseListe = new Button("Réinitialiser liste document");
		
		btnRecherche.setOnAction(btn-> GestionDocuments.rechercherDocument(tbRecherche.getText(), rbMotCle.isSelected(), lstTable));
		
		groupeRecherche.setSpacing(10);
		
		groupeRecherche.getChildren().addAll(tbRecherche,rbAuteur,rbMotCle, btnRecherche, btnReinitialiseListe);
		
		rbAuteur.setToggleGroup(tg);
		rbMotCle.setToggleGroup(tg);
		
		rbAuteur.setSelected(true);
		
		tbRecherche.setPromptText("Recherche");
		tbRecherche.setMaxWidth(100);
		
		
		
		//panneau.prefWidthProperty().bind(sc);
		
		panneau.setTop(lblTitre);
		panneau.setCenter(tabPane);
		
		panneau.setBottom(groupeRecherche);
		
		panneau.setAlignment(lblTitre, Pos.CENTER);
		root.getChildren().add(panneau);
		
		
		
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
		
		colonneAuteur.setPrefWidth(100);
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
		
		
		ObservableList<Document> donneeDoc = FXCollections.observableArrayList(
				ListeDocuments.getInstance().mapDocument.values().stream().flatMap(List::stream).collect(Collectors.toList()));
		ObservableList<Document> donneeLiv = FXCollections.observableArrayList(ListeDocuments.getInstance().mapDocument.get(TypeDocument.Livre));
		ObservableList<Document> donneePer = FXCollections.observableArrayList(ListeDocuments.getInstance().mapDocument.get(TypeDocument.Periodique));
		ObservableList<Document> donneeDvd = FXCollections.observableArrayList(ListeDocuments.getInstance().mapDocument.get(TypeDocument.Dvd));
		
		
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
		TableColumn<Document, Date> colonneDate = new TableColumn<Document, Date>("Date parution");
		TableColumn<Document, Etat> colonneEtat = new TableColumn<Document, Etat>("Disponibilité");
		
		colonneIdentifiant.setPrefWidth(80);
		colonneTitre.setPrefWidth(275);
		colonneDate.setPrefWidth(150);
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
}
