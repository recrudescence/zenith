package io.codestellation.zenith;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by Michael on 11/14/2015.
 */
public class SQLQueries {

    //returns details of mission by {missionName, shortDesc, longDesc, exp, and category}
    public HashMap<String, String> getMissionDetails(int i) throws SQLException {
        HashMap<String, String> missionDetails = new HashMap<String, String>();
        DatabaseConnection connection = new DatabaseConnection();
        //call query to get the information for mission with m_id = 1
        ResultSet rs = connection.sqlQuery(missionDetailsQuery(i));
        while(rs.next()){
            //parse result and store all the fields
            String missionName = rs.getString(2).trim();
            missionDetails.put("missionName", missionName);
            String shortDesc = rs.getString(3).trim();
            missionDetails.put("shortDesc", shortDesc);
            String longDesc = rs.getString(4).trim();
            missionDetails.put("longDesc", longDesc);
            String exp = Integer.toString(rs.getInt(5));
            missionDetails.put("exp", exp);
            ResultSet rsJoin = connection.sqlQuery(getCategoryName(i));
            //run query to join m_c_id with categories to get category name
            while(rsJoin.next()) {
                String category = rs.getString(1).trim();
                missionDetails.put("category", category);
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
