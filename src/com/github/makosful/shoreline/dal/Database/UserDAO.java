package com.github.makosful.shoreline.dal.Database;

import com.github.makosful.shoreline.be.User;
import com.github.makosful.shoreline.be.UserNew;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Axl
 */
public class UserDAO
{

    private final DataBaseConnector db;

    public UserDAO()
    {
        db = new DataBaseConnector();
    }

    public boolean createUser(UserNew u) throws SQLException
    {
        try (Connection con = db.getConnection())
        {
            /**
             * Order: First Name, Last Name, User Name, Email, Password (hashet)
             */
            String sql = "INSERT INTO Users "
                         + "VALUES (?, ?, ?, ?, ?);";
            PreparedStatement stmt = con.prepareStatement(sql);

            int i = 1;
            stmt.setString(i++, u.getFirstName());
            stmt.setString(i++, u.getLastName());
            stmt.setString(i++, u.getUserName());
            stmt.setString(i++, u.getEmail());
            stmt.setString(i++, u.getPassword());

            stmt.executeUpdate();

            return true;
        }
    }

    public User getUser(String uName, String pass) throws SQLException
    {
        try (Connection con = db.getConnection())
        {
            String sql = "SELECT * FROM Users "
                         + "WHERE UserName like ? AND Password like ?;";
            PreparedStatement stmt = con.prepareStatement(sql);

            int i = 1;
            stmt.setString(i++, uName);
            stmt.setString(i++, pass);

            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                return new User(
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("UserName"),
                        rs.getString("Email")
                );
            }
            return null;
        }
    }
}
