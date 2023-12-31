package app;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.control.ChoiceDialog;
import java.util.Optional;
import data.ChatacterData;
import items.RoleplayCharacter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Accordion;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;


public class MainPannel extends Application {

	Manager manager = new Manager("Gente");
	
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {

        setWindowSize(primaryStage, 0.8);
        
        // Insert console
        TextArea console = setConsole();
        
        // Insert dice box
        HBox diceBox = new HBox();
        setDiceBox(diceBox);
        
        // Create the main pane sections
        VBox left1 = new VBox();
        VBox left2 = new VBox(console);
        VBox rightPane = new VBox(diceBox);
        
        // Insert role-play sheets
        ChatacterData party = new ChatacterData(manager);
        
        // Manage how the sheets behave when clicked
        manageClicks(rightPane, party);

        // Create global pane
       setScene(primaryStage, SetPanes(left1, left2, rightPane));

    }

	private void setDiceBox(HBox diceBox) {
		
		diceBox.setAlignment(Pos.CENTER);
		
		Canvas rombo1 = createRomboCanvas();
        Canvas rombo2 = createRomboCanvas();
        Canvas rombo3 = createRomboCanvas();
        
        diceBox.getChildren().addAll(rombo1, rombo2, rombo3);
        
	}
	
    private Canvas createRomboCanvas() {
        Canvas canvas = new Canvas(50, 50);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        double width = canvas.getWidth();
        double height = canvas.getHeight();

        // Draw 
        gc.beginPath();
        gc.moveTo(width / 2, 0);
        gc.lineTo(width, height / 2);
        gc.lineTo(width / 2, height);
        gc.lineTo(0, height / 2);
        gc.closePath();
        gc.setFill(Color.BLANCHEDALMOND);
        gc.fill();
        gc.stroke();
        
        // Write number
        writeNumber(gc, width, height);

		return canvas;
    }

	private void writeNumber(GraphicsContext gc, double width, double height) {
		gc.setFill(Color.BLACK);
        Font font = Font.font("Arial", FontWeight.BOLD, 20);
        gc.setFont(font);

        String number = "10";
        
        double textWidth = gc.getFont().getSize();
        double textHeight = gc.getFont().getSize();
        gc.fillText(number, (width - textWidth)/2, (height + textHeight)/2);
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
                	doubleClickAction(currentSheet);
                    event.consume();
                }
            });
            accordion.getPanes().add(currentPane);
        }
        rightPane.getChildren().add(accordion);
	}


	private void doubleClickAction(RoleplayCharacter currentCharacter) {

		// Get current pane
		TitledPane currentPane = currentCharacter.getSheetPane();
	    System.out.println("Doble click: " + currentPane.getText());
	    currentPane.expandedProperty().set(true);
	    
	    // Create options
	    List<String> opciones = new ArrayList<>();
	    opciones.add("Iniciativa");
	    opciones.add("Aguante");
	    opciones.add("Caracteristica");
	    
	    // Create dialog
	    ChoiceDialog<String> dialogo = new ChoiceDialog<>(opciones.get(0), opciones);
	    dialogo.setTitle("Menú de opciones");
	    dialogo.setResizable(false);
	    dialogo.getDialogPane().setExpanded(true);
	    Optional<String> resultado = dialogo.showAndWait();
	    
	    if (resultado.isPresent()) {
	        switch(resultado.get()) {
	            case "Iniciativa":
	            	System.out.println("Iniciativa: " + manager.rollSingleFeature(currentCharacter.sheet.GetIniciativa()));               
	                break;
	            case "Aguante":
	            	System.out.println("Aguante" + manager.rollSingleFeature(currentCharacter.sheet.getAguante()));
	                break;
	            case "Caracteristica":
	            	int selectedValue = currentCharacter.getCheckedCaracteristica();
	            	int total = selectedValue + manager.getAverageValue();
	            	System.out.println(total);
	            	//System.out.println("Característica" + manager.rollSingleFeature(currentSheet.getAguante()));
	                break;
	            default:
	                break;
	        }
	    }
	}


	private void leftClickAction(RoleplayCharacter currentSheet) {
		TitledPane pane = currentSheet.getSheetPane();
		System.out.println("Personaje seleccionado: " + pane.getText());
	}

	private void rightClickAction(RoleplayCharacter currentSheet) {
		TitledPane pane = currentSheet.getSheetPane();
		System.out.println("Clic derecho detectado en " + pane.getText());
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
