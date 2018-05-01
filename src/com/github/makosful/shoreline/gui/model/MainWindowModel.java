package com.github.makosful.shoreline.gui.model;

import com.github.makosful.shoreline.be.ColumnObject;
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

    public MainWindowModel()
    {
        cache = Cache.getInstance();
        bll = new BLLManager();

        selectedColumns = FXCollections.observableArrayList();
    }

    public ObservableList<ColumnObject> getColumnNames()
    {
        columns = FXCollections.observableArrayList(bll.getColumnNames());
        return columns;
    }

    public List<ExcelRow> getExcelRowsList()
    {
        return bll.getExcelRowsList();
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

}
