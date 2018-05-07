package com.github.makosful.shoreline.dal;

/**
 *
 * @author B
 */
public abstract class AbstractReaderFactory {

    public abstract IReader getReader(String reader);
    
    /**
     * creates a new instance of readerfactor and returns it
     * @return 
     */
    public static ReaderFactory getReaderFactory()
    {
        return new ReaderFactory();
    }
}
