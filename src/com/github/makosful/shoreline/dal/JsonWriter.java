package com.github.makosful.shoreline.dal;

import java.io.*;
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
    private final StringWriter string;
    private String path;

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Creates a new empty JSON object
     */
    public JsonWriter()
    {
        json = new JSONObject();
        path = "output.json";
        string = new StringWriter();
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
        string = new StringWriter();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Implimented methods">
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
     * Clears the pending JSON.
     */
    public void clear()
    {
        json.clear();
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

    /**
     * Creates a JSON String based on the current data stored, and returns it.
     * This method will only generate and return a JSON String.
     *
     * @see #write() Use this method to write
     *
     * @return Returns a String of JSON
     *
     * @throws IOException
     */
    public String generateJsonString() throws IOException
    {
        json.writeJSONString(string);
        return string.toString();
    }

    /**
     * Returns a String of JSON identical to the one written for the output.
     * This method will only return an exsisting String.
     *
     * @see #generateJsonString() To generate a new String
     *
     * @return Returns the generated JSON string
     */
    public String getSerializedJson()
    {
        return string.toString();
    }

    @Override
    public String toString()
    {
        return super.toString();
    }
    //</editor-fold>

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
     * @see #setOutput(java.lang.String) Use this to set the output path and
     * file name. It defaults to [App Path]/output.json
     *
     * @throws IOException
     */
    public void write() throws IOException
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path)))
        {
            json.writeJSONString(bw);
            json.writeJSONString(string);
        }
    }

    /**
     * Writes the JSON using the Writer given. Said Writer will have the output
     * path.
     *
     * @see #generateJsonString() Use this instead if you with to only get a
     * String object with the JSON instead of writing to afile
     *
     * @param out
     *
     * @throws IOException
     */
    public void write(Writer out) throws IOException
    {
        json.writeJSONString(out);
        json.writeJSONString(string);
    }
    //</editor-fold>
}
