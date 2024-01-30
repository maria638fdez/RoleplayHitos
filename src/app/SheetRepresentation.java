package app;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import interfaces.Caracteristica;
import interfaces.Habilidad;
import items.Sheet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.Separator;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;


public class SheetRepresentation extends VBox {
	
    public Sheet sheet;
    public TitledPane sheetPane;
    protected TitledPane caracteristicasTitledPane;
    protected TitledPane habilidadesTitledPane;
    
    public TitledPane getCaracteristicasPane(){
    	return caracteristicasTitledPane;
    }
    
    public TitledPane getHabilidadesPane(){
    	return habilidadesTitledPane;
    }
    
	public SheetRepresentation(String name, int drama) {
		
		// Sheet (empty)
		sheet = new Sheet(this, name, drama);
		sheetPane = new TitledPane(name, new Label("Contenido"));
		
		// Panes
		caracteristicasTitledPane = createSecondaryPane("Caracteristicas", sheet.caracteristicas.getAllNames());//getAllNames());
		habilidadesTitledPane = createSecondaryPane("Habilidades", sheet.habilidades.getAllNames());
		
		//Agrupación de paneles
		VBox AttributesPane = new VBox(caracteristicasTitledPane, new Separator(), habilidadesTitledPane);
		sheetPane.setContent(AttributesPane);
    }

	private TitledPane createSecondaryPane(String label, ArrayList<String> allAttributes) {
		TitledPane titledPane = new TitledPane(label, new Label(label));
		ListView<String> listView = new ListView<String>();
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
	
	public int getChecked(ListView<String> view){
		
		MultipleSelectionModel<String> selectionModel = view.getSelectionModel();
		ObservableList<String> selectedItems = selectionModel.getSelectedItems();
		
		if (selectedItems.size()>0) {
			
			String feature =  selectedItems.get(0).toString();									
			return sheet.GetAttribute(feature);
}
		else {
			return 0;
		}
		
	}
	


	public TitledPane getSheetPane() {
		return sheetPane;
	}


}
