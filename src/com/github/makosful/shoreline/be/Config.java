/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.be;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author B
 */
public class Config
{
    private int id;
    private String name;
    private ObservableList<ColumnObject> chosenColumns  = FXCollections.observableArrayList();

    
    
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
 
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    
    public ObservableList<ColumnObject> getChosenColumns()
    {
        return chosenColumns;
    }

    public void setChosenColumns(ObservableList<ColumnObject> chosenColumns)
    {
        this.chosenColumns = chosenColumns;
    }
   
    
}
