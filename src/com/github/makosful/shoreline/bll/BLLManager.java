package com.github.makosful.shoreline.bll;

import com.github.makosful.shoreline.be.ColumnObject;
import com.github.makosful.shoreline.be.Config;
import com.github.makosful.shoreline.be.ExcelRow;
import com.github.makosful.shoreline.dal.DALException;
import com.github.makosful.shoreline.dal.DALManager;
import com.github.makosful.shoreline.dal.IDAL;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 * The facade for the Business Logic Layer. The sole purpose of this class is to
 * send requests along to classes specialized classes, and should not to any
 * logic itself.
 * This class may only throw the BLLException, and nothing else.
 * All data requests must pass through this layer. If the request doesn't
 * require any kind of logic, the request may go straight to DAL from here
 *
 * @see IBLL The interface used for this layer's facades
 * @see BLLException The sole Exception this class may throw
 * @see IDAL The interface for the Data Access Layer
 *
 * @author Axl
 */
public class BLLManager implements IBLL
{

    private final IDAL dal;

    private final TaskManager tasks;

    public BLLManager()
    {
        dal = new DALManager();

        tasks = new TaskManager();
    }

    @Override
    public void readFromExcelFile(String file, HashMap<String, Integer> cellOrder, boolean conversion) throws BLLException
    {
        try
        {
            dal.readFromXlsxFile(file, cellOrder, conversion);
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public List<ExcelRow> getExcelRowsList() throws BLLException
    {
    
        try
        {
            return dal.getExcelRowsList();
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
   
    }

    @Override
    public List<ColumnObject> getColumnNames() throws BLLException
    {

        try
        {
            return dal.getColumnNames();
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }

    }

    @Override
    public void addTask(List<ExcelRow> list)
    {
        tasks.addTask(list);
    }

    @Override
    public void saveConfig(String configName, ObservableList<ColumnObject> items) throws BLLException
    {
        try
        {
            dal.saveConfig(configName, items);
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }

    }

    @Override
    public ObservableList<Config> getAllConfigs() throws BLLException
    {
        try
        {
            return dal.getAllConfigs();
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }
}
