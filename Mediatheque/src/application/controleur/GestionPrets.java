package application.controleur;

import application.modele.Document;
import application.modele.Etat;
import application.modele.Personne;
import application.modele.Pret;
import application.modele.TypeDocument;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class GestionPrets {
	public void emprunterDocument(Personne personne, Document document){
		Alert alerteTropDoc ;
		Alert alerteConfimation = new Alert(AlertType.CONFIRMATION,personne.getStrPrenom()+" "+personne.getStrNom()+" à louer "+document.getStrTitre(),ButtonType.OK);
		Alert alerteDocLoue = new Alert(AlertType.WARNING,"Le document "+document.getStrCodeDocument()+" est déja loué",ButtonType.OK);
		Alert alerteAmande ;
		int intDocTypeEmprunte= 0;
		boolean booAUneAmande=false;
		//véeifier disponibilité du document 
		if (!document.getEtatDoc().equals(Etat.DISPONIBLE)) {
			alerteDocLoue.show();
		}
		else {
			TypeDocument typeEmprunte = null;
			//Détermine le type du document
			for(TypeDocument type : TypeDocument.values()) {
				if(type.getStrIndicateurType().equals(document.getStrCodeDocument().substring(0, 3)))
					typeEmprunte = type;
			}
			if(intDocTypeEmprunte>=typeEmprunte.getIntNbEmprunte()) {
				alerteAmande= new Alert(AlertType.WARNING,"Vous avec déja loué le nombre maximal de documents de type: "+typeEmprunte.getStrNomType(),ButtonType.OK);
				alerteAmande.showAndWait();
			}
			else {
				for(Pret  pret: personne.getLstPrets()) {
					if (!pret.getAmende().equals(null)) {
						booAUneAmande=true;
					}
				}
				if (booAUneAmande) {
					new Alert(AlertType.WARNING,personne.getStrPrenom()+" "+personne.getStrNom()+" ne peut louer de documents en raison d'une amande impayée de "+calculerAmande(personne)+"$",ButtonType.OK);
				}
				else{
					Pret pret= new Pret(document);
					personne.getLstPrets().add(pret);
					alerteConfimation.showAndWait();
				}
			}
		}
			
	}
	public void retournerDocument(Document doc) {
		
	}
	public double calculerAmande(Personne personne) {
		double dblAmande=0;
		for(Pret  pret: personne.getLstPrets()) {
			if (!pret.getAmende().equals(null)) {
				dblAmande+=pret.getAmende().getDblMontant();
			}
		}
		return dblAmande;
	}
	public void payerAmande() {
		
	}
}
