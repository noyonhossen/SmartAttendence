package com.tanvirnoor.smartattendence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Level extends AppCompatActivity implements OnClickListener {
    Button level1,level2,level3,level4;
    String student_or_teacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            student_or_teacher = bundle.getString("student_or_teacher");
        }

        level1=(Button)findViewById(R.id.level_1);
        level2=(Button)findViewById(R.id.level_2);
        level3=(Button)findViewById(R.id.level_3);
        level4=(Button)findViewById(R.id.level_4);

        level1.setOnClickListener(this);
        level2.setOnClickListener(this);
        level3.setOnClickListener(this);
        level4.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        if(student_or_teacher.equals("student")){
            if(view.getId()==R.id.level_1){
                Intent intent = new Intent(Level.this, Level_1_Courses.class);
                intent.putExtra("student_or_teacher","student");
                startActivity(intent);
            }
            if(view.getId()==R.id.level_2){
                Intent intent = new Intent(Level.this, Level_2_Courses.class);
                intent.putExtra("student_or_teacher","student");
                startActivity(intent);
            }
            if(view.getId()==R.id.level_3){
                Intent intent = new Intent(Level.this, Level_3_Courses.class);
                intent.putExtra("student_or_teacher","student");
                startActivity(intent);
            }
            if(view.getId()==R.id.level_4){
                Intent intent = new Intent(Level.this, Level_4_Courses.class);
                intent.putExtra("student_or_teacher","student");
                startActivity(intent);
            }
        }
        else if(student_or_teacher.equals("teacher")){
            if(view.getId()==R.id.level_1){
                Intent intent = new Intent(Level.this, Level_1_Courses.class);
                intent.putExtra("student_or_teacher","teacher");
                startActivity(intent);
            }
            if(view.getId()==R.id.level_2){
                Intent intent = new Intent(Level.this, Level_2_Courses.class);
                intent.putExtra("student_or_teacher","teacher");
                startActivity(intent);
            }
            if(view.getId()==R.id.level_3){
                Intent intent = new Intent(Level.this, Level_3_Courses.class);
                intent.putExtra("student_or_teacher","teacher");
                startActivity(intent);
            }
            if(view.getId()==R.id.level_4){
                Intent intent = new Intent(Level.this, Level_4_Courses.class);
                intent.putExtra("student_or_teacher","teacher");
                startActivity(intent);
            }
        }


    }
}
