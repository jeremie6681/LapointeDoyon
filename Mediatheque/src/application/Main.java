package application;

import java.util.List;
import java.util.stream.Collectors;
import application.modele.ListeDocuments;
import application.modele.ListePersonnes;
import application.modele.TypePersonne;
import application.vue.InterfaceTypeConnection;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			InterfaceTypeConnection ifLogin = new InterfaceTypeConnection(primaryStage);

			primaryStage.setScene(ifLogin.getScene());
			primaryStage.setResizable(false);
			primaryStage.sizeToScene();
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		// Inisialisation des listes de personnes et de documents
		ListeDocuments lstDocs = ListeDocuments.getInstance();
		ListePersonnes listePersonnes = ListePersonnes.getInstance();

		// lambda qui permet de s'assure de l'intégrité des références d'objet entre les
		// documents et les prets
		ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent).forEach(f -> f.getLstPrets()
				.forEach(g -> g.setDoc(ListeDocuments.getInstance().mapDocument.values().stream().flatMap(List::stream)
						.collect(Collectors.toCollection(FXCollections::observableArrayList)).stream()
						.filter(fil -> fil.getStrCodeDocument().equals(g.getDoc().getStrCodeDocument())).findFirst()
						.isPresent() == true
								? ListeDocuments.getInstance().mapDocument.values().stream().flatMap(List::stream)
										.collect(Collectors.toCollection(FXCollections::observableArrayList)).stream()
										.filter(fil -> fil.getStrCodeDocument().equals(g.getDoc().getStrCodeDocument()))
										.findFirst().get()
								: g.getDoc())));

		launch(args);
	}

}
