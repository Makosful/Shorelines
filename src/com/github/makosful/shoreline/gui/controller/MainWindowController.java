package com.github.makosful.shoreline.gui.controller;

import com.github.makosful.shoreline.Main;
import com.github.makosful.shoreline.be.Config;
import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.be.PopUp;
import com.github.makosful.shoreline.bll.BLLException;
import com.github.makosful.shoreline.gui.model.MainWindowModel;
import com.jfoenix.controls.JFXToggleButton;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.swing.filechooser.FileNameExtensionFilter;
import static org.apache.poi.hssf.usermodel.HeaderFooter.file;
import org.controlsfx.control.CheckListView;

/**
 * FXML Controller class
 *
 * @author Storm
 */
public class MainWindowController implements Initializable
{

    private MainWindowModel model;
    private Map<String, String> cellOrder;
    private ConversionLog log;
    private List<Runnable> listTask;
    private String filePath;
    private int output = 0;

    //<editor-fold defaultstate="collapsed" desc="FXML Stuff">
    //<editor-fold defaultstate="collapsed" desc="Split Pane Unused">
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

    //<editor-fold defaultstate="collapsed" desc="Output labels">
    @FXML
    private Label lbl01SiteName;
    @FXML
    private Label lbl02AssetSerialNo;
    @FXML
    private Label lbl03OrderType;
    @FXML
    private Label lbl04ExtWorkOrderID;
    @FXML
    private Label lbl05SystemStatus;
    @FXML
    private Label lbl06UserStatus;
    @FXML
    private Label lbl07CreatedOn;
    @FXML
    private Label lbl08CreatedBy;
    @FXML
    private Label lbl09NameDescription;
    @FXML
    private Label lbl10Priority;
    @FXML
    private Label lbl11Status;
    @FXML
    private Label lbl12EarliestStart;
    @FXML
    private Label lbl13LatestStart;
    @FXML
    private Label lbl14LatestFinish;
    @FXML
    private Label lbl15EstimatedTime;
    //</editor-fold>

    @FXML
    private Button btnInsertCustom;
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
    @FXML
    private ComboBox<Config> comboBoxConfig;
    @FXML
    private TextField txtFieldConfig;
    @FXML
    private MenuItem menuItemInstructions;
//</editor-fold>
    private Label[] labels;
    private Boolean movable = false;
    private Boolean isChecked = false;
    private Boolean ListViewInFocus = false;
    private Integer currentIndex;
    @FXML
    private Button btnDeleteSelected;
    @FXML
    private MenuItem fileLoader;

    final KeyCombination shortcutUp = new KeyCodeCombination(KeyCode.UP, KeyCombination.CONTROL_DOWN);
    final KeyCombination shortcutDown = new KeyCodeCombination(KeyCode.DOWN, KeyCombination.CONTROL_DOWN);

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
        cellOrder = new HashMap();
        listTask = new ArrayList();
        log = new ConversionLog();
        labels = new Label[]

        {
            lbl01SiteName, lbl02AssetSerialNo,
            lbl03OrderType, lbl04ExtWorkOrderID,
            lbl05SystemStatus, lbl06UserStatus,
            lbl07CreatedOn, lbl08CreatedBy,
            lbl09NameDescription, lbl10Priority,
            lbl11Status, lbl12EarliestStart,
            lbl13LatestStart, lbl14LatestFinish,
            lbl15EstimatedTime
        };

        labels = new Label[]
        {
            lbl01SiteName, lbl02AssetSerialNo,
            lbl03OrderType, lbl04ExtWorkOrderID,
            lbl05SystemStatus, lbl06UserStatus,
            lbl07CreatedOn, lbl08CreatedBy,
            lbl09NameDescription
        };

        AddListeners();
        addConfigs();
        addConfigListener();

    }

    private void shortcutMoveItemListView(KeyEvent event)
    {
        if (ListViewInFocus)
        {
            if (shortcutUp.match(event))
            {
                moveItemUpListViewNoFocus();
                event.consume();
            }
            else if (shortcutDown.match(event))
            {
                moveItemDownListViewNoFocus();
                event.consume();
            }
        }
    }

    /**
     * Moves the selected item up the list once
     *
     * @param event FXML parameter
     */
    @FXML
    private void handleMoveItemUp(ActionEvent event)
    {
        moveItemUpListView();
    }

    private void moveItemUpListView()
    {
        // Checks if the selected index has been marked as moveable
        if (movable)
        {
            moveItemUpListViewNoFocus();
            listViewSorted.requestFocus();
        }
    }

    private void moveItemUpListViewNoFocus()
    {
        // Checks if the selected index has been marked as moveable
        if (movable)
        {
            // Gets the two indexes
            currentIndex = listViewSorted.getSelectionModel().getSelectedIndex();
            int prevIndex = currentIndex - 1;

            // Swaps the two indecies
            Collections.swap(model.getSelectedList(), currentIndex, prevIndex);
            listViewSorted.getSelectionModel().clearAndSelect(prevIndex);
            listViewSorted.scrollTo(prevIndex);
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
        moveItemDownListView();
    }

    private void moveItemDownListView()
    {
        // Checks of the current item is marked as moveable
        if (movable)
        {
            moveItemDownListViewNoFocus();
            listViewSorted.requestFocus();
        }
    }

    private void moveItemDownListViewNoFocus()
    {
        // Checks of the current item is marked as moveable
        if (movable)
        {
            // Retrives the indicies for the two items to swap
            currentIndex = listViewSorted.getSelectionModel().getSelectedIndex();
            int nextIndex = currentIndex + 1;

            // Swaps the two items
            Collections.swap(model.getSelectedList(), currentIndex, nextIndex);
            listViewSorted.getSelectionModel().clearAndSelect(nextIndex);
            listViewSorted.scrollTo(nextIndex);
        }
    }

    /**
     * TODO
     *
     * @param event FXML Parameter
     */
    @FXML
    private void handleConversion(ActionEvent event) throws BLLException, InterruptedException
    {
        output++;

        List<Map> mapTask = model.getValues(getMap());

        if (mapTask != null)
        {
            Runnable task = model.makeTask(mapTask, "output" + output + ".json");
            if (task != null)
            {
                listTask.add(task);
            }
            else
            {
                showAlert("Convertion Error", "An error occured while converting the file, "
                                              + model.getErrorMessageProperty().getValue(), "Convertion Error");

                setLog("An error occured while converting the file, "
                       + model.getErrorMessageProperty().getValue(), "Error");
                model.saveLog(log);
            }
        }
        else
        {
            showAlert("Convertion Error", "An error occured while converting the file, "
                                          + model.getErrorMessageProperty().getValue(), "Conversion Error");

            setLog("An error occured while converting the file, "
                   + model.getErrorMessageProperty().getValue(), "Error");
            model.saveLog(log);
        }

    }

    /**
     * Creates a new alert, to show the user a specific error occured
     *
     * @param title
     * @param message
     * @param headerText
     */
    private void showAlert(String title, String message, String headerText)
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(headerText);
        alert.show();
    }

    @FXML
    private void executeTaskBatch(ActionEvent event)
    {
        ExecutorService exService = Executors.newFixedThreadPool(4);

        for (Runnable run : listTask)
        {

            exService.execute(run);
        }

        exService.shutdown();
        if (exService.isShutdown())
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Convertion Info");
            alert.setContentText("You successfully converted the files to JSON");
            alert.setHeaderText("Info");
            alert.show();

            log.setMessage("Message");
            log.setLogType("Conversion, no errors occured");
            log.setDate(new Date());

            setLog("No errors occured, conversion successful", "Conversion");
            model.saveLog(log);
        }

    }

    /**
     * Set the custom text for the log
     *
     * @param message
     * @param logType
     */
    private void setLog(String message, String logType)
    {
        log.setMessage(message);
        log.setLogType(logType);
    }

    /**
     * Checks if the selected item is moveable
     */
    private void checkIfValidToRelocate()
    {
        int index = listViewSorted.getSelectionModel().getSelectedIndex();
        if (index >= 0 && index < model.getSelectedList().size())
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
        listViewSorted.getItems().addListener(new ListChangeListener()
        {

            @Override
            public void onChanged(ListChangeListener.Change change)
            {
                for (Label label : labels)
                {
                    label.setText("");
                }

                for (int i = 0; i < listViewSorted.getItems().size(); i++)
                {
                    labels[i].setText(listViewSorted.getItems().get(i));
                }
            }
        });

        btnInsertCustom.disableProperty().bind(Bindings.isEmpty(listViewSorted.getItems()));
        btnDeleteSelected.disableProperty().bind(Bindings.isEmpty(listViewSorted.getItems()));

        listViewSorted.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>()
                              {
                                  @Override
                                  public void handle(KeyEvent event)
                                  {
                                      shortcutMoveItemListView(event);
                                  }
                              });

        listViewSorted.setItems(model.getSelectedList());

        listViewSorted.getSelectionModel().selectedIndexProperty().addListener((observable) ->
        {
            checkIfValidToRelocate();
            disableBtnOnIndex();
        });

        listViewSorted.focusedProperty().addListener((observable, oldValue, newValue) ->
        {
            if (newValue)
            {
                ListViewInFocus = true;
            }
            else
            {
                ListViewInFocus = false;
            }
        });

        chklistSelectData.getCheckModel().getCheckedItems().addListener((ListChangeListener.Change<? extends String> c) ->
        {
            if (c.next())
            {
                for (String s : c.getAddedSubList())
                {
                    if (!model.getSelectedList().contains(s))
                    {
                        model.getSelectedList().add(s);
                    }
                }
                model.getSelectedList().removeAll(c.getRemoved());
            }
        });
    }

    @FXML

    private void handleChecklistItemsStatus(ActionEvent event)
    {
        if (isChecked)
        {
            chklistSelectData.getCheckModel().clearChecks();
            isChecked = false;
            btnChecklistCheck.setText("Check all");
        }
        else
        {
            chklistSelectData.getCheckModel().checkAll();
            isChecked = true;
            btnChecklistCheck.setText("Uncheck all");
        }
    }

    /**
     * HashMap to save the column number of columnObject, and the name of
     * the json static label for example "siteName"
     */
    private Map getMap()
    {
        // Clearing hashMap.
        cellOrder.clear();
        String[] hashmapStrings = new String[]
        {
            "siteName", "assetSerialNumber", "orderType", "workOrderId", "systemStatus",
            "userStatus", "createdOn", "createdBy", "nameDescription",
            "priority", "status", "esDate", "lsDate", "lfDate", "esTime"
        };

        List<String> listOfStrings = listViewSorted.getItems();

        for (int i = 0; i < listOfStrings.size(); i++)
        {
            String col = listOfStrings.get(i);
            cellOrder.put(hashmapStrings[i], col);

            if (i == 14)
            {
                break;
            }
        }

        return cellOrder;
    }

    /**
     * Loading File - Static file.
     *
     * @param event
     */
    @FXML
    private void loadFile(ActionEvent event
    )
    {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter excelFilter = new FileChooser.ExtensionFilter("Excel files", "*.xlsx", "*.xls");
        FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter("Comma Seperated Values", "*.csv");
        fc.getExtensionFilters().addAll(excelFilter, csvFilter);

        File file = fc.showOpenDialog(btnConvert.getScene().getWindow());

        if (file == null)
        {
            return;
        }
        else
        {
            model.loadFile(file.getAbsolutePath());
            chklistSelectData.setItems(model.getCategories());
            AddListeners();

            //Set file name to log, which will be saved later
            log.setFileName(file.getName());

            if (model.loadFile(file.getAbsolutePath()))
            {
                chklistSelectData.setItems(model.getCategories());
                AddListeners();

                setLog("No errors occured, filed loaded successfully", "Conversion");
                model.saveLog(log);
            }
            else
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Reading File Error");
                alert.setContentText(model.getErrorMessageProperty().getValue());
                alert.show();

                setLog("An error occured while loading file for conversion, "
                       + model.getErrorMessageProperty().getValue(), "Error");
                model.saveLog(log);
            }
        }
    }

    /**
     * Set up the configurations in combobox. with the setConverter, the
     * objects
     * name
     * as string is shown in the comboboc and makes a reference to the
     * object
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
     * the chklistSelectData listViews checkboxes are checked correspomding to
     * the
     * configuration, therefore the listViewSorted is filled with the selected
     * columnheaders
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
                        for (String c : newConfig.getChosenColumns())
                        {
                            chklistSelectData.getCheckModel().check(chklistSelectData.getCheckModel().getItemIndex(c));
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
        }
    }

    @FXML
    private void handleBtnSaveConfig(ActionEvent event)
    {
        if (model.saveConfig(txtFieldConfig.getText(), listViewSorted.getItems()))
        {
            comboBoxConfig.getItems().clear();
            addConfigs();
            comboBoxConfig.getSelectionModel().select(comboBoxConfig.getItems().size() - 1);

            setLog("No errors occured, saved configuration successfully", "Configuration");
            model.saveLog(log);
        }
        else
        {
            setLog("An error occured while saving configuration, "
                   + model.getErrorMessageProperty().getValue(), "Error");
            model.saveLog(log);
        }
    }

    @FXML
    private void handleLogout(ActionEvent event)
    {
        model.logout();
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
        }
    }

    private void setupOuputExample()
    {
        setOutputLabelText();
    }

    private void setOutputLabelText()
    {
        //1st item
        if (listViewSorted.getItems().get(0).length() > 0)
        {

            lbl01SiteName.setText(listViewSorted.getItems().get(0));
        }
        else
        {
            lbl01SiteName.setText("Undefined");
        }
        //2nd item
        if (!listViewSorted.getItems().get(1).isEmpty() || listViewSorted.getItems().get(1) != null)
        {
            lbl02AssetSerialNo.setText(listViewSorted.getItems().get(1));
        }
        else
        {
            lbl02AssetSerialNo.setText("Undefined");
        }
        //3rd item
        if (!listViewSorted.getItems().get(2).isEmpty() || listViewSorted.getItems().get(2) != null)
        {
            lbl03OrderType.setText(listViewSorted.getItems().get(2));
        }
        else
        {
            lbl03OrderType.setText("Undefined");
        }
        //4th item
        if (!listViewSorted.getItems().get(3).isEmpty() || listViewSorted.getItems().get(3) != null)
        {
            lbl04ExtWorkOrderID.setText(listViewSorted.getItems().get(3));
        }
        else
        {
            lbl04ExtWorkOrderID.setText("Undefined");
        }
        //5th item
        if (!listViewSorted.getItems().get(4).isEmpty() || listViewSorted.getItems().get(4) != null)
        {
            lbl05SystemStatus.setText(listViewSorted.getItems().get(4));
        }
        else
        {
            lbl05SystemStatus.setText("Undefined");
        }
        //6th item
        if (!listViewSorted.getItems().get(5).isEmpty() || listViewSorted.getItems().get(5) != null)
        {
            lbl06UserStatus.setText(listViewSorted.getItems().get(5));
        }
        else
        {
            lbl06UserStatus.setText("Undefined");
        }
        //7th item
        if (!listViewSorted.getItems().get(6).isEmpty() || listViewSorted.getItems().get(6) != null)
        {
            lbl07CreatedOn.setText(listViewSorted.getItems().get(6));
        }
        else
        {
            lbl07CreatedOn.setText("Undefined");
        }
        //8th item
        if (!listViewSorted.getItems().get(7).isEmpty() || listViewSorted.getItems().get(7) != null)
        {
            lbl08CreatedBy.setText(listViewSorted.getItems().get(7));
        }
        else
        {
            lbl08CreatedBy.setText("Undefined");
        }
        //9th item
        if (!listViewSorted.getItems().get(8).isEmpty() || listViewSorted.getItems().get(8) != null)
        {
            lbl09NameDescription.setText(listViewSorted.getItems().get(8));
        }
        else
        {
            lbl09NameDescription.setText("Undefined");
        }
        //10th item
        if (!listViewSorted.getItems().get(9).isEmpty() || listViewSorted.getItems().get(9) != null)
        {
            lbl10Priority.setText(listViewSorted.getItems().get(9));
        }
        else
        {
            lbl10Priority.setText("Undefined");
        }
        //11th item
        if (!listViewSorted.getItems().get(10).isEmpty() || listViewSorted.getItems().get(10) != null)
        {
            lbl11Status.setText(listViewSorted.getItems().get(10));
        }
        else
        {
            lbl11Status.setText("Undefined");
        }
        //12th item
        if (!listViewSorted.getItems().get(11).isEmpty() || listViewSorted.getItems().get(11) != null)
        {
            lbl12EarliestStart.setText(listViewSorted.getItems().get(11));
        }
        else
        {
            lbl12EarliestStart.setText("Undefined");
        }
        //13th item
        if (!listViewSorted.getItems().get(12).isEmpty() || listViewSorted.getItems().get(12) != null)
        {
            lbl13LatestStart.setText(listViewSorted.getItems().get(12));
        }
        else
        {
            lbl13LatestStart.setText("Undefined");

        }
        //14th item
        if (!listViewSorted.getItems().get(13).isEmpty() || listViewSorted.getItems().get(13) != null)
        {
            lbl14LatestFinish.setText(listViewSorted.getItems().get(13));
        }
        else
        {
            lbl14LatestFinish.setText("Undefined");
        }
        //15th item
        if (!listViewSorted.getItems().get(14).isEmpty() || listViewSorted.getItems().get(14) != null)
        {
            lbl15EstimatedTime.setText(listViewSorted.getItems().get(14));
        }
        else
        {
            lbl15EstimatedTime.setText("Undefined");
        }
    }

    @FXML
    private void handleInsertCustomItem(ActionEvent event)
    {
        PopUp.display();
        listViewSorted.getItems().add(PopUp.getInputText());
    }

    @FXML
    private void handleDeleteSelectedItem(ActionEvent event)
    {
        listViewSorted.getItems().remove(listViewSorted.getSelectionModel().getSelectedItem());

        System.out.println(listViewSorted.getItems().get(0).length());

        listViewSorted.refresh();
//        setOutputLabelText();
    }

}
