package io.codestellation.zenith;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import static io.codestellation.zenith.R.drawable.iconhome;
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
        int icon[] = {iconhome, R.drawable.iconprofile, R.drawable.icongoals, R.drawable.iconsocial, R.drawable.iconhistory, R.drawable.icongoals};

        int i;
        for (i = 0; i < titles.length; i++){
            ResideMenuItem item = new ResideMenuItem(this, icon[i], titles[i]);
            item.setOnClickListener(this);
            resideMenu.addMenuItem(item, ResideMenu.DIRECTION_LEFT); // or  ResideMenu.DIRECTION_RIGHT
        }
    }

}
