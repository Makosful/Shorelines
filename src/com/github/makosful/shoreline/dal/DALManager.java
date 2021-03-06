package com.github.makosful.shoreline.dal;

import com.github.makosful.shoreline.be.Config;
import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.be.User;
import com.github.makosful.shoreline.dal.Database.ConfigDAO;
import com.github.makosful.shoreline.dal.Database.UserDAO;
import com.github.makosful.shoreline.dal.Exception.DALException;
import com.github.makosful.shoreline.dal.Exception.ReaderException;
import com.github.makosful.shoreline.dal.Interfaces.IDAL;
import com.github.makosful.shoreline.dal.Interfaces.IReader;
import com.github.makosful.shoreline.dal.Json.JsonWriter;
import com.github.makosful.shoreline.dal.LoggingFolder.LogContext;
import com.github.makosful.shoreline.dal.Database.LogDBDAO;
import com.github.makosful.shoreline.dal.LoggingFolder.LogFileDAO;
import com.github.makosful.shoreline.dal.RememberMe.StoreLogIn;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javafx.collections.ObservableList;
import javax.mail.MessagingException;

/**
 * A facade for the Data Access Layer as a whole.
 * The sole responsibility for this class is to redirect data requests to
 * classes specialized in handling that data
 *
 * @see IDAL The interface for Data Access Layer facades
 * @see DALException The sole exception this class may throw
 *
 * @author Axl
 */
public class DALManager implements IDAL
{

    // Reader
    private final ReaderFactory readerFactory;
    private IReader reader;

    // Writers
    private final StoreLogIn storeLogIn;
    private final JsonWriter jWriter;

    // DAO
    private final ConfigDAO cDAO;
    private final LogDBDAO lDAO;
    private final UserDAO userDAO;

    public DALManager()
    {
        readerFactory = new ReaderFactory();
        cDAO = new ConfigDAO();
        storeLogIn = new StoreLogIn();
        lDAO = new LogDBDAO();
        jWriter = new JsonWriter();
        userDAO = new UserDAO();
    }

    //<editor-fold defaultstate="collapsed" desc="Core File In">
    @Override
    public void setReader(String path) throws DALException
    {
        try
        {
            reader = readerFactory.getReader(path);
        }
        catch (IllegalArgumentException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }

    }

    @Override
    public List<String> fileGetHeader(String path) throws DALException
    {
        try
        {
            setReader(path);
            return reader.getHeaders(path);
        }
        catch (ReaderException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public List<Map> fileGetValues(Map<String, String> map, String path) throws DALException
    {
        try
        {
            return reader.getValues(map, path);
        }
        catch (ReaderException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Config">
    @Override
    public void saveConfig(String configName, ObservableList<String> items) throws DALException
    {
        try
        {
            int configId = cDAO.saveConfiguration(configName);

            for (String column : items)
            {
                cDAO.saveConfigColumns(configId, column);
            }
        }
        catch (SQLException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public ObservableList<Config> getAllConfigs() throws DALException
    {
        try
        {
            return cDAO.getAllConfigs();
        }
        catch (SQLException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Password">
    @Override
    public void savePassword(String userName, String password) throws DALException
    {
        try
        {
            storeLogIn.savePassword(userName, password);
        }
        catch (IOException ex)
        {
            throw new DALException("Error writing file");
        }
    }

    @Override
    public String[] getPassword() throws DALException
    {
        try
        {
            return storeLogIn.getPassword();
        }
        catch (FileNotFoundException ex)
        {
            throw new DALException("Error with password.txt");
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Logs">
    @Override
    public ObservableList<ConversionLog> getAllLogs() throws DALException
    {
        try
        {
            return lDAO.getLogs();
        }
        catch (SQLException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public void saveLog(ConversionLog log) throws DALException
    {

        //Get instance for calling the save log mehod,
        //which is part of a stategy pattern
        LogContext logContextDB = new LogContext(lDAO);
        LogContext logContextFile = new LogContext(new LogFileDAO());

        //save log in db and locally
        logContextDB.saveLog(log);
        logContextFile.saveLog(log);

    }

    @Override
    public ObservableList<ConversionLog> searchLogs(String searchText, List<String> checked) throws DALException
    {
        try
        {
            //prepares the sql string so the logs can selected
            //based on the criteria in the checked arraylist
            return lDAO.prepareLogSeach(searchText, checked);
        }
        catch (SQLException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }
    //</editor-fold>

    @Override
    public void createFile(List<Map> list, String fileName) throws DALException
    {
        try
        {
            jWriter.createFile(list, fileName);
        }
        catch (IOException ex)
        {
            throw new DALException("Could not write to file");
        }
    }

    //<editor-fold defaultstate="collapsed" desc="User Handling">
    @Override
    public boolean createUser(User user) throws DALException
    {
        try
        {
            return userDAO.createUser(user);
        }
        catch (SQLException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public User getUser(String uName, String pass) throws DALException
    {
        try
        {
            return userDAO.getUserLogin(uName, pass);
        }
        catch (SQLException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public User getUserByMail(String mail) throws DALException
    {
        try
        {
            return userDAO.getUserByMail(mail);
        }
        catch (SQLException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public boolean changeUserPassword(User user, String pass) throws DALException
    {
        try
        {
            return this.userDAO.changePassWord(user, pass);
        }
        catch (SQLException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public boolean sendEmail(User user, String pass) throws DALException
    {
        try
        {
            String title = "New Password";
            String message = "<p>We've generated a new password for you</p>\n"
                             + "<p>Username: " + user.getUserName() + "</p>\n"
                             + "<p>Password: " + pass + "</p>\n"
                             + "<p>We recommend you change it after logging back in</p>";

            final Email mail = new Email(user.getEmail(),
                                         title,
                                         message);

            return mail.sendMail();
        }
        catch (MessagingException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public boolean passwordMatch(User user, String pass) throws DALException
    {
        try
        {
            return this.userDAO.passwordMatch(user, pass);
        }
        catch (SQLException ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }
    //</editor-fold>
}
