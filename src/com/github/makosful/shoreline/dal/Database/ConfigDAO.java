/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.dal.Database;

import com.github.makosful.shoreline.be.Config;
import com.github.makosful.shoreline.dal.Database.DataBaseConnector;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author B
 */
public class ConfigDAO
{
    DataBaseConnector db;
    
    public ConfigDAO()
    {
        db = new DataBaseConnector();
    }
    /**
     * Insert the new configuration in the db with its name and return its id(generated keys) 
     * @param configName
     * @return 
     * @throws java.sql.SQLException 
     */
    public int saveConfiguration(String configName) throws SQLException
    {
        try (Connection con = db.getConnection())
        {

            String sqlInsert = "INSERT INTO Config (ConfigName) VALUES (?)";
            PreparedStatement preparedStatementInsert = con.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            preparedStatementInsert.setString(1, configName);
     
            preparedStatementInsert.executeUpdate();

            ResultSet rs = preparedStatementInsert.getGeneratedKeys();

            int id;
            rs.next();
            id = rs.getInt(1);
            return id;
        }
        catch (SQLException ex)
        {
            throw new SQLException(ex.getMessage());
        }
        
    }

    /**
     * save the column in db
     * @param configId
     * @param column 
     * @throws java.sql.SQLException 
     */
    public void saveConfigColumns(int configId, String column) throws SQLException
    {
        try (Connection con = db.getConnection())   
        {
            String sql = "INSERT INTO ConfigColumn (ConfigId, ColumnName) VALUES(?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, configId);
            ps.setString(2, column);
            
            ps.executeUpdate();
        }
        catch (SQLException ex)
        {
            throw new SQLException(ex.getMessage());
        }
        
        
    }

    /**
     * Gets all configurations with selected columns 
     */
    public ObservableList<Config> getAllConfigs() throws SQLException
    {
        try (Connection con = db.getConnection())   
        {
            
            ObservableList<Config> allConfigs = FXCollections.observableArrayList();
            String sql = "SELECT "
                       + "ConfigName, "
                       + "Id "
                       + "FROM Config";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            
     
            while (rs.next())
            {
                    
                Config config = new Config();

                config.setId(rs.getInt("Id"));

                config.setName(rs.getString("configName"));

                ObservableList<String> columns = createColumns(rs.getInt("Id"));

                config.setChosenColumns(columns);

                allConfigs.add(config);
            }

            return allConfigs;
        }
        catch (SQLException ex)
        {
            throw new SQLException(ex.getMessage());
        }
    }

    /**
     * Create all column objects for a specific config
     * @param configId
     * @return 
     */
    private ObservableList<String> createColumns(int configId) throws SQLException
    {
        try (Connection con = db.getConnection())   
        {
         String sql = "SELECT "
                       + "ColumnName "
                       + "FROM ConfigColumn "
                       + "WHERE ConfigId = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, configId);
            ResultSet rs = preparedStatement.executeQuery();

            ObservableList<String> columns = FXCollections.observableArrayList();
     
            while (rs.next())
            {
                columns.add(rs.getString("ColumnName"));
            }
            
            return columns;
        
        }
        catch (SQLException ex)
        {
            throw new SQLException(ex.getMessage());
            
        }
    }

}
