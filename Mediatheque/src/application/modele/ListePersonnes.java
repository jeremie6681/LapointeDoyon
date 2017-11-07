package application.modele;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListePersonnes {
	
	private final String strNomFichierPersonne = "Donnee/Personnes.txt";
	private final String strNomFichierSerialiser = "Donnee/lstPersonnes.ser";
	
	private ArrayList<Personne> lstPrepose = new ArrayList<>();
	private ArrayList<Personne> lstAdherent = new ArrayList<>();
	private ArrayList<Personne> lstAdmin = new ArrayList<>();
	
	public static Map<TypePersonne, List<Personne>> mapPersonne = new HashMap<>();
	
	private static ListePersonnes instanceLst;
	
	public ListePersonnes() {
		mapPersonne.put(TypePersonne.Adherent, lstAdherent);
		mapPersonne.put(TypePersonne.Prepose, lstPrepose);
		mapPersonne.put(TypePersonne.Admin, Arrays.asList( new Prepose("bob", "graton", "123 rue", "5141234567", "secret","admin")));
		
		//si pas de fichier sérialiser touver
		
		File fichierSerialiser = new File(strNomFichierSerialiser);
		
		//S'il y a eu déjà une sérialisation
		if(fichierSerialiser.exists()) {
			deserialisation();
		}
		else {
			lecture();
		}

	}
	
	public void serialisation() {
		try { 
		    
			FileOutputStream fichier = new FileOutputStream(strNomFichierSerialiser); 
			ObjectOutputStream oos = new ObjectOutputStream(fichier);    
			oos.writeObject(this.mapPersonne);
			oos.close();
			fichier.close();
			System.out.println("sérialisation des personnes terminée avec succès");
			}catch (IOException e) {      
			e.printStackTrace();    
			} 
	}
	
	private void deserialisation() {
		try {
			 FileInputStream fichier = new FileInputStream(strNomFichierSerialiser); 
			 ObjectInputStream ois = new ObjectInputStream(fichier);
			 this.mapPersonne = (HashMap<TypePersonne, List<Personne>>) ois.readObject();
			 this.lstAdherent= (ArrayList<Personne>) mapPersonne.get(TypePersonne.Adherent);
			 this.lstPrepose=(ArrayList<Personne>) mapPersonne.get(TypePersonne.Prepose);
			 this.lstAdmin=(ArrayList<Personne>) mapPersonne.get(TypePersonne.Admin);
			 ois.close();
			 fichier.close();
			 System.out.println("désérialisation des personnes terminée avec succès");
			 
			}catch(IOException e) {
				System.err.println("erreur avec la lecture des ficher de sérialisation des personnes");
				e.printStackTrace();
			}catch(ClassNotFoundException e2) {
				System.err.println("erreur avec la désérialisation des Personnes");
			}
		}
	
	private void lecture() {
		
	}
	
	
	public static ListePersonnes getInstance() {
		  if (instanceLst == null) 
			  instanceLst = new ListePersonnes();
		  return instanceLst;
	}
}
