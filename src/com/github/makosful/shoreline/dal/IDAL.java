package com.github.makosful.shoreline.dal;

/**
 * An interface holding all methods a DAL Facade will need to handle.
 * All methods declared here should throw a DALException.
 *
 * @see DALException The only Exception that may be thrown from this class
 *
 * @author Axl
 */
public interface IDAL
{

    public void readFromXlsFile(String file) throws DALException;

    public void readFromXlsxFile(String file) throws DALException;
}
