package app.coronainfo.coronainfo.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import app.coronainfo.coronainfo.Manager;
import app.coronainfo.coronainfo.R;
import app.coronainfo.coronainfo.model.Constants;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Setting theme
        SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCE_FILE_NAME, MODE_PRIVATE);
        boolean isChecked = preferences.getBoolean(Constants.CURRENT_THEME, false);
        if (isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        // update database
        Manager.initDatabase(SplashActivity.this);
        Manager.updateWorldStates(SplashActivity.this);
        Manager.updateStates(SplashActivity.this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        Handler handler = new Handler(this.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        }, Constants.SPLASH_OUT_TIME);
    }
}