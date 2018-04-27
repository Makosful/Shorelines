package com.github.makosful.shoreline.dal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 * This class will be responsible for writing JSON files. To read JSON files,
 * create a new class
 *
 * @author Axl
 * @param <T>
 */
public class JsonWriter<T>
{

    private final JSONObject json;
    private String path;

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Creates a new empty JSON object
     */
    public JsonWriter()
    {
        json = new JSONObject();
        path = "output.json";
    }

    /**
     * Creates a new JSON object based on a map
     *
     * @param map The map with the key value pair
     */
    public JsonWriter(Map map)
    {
        json = new JSONObject(map);
        path = "output.json";
    }
    //</editor-fold>

    /**
     * Returns the current JSON object
     *
     * @return
     */
    public JSONObject getJsonObject()
    {
        return this.json;
    }

    /**
     * Puts a given value into the JSON.
     *
     * Item are ordered by first to last.
     * Items can't be reordered without manually removing and adding
     *
     * List objects = arrays
     * Simple datatype can be used.
     * Map objects = nested objects
     *
     * @param key   The key to give the value
     * @param value The value to store. Should be String friendly.
     */
    public void put(String key, T value)
    {
        json.put(key.toLowerCase(), value);
    }

    /**
     * Removes a value from the list. Caseinsensitive.
     *
     * @param key The key of the value to remove
     */
    public void remove(String key)
    {
        json.remove(key.toLowerCase());
    }

    /**
     * Removes an item if it contains the specified value
     *
     * @param key   The key of the item
     * @param value The value to check
     */
    public void remove(String key, T value)
    {
        json.remove(key, value);
    }

    /**
     * Clears the pending JSON.
     */
    public void clear()
    {
        json.clear();
    }

    /**
     * retrives the value stored at the key. Caseinsensitive.
     *
     * @param key The key of the value to get
     *
     * @return Returns the value, if the key exists. Returns null of the key
     *         doesn't exist. Returns null if the value is null.
     */
    public Object get(String key)
    {
        return json.get(key);
    }

    /**
     * Returns the values stored as a Collection
     *
     * @return Returns a Collection with all the values
     */
    public Collection getValues()
    {
        return json.values();
    }

    /**
     * Checks if the JSON object currently holds a key
     *
     * @param key The key to look for
     */
    public void containsKey(String key)
    {
        json.containsKey(key);
    }

    /**
     * Checks if the JSON objects contains a specific value.
     * to look for a specific spot, use containsKey(String)
     *
     * @see #containsKey(java.lang.String)
     *
     * @param value
     *
     * @return Return true if the value exists. False otherwise
     */
    public boolean containsValue(T value)
    {
        return json.containsValue(value);
    }

    /**
     * checks if the JSON object is empty
     *
     * @return Returns true if it is empty. Otherwise false
     */
    public boolean isEmpty()
    {
        return json.isEmpty();
    }

    //<editor-fold defaultstate="collapsed" desc="Output">
    /**
     * Sets the output path. Defaults to app folder as output.json
     *
     * @param path The path to save to
     */
    public void setOutput(String path)
    {
        this.path = path;
    }

    /**
     * Returns the current output path
     *
     * @return Returns a String object with the current path
     */
    public String getOutput()
    {
        return this.path;
    }

    /**
     * Writes the stored JSON to the given output.
     *
     * @throws IOException
     */
    public void write() throws IOException
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path)))
        {
            json.writeJSONString(bw);
        }
    }
    //</editor-fold>
}
