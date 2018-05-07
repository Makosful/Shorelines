package com.github.makosful.shoreline.dal;

import com.github.makosful.shoreline.dal.Excel.ExcelReader;
import com.github.makosful.shoreline.dal.Excel.XlsReaderAdapter;
import com.github.makosful.shoreline.dal.Excel.XlsxReaderAdapter;

/**
 *
 * @author B
 */
public class ReaderFactory extends AbstractReaderFactory{

    
    private final XlsxReaderAdapter xlsxReader;

    public ReaderFactory()
    {
         xlsxReader = new XlsxReaderAdapter(new ExcelReader());
    }
    
    /**
     * Depending on the given string as parameter this method creates a new instance of a 
     * reader adapter and returns it
     * @param reader
     * @return 
     */
    @Override
    public IReader getReader(String reader)
    {
        if(reader.equalsIgnoreCase("xlsx"))
        {
            return new XlsxReaderAdapter(new ExcelReader());
        }
        else if(reader.equalsIgnoreCase("xls"))
        {
            return new XlsReaderAdapter(new ExcelReader());
        }
        else
        {
            return null;
        }
    }



}
