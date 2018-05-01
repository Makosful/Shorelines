package com.github.makosful.shoreline.bll;

import com.github.makosful.shoreline.BE.ColumnObject;
import com.github.makosful.shoreline.BE.ExcelRow;
import java.util.HashMap;
import java.util.List;

/**
 * This interface will declare all the methods the Business Logic Layer's facade
 * will use.
 * All methods declared in this interface shall throw a BLLException
 *
 * @see BLLException
 *
 * @author Axl
 */
public interface IBLL
{

    public void readFromExcelFile(String file, HashMap<String, Integer> cellOrder, boolean conversion) throws BLLException;
    
    public List<ExcelRow> getExcelRowsList();

    public List<ColumnObject> getColumnNames();
}
