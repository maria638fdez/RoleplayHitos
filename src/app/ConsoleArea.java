package app;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

public class ConsoleArea extends VBox {

    private TextArea textArea;
    private OutputStream outputStream;
    private LinkedList<String> history;

    public ConsoleArea() {
        history = new LinkedList<>();
        textArea = new TextArea();
        textArea.setEditable(false);

        outputStream = new OutputStream() {
            @Override
            public void write(int b) {
                String text = String.valueOf((char) b);
                textArea.appendText(text);
                addToHistory(text);
            }
        };

        try {
            System.setOut(new PrintStream(outputStream, true, StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Button historyButton = new Button("Historial");
        historyButton.setOnAction(event -> showHistory());

        Button clearButton = new Button("Limpiar");
        clearButton.setOnAction(event -> clearConsole());

        HBox buttonBox = new HBox(10, historyButton, clearButton);
        getChildren().addAll(textArea, buttonBox);
    }

    private void addToHistory(String text) {
        history.add(text);
    }

    private void showHistory() {
        Stage historyStage = new Stage();
        historyStage.initModality(Modality.APPLICATION_MODAL);
        historyStage.setTitle("Historial de Consola");

        TextArea historyTextArea = new TextArea();
        historyTextArea.setEditable(false);

        for (String entry : history) {
            historyTextArea.appendText(entry);
        }

        historyStage.setScene(new javafx.scene.Scene(new VBox(historyTextArea), 400, 300));
        historyStage.showAndWait();
    }
    private void clearConsole() {
        textArea.clear();
        history.clear();
    }
}