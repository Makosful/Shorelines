/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.dal.LoggingFolder;

import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.dal.DALException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


/**
 *
 * @author B
 */
public class LogFileDAO implements ILog
{

    @Override
    public void saveLog(ConversionLog conversionLog) throws DALException
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("")))
        {
            writer.write("------------------------------------------------------");
            writer.newLine();
            writer.write("Log Date: "+conversionLog.getDate());
            writer.newLine();
            writer.write("User id: "+conversionLog.getUserId());
            writer.newLine();
            writer.write("Message: "+conversionLog.getMessage());
            writer.newLine();
            writer.write("File: "+conversionLog.getFileName());
            writer.newLine();
            writer.write("Message:"+conversionLog.getLogType());
            writer.newLine();
            writer.write("------------------------------------------------------");
            writer.newLine();
            writer.newLine();
            writer.close();
        }
        catch (IOException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }
    
}
