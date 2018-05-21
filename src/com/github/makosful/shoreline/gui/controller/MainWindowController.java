package com.github.makosful.shoreline.gui.controller;

import com.github.makosful.shoreline.Main;
import com.github.makosful.shoreline.be.Config;
import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.bll.BLLException;
import com.github.makosful.shoreline.gui.model.MainWindowModel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    private List<Task> listTask;
    private String filePath;
    private Label[] labels;
    //<editor-fold defaultstate="collapsed" desc="Split Pane Descriptions">
    //<editor-fold defaultstate="collapsed" desc="FXML Stuff">
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
    @FXML
    private ComboBox<Config> comboBoxConfig;
    @FXML
    private TextField txtFieldConfig;
    @FXML
    private MenuItem menuItemInstructions;
//</editor-fold>

    private Boolean movable = false;
    private Boolean isChecked = false;
    private Boolean ListViewInFocus = false;
    private Integer currentIndex;

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
        
        AddListeners();
        addConfigs();
        addConfigListener();
    }

    @FXML
    private void handleChangePassword(ActionEvent event)
    {
        try
        {
            URL resource = Main.class.getResource("gui/view/ChangePassword.fxml");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(resource);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Change Password");
            stage.setResizable(false);
            stage.showAndWait();
        }
        catch (IOException ex)
        {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        Task task = model.makeTask(getMap(), filePath);
        if (task != null)
        {
            listTask.add(task);
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

    /**
     * Set the custom text for the log
     *
     * @param message
     * @param logType
     */
    public void setLog(String message, String logType)
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
                System.out.println("Detected a change! \n");
            }
        });

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
                        System.out.println(s);
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
    private void loadFile(ActionEvent event)
    {
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(btnConvert.getScene().getWindow());
        filePath = file.getName().split("\\.")[0];

        Thread thread = new Thread(() ->
        {
            //Set file name to log, which will be saved later
            log.setFileName(file.getName());
            
            if (model.loadFile(file.getAbsolutePath()))
            {
                Platform.runLater(() ->
                {
                    chklistSelectData.setItems(model.getCategories());
                    AddListeners();
                    listViewSorted.getItems().clear();
                    setLog("No errors occured, filed loaded successfully", "Conversion");
                    model.saveLog(log);
                });
                
            }
            else
            {
                Platform.runLater(() ->
                {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Reading File Error");
                    alert.setContentText(model.getErrorMessageProperty().getValue());
                    alert.show();
                });
                
                setLog("An error occured while loading file for conversion, "
                           + model.getErrorMessageProperty().getValue(), "Error");
                model.saveLog(log);
            }
        });
        thread.setDaemon(true);
        thread.start();

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
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
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

    @FXML
    private void taskWindow(ActionEvent event)
    {
        try
        {
            FXMLLoader fxLoader = new FXMLLoader(Main.class.getResource("gui/view/TaskManagerWindow.fxml"));
            Parent root = fxLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Task Handling");
            TaskManagerWindowController controller = fxLoader.getController();
            controller.getTaskList(listTask);
            controller.getMainController(this);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Failed to open the window");
            alert.setContentText(ex.getMessage());
            alert.show();
        }

    }

    // Saves log.
    public void saveLog()
    {
        model.saveLog(log);
    }

}
