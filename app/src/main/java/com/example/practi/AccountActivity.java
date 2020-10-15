package com.example.practi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AccountActivity extends AppCompatActivity {

    TextView tvid,tvname,tvemail,tvcontact,tvaddress;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        //Initializing Views
        tvid = findViewById(R.id.txtid);
        tvname = findViewById(R.id.txtname);
        tvemail = findViewById(R.id.txtemail);
        tvcontact = findViewById(R.id.txcontact);
        tvaddress = findViewById(R.id.txtaddress);

        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        tvid.setText("ID: "+FavoriteActivity.users.get(position).getId());
        tvname.setText("Nombre: "+FavoriteActivity.users.get(position).getFullname());
        tvemail.setText("Email: "+FavoriteActivity.users.get(position).getEmail());
        tvcontact.setText("Username: "+FavoriteActivity.users.get(position).getUsername());
        tvaddress.setText("Tipe: "+FavoriteActivity.users.get(position).getTipe());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.menuAccount);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.menuHome:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menuFavorite:
                        startActivity(new Intent(getApplicationContext(),FavoriteActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menuAccount:

                        return true;

                    case R.id.menuSettings:
                        startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}