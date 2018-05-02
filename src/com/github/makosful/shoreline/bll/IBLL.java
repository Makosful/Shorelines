package com.github.makosful.shoreline.bll;

import com.github.makosful.shoreline.be.ColumnObject;
import com.github.makosful.shoreline.be.ExcelRow;
import com.github.makosful.shoreline.dal.DALException;
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

    public List<ExcelRow> getExcelRowsList() throws BLLException;

    public List<ColumnObject> getColumnNames() throws BLLException;

    public void savePassword(String userName, String password) throws BLLException;

    public String[] getPassword() throws BLLException;

    public void saveConfig(String configName, ObservableList<ColumnObject> items);

    public void addTask(List<ExcelRow> excelRowsList);

    public String generatePassword() throws BLLException;

}
