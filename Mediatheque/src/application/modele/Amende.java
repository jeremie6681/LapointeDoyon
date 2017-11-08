package application.modele;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.omg.DynamicAny.DynAnyHelper;

public class Amende {
	
	private static int intCompteurNoAmende=1;
	private int intNoAmende;
	private double dblMontant;
	private boolean booPaye = false;		//est l'etat ...false = non-paye
	
	public Amende(LocalDate dateRetourPrevu, LocalDate dateDeRetourEffective, Double dblMontantPenalite) {
		
		Long lngNbJoursRetard=ChronoUnit.DAYS.between(dateRetourPrevu,dateRetourPrevu);
		this.dblMontant = (lngNbJoursRetard*dblMontantPenalite);
		this.intNoAmende = intCompteurNoAmende;
 }

	public double getDblMontant() {
		return dblMontant;
	}
}