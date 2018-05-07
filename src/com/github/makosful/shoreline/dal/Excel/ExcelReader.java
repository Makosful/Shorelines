package com.github.makosful.shoreline.dal.Excel;

import com.github.makosful.shoreline.dal.Exception.ReaderException;
import com.github.makosful.shoreline.dal.Interfaces.IReader;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
<<<<<<< HEAD
import java.util.logging.Level;
import java.util.logging.Logger;
=======
import java.util.*;
import java.util.Map.Entry;
>>>>>>> 6f0ceef1e2f9ce8c46fa0bd696d8ee71721451ed
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * This class is responsible for handling the data retrival from Microsoft Excel
 * sheets (.xlsx)
 *
 * @author Axl
 */
public class ExcelReader implements IReader
{
<<<<<<< HEAD

    private List<ExcelRow> excelRows;
    private List<Cell> cells;
    private final ExcelRowCreation excelRowCreation;
    private final List<ColumnObject> columnNames;

    private Cell cell;
=======
    
    private Sheet sheet;
>>>>>>> 6f0ceef1e2f9ce8c46fa0bd696d8ee71721451ed

    //<editor-fold defaultstate="collapsed" desc="File Load">
    @Override
    public boolean loadFile(String path) throws ReaderException
    {
        if (path.endsWith(".xls"))
        {
            return loadXls(path);
        }
        else if (path.endsWith(".xlsx"))
        {
            return loadXlsX(path);
        }
        else
        {
            throw new ReaderException("Unsupported file format");
        }
    }
    
    private boolean loadXls(String path) throws ReaderException
    {
        try
        {
            // Set up
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(path));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet s = wb.getSheetAt(0);
            
            this.sheet = s;
            return true;
        }
        catch (IOException ex)
        {
            throw new ReaderException(ex.getLocalizedMessage(), ex);
        }
        
    }
<<<<<<< HEAD

    /**
     * Simply reads XLSX files and prints out the cell adress
     * TODO: Make it return usable data
     *
     * @param file The XLSX file path to read as String
     *
     * @throws IOException
     */
    public void readFromXlsxFiles(String file, HashMap<String, Integer> cellOrder, boolean conversion) throws IOException
=======
    
    private boolean loadXlsX(String path) throws ReaderException
>>>>>>> 6f0ceef1e2f9ce8c46fa0bd696d8ee71721451ed
    {
        try
        {
            // Set up
            BufferedInputStream fs = new BufferedInputStream(
                    new FileInputStream(path));
            XSSFWorkbook wb = new XSSFWorkbook(fs);
            XSSFSheet s = wb.getSheetAt(0);
            
            this.sheet = s;
            return true;
        }
        catch (IOException ex)
        {
            throw new ReaderException(ex.getLocalizedMessage(), ex);
        }
    }
    //</editor-fold>

    @Override
    public List<String> getHeaders()
    {
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
    public List<Map> getValues(Map<String, String> keys) throws ReaderException
    {
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
<<<<<<< HEAD
                    try
                    {
                        excelCreation(cellOrder);
                    }
                    catch (Exception ex)
                    {
                        Logger.getLogger(ExcelReader.class.getName()).log(Level.SEVERE, null, ex);
                    }
=======
                    headers.put(
                            cell.getStringCellValue(),
                            cell.getAddress().getColumn()
                    );
>>>>>>> 6f0ceef1e2f9ce8c46fa0bd696d8ee71721451ed
                }
            }
        }
        //</editor-fold>

        // The handling of each cell
        //<editor-fold defaultstate="collapsed" desc="For Loop">
        for (int i = 0; i < rows; i++)
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

    /**
<<<<<<< HEAD
     * Creates excelRow Objects - holds information about a single excel row.
     * Uses HashMap to know which variables are supposed to hold specific
     * columns
     * in specific row.
     *
     * @param cellOrder
     */
    private void excelCreation(HashMap<String, Integer> cellOrder) throws Exception
    {
        ExcelRow excelRow = excelRowCreation.ExcelCreation(cellOrder, cells);
        excelRows.add(excelRow);
    }

    // Excel Row objects.
    public List<ExcelRow> getExcelRowsList()
    {
        return excelRows;
    }

    // Column names - for example Country.
    public List<ColumnObject> getColumnNames()
    {
        return columnNames;
    }

    public String getStringValue()
    {
        return excelRowCreation.getStringValue(cell);
    }

=======
     * Gets the value out of the cell as a string.
     * @param cell
     * @return 
     */
    public String getValueAsString(Cell cell)
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
>>>>>>> 6f0ceef1e2f9ce8c46fa0bd696d8ee71721451ed
}
