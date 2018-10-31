package app.p.alex.weatherapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import app.p.alex.weatherapp.fragments.DetailForecastFragment;
import app.p.alex.weatherapp.fragments.FavoritePlacesFragment;
import app.p.alex.weatherapp.fragments.HomeFragment;
import app.p.alex.weatherapp.fragments.SettingsFragment;
import app.p.alex.weatherapp.fragments.ThreeDaysForecastFragment;
import app.p.alex.weatherapp.fragments.UserDetailsFragment;
import app.p.alex.weatherapp.fragments.WeekForecastFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private HomeFragment homeFragment;
    private FavoritePlacesFragment favoritePlacesFragment;
    private SettingsFragment settingsFragment;
    private ThreeDaysForecastFragment threeDaysForecastFragment;
    private WeekForecastFragment weekForecastFragment;
    private FragmentManager fragmentManager;

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initHomeFragment();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Добавление места в Избранное", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initHomeFragment() {
        fragmentManager = getSupportFragmentManager();
        homeFragment = new HomeFragment();
        fragmentManager.beginTransaction().add(R.id.fragment_container, homeFragment).commit();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                if (homeFragment != null) {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
                }
                break;
            case R.id.nav_tree_days_forecast:
                if (threeDaysForecastFragment != null) {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, threeDaysForecastFragment).commit();
                } else {
                    threeDaysForecastFragment = new ThreeDaysForecastFragment();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, threeDaysForecastFragment).commit();
                }
                break;
            case R.id.nav_week_forecast:
                if (weekForecastFragment != null) {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, weekForecastFragment).commit();
                } else {
                    weekForecastFragment = new WeekForecastFragment();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, weekForecastFragment).commit();
                }
                break;
            case R.id.nav_favorite:
                if (favoritePlacesFragment != null) {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, favoritePlacesFragment).commit();
                } else {
                    favoritePlacesFragment = new FavoritePlacesFragment();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, favoritePlacesFragment).commit();
                }
                break;
            case R.id.nav_settings:
                if (settingsFragment != null) {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, settingsFragment).commit();
                } else {
                    settingsFragment = new SettingsFragment();
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, settingsFragment).commit();
                }
                break;
            case R.id.nav_about_mistake:
                // будет неявный интент для отправки письма
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
