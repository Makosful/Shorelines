package com.github.makosful.shoreline.gui.model;

import com.github.makosful.shoreline.be.UserNew;
import com.github.makosful.shoreline.bll.BLLException;
import com.github.makosful.shoreline.bll.BLLManager;
import com.github.makosful.shoreline.bll.IBLL;
import com.github.makosful.shoreline.gui.model.Cache.Scenes;

/**
 *
 * @author Axl
 */
public class SignupModel
{

    // Objects
    private final Cache cache;
    private final IBLL bll;

    public SignupModel()
    {
        cache = Cache.getInstance();

        bll = new BLLManager();
    }

    public void cancel()
    {
        goBack();
    }

    public void createUser(UserNew userNew)
    {
        try
        {
            bll.createUser(userNew);
            goBack();
        }
        catch (BLLException ex)
        {
            // Handle the error properly
            ex.printStackTrace();
        }
    }

    private void goBack()
    {
        cache.changeScene(Scenes.Login.getValue());
    }
}
