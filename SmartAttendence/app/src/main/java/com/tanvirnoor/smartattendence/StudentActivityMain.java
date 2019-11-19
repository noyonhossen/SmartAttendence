package com.tanvirnoor.smartattendence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentActivityMain extends AppCompatActivity implements View.OnClickListener {
    Button course1,course2;
    Button course3,course4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);

        setTitle("Courses");

        course1 = (Button)findViewById(R.id.studentID1);
        course2 = (Button)findViewById(R.id.studentID2);
        course3 = (Button)findViewById(R.id.studentID3);
        course4 = (Button)findViewById(R.id.studentID4);

        course1.setOnClickListener(this);
        course2.setOnClickListener(this);
        course3.setOnClickListener(this);
        course4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.studentID1){
            Intent intent = new Intent(StudentActivityMain.this, StudentActivity1.class);
            intent.putExtra("Course","Course_1");
            startActivity(intent);
        }
        if(view.getId()==R.id.studentID2){
            Intent intent = new Intent(StudentActivityMain.this, StudentActivity1.class);
            intent.putExtra("Course","Course_2");
            startActivity(intent);
        }
        if(view.getId()==R.id.studentID3){
            Intent intent = new Intent(StudentActivityMain.this, StudentActivity1.class);
            intent.putExtra("Course","Course_3");
            startActivity(intent);
        }
        if(view.getId()==R.id.studentID4){
            Intent intent = new Intent(StudentActivityMain.this, StudentActivity1.class);
            intent.putExtra("Course","Course_4");
            startActivity(intent);
        }
    }
}
