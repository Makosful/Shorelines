package com.github.makosful.shoreline.dal.Interfaces;

import com.github.makosful.shoreline.dal.Exception.ReaderException;
import java.util.List;
import java.util.Map;

/**
 * This interface is used to create Reader objects
 *
 * Standard usage of classes implimenting this interface is:
 * <ul>
 * <li>Call loadFile(string)</li>
 * <li>Call getHeaders() to get a list of of the categories</li>
 * <li>Call getValues(Map) to get the desired values</li>
 * </ul>
 *
 * @author David
 * @author Malthe
 */
public interface IReader
{

    /**
     * This method must be claeed before any other method works.
     * This method loads the file into memory and prepares it for use
     *
     * @param path The path to the file as a String
     *
     * @return Return true of the file could be loaded. An exception is thrown
     *         if anyting goes wrong
     *
     * @throws ReaderException If the reader fails for any reason, this
     *                         exception will be thrown.
     */
    //public boolean loadFile(String path) throws ReaderException;
    /**
     * This method will get the category types from the loaded file
     *
     * @param path
     *
     * @return Returns a List of all the categories from the file, as Strings
     *
     * @see #loadFile(java.lang.String) A file must be loaded using this method
     * before any categories can be used.
     *
     * @throws ReaderException If the categories fail to be retrived for any
     *                         reason, this exception will be thrown
     */
    public List<String> getHeaders(String path) throws ReaderException;

    /**
     * Retrive the values from the loaded file
     *
     * @param keys A map with the static output JSON key as the key value, and
     *             the category to pair with that key as the value
     * @param path
     *
     * @return Returns a list of Maps mapped for the JSON output
     *
     * @see #loadFile(java.lang.String) A file must be loaded using this method
     * before any values can be retrived
     * @see #getHeaders() The categories to use for the Value in the given map
     * should be retrived using this method.
     *
     * @throws ReaderException If anything goes wrong, this method will be
     *                         thrown
     */
    public List<Map> getValues(Map<String, String> keys, String path) throws ReaderException;

}
