package com.github.makosful.shoreline.gui.model;

import com.github.makosful.shoreline.be.User;
import com.github.makosful.shoreline.bll.BLLException;
import com.github.makosful.shoreline.bll.BLLManager;
import com.github.makosful.shoreline.bll.IBLL;
import com.github.makosful.shoreline.gui.model.Cache.Scenes;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Axl
 */
public class LoginModel
{

    private final Cache cache;
    private final IBLL bll;

    public LoginModel()
    {
        cache = Cache.getInstance();
        bll = new BLLManager();
    }

    public boolean attemptLogin(String userName, String password)
    {
        boolean valid = true;
        if (valid) // Replace with a proper test
        {
            // Create a new user based on the login
            User user = new User();

            // Give that user to Cache
            cache.setUser(user);

            // Change the screen to the main window
            cache.changeScene(Scenes.Main.getValue());

            // Return true for successful login
            return true;
        }
        else
        {
            // Return false for failed login
            return false;
        }
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
