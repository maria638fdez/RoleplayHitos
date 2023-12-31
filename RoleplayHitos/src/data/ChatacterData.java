package data;

import java.util.ArrayList;

import app.Manager;
import items.RoleplayCharacter;

public class ChatacterData {
	
	ArrayList<RoleplayCharacter> all = new ArrayList<RoleplayCharacter>();
	

	public  ChatacterData(Manager manager) {
		
	    RoleplayCharacter character1 = new RoleplayCharacter("Warrior", manager.getDrama());
	    character1.sheet.SetCaracteristicas(4, 4, 4, 4);
	    character1.sheet.SetHabilidades(5, 5, 5, 5, 5, 5, 5);
	    all.add(character1);
	    
	    RoleplayCharacter character2 = new RoleplayCharacter("Archer", manager.getDrama());
	    character2.sheet.SetCaracteristicas(4, 4, 4, 4);
	    character2.sheet.SetHabilidades(5, 5, 5, 5, 5, 5, 5);
	    all.add(character2);
	    
	    RoleplayCharacter character3 = new RoleplayCharacter("Mage", manager.getDrama());
	    character3.sheet.SetCaracteristicas(4, 4, 4, 4);
	    character3.sheet.SetHabilidades(5, 5, 5, 5, 5, 5, 5);
	    all.add(character3);
	}
	
	public ArrayList<RoleplayCharacter> GetAll() {
		return all;
	}
    


}


