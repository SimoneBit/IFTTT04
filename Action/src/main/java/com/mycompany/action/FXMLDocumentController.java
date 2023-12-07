package com.mycompany.action;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLDocumentController implements Initializable {

    @FXML
    private ListView<String> listView;

    @FXML
    private Label selection;

    @FXML
    void clear(ActionEvent event) {
        listView.getSelectionModel().clearSelection();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] items = {"Java","C#","C","C++","Python"};
        listView.getItems().addAll(items);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        listView.getSelectionModel().selectedItemProperty().addListener(this::selectionChanged);
    }

    private void selectionChanged(ObservableValue<? extends String> Observable, String oldVal, String newVal){
        ObservableList<String> selectedItems = listView.getSelectionModel().getSelectedItems();
        String getSelectedItem = (selectedItems.isEmpty())?"No Selected Item":selectedItems.toString();
        selection.setText(getSelectedItem);
    }
}
