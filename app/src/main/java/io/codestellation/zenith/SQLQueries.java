package io.codestellation.zenith;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Michael on 11/14/2015.
 */
public class SQLQueries {

    public String getMissionDetails(int i) throws SQLException {
        String missionDetails = "";
        DatabaseConnection connection = new DatabaseConnection();
        ResultSet rs = connection.sqlQuery(missionDetailsQuery(i));
        while(rs.next()){
            String missionName = rs.getString(2).trim();
            String shortDesc = rs.getString(3).trim();
            String longDesc = rs.getString(4).trim();
            int exp = rs.getInt(5);
            ResultSet rsJoin = connection.sqlQuery(getCategoryName(i));
            while(rsJoin.next()) {
                String category = rs.getString(1).trim();
            }

        }

        return missionDetails;
    }

    public String missionDetailsQuery(int i ){
        return "SELECT * FROM all_missions WHERE m_id = " + i + ";";
    }
    public String getCategoryName(int i){
        return "SELECT  c_name FROM categories JOIN all_missions ON categories.c_id = all_missions.m_c_id WHERE c_id = " + i  +";" ;
    }
}
