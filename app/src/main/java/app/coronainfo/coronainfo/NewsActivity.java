package app.coronainfo.coronainfo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.squareup.picasso.Picasso;

public class NewsActivity extends AppCompatActivity {

    private String TAG = "NewsActivity";

    private MaterialToolbar mToolbar;
    private ImageView mImageView;
    private MaterialTextView mTitleTextView, mDateTextView, mDescriptionTextView;
    private MaterialButton mBrowserButton;

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        initializeViews();
        setListeners();
        setContents();
    }

    private void initializeViews() {
        mToolbar = findViewById(R.id.news_toolbar);
        mImageView = findViewById(R.id.news_imageView);
        mTitleTextView = findViewById(R.id.news_title_textView);
        mDateTextView = findViewById(R.id.news_date_textView);
        mDescriptionTextView = findViewById(R.id.news_description_textView);
        mBrowserButton = findViewById(R.id.news_open_browser_button);
    }

    private void setListeners() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBrowserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(url));
                startActivity(browserIntent);
                Log.i(TAG, "openInBrowser:opened");
            }
        });
    }

    private void setContents() {
        url = getIntent().getStringExtra(Constants.NEWS_URL);

        String imageUrl = getIntent().getStringExtra(Constants.NEWS_IMAGE_URL);
        String title = getIntent().getStringExtra(Constants.NEWS_TITLE);
        String date = getIntent().getStringExtra(Constants.NEWS_DATE);
        String des = getIntent().getStringExtra(Constants.NEWS_DESCRIPTION);

        if (title == null || title.length() == 0) {
            title = "Unknown";
        }
        mTitleTextView.setText(title);

        if (date == null || date.length() == 0) {
            date = "Unknown";
        }
        mDateTextView.setText(date);

        if (des == null || des.length() == 0) {
            des = "Unknown";
        }
        mDescriptionTextView.setText(des);

        if (imageUrl != null && imageUrl.length() > 0) {
            Picasso.get().load(imageUrl).placeholder(R.drawable.ic_image_256).into(mImageView);
            mImageView.setVisibility(View.VISIBLE);
        }
    }

}