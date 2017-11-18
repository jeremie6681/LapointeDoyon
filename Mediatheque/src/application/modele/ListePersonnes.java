package application.modele;

import java.io.BufferedReader;
import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListePersonnes implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2361481750244889545L;
	private final String strNomFichierPersonne = "Donnee/Personnes.txt";
	private final String strNomFichierSerialiser = "Donnee/lstPersonnes.ser";
	
	private ObservableList<Personne> lstPrepose = FXCollections.observableArrayList();
	private ObservableList<Personne> lstAdherent = FXCollections.observableArrayList();
	private ObservableList<Personne> lstAdmin = FXCollections.observableArrayList();
	
	public Map<TypePersonne, ObservableList<Personne>> mapPersonne = new HashMap<>();
	
	
	private static ListePersonnes instanceLst;
	
	public ListePersonnes() {
		mapPersonne.put(TypePersonne.Adherent, lstAdherent);
		mapPersonne.put(TypePersonne.Prepose, lstPrepose);
		mapPersonne.put(TypePersonne.Admin, lstAdmin);
		
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
			
			//Passage d'un observableList à des arrayLists
			Map<TypePersonne, List<Personne>> mapTempo = new HashMap<TypePersonne, List<Personne>>();
			mapTempo.put(TypePersonne.Adherent, new ArrayList<>(mapPersonne.get(TypePersonne.Adherent)));
			mapTempo.put(TypePersonne.Prepose,new ArrayList<>(mapPersonne.get(TypePersonne.Prepose)));
			mapTempo.put(TypePersonne.Admin, new ArrayList<>(mapPersonne.get(TypePersonne.Admin)));
			
			oos.writeObject(mapTempo);
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
			 
			 Map<TypePersonne, List<Personne>> mapTempo = (HashMap<TypePersonne, List<Personne>>) ois.readObject();
			 
			 //Passage d'arraylist à des observableList
			 mapPersonne.get(TypePersonne.Adherent).addAll(mapTempo.get(TypePersonne.Adherent));
			 this.lstAdherent = mapPersonne.get(TypePersonne.Adherent);
			 
			 mapPersonne.get(TypePersonne.Prepose).addAll(mapTempo.get(TypePersonne.Prepose));
			 this.lstPrepose = mapPersonne.get(TypePersonne.Prepose);
			 
			 mapPersonne.get(TypePersonne.Admin).addAll(mapTempo.get(TypePersonne.Admin));
			 this.lstAdmin = mapPersonne.get(TypePersonne.Admin);
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
		BufferedReader brFichier = null;
		
		try {
			brFichier = new BufferedReader(new FileReader(strNomFichierPersonne));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String strLigne;
		try {
			while((strLigne = brFichier.readLine()) != null) {
				String[] tabLigne = strLigne.split(",");
				
				//Selon le type de personne, il crée l'object et le range dans la liste approprié
				switch (tabLigne[0].substring(0, 3)) {
				case "Adm":
					Prepose objAdministrateur = new Prepose(tabLigne[1], tabLigne[2], tabLigne[3], tabLigne[4], tabLigne[5], tabLigne[0]);
					lstAdmin.add(objAdministrateur);
					break;
				case "Pre":
					Prepose objPreposer = new Prepose(tabLigne[1], tabLigne[2], tabLigne[3], tabLigne[4], tabLigne[5], tabLigne[0]);
					lstPrepose.add(objPreposer);
					break;
				case "Adh":
					Adherent objAdherent = new Adherent(tabLigne[1], tabLigne[2], tabLigne[3], tabLigne[4], tabLigne[0]);
					lstAdherent.add(objAdherent);;
					break;

				default:
					throw new Exception();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void miseAjourPrets() {
		for (Personne personne : lstAdherent) {
			for(Pret pret : personne.getLstPrets()) {
				pret.gestionAmende();
			}
		}
	}
	
	
	public static ListePersonnes getInstance() {
					//////////////////////////////////////////peut-etre updater les amendes(et les noix) lorque l'on passe ici  
		  if (instanceLst == null) {
			  System.out.println("pas d affaire la");
			  instanceLst = new ListePersonnes();
		  }
		  return instanceLst;
	}

}
