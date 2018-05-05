package com.github.makosful.shoreline.gui.model;

import com.github.makosful.shoreline.Main;
import com.github.makosful.shoreline.be.ColumnObject;
import com.github.makosful.shoreline.be.Config;
import com.github.makosful.shoreline.be.ExcelRow;
import com.github.makosful.shoreline.bll.BLLException;
import com.github.makosful.shoreline.bll.BLLManager;
import com.github.makosful.shoreline.bll.IBLL;
import com.github.makosful.shoreline.gui.controller.MainWindowController;
import com.github.makosful.shoreline.gui.model.Cache.Scenes;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    // Mock Data
    ObservableList<ColumnObject> columns;
    ObservableList<ColumnObject> selectedList;
    ObservableList<Config> configs;

    public MainWindowModel()
    {
        cache = Cache.getInstance();
        bll = new BLLManager();

        selectedList = FXCollections.observableArrayList();
    }

    public ObservableList<ColumnObject> getColumnNames() throws BLLException
    {
        try
        {
            columns = FXCollections.observableArrayList(bll.getColumnNames());
        }
        catch (BLLException ex)
        {
            Logger.getLogger(MainWindowModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return columns;
    }

    public List<ExcelRow> getExcelRowsList() throws BLLException
    {
        try
        {
            return bll.getExcelRowsList();
        }
        catch (BLLException ex)
        {
            Logger.getLogger(MainWindowModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public ObservableList<ColumnObject> getSelectedList()
    {
        return selectedList;
    }

    public void convert(String import_dataxlsx, HashMap<String, Integer> cellOrder, boolean conversion)
    {
        try
        {
            bll.readFromExcelFile(import_dataxlsx, cellOrder, conversion);

            bll.addTask(getExcelRowsList());
        }
        catch (BLLException ex)
        {
            Logger.getLogger(MainWindowModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Pass the column objects and configname down for storing it in the db
     *
     * @param configName
     * @param items
     */
    public void saveConfig(String configName, ObservableList<ColumnObject> items)
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

    public void logout()
    {
        cache.clearUser();
        cache.changeScene(Scenes.Login.getValue()); // ID 1
    }

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
}
