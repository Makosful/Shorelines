package com.github.makosful.shoreline.dal.LoggingFolder;

import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.dal.Exception.DALException;

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
