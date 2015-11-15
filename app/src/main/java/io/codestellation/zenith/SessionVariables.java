package io.codestellation.zenith;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Michael on 11/14/2015.
 */
public class SessionVariables extends Application {
//    public  SharedPreferences preferences;
  //   SharedPreferences.Editor editor = preferences.edit();
    //global variables to be used across application
    private  ArrayList<Integer> todaysMissions = new ArrayList<Integer>();
    private  ArrayList<String> goalHistory = new ArrayList<String>()    ;
    private  Calendar today;
    private  ArrayList<String> menuOptions;
    private  HashMap catExp;
    private  final String logInEmail = "logInEmail";
    private  final String logInState = "logInState";
    public SQLQueries query = new SQLQueries();
    DatabaseConnection connection;


    public  void initialize() throws SQLException {
    //    preferences = getSharedPreferences(this.getApplicationContext());
      // editor.putString(logInEmail, null);
        //editor.putBoolean(logInState, false);
        //editor.commit();
        today = Calendar.getInstance();
        createTodaysMissions();
        goalHistory = new ArrayList<String>();
        menuOptions = new ArrayList<String>(Arrays.asList("Today's Goals", "social", "History", "Settings"));
        catExp  = new HashMap<String, Integer>();
    }

    public ArrayList<Integer> getTodaysMissions(){
        return todaysMissions;
    }
    /*public  SharedPreferences getSharedPreferences(Context applicationContext){
        return applicationContext.getSharedPreferences("zenith",0);
    }
    public  void setLoggedInUserEmail(String email){
        editor.putString(logInEmail, email);
        editor.commit();
    }
    public  void setLoggedInState(Boolean loggedIn) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(logInState, loggedIn);
        editor.commit();
    }*/

    public  ArrayList<String> getHistory(){
        return goalHistory;
    }
    public  Calendar getDay(){
        return today;
    }
    public  HashMap getCatExp(){
        return catExp;
    }

    public void createTodaysMissions() throws SQLException {
        //create new missions everyday
        Calendar todaysDate = Calendar.getInstance();
        connection = new DatabaseConnection();
        connection.sqlQuery(query.clearStaleMissions());
       // if (this.getDay().compareTo(todaysDate) < 0 || this.getDay() == null) {
            today = todaysDate;
            ResultSet numInProgress = connection.sqlQuery(query.getNumInProgress());
            int numIP = 0;
            if(numInProgress!= null){
            while(numInProgress.next()){
                numIP = numInProgress.getInt(1);
            }}
            for (int i = numIP; i < 3; i++) {
                Random rand = new Random();
                int randomNum = rand.nextInt(16);
                //dont allow duplicate missions in a day
                ResultSet isInProgress = connection.sqlQuery(query.getAlreadyInProgress(randomNum));
                int inProgress = 0;
                if (isInProgress !=null){
                while (isInProgress.next()) {
                    inProgress = isInProgress.getInt(1);
                }}
                //if mission already is a today's mission, get another one
                if (inProgress == 1)
                    i--;
                else {
                    todaysMissions.add(randomNum);
                    ResultSet toAdd = connection.sqlQuery(query.addNewMission(randomNum));
                }
            }
       // }
       // else
         //   return m_ids;
    }
    public HashMap<String, String> getMissionDetails(int i) throws SQLException {
        HashMap<String, String> missionDetails = new HashMap<String, String>();
        connection = new DatabaseConnection();
        //call query to get the information for mission with m_id = 1
        ResultSet rs = connection.sqlQuery(query.missionDetailsQuery(i));
        if(rs != null){
        while(rs.next()) {
            //parse result and store all the fields
            String missionName = rs.getString(2).trim();
            missionDetails.put("missionName", missionName);
            String shortDesc = rs.getString(3).trim();
            missionDetails.put("shortDesc", shortDesc);
            String longDesc = rs.getString(4).trim();
            missionDetails.put("longDesc", longDesc);
            String exp = Integer.toString(rs.getInt(5));
            missionDetails.put("exp", exp);
            ResultSet rsJoin = connection.sqlQuery(query.getCategoryName(i));
            //run query to join m_c_id with categories to get category name
            while (rsJoin.next()) {
                String category = rs.getString(1).trim();
                missionDetails.put("category", category);
            }
        }

        }

        return missionDetails;
    }

}
