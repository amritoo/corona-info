package app.coronainfo.coronainfo.ui.news;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import app.coronainfo.coronainfo.R;
import app.coronainfo.coronainfo.model.Constants;
import app.coronainfo.coronainfo.model.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private Context mContext;
    private List<News> mNewsList;

    public NewsAdapter(Context mContext, List<News> mNewsList) {
        this.mContext = mContext;
        this.mNewsList = mNewsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_simple, parent, false);
        NewsViewHolder viewHolder;
        viewHolder = new NewsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        final News news = mNewsList.get(position);
        holder.textView.setText(news.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // start news activity
                Intent intent = new Intent(mContext, NewsActivity.class);
                intent.putExtra(Constants.NEWS_URL, news.getUrl());
                intent.putExtra(Constants.NEWS_IMAGE_URL, news.getUrlToImage());
                intent.putExtra(Constants.NEWS_TITLE, news.getTitle());
                intent.putExtra(Constants.NEWS_DATE, news.getDate());
                intent.putExtra(Constants.NEWS_DESCRIPTION, news.getDescription());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    /**
     * The view holder class that extends {@link RecyclerView.ViewHolder}
     * for {@link NewsAdapter}.
     */
    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        MaterialTextView textView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textview);
        }
    }

}
