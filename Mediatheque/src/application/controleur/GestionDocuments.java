package application.controleur;

import java.util.List;

import application.modele.Document;
import application.modele.ListeDocuments;
import application.modele.Livre;
import application.modele.TypeDocument;
import javafx.scene.control.TableView;

public class GestionDocuments {
	public void ajouterDocument(){
		
		//Doit être appeler en moment donner
		motCleAjout();
	}
	
	public void supprimerDocuments(String strCodeDocument){
		TypeDocument typeSupprimer = null;
		
		//Détermine le type du document
		for(TypeDocument type : TypeDocument.values()) {
			if(type.getStrIndicateurType().equals(strCodeDocument.substring(0, 3)))
				typeSupprimer = type;
		}
		
		ListeDocuments.getInstance().mapDocument.get(typeSupprimer).removeIf(docu -> docu.getStrCodeDocument().equals(strCodeDocument));
		
		/////////////////////////////////////////////////////////////////////////////////Peut etre remettre a jour les onglets
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
	
	private void motCleAjout() {
		
	}
	
}
