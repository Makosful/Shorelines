package com.github.makosful.shoreline.gui.model;

import com.github.makosful.shoreline.be.User;
import com.github.makosful.shoreline.gui.model.Cache.Scenes;

/**
 *
 * @author Axl
 */
public class LoginModel
{

    private final Cache cache;

    public LoginModel()
    {
        cache = Cache.getInstance();
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
}
