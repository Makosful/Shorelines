/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.dal.LoggingFolder;

import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.dal.DataBaseConnector.DataBaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hussain
 */
public class LoggingManager
{

    DataBaseConnector dbConnector;

    public LoggingManager()
    {
        dbConnector = new DataBaseConnector();
    }
    /**
     * Making a log.
     * @param userId
     * @param message
     * @param fileName
     * @param logType
     * @param date
     * @throws SQLException 
     */
    public void makeLog(int userId, String message, String fileName, String logType) throws SQLException
    {
        try (Connection con = dbConnector.getConnection())
        {
            int i = 1;
            String sql = "Insert Into Logs values(?, ?, ?, ?, GETDATE())";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(i++, userId);
            pstmt.setString(i++, message);
            pstmt.setString(i++, fileName);
            pstmt.setString(i++, logType);
            pstmt.executeUpdate();
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
            throw new SQLException(ex.getMessage());
        }

    }
    /**
     * Getting all logs from DB.
     * @return
     * @throws SQLException 
     */
    public List<ConversionLog> getLogs() throws SQLException
    {
        List<ConversionLog> logs = new ArrayList();

        try (Connection con = dbConnector.getConnection())
        {
            String sql = "SELECT * FROM Logs";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                int userId = rs.getInt("userID");
                String message = rs.getString("Message");
                String logType = rs.getString("LogType");
                String fileName = rs.getString("FileName");
                Date date = rs.getDate("Date");

                ConversionLog conversionLogger = new ConversionLog(userId, message, logType, fileName, date);

                logs.add(conversionLogger);
            }
            return logs;
        }
        catch (SQLException ex)
        {
             System.out.println(ex.getMessage());
           throw new SQLException(ex.getMessage());
        }

    }
}
