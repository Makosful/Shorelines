/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.dal;


import java.io.IOException;


/**
 *
 * @author B
 */
public class ExcelReaderAdapter implements IReader
{
    private ExcelReader excelReader;
    
    public ExcelReaderAdapter(ExcelReader excelReader)
    {
        this.excelReader = excelReader;
    }

    @Override
    public void readFile(String file)
    {
        
   
        try
        {
            excelReader.readFromXlsxFiles(file);
        }
        catch (IOException ex)
        {
            
        }
   
        
    }



    
}
