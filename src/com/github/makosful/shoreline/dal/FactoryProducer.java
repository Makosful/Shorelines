package com.github.makosful.shoreline.dal;

/**
 *
 * @author B
 */
public class FactoryProducer {

    public static ReaderFactory getFactory()
    {
        return new ReaderFactory();
    }
}
