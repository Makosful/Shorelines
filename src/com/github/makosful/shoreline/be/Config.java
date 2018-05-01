/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.be;

import java.util.List;

/**
 *
 * @author B
 */
public class Config
{
    private String name;
    private List<ColumnObject> chosenColumns;

 
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    
    public List<ColumnObject> getChosenColumns()
    {
        return chosenColumns;
    }

    public void setChosenColumns(List<ColumnObject> chosenColumns)
    {
        this.chosenColumns = chosenColumns;
    }
    
}
