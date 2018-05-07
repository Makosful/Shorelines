/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.dal;


import com.github.makosful.shoreline.be.ExcelRow;
import com.github.makosful.shoreline.dal.Excel.ExcelReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


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
    public void readFile(String file, HashMap<String, Integer> cellOrder, boolean conversion) throws DALException
    {
        

        try
        {
            excelReader.readFromXlsxFiles(file, cellOrder, conversion);
        }
        catch (Exception ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
   
   
        
    }

    @Override
    public List<ExcelRow> getRowList()
    {
        return excelReader.getExcelRowsList();
    }

    @Override
    public List getColumnNames()
    {
        return excelReader.getColumnNames();
    }



    
}
