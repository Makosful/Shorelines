package com.github.makosful.shoreline.dal;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
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
     * TODO
     *
     * @param file The file to read from
     *
     * @throws java.io.IOException
     */
    public void readFromXlsFile(String file) throws IOException
    {
        // Set up
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);

        // Location within the sheet
        HSSFRow row;
        HSSFCell cell;

        int rows; // Number of rows
        rows = sheet.getPhysicalNumberOfRows();

        int cols = 0; // Number of columns
        int tmp;

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
                        // Code
                    }
                }
            }
        }
    }

    void readFromXlsxFiles(String file) throws IOException
    {
        // Set up
        XSSFWorkbook wb = new XSSFWorkbook(
                new BufferedInputStream(
                        new FileInputStream(file)));
        XSSFSheet sheet = wb.getSheetAt(0);

        // Location within the sheet
        XSSFRow row;
        XSSFCell cell;

        int rows; // Number of rows
        rows = sheet.getPhysicalNumberOfRows();

        int cols = 0; // Number of columns
        int tmp;

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
                        System.out.println(cell.getRawValue());
                    }
                }
            }
        }
    }
}
