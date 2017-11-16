package application.controleur;

import java.time.LocalDate;
import java.util.Optional;

import application.modele.Adherent;
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
	public static void emprunterDocument(Personne personne, Document document){

		
		// vérifier disponibilité du document
		if (document != null & personne != null) {
			Alert alerteTropDoc;
			Alert alerteConfimation = new Alert(AlertType.CONFIRMATION,personne.getStrPrenom() + " " + personne.getStrNom() + " à louer " + document.getStrTitre(),ButtonType.OK);
			Alert alerteDocLoue = new Alert(AlertType.WARNING,"Le document " + document.getStrCodeDocument() + " est déja loué", ButtonType.OK);
			Alert alerteAmende;
			int intDocTypeEmprunte = 0;
			boolean booAUneAmande = false;
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
				if (intDocTypeEmprunte >= document.getTypeDocument().getIntNbEmprunte()) {
					alerteTropDoc = new Alert(AlertType.WARNING,
							"Vous avec déja loué le nombre maximal de documents de type: "
									+ document.getTypeDocument().getStrNomType(),
							ButtonType.OK);
					alerteTropDoc.showAndWait();
				} else {
					for (Pret pret : personne.getLstPrets()) {
						if (pret.getAmende() != null) {
							booAUneAmande = true;
						}
					}
					if (booAUneAmande) {
						alerteAmende = new Alert(AlertType.WARNING,
								personne.getStrPrenom() + " " + personne.getStrNom()
										+ " ne peut louer de documents en raison d'une amande impayée de "
										+ String.format("%.2f", calculerAmende(personne)) + "$",
								ButtonType.OK);
						alerteAmende.showAndWait();
					} else {
						Pret pret = new Pret(document);
						personne.getLstPrets().add(pret);
						alerteConfimation.showAndWait();
					}
				}
			}
		} else {
			Alert alerteDocsNonSelectionne = new Alert(AlertType.WARNING,
					"veuillez selectionner un document ET un adhérent", ButtonType.OK);
			alerteDocsNonSelectionne.showAndWait();
		}
	}
	public static void retournerDocument(Document doc) {
		if (doc != null) {
			if (!doc.getEtatDoc().equals(Etat.DISPONIBLE)) {

				// ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent).forEach(f->f.getLstPrets().stream().filter(j->j.getDoc().equals(doc)).findFirst().get().retourDoc());

				for (Personne adh : ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent)) {
					try {
					for (Pret p : adh.getLstPrets()) {
						if (doc.equals(p.getDoc())) {
							
							if (LocalDate.now().isAfter(p.getDateRetourPrevue())) {
								p.setAmende(new Amende(p.getDateRetourPrevue(), LocalDate.now(), p.getDblmontantPenalite(),true));
								p.setDateEffectiveRetour(LocalDate.now());
								p.getDoc().setEtatDoc(Etat.DISPONIBLE);
								}
							else {
								p.getDoc().setEtatDoc(Etat.DISPONIBLE);
								adh.getLstPrets().remove(p);	
							}
						}else {}
					}
					}catch(Exception e) {
						
					}
				}

			} else {
				Alert alerteDocumentNonEmprumte = new Alert(AlertType.WARNING,
						"Le document " + doc.getStrTitre() + " n'est pas emprunté ", ButtonType.OK);
				alerteDocumentNonEmprumte.showAndWait();
			}
		} else {
			Alert alerteDocsNonSelectionne = new Alert(AlertType.WARNING,
					"veuillez selectionner un document à retourner", ButtonType.OK);
			alerteDocsNonSelectionne.showAndWait();
		}
	}
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
	public static void payerAmande(Personne personne) {
		Alert alerteAucuneAmende;
		double dblAmende= calculerAmende(personne);
		String s = String.format("%.2f", dblAmende);
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Amende");
		alert.setHeaderText("Payer une amende");
		alert.setContentText(personne.getStrNoPersonne()+" "+personne.getStrNom()+ "veut vas payer son amende de " + s +"$");
		

		if (dblAmende!=0){
			
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				for (Pret pret : personne.getLstPrets()){
					try {
					if(pret.getDoc().getEtatDoc()==Etat.DISPONIBLE) {		///paye les dettes pour documents retournés
						personne.getLstPrets().remove(pret);
					}
				}	
				catch(NullPointerException n) {
				}	
				}
			} else {
			}	
		}
		else {
			alerteAucuneAmende = new Alert(AlertType.INFORMATION,personne.getStrPrenom()+" n'a aucune amende à rembourser",ButtonType.OK);
			alerteAucuneAmende.showAndWait();
		}
	}

}
