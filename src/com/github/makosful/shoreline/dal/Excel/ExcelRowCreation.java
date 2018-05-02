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

    ExcelRow excelRow;

    public ExcelRow ExcelCreation(HashMap<String, Integer> cellOrder, List<Cell> cells) throws Exception
    {
        try
        {
        excelRow = new ExcelRow();
        if (!cellOrder.isEmpty())
        {
            int cellNumber = cellOrder.get("siteName");
            excelRow.setSiteName(cells.get(cellNumber).getStringCellValue());
            cellNumber = cellOrder.get("assetSerialNumber");

            excelRow.setAssetSerialNumber(cells.get(cellNumber).getStringCellValue());

            cellNumber = cellOrder.get("orderType");
            excelRow.setOrderType(cells.get(cellNumber).getStringCellValue());

            cellNumber = cellOrder.get("systemStatus");
            excelRow.setSystemStatus(cells.get(cellNumber).getStringCellValue());

            cellNumber = cellOrder.get("userStatus");
            excelRow.setUserStatus(getStringValue(cells.get(cellNumber)));
            cellNumber = cellOrder.get("createdOn");
            excelRow.setCreatedOn(getStringValue(cells.get(cellNumber)));
            cellNumber = cellOrder.get("createdBy");
            excelRow.setCreatedBy(getStringValue(cells.get(cellNumber)));
            cellNumber = cellOrder.get("nameDescription");
            excelRow.setNameDescription(getStringValue(cells.get(cellNumber)));
            cellNumber = cellOrder.get("priority");
            excelRow.setPriority(getStringValue(cells.get(cellNumber)));
            cellNumber = cellOrder.get("status");
            excelRow.setStatus(getStringValue(cells.get(cellNumber)));
            cellNumber = cellOrder.get("esDate");
            excelRow.setEsDate(getStringValue(cells.get(cellNumber)));
            cellNumber = cellOrder.get("lsDate");
            excelRow.setLsDate(getStringValue(cells.get(cellNumber)));
            cellNumber = cellOrder.get("lfDate");
            excelRow.setLfDate(getStringValue(cells.get(cellNumber)));
            cellNumber = cellOrder.get("esTime");
            excelRow.setEsTime(getStringValue(cells.get(cellNumber)));
        }
        return excelRow;
        }
        catch(Exception e)
        {
            throw new Exception(e.getMessage());
        }
        
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
}
