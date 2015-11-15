package io.codestellation.zenith;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;

public class profilePage extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        ImageView image = (ImageView) findViewById(R.id.titleProfilePage);
        image.setImageResource(R.drawable.temp_radar_image);
    }

}
