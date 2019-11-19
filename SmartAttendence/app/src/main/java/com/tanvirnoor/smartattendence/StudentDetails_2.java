package com.tanvirnoor.smartattendence;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentDetails_2 extends AppCompatActivity {
    private ListView listView;
    DatabaseReference databaseReference;
    private List<StudentInfo> studentList;
    private  CustomAdapter2 customAdapter;
    String todaysDate;
    TextView total;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details_2);

        setTitle("Today's Attendance");

        databaseReference = FirebaseDatabase.getInstance().getReference("Attendence2");

         total = (TextView) findViewById(R.id.totalAttendedStudentsID2);;

        studentList = new ArrayList<>();
        customAdapter = new CustomAdapter2(StudentDetails_2.this,studentList);
        listView = (ListView)findViewById(R.id.listViewId2);

        DatePicker datePicker = new DatePicker(this);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        month++;
        int year =  datePicker.getYear();
        todaysDate = day+"/"+month+"/"+year;

    }
    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studentList.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    StudentInfo student = dataSnapshot1.getValue(StudentInfo.class);
                    if(student.getDate().equals(todaysDate)){
                        studentList.add(student);
                        count++;
                    }
                }
                listView.setAdapter(customAdapter);
                total.setText("Total: "+count);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onStart();
    }

    public void OpenCountApp(View view) {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.truiton.mobile.vision.facedetection");
        if (launchIntent != null) {
            startActivity(launchIntent);//null pointer check in case package name was not found
        }
        else{
            Toast.makeText(this,"No App",Toast.LENGTH_SHORT).show();
        }
    }
}
