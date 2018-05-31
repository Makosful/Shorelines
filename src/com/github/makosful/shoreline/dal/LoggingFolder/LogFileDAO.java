package com.github.makosful.shoreline.dal.LoggingFolder;

import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.dal.Exception.DALException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author B
 */
public class LogFileDAO implements ILog
{

    @Override
    public void saveLog(ConversionLog log) throws DALException
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true)))
        {
            writer.write("------------------------------------------------------");
            writer.newLine();
            writer.write("Log Date: " + new Date());
            writer.newLine();
            writer.write("User email: " + log.getEmail());
            writer.newLine();
            writer.write("Message: " + log.getMessage());
            writer.newLine();
            writer.write("File: " + log.getFileName());
            writer.newLine();
            writer.write("LogType:" + log.getLogType());
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
