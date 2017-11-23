package application.modele;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Amende implements Serializable {
	
	private static final long serialVersionUID = -2697555557344547730L;
	private double dblMontant;
	private boolean booRetour = false;		
	
	public Amende(LocalDate dateRetourPrevu, LocalDate dateDeRetourEffective, Double dblMontantPenalite,boolean booRetourne) {
		
		Long lngNbJoursRetard=Math.abs(ChronoUnit.DAYS.between(dateDeRetourEffective,dateRetourPrevu));
		this.dblMontant = (lngNbJoursRetard*dblMontantPenalite);
		this.booRetour= booRetourne;
 }

	public double getDblMontant() {
		return dblMontant;
	}

	public boolean getBooRetour() {
		return booRetour;
	}
}