package io.codestellation.zenith;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.service.textservice.SpellCheckerService;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.MotionEvent;
import android.widget.Toast;
import android.widget.RelativeLayout;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import java.sql.SQLException;

import static io.codestellation.zenith.R.drawable.*;

public class MainPage extends ActionBarActivity implements View.OnClickListener, MainFragment.OnFragmentInteractionListener,ProfileFragment.OnFragmentInteractionListener,GoalsFragment.OnFragmentInteractionListener,SocialFragment.OnFragmentInteractionListener,HistoryFragment.OnFragmentInteractionListener, SettingsFragment.OnFragmentInteractionListener, HomeFragment.OnFragmentInteractionListener

{
    private ResideMenu resideMenu;
    private MainPage mContext;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemProfile;
    private ResideMenuItem itemGoals;
    private ResideMenuItem itemSocial;
    private ResideMenuItem itemHistory;
    private ResideMenuItem itemSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        mContext = this;
        SessionVariables sv = (SessionVariables) getApplicationContext();
        try {
            sv.initialize();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setupMenu();

        if (savedInstanceState == null)
            changeFragment(new MainFragment());
    }

    private void setupMenu(){

        // Menu Drawer
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(zenithmainbg);
        resideMenu.attachToActivity(this);

        resideMenu.setMenuListener(menuListener); // Prints Open/Close - not needed

        //Scale factor has to be between 0.0f and 1.0f
        resideMenu.setScaleValue(0.75f);

        //Create Menu Items
        String titles[] = { "Home", "Profile", "Missions", "Social", "History", "Settings" };
        int icon[] = {R.drawable.icon_home, R.drawable.icon_profile, R.drawable.icon_goals, R.drawable.icon_social, R.drawable.icon_history, R.drawable.icon_settings};

        itemHome = new ResideMenuItem(this, icon[0], titles[0]);
        itemProfile = new ResideMenuItem(this, icon[1], titles[1]);
        itemGoals= new ResideMenuItem(this, icon[2], titles[2]);
        itemSocial= new ResideMenuItem(this, icon[3], titles[3]);
        itemHistory= new ResideMenuItem(this, icon[4], titles[4]);
        itemSettings= new ResideMenuItem(this, icon[5], titles[5]);

        itemHome.setOnClickListener(this);
        itemProfile.setOnClickListener(this);
        itemGoals.setOnClickListener(this);
        itemSocial.setOnClickListener(this);
        itemHistory.setOnClickListener(this);
        itemSettings.setOnClickListener(this);


        //Add our icons to each side pls

        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemProfile, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemGoals, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemSocial, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemHistory, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_LEFT);

        //We could choose to disable a direction with
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

//        findViewById(R.id.bLeft).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
//            }
//        });
//        findViewById(R.id.bRight).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
//            }
//        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {     //SWIPE LEFT ALL DAY.
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {   //CLICK THE MENU BUTTONSSSSS
        if(view == itemHome)
        {
            changeFragment(new MainFragment());
        }else if (view == itemProfile)
        {
            changeFragment(new ProfileFragment());
        }else if (view == itemGoals)
        {
            changeFragment(new GoalsFragment());
        }else if (view == itemSocial)
        {
            changeFragment(new SocialFragment());
        }else if (view == itemHistory)
        {
            changeFragment(new HistoryFragment());
        }
        else if (view == itemSettings)
        {
            changeFragment(new SettingsFragment());
        }

        resideMenu.closeMenu();
    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener(){
        @Override
        public void openMenu() {
            Toast.makeText(mContext, "Open Sesame!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
            Toast.makeText(mContext, "Abra Close-dabra!", Toast.LENGTH_SHORT).show();
        }
    };

    private void changeFragment(Fragment targetFragment)
    {
        SessionVariables sv = (SessionVariables) getApplicationContext();

        resideMenu.clearIgnoredViewList();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    public ResideMenu getResideMenu(){
        return resideMenu;
    }

    public void onFragmentInteraction(Uri uri){
        //you can leave it empty, needed in case fragments must interact with eachother.
    }
}
