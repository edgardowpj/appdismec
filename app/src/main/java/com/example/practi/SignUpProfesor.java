package com.example.practi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class SignUpProfesor extends AppCompatActivity {

    TextInputEditText textInputEditTextFullname, textInputEditTextUsername, textInputEditTextPassword, textInputEditTextEmail, textInputEditTextCourse;
    Button buttonSignUp;
    TextView textViewLogin;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_profesor);

        textInputEditTextFullname= findViewById(R.id.fullname);
        textInputEditTextUsername= findViewById(R.id.username);
        textInputEditTextPassword= findViewById(R.id.password);
        textInputEditTextEmail= findViewById(R.id.email);
        textInputEditTextCourse= findViewById(R.id.course);
        buttonSignUp= findViewById(R.id.buttonSignUp);
        textViewLogin=findViewById(R.id.loginText);
        progressBar=findViewById(R.id.progress);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String fullname,username,password,email,course;
                fullname= String.valueOf(textInputEditTextFullname.getText());
                username= String.valueOf(textInputEditTextUsername.getText());
                password= String.valueOf(textInputEditTextPassword.getText());
                email= String.valueOf(textInputEditTextEmail.getText());
                course= String.valueOf(textInputEditTextCourse.getText());

                if(!fullname.equals("")&& !username.equals("") && !password.equals("") && !email.equals("") && !course.equals("")){

                    //Start ProgressBar first (Set visibility VISIBLE)
                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[5];

                            field[0] = "username";
                            field[1] = "email";
                            field[2] = "password";
                            field[3] = "fullname";
                            field[4] = "course";

                            String[] data = new String[5];
                            data[0] = username;
                            data[1] = email;
                            data[2] = password;
                            data[3] = fullname;
                            data[4] = course;
                            PutData putData = new PutData("http://192.168.1.55/proyecto/signupProfesor.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Success")){
                                        Intent intent = new Intent(getApplicationContext(), Login.class);
                                        startActivity(intent);
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                                        finish();
                                    }else{
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


    }
}