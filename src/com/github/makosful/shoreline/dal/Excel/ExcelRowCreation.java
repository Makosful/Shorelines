/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.dal.Excel;

import com.github.makosful.shoreline.be.ExcelRow;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

/**
 *
 * @author Hussain
 */
public class ExcelRowCreation
{

    private ExcelRow excelRow;
    
    private final String[] hashmapStrings = new String[]
    {
        "siteName", "assetSerialNumber", "orderType", "workOrderId", "systemStatus",
        "userStatus", "createdOn", "createdBy", "nameDescription",
        "priority", "status", "esDate", "lsDate", "lfDate", "esTime"
    };
    
    private HashMap<String, Integer> cellOrder;
    private List<Cell> cells;
    private int i = 0;

    public ExcelRow ExcelCreation(HashMap<String, Integer> cellOrder, List<Cell> cells) throws Exception
    {
        try
        {
            this.cellOrder = cellOrder;
            this.cells = cells;
            if (!cellOrder.isEmpty())
            {
                return makeExcelRow();
            }
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
        return null;
    }
    
    public int getCellNumber()
    {
        return cellOrder.get(hashmapStrings[i++]);
    }
     
    public String getStringValue(Cell cell)
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
    
    public ExcelRow makeExcelRow()
    {
        excelRow = new ExcelRow();
        excelRow.setSiteName(cells.get(getCellNumber()).getStringCellValue());
        excelRow.setAssetSerialNumber(cells.get(getCellNumber()).getStringCellValue());
        excelRow.setOrderType(cells.get(getCellNumber()).getStringCellValue());
        excelRow.setWorkOrderId(cells.get(getCellNumber()).getStringCellValue());
        excelRow.setSystemStatus(cells.get(getCellNumber()).getStringCellValue());
        excelRow.setUserStatus(getStringValue(cells.get(getCellNumber())));
        excelRow.setCreatedOn(getStringValue(cells.get(getCellNumber())));
        excelRow.setCreatedBy(getStringValue(cells.get(getCellNumber())));
        excelRow.setNameDescription(getStringValue(cells.get(getCellNumber())));
        excelRow.setPriority(getStringValue(cells.get(getCellNumber())));
        excelRow.setStatus(getStringValue(cells.get(getCellNumber())));
        excelRow.setEsDate(getStringValue(cells.get(getCellNumber())));
        excelRow.setLsDate(getStringValue(cells.get(getCellNumber())));
        excelRow.setLfDate(getStringValue(cells.get(getCellNumber())));
        excelRow.setEsTime(getStringValue(cells.get(getCellNumber())));
        i = 0;
        return excelRow;
    }
}
