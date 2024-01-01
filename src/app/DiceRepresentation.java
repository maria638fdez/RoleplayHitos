package app;
import items.DiceSet;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DiceRepresentation {
	
	public HBox diceBox;
	private Canvas die1 = new Canvas(50, 50);
	private Canvas die2 = new Canvas(50, 50);
	private Canvas die3 = new Canvas(50, 50);
	private DiceSet dice = new DiceSet();
	

	public DiceRepresentation(DiceSet dice) {
		
		this.dice = dice;
		diceBox = new HBox();


        diceBox.setAlignment(Pos.CENTER);
        diceBox.getChildren().addAll(die1, die2, die3);
        
        this.dice = dice;
        updateDice();
        
	}
	
	public void updateDice() {
		
		die1 = drawDie(die1, dice.m());
        die2 = drawDie(die2, dice.C());
        die3 = drawDie(die3, dice.M());
	}
	
    private Canvas drawDie(Canvas romboid, int number) {
        drawDieGeometry(romboid);
        writeNumberOnDie(number, romboid);
		return romboid;
    }
    
	private void drawDieGeometry(Canvas canvas) {
		
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
	}
	
	private void writeNumberOnDie(int number, Canvas dieCanvas) {
		
		// Get
		String numberString = Integer.toString(number);
		GraphicsContext romboid = dieCanvas.getGraphicsContext2D();
        double width = dieCanvas.getWidth();
        double height = dieCanvas.getHeight();
        
        
        //Set color
        if (number==10) 		{romboid.setFill(Color.GREEN);}
        else if (number<=1)		{romboid.setFill(Color.RED);}
        else 					{romboid.setFill(Color.BLACK);}
        
        
        Font font = Font.font("Arial", FontWeight.BOLD, 20);
        romboid.setFont(font);
        
        double textWidth = romboid.getFont().getSize();
        double textHeight = romboid.getFont().getSize();
        romboid.fillText(numberString, (width - textWidth)/2, (height + textHeight)/2);
	}
	
	public int roll_m() {
		this.dice.roll();
		int dice = this.dice.M();
		return dice;
	}
	
	
	public int roll_M() {
		this.dice.roll();
		int dice = this.dice.M();
		return dice;
	}
	

	public int roll_C() {
		this.dice.roll();
		int dice = this.dice.C();
		return dice;
	}



}
