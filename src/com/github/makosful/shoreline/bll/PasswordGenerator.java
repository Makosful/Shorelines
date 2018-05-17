package com.github.makosful.shoreline.bll;

import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author Axl
 */
public class PasswordGenerator
{

    private static final String UPPER = "QWERTYUIOPASDFGHJKLZXCVBNM";
    private static final String LOWER = "qwertyuiopasdfghjklzxcvbnm";
    private static final String DIGIT = "0123456789";
    private static final String ALPHA = UPPER + LOWER + DIGIT;

    private final Random random;

    private final char[] symbols;
    private final char[] buf;

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Creates a new PasswordGenerator
     *
     * @param length  The length of the password
     * @param random  A Random object
     * @param symbols The characters to chose from
     */
    public PasswordGenerator(int length, Random random, String symbols)
    {
        if (length < 1)
        {
            throw new IllegalArgumentException("String length must be 1 or more");
        }
        if (symbols.length() < 2)
        {
            throw new IllegalArgumentException("There must be at least 2 symbols to chose from");
        }

        this.random = random;
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
    }

    /**
     * Creates a new PasswordGenerator.
     * Allowed characters include standard english letter, plus the nordic Æ, Ø
     * and Å, both uppercase and lower case, as well as base 10 arabic numerals
     * 0-9
     *
     * @param length The length of the password
     * @param random A Random object
     */
    public PasswordGenerator(int length, Random random)
    {
        this(length, random, ALPHA);
    }

    /**
     * Creates a new PasswordGenerator.
     * Allowed characters include standard english letter, plus the nordic Æ, Ø
     * and Å, both uppercase and lower case, as well as base 10 arabic numerals
     * 0-9
     *
     * @param length The length of the password
     */
    public PasswordGenerator(int length)
    {
        this(length, new SecureRandom());
    }

    /**
     * Creates a new PasswordGenerator.
     *
     * Allowed characters include standard english letter, plus the nordic Æ, Ø
     * and Å, both uppercase and lower case, as well as base 10 arabic numerals
     * 0-9.
     *
     * The default password length is 8
     */
    public PasswordGenerator()
    {
        this(12);
    }
    //</editor-fold>

    /**
     * Generates a new password based on the variables sat in the constructor.
     *
     * @return Returns a String with a random sequence of characters as defined
     *         by the constructor
     */
    public String nextString()
    {
        for (int index = 0; index < buf.length; ++index)
        {
            buf[index] = symbols[random.nextInt(symbols.length)];
        }
        return new String(buf);
    }
}
