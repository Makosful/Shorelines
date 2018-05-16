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

    public User getUserLogin(String uName, String pass) throws SQLException
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

    public User getUserByMail(String mail) throws SQLException
    {
        try (Connection con = db.getConnection())
        {
            String sql = "SELECT * FROM Users WHERE Users.Email LIKE ?;";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, mail);

            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                return new User(rs.getString("FirstName"),
                                rs.getString("LastName"),
                                rs.getString("UserName"),
                                rs.getString("Email"));
            }
            return null;
        }
    }

    public boolean changePassWord(User user, String pass) throws SQLException
    {
        try (Connection con = db.getConnection())
        {
            String sql = "UPDATE  Users "
                         + "SET Users.Password = ? "
                         + "WHERE Users.UserName LIKE ?;";
            PreparedStatement stmt = con.prepareStatement(sql);

            int i = 1;
            stmt.setString(i++, pass);
            stmt.setString(i++, user.getUserName());

            int res = stmt.executeUpdate();

            return res > 0;
        }
    }

    public User passwordMatch(User user) throws SQLException
    {
        try (Connection con = db.getConnection())
        {
            String sql = "select * from Users where Users.Username like ?;";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, user.getUserName());

            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                return new User(rs.getString("FirstName"),
                                rs.getString("LastName"),
                                rs.getString("UserName"),
                                rs.getString("Email"));
            }
            return null;
        }
    }

    public boolean passwordMatch(User user, String pass) throws SQLException
    {
        try (Connection con = db.getConnection())
        {
            String sql = "SELECT * FROM Users "
                         + "WHERE Users.UserName like ? "
                         + "AND Users.Password like ?;";
            PreparedStatement stmt = con.prepareStatement(sql);

            int i = 1;
            stmt.setString(i++, user.getUserName());
            stmt.setString(i++, pass);

            ResultSet rs = stmt.executeQuery();

            return rs.next();
        }
    }
}
