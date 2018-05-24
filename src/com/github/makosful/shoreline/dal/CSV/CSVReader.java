package com.github.makosful.shoreline.dal.CSV;

import com.github.makosful.shoreline.dal.Exception.ReaderException;
import com.github.makosful.shoreline.dal.Interfaces.IReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map.Entry;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author B
 */
public class CSVReader implements IReader
{

    private final Map<String, Integer> headers = new HashMap();

    //@Override
    private List<CSVRecord> loadFile(String path) throws ReaderException
    {

        try
        {
            Reader reader = Files.newBufferedReader(Paths.get(path));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            return csvParser.getRecords();
        }
        catch (IOException ex)
        {
            throw new ReaderException("File could not be read");
        }

    }

    @Override
    public List<String> getHeaders(String path) throws ReaderException
    {
        List<CSVRecord> record = loadFile(path);

        List<String> list = new ArrayList();
        int i = 0;
        for (String cell : record.get(0))
        {
            cell = RenameDuplicateHeaderNames(record.get(0), cell, i);
            
            list.add(cell);
            headers.put(cell, i++);
        }

        return list;
    }

    /**
     * To avoid duplicate header names, it renames them by adding a number representing
     * the times the duplicate name has appeared previously 
     * @param record
     * @param cell
     * @param i
     * @return 
     */
    private String RenameDuplicateHeaderNames(CSVRecord record, String cell, int i)
    {
        int q = 0;
        int numberOfIdenticalCellsBefore = 0;
        boolean identicalCellsAfterAndNoneBefore = false;
        for (String cellCheck : record)
        {
            if(cell.equals(cellCheck))
            {
                if(q < i)
                {
                    numberOfIdenticalCellsBefore++;
                }
                else if(q > i && numberOfIdenticalCellsBefore == 0)
                {
                    identicalCellsAfterAndNoneBefore = true;
                }
            }
            q++;
        }
        if(numberOfIdenticalCellsBefore > 0)
        {
            cell = cell+(numberOfIdenticalCellsBefore+1);
        }
        else if(identicalCellsAfterAndNoneBefore)
        {
            cell = cell+1;
        }
        return cell;
    }

    @Override
    public List<Map> getValues(Map<String, String> keys, String path) throws ReaderException
    {
        List<CSVRecord> record = loadFile(path);

        List<Map> list = new ArrayList();
        boolean first = true;

        for (CSVRecord csvRecord : record)
        {
            if (!first)
            {
                Map<String, String> map = new LinkedHashMap();
                for (Entry<String, String> entry : keys.entrySet())
                {
                    map.put(entry.getKey(), csvRecord.get(headers.get(entry.getValue())));
                }
                list.add(map);
            }
            first = false;
        }
        return list;
    }

}
