package com.github.makosful.shoreline.be;

/**
 * Empty class for now. fill as needed
 *
 * @author Axl
 */
public class User
{

    private int userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;

    /**
     * This constructor is only supposed to be used,
     * when you are creating a new user.
     * @param firstName
     * @param lastName
     * @param userName
     * @param email
     * @param password 
     */
    public User(String firstName, String lastName, String userName, String email, String password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
    /**
     * Constructor for use, when you are logging in.
     * @param userId
     * @param firstName
     * @param lastName
     * @param userName
     * @param email 
     */
    public User(int userId, String firstName, String lastName, String userName, String email)
    {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
    }
    /**
     * May only be used when creating a new user.
     * Will return null if you are not creating a new user.
     * @return 
     */
    public String getPassword()
    {
        return password;
    }
    /**
     * May only be used when creating a new user.
     * @param password 
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

}
