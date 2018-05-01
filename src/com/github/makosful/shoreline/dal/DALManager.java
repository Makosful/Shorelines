package com.github.makosful.shoreline.dal;

import com.github.makosful.shoreline.BE.ColumnObject;
import com.github.makosful.shoreline.BE.ExcelRow;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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

    public DALManager()
    {
        excel = new ExcelReader();
        jWriter = new JsonWriter();

        try
        {
            jWriter.put("MyInt", 6.9f);
            jWriter.put("MyFloat", 6.9f);
            jWriter.remove("Myfloat");
            jWriter.write();
        }
        catch (IOException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void readFromXlsFile(String file, HashMap<String, Integer> cellOrder) throws DALException
    {
        try
        {
            excel.readFromXlsFile(file, cellOrder);
        }
        catch (IOException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public void readFromXlsxFile(String file, HashMap<String, Integer> cellOrder) throws DALException
    {
        try
        {
            excel.readFromXlsxFiles(file, cellOrder);
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
    
    
}
