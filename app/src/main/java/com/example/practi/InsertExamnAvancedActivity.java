package com.example.practi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InsertExamnAvancedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_examn_avanced);
    }

    public void Question_Exam(View view){
        Intent intent = new Intent(this, InsertExamActivity.class);
        startActivity(intent);
    }

    public void Avanced_Exam(View view){
        Intent intent = new Intent(this,AvancedQuizActivity.class);
        startActivity(intent);
    }

}