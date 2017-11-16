package application.modele;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Amende implements Serializable {
	

	private static final long serialVersionUID = -2697555557344547730L;
	private static int intCompteurNoAmende=1;
	private int intNoAmende;
	private double dblMontant;
	private boolean booRetour = false;		//est l'etat ...false = non-paye
	
	public Amende(LocalDate dateRetourPrevu, LocalDate dateDeRetourEffective, Double dblMontantPenalite,boolean booRetourne) {
		
		Long lngNbJoursRetard=ChronoUnit.DAYS.between(dateDeRetourEffective,dateRetourPrevu);
		this.dblMontant = (lngNbJoursRetard*dblMontantPenalite);
		this.intNoAmende = intCompteurNoAmende;
		this.booRetour= booRetourne;
 }

	public double getDblMontant() {
		return dblMontant;
	}

	public boolean getBooRetour() {
		// TODO Auto-generated method stub
		return booRetour;
	}
}