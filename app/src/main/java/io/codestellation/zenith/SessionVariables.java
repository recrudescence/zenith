package io.codestellation.zenith;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.TextView;

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
    public  SharedPreferences preferences;
     SharedPreferences.Editor editor = preferences.edit();
    //global variables to be used across application
    private  ArrayList<Integer> todaysMissions;
    private  ArrayList<String> goalHistory;
    private  Calendar today;
    private  ArrayList<String> menuOptions;
    private  HashMap catExp;
    private  final String logInEmail = "logInEmail";
    private  final String logInState = "logInState";


    public  void initialize(){
        preferences = getSharedPreferences(this.getApplicationContext());
        editor.putString(logInEmail, null);
        editor.putBoolean(logInState, false);
        editor.commit();
        today = Calendar.getInstance();
        todaysMissions = new ArrayList<Integer>();
        goalHistory = new ArrayList<String>();
        menuOptions = new ArrayList<String>(Arrays.asList("Today's Goals", "social", "History", "Settings"));
        catExp  = new HashMap<String, Integer>();
    }

    public  SharedPreferences getSharedPreferences(Context applicationContext){
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
    public ArrayList<Integer> getTodaysMissions(){
        //create new missions everyday
        Calendar todaysDate = Calendar.getInstance();

        if (this.getDay().compareTo(todaysDate) < 0 || this.getDay() == null) {
            today = todaysDate;
            ArrayList<Integer> m_ids = new ArrayList<Integer>();
            for (int i = 0; i < 3; i++) {
                Random rand = new Random();
                int randomNum = rand.nextInt(16);
                //dont allow duplicate missions in a day
                if (m_ids.contains(randomNum))
                    i--;
                else
                    m_ids.add(randomNum);
            }
            return m_ids;
        }
        else
            return todaysMissions;
    }

}
