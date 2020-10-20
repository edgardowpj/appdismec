package com.example.practi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_student);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView2);

        bottomNavigationView.setSelectedItemId(R.id.menuSettings2);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.menuHome2:
                        startActivity(new Intent(getApplicationContext(),StudentActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menuSettings2:
                        return true;
                }
                return false;
            }
        });
    }

    public void ExitToTheApp(View view){
        Intent intent = new Intent(this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}