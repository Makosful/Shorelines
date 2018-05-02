package com.github.makosful.shoreline.dal;

import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.collections4.map.LinkedMap;
import static org.junit.Assert.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Axl
 */
public class JsonWriterTest
{

    private JsonWriter json;
    private StringWriter out;

    //<editor-fold defaultstate="collapsed" desc="Setup">
    @BeforeClass
    public static void setUpClass()
    {
        System.out.println("Testing on JsonWriter class");
        System.out.println("If at leats one of the current 5 tests passes, write works");
    }

    @AfterClass
    public static void tearDownClass()
    {
        System.out.println("Done testing on JsonWriter class");
        System.out.println();
    }

    @Before
    public void setUp()
    {
        json = new JsonWriter();
        out = new StringWriter();
    }

    @After
    public void tearDown()
    {
        json = null;
        out = null;
    }
    //</editor-fold>

    @Test
    public void testPuttingStringinJson()
    {
        try
        {
            System.out.println("Testing: Putting string into Json");
            Map map = new LinkedHashMap();
            map.put("Fruit", "Apple");
            json.addObject(map);

            String expectedResult = "[{\"fruit\":\"Apple\"}]";
            json.write(out);

            assertTrue(expectedResult.equalsIgnoreCase(out.toString()));
        }
        catch (IOException ex)
        {
            fail(ex.getLocalizedMessage());
        }
    }

    @Test
    public void testPutIntJson()
    {
        try
        {
            System.out.println("Testing: Putting a simple int datatype into JSON");
            Map map = new LinkedHashMap();
            map.put("MyInt", 69);
            json.addObject(map);

            String expectedResult = "[{\"myint\":69}]";
            json.write(out);

            assertTrue(expectedResult.equalsIgnoreCase(out.toString()));
        }
        catch (IOException ex)
        {
            fail(ex.getLocalizedMessage());
        }
    }

    @Test
    public void testPutFloatJson()
    {
        try
        {
            System.out.println("Testing: Putting a simple float datatype into JSON");
            Map map = new LinkedHashMap();
            map.put("MyFloat", 6.9f);
            json.addObject(map);

            String expectedResult = "[{\"MyFloat\":6.9}]";
            json.write(out);

            assertTrue(expectedResult.equalsIgnoreCase(out.toString()));
        }
        catch (IOException ex)
        {
            fail(ex.getLocalizedMessage());
        }
    }

    @Test
    public void testRemoveFromJson()
    {
        try
        {
            System.out.println("Testing: Removing a value from jason with a key");
            Map map = new LinkedHashMap();

            map.put("MyInt", 6.9);
            map.put("Myfloat", 6.9f);
            map.remove("Myfloat");

            json.addObject(map);

            String expectedResult = "[{\"myint\":6.9}]";
            json.write(out);

            assertTrue(expectedResult.equalsIgnoreCase(out.toString()));
        }
        catch (IOException ex)
        {
            fail(ex.getLocalizedMessage());
        }
    }

    @Test
    public void testRemoveFromJsonFalse()
    {
        try
        {
            System.out.println("Testing: Removing a key that doesn't exist");
            Map map = new LinkedMap();

            map.put("MyInt", 6.9);
            map.put("Myfloat", 6.9f);
            map.remove("Myfloater");

            String expectedResult = "[{\"myint\":6.9}]";
            json.write(out);

            assertFalse(expectedResult.equalsIgnoreCase(out.toString()));
        }
        catch (IOException ex)
        {
            fail(ex.getLocalizedMessage());
        }
    }
}
