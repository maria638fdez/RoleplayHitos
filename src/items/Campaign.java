package items;
import java.util.EnumMap;

public class Campaign {
	
	public enum levelName {
	   	Titanes,
	   	Heroes,
	   	Protagonistas,
	   	Gente;
	}

    public enum levelData{
    	maximo,
    	puntos_caracteristicas,
    	puntos_habilidades,
    	drama;
    }
    
    public DiceSet diceset;
    public levelName campaignLevel;
    public EnumMap<levelData, Integer> campaignValues;

    public Campaign() {
    	 
    	this("Corriente");
    }
    
    public Campaign(String stringLevel) {
    	
    	diceset = new DiceSet();
    	campaignValues = new EnumMap<levelData, Integer>(levelData.class);
    	
    	//Set campaign level
    	campaignLevel = levelName.valueOf(stringLevel);
    	try {
	    	setCampaignLevel(campaignLevel);
	    } catch (IllegalArgumentException e) {
	        System.err.println("El String " + stringLevel + " no es un nivel de poder");
	    }
    	
    }
    
    public Integer getCampaign_PuntosCaracteristica() {
    	return this.campaignValues.get(levelData.puntos_caracteristicas);
    }
    
    public Integer getCampaign_PuntosHabilidades() {
    	return this.campaignValues.get(levelData.puntos_habilidades);
    }
    
    public Integer getCampaign_PuntosMaximos() {
    	return this.campaignValues.get(levelData.maximo);
    }
    
    public Integer getCampaign_Drama() {
    	return this.campaignValues.get(levelData.drama);
    }

	private void setCampaignLevel(levelName level) {
		switch (level) {
			case Titanes:		{	setCampaignValues(30,12,50,2); 		break;}
			case Heroes: 		{	setCampaignValues(24,10,40,3); 		break;}
			case Protagonistas:	{	setCampaignValues(18,9,40,4); 		break;}
			case Gente: 		{	setCampaignValues(16,7,35,5); 		break;}
		default:
			throw new IllegalArgumentException("Unexpected value: " + level);
		}
	}


	private void setCampaignValues(int caracteristicas, int maximo, int habilidades, int drama) {
		campaignValues.put(levelData.puntos_caracteristicas, caracteristicas);
		campaignValues.put(levelData.maximo, maximo);
		campaignValues.put(levelData.puntos_habilidades, habilidades);
		campaignValues.put(levelData.drama, drama);
	}
	
	

	
	public int getAverageValue() {
		return Math.floorDiv(campaignValues.get(levelData.puntos_habilidades), 4);
	}
	
	public int getDrama() {
		return campaignValues.get(levelData.drama);
	}


}
