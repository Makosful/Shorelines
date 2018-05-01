/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.BE;

/**
 *
 * @author Hussain
 */
public class ColumnObject
{
    private final String columnName;
    private final int columnID;
    
    public ColumnObject(String name, int columnID)
    {
        this.columnName = name;
        this.columnID = columnID;
    }

    public String getColumnName()
    {
        return columnName;
    }

    public int getColumnID()
    {
        return columnID;
    }
    
    @Override
    public String toString()
    {
        return columnName;
    }
}
