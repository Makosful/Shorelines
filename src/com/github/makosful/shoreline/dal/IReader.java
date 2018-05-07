package com.github.makosful.shoreline.dal;

import com.github.makosful.shoreline.dal.Exception.DALException;
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
