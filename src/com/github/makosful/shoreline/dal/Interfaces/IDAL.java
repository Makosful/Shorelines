package com.github.makosful.shoreline.dal.Interfaces;

import com.github.makosful.shoreline.be.Config;
import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.dal.Exception.DALException;
import com.github.makosful.shoreline.dal.Json.JsonWriter;
import java.util.List;
import java.util.Map;
import javafx.collections.ObservableList;

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

    //<editor-fold defaultstate="collapsed" desc="File handling">
    public boolean fileLoad(String path) throws DALException;

    public List<String> fileGetHeader() throws DALException;

    public List<Map> fileGetValues(Map<String, String> keys) throws DALException;
    //</editor-fold>

    public void savePassword(String userName, String password) throws DALException;

    public String[] getPassword() throws DALException;

    public void saveConfig(String configName, ObservableList<String> items) throws DALException;
    
    public void createFile(List<Map> list, String path) throws DALException;

    public ObservableList<Config> getAllConfigs() throws DALException;

    public ObservableList<ConversionLog> getAllLogs(int userId) throws DALException;

    public void saveLog(ObservableList<ConversionLog> conversionLog) throws DALException;
    
    public void setReader(String path);
}
