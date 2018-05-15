package com.github.makosful.shoreline.gui.model;

import com.github.makosful.shoreline.bll.BLLException;
import com.github.makosful.shoreline.bll.BLLManager;
import com.github.makosful.shoreline.bll.IBLL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Axl
 */
public class LoginModel
{

    private final Cache cache;
    private final IBLL bll;

    private StringProperty message;

    public LoginModel()
    {
        cache = Cache.getInstance();
        bll = new BLLManager();
    }

    public void savePassword(String userName, String password) throws BLLException
    {
        bll.savePassword(userName, password);
    }

    public String[] getPassword() throws BLLException
    {
        return bll.getPassword();
    }

    public String getNewPassword()
    {
        try
        {
            return bll.generatePassword();
        }
        catch (BLLException ex)
        {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
            return new String(); // Empty string
        }
    }
}
