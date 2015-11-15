package io.codestellation.zenith;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by Michael on 11/14/2015.
 */
public class SQLQueries {

    //returns details of mission by {missionName, shortDesc, longDesc, exp, and category}


    public String missionDetailsQuery(int i ){
        return "SELECT * FROM all_missions WHERE m_id = " + i + ";";
    }

    public String getCategoryName(int i){
        return "SELECT c_name FROM categories JOIN all_missions ON categories.c_id = all_missions.m_c_id WHERE c_id = " + i  +";" ;
    }

    public String clearStaleMissions() {
        return "DELETE FROM user_missions WHERE um_status = 'Not Started';";
    }

    public String addNewMission(int m_id) {
        return "INSERT INTO user_missions VALUES (" + 0 + ", " + m_id + ", 'Not Started');";
    }
    public String getNumInProgress(){
        return "SELECT COUNT(*) FROM user_missions WHERE status = 'In Progress'";
    }
    public String getAlreadyInProgress(int x){
        return "SELECT CASE WHEN EXISTS (SELECT * FROM user_missions WHERE m_id = " + x + ") THEN 1 ELSE 0 END";
    }
}
