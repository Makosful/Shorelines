/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.dal.LoggingFolder;

import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.dal.DALException;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author B
 */
public interface ILog
{
    /**
     * Save log to either database or file
     * @param conversionLog
     * @throws DALException 
     */
    public void saveLog(ConversionLog conversionLog) throws DALException;
    
}
