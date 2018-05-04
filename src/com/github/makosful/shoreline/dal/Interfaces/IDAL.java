package com.github.makosful.shoreline.dal.Interfaces;

import com.github.makosful.shoreline.be.ColumnObject;
import com.github.makosful.shoreline.be.Config;
import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.dal.Exception.DALException;
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

    //<editor-fold defaultstate="collapsed" desc="comment">
    public boolean excelLoad(String path) throws DALException;

    public List<String> excelGetHeader() throws DALException;

    public List<Map> excelGetValues(Map<String, String> keys) throws DALException;

    public void savePassword(String userName, String password) throws DALException;

    public String[] getPassword() throws DALException;

    public void saveConfig(String configName, ObservableList<ColumnObject> items) throws DALException;

    public void jsonAdd(Map jsonObj) throws DALException;

    public void jsonWrite() throws DALException;

    public ObservableList<Config> getAllConfigs() throws DALException;

    public ObservableList<ConversionLog> getAllLogs(int userId) throws DALException;

}
