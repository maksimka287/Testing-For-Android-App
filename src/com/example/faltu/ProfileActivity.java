package com.example.faltu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by MaKsa on 24.03.16.
 */
public class ProfileActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        Button profile = (Button) findViewById(R.id.profile);
        profile.setFocusable(true);

        Button tests = (Button) findViewById(R.id.tests);
        tests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, ListTestsActivity.class);
                intent.addFlags(1073741824);
                startActivity(intent);
            }
        });

    }

}
