package app;
import java.util.EnumMap;

import items.DiceSet;
import items.RoleplayCharacter;
import items.SheetRetriever.Caracteristica;

public class Manager {
	
	public enum levelName {
	   	Titanes,
	   	Heroes,
	   	Protagonistas,
	   	Gente;
	}
	

    public enum levelRules{
    	maximo,
    	puntos_caracteristicas,
    	puntos_habilidades,
    	drama;
    }
    
    
    DiceSet diceset = new DiceSet();
    public EnumMap<levelRules, Integer> campaignValues = new EnumMap<levelRules, Integer>(levelRules.class);

    public Manager() {
    	this("Titanes");
    }
    
    public Manager(String stringLevel) {
    	levelName level = levelName.valueOf(stringLevel);
    	try {
	    	setCampaignLevel(level);
	    } catch (IllegalArgumentException e) {
	        System.err.println("El String " + stringLevel + " no es un nivel de poder");
	    }
    }

	private void setCampaignLevel(levelName level) {
		switch (level) {
			case Titanes:		{	setCampaignValues(30,12,50,2);	break;}
			case Heroes: 		{	setCampaignValues(24,10,40,3);	break;}
			case Protagonistas:	{	setCampaignValues(18,9,40,4);	break;}
			case Gente: 		{	setCampaignValues(16,7,35,5);	break;}
		default:
			throw new IllegalArgumentException("Unexpected value: " + level);
		}
	}


	private void setCampaignValues(int caracteristicas, int maximo, int habilidades, int drama) {
		campaignValues.put(levelRules.puntos_caracteristicas, caracteristicas);
		campaignValues.put(levelRules.maximo, maximo);
		campaignValues.put(levelRules.puntos_habilidades, habilidades);
		campaignValues.put(levelRules.drama, drama);
	}
	

	public int rollSingleFeature(int feature) {
		diceset.roll();
		int dice = diceset.C();
		return feature + dice;
	}
	
	public int getAverageValue() {
		return Math.floorDiv(campaignValues.get(levelRules.puntos_habilidades), 4);
	}
	
	public int getDrama() {
		return campaignValues.get(levelRules.drama);
	}


}
