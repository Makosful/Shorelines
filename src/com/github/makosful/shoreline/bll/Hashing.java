/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.bll;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author B
 */
public class Hashing
{
    
    
    /**
     * One way function/hashing of password
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException 
     */
    public static String hashPass(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException 
    {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        byte[] hash = digest.digest(password.getBytes("UTF-8"));

        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < hash.length; i++)
        {

            String hex = Integer.toHexString(0xff & hash[i]);

            if(hex.length() == 1) hexString.append('0');

            hexString.append(hex);

        }

        return hexString.toString();
        
    }
    
}
