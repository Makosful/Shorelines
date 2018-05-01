/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.dal;

import com.github.makosful.shoreline.be.ColumnObject;
import javafx.collections.ObservableList;

/**
 *
 * @author B
 */
public class ConfigDAO
{

    /**
     * Insert the new configuration in the db with its name and return its id(generated keys) 
     * @param configName
     * @return 
     */
    public int saveConfiguration(String configName)
    {
        System.out.println(configName);
        return 0;
    }

    /**
     * save the column in db
     * @param configId
     * @param column 
     */
    public void saveConfigColumns(int configId, ColumnObject column)
    {
        System.out.println("id:"+configId+"column:"+column.getColumnName());
        
    }

}
