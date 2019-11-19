package com.tanvirnoor.smartattendence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainPage extends AppCompatActivity implements View.OnClickListener{
    Button buttonTeacher;
    Button buttonStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        buttonTeacher = (Button)findViewById(R.id.buttonTeacherID);
        buttonStudent = (Button)findViewById(R.id.buttonStudentID);

        buttonTeacher.setOnClickListener(this);
        buttonStudent.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.buttonStudentID){
            //Intent intent = new Intent(MainPage.this, StudentActivityMain.class);
            Intent intent = new Intent(MainPage.this, Level.class);
            intent.putExtra("student_or_teacher","student");
            startActivity(intent);
        }
        if(view.getId()==R.id.buttonTeacherID){
            Intent intent = new Intent(MainPage.this,Level.class);
            intent.putExtra("student_or_teacher","teacher");
            startActivity(intent);
        }
    }



}
