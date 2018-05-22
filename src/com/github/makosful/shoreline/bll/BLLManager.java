package com.github.makosful.shoreline.bll;

import com.github.makosful.shoreline.be.Config;
import com.github.makosful.shoreline.be.ConversionLog;
import com.github.makosful.shoreline.be.User;
import com.github.makosful.shoreline.be.UserNew;
import com.github.makosful.shoreline.dal.DALManager;
import com.github.makosful.shoreline.dal.Exception.DALException;
import com.github.makosful.shoreline.dal.Interfaces.IDAL;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

/**
 * The facade for the Business Logic Layer. The sole purpose of this class is to
 * send requests along to classes specialized classes, and should not to any
 * logic itself.
 * This class may only throw the BLLException, and nothing else.
 * All data requests must pass through this layer. If the request doesn't
 * require any kind of logic, the request may go straight to DAL from here
 *
 * @see IBLL The interface used for this layer's facades
 * @see BLLException The sole Exception this class may throw
 * @see IDAL The interface for the Data Access Layer
 *
 * @author Axl
 */
public final class BLLManager implements IBLL
{

    private final IDAL dal;
    private final Log log;
    private final TaskManager tasks;
    private final PasswordGenerator passGen;

    public BLLManager()
    {
        dal = new DALManager();
        tasks = new TaskManager();
        setDalManager();
        log = new Log();
        passGen = new PasswordGenerator(12);
    }

    @Override
    public void savePassword(String userName, String password) throws BLLException
    {
        try
        {
            dal.savePassword(userName, password);
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getMessage());
        }
    }

    @Override
    public String[] getPassword() throws BLLException
    {
        try
        {
            return dal.getPassword();
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getMessage());
        }
    }

    @Override
    public Task makeTask(Map<String, String> map, String filePath, String fileName) throws BLLException
    {
        try
        {
            return tasks.makeTask(map, filePath, fileName);
        }
        catch (BLLException ex)
        {
            throw new BLLException(ex.getMessage());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Configuration">
    @Override
    public void saveConfig(String configName, ObservableList<String> items) throws BLLException
    {
        try
        {
            dal.saveConfig(configName, items);
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }

    }

    @Override
    public ObservableList<Config> getAllConfigs() throws BLLException
    {
        try
        {
            return dal.getAllConfigs();
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }

    }
    //</editor-fold>

    @Override
    public String generatePassword() throws BLLException
    {
        return passGen.nextString();
    }

    @Override
    public List<String> getHeaders(String path) throws BLLException
    {
        try
        {
            return dal.fileGetHeader(path);
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public List<Map> getValues(Map<String, String> map, String path) throws BLLException
    {
        try
        {
            return dal.fileGetValues(map, path);
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }

    public void setDalManager()
    {
        tasks.setDalManager(dal);
    }

    //<editor-fold defaultstate="collapsed" desc="Logs">
    @Override
    public void saveLog(ConversionLog conversionLog) throws BLLException
    {
        log.saveLog(conversionLog);
    }

    @Override
    public ObservableList<ConversionLog> getAllLogs() throws BLLException
    {
        return log.getAllLogs();
    }

    @Override
    public ObservableList<ConversionLog> searchLogs(String searchText, List<String> checked) throws BLLException
    {
        try
        {
            return dal.searchLogs(searchText, checked);
        }
        catch (DALException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="User Handling">
    @Override
    public boolean createUser(UserNew u) throws BLLException
    {
        try
        {
            String hash = Hashing.hashPass(u.getPassword());

            return dal.createUser(new UserNew(u.getFirstName(),
                                              u.getLastName(),
                                              u.getUserName(),
                                              u.getEmail(),
                                              hash));
        }
        catch (DALException
               | NoSuchAlgorithmException
               | UnsupportedEncodingException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public User login(String uName, String pass) throws BLLException
    {
        try
        {
            String hashPass = Hashing.hashPass(pass);
            return dal.getUser(uName, hashPass);
        }
        catch (NoSuchAlgorithmException
               | UnsupportedEncodingException
               | DALException ex)
        {
            throw new BLLException(pass, ex);
        }
    }

    @Override
    public boolean getUserByMail(String mail) throws BLLException
    {
        try
        {
            final User user = dal.getUserByMail(mail);

            if (user == null)
            {
                return false;
            }

            final String pass = generatePassword();
            final String hash = Hashing.hashPass(pass);
            boolean changed = dal.changeUserPassword(user, hash);

            if (!changed)
            {
                return false;
            }

            boolean sentEmail = dal.sendEmail(user, pass);

            if (!sentEmail)
            {
                throw new DALException("New password was generated, but the mail couldn't be sent");
            }
            return true;
        }
        catch (DALException
               | NoSuchAlgorithmException
               | UnsupportedEncodingException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public boolean changePassword(User user, String pass) throws BLLException
    {
        try
        {
            return dal.changeUserPassword(user, Hashing.hashPass(pass));
        }
        catch (DALException
               | NoSuchAlgorithmException
               | UnsupportedEncodingException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public boolean passwordMatch(User user, String pass) throws BLLException
    {
        try
        {
            return dal.passwordMatch(user, Hashing.hashPass(pass));
        }
        catch (DALException
               | NoSuchAlgorithmException
               | UnsupportedEncodingException ex)
        {
            throw new BLLException(ex.getLocalizedMessage(), ex);
        }
    }
    //</editor-fold>
}
