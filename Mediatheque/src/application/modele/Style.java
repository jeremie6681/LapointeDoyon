package application.modele;

import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

//Contient certains apparence commune (un peu comme un CSS)
public class Style {
	public static final Border styleBordure = new Border(
			new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT));
	
	public static final Font  fntBoutons = Font.font("Arial",FontWeight.BOLD,FontPosture.REGULAR, 20);
	public static final Image imgAmende = new Image("amande.jpg");
	
}