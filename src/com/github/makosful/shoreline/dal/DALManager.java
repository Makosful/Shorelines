package com.github.makosful.shoreline.dal;

import com.github.makosful.shoreline.be.Config;
import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.dal.Database.ConfigDAO;
import com.github.makosful.shoreline.dal.Excel.ExcelReader;
import com.github.makosful.shoreline.dal.Exception.DALException;
import com.github.makosful.shoreline.dal.Exception.ReaderException;
import com.github.makosful.shoreline.dal.Interfaces.IDAL;
import com.github.makosful.shoreline.dal.Interfaces.IReader;
import com.github.makosful.shoreline.dal.Json.JsonReader;
import com.github.makosful.shoreline.dal.Json.JsonWriter;
import com.github.makosful.shoreline.dal.LoggingFolder.LogContext;
import com.github.makosful.shoreline.dal.LoggingFolder.LogDBDAO;
import com.github.makosful.shoreline.dal.LoggingFolder.LogFileDAO;
import com.github.makosful.shoreline.dal.RememberMe.StoreLogIn;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javafx.collections.ObservableList;

/**
 * A facade for the Data Access Layer as a whole.
 * The sole responsibility for this class is to redirect data requests to
 * classes specialized in handling that data
 *
 * @see IDAL The interface for Data Access Layer facades
 * @see DALException The sole exception this class may throw
 *
 * @author Axl
 */
public class DALManager implements IDAL
{

    private JsonWriter jWriter;
    private IReader reader;
    private IReader jReader;
    private IReader excel;

    private StoreLogIn storeLogIn;
    private ConfigDAO cDAO;
    private LogDBDAO lDAO;
    private AbstractFactoryReader readerFactory;

    public DALManager()
    {
        cDAO = new ConfigDAO();
        jWriter = new JsonWriter();
        readerFactory = FactoryProducer.getFactory();

        jReader = new JsonReader();

        storeLogIn = new StoreLogIn();
        lDAO = new LogDBDAO();

    }

    //<editor-fold defaultstate="collapsed" desc="Core File In">
    @Override
    public boolean fileLoad(String path) throws DALException
    {
        try
        {
            setReader(path);
            return reader.loadFile(path);
        }
        catch (ReaderException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public void setReader(String path)
    {
        reader = readerFactory.getReader(path);
    }

    @Override
    public List<String> fileGetHeader() throws DALException
    {
        try
        {
            return jReader.getHeaders();
        }
        catch (ReaderException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public List<Map> fileGetValues(Map<String, String> keys) throws DALException
    {
        try
        {
            return jReader.getValues(keys);
        }
        catch (ReaderException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Core File Out">
    @Override
    public void jsonAdd(Map jsonObj) throws DALException
    {
        jWriter.addObject(jsonObj);
    }

    @Override
    public void jsonWrite() throws DALException
    {
        try
        {
            jWriter.write();
        }
        catch (IOException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Config">
    @Override
    public void saveConfig(String configName, ObservableList<String> items) throws DALException
    {
        try
        {
            int configId = cDAO.saveConfiguration(configName);

            for (String column : items)
            {
                //cDAO.saveConfigColumns(configId, column);
            }
        }
        catch (SQLException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public ObservableList<Config> getAllConfigs() throws DALException
    {
        try
        {
            return cDAO.getAllConfigs();
        }
        catch (SQLException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Password">
    @Override
    public void savePassword(String userName, String password) throws DALException
    {
        try
        {
            storeLogIn.savePassword(userName, password);
        }
        catch (IOException ex)
        {
            throw new DALException("Error writing file");
        }
    }

    @Override
    public String[] getPassword() throws DALException
    {
        try
        {
            return storeLogIn.getPassword();
        }
        catch (FileNotFoundException ex)
        {
            throw new DALException("Error with password.txt");
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Logs">
    @Override
    public ObservableList<ConversionLog> getAllLogs(int userId) throws DALException
    {
        try
        {
            return lDAO.getLogs(userId);
        }
        catch (SQLException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public void saveLog(ObservableList<ConversionLog> conversionLog) throws DALException
    {
        LogContext logContextDB = new LogContext(lDAO);
        LogContext logContextFile = new LogContext(new LogFileDAO());

        for (ConversionLog log : conversionLog)
        {
            logContextDB.saveLog(log);
            logContextFile.saveLog(log);
        }

    }
    //</editor-fold>
}
