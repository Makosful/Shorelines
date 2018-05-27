/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.dal.RememberMe;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Hussain
 */
public class StoreLogIn
{

    String filePath = "password.txt";

    /**
     * Writes the login credentials to a txt file locally,
     * to be used next time the user wants to login
     *
     * @param userName
     * @param password
     */
    public void savePassword(String userName, String password) throws IOException
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath)))
        {
            writer.write(userName);
            writer.newLine();
            writer.write(password);
            writer.flush();
        }

        catch (IOException e)
        {
            throw new IOException();
        }
    }

    /**
     * Get the login credentials for the user stored on this local machine
     *
     * @return String array of login info - username and password
     */
    public String[] getPassword() throws FileNotFoundException
    {
        try
        {
            String[] logInInfo = new String[2];
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(filePath)));

            for (int i = 0; i < logInInfo.length; i++)
            {
                if (scanner.hasNext())
                {
                    logInInfo[i] = scanner.nextLine();
                }
            }

            return logInInfo;
        }
        catch (FileNotFoundException e)
        {
           throw new FileNotFoundException();
        }
    }
}
