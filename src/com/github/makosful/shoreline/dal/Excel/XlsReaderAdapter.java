package com.github.makosful.shoreline.dal.Excel;

import com.github.makosful.shoreline.dal.DALException;
import com.github.makosful.shoreline.dal.IReader;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author B
 */
public class XlsReaderAdapter implements IReader{

    private ExcelReader excelReader;
    public XlsReaderAdapter(ExcelReader excelReader)
    {
        this.excelReader = excelReader;
    }

    
    @Override
    public void readFile(String file, HashMap<String, Integer> cellOrder, boolean conversion) throws DALException
    {
        try
        {
            excelReader.readFromXlsFile(file, cellOrder, conversion);
        }
        catch (Exception ex)
        {
            throw new DALException(ex.getLocalizedMessage(), ex);
        }
    }

    @Override
    public List getRowList()
    {
        return excelReader.getExcelRowsList();
    }

    @Override
    public List getColumnNames()
    {
        return excelReader.getColumnNames();
    }
    
}
