package app;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ListView;

import java.util.Optional;
import data.ChatacterData;
import items.RoleplayCharacter;
import items.SheetRetriever;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;


public class MainTable extends Application {

	Master master;
	TurnTracker tracker = new TurnTracker();
	DiceRepresentation dice;
	TextArea console;
	
	
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	
    	master = new Master("Gente");
    	//System.out.println(master.diceset);
    	dice = new DiceRepresentation(master.diceset);

        setWindowSize(primaryStage, 0.8);
        
        // Insert console
        console = setConsole();

        // Create the main pane sections
        VBox left1 = new VBox();
        left1.getChildren().add(tracker.tracker);
        VBox left2 = new VBox(console);
        VBox rightPane = new VBox(dice.diceBox);
        
        // Insert role-play sheets
        ChatacterData party = new ChatacterData(master);
        
        // Manage how the sheets behave when clicked
        manageClicks(rightPane, party);

        // Create global pane
       setScene(primaryStage, SetPanes(left1, left2, rightPane));

    }


	private TextArea setConsole() {
		TextArea console = new TextArea();
        console.setEditable(false);
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) {
                console.appendText(String.valueOf((char) b));
            }
        };
        System.setOut(new PrintStream(out));
		return console;
	}

	private void manageClicks(VBox rightPane, ChatacterData party) {
		
		//Displays all character sheets
		Accordion accordion = new Accordion();
        for (RoleplayCharacter currentSheet : party.GetAll()) {
            TitledPane currentPane = currentSheet.getSheetPane();
            currentPane.setOnMouseClicked(event -> {
                if (currentPane.isExpanded()) {
                	if (event.getButton() == MouseButton.SECONDARY) { 
                		rightClickAction(currentSheet);
                    }
                    else {
                    	leftClickAction(currentSheet);
                    }
                } 
                else {
                	doubleClickActions(currentSheet);
                    event.consume();
                }
            });
            accordion.getPanes().add(currentPane);
        }
        rightPane.getChildren().add(accordion);
	}

	private void doubleClickActions(RoleplayCharacter currentCharacter) {

		// START: Get current pane
		TitledPane currentPane = currentCharacter.getSheetPane();
    	TitledPane caracteristicasPane = currentCharacter.getCaracteristicasPane();
    	TitledPane habilidadesPane = currentCharacter.getHabilidadesPane();
    	SheetRetriever sheet = currentCharacter.sheet;
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
	    ChoiceDialog<String> dialogClick = new ChoiceDialog<>(opciones.get(0), opciones);
	    dialogClick.setTitle("Acciones");
	    dialogClick.setResizable(false);
	    dialogClick.getDialogPane().setExpanded(true);
	    Optional<String> interaction = dialogClick.showAndWait();
	    
	    if (interaction.isPresent()) {
	    	
	    	int C;
        	int selectedCaracteristica;
        	int selectedHabilidad;
        	int total;
        
        	
	        switch(interaction.get()) {
	        
	            case "Iniciativa":
	            	C = master.roll_C();
	            	dice.updateDice();
	            	int iniciativaTotal = sheet.GetIniciativa() + C;
	            	dice.updateDice();
	            	System.out.println( "Iniciativa: " + iniciativaTotal+ " ("+ sheet.GetIniciativa() +" + "+ C +")");               
	                break;
	            case "Aguante":
	            	C = master.roll_C();
	            	dice.updateDice();
	            	int aguante = sheet.getAguante() + C;
	            	dice.updateDice();
	            	System.out.println("Aguante: " + aguante+" ("+sheet.getAguante() +" + "+ C +")");
	                break;
	            case "Caracteristica unica":
	            	C = master.roll_C();
	            	dice.updateDice();
	            	selectedCaracteristica = currentCharacter.getChecked(listViewCaracteristicas);
	            	total = selectedCaracteristica + master.getAverageValue() +  C;
	            	dice.updateDice();
	            	System.out.println(total+" ("+selectedCaracteristica +" + "+ C +")");
	                break;
	            case "Caracteristica con habilidad":
	            	C = master.roll_C();
	            	dice.updateDice();
	            	selectedCaracteristica = currentCharacter.getChecked(listViewCaracteristicas);
	            	selectedHabilidad = currentCharacter.getChecked(listViewHabilidades);
	            	total = selectedCaracteristica + selectedHabilidad +  C;
	            	dice.updateDice();
	            	System.out.println(total+" ("+selectedCaracteristica+" + "+selectedHabilidad+" + "+ C +")");
	                break;	                
	            default:
	                break;
	        }
	    }
	}

	
	private void leftClickAction(RoleplayCharacter currentCharacter) {
		TitledPane currentPane = currentCharacter.getSheetPane();
		
	}

	private void rightClickAction(RoleplayCharacter currentCharacter) {
		
		TitledPane currentPane = currentCharacter.getSheetPane();
		currentPane.expandedProperty().set(true);
		
	    // Create options
	    List<String> opciones = new ArrayList<>();
	    opciones.add("Atacar a...");
	    opciones.add("Recibir ayuda de...");
	    opciones.add("Sanar a...");
	    opciones.add("Enfrentarse a...");
	    
	    // Create dialog
	    ChoiceDialog<String> dialogClick = new ChoiceDialog<>(opciones.get(0), opciones);
	    dialogClick.setTitle("Acciones conjuntas");
	    dialogClick.setResizable(false);
	    dialogClick.getDialogPane().setExpanded(true);
	    
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

//	    TitledPane caracteristicasPane = currentCharacter.getCaracteristicasPane();
//	    TitledPane habilidadesPane = currentCharacter.getHabilidadesPane();
//	    	
//		    
//	    	ListView<String> listViewCaracteristicas = (ListView<String>) caracteristicasPane.getContent();
//	    	ListView<String> listViewHabilidades = (ListView<String>) habilidadesPane.getContent();
//		    

//		    

		
	}

	private void setScene(Stage primaryStage, SplitPane splitPane) {
		// Scene configuration
        Scene scene = new Scene(splitPane, primaryStage.getWidth(), primaryStage.getHeight());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mi Aplicación JavaFX");
        primaryStage.centerOnScreen();
        primaryStage.show();
	}
    
    private void setWindowSize(Stage stage, double percentage) {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        double windowWidth = bounds.getWidth() * percentage;
        double windowHeight = bounds.getHeight() * percentage;

        stage.setWidth(windowWidth);
        stage.setHeight(windowHeight);
    }

	private SplitPane SetPanes(VBox left1, VBox left2, VBox rightPane) {
        SplitPane leftPane = new SplitPane();
        leftPane.setOrientation(javafx.geometry.Orientation.VERTICAL);
        leftPane.getItems().setAll(left1, left2);
        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(leftPane, rightPane);
		return splitPane;
	}
}
