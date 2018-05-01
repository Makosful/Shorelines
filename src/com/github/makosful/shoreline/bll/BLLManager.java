package com.github.makosful.shoreline.bll;

import com.github.makosful.shoreline.BE.ColumnObject;
import com.github.makosful.shoreline.BE.ExcelRow;
import com.github.makosful.shoreline.dal.DALException;
import com.github.makosful.shoreline.dal.DALManager;
import com.github.makosful.shoreline.dal.IDAL;
import java.util.HashMap;
import java.util.List;

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

    public BLLManager()
    {
        dal = new DALManager();
    }

    @Override
    public void readFromExcelFile(String file, HashMap<String, Integer> cellOrder) throws BLLException
    {
        try
        {
            dal.readFromXlsxFile(file, cellOrder);
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public List<ExcelRow> getExcelRowsList()
    {
        return dal.getExcelRowsList();
    }

    @Override
    public List<ColumnObject> getColumnNames()
    {
        return dal.getColumnNames();
    }
}
