package io.codestellation.zenith;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.TextView;

/**
 * Created by Michael on 11/14/2015.
 */
public class SessionVariables {
    static final String USER_EMAIL = "logged_in_email";
    static final String LOGGEDIN_STATUS = "logged_in_status";

    public static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }
    public static void setLoggedInUserEmail(TextView.OnEditorActionListener ctx, String email) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(USER_EMAIL, email);
        editor.commit();
    }

    public static String getLoggedInEmailUser(Context ctx) {
        return getSharedPreferences(ctx).getString(USER_EMAIL, "");
    }

    public static void setUserLoggedInStatus(Context ctx, boolean status) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putBoolean(LOGGEDIN_STATUS, status);
        editor.commit();
    }

    public static boolean getUserLoggedInStatus(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(LOGGEDIN_STATUS, false);
    }

    public static void clearLoggedInEmailAddress(Context ctx) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.remove(USER_EMAIL);
        editor.remove(LOGGEDIN_STATUS);
        editor.commit();
    }
}
