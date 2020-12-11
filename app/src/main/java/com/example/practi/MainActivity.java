package com.example.practi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public static Test test=new Test();
    Test test2;
    String nombres;
    TextView txtBienvenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtBienvenido=(TextView)findViewById(R.id.textUsername);

       BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

       bottomNavigationView.setSelectedItemId(R.id.menuHome);

       bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {

               switch (item.getItemId()){

                   case R.id.menuHome:
                       return true;

                 /*  case R.id.menuFavorite:
                       return true;*/

                /*   case R.id.menuAccount:
                       startActivity(new Intent(getApplicationContext(),AccountActivity.class));
                       overridePendingTransition(0,0);
                       return true;*/

                   case R.id.menuSettings:
                       startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
                       overridePendingTransition(0,0);
                       return true;
               }
               return false;
           }
       });




    }

    public void AddExam(View view){
        Intent intent = new Intent(this, InsertExamActivity.class);
        startActivity(intent);
    }
    public void ListStudent(View view){
        Intent intent = new Intent(this, FavoriteActivity.class);
        startActivity(intent);
    }


}