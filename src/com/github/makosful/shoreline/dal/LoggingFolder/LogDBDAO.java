package com.github.makosful.shoreline.dal.LoggingFolder;

import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.dal.Database.DataBaseConnector;
import com.github.makosful.shoreline.dal.Exception.DALException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
     * @return ObservableList<ConversionLog> logs
     * @throws SQLException
     */
    public ObservableList<ConversionLog> getLogs() throws SQLException
    {
        ObservableList<ConversionLog> logs = FXCollections.observableArrayList();

        try (Connection con = dbConnector.getConnection())
        {
            String sql = "SELECT * FROM Logs";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                ConversionLog conversionLogger = new ConversionLog();

                conversionLogger.setUserId(rs.getInt("UserId"));
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
            System.out.println(ex.getMessage());
            throw new SQLException(ex.getMessage());
        }

    }

    /**
     * Get logs based on the seachtext, using the LIKE keyword, so logs having the seachtext mid-sentence,
     * will also be selected  
     * @param searchText 
     * @param searchCriteria 
     * @param checkedSize 
     * @return  ObservableList -> ConversionLog
     * @throws java.sql.SQLException 
     */
    public ObservableList<ConversionLog> searchLogs(String searchText, String searchCriteria, int checkedSize) throws SQLException
    {
        ObservableList<ConversionLog> logs = FXCollections.observableArrayList();
        
        try (Connection con = dbConnector.getConnection())
        {
            String sql = "SELECT * FROM Logs WHERE "+searchCriteria;
            PreparedStatement ps = con.prepareStatement(sql);

            for(int i = 1; i <= checkedSize; i++)
            {
                ps.setString(i, "%"+searchText+"%");
            }
            
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
    
    /**
     * 
     * @param searchText
     * @param checked
     * @return
     * @throws SQLException 
     */
    public ObservableList<ConversionLog> prepareLogSeach(String searchText, List<String> checked) throws SQLException
    {
        String searchCriteria;
        int checkedSize;
        //if the checkboxes are checked, 
        //create a search with the selected seachcriteria/tablecolumns to seach in
        if(!checked.isEmpty())
        {
            searchCriteria = createSqlSearchString(checked);
            checkedSize = checked.size();
        }
        else
        {
            //if the checkboxes are not checked, create a regular search with all the tablecolumns,
            // first step is to add all the column names to the arraylist
            checked.add("UserId");
            checked.add("message");
            checked.add("FileName");
            checked.add("LogType");
            
            //create the string which is appended to the regular non-dynamic sql string 
            searchCriteria = createSqlSearchString(checked);
            
            checkedSize = checked.size();
            
            //clear the arraylist so it can be used again whithout it containing duplicates
            checked.clear();
        }
        
        return searchLogs(searchText, searchCriteria, checkedSize);
    }
    
    /**
     * Creates a sql string based on the seach criteria in the arraylist 'checked'. 
     * The arraylist contains the column names, which will seached in.
     * @param checked
     * @return sql string with the tables to be seached in  
     */
    private String createSqlSearchString(List<String> checked)
    {
        String searchCriteria = "";
        
        for(String tableColumn : checked)
        {
            if(!searchCriteria.isEmpty())
            {
                searchCriteria += "OR ";
            }
            searchCriteria += tableColumn+" LIKE ? ";
        }
        
        return searchCriteria;
    }
}
