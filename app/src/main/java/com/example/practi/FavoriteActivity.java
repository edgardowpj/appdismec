package com.example.practi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {

    ListView list;
    Add add;
    public static ArrayList<Users>users=new ArrayList<>();
    String url="http://192.168.1.55/proyecto/Listar.php";
    Users users2;
    TextView tvid,tvfulname,tvemail,tvusername,tvtipe;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);


        list=findViewById(R.id.myListView);
        add=new Add(this,users);
        list.setAdapter(add);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"Mostrar datos"};
                builder.setTitle(users.get(position).getFullname());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){

                            case 0:

                                startActivity(new Intent(getApplicationContext(), AccountActivity.class)
                                        .putExtra("position",position));

                                break;
                        }
                    }
                });


                builder.create().show();


            }
        });

        ShowDates();



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.menuFavorite);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.menuHome:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menuFavorite:

                        return true;

                  /*  case R.id.menuAccount:
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

    private void ShowDates() {
       StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {

               users.clear();
               try {
                   JSONObject jsonObject=new JSONObject(response);
                   String succes=jsonObject.getString("succes");

                   JSONArray jsonArray=jsonObject.getJSONArray("users");

                   if(succes.equals("1")){
                       for (int i=0;i<jsonArray.length();i++){
                           JSONObject object=jsonArray.getJSONObject(i);
                           String id=object.getString("id");
                           String fullname=object.getString("fullname");
                           String username=object.getString("username");
                           String email=object.getString("email");
                           String tipe=object.getString("tipe");

                           users2=new  Users(id,fullname,username,email,tipe);
                           users.add(users2);
                           add.notifyDataSetChanged();

                       }
                   }
               }catch (JSONException e){
                   e.printStackTrace();
               }
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Toast.makeText(FavoriteActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
           }
       });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }


}