package application.controleur;

import application.modele.Document;
import application.modele.Etat;
import application.modele.ListeDocuments;
import application.modele.ListePersonnes;
import application.modele.Personne;
import application.modele.Pret;
import application.modele.TypeDocument;
import application.modele.TypePersonne;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class GestionPrets {
	public void emprunterDocument(Personne personne, Document document){
		Alert alerteTropDoc ;
		Alert alerteConfimation = new Alert(AlertType.CONFIRMATION,personne.getStrPrenom()+" "+personne.getStrNom()+" à louer "+document.getStrTitre(),ButtonType.OK);
		Alert alerteDocLoue = new Alert(AlertType.WARNING,"Le document "+document.getStrCodeDocument()+" est déja loué",ButtonType.OK);
		Alert alerteAmende ;
		int intDocTypeEmprunte= 0;
		boolean booAUneAmande=false;
		
		//vérifier disponibilité du document 
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
			for(Pret pret : personne.getLstPrets()){  
				if (pret.getDoc()!= null){
					if (pret.getDoc().getStrCodeDocument().substring(0, 3).equals(typeEmprunte)){
					intDocTypeEmprunte++;
					}
				}
			}
			if(intDocTypeEmprunte>=typeEmprunte.getIntNbEmprunte()) {
				alerteTropDoc= new Alert(AlertType.WARNING,"Vous avec déja loué le nombre maximal de documents de type: "+typeEmprunte.getStrNomType(),ButtonType.OK);
				alerteTropDoc.showAndWait();
			}
			else {
				for(Pret  pret: personne.getLstPrets()) {
					if (!pret.getAmende().equals(null)) {
						booAUneAmande=true;
					}
				}
				if (booAUneAmande) {
					alerteAmende =new Alert(AlertType.WARNING,personne.getStrPrenom()+" "+personne.getStrNom()+" ne peut louer de documents en raison d'une amande impayée de "+calculerAmande(personne)+"$",ButtonType.OK);
					alerteAmende.showAndWait();
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
		if (!doc.getEtatDoc().equals(Etat.DISPONIBLE)){
			doc.setEtatDoc(Etat.DISPONIBLE);
			           //ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent).removeIf(personne -> personne.getLstPrets().stream().filter(f->f.equals(doc)).findFirst().equals(personne));     retirer une personne selon un doc 
			           //ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent).forEach(f->f.getLstPrets().removeIf(j->j.equals(doc))); RETirer un pret selon un doc 
			ListePersonnes.getInstance().mapPersonne.get(TypePersonne.Adherent).forEach(f->f.getLstPrets().stream().filter(j->j.getDoc().equals(doc)).findFirst().get().retourDoc());
			
		}
		else{
			Alert alerteDocumentNonEmprumte=new Alert(AlertType.WARNING,"Le document "+doc .getStrTitre()+" est déja emprunté",ButtonType.OK);
			alerteDocumentNonEmprumte.showAndWait();
		}
	}
	public double calculerAmande(Personne personne) {
		double dblAmende=0;
		for(Pret  pret: personne.getLstPrets()) {
			if (!pret.getAmende().equals(null)) {
				dblAmende+=pret.getAmende().getDblMontant();
			}
		}
		return dblAmende;
	}
	public void payerAmande(Personne personne) {
		Alert alerteAucuneAmende;
		double dblAmende= calculerAmande(personne);
		if (dblAmende!=0){
			for (Pret pret : personne.getLstPrets()){
				if (pret.getDoc().equals(null)){
					pret=null;
				}
			}
			alerteAucuneAmende = new Alert(AlertType.INFORMATION,personne.getStrPrenom()+" n'a aucune amende à rembourser",ButtonType.OK);
			alerteAucuneAmende.showAndWait();
		}
		else {
			alerteAucuneAmende = new Alert(AlertType.INFORMATION,personne.getStrPrenom()+" n'a aucune amende à rembourser",ButtonType.OK);
			alerteAucuneAmende.showAndWait();
		}
	}
}
