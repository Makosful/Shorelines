package com.github.makosful.shoreline.gui.model;

import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.bll.BLLException;
import com.github.makosful.shoreline.bll.BLLManager;
import com.github.makosful.shoreline.bll.IBLL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Axl
 */
public class LogWindowModel
{

    private final IBLL bll;
    private String errorMessage;
    private ObservableList<ConversionLog> fullList;

    public LogWindowModel()
    {
        bll = new BLLManager();

        fullList = FXCollections.observableArrayList();
    }

    /**
     * Get the error messages only
     *
     * @return Returns an ObservableLists with only the error logs
     */
    public ObservableList<ConversionLog> getErrorLog()
    {

        ObservableList<ConversionLog> errors = FXCollections.observableArrayList();

        // Extracts the errors from the list and puts them in a new list
        for (ConversionLog log : fullList)
        {
            if (log.getLogType().equalsIgnoreCase("error"))
            {
                errors.add(log);
            }
        }

        return errors;
    }

    /**
     * Gets the full list of errors
     *
     * @return Returns the full list of errors
     */
    public ObservableList<ConversionLog> getFullLog()
    {
        try
        {
            fullList = bll.getAllLogs();
            return fullList;
        }
        catch (BLLException ex)
        {
            errorMessage = ex.getLocalizedMessage();
            return null;
        }
    }

    /**
     * Pass the searchtext down to the db, making a search for the specific searchtext,
     * and return the searchresult
     * @param searchText 
     * @param checked 
     */
    public void searchLogs(String searchText, List<String> checked)
    {
        try
        {
            fullList.clear();
            fullList.addAll(bll.searchLogs(searchText, checked));        
        }
        catch (BLLException ex)
        {
            Logger.getLogger(LogWindowModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
