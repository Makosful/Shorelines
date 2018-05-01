package com.github.makosful.shoreline.dal;

import com.github.makosful.shoreline.be.ColumnObject;
import com.github.makosful.shoreline.be.ExcelRow;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javafx.collections.ObservableList;
import java.util.Map;


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
    private ConfigDAO cDAO;

    public DALManager()
    {
        excel = new ExcelReader();
        jWriter = new JsonWriter();
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
    public void saveConfig(String configName, ObservableList<ColumnObject> items)
    {
        int configId = cDAO.saveConfiguration(configName);
        
        for(ColumnObject column : items){
            cDAO.saveConfigColumns(configId, column);
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

}
