package com.github.makosful.shoreline.dal.Interfaces;

import com.github.makosful.shoreline.dal.Exception.DALException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author B
 */
public interface IReader
{

    /**
     * @deprecated
     *
     * @param file
     * @param cellOrder
     * @param conversion
     *
     * @throws DALException
     */
    public void readFile(String file, HashMap<String, Integer> cellOrder, boolean conversion) throws DALException;

    public boolean loadFile(String path) throws DALException;

    public List<String> getHeaders() throws DALException;

    public List<Map> getValues(Map<String, String> keys) throws DALException;

}
