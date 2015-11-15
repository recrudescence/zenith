package io.codestellation.zenith;

import android.app.Application;
import android.os.AsyncTask;

import java.sql.*;
/**
 * Created by Michael on 11/14/2015.
 */
public class DatabaseConnection extends AsyncTask<Void, Void, Boolean> {

    final String JDBC_DRIVER = "org.postgresql.Driver";
    final String DB_TYPE = "postgresql";
    final String DB_DRIVER = "jdbc";
    final String DB_NAME = "zenith_psql";
    final String DB_HOST = "zenith-psql.cqvpy1ldbcd1.us-east-1.rds.amazonaws.com";
    final String DB_PORT = "5432";
    final String DB_URL = String.format("%s:%s://%s:%s/%s",DB_DRIVER, DB_TYPE, DB_HOST, DB_PORT, DB_NAME);
    final String DB_USER = "zenith";
    final String DB_PASSWORD = "codestellation";
    String query;
    ResultSet rs;

    public void sqlQuery(String query) {
        this.query = query;
    }

    public ResultSet queryResult() {
        return rs;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("class not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("sql exception");
            e.printStackTrace();
        }
        System.out.println("db access complete");
        return true;
    }
}
