package com.github.makosful.shoreline.bll;

/**
 * A custom Exception. This is the only Exception that may be thrown out from
 * the Business Logic Layer Facade
 *
 * @see IBLL The BLL Facade
 *
 * @author Axl
 */
public class BLLException extends Exception
{

    public BLLException(String message)
    {
        super(message);
    }

    public BLLException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public BLLException(Throwable cause)
    {
        super(cause);
    }
}
