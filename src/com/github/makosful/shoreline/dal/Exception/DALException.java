package com.github.makosful.shoreline.dal;

/**
 * A custom Exception for the DAL Facade to use. The only Exception that may be
 * throw out from the Data Access Layer
 *
 * @see IDAL The Facade interface
 *
 * @author Axl
 */
public class DALException extends Exception
{

    public DALException(String message)
    {
        super(message);
    }

    public DALException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public DALException(Throwable cause)
    {
        super(cause);
    }
}
