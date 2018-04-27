package com.github.makosful.shoreline.dal;

import java.io.IOException;
import java.util.HashMap;
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
    private final JsonWriter json;

    public DALManager()
    {
        excel = new ExcelReader();
        json = new JsonWriter();

        try
        {
            HashMap m = new HashMap();
            m.put("FirstName", "Malthe");
            m.put("LastName", "Schwartz");
            json.put("ID", 002);
            json.put("Name", m);
            json.write();
        }
        catch (IOException ex)
        {
            Logger.getLogger(DALManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void readFromXlsFile(String file) throws DALException
    {
        try
        {
            excel.readFromXlsFile(file);
        }
        catch (IOException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public void readFromXlsxFile(String file) throws DALException
    {
        try
        {
            excel.readFromXlsxFiles(file);
        }
        catch (IOException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }
}
