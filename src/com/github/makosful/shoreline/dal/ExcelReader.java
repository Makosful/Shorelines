package com.github.makosful.shoreline.dal;

import com.github.makosful.shoreline.BE.ColumnObject;
import com.github.makosful.shoreline.BE.ExcelRow;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
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
public class ExcelReader
{

    private List<ExcelRow> excelRows;
    private List<Cell> cells;
    private final ExcelRowCreation excelRowCreation;
    private final List<ColumnObject> columnNames;

    public ExcelReader()
    {
        excelRowCreation = new ExcelRowCreation();
        excelRows = new ArrayList();
        columnNames = new ArrayList();
        excelRows = new ArrayList();
    }

    /**
     * Simply reads an XLS file and prints out the cell adress of every item
     * TODO: Make it return useable data
     *
     * @param file The XLS file to read from
     *
     * @throws java.io.IOException
     */
    public void readFromXlsFile(String file, HashMap<String, Integer> cellOrder) throws IOException
    {
        // Set up
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);

        readExcelSheet(sheet, cellOrder);
    }

    /**
     * Simply reads XLSX files and prints out the cell adress
     * TODO: Make it return usable data
     *
     * @param file The XLSX file to read
     *
     * @throws IOException
     */
    public void readFromXlsxFiles(String file, HashMap<String, Integer> cellOrder) throws IOException
    {
        // Set up
        XSSFWorkbook wb = new XSSFWorkbook(
                new BufferedInputStream(
                        new FileInputStream(file)));
        XSSFSheet sheet = wb.getSheetAt(0);

        readExcelSheet(sheet, cellOrder);
    }

    /**
     * Modualized reading of Excel files
     *
     * @param sheet An object implimenting the Sheet interface from ss.usermodel
     */
    private void readExcelSheet(Sheet sheet, HashMap<String, Integer> cellOrder)
    {
        excelRows.clear();
        columnNames.clear();
        Row row;
        int rows; // Number of rows
        // +1 So it takes last row along, pga. nul indeksering.
        rows = sheet.getPhysicalNumberOfRows() + 1;

        Cell cell;
        int cols = 0; // Number of columns
        int tmp;

        // Scans the first few rows in case the data starts lower
        for (int i = 0; i < 10; i++)
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

        // The core loop that goes through every cell
        for (int r = 0; r < rows; r++)
        {
            row = sheet.getRow(r);
            if (row != null)
            {
                cells = new ArrayList();
                for (int c = 0; c < cols; c++)
                {
                    cell = row.getCell((short) c);
                    if (cell != null)
                    {

                        if (r == 0)
                        {
                            ColumnObject col = new ColumnObject(cell.getStringCellValue(), cell.getColumnIndex());
                            columnNames.add(col);
                        }
                        else
                        {
                            cells.add(cell);
                        }
                    }
                }
                if (!cells.isEmpty())
                {
                    excelCreation(cellOrder);
                }
            }
        }
    }

    private void excelCreation(HashMap<String, Integer> cellOrder)
    {
        ExcelRow excelRow = excelRowCreation.ExcelCreation(cellOrder, cells);
        if (excelRow.getSiteName() != null)
        {
            excelRows.add(excelRow);
        }
    }

    public List<ExcelRow> getExcelRowsList()
    {
        return excelRows;
    }

    public List<ColumnObject> getColumnNames()
    {
        return columnNames;
    }

}
