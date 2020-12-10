package app.coronainfo.coronainfo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.MaterialToolbar;

import app.coronainfo.coronainfo.Manager;
import app.coronainfo.coronainfo.R;
import app.coronainfo.coronainfo.ui.country.CountryFragment;
import app.coronainfo.coronainfo.ui.home.HomeFragment;
import app.coronainfo.coronainfo.ui.news.NewsFragment;

public class MainActivity extends AppCompatActivity {

    private MaterialToolbar mToolbar;
    private Fragment mHomeFragment, mCountryFragment, mNewsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.main_topAppBar);
        setListeners();

        mHomeFragment = HomeFragment.newInstance();
        replaceFragment(mHomeFragment);

        // update database
        Manager.updateTravelAlert(MainActivity.this);
        Manager.updateNews(MainActivity.this);
    }

    private void setListeners() {
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        replaceFragment(mHomeFragment);
                        break;
                    case R.id.menu_country:
                        if (mCountryFragment == null) {
                            mCountryFragment = CountryFragment.newInstance();
                        }
                        replaceFragment(mCountryFragment);
                        break;
                    case R.id.menu_news:
                        if (mNewsFragment == null) {
                            mNewsFragment = NewsFragment.newInstance();
                        }
                        replaceFragment(mNewsFragment);
                        break;
                    case R.id.menu_settings:
                        startActivity(createIntent(SettingsActivity.class));
                        break;
                    case R.id.menu_about:
                        startActivity(createIntent(AboutActivity.class));
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
    }

    private Intent createIntent(Class<?> className) {
        return new Intent(this, className);
    }

    /**
     * This method changes current fragment with the given fragment
     *
     * @param fragment the fragment to transition to
     */
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_placeholder, fragment);
        transaction.commit();
    }

}