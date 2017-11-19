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
import javafx.stage.Stage;

public class GestionPersonnes {

	public static void connection(String strNomConnection, Stage primaryStage) {
		boolean booConnecter = false;
		Alert alerteConnection = new Alert(AlertType.WARNING, "votre identifiant n'est pas valide", ButtonType.OK);
		InterfacePrincipale interfacePrincipale;
		if (strNomConnection != null && strNomConnection.trim() != "") {
			for (Personne personne : ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent)) {
				if (personne.getStrNoPersonne().equalsIgnoreCase(strNomConnection.trim())) {
					interfacePrincipale = new InterfacePrincipale(primaryStage, TypePersonne.Adherent, personne);
					primaryStage.setScene(interfacePrincipale.getScene());
					booConnecter = true;
				}
			}
		}
		if (!booConnecter) {
			alerteConnection.showAndWait();
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
		}
	}

	public static boolean ajouterAdherent(String strNom, String strPrenom, String strAdresse, String strNoTelephone,
			Stage stage) {
		boolean booAjoute = false;
		Alert alerteAjouterPersonne = ajouterDeBase(strNom, strPrenom, strAdresse, strNoTelephone);

		if (alerteAjouterPersonne == null) {
			//Adherent.ouRenduNoPersonnes();
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

		return alerteAjouterPersonne;
	}

	public static void supprimerPersonne(Personne personne) {
		try {
			System.out.println(personne.getLstPrets().size());
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setHeaderText("Suppresion du dossier d'une personne");
			alert.setContentText("Voulez vous vraiment Supprimer le dossier de " + personne.getStrPrenom() + " "
					+ personne.getStrNom() + "?");
			if (personne.getLstPrets().size() == 0) {
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

}
