package com.github.makosful.shoreline.gui.controller;

import com.github.makosful.shoreline.be.Config;
import com.github.makosful.shoreline.gui.model.MainWindowModel;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javafx.util.StringConverter;
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
    @FXML
    private ComboBox<Config> comboBoxConfig;

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
        
        addConfigs();
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

        listViewSorted.getSelectionModel().selectedIndexProperty().addListener((observable)
                -> {
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

    private void addConfigs()
    {
        Config c = new Config();
        c.setName("IBM");
        
        comboBoxConfig.getItems().add(c);
        comboBoxConfig.setConverter(new StringConverter<Config>() {

            @Override
            public String toString(Config config)
            {
                return config.getName();
            }

            @Override
            public Config fromString(String configName)
            {
                return comboBoxConfig.getItems().stream().filter(ap -> 
                ap.getName().equals(configName)).findFirst().orElse(null);
            }
        });
        
        comboBoxConfig.valueProperty().addListener((obs, oldval, newval) -> {
       
                System.out.println("Selected airport: " + newval.getName());
        });
       
        
        
    }

    @FXML
    private void handleSelectedConfig(ActionEvent event)
    {
        
    }
}
