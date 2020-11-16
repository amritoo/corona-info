package app.coronainfo.coronainfo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.textview.MaterialTextView;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    private MaterialTextView mConfirmedCTV, mDeathCTV, mTotalConfirmedCTV, mTotalDeathCTV, mTotalRecoverCTV, mActiveCTV,
            mConfirmedGTV, mDeathGTV, mTotalConfirmedGTV, mTotalDeathGTV, mTotalRecoverGTV, mActiveGTV, mDateTV;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mConfirmedCTV = view.findViewById(R.id.country_confirmed_textView);
        mDeathCTV = view.findViewById(R.id.country_death_textView);
        mTotalConfirmedCTV = view.findViewById(R.id.country_total_confirmed_textView);
        mTotalDeathCTV = view.findViewById(R.id.country_total_death_textView);
        mTotalRecoverCTV = view.findViewById(R.id.country_total_recovered_textView);
        mActiveCTV = view.findViewById(R.id.country_active_textView);

        mConfirmedGTV = view.findViewById(R.id.global_confirmed_textView);
        mDeathGTV = view.findViewById(R.id.global_death_textView);
        mTotalConfirmedGTV = view.findViewById(R.id.global_total_confirmed_textView);
        mTotalDeathGTV = view.findViewById(R.id.global_total_death_textView);
        mTotalRecoverGTV = view.findViewById(R.id.global_total_recovered_textView);
        mActiveGTV = view.findViewById(R.id.global_active_textView);

        mDateTV = view.findViewById(R.id.home_date_textView);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        SharedPreferences preferences = getContext().getSharedPreferences(Constants.PREFERENCE_FILE_NAME, MODE_PRIVATE);
        String countryCode = preferences.getString(Constants.HOME_COUNTRY_CODE, "BD");

        States country = new Manager(getContext()).getCountryStates(countryCode);
//        mConfirmedCTV.setText(country.getNewConfirmed());
//        mDeathCTV.setText(country.getNewDeath());
//        mTotalConfirmedCTV.setText(country.getTotalConfirmed());
//        mTotalDeathCTV.setText(country.getTotalDeath());
//        mTotalRecoverCTV.setText(country.getTotalRecovered());
//        mActiveCTV.setText(country.getActiveCase());
//
//        States global = new Manager(getContext()).getWorldStates();
//        mConfirmedGTV.setText(global.getNewConfirmed());
//        mDeathGTV.setText(global.getNewDeath());
//        mTotalConfirmedGTV.setText(global.getTotalConfirmed());
//        mTotalDeathGTV.setText(global.getTotalDeath());
//        mTotalRecoverGTV.setText(global.getTotalRecovered());
//        mActiveGTV.setText(global.getActiveCase());
//
//        mDateTV.setText(global.getTime());
    }

}