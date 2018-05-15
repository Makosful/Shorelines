package com.github.makosful.shoreline.dal.Json;

import com.google.gson.Gson;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * This class will be responsible for writing JSON files. To read JSON files,
 * create a new class
 *
 * @author Axl
 */
public class JsonWriter
{

    private final Gson gson;
    private final StringWriter string;
    private String path;

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Creates a new empty JSON object
     */
    public JsonWriter()
    {
        gson = new Gson();
        string = new StringWriter();
    }

    //</editor-fold>

    private void setJson(List<Map> maps)
    {
        string.write(gson.toJson(maps));
    }

    //<editor-fold defaultstate="collapsed" desc="Output">
    /**
     * Sets the output path. Defaults to app folder as output.json
     *
     * @param path The path to save to
     */
    private void setOutput(String path)
    {
        this.path = path;
    }

    /**
     * Writes the stored JSON to the given output.
     *
     * Uses a BufferedWriter as default
     *
     * @see #setOutput(java.lang.String) Use this to set the output path and
     * file name. It defaults to [App Path]/output.json
     *
     * @throws IOException
     */
    private void write() throws IOException
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path)))
        {
            write(bw);
        }
        catch (IOException ex)
        {
            throw new IOException(ex.getMessage());
        }
    }

    /**
     * Writes the JSON using the Writer given. Said Writer will have the output
     * path.
     *
     * Uses a BufferedWriter as default.
     *
     * Try-with-resource must be done manually when supplying the Writer. The
     * Writer is expected to have it's own location and will not use the
     * locaiton sat by this class' call
     *
     * @see #generateJsonString() Use this instead if you with to only get a
     * String object with the JSON instead of writing to afile
     *
     * @param out
     *
     * @throws IOException
     */
    private void write(Writer out) throws IOException
    {
        out.write(string.toString());
    }

    public void createFile(List<Map> list, String path) 
    {
        try
        {
            JsonWriter jWriter = new JsonWriter();
            jWriter.setJson(list);
            jWriter.setOutput(path);
            jWriter.write();
        }
        catch (IOException ex)
        {
           ex.printStackTrace();
        }
    }
    //</editor-fold>
}
