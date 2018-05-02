package com.github.makosful.shoreline.gui.model;

import com.github.makosful.shoreline.be.ColumnObject;
import com.github.makosful.shoreline.be.Config;
import com.github.makosful.shoreline.be.ExcelRow;
import com.github.makosful.shoreline.bll.BLLException;
import com.github.makosful.shoreline.bll.BLLManager;
import com.github.makosful.shoreline.bll.IBLL;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    ObservableList<ColumnObject> selectedColumns;
    ObservableList<Config> configs;
    public MainWindowModel()
    {
        cache = Cache.getInstance();
        bll = new BLLManager();

        selectedColumns = FXCollections.observableArrayList();
    }

    public ObservableList<ColumnObject> getColumnNames()
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

    public List<ExcelRow> getExcelRowsList()
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

    public ObservableList<ColumnObject> getSelectedStrings()
    {
        return selectedColumns;
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
     * Select a specific config from the observable list based on the selected id 
     * @param id
     * @return 
     */
    public Config getConfig(int id)
    {
        for(Config config : configs)
        {
            if(config.getId() == id)
            {
                return config;
            }
        }
        return null;
    }

    /**
     * Call down to the db for retrieving the configs 
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

}
