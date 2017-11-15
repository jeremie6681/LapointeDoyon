package application.controleur;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import application.modele.DVD;
import application.modele.Document;
import application.modele.Etat;
import application.modele.ListeDocuments;
import application.modele.Livre;
import application.modele.Periodique;
import application.modele.TypeDocument;
import application.vue.InterfaceAjouterDocument;
import application.vue.InterfacePrincipale;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sun.security.krb5.internal.tools.Ktab;

public class GestionDocuments {
	String[] tabMotCleExclu = {"les","une","des","aux","cet","cette","ces","mon","ton","son","mes","tes","ses","notre",
			"votre","leur","nos","vos","quel","quelle","quels","quelles","aucun","aucune","aucuns","aucunes","maint",
			"mainte","maints","leur","leurs","mais","donc","car","nous","vous","ils","par","avec","sous","dans","pour",
			"sans","est","que","qui","quand"};
	
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
		/*TypeDocument typeSupprimer = null;
		
		//Détermine le type du document
		for(TypeDocument type : TypeDocument.values()) {
			if(type.getStrIndicateurType().equals(strCodeDocument.substring(0, 3)))
				typeSupprimer = type;
		}
		
		ListeDocuments.getInstance().mapDocument.get(typeSupprimer).removeIf(docu -> docu.getStrCodeDocument().equals(strCodeDocument));
		
		//*////////////////////////////////////////////////////////////////////////////////Peut etre remettre a jour les onglets
		
		//a ajouter fenetre voulez vous vraiment
		
		ListeDocuments.getInstance().mapDocument.get(document.getTypeDocument()).removeIf(d->document.equals(d));
		//ListeDocuments.getInstance().mapDocument.get(TypeDocument.Livre).forEach(e-> System.out.println(e.getStrTitre())); //just pour verifier la supression
		
	}
	
	public static void rechercherDocument(String strMotRecherche,boolean booRechercheMotClée, TableView<Document>[] lstTable){
		//Si la recherche n'est pas vide
		if(!strMotRecherche.isEmpty()) {
			if (booRechercheMotClée) {
				
			}
			//Recherche par auteur
			else {
				lstTable[1].getItems().removeIf(docu-> !((Livre)docu).getStrAuteur().toLowerCase().contains(strMotRecherche.toLowerCase()));
				
				
			}
		}	
	}
	
	//Parcours la liste de documents pour ajouter des mots clé
	public void motCleListe() {
		
		//Parcours les 3 types de documents
		for(int x = 0; x<3;x++) {
			List<Document> typeDoc = ListeDocuments.getInstance().mapDocument.get(TypeDocument.values()[x]);
			
			//Vérifie que les listes ne sont pas null
			if(typeDoc.size() > 0) {
				
				//parcours la liste des documents du même type
				for(int y=0; y < typeDoc.size();y++) {
					// a completer ... Dépends si liste mot clé banie fournie
					
					
				}
			}
		}
	}
	
	private static void motCleAjout() {
		
	}
	public static boolean ajouterLivre(String strTitre,LocalDate dateParution,String strAuteur) {
		boolean booAjoutReussi=false;
		Alert alerteDocument=validerDocuments(dateParution,strTitre);
		
		if (strAuteur.equals(null)||strAuteur.trim().equals("")) {
			alerteDocument = new Alert(AlertType.WARNING,"Veuillez spécifier un auteur", ButtonType.OK);
		}
		if(alerteDocument==null) {
			alerteDocument= new Alert(AlertType.CONFIRMATION,"Le document \" "+strTitre+" \"à été ajouté avec succèss", ButtonType.OK);
			Livre livre =new Livre(strTitre,dateParution,strAuteur);
			ListeDocuments.getInstance().mapDocument.get(TypeDocument.Livre).add(livre);
			booAjoutReussi=true;
		}
		alerteDocument.showAndWait();
		motCleAjout();
		return booAjoutReussi;
	}
	public static boolean ajouterDVD(String strTitre,LocalDate dateParution,int intNbDisques,String strRealisateur) {
		boolean booAjoutReussi=false;
		Alert alerteDocument=validerDocuments(dateParution,strTitre);
		
		if (strRealisateur.equals(null)||strRealisateur.trim().equals("")) {
			alerteDocument = new Alert(AlertType.WARNING,"Veuillez spécifier un réalisateur", ButtonType.OK);
		}
		if(alerteDocument==null) {
			alerteDocument= new Alert(AlertType.CONFIRMATION,"Le document \" "+strTitre+" \"à été ajouté avec succèss", ButtonType.OK);
			DVD dvd =new DVD(strTitre,dateParution,(short)intNbDisques,strRealisateur);
			ListeDocuments.getInstance().mapDocument.get(TypeDocument.Dvd).add(dvd);
			booAjoutReussi=true;
		}
		alerteDocument.showAndWait();
		
		motCleAjout();
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
			alerteDocument= new Alert(AlertType.CONFIRMATION,"Le document \" "+strTitre+" \"à été ajouté avec succèss", ButtonType.OK);
			Periodique periodique =new Periodique(strTitre,dateParution,intNoVolume,intNoPeriodique);
			ListeDocuments.getInstance().mapDocument.get(TypeDocument.Periodique).add(periodique);
			booAjoutReussi=true;
		}
		alerteDocument.showAndWait();
		
		motCleAjout();
		return booAjoutReussi;
	}
	
	private static Alert validerDocuments(LocalDate dateParution,String strTitre) {
		Alert alerteDocument= null;
		if (LocalDate.now().isBefore(dateParution)){
			alerteDocument = new Alert(AlertType.WARNING,"La date de parution spécifiée est après la date d'aujourd'hui", ButtonType.OK);
		}
		else if (strTitre.equals(null)||strTitre.trim().equals("")) {
			alerteDocument = new Alert(AlertType.WARNING,"Veuillez spécifier un titre", ButtonType.OK);
		}
		return alerteDocument;
	} 
}
