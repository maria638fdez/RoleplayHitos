package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;

public class TurnTrackerRepresentation implements ActionListener {
	
	TitledPane tracker;
    private Map<String, Integer> turnOrder;
    private JList<String> list;
    private JButton resetButton;
    private JButton startButton;
	
	public TurnTrackerRepresentation() {
		
		
		resetTracker();

		turnOrder = new LinkedHashMap<String, Integer>();
		list = new JList<String>();

        
        resetButton = new JButton("Reset");
        resetButton.addActionListener(this);
        startButton = new JButton("Start");
        startButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(resetButton);
        buttonPanel.add(startButton);


	}
	
	void resetTracker(){
        HBox panel = new HBox();
        
        // Crear los botones
        Button button1 = new Button("Reset");
        Button button2 = new Button("Start");
        
        panel.getChildren().addAll(new Label("Acciones: "), button1, button2);
        panel.setAlignment(Pos.CENTER);
        panel.setSpacing(10);
        
        tracker = new TitledPane("Turn tracker", panel);
		tracker.setCollapsible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
