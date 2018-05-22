package com.github.makosful.shoreline.dal.Excel;

import com.github.makosful.shoreline.dal.Exception.ReaderException;
import com.github.makosful.shoreline.dal.Interfaces.IReader;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * This class is responsible for handling the data retrival from Microsoft Excel
 * sheets (.xlsx)
 *
 * @author Axl
 */
public class ExcelReader implements IReader
{

    //<editor-fold defaultstate="collapsed" desc="File Load">
    //@Override
    private Sheet loadFile(String path) throws ReaderException
    {
        try
        {
            if (path.endsWith(".xls"))
            {
                // Set up
                POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(path));
                HSSFWorkbook wb = new HSSFWorkbook(fs);
                return wb.getSheetAt(0);
            }
            else if (path.endsWith(".xlsx"))
            {
                // Set up
                BufferedInputStream fs = new BufferedInputStream(
                        new FileInputStream(path));
                XSSFWorkbook wb = new XSSFWorkbook(fs);
                return wb.getSheetAt(0);
            }
            else
            {
                throw new ReaderException("Unsupported file format");
            }
        }
        catch (IOException | ReaderException ex)
        {
            throw new ReaderException(ex.getLocalizedMessage(), ex);
        }
    }
    //</editor-fold>

    @Override
    public List<String> getHeaders(String path) throws ReaderException
    {
        Sheet sheet = loadFile(path);
        List<String> list = new ArrayList<>();

        Row row; // Generic row
        Cell cell; // Generic cell

        int rows; // No of rows
        rows = sheet.getPhysicalNumberOfRows();

        int cols = 0; // No of columns
        int tmp;

        // Reads a few lines down to find some data
        //<editor-fold defaultstate="collapsed" desc="For Loop">
        for (int i = 0; i < 10 || i < rows; i++)
        {
            row = sheet.getRow(i);
            if (row != null)
            {
                tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                if (tmp > cols)
                {
                    cols = tmp;
                }
            }
        }
        //</editor-fold>

        // The handling of each cell
        //<editor-fold defaultstate="collapsed" desc="For Loop">
        row = sheet.getRow(0);
        if (row != null)
        {
            for (int c = 0; c < cols; c++)
            {
                cell = row.getCell((short) c);
                if (cell != null)
                {
                    list.add(cell.getStringCellValue());
                }
            }
        }
        //</editor-fold>

        return list;
    }

    @Override
    public List<Map> getValues(Map<String, String> keys, String path)
    {
        try
        {
            Sheet sheet = loadFile(path);
            //<editor-fold defaultstate="collapsed" desc="State Handling">
            if (sheet == null)
            {
                String s = "File must be loaded before calling this method";
                throw new ReaderException(s);
            }
            //</editor-fold>

            List<Map> list = new ArrayList();
            Map<String, Integer> headers = new HashMap();

            Row row; // Generic row
            Cell cell; // Generic cell

            int rows; // No of rows
            rows = sheet.getPhysicalNumberOfRows() + 1;

            int cols = 0; // No of columns
            int tmp;

            // Reads a few lines down to find some data
            //<editor-fold defaultstate="collapsed" desc="For Loop">
            for (int i = 0; i < 10 || i < rows; i++)
            {
                row = sheet.getRow(i);
                if (row != null)
                {
                    tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                    if (tmp > cols)
                    {
                        cols = tmp;
                    }
                }
            }
            //</editor-fold>

            // Getting the headers
            //<editor-fold defaultstate="collapsed" desc="For Loop">
            row = sheet.getRow(0);
            if (row != null)
            {
                for (int c = 0; c < cols; c++)
                {
                    cell = row.getCell((short) c);
                    if (cell != null)
                    {
                        headers.put(
                                cell.getStringCellValue(),
                                cell.getAddress().getColumn()
                        );
                    }
                }
            }
            //</editor-fold>

            // The handling of each cell
            //<editor-fold defaultstate="collapsed" desc="For Loop">
            for (int i = 1; i < rows; i++)
            {
                row = sheet.getRow(i);
                if (row != null)
                {
                    Map<String, String> map = new LinkedHashMap();
                    for (Entry<String, String> entry : keys.entrySet())
                    {
                        Cell c = sheet.getRow(i).getCell(headers.get(entry.getValue()));
                        map.put(entry.getKey(), getValueAsString(c));
                    }
                    list.add(map);
                }
            }
            //</editor-fold>

            return list;
        }
        catch (ReaderException ex)
        {
            ex.printStackTrace();

        }
        return null;
    }

    /**
     * Gets the value out of the cell as a string.
     *
     * @param cell
     *
     * @return
     */
    private String getValueAsString(Cell cell)
    {
        String cellValue;
        if (cell.getCellTypeEnum() == CellType.NUMERIC)
        {
            cellValue = String.valueOf(cell.getNumericCellValue());
        }
        else
        {
            return cell.getStringCellValue();
        }
        return cellValue;
    }

}
