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
//     SharedPreferences.Editor editor = preferences.edit();
    //global variables to be used across application
    private  ArrayList<Integer> todaysMissions;
    private  ArrayList<String> goalHistory;
    private  Calendar today;
    private  ArrayList<String> menuOptions;
    private  HashMap catExp;
    private  final String logInEmail = "logInEmail";
    private  final String logInState = "logInState";
    public SQLQueries query = new SQLQueries();
    DatabaseConnection connection = new DatabaseConnection();


    public  void initialize() throws SQLException, InterruptedException {
//        preferences = getSharedPreferences(this.getApplicationContext());
//        editor.putString(logInEmail, null);
//        editor.putBoolean(logInState, false);
//        editor.commit();
        today = Calendar.getInstance();
        todaysMissions = getTodaysMissions();
        goalHistory = new ArrayList<String>();
        menuOptions = new ArrayList<String>(Arrays.asList("Today's Goals", "social", "History", "Settings"));
        catExp  = new HashMap<String, Integer>();

        //temp:
        getMissionDetails(1);
    }

    public  SharedPreferences getSharedPreferences(Context applicationContext){
        return applicationContext.getSharedPreferences("zenith",0);
    }
    public  void setLoggedInUserEmail(String email){
//        editor.putString(logInEmail, email);
//        editor.commit();
    }
    public  void setLoggedInState(Boolean loggedIn) {
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putBoolean(logInState, loggedIn);
//        editor.commit();
    }

    public  ArrayList<String> getHistory(){
        return goalHistory;
    }
    public  Calendar getDay(){
        return today;
    }
    public  HashMap getCatExp(){
        return catExp;
    }

    public ArrayList<Integer> getTodaysMissions() throws SQLException, InterruptedException {
        //create new missions everyday
        Calendar todaysDate = Calendar.getInstance();
        connection.sqlQuery(query.clearStaleMissions());
        Thread.sleep(2000);
        if (this.getDay().compareTo(todaysDate) < 0 || this.getDay() == null) {
            today = todaysDate;
            ArrayList<Integer> m_ids = new ArrayList<Integer>();
            connection.sqlQuery(query.getNumInProgress());
            connection.execute((Void) null);
            Thread.sleep(2000);
            ResultSet numInProgress = connection.queryResult();
            int numIP = 0;
            while(numInProgress.next()){
                numIP = numInProgress.getInt(1);
            }
            for (int i = numIP; i < 3; i++) {
                Random rand = new Random();
                int randomNum = rand.nextInt(16);
                //dont allow duplicate missions in a day
                connection.sqlQuery(query.getAlreadyInProgress(randomNum));
                Thread.sleep(2000);
                ResultSet isInProgress = connection.queryResult();
                int inProgress = 0;
                while (isInProgress.next()) {
                    inProgress = isInProgress.getInt(1);
                }
                //if mission already is a today's mission, get another one
                if (inProgress == 1)
                    i--;
                else {
                    m_ids.add(randomNum);
                    connection.sqlQuery(query.addNewMission(randomNum));
                    Thread.sleep(2000);
                    ResultSet toAdd = connection.queryResult();

                }
            }
            return todaysMissions = m_ids;
        }
        else
            return todaysMissions;
    }
    public HashMap<String, String> getMissionDetails(int i) throws SQLException, InterruptedException {
        HashMap<String, String> missionDetails = new HashMap<String, String>();
        DatabaseConnection connection = new DatabaseConnection();
        //call query to get the information for mission with m_id = 1
        connection.sqlQuery(query.missionDetailsQuery(i));
        connection.execute((Void) null);
        Thread.sleep(2000);
        ResultSet rs = connection.queryResult();
        if (rs != null) {
            while (rs.next()) {
                //parse result and store all the fields
                String missionName = rs.getString(2).trim();
                missionDetails.put("missionName", missionName);
                System.out.println(missionName);
                String shortDesc = rs.getString(3).trim();
                missionDetails.put("shortDesc", shortDesc);
                System.out.println(shortDesc);
                String longDesc = rs.getString(4).trim();
                missionDetails.put("longDesc", longDesc);
                System.out.println(longDesc);
                String exp = Integer.toString(rs.getInt(5));
                missionDetails.put("exp", exp);
                System.out.println(exp);
                connection.sqlQuery(query.getCategoryName(i));
                Thread.sleep(2000);
                ResultSet rsJoin = connection.queryResult();
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
