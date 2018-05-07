package com.github.makosful.shoreline.dal.Exception;

/**
 *
 * @author Axl
 */
public class ReaderException extends Exception
{

    public ReaderException(String message)
    {
        super(message);
    }

    public ReaderException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ReaderException(Throwable cause)
    {
        super(cause);
    }
}
