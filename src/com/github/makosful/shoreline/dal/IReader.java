/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.makosful.shoreline.dal;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author B
 */
public interface IReader
{
    public void readFile(String file, HashMap<String, Integer> cellOrder, boolean conversion) throws DALException;
    
    public List getRowList();
    
    public List getColumnNames();
    
}
