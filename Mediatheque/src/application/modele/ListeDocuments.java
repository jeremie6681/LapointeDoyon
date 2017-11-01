package application.modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public final class ListeDocuments {
	private final String strNomFichierDVD = "Donnee/DVD.txt";
	private final String strNomFichierLivre = "Donnee/Livres.txt";
	private final String strNomFichierPeriodique = "Donnee/Periodiques.txt";
	private final String strNomFichierSerialiser = "Donnee/Serialiser.ser";
	
	public ArrayList<Document> lstDocument = new ArrayList<>();
	
	private static ListeDocuments instanceDoc = new ListeDocuments();
	
	public ListeDocuments() {
		//si pas de fichier sérialiser touver
		
		File fichierSerialiser = new File(strNomFichierSerialiser);
		
		//S'il y a eu déjà une sérialisation
		if(fichierSerialiser.exists()) {
			Deserialisation();
		}
		else {
			LectureFichierOriginal();
		}	
	}
	
	private void LectureFichierOriginal() {
		//Lecture fichier (0 = dvd, 1 = livre, 2 = periodique)
		for(int intTypeDocument = 0; intTypeDocument < 3;intTypeDocument++) {
			BufferedReader brFichier = null;
			try {
				if(intTypeDocument == 0) {
					brFichier = new BufferedReader(new FileReader(strNomFichierDVD));
				}
				else if (intTypeDocument == 1) {
					brFichier = new BufferedReader(new FileReader(strNomFichierLivre));
				}
				else {
					brFichier = new BufferedReader(new FileReader(strNomFichierPeriodique));
				}
				
			} catch (FileNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			String strLigne;
			
			try {
				while ((strLigne = brFichier.readLine()) != null) {
					String[] tabLigne = strLigne.split(",");
					
					DateFormat dfDocument = new SimpleDateFormat("dd-MM-yyyy");
					Date dateDocument = dfDocument.parse(tabLigne[2]);
					
					//Crée le document et l'ajoute dans la liste selon son type
					if(intTypeDocument == 0) {
						DVD objDVD = new DVD(tabLigne[0], tabLigne[1], dateDocument , Etat.DISPONIBLE, Short.parseShort(tabLigne[3].trim()), tabLigne[4]);
						lstDocument.add(objDVD);
					}
					else if (intTypeDocument == 1) {
						Livre objLivre = new Livre(tabLigne[0], tabLigne[1], dateDocument, Etat.DISPONIBLE, tabLigne[3]);
						lstDocument.add(objLivre);
					}
					else {
						Periodique objPeriodique = new Periodique(tabLigne[0], tabLigne[1], dateDocument, Etat.DISPONIBLE, Integer.parseInt(tabLigne[3].trim()), Integer.parseInt(tabLigne[4].trim()));
						lstDocument.add(objPeriodique);
					}	
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		
		//lstDocument.forEach(System.out :: println);
		//if(lstDocument.get(0).
	}
	
	private void Serialisation() {
		
	}
	
	private void Deserialisation() {
		
	}
	
	public static ListeDocuments getInstance() {
		return instanceDoc;
	}
}
