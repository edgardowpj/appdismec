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

public class ListPDFActivity extends AppCompatActivity {


    ListView list;
    AddPdf add;
    public static ArrayList<ListPDFLoad> pdfs=new ArrayList<>();
    String url="http://192.168.1.66/proyecto/ListarPDFS.php";
    ListPDFLoad pdfs2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_p_d_f);

        list=findViewById(R.id.myListView);
        add=new AddPdf(this,pdfs);
        list.setAdapter(add);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());
                CharSequence[] dialogItem = {"Ver"};
                builder.setTitle(pdfs.get(position).getId());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){
                            case 0:
                                startActivity(new Intent(getApplicationContext(), PdfViewerActivity.class)
                                        .putExtra("position",position));
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ShowDates();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView2);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.menuHome2:
                        startActivity(new Intent(getApplicationContext(),StudentActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menuSettings2:
                        startActivity(new Intent(getApplicationContext(),SettingsStudentActivity.class));
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

                pdfs.clear();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String succes=jsonObject.getString("succes");

                    JSONArray jsonArray=jsonObject.getJSONArray("documentspdf");

                    if(succes.equals("1")){
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject object=jsonArray.getJSONObject(i);
                            String id=object.getString("id");
                            String title=object.getString("title");
                            String location=object.getString("location");

                            pdfs2=new ListPDFLoad(id,title,location);
                            pdfs.add(pdfs2);
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
                Toast.makeText(ListPDFActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }


}