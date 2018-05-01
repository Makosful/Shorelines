/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.bll;

import com.github.makosful.shoreline.BE.ExcelRow;
import org.json.simple.JSONObject;

/**
 *
 * @author Hussain
 */
public class ExcelConverter
{
    public void convertToJSon(ExcelRow excel)
    {
        JSONObject json = new JSONObject();
        json.put("siteName", excel.getSiteName());
    }
}
