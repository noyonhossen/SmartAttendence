package com.tanvirnoor.smartattendence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TeacherActivity_2 extends AppCompatActivity implements View.OnClickListener {
    Button course3,course4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_2);

        setTitle("Courses");

        course3 = (Button)findViewById(R.id.couese_3_teacher);
        course4 = (Button)findViewById(R.id.couese_4_teacher);
        course3.setOnClickListener(this);
        course4.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.couese_3_teacher){
            Intent intent = new Intent(TeacherActivity_2.this, Course_3_Activity.class);
            startActivity(intent);
        }
        if(view.getId()==R.id.couese_4_teacher){
            Intent intent = new Intent(TeacherActivity_2.this, Course_4_Activity.class);
            startActivity(intent);
        }
    }
}
