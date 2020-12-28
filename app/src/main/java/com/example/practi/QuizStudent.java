package com.example.practi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class QuizStudent extends AppCompatActivity {

    private Button Star_btn;
    private Button Cancel_btn;
    TextInputEditText textInputEditTextPassword;
    ProgressBar progressBar;
    TextView textViewName;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_student);

        Intent intent =getIntent();
        position = intent.getExtras().getInt("position");
        textViewName = findViewById(R.id.validar2);
        textViewName.setText(ListUsersToExamActivity.usersToExams.get(position).getUsername());


        Star_btn=findViewById(R.id.Star_Btn);
        Cancel_btn=findViewById(R.id.cancel_btn);
        textInputEditTextPassword=findViewById(R.id.passwordLogin);
        progressBar=findViewById(R.id.progress);


        Star_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username , password;

                username= textViewName.getText().toString();
                Log.d("Test",username);
                password= String.valueOf(textInputEditTextPassword.getText());
                if(!username.equals("") && !password.equals("")){

                    //Start ProgressBar first (Set visibility VISIBLE)
                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "password";
                            String[] data = new String[2];
                            data[0] = username;
                            data[1] = password;
                            PutData putData = new PutData("http://192.168.1.66/proyecto/login.php", "POST", field, data);
                            Log.d("Send1","Succes");
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    Log.d("Username",username);
                                    Log.d("Password",password);
                                    if(result.equals("Login Success")){
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), CategoriesActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else {
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }

                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(),"All fields required",Toast.LENGTH_SHORT).show();
                }
            }
        });


        Cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(QuizStudent.this,StudentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

}