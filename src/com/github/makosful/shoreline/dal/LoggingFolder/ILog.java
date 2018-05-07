package com.github.makosful.shoreline.dal.LoggingFolder;

import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.dal.Exception.DALException;

/**
 *
 * @author B
 */
public interface ILog
{

    /**
     * Save log to either database or file
     *
     * @param conversionLog
     *
     * @throws DALException
     */
    public void saveLog(ConversionLog conversionLog) throws DALException;

}
