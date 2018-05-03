package com.github.makosful.shoreline.dal.Excel;

import com.github.makosful.shoreline.dal.Interfaces.IReader;
import com.github.makosful.shoreline.dal.Exception.DALException;
import com.github.makosful.shoreline.dal.Excel.ExcelReader;
import java.util.HashMap;

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

}
