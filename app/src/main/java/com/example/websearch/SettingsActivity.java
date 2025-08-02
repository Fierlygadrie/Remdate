package com.remdate.app;

import android.os.Bundle;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    private Switch darkModeSwitch;
    private Switch cacheSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        darkModeSwitch = findViewById(R.id.darkModeSwitch);
        cacheSwitch = findViewById(R.id.cacheSwitch);

        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            ThemeUtils.setDarkMode(this, isChecked);
        });

        cacheSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                WebUtils.clearWebViewCache(this);
            }
        });
    }
}