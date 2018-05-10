package com.github.makosful.shoreline.dal;

import com.github.makosful.shoreline.dal.Interfaces.IReader;

/**
 *
 * @author B
 */
public abstract class AbstractFactoryReader {
    
    public abstract IReader getReader(String path) throws IllegalArgumentException;
   
    public abstract String getExtension(String path);
   
}
