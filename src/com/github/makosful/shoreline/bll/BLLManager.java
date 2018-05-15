package com.github.makosful.shoreline.bll;

import com.github.makosful.shoreline.be.Config;
import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.dal.DALManager;
import com.github.makosful.shoreline.dal.Exception.DALException;
import com.github.makosful.shoreline.dal.Interfaces.IDAL;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

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
    private final Log log;
    private final TaskManager tasks;
    private final PasswordGenerator pass;

    public BLLManager()
    {
        dal = new DALManager();
        log = new Log();
        tasks = new TaskManager();
        pass = new PasswordGenerator(12);
    }

    @Override
    public void savePassword(String userName, String password) throws BLLException
    {
        try
        {
            dal.savePassword(userName, password);
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getMessage());
        }
    }

    @Override
    public String[] getPassword() throws BLLException
    {
        try
        {
            return dal.getPassword();
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getMessage());
        }
    }

    @Override
    public Task makeTask(Map<String, String> map, String path) throws BLLException
    {
        try 
        {
            return tasks.makeTask(map, path);
        }
        catch (BLLException ex)
        {
           throw new BLLException(ex.getMessage());
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="Configuration">
    @Override
    public void saveConfig(String configName, ObservableList<String> items) throws BLLException
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
    //</editor-fold>
    
    @Override
    public String generatePassword() throws BLLException
    {
        return pass.nextString();
    }

    @Override
    public boolean loadFile(String path) throws BLLException
    {
        try
        {
            dal.setReader(path);
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
        try
        {
            
            return dal.fileLoad(path);
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public List<String> getHeaders() throws BLLException
    {
        try
        {
            return dal.fileGetHeader();
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public List<Map> getValues(Map<String, String> map) throws BLLException
    {
        try
        {
            return dal.fileGetValues(map);
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }
    
    //<editor-fold defaultstate="collapsed" desc="Logs">
    @Override
    public void saveLog(ConversionLog conversionLog) throws BLLException
    {
        log.saveLog(conversionLog);
    }

    @Override
    public ObservableList<ConversionLog> getAllLogs() throws BLLException
    {
        return log.getAllLogs(0);
    }

    @Override
    public ObservableList<ConversionLog> searchLogs(String searchText, List<String> checked) throws BLLException
    {
        try
        {
            return dal.searchLogs(searchText, checked);
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }
    //</editor-fold>
}
