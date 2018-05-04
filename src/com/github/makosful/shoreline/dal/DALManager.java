package com.github.makosful.shoreline.dal;

import com.github.makosful.shoreline.be.Config;
import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.dal.Database.ConfigDAO;
import com.github.makosful.shoreline.dal.Database.LogDAO;
import com.github.makosful.shoreline.dal.Excel.ExcelReader;
import com.github.makosful.shoreline.dal.Exception.DALException;
import com.github.makosful.shoreline.dal.Exception.ReaderException;
import com.github.makosful.shoreline.dal.Interfaces.IDAL;
import com.github.makosful.shoreline.dal.Interfaces.IReader;
import com.github.makosful.shoreline.dal.Json.JsonReader;
import com.github.makosful.shoreline.dal.Json.JsonWriter;
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

    private final JsonWriter jWriter;
    private final IReader jReader;
    private final IReader excel;

    private final StoreLogIn storeLogIn;
    private final ConfigDAO cDAO;
    private final LogDAO lDAO;

    public DALManager()
    {
        cDAO = new ConfigDAO();
        excel = new ExcelReader();
        jWriter = new JsonWriter();
        jReader = new JsonReader();
        storeLogIn = new StoreLogIn();
        lDAO = new LogDAO();
    }

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

    @Override
    public ObservableList<ConversionLog> getAllLogs(int userId) throws DALException
    {
        try
        {
            return lDAO.getAllLogs(userId);
        }
        catch (SQLException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Excel">
    @Override
    public boolean excelLoad(String path) throws DALException
    {
        try
        {
            return excel.loadFile(path);
        }
        catch (ReaderException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public List<String> excelGetHeader() throws DALException
    {
        try
        {
            return excel.getHeaders();
        }
        catch (ReaderException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public List<Map> excelGetValues(Map<String, String> keys) throws DALException
    {
        try
        {
            return excel.getValues(keys);
        }
        catch (ReaderException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="JSON">
    @Override
    public boolean jsonLoad(String path) throws DALException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<String> jsonGetHeader() throws DALException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Map> jsonGetValues(Map<String, String> keys) throws DALException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    //</editor-fold>
}
