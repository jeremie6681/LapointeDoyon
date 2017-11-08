package application.modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ListeDocuments implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String strNomFichierDVD = "Donnee/DVD.txt";
	private final String strNomFichierLivre = "Donnee/Livres.txt";
	private final String strNomFichierPeriodique = "Donnee/Periodiques.txt";
	private final String strNomFichierSerialiser = "Donnee/lstDocuments.ser";

	//public ArrayList<Document> lstDocument = new ArrayList<>();
	
	private ArrayList<Document> lstLivre = new ArrayList<>();
	private ArrayList<Document> lstDvd = new ArrayList<>();
	private ArrayList<Document> lstPeriodique = new ArrayList<>();
	
	public Map<TypeDocument, List<Document>> mapDocument = new HashMap<>();
	
	
	private static ListeDocuments instanceDoc ;      
	
	private ListeDocuments() {
		mapDocument.put(TypeDocument.Livre, lstLivre);
		mapDocument.put(TypeDocument.Periodique, lstPeriodique);
		mapDocument.put(TypeDocument.Dvd, lstDvd);
		
		//si pas de fichier sérialiser touver
		
		File fichierSerialiser = new File(strNomFichierSerialiser);
		
		//S'il y a eu déjà une sérialisation
		if(fichierSerialiser.exists()) {
			deserialisation();
		}
		else {
			lectureFichierOriginal();
		}

		
		//System.out.println(mapDocument.get(TypeDocument.Livre).get(0).getClass().getSimpleName());
		mapDocument.get(TypeDocument.Livre).forEach(System.out :: println);

	}
	
	private void lectureFichierOriginal() {
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
						lstDvd.add(objDVD);
					}
					else if (intTypeDocument == 1) {
						Livre objLivre = new Livre(tabLigne[0], tabLigne[1], dateDocument, Etat.DISPONIBLE, tabLigne[3]);
						lstLivre.add(objLivre);
					}
					else {
						Periodique objPeriodique = new Periodique(tabLigne[0], tabLigne[1], dateDocument, Etat.DISPONIBLE, Integer.parseInt(tabLigne[3].trim()), Integer.parseInt(tabLigne[4].trim()));
						lstPeriodique.add(objPeriodique);
					}	
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
		}
		
		
		//mapDocument.get(TypeDocument.Livre).forEach(System.out :: println);
		//if(lstDocument.get(0).
	}
	
	public void serialisation() {
		try { 
		    
			FileOutputStream fichier = new FileOutputStream(strNomFichierSerialiser); 
			ObjectOutputStream oos = new ObjectOutputStream(fichier);    
			oos.writeObject(this.mapDocument);
			oos.close();
			fichier.close();
			System.out.println("sérialisation des documents terminée avec succès");
			}catch (IOException e) {      
			e.printStackTrace();    
			} 
	}
	
	private void deserialisation() {
		 
		try {
		 FileInputStream fichier = new FileInputStream(strNomFichierSerialiser); 
		 ObjectInputStream ois = new ObjectInputStream(fichier);
		 this.mapDocument = (HashMap<TypeDocument, List<Document>>) ois.readObject();
		 this.lstLivre= (ArrayList<Document>) mapDocument.get(TypeDocument.Livre);
		 this.lstDvd=(ArrayList<Document>) mapDocument.get(TypeDocument.Dvd);
		 this.lstPeriodique=(ArrayList<Document>) mapDocument.get(TypeDocument.Periodique);
		 ois.close();
		 fichier.close();
		 System.out.println("désérialisation des documents terminée avec succès");
		 
		 
		 ///
		 ///reche
		 ///
		 
		}catch(IOException e) {
			System.err.println("erreur avec la lecture des ficher de sérialisation des documents");
			e.printStackTrace();
		}catch(ClassNotFoundException e2) {
			System.err.println("erreur avec la désérialisation des documents");
		}
	}
	
	public static ListeDocuments getInstance() {
		  if (instanceDoc == null) 
			  instanceDoc = new ListeDocuments();
		  return instanceDoc;
	}
}
