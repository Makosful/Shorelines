package com.github.makosful.shoreline.bll;

import com.github.makosful.shoreline.be.ExcelRow;
import com.github.makosful.shoreline.dal.DALManager;
import com.github.makosful.shoreline.dal.IDAL;
import org.json.simple.JSONObject;

/**
 *
 * @author Hussain
 */
public class ExcelConverter
{

    private final IDAL dal;

    public ExcelConverter()
    {
        dal = new DALManager();
    }

    public void convertToJSon(ExcelRow excel)
    {
        JSONObject json = new JSONObject();
        json.put("siteName", excel.getSiteName());
    }
}
