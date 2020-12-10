package app.coronainfo.coronainfo.ui.country;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textview.MaterialTextView;

import app.coronainfo.coronainfo.Manager;
import app.coronainfo.coronainfo.R;
import app.coronainfo.coronainfo.model.Constants;
import app.coronainfo.coronainfo.model.States;

public class CountryActivity extends AppCompatActivity {

    private MaterialToolbar mToolbar;
    private MaterialTextView mCountryName, mNewConfirmed, mNewDeath, mTotalConfirmed,
            mTotalDeath, mTotalRecovered, mActive, mTravelAlert, mDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        initializeViews();
        setContents();

    }

    private void initializeViews() {
        mToolbar = findViewById(R.id.country_toolbar);
        mCountryName = findViewById(R.id.country_textView);
        mNewConfirmed = findViewById(R.id.confirmed_textView);
        mNewDeath = findViewById(R.id.death_textView);
        mTotalConfirmed = findViewById(R.id.total_confirmed_textView);
        mTotalDeath = findViewById(R.id.total_death_textView);
        mTotalRecovered = findViewById(R.id.total_recovered_textView);
        mActive = findViewById(R.id.active_textView);
        mTravelAlert = findViewById(R.id.warning_textView);
        mDate = findViewById(R.id.date_textView);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setContents() {
        String code = getIntent().getStringExtra(Constants.COUNTRY_CODE);
        States states = new Manager().getCountryStates(code);

        mCountryName.setText(states.getCountry());
        mNewConfirmed.setText(String.valueOf(states.getNewConfirmed()));
        mNewDeath.setText(String.valueOf(states.getNewDeath()));
        mTotalConfirmed.setText(String.valueOf(states.getTotalConfirmed()));
        mTotalDeath.setText(String.valueOf(states.getTotalDeath()));
        mTotalRecovered.setText(String.valueOf(states.getTotalRecovered()));
        mActive.setText(String.valueOf(states.getActiveCase()));
        String alert = states.getAlertMessage();
        if (alert != null && alert.length() > 0) {
            mTravelAlert.setText(alert);
        } else {
            mTravelAlert.setVisibility(View.GONE);
        }
        mDate.setText(states.getTimeFormatted());
    }

}