package com.github.makosful.shoreline.gui.model;

import com.github.makosful.shoreline.be.User;
import com.github.makosful.shoreline.bll.BLLException;
import com.github.makosful.shoreline.bll.BLLManager;
import com.github.makosful.shoreline.bll.IBLL;
import com.github.makosful.shoreline.gui.model.Cache.Scenes;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Axl
 */
public class LoginModel
{

    private final Cache cache;
    private final IBLL bll;

    private final StringProperty message;

    public LoginModel()
    {
        cache = Cache.getInstance();
        bll = new BLLManager();

        message = new SimpleStringProperty();
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

    public StringProperty getMessageProperty()
    {
        return message;
    }

    public void openSignup()
    {
        cache.changeScene(Scenes.SignUp.getValue());
    }

    public void login(String uName, String pass)
    {
        try
        {
            final User u = bll.login(uName, pass);

            if (u == null)
            {
                // Error Message
                message.setValue("Wrong username or password");
            }
            else
            {
                cache.setUser(u);
                cache.changeScene(Scenes.Main.getValue());
            }
        }
        catch (BLLException ex)
        {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
