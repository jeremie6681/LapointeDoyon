package application.controleur;

import java.util.List;
import java.util.stream.Collectors;

import application.modele.Document;
import application.modele.ListeDocuments;
import application.modele.TypeDocument;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TabPane;

public class GestionInterface {
	
	public static void ecouteurDonneeOnglet(ObservableList<Document> donneeDoc,ObservableList<Document> donneeLiv , TabPane tabPane) {
		
		donneeDoc.addListener(new ListChangeListener<Document>() {

			@Override
			public void onChanged(Change<? extends Document> c) {
				// TODO Auto-generated method stub
				while(c.next()) {
					if (c.wasRemoved()) {
						tabPane.getSelectionModel().select(0);
					}
				}
			}
			
		});
		
		donneeLiv.addListener(new ListChangeListener<Document>() {

			@Override
			public void onChanged(Change<? extends Document> arg0) {
				// TODO Auto-generated method stub
				
				while(arg0.next()) {
					if(arg0.wasRemoved()) {
						tabPane.getSelectionModel().select(1);
					}
				}
			}
			
		});
		
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////Note penser a rajouter le vide zone de texte
	//R�initialise les donn�es des onglets avec les listes de documents
	public static void rechargeDonnee(ObservableList<Document> donneeDoc,ObservableList<Document> donneeLiv,
			ObservableList<Document> donneePer,ObservableList<Document> donneeDvd) {
		
		donneeDvd.clear();
		donneeDvd.addAll(FXCollections.observableArrayList(ListeDocuments.getInstance().mapDocument.get(TypeDocument.Dvd)));
		
		donneePer.clear();
		donneePer.addAll(FXCollections.observableArrayList(ListeDocuments.getInstance().mapDocument.get(TypeDocument.Periodique)));
		
		donneeLiv.clear();
		donneeLiv.addAll(FXCollections.observableArrayList(ListeDocuments.getInstance().mapDocument.get(TypeDocument.Livre)));
		
		donneeDoc.clear();
		donneeDoc.addAll(FXCollections.observableArrayList(
				ListeDocuments.getInstance().mapDocument.values().stream().flatMap(List::stream).collect(Collectors.toList())));
	}
}
