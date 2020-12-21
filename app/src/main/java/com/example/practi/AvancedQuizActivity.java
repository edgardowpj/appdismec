package com.example.practi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AvancedQuizActivity extends AppCompatActivity {

    private Button btnSelect, btnUpload;
    private TextView textView;

    private  int REQ_PDF = 21;
    private  String encodedPDF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avanced_quiz);

        textView = findViewById(R.id.textView);
        btnSelect = findViewById(R.id.btnSelect);
        btnUpload = findViewById(R.id.btnUpload);

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
                chooseFile.setType("application/pdf");
                chooseFile = Intent.createChooser(chooseFile, "Choose a file");
                startActivityForResult(chooseFile, REQ_PDF);

            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadDocument();
            }
        });


    }


    private void uploadDocument() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("cargando...");
        Call<ResponsePOJO> call = RetrofitClient.getInstance().getAPI().uploadDocument(encodedPDF);
        call.enqueue(new Callback<ResponsePOJO>() {
            @Override
            public void onResponse(Call<ResponsePOJO> call, Response<ResponsePOJO> response) {
                progressDialog.show();
                Pass();
                Log.d("Test","Passed");
                progressDialog.dismiss();
                Toast.makeText(AvancedQuizActivity.this, response.body().getRemarks(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponsePOJO> call, Throwable t) {
                Toast.makeText(AvancedQuizActivity.this, "Network Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void Pass(){
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_PDF && resultCode == RESULT_OK && data != null){

            Uri path = data.getData();


            try {
                InputStream inputStream = AvancedQuizActivity.this.getContentResolver().openInputStream(path);
                byte[] pdfInBytes = new byte[inputStream.available()];
                inputStream.read(pdfInBytes);
                encodedPDF = Base64.encodeToString(pdfInBytes, Base64.DEFAULT);

                textView.setText("Document Selected");
                btnSelect.setText("Change Document");

                Toast.makeText(this, "Document Selected", Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}