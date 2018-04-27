package com.github.makosful.shoreline.dal;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
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

    /**
     * Simply reads an XLS file and prints out the cell adress of every item
     * TODO: Make it return useable data
     *
     * @param file The XLS file to read from
     *
     * @throws java.io.IOException
     */
    public void readFromXlsFile(String file) throws IOException
    {
        // Set up
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);

        readExcelSheet(sheet);
    }

    /**
     * Simply reads XLSX files and prints out the cell adress
     * TODO: Make it return usable data
     *
     * @param file The XLSX file to read
     *
     * @throws IOException
     */
    public void readFromXlsxFiles(String file) throws IOException
    {
        // Set up
        XSSFWorkbook wb = new XSSFWorkbook(
                new BufferedInputStream(
                        new FileInputStream(file)));
        XSSFSheet sheet = wb.getSheetAt(0);

        readExcelSheet(sheet);
    }

    /**
     * Modualized reading of Excel files
     *
     * @param sheet An object implimenting the Sheet interface from ss.usermodel
     */
    private void readExcelSheet(Sheet sheet)
    {
        Row row;
        int rows; // Number of rows
        rows = sheet.getPhysicalNumberOfRows();

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
                for (int c = 0; c < cols; c++)
                {
                    cell = row.getCell((short) c);
                    if (cell != null)
                    {
                        System.out.println(cell.getAddress());
                    }
                }
            }
        }
    }
}
