package io.codestellation.zenith;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import static io.codestellation.zenith.R.drawable.mainpagebackground;

public class mainPage extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        // Menu Drawer
        ResideMenu resideMenu = new ResideMenu(this);
        resideMenu.setBackground(mainpagebackground);
        resideMenu.attachToActivity(this);

        String titles[] = { "Home", "Profile", "Today's Goals", "Social", "History", "Settings" };
        int icon[] = { R.drawable.iconHome, R.drawable.iconProfile, R.drawable.todaysGoals, R.drawable.social, R.drawable.history, R.drawable.settings };

        int i;
        for (i = 0; i < titles.length; i++){
            ResideMenuItem item = new ResideMenuItem(this, icon[i], titles[i]);
            item.setOnClickListener(this);
            resideMenu.addMenuItem(item,  ResideMenu.DIRECTION_LEFT); // or  ResideMenu.DIRECTION_RIGHT
        }
    }

}
