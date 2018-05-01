package com.github.makosful.shoreline.dal;

import java.io.*;
import java.util.Map;
import org.json.simple.JSONArray;

/**
 * This class will be responsible for writing JSON files. To read JSON files,
 * create a new class
 *
 * @author Axl
 * @param <T>
 */
public class JsonWriter
{

    private final JSONArray json;
    private final StringWriter string;
    private String path;

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Creates a new empty JSON object
     */
    public JsonWriter()
    {
        json = new JSONArray();
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
        json = new JSONArray();
        path = "output.json";
        string = new StringWriter();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Implimented methods">
    public void addObject(Map jsonObj)
    {
        json.add(jsonObj);
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
