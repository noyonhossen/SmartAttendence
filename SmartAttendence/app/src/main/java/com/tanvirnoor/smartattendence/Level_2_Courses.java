package com.tanvirnoor.smartattendence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Level_2_Courses extends AppCompatActivity {
    ListView listView;
    String student_or_teacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_2__courses);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            student_or_teacher = bundle.getString("student_or_teacher");
        }

        listView=(ListView)findViewById(R.id.listViewId_2);
        final String[] list = getResources().getStringArray(R.array.list_level_2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Level_2_Courses.this,R.layout.sample_layout_course,R.id.textViewId,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(student_or_teacher.equals("student")){
                    if(list[position].equals("ECE 425")){
                        Intent intent = new Intent(Level_2_Courses.this, StudentActivity1.class);
                        intent.putExtra("Course","Course_1");
                        startActivity(intent);
                    }
                    else if(list[position].equals("ECE 437")){
                        Intent intent = new Intent(Level_2_Courses.this, StudentActivity1.class);
                        intent.putExtra("Course","Course_2");
                        startActivity(intent);
                    }
                    else if(list[position].equals("ECE 471")){
                        Intent intent = new Intent(Level_2_Courses.this, StudentActivity1.class);
                        intent.putExtra("Course","Course_3");
                        startActivity(intent);
                    }
                    else if(list[position].equals("ECE 475")){
                        Intent intent = new Intent(Level_2_Courses.this, StudentActivity1.class);
                        intent.putExtra("Course","Course_4");
                        startActivity(intent);
                    }
                }
                else if(student_or_teacher.equals("teacher")){
                    Intent intent = new Intent(Level_2_Courses.this,TeacherLogin.class);
                    intent.putExtra("subject",list[position]);
                    startActivity(intent);
                }

            }
        });
    }
}
