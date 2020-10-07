package com.example.practi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String nombres="fullname";
    TextView txtBienvenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtBienvenido=(TextView)findViewById(R.id.textUsername);
        String usuario=getIntent().getStringExtra("users");
        txtBienvenido.setText("¡ "+ usuario + "!");
    }
}