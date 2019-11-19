package com.tanvirnoor.smartattendence;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentDetailsOfEachID extends AppCompatActivity {
    String bvalue;
    DatabaseReference databaseReference;
    DatabaseReference databaseReferenceTotalClass;
    int attend;
    double marks;
    int credit;
    TextView textViewId,textViewTotalAttendence,textViewPersentage,textViewTotalClass;

    private ListView listView;
    private List<StudentInfo> studentList;
    private  CustomAdapter customAdapter;
    String Course;
    String value;
    int total_class;
    private static String FILE_NAME = "attendance.txt";
    File file;
    StringBuilder builder = new StringBuilder();
    StringBuilder builder2 = new StringBuilder();
    private Context mContext=StudentDetailsOfEachID.this;

    private static final int REQUEST = 112;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details_of_each_id);



        textViewId = (TextView)findViewById(R.id.idOfStudent);
        textViewPersentage = (TextView)findViewById(R.id.percentageId);
        textViewTotalAttendence = (TextView)findViewById(R.id.totalAttendenceId);
        textViewTotalClass = (TextView)findViewById(R.id.totalclassId);

        studentList = new ArrayList<>();
        customAdapter = new CustomAdapter(StudentDetailsOfEachID.this,studentList);
        listView = (ListView)findViewById(R.id.listViewOneStudentDetailsId);





        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            bvalue = bundle.getString("ID");
            FILE_NAME=bvalue+".txt";
            Course = bundle.getString("course");
            if(Course.equals("Course_1")){
                credit=2*5;
                databaseReference = FirebaseDatabase.getInstance().getReference("Attendence1");
                databaseReferenceTotalClass = FirebaseDatabase.getInstance().getReference("TotalClass_Course_1");
            }
            else if(Course.equals("Course_2")){
                credit=3*5;
                databaseReference = FirebaseDatabase.getInstance().getReference("Attendence2");
                databaseReferenceTotalClass = FirebaseDatabase.getInstance().getReference("TotalClass_Course_2");
            }
            else if(Course.equals("Course_3")){
                credit=2*5;
                databaseReference = FirebaseDatabase.getInstance().getReference("Attendence3");
                databaseReferenceTotalClass = FirebaseDatabase.getInstance().getReference("TotalClass_Course_3");
            }
            else if(Course.equals("Course_4")){
                credit=3*5;
                databaseReference = FirebaseDatabase.getInstance().getReference("Attendence4");
                databaseReferenceTotalClass = FirebaseDatabase.getInstance().getReference("TotalClass_Course_4");
            }

        }
        if (Build.VERSION.SDK_INT >= 23) {
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (!hasPermissions(mContext, PERMISSIONS)) {
                ActivityCompat.requestPermissions((Activity) mContext, PERMISSIONS, REQUEST );
            } else {
                //do here
                file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),FILE_NAME);
            }
        } else {
            //do here
        }
        setTitle(bvalue);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studentList.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                    StudentInfo student = dataSnapshot1.getValue(StudentInfo.class);
                    //Toast.makeText(StudentDetails_1.this,"ID: "+student.getId(),Toast.LENGTH_SHORT).show();

                    if(student.getId().equals(bvalue)){
                        attend++;

                        studentList.add(student);
                        builder2.append("\nDate: "+student.getDate()+"\n");
                        builder2.append("Time: "+student.getTime()+"\n");
                        builder2.append("Latitude: "+student.getLatitude_txt()+"\n");
                        builder2.append("Longitude: "+student.getLongitude_txt()+"\n");
                    }
                }
                listView.setAdapter(customAdapter);
                //Toast.makeText(StudentDetailsOfEachID.this,"1602164  Attendance: "+attend, Toast.LENGTH_SHORT).show();
                textViewId.setText("ID: "+bvalue);
                textViewTotalAttendence.setText("Total Attendance: "+attend);

                marks=((double)(attend)/(double)total_class)*(double)credit;
                textViewPersentage.setText("Marks: "+marks);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReferenceTotalClass.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                value = dataSnapshot.getValue(String.class);
                total_class = Integer.parseInt(value);
                textViewTotalClass.setText("Total Class: "+value);

                marks=((double)(attend)/(double)total_class)*(double)credit;
                textViewPersentage.setText("Marks: "+marks);

                //Toast.makeText(getApplicationContext(), "Password: " + value, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getApplicationContext(), "Failed to read value. " + error.toException(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void Save(View v) {
        builder.append("Id: "+bvalue+"\n");
        builder.append("Course: "+Course+"\n");
        builder.append("Total Class: "+value+"\n");
        builder.append("Total Attendance: "+attend+"\n");
        builder.append("Marks: "+marks+"\n");
        builder.append(builder2.toString());
        //String text = GetText();
        String text = builder.toString();
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file);
            //fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());


            Toast.makeText(this, "Data save successfully", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    protected void onStart() {


        super.onStart();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //do here
                } else {
                    Toast.makeText(mContext, "The app was not allowed to write in your storage", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}
