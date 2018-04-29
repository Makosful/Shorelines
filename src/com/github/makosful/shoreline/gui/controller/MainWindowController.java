/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.gui.controller;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.controlsfx.control.CheckListView;

/**
 * FXML Controller class
 *
 * @author Storm
 */
public class MainWindowController implements Initializable {

    //<editor-fold defaultstate="collapsed" desc="Split Pane Descriptions">
    @FXML
    private Color x211;
    @FXML
    private Font x111;
    @FXML
    private Color x2;
    @FXML
    private Font x1;
    @FXML
    private Color x21;
    @FXML
    private Font x11;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Labels">
    @FXML
    private Label lblSiteName;
    @FXML
    private Label lblAssetSerialNo;
    @FXML
    private Label lblExtWorkOrderID;
    @FXML
    private Label lblSystemStatus;
    @FXML
    private Label lblUserStatus;
    @FXML
    private Label lblCreatedOn;
    @FXML
    private Label lblCreatedBy;
    @FXML
    private Label lblPriority;
    @FXML
    private Label lblStatus;
    @FXML
    private Label lblEarliestStart;
    @FXML
    private Label lblLatestStart;
    @FXML
    private Label lblLatestFinish;
    @FXML
    private Label lblEstimatedTime;
    @FXML
    private Label lblNameDescription;
    @FXML
    private Label lblOrderType;
    //</editor-fold>

    @FXML
    private CheckListView<String> chklistSelectData;
    @FXML
    private ListView<String> listViewSorted;
    @FXML
    private Button btnMoveUp;
    @FXML
    private Button btnMoveDown;
    @FXML
    private Button btnConvert;
    @FXML
    private ColumnConstraints gridOutputColumn;

    private Boolean movable;
    private Integer currentIndex;

    ObservableList<String> strings = FXCollections.observableArrayList("One 1", "Two 2", "Three 3", "Four 4", "Five 5", "Six 6", "Seven 7", "Eight 8", "Nine 9", "Ten10");
    ObservableList<String> selectedStrings = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        chklistSelectData.setItems(strings);

        listViewSorted.setItems(selectedStrings);

        AddListeners();
    }

    @FXML
    private void handleMoveItemUp(ActionEvent event) {
        if (movable) {
            // Gets the two indexes
            currentIndex = listViewSorted.getSelectionModel().getSelectedIndex();
            int prevIndex = currentIndex - 1;

            //swaps 2 parameters, being the new and old indexes.
            Collections.swap(selectedStrings, currentIndex, prevIndex);

            //requests the ListView to select the current
            listViewSorted.getSelectionModel().select(prevIndex);
            listViewSorted.requestFocus();
        } else {
            // If the item can't be moved, return without doing anything

        }
    }

    @FXML
    private void handleMoveItemDown(ActionEvent event) {
        if (movable) {
            currentIndex = listViewSorted.getSelectionModel().getSelectedIndex();
            int nextIndex = currentIndex + 1;

            Collections.swap(selectedStrings, currentIndex, nextIndex);

            listViewSorted.getSelectionModel().select(nextIndex);
            listViewSorted.requestFocus();
        } else {
        }
    }

    @FXML
    private void handleConversion(ActionEvent event) {

    }

    private void checkIfValidToRelocate() {
        movable = listViewSorted.getSelectionModel().getSelectedIndex() >= 0
                && listViewSorted.getSelectionModel().getSelectedIndex() < selectedStrings.size();
    }

    private void disableBtnOnIndex() {
        if (listViewSorted.getSelectionModel().getSelectedIndex() == 0) {
            btnMoveUp.setDisable(true);
        } else {
            btnMoveUp.setDisable(false);
        }
        if (listViewSorted.getSelectionModel().getSelectedIndex() == listViewSorted.getItems().size() - 1) {
            btnMoveDown.setDisable(true);
        } else {
            btnMoveDown.setDisable(false);
        }
    }

    private void AddListeners() {
        chklistSelectData.getCheckModel().getCheckedItems().addListener((ListChangeListener.Change<? extends String> c) -> {
            if (c.next()) {
                selectedStrings.addAll(c.getAddedSubList());
                selectedStrings.removeAll(c.getRemoved());
            }
        });

        listViewSorted.getSelectionModel().selectedIndexProperty().addListener((observable) -> {
            checkIfValidToRelocate();

            disableBtnOnIndex();
        });
    }
}
