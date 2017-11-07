package application.controleur;

import java.util.List;

import application.modele.Document;
import application.modele.ListeDocuments;
import application.modele.Livre;
import application.modele.TypeDocument;
import javafx.scene.control.TableView;

public class GestionDocuments {
	public void ajouterDocument(){
		
		//Doit �tre appeler en moment donner
		motCleAjout();
	}
	public void supprimerDocuments(){
		
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
	
	private void motCleAjout() {
		
	}
	
}
