package com.github.makosful.shoreline.bll;

import com.github.makosful.shoreline.be.ColumnObject;
import com.github.makosful.shoreline.be.ExcelRow;
import java.util.HashMap;
import java.util.List;
import javafx.collections.ObservableList;

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

    public void saveConfig(String configName, ObservableList<ColumnObject> items);
}
