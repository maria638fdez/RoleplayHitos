package data;

import java.util.ArrayList;

import app.SheetRepresentation;
import items.Campaign;
import items.Sheet;

public class ChatacterCollection {
	
	ArrayList<SheetRepresentation> charachers = new ArrayList<SheetRepresentation>();
	

	public  ChatacterCollection(Campaign campaign) {
		
	    SheetRepresentation character1 = new SheetRepresentation("Warrior", 0);
	    character1.sheet.SetCaracteristicas(4, 4, 4, 4);
	    character1.sheet.SetHabilidades(5, 5, 5, 5, 5, 5, 5);
	    character1.sheet.setDrama(campaign.getCampaign_Drama());
	    if(character1.sheet.validateSheet(campaign)) { charachers.add(character1); }
	    
	    SheetRepresentation character2 = new SheetRepresentation("Archer", 0);
	    character2.sheet.SetCaracteristicas(4, 4, 4, 4);
	    character2.sheet.SetHabilidades(5, 5, 5, 5, 5, 5, 5);
	    character2.sheet.setDrama(campaign.getCampaign_Drama());
	    if(character2.sheet.validateSheet(campaign)) { charachers.add(character2); }
	    
	    SheetRepresentation character3 = new SheetRepresentation("Mage", 0);
	    character3.sheet.SetCaracteristicas(4, 4, 4, 4);
	    character3.sheet.SetHabilidades(5, 5, 5, 5, 5, 5, 5);
	    character3.sheet.setDrama(campaign.getCampaign_Drama());
	    if(character3.sheet.validateSheet(campaign)) { charachers.add(character3); }
	}
	
	public ArrayList<SheetRepresentation> GetAll() {
		return charachers;
	}
	

    
    


}


