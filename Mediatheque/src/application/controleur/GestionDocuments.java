package application.controleur;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import application.modele.DVD;
import application.modele.Document;
import application.modele.ListeDocuments;
import application.modele.ListePersonnes;
import application.modele.Livre;
import application.modele.Periodique;
import application.modele.TypeDocument;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;

public class GestionDocuments {
	private final static String[] tabMotCleExclu = {"les","une","des","aux","cet","cette","ces","mon","ton","son","mes","tes","ses","notre",
			"votre","leur","nos","vos","quel","quelle","quels","quelles","aucun","aucune","aucuns","aucunes","maint",
			"mainte","maints","leur","leurs","mais","donc","car","nous","vous","ils","par","avec","sous","dans","pour",
			"sans","est","que","qui","quand", "eux", "tous", "tout","toute", "toutes","ceux", "celle", "et", "sur", "ont"};
	
	///
	///methode a appeler quand on clic sur btnAjouterDocument ou equivalent
	///
	/*public void ouvrirFenetreAjouterDocument(){
		
		Stage secondaryStage = new Stage();
		InterfaceAjouterDocument interfaceAjouterDoc = new InterfaceAjouterDocument();
		secondaryStage.setScene(interfaceAjouterDoc.getScene());
		secondaryStage.showAndWait();
	}*/
	
	public static void supprimerDocuments(Document document){
		try {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation ");
		alert.setHeaderText("Supression d'un document");
		alert.setContentText("Voulez-vous vraiment Suprimer le "+document.getTypeDocument().getStrNomType()+": "+document.getStrTitre());

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			ListeDocuments.getInstance().mapDocument.get(document.getTypeDocument()).removeIf(d->document.equals(d));
		} else {}
		}catch(NullPointerException e) {
			Alert alertErreur = new Alert(AlertType.WARNING,"vous devez selectionner un document");
			alertErreur.showAndWait();
		}
	
	}
	
	public static void rechercherDocument(String strMotRecherche,boolean booRechercheMotClée, TableView<Document>[] lstTable, TabPane tabPane){
		//Si la recherche n'est pas vide ou a plusieur mot
		if(!strMotRecherche.isEmpty() && strMotRecherche.split(" ").length == 1) {
			if (booRechercheMotClée) {								
				FilteredList<Document> filtreDoc = new FilteredList<>(
						ListeDocuments.getInstance().mapDocument.values().stream().flatMap(List::stream).collect(Collectors.toCollection(FXCollections::observableArrayList)),
						docu -> docu.getLstMots().contains(strMotRecherche.toLowerCase()));
				SortedList<Document> triDonneMotCle = new SortedList<>(filtreDoc);
				
				triDonneMotCle.comparatorProperty().bind(lstTable[0].comparatorProperty());
				lstTable[0].setItems(triDonneMotCle);
				
				tabPane.getSelectionModel().select(0);
			}
			//Recherche par auteur
			else {
				FilteredList<Document> filtreTableLivre = new FilteredList<>(
						ListeDocuments.getInstance().mapDocument.get(TypeDocument.Livre), docu -> ((Livre)docu).getStrAuteur().toLowerCase().contains(strMotRecherche.toLowerCase()));
				
				SortedList<Document> triDonneAuteur = new SortedList<>(filtreTableLivre);
				
				triDonneAuteur.comparatorProperty().bind(lstTable[1].comparatorProperty());
				lstTable[1].setItems(triDonneAuteur);
				
				tabPane.getSelectionModel().select(1);
			}
		}
		else {
			Alert alRecherche = new Alert(AlertType.WARNING, "Vous devez inscrire un mot et seulement un pour effectuer une recherche!", ButtonType.OK);
			alRecherche.showAndWait();
		}
	}
	
	
	//Trouve les mots clé pour le document
	public static void motCleAjout(Document doc) {
		
		if (doc.getTypeDocument().equals(TypeDocument.Livre)) {
			//Auteur
			String[] tabNom = ((Livre) doc).getStrAuteur().split(" ");
			Arrays.asList(tabNom).stream().filter(h -> !Arrays.asList(tabMotCleExclu).contains(h.trim().toLowerCase())).filter(q -> !q.trim().equals("")).forEach(f -> doc.setLstMots(f.trim().toLowerCase()));
		}
		else if (doc.getTypeDocument().equals(TypeDocument.Dvd)) {
			//Réalisateur
			String[] tabNom = ((DVD) doc).getStrResalisateur().split(" ");
			Arrays.asList(tabNom).stream().filter(h -> !Arrays.asList(tabMotCleExclu).contains(h.trim().toLowerCase())).filter(q -> !q.trim().equals("")).forEach(f -> doc.setLstMots(f.trim().toLowerCase()));
		}
		
		//Titre
		String[] tabTitre = doc.getStrTitre().replace('\'', ' ').replace(':', ' ').split(" ");
		Arrays.asList(tabTitre).stream().filter(f -> f.length() > 2).filter(h -> !Arrays.asList(tabMotCleExclu).contains(h.trim().toLowerCase())).forEach(g -> doc.setLstMots(g.trim().toLowerCase()));
		
		doc.getLstMots().forEach(System.out::println);
	}
	
	
	
	public static boolean ajouterLivre(String strTitre,LocalDate dateParution,String strAuteur) {
		boolean booAjoutReussi=false;
		Alert alerteDocument=validerDocuments(dateParution,strTitre);
		
		if (strAuteur.equals(null)||strAuteur.trim().equals("")) {
			alerteDocument = new Alert(AlertType.WARNING,"Veuillez spécifier un auteur", ButtonType.OK);
		}
		if(alerteDocument==null) {
			DVD.ouRenduNo();
			alerteDocument= new Alert(AlertType.CONFIRMATION,"Le document \" "+strTitre+" \" à été ajouté avec succèss", ButtonType.OK);
			Livre livre =new Livre(strTitre,dateParution,strAuteur);
			ListeDocuments.getInstance().mapDocument.get(TypeDocument.Livre).add(livre);
			booAjoutReussi=true;
			
			motCleAjout(livre);
			
		}
		alerteDocument.showAndWait();
		
		return booAjoutReussi;
	}
	
	public static boolean ajouterDVD(String strTitre,LocalDate dateParution,int intNbDisques,String strRealisateur) {
		boolean booAjoutReussi=false;
		Alert alerteDocument=validerDocuments(dateParution,strTitre);
		
		if (strRealisateur.equals(null)||strRealisateur.trim().equals("")) {
			alerteDocument = new Alert(AlertType.WARNING,"Veuillez spécifier un réalisateur", ButtonType.OK);
		}
		if(alerteDocument==null) {
			DVD.ouRenduNo();
			alerteDocument= new Alert(AlertType.CONFIRMATION,"Le document \" "+strTitre+" \" à été ajouté avec succèss", ButtonType.OK);
			DVD dvd =new DVD(strTitre,dateParution,(short)intNbDisques,strRealisateur);
			ListeDocuments.getInstance().mapDocument.get(TypeDocument.Dvd).add(dvd);
			booAjoutReussi=true;
			
			motCleAjout(dvd);
			
		}
		alerteDocument.showAndWait();
		
		return booAjoutReussi;
	}
	
	@SuppressWarnings("unused")
	public static boolean ajouterPeriodique(String strTitre,LocalDate dateParution,String strNoVolume,String strNoPeriodique) {
		boolean booAjoutReussi=false;
		Alert alerteDocument=validerDocuments(dateParution,strTitre);
		
		int intNoPeriodique= Integer.MIN_VALUE;
		int intNoVolume= Integer.MIN_VALUE;
		
		if (strNoVolume.equals(null)||strNoVolume.trim().equals("")||strNoPeriodique.equals(null)||strNoPeriodique.trim().equals("")) {
			alerteDocument = new Alert(AlertType.WARNING,"Veuillez spécifier un numéro de periodique et un numéro de volume", ButtonType.OK);
		}
		else {
			try {
				intNoVolume=Integer.parseInt(strNoVolume);
				intNoPeriodique= Integer.parseInt(strNoPeriodique);
			}catch(Exception e) {
				alerteDocument = new Alert(AlertType.WARNING,"Veuillez spécifier un numéro de periodique et un numéro de volume valides", ButtonType.OK);
			}
			
		}
		if(alerteDocument==null) {
			Periodique.ouRenduNo();
			alerteDocument= new Alert(AlertType.CONFIRMATION,"Le document \" "+strTitre+" \" à été ajouté avec succèss", ButtonType.OK);
			Periodique periodique =new Periodique(strTitre,dateParution,intNoVolume,intNoPeriodique);
			ListeDocuments.getInstance().mapDocument.get(TypeDocument.Periodique).add(periodique);
			booAjoutReussi=true;
			
			motCleAjout(periodique);
			
		}
		alerteDocument.showAndWait();
		
		return booAjoutReussi;
	}
	
	private static Alert validerDocuments(LocalDate dateParution,String strTitre) {
		Alert alerteDocument= null;
		if (dateParution==null) {
			alerteDocument = new Alert(AlertType.WARNING,"veuillez entrer une date", ButtonType.OK);
		}
		else if (LocalDate.now().isBefore(dateParution)){
			alerteDocument = new Alert(AlertType.WARNING,"La date de parution spécifiée est après la date d'aujourd'hui", ButtonType.OK);
		}
		else if (strTitre.equals(null)||strTitre.trim().equals("")) {
			alerteDocument = new Alert(AlertType.WARNING,"Veuillez spécifier un titre", ButtonType.OK);
		}
		return alerteDocument;
	}
}
