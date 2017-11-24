package application.controleur;

import java.util.Optional;

import application.modele.Adherent;
import application.modele.ListePersonnes;
import application.modele.Personne;
import application.modele.Prepose;
import application.modele.Pret;
import application.modele.TypePersonne;
import application.vue.InterfacePrincipale;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class GestionPersonnes {
	/*
	 * valide les donn�es pour permetre � un adh�rent de se connecter
	 *  avec un num�ro de t�l�phone pour afficher les information de 
	 *  son dossier. affiche celles ci si tout est valide
	 */
	public static void connectionAdh(String strNoTell, Stage stage) {
		Personne personneTrouve=null;
		if (strNoTell!=null &&strNoTell!="") {
			for (Personne adh : ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent)) {
				if(strNoTell.equals(adh.getStrNoTelephone())) {
					personneTrouve=adh;
				}
			}
			
		}else {
			Alert alerteChaineVide = new Alert(AlertType.WARNING,"veuillez insrire un num�ros de t�l�phone", ButtonType.OK);
			alerteChaineVide.showAndWait();
		}
		
		if(personneTrouve==null) {
			Alert alerteNonTrouve = new Alert(AlertType.WARNING,"Le num�ros de t�l�phone entre ne correspond � aucun dossier", ButtonType.OK);
			alerteNonTrouve.showAndWait();
		}
		else {
			Alert alerteDossier = new Alert(AlertType.INFORMATION,((Adherent)personneTrouve).toString(), ButtonType.OK);
			alerteDossier.showAndWait();
			stage.hide();
		}
	}
	/*
	 * valide les donn�es pour permetre � un adh�rent de se connecter pour
	 * afficher les information de son dossier. affiche celles ci si tout est valide
	 */
	public static void connectionAdh(String strNom,String strPrenom, Stage stage) {
		Personne personneTrouve=null;
		if (strNom!=null &&strNom!=""&&strPrenom!=null &&strPrenom!="") {
			for (Personne adh : ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent)) {
				if(strNom.equalsIgnoreCase(adh.getStrNom())) {
					if(strPrenom.equalsIgnoreCase(adh.getStrPrenom())) {
						personneTrouve=adh;
					}
				}
			}
			
		}else {
			Alert alerteChaineVide = new Alert(AlertType.WARNING,"Veuillez insrire votre nom ET pr�nom", ButtonType.OK);
			alerteChaineVide.showAndWait();
		}
		
		if(personneTrouve==null) {
			Alert alerteNonTrouve = new Alert(AlertType.WARNING,"Votre nom ne correspond � aucun dossier", ButtonType.OK);
			alerteNonTrouve.showAndWait();
		}
		else {
			Alert alerteDossier = new Alert(AlertType.INFORMATION,((Adherent)personneTrouve).toString(), ButtonType.OK);
			alerteDossier.showAndWait();
			stage.hide();
		}
	}
	/*
	 * permet � l'admin ou aux pr�pos�s de se connecter
	 * afin d'acc�der � l'interface pricipale
	 */
	public static void connection(String strNomConnection, String strMotPasse, Stage primaryStage) {
		boolean booConnecter = false;
		Alert alerteConnection = new Alert(AlertType.WARNING,
				"votre identifiant ou votre Mot de passe n'est pas valide", ButtonType.OK);
		InterfacePrincipale interfacePrincipale = null;
		Prepose temp;
		//pour Admin
		if (strNomConnection != null && strNomConnection.trim() != "") {
			if (strNomConnection.trim().equalsIgnoreCase(ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Admin).get(0).getStrNoPersonne())) {
				temp = (Prepose) ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Admin).get(0);
				if (strMotPasse.trim().equals(temp.getStrMotPasse())) {
					interfacePrincipale = new InterfacePrincipale(primaryStage, TypePersonne.Admin, temp);
					booConnecter = true;
				}
				//pour Pr�pos�s
			} else {
				for (Personne personne : ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Prepose)) {
					if (personne.getStrNoPersonne().equalsIgnoreCase(strNomConnection.trim())) {
						temp = (Prepose) personne;
						if (strMotPasse.trim().equals(temp.getStrMotPasse())) {
							interfacePrincipale = new InterfacePrincipale(primaryStage, TypePersonne.Prepose, temp);
							booConnecter = true;
							
						}
					}
				}
			}
		}
		if (!booConnecter) {
			alerteConnection.showAndWait();
		} else {
			//connexion
			primaryStage.setScene(interfacePrincipale.getScene());
			//pour que la fenetre ne soit pas a l'exterieur de l'ecrand
			primaryStage.setX(25);
			primaryStage.setY(25);
		}
	}
	
	/*
	 * valide les donn�es n�sc�saires pour ajouter un adh�rent
	 * si celle-ci sont valides cr�e et ajoute l'adh�rent � la 
	 * liste de personnes. affiche un message si une donn�e
	 *  n'est pas ad�quate
	 *  
	 *  retourne : true si la personne � �t� ajout�e
	 */
	public static boolean ajouterAdherent(String strNom, String strPrenom, String strAdresse, String strNoTelephone,Stage stage) {
		boolean booAjoute = false;
		Alert alerteAjouterPersonne = ajouterDeBase(strNom, strPrenom, strAdresse, strNoTelephone);

		if (alerteAjouterPersonne == null) {
			Adherent.ouRenduNoPersonnes();
			Adherent adherent = new Adherent(strNom, strPrenom, strAdresse, strNoTelephone);
			ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent).add(adherent);
			
			alerteAjouterPersonne = new Alert(AlertType.CONFIRMATION, strPrenom + " " + strNom + "a �t� cr�er avec succes. Son Identifiant est : " 
			+ adherent.getStrNoPersonne(), ButtonType.OK);
			
			booAjoute = true;
			stage.hide();
		}
		alerteAjouterPersonne.showAndWait();
		return booAjoute;
	}
	
	/*
	 * valide les donn�es n�sc�saires pour ajouter un Pr�pos�
	 * si celle-ci sont valides cr�e et ajoute l'adh�rent � la 
	 * liste de personnes. affiche un message si une donn�e
	 * n'est pas ad�quate
	 *  
	 *  retourne : true si la personne � �t� ajout�e
	 */
	public static Object ajouterPrepose(String strNom, String strPrenom, String strAdresse, String strNoTelephone,String strPwd, String strPwdConfirmer, Stage stage) {
		boolean booAjoute = false;
		Alert alerteAjouterPersonne = ajouterDeBase(strNom, strPrenom, strAdresse, strNoTelephone);

		if (strPwd == null || strPwd.trim().equals("") || strPwdConfirmer == null
				|| strPwdConfirmer.trim().equals("")) {
			alerteAjouterPersonne = new Alert(AlertType.WARNING,"veuillez insrire le mot de passe ainsi que sa confirmation", ButtonType.OK);
		} else if (!strPwd.equals(strPwdConfirmer)) {
			alerteAjouterPersonne = new Alert(AlertType.WARNING,"Le mot de passe et que sa confirmation ne sont pas identiques", ButtonType.OK);
		}
		
		if (alerteAjouterPersonne == null) {
			Prepose.ouRenduNoPersonnes();
			Prepose prepose = new Prepose(strNom, strPrenom, strAdresse, strNoTelephone, strPwd);
			ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Prepose).add(prepose);
			alerteAjouterPersonne = new Alert(AlertType.CONFIRMATION, strPrenom + " " + strNom
					+ "a �t� cr�er avec succes. Son Identifiant est : " + prepose.getStrNoPersonne(), ButtonType.OK);
			booAjoute = true;
			stage.hide();
		}
		alerteAjouterPersonne.showAndWait();
		return booAjoute;
	}
	/*
	 * validation pour la cr�ation de personnes
	 * 
	 * retourne: null si toute les donn�es sont valides,
	 *  sinon retourne un message d'erreur
	 */
	private static Alert ajouterDeBase(String strNom, String strPrenom, String strAdresse, String strNoTelephone) {

		Alert alerteAjouterPersonne = null;
		if (strNom == null || strNom.trim().equals("")) {
			alerteAjouterPersonne = new Alert(AlertType.WARNING, "veuillez sp�cifier un nom ", ButtonType.OK);
		} else if (strPrenom == null || strPrenom.trim().equals("")) {
			alerteAjouterPersonne = new Alert(AlertType.WARNING, "veuillez sp�cifier un Pr�nom ", ButtonType.OK);
		} else if (strAdresse == null || strAdresse.trim().equals("")) {
			alerteAjouterPersonne = new Alert(AlertType.WARNING, "veuillez sp�cifier une adresse ", ButtonType.OK);
		} else if (strNoTelephone == null || strNoTelephone.trim().equals("")) {
			alerteAjouterPersonne = new Alert(AlertType.WARNING, "veuillez sp�cifier une num�ro de t�l�phone",
					ButtonType.OK);
		} else if (!strNoTelephone.trim().matches("^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$")) {// https://howtodoinjava.com/regex/java-regex-validate-and-format-north-american-phone-numbers/
			alerteAjouterPersonne = new Alert(AlertType.WARNING,"veuillez sp�cifier une num�ro de t�l�phone valide en Am�rique du Nord", ButtonType.OK);
		}
		//ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent).forEach(e->System.out.println(e));
		return alerteAjouterPersonne;
	}

	public static void supprimerPersonne(Personne personne) {
		try {
			//System.err.println(personne);
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setHeaderText("Suppresion du dossier d'une personne");
			alert.setContentText("Voulez vous vraiment Supprimer le dossier de " + personne.getStrPrenom() + " "+ personne.getStrNom() + "?");
			if (personne.getLstPrets().size() == 0||personne.getLstPrets()==null) {
				Optional<ButtonType> result = alert.showAndWait();			//affiche un message de confirmation
				if (result.get() == ButtonType.OK) {
					ListePersonnes.getInstance().mapPersonne.get(personne.getTypePersonne()).removeIf(p -> personne.equals(p));
				} else {}
			} else {
				alert = new Alert(AlertType.WARNING, personne.getStrPrenom() + " " + personne.getStrPrenom()
						+ " doit avoir retourn� tous ses emprunts et avoir pay� ses frais de retard avant de pouovoir fermer son dossier",ButtonType.OK);
				alert.showAndWait();
			}
		} catch (NullPointerException e) {
			Alert alertErreur = new Alert(AlertType.WARNING, "vous devez choisir une personne");
			alertErreur.showAndWait();
		}

	}
	/*
	 * validation de donn�es pour la modification d'un adh�rent.
	 * modifie celui-ci si les donn�es sont valides.
	 */
	public static void modifierAdherent(Adherent adh, String strAdresse, String strNoTelephone, Stage stage) {
		Alert alerteModifierPersonne = ajouterDeBase(adh.getStrNom(), adh.getStrPrenom(), strAdresse, strNoTelephone);
		if (alerteModifierPersonne == null) {
			adh.setStrAdresse(strAdresse);
			adh.setStrNoTelephone(strNoTelephone);
			alerteModifierPersonne = new Alert(AlertType.CONFIRMATION,
					adh.getStrPrenom() + " " + adh.getStrNom() + "a �t� modfi� avec succes", ButtonType.OK);
			stage.hide();
		}
		alerteModifierPersonne.showAndWait();
	}
	/*
	 *permet l'affichage des prets d'une personne 
	 */
	public static void afficherPrets(Personne p) {
		//v�rifier si une personne est selectionn�e
		if (p != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Affichage des prets");
			alert.setHeaderText(
					"Prets de " + p.getStrPrenom() + " " + p.getStrNom() + " (" + p.getStrNoPersonne() + ")");
			String strContentext = "";

			if (p.getLstPrets().size() == 0 || p.getLstPrets() == null) {
				strContentext = p.getStrPrenom() + " " + p.getStrNom() + " (" + p.getStrNoPersonne()+ ") n'a aucuns Prets et Amendes";
			} else {
				for (Pret pret : p.getLstPrets()) {
					strContentext += pret.toString();
				}
			}
			alert.setContentText(strContentext);
			alert.showAndWait();

		} else {
			Alert alert = new Alert(AlertType.WARNING,"vous devez selectionner un personne pour laquelle afficher les prets");
			alert.showAndWait();
		}
	}
	/*
	 * validation de donn�es pour la modification d'un pr�pose.
	 * modifie celui-ci si les donn�es sont valides.
	 */
	public static void modifierPrepose(Prepose prep, String strAdresse, String strNoTelephone,String strPwd,String strPwdConfirmer, Stage stage) {
		Alert alerteModifierPersonne = ajouterDeBase(prep.getStrNom(), prep.getStrPrenom(), strAdresse, strNoTelephone);
		if (strPwd == null || strPwd.trim().equals("") || strPwdConfirmer == null|| strPwdConfirmer.trim().equals("")) {
			alerteModifierPersonne = new Alert(AlertType.WARNING,"veuillez insrire le mot de passe ainsi que sa confirmation", ButtonType.OK);
		}
		if(strPwd!=strPwdConfirmer) {
			alerteModifierPersonne = new Alert(AlertType.WARNING,"Le mot de passe ainsi que sa confirmation doivent �tres identiques", ButtonType.OK);
		}
		if (alerteModifierPersonne == null) {
			prep.setStrAdresse(strAdresse);
			prep.setStrNoTelephone(strNoTelephone);
			alerteModifierPersonne = new Alert(AlertType.CONFIRMATION,prep.getStrPrenom() + " " + prep.getStrNom() + "a �t� modfi� avec succ�s", ButtonType.OK);
			stage.hide();
		}
		
		alerteModifierPersonne.showAndWait();
	}
}
