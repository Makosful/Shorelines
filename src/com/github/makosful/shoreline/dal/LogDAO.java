/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.dal;

import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.dal.DataBaseConnector.DataBaseConnector;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author B
 */
public class LogDAO
{
    
    DataBaseConnector db;
    
    public LogDAO()
    {
        db = new DataBaseConnector();
    }

    
    /**
     * Get all logs for a specific user
     * @param userId
     * @return
     * @throws SQLException 
     */
    public ObservableList<ConversionLog> getAllLogs(int userId) throws SQLException
    {
        try (Connection con = db.getConnection())   
        {
            
            ObservableList<ConversionLog> allLogs = FXCollections.observableArrayList();
            String sql = "SELECT * FROM Logs WHERE UserId = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next())
            {   
                ConversionLog conversionLog = new ConversionLog(rs.getInt("UserId"), 
                                                                rs.getString("Message"), 
                                                                rs.getString("FileName"), 
                                                                rs.getString("LogType"), 
                                                                rs.getDate("Date"));
                allLogs.add(conversionLog);
            }

            return allLogs;
        }
        catch (SQLException ex)
        {
            throw new SQLException(ex.getMessage());
        }
    }

    public void saveLog(ConversionLog conversionLog) throws SQLException
    {
        try(Connection con = db.getConnection())
        {
            String sql = "INSERT INTO Logs (UserId, Message, FileName, LogType, Date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            
            int i = 1;
            ps.setInt(i++, conversionLog.getUserId());
            ps.setString(i++, conversionLog.getMessage());
            ps.setString(i++, conversionLog.getFileName());
            ps.setString(i++, conversionLog.getLogType());
            ps.setDate(i++, (Date) conversionLog.getDate());
            
            ps.executeUpdate();
        }
        catch(SQLException ex)
        {
            throw new SQLException(ex.getMessage());
        }
    }
    
}
