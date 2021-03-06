package com.example.piggy_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.piggy_android.fragments.ChatListFragment;
import com.example.piggy_android.fragments.GroupFragment;
import com.example.piggy_android.fragments.HomeFragment;
import com.example.piggy_android.fragments.ProfileFragment;
import com.example.piggy_android.fragments.UsersFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardActivity extends AppCompatActivity {

    // Firebase Auth
    FirebaseAuth firebaseAuth;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Actionbar
        actionBar = getSupportActionBar();
        actionBar.setTitle("");

        // init
        firebaseAuth = FirebaseAuth.getInstance();

        // bottom navigation
        BottomNavigationView navigationView = findViewById(R.id.bottomNavigation);
        navigationView.setOnNavigationItemSelectedListener(selectedListener);

        // Home fragment is default
        HomeFragment fragment1 = new HomeFragment();
        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        ft1.replace(R.id.container, fragment1, "");
        ft1.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // handle clicks
            switch (item.getItemId()) {
                case R.id.home_nav:
                    //fragment transition
                    HomeFragment fragment1 = new HomeFragment();
                    FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                    ft1.replace(R.id.container, fragment1, "");
                    ft1.commit();
                    return true;
                case R.id.search_nav:
                    //fragment transition
                    UsersFragment fragment2 = new UsersFragment();
                    FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                    ft2.replace(R.id.container, fragment2, "");
                    ft2.commit();
                    return true;
                case R.id.groups_nav:
                    //fragment transition
                    GroupFragment fragment3 = new GroupFragment();
                    FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
                    ft3.replace(R.id.container, fragment3, "");
                    ft3.commit();
                    return true;
                case R.id.chat_nav:
                    //fragment transition
                    ChatListFragment fragment4 = new ChatListFragment();
                    FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
                    ft4.replace(R.id.container, fragment4, "");
                    ft4.commit();
                    return true;
                case R.id.profile_nav:
                    //fragment transition
                    ProfileFragment fragment5 = new ProfileFragment();
                    FragmentTransaction ft5 = getSupportFragmentManager().beginTransaction();
                    ft5.replace(R.id.container, fragment5, "");
                    ft5.commit();
                    return true;
            }
            return false;
        }
    };

    private void checkUserStatus() {
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            // user is signed in
            // set username
//            usernameTextView.setText(user.getDisplayName());
        } else {
            // user is not signed in, go to main activity
            startActivity(new Intent(DashboardActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onStart() {
        // check status on start
        checkUserStatus();
        super.onStart();
    }

}