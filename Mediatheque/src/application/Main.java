package application;

import java.util.List;
import java.util.stream.Collectors;

import application.modele.Adherent;
import application.modele.Document;
import application.modele.ListeDocuments;
import application.modele.ListePersonnes;
import application.modele.Personne;
import application.modele.Prepose;
import application.modele.TypePersonne;
import application.vue.InterfaceAjouterDocument;
import application.vue.InterfaceLoginPrepose;
import application.vue.InterfacePrincipale;
import application.vue.InterfaceTypeConnection;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			InterfaceTypeConnection ifLogin = new InterfaceTypeConnection(primaryStage);
			//InterfaceLoginPrepose ifLogin = new InterfaceLoginPrepose();
			//InterfaceLoginAdherent ifLogin = new InterfaceLoginAdherent();
			//InterfaceNouvelUtilisateur ifLogin= new InterfaceNouvelUtilisateur();
			//InterfaceAjouterDocument ifLogin = new InterfaceAjouterDocument();
		   // Adherent pTest = new Adherent("strnom","strPrenom","stradr","strnotel","adh08"); 
		   // Prepose p = new Prepose("", "", "", "", "1","pre100");
		//	InterfacePrincipale ifLogin = new InterfacePrincipale(primaryStage,TypePersonne.Prepose,pTest);

			//InterfacePrincipale ifLogin = new InterfacePrincipale(primaryStage,TypePersonne.Adherent,pTest);


			//InterfaceAjouterDocument ifLogin = new InterfaceAjouterDocument();
			primaryStage.setScene(ifLogin.getScene());
			
			primaryStage.setResizable(false);
			primaryStage.sizeToScene();
			primaryStage.show();
			
		
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
	
		ListeDocuments lstDocs = ListeDocuments.getInstance();
		
		ListePersonnes listePersonnes = ListePersonnes.getInstance();
		/*
		boolean boo = ListeDocuments.getInstance().mapDocument.values().stream().flatMap(List::stream)
				.collect(Collectors.toCollection(FXCollections::observableArrayList)).stream()
			    .map(Document::getStrCodeDocument)
			    .anyMatch(idToCheck::equals);*/
		
		//lambda de jesus qui permet de s'assure de l'intégrité des références d'objet entre les documents et les prets
		ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent).forEach( f -> f.getLstPrets().forEach(g -> g.setDoc(ListeDocuments.getInstance().mapDocument.values().stream().flatMap(List::stream)
				.collect(Collectors.toCollection(FXCollections::observableArrayList)).stream().filter(fil -> fil.getStrCodeDocument().equals(g.getDoc().getStrCodeDocument())).findFirst().isPresent() == true ? 
						ListeDocuments.getInstance().mapDocument.values().stream().flatMap(List::stream).collect(Collectors.toCollection(FXCollections::observableArrayList))
						.stream().filter(fil -> fil.getStrCodeDocument().equals(g.getDoc().getStrCodeDocument())).findFirst().get():g.getDoc())));
		
		
		
		
		/*g.setDoc(ListeDocuments.getInstance().mapDocument.values().stream().flatMap(List::stream)
					.collect(Collectors.toCollection(FXCollections::observableArrayList)).stream().filter(fil -> fil.getStrCodeDocument().equals(g.getDoc())).findFirst())));*/
		
		
		/*
		ListeDocuments.getInstance().mapDocument.values().stream().flatMap(List::stream)
		.collect(Collectors.toCollection(FXCollections::observableArrayList)).stream().filter(fil -> fil.getStrCodeDocument().equals(arg0)).findFirst();*/

		launch(args);
	}
	
}
