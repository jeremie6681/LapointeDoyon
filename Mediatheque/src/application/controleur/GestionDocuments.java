package application.controleur;

import java.util.List;

import application.modele.Document;
import application.modele.ListeDocuments;
import application.modele.Livre;
import application.modele.TypeDocument;
import application.vue.InterfaceAjouterDocument;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class GestionDocuments {
	String[] tabMotCleExclu = {"les","une","des","aux","cet","cette","ces","mon","ton","son","mes","tes","ses","notre",
			"votre","leur","nos","vos","quel","quelle","quels","quelles","aucun","aucune","aucuns","aucunes","maint",
			"mainte","maints","leur","leurs","mais","doc","car","nous","vous","ils","par","avec","sous","dans","pour",
			"en","sans","o�","ai","est","que","qui","quand"};
	
	///
	///methode a appeler quand on clic sur btnAjouterDocument ou equivalent
	///
	public void ouvrirFenetreAjouterDocument(){
		
		Stage secondaryStage = new Stage();
		InterfaceAjouterDocument interfaceAjouterDoc = new InterfaceAjouterDocument();
		secondaryStage.setScene(interfaceAjouterDoc.getScene());
		secondaryStage.showAndWait();
	}
	
	public void supprimerDocuments(String strCodeDocument){
		TypeDocument typeSupprimer = null;
		
		//D�termine le type du document
		for(TypeDocument type : TypeDocument.values()) {
			if(type.getStrIndicateurType().equals(strCodeDocument.substring(0, 3)))
				typeSupprimer = type;
		}
		
		ListeDocuments.getInstance().mapDocument.get(typeSupprimer).removeIf(docu -> docu.getStrCodeDocument().equals(strCodeDocument));
		
		/////////////////////////////////////////////////////////////////////////////////Peut etre remettre a jour les onglets
	}
	
	public static void rechercherDocument(String strMotRecherche,boolean booRechercheMotCl�e, TableView<Document>[] lstTable){
		//Si la recherche n'est pas vide
		if(!strMotRecherche.isEmpty()) {
			if (booRechercheMotCl�e) {
				
			}
			//Recherche par auteur
			else {
				lstTable[1].getItems().removeIf(docu-> !((Livre)docu).getStrAuteur().toLowerCase().contains(strMotRecherche.toLowerCase()));
				
				
			}
		}	
	}
	
	//Parcours la liste de documents pour ajouter des mots cl�
	public void motCleListe() {
		
		//Parcours les 3 types de documents
		for(int x = 0; x<3;x++) {
			List<Document> typeDoc = ListeDocuments.getInstance().mapDocument.get(TypeDocument.values()[x]);
			
			//V�rifie que les listes ne sont pas null
			if(typeDoc.size() > 0) {
				
				//parcours la liste des documents du m�me type
				for(int y=0; y < typeDoc.size();y++) {
					// a completer ... D�pends si liste mot cl� banie fournie
					
					
				}
			}
		}
	}
	
	private static void motCleAjout() {
		
	}
	
	///
	///methode appell� par le event handler du bouton que l'on pesse pou ajouter le doc 
	///appel la methode qui ajoute les mots cl�s
	///
	public static void ajouterDocument() {
		
		
		
		motCleAjout();
	}
	
}
