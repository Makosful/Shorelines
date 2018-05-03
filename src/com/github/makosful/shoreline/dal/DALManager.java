package com.github.makosful.shoreline.dal;

import com.github.makosful.shoreline.dal.Excel.ExcelReader;
import com.github.makosful.shoreline.be.ColumnObject;
import com.github.makosful.shoreline.be.Config;
import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.be.ExcelRow;
import com.github.makosful.shoreline.dal.LoggingFolder.LoggingManager;
import com.github.makosful.shoreline.dal.RememberMe.StoreLogIn;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javafx.collections.ObservableList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;



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

    private final ExcelReader excel;
    private final JsonWriter jWriter;
    private final StoreLogIn storeLogIn;
    private final ConfigDAO cDAO;
    private final LoggingManager lDAO;

    public DALManager()
    {
        cDAO = new ConfigDAO();
        excel = new ExcelReader();
        jWriter = new JsonWriter();
        storeLogIn = new StoreLogIn();
        lDAO = new LoggingManager();
    }

    @Override
    public void readFromXlsFile(String file, HashMap<String, Integer> cellOrder, boolean conversion) throws DALException
    {
        try
        {
            excel.readFromXlsFile(file, cellOrder, conversion);
        }
        catch (IOException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
        catch (Exception ex)
        {
             throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public void readFromXlsxFile(String file, HashMap<String, Integer> cellOrder, boolean conversion) throws DALException
    {
        try
        {
            excel.readFromXlsxFiles(file, cellOrder, conversion);
        }
        catch (IOException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
        catch (Exception ex)
        {
             throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public List<ExcelRow> getExcelRowsList()
    {
        return excel.getExcelRowsList();
    }

    @Override
    public List<ColumnObject> getColumnNames()
    {
        return excel.getColumnNames();
    }

    @Override
    public void saveConfig(String configName, ObservableList<ColumnObject> items) throws DALException
    {
        try
        {
            int configId = cDAO.saveConfiguration(configName);
            
            for(ColumnObject column : items){
                cDAO.saveConfigColumns(configId, column);
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
        for(ConversionLog log : conversionLog)
        {
            try
            {
                lDAO.saveLog(log);
            }
            catch (SQLException ex)
            {
                throw new DALException(ex.getLocalizedMessage(), ex);
            }
        }
        
    }


}
