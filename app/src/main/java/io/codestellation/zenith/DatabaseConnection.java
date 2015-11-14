package io.codestellation.zenith;

import android.app.Application;

import java.sql.*;
/**
 * Created by Michael on 11/14/2015.
 */
public class DatabaseConnection extends Application{

    final String JDBC_DRIVER = "com.postgresql.jdbc.Driver";
    final String DB_TYPE = "postgresql";
    final String DB_DRIVER = "jdbc";
    final String DB_NAME = "zenith-psql";
    final String DB_HOST = "zenith-psql.cqvpy1ldbcd1.us-east-1.rds.amazonaws.com";
    final String DB_URL = String.format("%s:%s://%s/%s",DB_DRIVER, DB_TYPE, DB_HOST, DB_NAME);
    final String DB_USER = "zenith";
    final String DB_PASSWORD = "codestellation";
    Connection conn;

    public ResultSet sqlQuery(String query) {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            return rs;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
