package team383.edu.panthertheatres;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    public static boolean cameFromRegister = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }
        Fragment fromRegister = null;
        if (cameFromRegister){
            fromRegister = new FeedbackFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    fromRegister).commit();

        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            cameFromRegister = false;
                            break;
                        case R.id.nav_showtimes:
                            selectedFragment = new ShowtimesFragment();
                            cameFromRegister = false;
                            break;
                        case R.id.nav_movies:
                            selectedFragment = new MoviesFragment();
                            cameFromRegister = false;
                            break;
                        case R.id.nav_locations:
                            selectedFragment = new LocationsFragment();
                            cameFromRegister = false;
                            break;
                        case R.id.nav_feedback:
                            selectedFragment = new FeedbackFragment();
                            cameFromRegister = false;
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
}