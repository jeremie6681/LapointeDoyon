package application.controleur;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import application.modele.Amende;
import application.modele.Document;
import application.modele.Etat;
import application.modele.ListePersonnes;
import application.modele.Personne;
import application.modele.Pret;
import application.modele.TypePersonne;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class GestionPrets {
	/*
	 *permet la v�rification des donn�es envoy�s par l'interface pricipale
	 *si tout est correcte, cr�e un pret dans la lstPret de la personne, sinon,
	 *affiche un message d'erreur
	 */
	public static void emprunterDocument(Personne personne, Document document){

		//v�rifie si un document et une personne sont s�lectionn�s
		if (document != null & personne != null) {
			Alert alerteTropDoc;
			Alert alerteConfimation = new Alert(AlertType.CONFIRMATION,personne.getStrPrenom() + " " + personne.getStrNom() + " � louer " + document.getStrTitre(),ButtonType.OK);
			Alert alerteDocLoue = new Alert(AlertType.WARNING,"Le document " + document.getStrCodeDocument() + " est d�ja lou�", ButtonType.OK);
			Alert alerteAmende;
			int intDocTypeEmprunte = 0;
			boolean booAUneAmande = false;
			//v�rifie la disponibilit� du document
			if (!document.getEtatDoc().equals(Etat.DISPONIBLE)) {
				alerteDocLoue.show();
			} else {

				for (Pret pret : personne.getLstPrets()) {
					if (pret.getDoc() != null) {
						if (pret.getDoc().getTypeDocument().equals(document.getTypeDocument())) {
							intDocTypeEmprunte++;
						}
					}
				}
				//v�rifi si le nombre maximal de document de la cat�gorie est atteint
				if (intDocTypeEmprunte >= document.getTypeDocument().getIntNbEmprunte()) {
					alerteTropDoc = new Alert(AlertType.WARNING,"Vous avec d�ja lou� le nombre maximal de documents de type: "+ document.getTypeDocument().getStrNomType(),ButtonType.OK);
					alerteTropDoc.showAndWait();
				} 
				else {
					//cherche si la personne � une amende
					for (Pret pret : personne.getLstPrets()) {
						if (pret.getAmende() != null) {
							booAUneAmande = true;
						}
					}
					if (booAUneAmande) {
						alerteAmende = new Alert(AlertType.WARNING,
								personne.getStrPrenom() + " " + personne.getStrNom()+ " ne peut louer de documents en raison d'une amande impay�e de "
										+ String.format("%.2f", calculerAmende(personne)) + "$",ButtonType.OK);
						alerteAmende.showAndWait();
					} else {
						//ajout du pret
						Pret pret = new Pret(document);
						personne.getLstPrets().add(pret);
						alerteConfimation.showAndWait();
					}
				}
			}
		} else {
			Alert alerteDocsNonSelectionne = new Alert(AlertType.WARNING,"veuillez selectionner un document ET un adh�rent", ButtonType.OK);
			alerteDocsNonSelectionne.showAndWait();
		}
	}
	/*
	 * Permet de retourner le document s�lectionn�
	 */
	public static void retournerDocument(Document doc) {
		//v�rifie qu'un document est bien s�lectionn�
		if (doc != null) {
			//v�rifie qu le document � bien �t� lou�
			if (!doc.getEtatDoc().equals(Etat.DISPONIBLE)) {

				for (Personne adh : ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent)) {
					try {
						for (Pret p : adh.getLstPrets()) {
							System.out.println(adh.getStrNom());
							System.out.println(p.getDoc());
							System.out.println(doc);
							if (doc.getStrCodeDocument().equals(p.getDoc().getStrCodeDocument())) {
								//retour d'un document avec amende
								if (LocalDate.now().isAfter(p.getDateRetourPrevue())) {
									p.setAmende(new Amende(p.getDateRetourPrevue(), LocalDate.now(),
											p.getDblmontantPenalite(), true));
									p.setDateEffectiveRetour(LocalDate.now());
									p.getDoc().setEtatDoc(Etat.DISPONIBLE);

									Alert alerteRetour = new Alert(AlertType.INFORMATION, "Le document "+ doc.getStrTitre()+ " � �t� retourn�, mais une amende de est associ�e � au dossier de "
											+ adh.getStrPrenom() + " " + adh.getStrNom(), ButtonType.OK);

									alerteRetour.showAndWait();
									//retour normal d'un document
								} else {
									p.getDoc().setEtatDoc(Etat.DISPONIBLE);
									adh.getLstPrets().remove(p);
									System.out.println("retour bas");
									Alert alerteRetour = new Alert(AlertType.INFORMATION,"Le document " + doc.getStrTitre() + " � �t� retourn�", ButtonType.OK);
									alerteRetour.showAndWait();
								}
							} 
							else {}
						}
					} catch (Exception e) {}
				}

			} else {
				Alert alerteDocumentNonEmprumte = new Alert(AlertType.WARNING,"Le document " + doc.getStrTitre() + " n'est pas emprunt� ", ButtonType.OK);
				alerteDocumentNonEmprumte.showAndWait();
			}
		} else {
			Alert alerteDocsNonSelectionne = new Alert(AlertType.WARNING,"veuillez selectionner un document � retourner", ButtonType.OK);
			alerteDocsNonSelectionne.showAndWait();
		}
	}
	/*
	 * calcule le montant d'une amende
	 * 
	 * retourne: un double qui contient le montant de l'amende
	 */
	public static double calculerAmende(Personne personne) {
		double dblAmende = 0;
		try {
			for (Pret pret : personne.getLstPrets()) {
				if (pret.getAmende() != null) {
					dblAmende += pret.getAmende().getDblMontant();
				}
			}
		} catch (NullPointerException n) {}
		return dblAmende;
	}
	/*
	 * permet de payer les amende d'un adh�rent
	 */
	public static void payerAmande(Personne personne) {
		Alert alerteAucuneAmende;
		double dblAmende = calculerAmende(personne);
		String s = String.format("%.2f", dblAmende);
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Amende");
		alert.setHeaderText("Payer une amende");
		ArrayList<Pret> ar = new ArrayList<>();
		//v�rifie qu'une personne soit s�lectionn�e
		if (personne != null) {
			alert.setContentText(personne.getStrNoPersonne() + " " + personne.getStrNom()+ " vas payer son amende de " + s + "$");
			if (dblAmende != 0) {
				//permet la confimation
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					for (Pret pret : personne.getLstPrets()) {
						if (pret.getAmende() != null && pret.getAmende().getBooRetour() == true) {
							ar.add(pret);		//en raison des theads, la supression doit �tre fait � l'ext�rieur du for
						}
					}
					//supression de toutes les amendes trouv�es
					ar.forEach(e -> personne.getLstPrets().remove(e));
				} else {
				}
			} else {
				alerteAucuneAmende = new Alert(AlertType.INFORMATION,personne.getStrPrenom() + " n'a aucune amende � rembourser", ButtonType.OK);
				alerteAucuneAmende.showAndWait();
			}
		} else {
			alerteAucuneAmende = new Alert(AlertType.WARNING,"vous devez selectionner la personne qui veut payer ses amendes", ButtonType.OK);
			alerteAucuneAmende.showAndWait();
		}
	}

}
