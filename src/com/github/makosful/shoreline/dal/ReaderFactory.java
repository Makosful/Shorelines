package com.github.makosful.shoreline.dal;

import com.github.makosful.shoreline.dal.CSV.CSVReader;
import com.github.makosful.shoreline.dal.Excel.ExcelReader;
import com.github.makosful.shoreline.dal.Interfaces.IReader;

/**
 *
 * @author B
 */
public class ReaderFactory
{

    /**
     * Returns reader according to file format; supported file formats:
     * xlsx, xls, csv.
     *
     * @param path
     *
     * @return
     *
     * @throws IllegalArgumentException
     */
    public IReader getReader(String path) throws IllegalArgumentException
    {
        String extension = path.substring(path.lastIndexOf('.') + 1);

        switch (extension)
        {
            case "xlsx":
                return new ExcelReader();
            case "xls":
                return new ExcelReader();
            case "csv":
                return new CSVReader();
            default:
                throw new IllegalArgumentException("The filetype '" + extension + "' is not valid");
        }
    }
}
