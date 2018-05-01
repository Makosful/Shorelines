package com.github.makosful.shoreline.dal;

import java.io.IOException;

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
