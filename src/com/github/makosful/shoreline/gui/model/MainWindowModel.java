package com.github.makosful.shoreline.gui.model;

import com.github.makosful.shoreline.be.SortedListICell;
import com.github.makosful.shoreline.bll.BLLException;
import com.github.makosful.shoreline.bll.BLLManager;
import com.github.makosful.shoreline.bll.IBLL;
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
    ObservableList<String> strings = FXCollections.observableArrayList("One 1", "Two 2", "Three 3", "Four 4", "Five 5", "Six 6", "Seven 7", "Eight 8", "Nine 9", "Ten10");
    ObservableList<String> selectedStrings = FXCollections.observableArrayList();
    ObservableList<SortedListICell> selectedStrings = FXCollections.observableArrayList();

    public MainWindowModel()
    {
        cache = Cache.getInstance();
        bll = new BLLManager();
    }

    public ObservableList<String> getMockStrings()
    {
        return strings;
    }

    public ObservableList<String> getSelectedStrings()
    {
        return selectedStrings;
    }

    public void readFromExcel(String import_dataxlsx)
    {
        try
        {
            bll.readFromExcelFile(import_dataxlsx);
        }
        catch (BLLException ex)
        {
            Logger.getLogger(MainWindowModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
