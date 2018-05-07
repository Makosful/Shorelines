package com.github.makosful.shoreline.bll;

import com.github.makosful.shoreline.be.Config;
import com.github.makosful.shoreline.be.ExcelRow;
import java.util.List;
import java.util.Map;
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

    public boolean loadFile(String path) throws BLLException;

    public List<String> getHeaders() throws BLLException;

    public List<Map> getValues(Map<String, String> keys) throws BLLException;

    public void saveConfig(String configName, ObservableList<String> items) throws BLLException;

    public void addTask(List<ExcelRow> excelRowsList) throws BLLException;

    public ObservableList<Config> getAllConfigs() throws BLLException;

    public void savePassword(String userName, String password) throws BLLException;

    public String[] getPassword() throws BLLException;

    public String generatePassword() throws BLLException;

}
