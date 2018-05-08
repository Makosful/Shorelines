package com.github.makosful.shoreline.gui.model;

import com.github.makosful.shoreline.Main;
import com.github.makosful.shoreline.be.Config;
import com.github.makosful.shoreline.bll.BLLException;
import com.github.makosful.shoreline.bll.BLLManager;
import com.github.makosful.shoreline.bll.IBLL;
import com.github.makosful.shoreline.gui.controller.MainWindowController;
import com.github.makosful.shoreline.gui.model.Cache.Scenes;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public boolean loadFile(String path)
    {
        try
        {
            return bll.loadFile(path);
        }
        catch (BLLException ex)
        {
            errorMessage.setValue(ex.getLocalizedMessage());
            return false;
        }
    }

    /**
     * Gets the
     *
     * @return
     */
    public ObservableList<String> getCategories()
    {
        try
        {
            return FXCollections.observableArrayList(bll.getHeaders());
        }
        catch (BLLException ex)
        {
            errorMessage.setValue(ex.getLocalizedMessage());
            return FXCollections.observableArrayList();
        }
    }

    public List<Map> getValues()
    {
        try
        {
            return bll.getValues(null);
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
     */
    public void saveConfig(String configName, ObservableList<String> items)
    {
        try
        {
            bll.saveConfig(configName, items);
        }
        catch (BLLException ex)
        {
            Logger.getLogger(MainWindowModel.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MainWindowModel.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //</editor-fold>

    public void logout()
    {
        cache.clearUser();
        cache.changeScene(Scenes.Login.getValue()); // ID 1
    }

    public Config getConfig(int id)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public Task makeTask(List<Map> list) throws BLLException
    {
       return bll.makeTask(list);
    }
}
