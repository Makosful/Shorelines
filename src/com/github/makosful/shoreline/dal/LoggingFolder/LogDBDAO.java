package com.github.makosful.shoreline.dal.LoggingFolder;

import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.dal.Database.DataBaseConnector;
import com.github.makosful.shoreline.dal.Exception.DALException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Hussain
 */
public class LogDBDAO implements ILog
{

    DataBaseConnector dbConnector;

    public LogDBDAO()
    {
        dbConnector = new DataBaseConnector();
    }

    /**
     * Making a log.
     *
     * @param log
     *
     * @throws com.github.makosful.shoreline.dal.Exception.DALException
     */
    @Override
    public void saveLog(ConversionLog log) throws DALException
    {
        try (Connection con = dbConnector.getConnection())
        {
            int i = 1;
            String sql = "INSERT INTO Logs VALUES(?, ?, ?, ?, GETDATE())";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(i++, log.getUserId());
            pstmt.setString(i++, log.getMessage());
            pstmt.setString(i++, log.getLogType());
            pstmt.setString(i++, log.getFileName());
            pstmt.executeUpdate();
        }
        catch (SQLException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }

    }

    /**
     * Getting all logs from DB.
     *
     * @param userId
     *
     * @return
     *
     * @throws SQLException
     */
    public ObservableList<ConversionLog> getLogs(int userId) throws SQLException
    {
        ObservableList<ConversionLog> logs = FXCollections.observableArrayList();

        try (Connection con = dbConnector.getConnection())
        {
            String sql = "SELECT * FROM Logs WHERE UserId = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                ConversionLog conversionLogger = new ConversionLog();

                conversionLogger.setUserId(userId);
                conversionLogger.setMessage(rs.getString("Message"));
                conversionLogger.setFileName(rs.getString("FileName"));
                conversionLogger.setLogType(rs.getString("LogType"));
                conversionLogger.setDate(rs.getDate("Date"));

                logs.add(conversionLogger);
            }
            return logs;
        }
        catch (SQLException ex)
        {
            throw new SQLException(ex.getMessage());
        }

    }


    /**
     * Get logs based on the seachtext, using the LIKE keyword, so logs having the seachtext mid-sentence,
     * will also be selected  
     * @param searchText 
     * @return  
     * @throws java.sql.SQLException 
     */
    public ObservableList<ConversionLog> searchLogs(String searchText) throws SQLException
    {
        ObservableList<ConversionLog> logs = FXCollections.observableArrayList();
        
        try (Connection con = dbConnector.getConnection())
        {
            String sql = "SELECT * FROM Logs WHERE UserId LIKE ? OR Message LIKE ? OR FileName LIKE ? OR LogType LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, "%"+searchText+"%");
            ps.setString(i++, "%"+searchText+"%");
            ps.setString(i++, "%"+searchText+"%");
            ps.setString(i++, "%"+searchText+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                ConversionLog conversionLogger = new ConversionLog();

                conversionLogger.setUserId(0);
                conversionLogger.setMessage(rs.getString("Message"));
                conversionLogger.setFileName(rs.getString("FileName"));
                conversionLogger.setLogType(rs.getString("LogType"));
                conversionLogger.setDate(rs.getDate("Date"));

                logs.add(conversionLogger);
            }
            return logs;
        }
        catch (SQLException ex)
        {
            throw new SQLException(ex.getMessage());
        }
    }
}
