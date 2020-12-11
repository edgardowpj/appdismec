package com.example.practi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

public class AccountActivity extends AppCompatActivity {

    TextView tvid,tvname,tvemail,tvcontact,tvaddress;
    String tvname2,tvemail2;
    EditText tvemail3,tvusername;
    int position;
    Button btn_insert;


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
        btn_insert=findViewById(R.id.btnLogin2);

        tvemail3=findViewById(R.id.txtemail2);
        tvusername=findViewById(R.id.txcontact2);

        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");

        tvusername.setText(FavoriteActivity.users.get(position).getUsername());
        tvid.setText("Id: "+FavoriteActivity.users.get(position).getId());
        tvname.setText("Fullname: "+FavoriteActivity.users.get(position).getFullname());
        tvemail.setText("Email: "+FavoriteActivity.users.get(position).getEmail());
        tvcontact.setText("Username: "+FavoriteActivity.users.get(position).getUsername());
        tvaddress.setText("Tipe: "+FavoriteActivity.users.get(position).getTipe());
        tvname2=tvname.toString();
        tvemail2=tvemail.toString();

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isertData();
            }
        });


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

    private void isertData() {

        final String id = tvid.getText().toString().trim();
        final String username = tvusername.getText().toString().trim();
        final String email = tvemail3.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("cargando...");

        if(username.isEmpty()){
            tvusername.setError("complete los campos");
            return;
        }else if(email.isEmpty()){
            tvemail.setError("complete los campos");
            return;
        }else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.1.66/proyecto/insert.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if(response.equalsIgnoreCase("Data Inserted")){

                                Toast.makeText(AccountActivity.this, "Datos insertados", Toast.LENGTH_SHORT).show();

                                progressDialog.dismiss();
                            }
                            else{
                                Toast.makeText(AccountActivity.this, response, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(AccountActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> params = new HashMap<String,String>();

                    params.put("username",username);
                    params.put("email",email);
                    return params;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(AccountActivity.this);
            requestQueue.add(request);

        }
    }


}