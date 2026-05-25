package com.quynh.ai_skin_diagnosis;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNav = findViewById(R.id.bottomNav);

        // Fragment mặc định
        replaceFragment(new HomeFragment());
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            if (item.getItemId() == R.id.nav_home) {
                fragment = new HomeFragment();
            } else if (item.getItemId() == R.id.nav_history) {
                fragment = new HistoryFragment();
            }
            else if (item.getItemId() == R.id.nav_article) {
                fragment = new ArticleFragment();
            }
            else if (item.getItemId() == R.id.nav_tips) {
                fragment = new TipsFragment();
            }
            if (fragment != null) {
                replaceFragment(fragment);
            }
            return true;
        });
    }
    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}