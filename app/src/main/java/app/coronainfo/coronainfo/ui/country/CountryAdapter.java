package app.coronainfo.coronainfo.ui.country;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import app.coronainfo.coronainfo.R;
import app.coronainfo.coronainfo.model.Constants;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private Context mContext;
    private List<String> mCountryList;
    private Map<String, String> mCountryMap;

    public CountryAdapter(Context mContext, Map<String, String> mCountryMap) {
        this.mContext = mContext;
        this.mCountryMap = mCountryMap;
        this.mCountryList = new ArrayList<>(mCountryMap.keySet());
        Collections.sort(this.mCountryList);
    }

    @NonNull
    @Override
    public CountryAdapter.CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_simple, parent, false);
        CountryAdapter.CountryViewHolder viewHolder;
        viewHolder = new CountryAdapter.CountryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.CountryViewHolder holder, int position) {
        final String country = mCountryList.get(position);
        holder.textView.setText(country);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // start country activity
                Intent intent = new Intent(mContext, CountryActivity.class);
                intent.putExtra(Constants.COUNTRY_CODE, mCountryMap.get(country));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCountryList.size();
    }

    /**
     * The view holder class that extends {@link RecyclerView.ViewHolder}
     * for {@link CountryAdapter}.
     */
    public static class CountryViewHolder extends RecyclerView.ViewHolder {

        MaterialTextView textView;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textview);
        }
    }

}