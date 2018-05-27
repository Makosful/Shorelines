/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.bll;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author B
 */
public class HashingTest
{
    
    public HashingTest()
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
     * Test of hashPass method, of class Hashing.
     */
    @Test
    public void testHashPass() throws Exception
    {
        String hashPass = Hashing.hashPass("password");

        assertEquals(hashPass, Hashing.hashPass("password"));
    }
    
}
