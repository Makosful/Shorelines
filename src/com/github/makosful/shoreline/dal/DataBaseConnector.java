/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;

/**
 *
 * @author B
 */
class DataBaseConnector
{
    private final SQLServerDataSource dataSource;

    /**
     * Constructor
     * Setting the relevant info to the database connection.
     */
    public DataBaseConnector()
    {
        
        dataSource = new SQLServerDataSource();

        dataSource.setServerName("EASV-DB2");
        dataSource.setPortNumber(1433);
        dataSource.setDatabaseName("Shorelines");
        dataSource.setUser("CS2017A_8");
        dataSource.setPassword("CS2017A_8");
    }

    /**
     * Getting the connection from the database.
     *
     * @return Returns a Connection
     *
     * @throws SQLServerException Throws an exception if it fails to connect to
     *                            the database
     */
    public Connection getConnection() throws SQLServerException
    {
        return dataSource.getConnection();
    }
}
