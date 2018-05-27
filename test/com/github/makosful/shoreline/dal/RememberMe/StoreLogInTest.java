/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.dal.RememberMe;

import java.io.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hussain
 */
public class StoreLogInTest
{
    
    public StoreLogInTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }

    /**
     * Test of savePassword method, of class StoreLogIn.
     */
    @org.junit.Test
    public void testSavePassword() throws IOException
    {
        String filePath = "password.txt";
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write("test1");
            writer.newLine();
            writer.write("test2");
            writer.flush();
            writer.close();
            String[] logInInfo = new String[2];
                        BufferedReader reader = new BufferedReader(new FileReader(filePath));
            
            logInInfo[0] = reader.readLine();
            logInInfo[1] = reader.readLine();
            reader.close();
            for(String string : logInInfo)
            {
                System.out.println(string);
            }
    }
//
//    /**
//     * Test of getPassword method, of class StoreLogIn.
//     */
//    @org.junit.Test
//    public void testGetPassword() throws FileNotFoundException, IOException
//    {
//                    String[] logInInfo = new String[2];
//        String filePath = "psasword.txt";
//            BufferedReader reader = new BufferedReader(new FileReader(filePath));
//            
//            logInInfo[0] = reader.readLine();
//            logInInfo[1] = reader.readLine();
//            reader.close();
//            for(String string : logInInfo)
//            {
//                System.out.println(string);
//            }
//    }
    
}
