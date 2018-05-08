package com.github.makosful.shoreline.dal;

import com.github.makosful.shoreline.dal.Excel.ExcelReader;
import com.github.makosful.shoreline.dal.Interfaces.IReader;
import com.github.makosful.shoreline.dal.Json.JsonReader;

/**
 *
 * @author B
 */
public class ReaderFactory extends AbstractFactoryReader{


    
    @Override
    public IReader getReader(String path)
    {
        String extension = getExtension(path);
        
        if(extension.equalsIgnoreCase("xlsx"))
        {
            return new ExcelReader();
        }
        else if(extension.equalsIgnoreCase("xls"))
        {
            return new ExcelReader();
        }
        else if(extension.equalsIgnoreCase("json"))
        {
            return new JsonReader();
        }
        else
        {
            return null;
        }
        
    }

    @Override
    public String getExtension(String path)
    {
        String extension = "";
 
        int i = path.lastIndexOf('.');
        int p = Math.max(path.lastIndexOf('/'), path.lastIndexOf('\\'));

        if (i > p) {
            extension = path.substring(i+1);
        }
        
        return extension;
    }




}
