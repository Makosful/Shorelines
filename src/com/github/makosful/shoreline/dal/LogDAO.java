/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.dal;

import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.dal.DataBaseConnector.DataBaseConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
}
