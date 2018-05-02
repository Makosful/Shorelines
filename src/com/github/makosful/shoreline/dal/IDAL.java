package com.github.makosful.shoreline.dal;

import com.github.makosful.shoreline.be.ColumnObject;
import com.github.makosful.shoreline.be.ExcelRow;
import java.util.HashMap;
import java.util.List;

/**
 * An interface holding all methods a DAL Facade will need to handle.
 * All methods declared here should throw a DALException.
 *
 * @see DALException The only Exception that may be thrown from this class
 *
 * @author Axl
 */
public interface IDAL
{

    public void readFromXlsFile(String file, HashMap<String, Integer> cellOrder, boolean conversion) throws DALException;

    public void readFromXlsxFile(String file, HashMap<String, Integer> cellOrder, boolean conversion) throws DALException;
    
    public List<ExcelRow> getExcelRowsList();

    public List<ColumnObject> getColumnNames();

    public void savePassword(String userName, String password) throws DALException;

    public String[] getPassword() throws DALException;
    
}
