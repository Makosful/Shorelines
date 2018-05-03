/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.bll;

import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.dal.DALException;
import com.github.makosful.shoreline.dal.DALManager;
import com.github.makosful.shoreline.dal.IDAL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author B
 */
public class Log
{

    private final IDAL dal;
    public Log()
    {
        dal = new DALManager();
    }
    
    
    /**
     * Methad tp pass down the conversionLog to the db to be stored
     * @param conversionLog
     * @throws BLLException 
     */
    public void saveLog(ObservableList<ConversionLog> conversionLog) throws BLLException
    {
        try
        {
            dal.saveLog(conversionLog);
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }
    
    
    /**
     * Method to call down to the logging manager for getting all the logs in the db
     * for a specific user
     * @param userId
     * @return
     * @throws BLLException 
     */
    public ObservableList<ConversionLog> getAllLogs(int userId) throws BLLException
    {
        try
        {
            return dal.getAllLogs(userId);
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }
    
    
}