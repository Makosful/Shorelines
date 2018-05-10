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
    public IReader getReader(String path) throws IllegalArgumentException
    {
        String extension = getExtension(path);
        
        switch(extension)
        {
            case "xlsx":
                return new ExcelReader();
            case "xls":
                return new ExcelReader();
            case "json":
                return new JsonReader();
            default: 
                throw new IllegalArgumentException("The filetype '"+extension+"' is not valid");
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
