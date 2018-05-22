package com.github.makosful.shoreline.gui.model;

import com.github.makosful.shoreline.Main;
import com.github.makosful.shoreline.be.Config;
import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.bll.BLLException;
import com.github.makosful.shoreline.bll.BLLManager;
import com.github.makosful.shoreline.bll.IBLL;
import com.github.makosful.shoreline.gui.model.Cache.Scenes;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This Model handles the data specific to the MainWindow MVC
 *
 * @author Axl
 */
public class MainWindowModel
{

    // Objects
    private final Cache cache;
    private final IBLL bll;

    // Properties
    private final SimpleStringProperty errorMessage;

    // Mock Data
    ObservableList<Config> configs;
    ObservableList<String> available;
    ObservableList<String> selected;

    private String filepath;

    public MainWindowModel()
    {
        cache = Cache.getInstance();
        bll = new BLLManager();

        errorMessage = new SimpleStringProperty();

        configs = FXCollections.observableArrayList();
        selected = FXCollections.observableArrayList();
        available = FXCollections.observableArrayList();
    }

    //<editor-fold defaultstate="collapsed" desc="Properties">
    public SimpleStringProperty getErrorMessageProperty()
    {
        return this.errorMessage;
    }
    //</editor-fold>

    public ObservableList<String> getAvailableList()
    {
        return this.available;
    }

    public ObservableList<String> getSelectedList()
    {
        return this.selected;
    }

    //<editor-fold defaultstate="collapsed" desc="Basic File Handling">
    public void loadFile(String path)
    {
        this.filepath = path;
    }

    public void clearFile()
    {
        this.filepath = null;
    }

    public boolean isFileEmpty()
    {
        return filepath == null;
    }

    /**
     * Gets the
     *
     * @param path
     *
     * @return
     */
    public ObservableList<String> getCategories()
    {
        try
        {
            return FXCollections.observableArrayList(bll.getHeaders(filepath));
        }
        catch (BLLException ex)
        {
            errorMessage.setValue(ex.getLocalizedMessage());
            return FXCollections.observableArrayList();
        }
    }

    public List<Map> getValues(Map<String, String> map)
    {
        try
        {
            return bll.getValues(map, filepath);
        }
        catch (BLLException ex)
        {
            errorMessage.setValue(ex.getLocalizedMessage());
            return null;
        }

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Convertion">
    public void queueTask()
    {
        //TODO
    }

    public void executeTasks()
    {
        //TODO
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Configs">
    /**
     * Pass the column objects and configname down for storing it in the db
     *
     * @param configName
     * @param items
     *
     * @return boolean which tells if the saving of config was successful
     */
    public boolean saveConfig(String configName, ObservableList<String> items)
    {
        try
        {
            bll.saveConfig(configName, items);
            return true;
        }
        catch (BLLException ex)
        {
            errorMessage.setValue(ex.getLocalizedMessage());
            return false;
        }
    }

    /**
     * Call down to the db for retrieving the configs
     *
     * @return
     */
    public ObservableList<Config> getAllConfigs()
    {
        try
        {
            configs = bll.getAllConfigs();
            return configs;
        }
        catch (BLLException ex)
        {
            errorMessage.setValue(ex.getLocalizedMessage());
        }
        return null;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Logging">
    public void openLogWindow()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("gui/view/LogWindow.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Log");
            stage.show();
        }
        catch (IOException ex)
        {

        }
    }

    /**
     * Method to pass the log down to data access objects for storing the log
     *
     * @param log
     */
    public void saveLog(ConversionLog log)
    {
        try
        {
            bll.saveLog(log);
        }
        catch (BLLException ex)
        {
            errorMessage.setValue(ex.getLocalizedMessage());
        }
    }

    //</editor-fold>
    public void logout()
    {
        cache.clearUser();
        cache.changeScene(Scenes.Login.getValue()); // ID 1
    }

    public Task makeTask(Map<String, String> map, String path) throws BLLException
    {
        return bll.makeTask(map, path);
    }
}
