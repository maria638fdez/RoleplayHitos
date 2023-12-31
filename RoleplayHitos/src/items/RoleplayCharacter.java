package items;


import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.Separator;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;


public class RoleplayCharacter extends VBox {
	
    private String name;
    public SheetRetriever sheet;
    public TitledPane sheetPane;
    protected TitledPane caracteristicasTitledPane;
    protected TitledPane habilidadesTitledPane;
    
	public RoleplayCharacter(String name, int drama) {
		
		// Sheet (empty)
		sheet = new SheetRetriever(name, drama);
		sheetPane = new TitledPane(name, new Label("Contenido"));
		
		// Panes
		caracteristicasTitledPane = createSecondaryPane("Caracteristicas", sheet.getAllCaracteristicas());
		TitledPane habilidadesTitledPane = createSecondaryPane("Habilidades", sheet.getAllHabilidades());
		
		//Agrupación de paneles
		VBox AttributesPane = new VBox(caracteristicasTitledPane, new Separator(), habilidadesTitledPane);
		sheetPane.setContent(AttributesPane);
    }

	private TitledPane createSecondaryPane(String label, ArrayList<String> allAttributes) {
		TitledPane titledPane = new TitledPane(label, new Label(label));
		ListView<String> listView = new ListView<>();
		MultipleSelectionModel<String> selectionModel = listView.getSelectionModel();
		ObservableList<String> list = FXCollections.observableArrayList();
		for (String attribute : allAttributes) {
			list.add(attribute);
		}
		listView.setItems(list);
		listView.setPrefHeight(list.size() * 24);
		titledPane.setContent(listView);
		titledPane.setCollapsible(false);
		return titledPane;
	}
	
	
	public int getCheckedCaracteristica(){
		ListView<String> content = (ListView<String>) caracteristicasTitledPane.getContent();
		ListView<String> view = content;
		MultipleSelectionModel<String> selectionModel = view.getSelectionModel();
		ObservableList<String> selectedItems = selectionModel.getSelectedItems();
		String s = selectedItems.toString();
		//int value = sheet.GetCaracteristica(s);
		return 0;
	}
	
	public String getCheckedHabilidad(){
		return sheetPane.getText();
	}

	public TitledPane getSheetPane() {
		return sheetPane;
	}


}
