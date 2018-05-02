/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.gui.model;

import com.github.makosful.shoreline.bll.BLLException;
import com.github.makosful.shoreline.bll.BLLManager;
import com.github.makosful.shoreline.bll.IBLL;

/**
 *
 * @author Hussain
 */
public class LogInModel
{

    IBLL bll = new BLLManager();

    public String[] getPassword() throws BLLException
    {
        return bll.getPassword();
    }

    public void savePassword(String userName, String password) throws BLLException
    {
        bll.savePassword(userName, password);
    }

}
