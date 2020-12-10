package app.coronainfo.coronainfo.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import app.coronainfo.coronainfo.Manager;
import app.coronainfo.coronainfo.R;
import app.coronainfo.coronainfo.model.Constants;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "SettingsActivity";
    private SharedPreferences preferences;
    private boolean first;
    private List<String> countries;
    private Map<String, String> countryCodeMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        this.countryCodeMap = new Manager().getCountryCodes();
        this.countries = new ArrayList<>(countryCodeMap.keySet());
        Collections.sort(countries);
        this.preferences = getSharedPreferences(Constants.PREFERENCE_FILE_NAME, MODE_PRIVATE);

        MaterialToolbar toolbar = findViewById(R.id.settings_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Setting country
        Spinner mCountrySpinner = findViewById(R.id.home_country_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCountrySpinner.setAdapter(adapter);
        mCountrySpinner.setOnItemSelectedListener(this);
        mCountrySpinner.setSelection(getPosition());

        // Setting theme switch
        final SwitchMaterial mThemeSwitch = findViewById(R.id.settings_theme_switch);
        boolean isChecked = preferences.getBoolean(Constants.CURRENT_THEME, false);
        mThemeSwitch.setChecked(isChecked);
        mThemeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(Constants.CURRENT_THEME, isChecked);
                editor.apply();
                Log.i(TAG, "saveTheme:saved");
            }
        });
    }

    private int getPosition() {
        first = true;
        String saved = preferences.getString(Constants.HOME_COUNTRY_CODE, "Default");
        for (String key : countryCodeMap.keySet()) {
            assert saved != null;
            if (saved.equals(countryCodeMap.get(key))) {
                return countries.indexOf(key);
            }
        }
        return 0;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (first) {
            first = false;
        } else {
            SharedPreferences.Editor editor = preferences.edit();
            String country = String.valueOf(parent.getItemAtPosition(position));
            editor.putString(Constants.HOME_COUNTRY_CODE, countryCodeMap.get(country));
            editor.apply();
            Log.i(TAG, "saveHomeCountry:saved");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Nothing is selected!", Toast.LENGTH_SHORT).show();
    }

}