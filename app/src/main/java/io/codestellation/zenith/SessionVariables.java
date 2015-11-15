package io.codestellation.zenith;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michael on 11/14/2015.
 */
public class SessionVariables extends Application {
    public static SharedPreferences preferences;
    static SharedPreferences.Editor editor = preferences.edit();
    //global variables to be used across application
    private static String[] todaysGoals;
    private static ArrayList<String> goalHistory;
    private static Date today;
    private static ArrayList<String> menuOptions;
    private static HashMap catExp;
    private static final String logInEmail = "logInEmail";
    private static final String logInState = "logInState";


    public static void initialize(){
       /* preferences = getSharedPreferences(this.getApplicationContext());
        editor.putString(logInEmail, null);
        editor.putBoolean(logInState, false);
        editor.commit();*/
        today = new Date();
        todaysGoals = new String[15];
        goalHistory = new ArrayList<String>();
        menuOptions = new ArrayList<String>(Arrays.asList("Today's Goals", "social", "History", "Settings"));
        catExp  = new HashMap<String, Integer>();
    }

    public static SharedPreferences getSharedPreferences(Context applicationContext){
        return applicationContext.getSharedPreferences("zenith",0);
    }
    public static void setLoggedInUserEmail(String email){
        editor.putString(logInEmail, email);
        editor.commit();
    }
    public static void setLoggedInState(Boolean loggedIn) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(logInState, loggedIn);
        editor.commit();
    }

    public static String[] getTodaysGoals(){
        return todaysGoals;
    }
    public static ArrayList<String> getHistory(){
        return goalHistory;
    }
    public static Date getDay(){
        return today;
    }
    public static HashMap getCatExp(){
        return catExp;
    }

}
