package application.controleur;

import java.util.Optional;

import application.modele.Adherent;
import application.modele.Document;
import application.modele.ListeDocuments;
import application.modele.ListePersonnes;
import application.modele.Personne;
import application.modele.Prepose;
import application.modele.Pret;
import application.modele.TypePersonne;
import application.vue.InterfacePrincipale;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class GestionPersonnes {

	public static void connectionAdh(String strNoTell, Stage stage) {
		Personne personneTrouve=null;
		if (strNoTell!=null &&strNoTell!="") {
			for (Personne adh : ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent)) {
				if(strNoTell.equals(adh.getStrNoTelephone())) {
					personneTrouve=adh;
				}
			}
			
		}else {
			Alert alerteChaineVide = new Alert(AlertType.WARNING,"veuillez insrire un numéros de téléphone", ButtonType.OK);
			alerteChaineVide.showAndWait();
		}
		
		if(personneTrouve==null) {
			Alert alerteNonTrouve = new Alert(AlertType.WARNING,"Le numéros de téléphone entre ne correspond à aucun dossier", ButtonType.OK);
			alerteNonTrouve.showAndWait();
		}
		else {
			Alert alerteDossier = new Alert(AlertType.INFORMATION,((Adherent)personneTrouve).toString(), ButtonType.OK);
			alerteDossier.showAndWait();
			stage.hide();
		}
	}
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
			Alert alerteChaineVide = new Alert(AlertType.WARNING,"Veuillez insrire votre nom ET prénom", ButtonType.OK);
			alerteChaineVide.showAndWait();
		}
		
		if(personneTrouve==null) {
			Alert alerteNonTrouve = new Alert(AlertType.WARNING,"Votre nom ne correspond à aucun dossier", ButtonType.OK);
			alerteNonTrouve.showAndWait();
		}
		else {
			Alert alerteDossier = new Alert(AlertType.INFORMATION,((Adherent)personneTrouve).toString(), ButtonType.OK);
			alerteDossier.showAndWait();
			stage.hide();
		}
	}
	

	public static void connection(String strNomConnection, String strMotPasse, Stage primaryStage) {
		boolean booConnecter = false;
		Alert alerteConnection = new Alert(AlertType.WARNING,
				"votre identifiant ou votre Mot de passe n'est pas valide", ButtonType.OK);
		InterfacePrincipale interfacePrincipale = null;
		Prepose temp;

		if (strNomConnection != null && strNomConnection.trim() != "") {
			if (strNomConnection.trim().equalsIgnoreCase(
					ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Admin).get(0).getStrNoPersonne())) {
				temp = (Prepose) ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Admin).get(0);
				if (strMotPasse.trim().equals(temp.getStrMotPasse())) {
					interfacePrincipale = new InterfacePrincipale(primaryStage, TypePersonne.Admin, temp);
					booConnecter = true;
					

				}
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
			primaryStage.setScene(interfacePrincipale.getScene());
			//pour que la fenetre ne soit pas a l'exterieur de l'ecrand
			primaryStage.setX(25);
			primaryStage.setY(25);
		}
	}

	public static boolean ajouterAdherent(String strNom, String strPrenom, String strAdresse, String strNoTelephone,
			Stage stage) {
		boolean booAjoute = false;
		Alert alerteAjouterPersonne = ajouterDeBase(strNom, strPrenom, strAdresse, strNoTelephone);

		if (alerteAjouterPersonne == null) {
			Adherent.ouRenduNoPersonnes();
			Adherent adherent = new Adherent(strNom, strPrenom, strAdresse, strNoTelephone);
			ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent).add(adherent);
			alerteAjouterPersonne = new Alert(AlertType.CONFIRMATION, strPrenom + " " + strNom
					+ "a été créer avec succes. Son Identifiant est : " + adherent.getStrNoPersonne(), ButtonType.OK);
			booAjoute = true;
			stage.hide();
		}
		alerteAjouterPersonne.showAndWait();
		return booAjoute;
	}

	public static Object ajouterPrepose(String strNom, String strPrenom, String strAdresse, String strNoTelephone,
			String strPwd, String strPwdConfirmer, Stage stage) {
		boolean booAjoute = false;
		Alert alerteAjouterPersonne = ajouterDeBase(strNom, strPrenom, strAdresse, strNoTelephone);

		if (strPwd == null || strPwd.trim().equals("") || strPwdConfirmer == null
				|| strPwdConfirmer.trim().equals("")) {
			alerteAjouterPersonne = new Alert(AlertType.WARNING,
					"veuillez insrire le mot de passe ainsi que sa confirmation", ButtonType.OK);
		} else if (!strPwd.equals(strPwdConfirmer)) {
			alerteAjouterPersonne = new Alert(AlertType.WARNING,
					"Le mot de passe et que sa confirmation ne sont pas identiques", ButtonType.OK);
		}

		if (alerteAjouterPersonne == null) {
			Prepose.ouRenduNoPersonnes();
			Prepose prepose = new Prepose(strNom, strPrenom, strAdresse, strNoTelephone, strPwd);
			ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Prepose).add(prepose);
			alerteAjouterPersonne = new Alert(AlertType.CONFIRMATION, strPrenom + " " + strNom
					+ "a été créer avec succes. Son Identifiant est : " + prepose.getStrNoPersonne(), ButtonType.OK);
			booAjoute = true;
			stage.hide();
		}
		alerteAjouterPersonne.showAndWait();
		return booAjoute;
	}

	private static Alert ajouterDeBase(String strNom, String strPrenom, String strAdresse, String strNoTelephone) {

		Alert alerteAjouterPersonne = null;
		if (strNom == null || strNom.trim().equals("")) {
			alerteAjouterPersonne = new Alert(AlertType.WARNING, "veuillez spécifier un nom ", ButtonType.OK);
		} else if (strPrenom == null || strPrenom.trim().equals("")) {
			alerteAjouterPersonne = new Alert(AlertType.WARNING, "veuillez spécifier un Prénom ", ButtonType.OK);
		} else if (strAdresse == null || strAdresse.trim().equals("")) {
			alerteAjouterPersonne = new Alert(AlertType.WARNING, "veuillez spécifier une adresse ", ButtonType.OK);
		} else if (strNoTelephone == null || strNoTelephone.trim().equals("")) {
			alerteAjouterPersonne = new Alert(AlertType.WARNING, "veuillez spécifier une numéro de téléphone",
					ButtonType.OK);
		} else if (!strNoTelephone.trim().matches("^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$")) {// https://howtodoinjava.com/regex/java-regex-validate-and-format-north-american-phone-numbers/
			alerteAjouterPersonne = new Alert(AlertType.WARNING,
					"veuillez spécifier une numéro de téléphone valide en Amérique du Nord", ButtonType.OK);
		}
		ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent).forEach(e->System.out.println(e));
		return alerteAjouterPersonne;
	}

	public static void supprimerPersonne(Personne personne) {
		try {
			System.err.println(personne);
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setHeaderText("Suppresion du dossier d'une personne");
			alert.setContentText("Voulez vous vraiment Supprimer le dossier de " + personne.getStrPrenom() + " "
					+ personne.getStrNom() + "?");
			if (personne.getLstPrets().size() == 0||personne.getLstPrets()==null) {
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					ListePersonnes.getInstance().mapPersonne.get(personne.getTypePersonne())
							.removeIf(p -> personne.equals(p));
				} else {
				}
			} else {
				alert = new Alert(AlertType.WARNING, personne.getStrPrenom() + " " + personne.getStrPrenom()
						+ " doit avoir retourné tous ses emprunts et avoir payé ses frais de retard avant de pouovoir fermer son dossier",
						ButtonType.OK);
				alert.showAndWait();
			}
		} catch (NullPointerException e) {
			Alert alertErreur = new Alert(AlertType.WARNING, "vous devez choisir une personne");
			alertErreur.showAndWait();
		}

	}

	public static void modifierAdherent(Adherent adh, String strAdresse, String strNoTelephone, Stage stage) {
		Alert alerteModifierPersonne = ajouterDeBase(adh.getStrNom(), adh.getStrPrenom(), strAdresse, strNoTelephone);
		if (alerteModifierPersonne == null) {
			adh.setStrAdresse(strAdresse);
			adh.setStrNoTelephone(strNoTelephone);
			alerteModifierPersonne = new Alert(AlertType.CONFIRMATION,
					adh.getStrPrenom() + " " + adh.getStrNom() + "a été modfié avec succes", ButtonType.OK);
			stage.hide();
		}
		alerteModifierPersonne.showAndWait();
	}

	public static void afficherPrets(Personne p) {
		if (p != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Affichage des prets");
			alert.setHeaderText(
					"Prets de " + p.getStrPrenom() + " " + p.getStrNom() + " (" + p.getStrNoPersonne() + ")");
			String strContentext = "";

			if (p.getLstPrets().size() == 0 || p.getLstPrets() == null) {
				strContentext = p.getStrPrenom() + " " + p.getStrNom() + " (" + p.getStrNoPersonne()
						+ ") n'a aucuns Prets et Amendes";
			} else {
				for (Pret pret : p.getLstPrets()) {
					strContentext += pret.toString();
				}
			}

			alert.setContentText(strContentext);
			alert.showAndWait();

		} else {
			Alert alert = new Alert(AlertType.WARNING,
					"vous devez selectionner un personne pour laquelle afficher les prets");
			alert.showAndWait();
		}
	}
	public static void modifierPrepose(Prepose prep, String strAdresse, String strNoTelephone,String strPwd,String strPwdConfirmer, Stage stage) {
		Alert alerteModifierPersonne = ajouterDeBase(prep.getStrNom(), prep.getStrPrenom(), strAdresse, strNoTelephone);
		if (strPwd == null || strPwd.trim().equals("") || strPwdConfirmer == null
				|| strPwdConfirmer.trim().equals("")) {
			alerteModifierPersonne = new Alert(AlertType.WARNING,"veuillez insrire le mot de passe ainsi que sa confirmation", ButtonType.OK);
		}
		if (alerteModifierPersonne == null) {
			prep.setStrAdresse(strAdresse);
			prep.setStrNoTelephone(strNoTelephone);
			alerteModifierPersonne = new Alert(AlertType.CONFIRMATION,
					prep.getStrPrenom() + " " + prep.getStrNom() + "a été modfié avec succes", ButtonType.OK);
			stage.hide();
		}
		alerteModifierPersonne.showAndWait();
	}
}
