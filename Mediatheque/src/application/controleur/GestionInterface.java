package application.controleur;

import java.util.List;
import java.util.stream.Collectors;

import application.modele.Document;
import application.modele.ListeDocuments;
import application.modele.ListePersonnes;
import application.modele.Personne;
import application.modele.TypeDocument;
import application.modele.TypePersonne;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;

public class GestionInterface {
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////Note penser a rajouter le vide zone de texte
	//Réinitialise les données des onglets avec les listes de documents
	
	public static void rechargeDonneeDoc(TableView<Document>[] lstTable) {
		lstTable[0].setItems(ListeDocuments.getInstance().mapDocument.values().stream()
				.flatMap(List::stream).collect(Collectors.toCollection(FXCollections::observableArrayList)));
		
	}
	
	public static void rechargeDonneLivre(TableView<Document>[] lstTable) {
		lstTable[1].setItems(ListeDocuments.getInstance().mapDocument.get(TypeDocument.Livre));
	}
}
