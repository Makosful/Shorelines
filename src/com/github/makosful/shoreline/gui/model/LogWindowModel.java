package com.github.makosful.shoreline.gui.model;

import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.bll.BLLManager;
import com.github.makosful.shoreline.bll.IBLL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Axl
 */
public class LogWindowModel
{

    private final Cache cache;
    private final IBLL bll;

    private ObservableList<ConversionLog> fullList;

    public LogWindowModel()
    {
        cache = Cache.getInstance();
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
        return fullList;
    }
}
