package application.modele;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;

public class ListeDocuments {
	private final String strNomFichierDVD = "Donnee/DVD.txt";
	private final String strNomFichierLivre = "Donnee/Livres.txt";
	private final String strNomFichierPeriodique = "Donnee/Periodiques.txt";
	
	//private final String strNomFichierDVDSerialiser = "DVD.ser";
	
	public ArrayList<Document> lstDocument = new ArrayList<>();
	
	public ListeDocuments() {
		//si pas de fichier sérialiser touver
		
		LectureFichierOriginal();
	}
	
	private void LectureFichierOriginal() {
		//Lecture fichier DVD
		BufferedReader brFichier = null;
		try {
			//brFichier = new BufferedReader(new FileReader(strNomFichierDVD));
			brFichier = new BufferedReader(new FileReader(strNomFichierLivre));
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		String strLigne;
		
		try {
			while ((strLigne = brFichier.readLine()) != null) {
				//S'il y a des mots clé pour le document
				if(strLigne.matches(";")) {
					
				}
				//Pas de mot clé
				else {
					
				}
				
				
				
				
				
				
				
				System.out.println(strLigne);
				String[] tabLigne = strLigne.split(";");
				String[] tabInfoDoc = tabLigne[0].split(",");
				String[] tabMotCle = tabLigne[1].split(",");
				
				DateFormat dfDocument = new SimpleDateFormat("dd-MM-yyyy");
				Date dateDocument = dfDocument.parse(tabInfoDoc[2]);
				
				//DVD objDVD = new DVD(tabInfoDoc[0], tabInfoDoc[1], dateDocument , Etat.DISPONIBLE, Short.parseShort(tabInfoDoc[3]), tabInfoDoc[4]);
				//lstDocument.add(objDVD);
				
				Livre objLivre = new Livre(tabInfoDoc[0], tabInfoDoc[1], dateDocument, Etat.DISPONIBLE, tabInfoDoc[3]);
				lstDocument.add(objLivre);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//lstDocument.forEach(System.out :: println);
	}
	
	private void Serialisation() {
		
	}
	
	private void Deserialisation() {
		
	}
}
