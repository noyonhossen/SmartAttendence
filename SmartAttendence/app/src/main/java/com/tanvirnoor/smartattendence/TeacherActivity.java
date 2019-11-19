package com.tanvirnoor.smartattendence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TeacherActivity extends AppCompatActivity implements View.OnClickListener {
    Button course1,course2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        setTitle("Courses");

        course1 = (Button)findViewById(R.id.couese_1_teacher);
        course2 = (Button)findViewById(R.id.couese_2_teacher);
        course1.setOnClickListener(this);
        course2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.couese_1_teacher){
            Intent intent = new Intent(TeacherActivity.this, Level.class);
            //Intent intent = new Intent(TeacherActivity.this, Course_1_Activity.class);
            startActivity(intent);
        }
        /*if(view.getId()==R.id.couese_2_teacher){
            Intent intent = new Intent(TeacherActivity.this, Course_2_Activity.class);
            startActivity(intent);
        }*/
    }
}
