/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.dal.LoggingFolder;

import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.dal.DALException;

/**
 *
 * @author B
 */
public class LogContext
{
    private ILog log;

    public LogContext(ILog log)
    {
        this.log = log;
    }
    
    public void saveLog(ConversionLog conversionLog) throws DALException
    {
        log.saveLog(conversionLog);
    }
}
