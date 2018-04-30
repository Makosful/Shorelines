package com.github.makosful.shoreline.gui.controller;

import com.github.makosful.shoreline.be.SortedListICell;
import com.github.makosful.shoreline.gui.model.MainWindowModel;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
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

    private MainWindowModel model;

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
    private Label lbl01SiteName;
    @FXML
    private Label lbl02AssetSerialNo;
    @FXML
    private Label lbl03ExtWorkOrderID;
    @FXML
    private Label lbl04SystemStatus;
    @FXML
    private Label lbl05UserStatus;
    @FXML
    private Label lbl06CreatedOn;
    @FXML
    private Label lbl07CreatedBy;
    @FXML
    private Label lbl08Priority;
    @FXML
    private Label lbl09Status;
    @FXML
    private Label lbl10EarliestStart;
    @FXML
    private Label lb111LatestStart;
    @FXML
    private Label lbl12LatestFinish;
    @FXML
    private Label lbl13EstimatedTime;
    @FXML
    private Label lb14lNameDescription;
    @FXML
    private Label lbl15OrderType;
    //</editor-fold>

    @FXML
    private Button btnMoveUp;
    @FXML
    private Button btnMoveDown;
    @FXML
    private Button btnConvert;
    @FXML
    private Button btnChecklistCheck;

    @FXML
    private CheckListView<String> chklistSelectData;
    @FXML
    private ListView<String> listViewSorted;
    @FXML
    private ColumnConstraints gridOutputColumn;

    private Boolean movable = false;
    private Boolean isChecked = false;
    private Integer currentIndex;

    /**
     * Initializes the controller class.
     *
     * @param url FXML parameter.
     * @param rb FXML parameter.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        model = new MainWindowModel();

        chklistSelectData.setItems(model.getMockStrings());

        listViewSorted.setItems(model.getSelectedStrings());

        AddListeners();
    }

    /**
     * Moves the selected item up the list once
     *
     * @param event FXML parameter
     */
    @FXML
    private void handleMoveItemUp(ActionEvent event) {
        // Checks if the selected index has been marked as moveable
        if (movable) {
            // Gets the two indexes
            currentIndex = listViewSorted.getSelectionModel().getSelectedIndex();
            int prevIndex = currentIndex - 1;

            // Swaps the two indecies
            Collections.swap(model.getSelectedStrings(), currentIndex, prevIndex);

            // Reselects the item that was just moved
            listViewSorted.getSelectionModel().select(prevIndex);
            listViewSorted.requestFocus();
        }
    }

    /**
     * Moves the currently selected item down the list once
     *
     * @param event FXML Parameter
     */
    @FXML
    private void handleMoveItemDown(ActionEvent event) {
        // Checks of the current item is marked as moveable
        if (movable) {
            // Retrives the indicies for the two items to swap
            currentIndex = listViewSorted.getSelectionModel().getSelectedIndex();
            int prev = currentIndex + 1;

            // Swaps the two items
            Collections.swap(model.getSelectedStrings(), currentIndex, prev);

            // Reselects the item that was just moved
            listViewSorted.getSelectionModel().select(prev);
            listViewSorted.requestFocus();
        }
    }

    /**
     * TODO
     *
     * @param event FXML Parameter
     */
    @FXML
    private void handleConversion(ActionEvent event) {
        model.readFromExcel("Import_data.xlsx");
    }

    /**
     * Checks if the selected item is moveable
     */
    private void checkIfValidToRelocate() {
        if (listViewSorted.getSelectionModel().getSelectedIndex() >= 0
                && listViewSorted.getSelectionModel().getSelectedIndex() < model.getSelectedStrings().size()) {
            movable = true;
        } else {

        }
    }

    /**
     * Handles the disabling and reenabling of the Up/Down butons
     */
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

    /**
     * Adds listeners to the the View
     */
    private void AddListeners() {
        listViewSorted.setItems(chklistSelectData.getCheckModel().getCheckedItems());

//        enableListViewDragAndSort();
        listViewSorted.getSelectionModel().selectedIndexProperty().addListener((observable) -> {
            checkIfValidToRelocate();

            disableBtnOnIndex();
        });

        chklistSelectData.getCheckModel().getCheckedItems().addListener((ListChangeListener.Change<? extends String> c) -> {
            if (c.next()) {
                model.getSelectedStrings().addAll(c.getAddedSubList());
                model.getSelectedStrings().removeAll(c.getRemoved());
            }
        });
    }

    @FXML
    private void handleChecklistItemsStatus(ActionEvent event) {
        if (!isChecked) {
            chklistSelectData.getCheckModel().checkAll();
            isChecked = !isChecked;
        } else if (isChecked) {
            chklistSelectData.getCheckModel().clearChecks();
            isChecked = !isChecked;
        }
    }
}
