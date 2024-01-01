package app;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import items.Campaign;
import items.Sheet;
import items.Table;
import javafx.scene.control.Accordion;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseButton;

public class CharacterCollectionRepresentation extends Accordion {
	
	Table table;
	
	public CharacterCollectionRepresentation(ArrayList<SheetRepresentation> sheetList, Campaign campaign, DiceRepresentation dice, Table table) {
		
		this.table = table; 

		// For each character of the list, decide action
        for (SheetRepresentation currentSheet : sheetList) {
            TitledPane currentPane = currentSheet.getSheetPane();
            currentPane.setOnMouseClicked(event -> {
                if (currentPane.isExpanded()) {
                	if (event.getButton() == MouseButton.SECONDARY) { 
                		rightClickAction(currentSheet);
                    }
                    else {
                    	leftClickAction(currentSheet, campaign, dice);
                    }
                } 
                else {
                	doubleClickActions(currentSheet, campaign, dice);
                    event.consume();
                }
            });
            this.getPanes().add(currentPane);
        }        
	}
		
	

private void doubleClickActions(SheetRepresentation currentCharacter, Campaign campaign, DiceRepresentation dice) {

	// START: Get current pane
	TitledPane currentPane = currentCharacter.getSheetPane();
	TitledPane caracteristicasPane = currentCharacter.getCaracteristicasPane();
	TitledPane habilidadesPane = currentCharacter.getHabilidadesPane();
	Sheet sheet = currentCharacter.sheet;
    currentPane.expandedProperty().set(true);
	ListView<String> listViewCaracteristicas = (ListView<String>) caracteristicasPane.getContent();
	ListView<String> listViewHabilidades = (ListView<String>) habilidadesPane.getContent();
    
    // Create options
    List<String> opciones = new ArrayList<>();
    opciones.add("Iniciativa");
    opciones.add("Aguante");
    opciones.add("Caracteristica unica");
    opciones.add("Caracteristica con habilidad");
    
    // Create dialog
    ChoiceDialog<String> dialogClick = extracted(opciones);
    Optional<String> interaction = dialogClick.showAndWait();
    
    if (interaction.isPresent()) {
    	
        switch(interaction.get()) {
        
            case "Iniciativa":
            	table.rollIniciativa(sheet);               
                break;
            case "Aguante":
            	table.rollAguante(sheet);
                break;
            case "Caracteristica unica":
            	table.rollCaracteristicaUnica(sheet, currentCharacter.getChecked(listViewCaracteristicas), campaign.getAverageValue());
                break;
            case "Caracteristica con habilidad":
            	table.rollCaracteristicaHabilidad(sheet, currentCharacter.getChecked(listViewCaracteristicas), currentCharacter.getChecked(listViewHabilidades), campaign.getAverageValue());
                break;	                
            default:
                break;
        }
    }
}


private void leftClickAction(SheetRepresentation currentCharacter, Campaign campaign, DiceRepresentation dice) {
	TitledPane currentPane = currentCharacter.getSheetPane();
}

private void rightClickAction(SheetRepresentation currentCharacter) {
	
	TitledPane currentPane = currentCharacter.getSheetPane();
	currentPane.expandedProperty().set(true);
	
    // Create options
    List<String> opciones = new ArrayList<>();
    opciones.add("Atacar a...");
    opciones.add("Recibir ayuda de...");
    opciones.add("Sanar a...");
    opciones.add("Enfrentarse a...");
    
    // Create dialog
    ChoiceDialog<String> dialogClick = extracted(opciones);
    
    Optional<String> interaction = dialogClick.showAndWait();
    
    if (interaction.isPresent()) {
        switch(interaction.get()) {
        
	        case "Atacar a...":
	            System.out.println("Ataque");  
	            break;
	        case "Recibir ayuda de...":
	        	System.out.println("Ayuda"); 
	            break;
	        case "Sanar a...":
	        	System.out.println("Sanar");
	        case "Enfrentarse a...":
	        	System.out.println("Enfrentar");
	            break;	                
	        default:
	            break;
    }
}    


}



private ChoiceDialog<String> extracted(List<String> opciones) {
	ChoiceDialog<String> dialogClick = new ChoiceDialog<>(opciones.get(0), opciones);
    dialogClick.setTitle("Acciones conjuntas");
    dialogClick.setResizable(false);
    dialogClick.getDialogPane().setExpanded(true);
	return dialogClick;
}}
