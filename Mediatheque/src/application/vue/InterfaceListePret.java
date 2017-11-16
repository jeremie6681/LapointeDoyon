package application.vue;

import java.time.LocalDate;

import application.modele.Pret;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class InterfaceListePret {
	private Scene scene;
	private Stage stage;
	
	
	public InterfaceListePret(Stage stage) {
		this.stage = stage;
		
		
		TableView<Pret> tablePret = new TableView<Pret>();
		
		TableColumn<Pret, Integer> colonneNoPret = new TableColumn<Pret, Integer>("Identifiant du prêt");
		TableColumn<Pret, String> colonneNoDocPret = new TableColumn<Pret, String>("Identifiant du document");
		TableColumn<Pret, LocalDate> colonneDatePret = new TableColumn<Pret, LocalDate>("Date du prêt");
		TableColumn<Pret, LocalDate> colonneDatePrevue = new TableColumn<Pret, LocalDate>("Date de retour prévue");
		
		colonneNoPret.setPrefWidth(150);
		colonneNoDocPret.setPrefWidth(150);
		colonneDatePret.setPrefWidth(150);
		colonneDatePrevue.setPrefWidth(150);
		
		colonneNoPret.setCellValueFactory(new PropertyValueFactory<>("intNoPret"));
		colonneNoDocPret.setCellValueFactory(new PropertyValueFactory<>("strCodeDoc"));
		colonneDatePret.setCellValueFactory(new PropertyValueFactory<>("datePret"));
		colonneDatePrevue.setCellValueFactory(new PropertyValueFactory<>("dateRetourPrevue"));
		
		//ObservableList<Pret> donneePret = ....
	}

}
