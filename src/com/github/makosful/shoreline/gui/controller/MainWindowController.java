package com.github.makosful.shoreline.gui.controller;

import com.github.makosful.shoreline.Main;
import com.github.makosful.shoreline.be.ColumnObject;
import com.github.makosful.shoreline.be.Config;
import com.github.makosful.shoreline.bll.BLLException;
import com.github.makosful.shoreline.gui.model.MainWindowModel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
<<<<<<< HEAD
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
=======
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
>>>>>>> 6f0ceef1e2f9ce8c46fa0bd696d8ee71721451ed
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
<<<<<<< HEAD
import javafx.scene.input.*;
=======
import javafx.scene.control.Alert.AlertType;
>>>>>>> 6f0ceef1e2f9ce8c46fa0bd696d8ee71721451ed
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.controlsfx.control.CheckListView;

/**
 * FXML Controller class
 *
 * @author Storm
 */
public class MainWindowController implements Initializable
{

    private MainWindowModel model;
<<<<<<< HEAD
    private HashMap<String, Integer> cellOrder;
=======
    private List<Task> listOfTasks;
>>>>>>> 6f0ceef1e2f9ce8c46fa0bd696d8ee71721451ed

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
    private Button btnChecklistCheck;

    @FXML
    private ColumnConstraints gridOutputColumn;

    private Boolean movable = false;
    private Boolean isChecked = false;
    private Integer currentIndex;
    @FXML
    private ComboBox<Config> comboBoxConfig;
    @FXML
    private TextField txtFieldConfig;
    @FXML
    private MenuItem menuItemInstructions;

    /**
     * Initializes the controller class.
     *
     * @param url FXML parameter.
     * @param rb  FXML parameter.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        model = new MainWindowModel();

//        listViewSorted.setCellFactory(param -> new ListCell<>());
        cellOrder = new HashMap<String, Integer>();
        addConfigs();
        addConfigListener();
    }

    /**
     * Moves the selected item up the list once
     *
     * @param event FXML parameter
     */
    @FXML
    private void handleMoveItemUp(ActionEvent event)
    {
        // Checks if the selected index has been marked as moveable
        if (movable)
        {
            // Gets the two indexes
            currentIndex = listViewSorted.getSelectionModel().getSelectedIndex();
            int prevIndex = currentIndex - 1;

            // Swaps the two indecies
<<<<<<< HEAD
            Collections.swap(model.getSelectedObject(), currentIndex, prevIndex);
=======
            Collections.swap(model.getSelectedList(), currentIndex, prevIndex);
>>>>>>> 6f0ceef1e2f9ce8c46fa0bd696d8ee71721451ed

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
    private void handleMoveItemDown(ActionEvent event)
    {
        // Checks of the current item is marked as moveable
        if (movable)
        {
            // Retrives the indicies for the two items to swap
            currentIndex = listViewSorted.getSelectionModel().getSelectedIndex();
            int prev = currentIndex + 1;

            // Swaps the two items
<<<<<<< HEAD
            Collections.swap(model.getSelectedObject(), currentIndex, prev);
=======
            Collections.swap(model.getSelectedList(), currentIndex, prev);
>>>>>>> 6f0ceef1e2f9ce8c46fa0bd696d8ee71721451ed

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
    private void handleConversion(ActionEvent event) throws BLLException
    {
<<<<<<< HEAD
        hashMapPut();
        model.convert("import_data.xlsx", cellOrder, true);

        for (ExcelRow e : model.getExcelRowsList())
        {
            System.out.println(e.getSiteName());
        }
=======
//        listOfTasks.add();
>>>>>>> 6f0ceef1e2f9ce8c46fa0bd696d8ee71721451ed
    }

    /**
     * Checks if the selected item is moveable
     */
    private void checkIfValidToRelocate()
    {
<<<<<<< HEAD
        if (listViewSorted.getSelectionModel().getSelectedIndex() >= 0
            && listViewSorted.getSelectionModel().getSelectedIndex() < model.getSelectedObject().size())
=======
        int index = listViewSorted.getSelectionModel().getSelectedIndex();
        if (index >= 0 && index < model.getSelectedList().size())
>>>>>>> 6f0ceef1e2f9ce8c46fa0bd696d8ee71721451ed
        {
            movable = true;
        }
        else
        {
            movable = false;
        }
    }

    /**
     * Handles the disabling and reenabling of the Up/Down butons
     */
    private void disableBtnOnIndex()
    {
        if (listViewSorted.getSelectionModel().getSelectedIndex() == 0)
        {
            btnMoveUp.setDisable(true);
        }
        else
        {
            btnMoveUp.setDisable(false);
        }

        if (listViewSorted.getSelectionModel().getSelectedIndex() == listViewSorted.getItems().size() - 1)
        {
            btnMoveDown.setDisable(true);
        }
        else
        {
            btnMoveDown.setDisable(false);
        }
    }

    /**
     * Adds listeners to the the View
     */
    private void AddListeners()
    {
<<<<<<< HEAD
        listViewSorted.setItems(model.getSelectedObject());
=======

        listViewSorted.setItems(model.getSelectedList());
>>>>>>> 6f0ceef1e2f9ce8c46fa0bd696d8ee71721451ed

        listViewSorted.getSelectionModel().selectedIndexProperty().addListener((observable) ->
        {
            checkIfValidToRelocate();
            disableBtnOnIndex();
        });

<<<<<<< HEAD
        chklistSelectData.getItems().addListener(new ListChangeListener<ColumnObject>()
        {
            @Override
            public void onChanged(Change<? extends ColumnObject> c)
            {
                c.next();
                if (c.wasAdded())
                {
                    chklistSelectData.getSelectionModel().select(c.getAddedSubList().get(0));
                    System.out.println(c.getAddedSubList().get(0));
=======
        chklistSelectData.getCheckModel().getCheckedItems().addListener((ListChangeListener.Change<? extends String> c) ->
        {
            for (String s : c.getAddedSubList())
            {
                if (!model.getSelectedList().contains(s))
                {
                    model.getSelectedList().add(s);
>>>>>>> 6f0ceef1e2f9ce8c46fa0bd696d8ee71721451ed
                }
            }
            model.getSelectedList().removeAll(c.getRemoved());
        });
<<<<<<< HEAD

        chklistSelectData.getCheckModel().getCheckedItems().addListener(new ListChangeListener<ColumnObject>()
        {
            @Override
            public void onChanged(ListChangeListener.Change<? extends ColumnObject> c)
            {
                boolean changable = false;
                c.next();
                if (c.wasAdded())
                {
                    System.out.println("Item Checked : " + c.getAddedSubList().get(0));
                    model.getSelectedObject().addAll(c.getAddedSubList().get(0));
                }
                else if (c.wasRemoved())
                {
                    System.out.println("Item Unchecked : " + c.getRemoved().get(0));
                    model.getSelectedObject().removeAll(c.getRemoved());
                }
            }
        });
//        chklistSelectData.getCheckModel().getCheckedItems().addListener((ListChangeListener.Change<? extends ColumnObject> c) ->
//        {
//
//            if(c.)
//            if (c.next())
//            {
//                model.getSelectedObject().addAll(c.getAddedSubList());
//                model.getSelectedObject().removeAll(c.getRemoved());
//            }
//        });

=======
>>>>>>> 6f0ceef1e2f9ce8c46fa0bd696d8ee71721451ed
    }

    @FXML
    private void handleChecklistItemsStatus(ActionEvent event)
    {
//        if (!isChecked)
//        {
//            for (ColumnObject item : chklistSelectData.getItems())
//            {
//
//                chklistSelectData.getCheckModel().check(item);
//            }
//            btnChecklistCheck.setText("Uncheck all");
//        }
//        else
//        {
//            for (ColumnObject checkedItem : chklistSelectData.getItems())
//            {
//                chklistSelectData.getCheckModel().clearCheck(checkedItem);
//            }
////            chklistSelectData.getCheckModel().clearChecks();
//            btnChecklistCheck.setText("Check all");
//        }
//        isChecked = !isChecked;

    }

    public boolean contains(ListView<ColumnObject> listView, ColumnObject cO)
    {
        for (ColumnObject item : chklistSelectData.getCheckModel().getCheckedItems())
        {
            if (item.equals(cO))
            {
                System.out.println("Matching");
                return true;
            }
        }
        System.out.println("Not matching");

        return false;
    }

    /**
     * Loading File - Static file.
     *
     * @param event
     */
    @FXML
    private void loadFile(ActionEvent event)
    {
<<<<<<< HEAD
        model.convert("import_data.xlsx", cellOrder, false);
        try
        {
            chklistSelectData.setItems(model.getColumnNames());

        }
        catch (BLLException ex)
        {
            Logger.getLogger(MainWindowController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
=======
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(btnConvert.getScene().getWindow());
        model.loadFile(file.getAbsolutePath());
        chklistSelectData.setItems(model.getCategories());
>>>>>>> 6f0ceef1e2f9ce8c46fa0bd696d8ee71721451ed
        AddListeners();
    }

    /**
     * Set up the configurations in combobox. with the setConverter, the objects
     * name
     * as string is shown in the comboboc and makes a reference to the object
     * from the string
     */
    private void addConfigs()
    {
        ObservableList<Config> configs = model.getAllConfigs();
        Config c = new Config();
        c.setName("No config");
        comboBoxConfig.getItems().add(c);
        comboBoxConfig.getItems().addAll(configs);
        comboBoxConfig.setConverter(new StringConverter<Config>()
        {

            @Override
            public String toString(Config config)
            {
                return config.getName();
            }

            @Override
            public Config fromString(String configName)
            {
                return comboBoxConfig.getItems().stream().filter(config
                        -> config.getName().equals(configName)).
                        findFirst().orElse(null);
            }
        });

    }

    /**
     * Add listener to when a configuration is selected
     */
    private void addConfigListener()
    {
        try
        {
            comboBoxConfig.valueProperty().addListener((obs, oldConfig, newConfig) ->
            {

            
                if (newConfig != null)
                {
                    chklistSelectData.getCheckModel().clearChecks();
                    if (!newConfig.getName().equals("No config"))
                    {
                        for (ColumnObject c : newConfig.getChosenColumns())
                        {
                            chklistSelectData.getCheckModel().check(c.getColumnID());
                        }
                    }
                }
            });
        }
        catch (IndexOutOfBoundsException e)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Config Error");
            alert.setContentText("Failed to select amount of columns /n, "
                                 + " are you sure you've selected the correct config? ");
            alert.show();
        }
    }

    @FXML
    private void handleBtnSaveConfig(ActionEvent event)
    {
        model.saveConfig(txtFieldConfig.getText(), listViewSorted.getItems());
        comboBoxConfig.getItems().clear();
        addConfigs();
        comboBoxConfig.getSelectionModel().select(comboBoxConfig.getItems().size() - 1);
    }

    @FXML
    private void handleLogout(ActionEvent event)
    {
        model.logout();

    }

    public class listViewCell extends ListCell<ColumnObject>
    {

        private listViewCell()
        {
            ListCell thisCell = this;

            setOnDragDetected(event ->
            {
                if (getItem() == null)
                {
                    return;
                }

                ObservableList<ColumnObject> listItems = listViewSorted.getItems();

                Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(getItem().toString());

                event.consume();
            });

            setOnDragOver(event ->
            {
                if (event.getGestureSource() != thisCell)
                {
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            });

            setOnDragEntered(event ->
            {
                if (event.getGestureSource() != thisCell)
                {
                    setOpacity(0.3);
                }
            });

            setOnDragExited(event ->
            {
                if (event.getGestureSource() != thisCell)
                {
                    setOpacity(1);
                }
            });

            setOnDragDropped(event ->
            {
                if (getItem() == null)
                {
                    return;
                }

                Dragboard db = event.getDragboard();
                boolean success = false;

                if (db.hasString())
                {
                    ObservableList<ColumnObject> items = listViewSorted.getItems();
                    int draggedIdx = items.indexOf(db.getString());
                    int thisIdx = items.indexOf(getItem());

                    items.set(draggedIdx, getItem());

                    List<ColumnObject> listItemsCopy = new ArrayList<>(listViewSorted.getItems());
                    getListView().getItems().setAll(listItemsCopy);

                    success = true;
                }
                event.setDropCompleted(success);

                event.consume();
            });

            setOnDragDone(DragEvent::consume);
        }

        @Override
        protected void updateItem(ColumnObject item, boolean empty)
        {
            super.updateItem(item, empty);

            if (empty || item == null)
            {
                setGraphic(null);
            }
            else
            {
                listViewSorted.getItems().indexOf(item);
            }
        }
    }

    @FXML
    private void handleOpenLog(ActionEvent event)
    {
        model.openLogWindow();
    }

    @FXML
    private void handleShowInstructionsWindow(ActionEvent event)
    {
        try
        {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(Main.class.getResource("gui/view/HelpWindow.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Shoreline | Instructions");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();

        }
        catch (IOException ex)
        {
            System.out.println("failed to open window");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error opening the window");
            alert.setContentText(ex.getMessage());
            alert.show();
            System.out.println(ex.getLocalizedMessage());
        }
    }
}
