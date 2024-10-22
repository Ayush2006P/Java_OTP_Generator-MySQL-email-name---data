package Dao;

import Model.User;
import com.jk.explore.java.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // Method to check if a user with a given email exists in the database
    public static boolean isExists(String email) throws SQLException {
        Connection connection = MyConnection.getConnection();

        // SQL query to check if the email exists in the database
        PreparedStatement ps = connection.prepareStatement("SELECT email FROM users WHERE email = ?");
        ps.setString(1, email);  // Set the email parameter

        ResultSet rs = ps.executeQuery();

        // If the result set has at least one row, the email exists
        return rs.next();
    }

    // Method to save a new user to the database
    public static int saveUser(User user) throws SQLException {
        Connection connection = MyConnection.getConnection();

        // Correct SQL syntax: Inserting only name and email, auto-incrementing id
        PreparedStatement ps = connection.prepareStatement("INSERT INTO users (name, email) VALUES(?, ?)");
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());

        return ps.executeUpdate();  // Return number of rows affected
    }
}
