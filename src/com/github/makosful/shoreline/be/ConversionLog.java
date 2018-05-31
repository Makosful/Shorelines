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
    private String email;
    private String message;
    private String fileName;
    private String logType;
    private Date date;

    public ConversionLog(String email, String message, String logType, String fileName, Date date)
    {
        this.email = email;
        this.message = message;
        this.logType = logType;
        this.fileName = fileName;
        this.date = date;
    }

    public ConversionLog()
    {
        
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getLogType()
    {
        return logType;
    }

    public void setLogType(String logType)
    {
        this.logType = logType;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

}
