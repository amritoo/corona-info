package app.coronainfo.coronainfo.ui.country;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Map;

import app.coronainfo.coronainfo.Manager;
import app.coronainfo.coronainfo.R;

public class CountryFragment extends Fragment {

    private CountryAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Map<String, String> mCountryMap;

    public CountryFragment() {
        // Required empty public constructor
    }

    public static CountryFragment newInstance() {
        return new CountryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_country, container, false);

        mRecyclerView = view.findViewById(R.id.country_recyclerView);
        mCountryMap = new Manager().getCountryCodes();
        mAdapter = new CountryAdapter(getContext(), mCountryMap);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

}