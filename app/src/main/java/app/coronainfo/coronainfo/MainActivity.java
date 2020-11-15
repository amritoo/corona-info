package app.coronainfo.coronainfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    private MaterialToolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.main_topAppBar);
        setListeners();
    }

    private void setListeners() {
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        System.out.println("Home clicked");
                        break;
                    case R.id.menu_country:
                        System.out.println("Search clicked");
                        break;
                    case R.id.menu_news:
                        System.out.println("News clicked");
                        break;
                    case R.id.menu_settings:
                        startActivity(createIntent(SettingsActivity.class));
                        break;
                    case R.id.menu_help:
                        System.out.println("Help clicked");
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
}