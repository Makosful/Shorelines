/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.dal;

import com.github.makosful.shoreline.BE.ExcelRow;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;

/**
 *
 * @author Hussain
 */
public class ExcelRowCreation
{

    ExcelRow excelRow;

    public ExcelRow ExcelCreation(HashMap<String, Integer> cellOrder, List<Cell> cells)
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
            excelRow.setUserStatus(cells.get(cellNumber).getStringCellValue());
            cellNumber = cellOrder.get("createdOn");
            excelRow.setCreatedOn(cells.get(cellNumber).getStringCellValue());
            cellNumber = cellOrder.get("createdBy");
            excelRow.setCreatedBy(cells.get(cellNumber).getStringCellValue());
            cellNumber = cellOrder.get("nameDescription");
            excelRow.setNameDescription(cells.get(cellNumber).getStringCellValue());
            cellNumber = cellOrder.get("priority");
            excelRow.setPriority(cells.get(cellNumber).getStringCellValue());
            cellNumber = cellOrder.get("status");
            excelRow.setStatus(cells.get(cellNumber).getStringCellValue());
            cellNumber = cellOrder.get("esDate");
            excelRow.setEsDate(cells.get(cellNumber).getStringCellValue());
            cellNumber = cellOrder.get("lsDate");
            excelRow.setLsDate(cells.get(cellNumber).getStringCellValue());
            cellNumber = cellOrder.get("lfDate");
            excelRow.setLfDate(cells.get(cellNumber).getStringCellValue());
            cellNumber = cellOrder.get("esTime");
            excelRow.setEsTime(cells.get(cellNumber).getStringCellValue());

        }
        return excelRow;
    }
}
