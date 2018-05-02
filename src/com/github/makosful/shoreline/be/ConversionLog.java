/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.be;

import java.util.Date;

/**
 *
 * @author Hussain
 */
public class ConversionLog
{
    private final int userId;
    private final String message;
    private final String fileName;
    private final String logType;
    private final Date date;
    
    public ConversionLog(int userId, String message, String fileName, String logType, Date date)
    {
        this.userId = userId;
        this.message = message;
        this.fileName = fileName;
        this.logType = logType;
        this.date = date;
    }

    public int getUserId()
    {
        return userId;
    }

    public String getMessage()
    {
        return message;
    }

    public String getFileName()
    {
        return fileName;
    }

    public String getLogType()
    {
        return logType;
    }

    public Date getDate()
    {
        return date;
    }
    
    
}
