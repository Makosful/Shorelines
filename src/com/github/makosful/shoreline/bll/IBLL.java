package com.github.makosful.shoreline.bll;

import com.github.makosful.shoreline.be.Config;
import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.be.User;
import com.github.makosful.shoreline.be.UserNew;
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

    public Task makeTask(Map<String, String> map, String path) throws BLLException;

    public ObservableList<Config> getAllConfigs() throws BLLException;

    public void savePassword(String userName, String password) throws BLLException;

    public String[] getPassword() throws BLLException;

    public String generatePassword() throws BLLException;

    public void saveLog(ConversionLog log) throws BLLException;

    public ObservableList<ConversionLog> getAllLogs() throws BLLException;

    public ObservableList<ConversionLog> searchLogs(String searchText, List<String> checked) throws BLLException;

    public boolean createUser(UserNew userNew) throws BLLException;

    public User login(String uName, String pass) throws BLLException;

    public boolean getUserByMail(String text) throws BLLException;
}
