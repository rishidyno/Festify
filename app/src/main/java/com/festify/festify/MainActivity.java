package com.festify.festify;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.festify.festify.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * inheritance from AppCompatActivity
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
            R.id.navigation_home, R.id.navigation_dashboard
            , R.id.navigation_notifications).build();

    /**why is super .onCreate() is called ?
     * design principle so that everything
     * open for extension for closed for modification
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = binding.navView;
        // this keyword
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
