package app;
import data.ChatacterCollection;
import items.Campaign;
import items.Table;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;


public class MainTable extends Application {
	
	Table table;
	Campaign campaign;
	TurnTrackerRepresentation tracker;
	DiceRepresentation dice;
	ConsoleArea console;
	ChatacterCollection characters;
	
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	
    	//Initialize
    	
    	campaign = new Campaign("Gente");
    	dice = new DiceRepresentation(campaign.diceset);
    	table = new Table(dice);
    	console = new ConsoleArea();
    	tracker = new TurnTrackerRepresentation();
    	characters = new ChatacterCollection(campaign);
    	
        // Insert role-play sheets
		CharacterCollectionRepresentation characterAccordion = new CharacterCollectionRepresentation(characters.GetAll(), campaign, dice, table);

        // Create the main pane sections
        VBox left_up_pane = new VBox(tracker.tracker);
        VBox left_down_pane = new VBox(console);
        VBox right = new VBox(dice.diceBox, characterAccordion);
        
        // Create global pane
		setScene(primaryStage, SetPanes(left_up_pane, left_down_pane, right));

    }


	private void setScene(Stage primaryStage, SplitPane splitPane) {
		setWindowSize(primaryStage, 0.8);
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
