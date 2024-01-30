package items;

import app.DiceRepresentation;
import app.SheetRepresentation;

public class Table {
	
	DiceRepresentation dice;
	
	public Table(DiceRepresentation dice) {
		this.dice = dice;
	}

	public void rollIniciativa(Sheet sheet) {
    	int C = dice.roll_C();
    	dice.updateDice();
    	int iniciativaTotal = sheet.GetIniciativa() + C;
    	dice.updateDice();
    	System.out.println( "Iniciativa: " + iniciativaTotal+ " ("+ sheet.GetIniciativa() +" + "+ C +")"); 
	}
	
	public void rollAguante(Sheet sheet) {
		int C = dice.roll_C();
    	dice.updateDice();
    	int aguante = sheet.getAguante() + C;
    	dice.updateDice();
    	System.out.println("Aguante: " + aguante+" ("+sheet.getAguante() +" + "+ C +")");
	}

	public void rollCaracteristicaUnica(Sheet sheet, int caracteristica, int averageValue) {
		int C = dice.roll_C();
    	dice.updateDice();
    	int total = caracteristica + averageValue +  C;
    	dice.updateDice();
    	System.out.println(total+" ("+caracteristica +" + "+ C +")");
	}

	public void rollCaracteristicaHabilidad(Sheet sheet, int caracteristica, int habilidad, int averageValue) {
    	int C = dice.roll_C();
    	dice.updateDice();
    	int total = caracteristica + habilidad +  C;
    	dice.updateDice();
    	System.out.println(total+" ("+caracteristica+" + "+habilidad+" + "+ C +")");
		
	}
	
	

}
