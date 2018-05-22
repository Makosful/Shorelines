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
public class CSVReader implements IReader{
    
    private List<CSVRecord> record;
    private Reader reader;
    private Map<String, Integer> headers = new HashMap();
    
    
    @Override
    public boolean loadFile(String path) throws ReaderException
    {
        try
        {
            reader = Files.newBufferedReader(Paths.get(path));
        }
        catch (IOException ex)
        {
             throw new ReaderException("File could not be read");
        }
        
        try
        {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            record = csvParser.getRecords();
            return true;
            
        }
        catch (IOException ex)
        {
            throw new ReaderException("File could not be read");
        }
        
    }
   
    @Override
    public List<String> getHeaders() throws ReaderException
    {
        List<String> list = new ArrayList();
        int i = 0;
        for(String cell : record.get(0))
        {
            list.add(cell);
            headers.put(cell, i++);
        }
       
        return list;   
    }

    @Override
    public List<Map> getValues(Map<String, String> keys) throws ReaderException
    {   
        List<Map> list = new ArrayList();
        boolean first = true;
        
        for(CSVRecord csvRecord : record)
        {
            if(!first)
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
