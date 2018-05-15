package com.github.makosful.shoreline.bll;

import com.github.makosful.shoreline.be.Config;
import com.github.makosful.shoreline.be.ConversionLog;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

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

    public Task makeTask(List<Map> fileDataList, String path) throws BLLException;

    public ObservableList<Config> getAllConfigs() throws BLLException;

    public void savePassword(String userName, String password) throws BLLException;

    public String[] getPassword() throws BLLException;

    public String generatePassword() throws BLLException;

    public void saveLog(ConversionLog log) throws BLLException;
    
    public ObservableList<ConversionLog> getAllLogs() throws BLLException;

    public ObservableList<ConversionLog> searchLogs(String searchText, List<String> checked) throws BLLException;
}
