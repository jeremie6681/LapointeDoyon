package application.vue;

import java.time.LocalDate;

import application.modele.Adherent;
import application.modele.Personne;
import application.modele.Pret;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class InterfaceListePret {
	private Scene scene;
	private Stage stage;
	
	
	public InterfaceListePret(Stage stage, Adherent adherent) {
		this.stage = stage;
		
		VBox panneauListePret = new VBox(10);
		panneauListePret.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),BorderWidths.DEFAULT)));
		
		TableView<Pret> tablePret = new TableView<Pret>();
		
		TableColumn<Pret, String> colonneNoDocPret = new TableColumn<Pret, String>("Identifiant du document");
		TableColumn<Pret, LocalDate> colonneDatePret = new TableColumn<Pret, LocalDate>("Date du prêt");
		TableColumn<Pret, LocalDate> colonneDatePrevue = new TableColumn<Pret, LocalDate>("Date de retour prévue");
		
		colonneNoDocPret.setPrefWidth(150);
		colonneDatePret.setPrefWidth(150);
		colonneDatePrevue.setPrefWidth(150);
		
		colonneNoDocPret.setCellValueFactory(new PropertyValueFactory<>("strCodeDoc"));
		colonneDatePret.setCellValueFactory(new PropertyValueFactory<>("datePret"));
		colonneDatePrevue.setCellValueFactory(new PropertyValueFactory<>("dateRetourPrevue"));
		
		ObservableList<Pret> donneePret = FXCollections.observableArrayList(adherent.getLstPrets());
		
		tablePret.getColumns().addAll(colonneNoDocPret,colonneDatePret,colonneDatePrevue);
		tablePret.setItems(donneePret);
		tablePret.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,new CornerRadii(5),BorderWidths.DEFAULT)));
		
		
	}

}
