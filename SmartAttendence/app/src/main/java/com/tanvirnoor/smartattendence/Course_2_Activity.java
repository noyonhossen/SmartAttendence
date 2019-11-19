package com.tanvirnoor.smartattendence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Course_2_Activity extends AppCompatActivity implements View.OnClickListener{
    DatabaseReference databaseReference2;
    DatabaseReference databaseReferenceTotalClass;
    EditText editText;

    Button button;

    String value;
    TextView textView;
    int total_class;

    boolean active = true;
    boolean active2 = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_2_);

        setTitle("Course 2");

        databaseReference2 = FirebaseDatabase.getInstance().getReference("Password2");
        databaseReferenceTotalClass = FirebaseDatabase.getInstance().getReference("TotalClass_Course_2");

        button=(Button)findViewById(R.id.button_send_passwordID2);
        editText=(EditText)findViewById(R.id.edittextPasswordID2);
        textView = (TextView)findViewById(R.id.total_classID_2);
        button.setOnClickListener(this);

        databaseReferenceTotalClass.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                value = dataSnapshot.getValue(String.class);
                total_class = Integer.parseInt(value);
                textView.setText(value);

                //Toast.makeText(getApplicationContext(), "Password: " + value, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getApplicationContext(), "Failed to read value. " + error.toException(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.button_send_passwordID2){
            if(button.getText().equals("Set Password")){
                String user_pass = editText.getText().toString();
                databaseReference2.setValue(user_pass);
                Toast.makeText(Course_2_Activity.this,"Password Set Successfully",Toast.LENGTH_SHORT).show();
                button.setText("Cancel");

                /*String method = "register";
                BackgroundTask backgroundTask = new BackgroundTask(this);
                backgroundTask.execute(method,name,user_name,user_pass);*/

            }
            else{
                databaseReference2.setValue("");
                Toast.makeText(Course_2_Activity.this,"Password Remove Successfully",Toast.LENGTH_SHORT).show();
                button.setText("Set Password");
            }
        }
    }
    public void OpenDetails2(View view) {
        Intent intent = new Intent(Course_2_Activity.this, AllStudents.class);
        intent.putExtra("course","Course_2");
        startActivity(intent);
    }
    public void TodaysAttendence2(View view) {
        Intent intent = new Intent(Course_2_Activity.this, StudentDetails_2.class);
        startActivity(intent);
    }

    public void MinusClass(View view) {
        if(active2) {
            total_class--;
            if(total_class<0){
                Toast.makeText(Course_2_Activity.this,"Class cannot be minus",Toast.LENGTH_SHORT).show();
            }
            else {

                databaseReferenceTotalClass.setValue(Integer.toString(total_class));
                textView.setText(""+total_class);
                Toast.makeText(Course_2_Activity.this,"Class Minus Successfully",Toast.LENGTH_SHORT).show();

            }
        }
        active2=false;

    }

    public void AddClass(View view) {
        if(active){
            total_class++;
            databaseReferenceTotalClass.setValue(Integer.toString(total_class));
            textView.setText(""+total_class);
            Toast.makeText(Course_2_Activity.this,"Class Added Successfully",Toast.LENGTH_SHORT).show();
        }
        active=false;
    }
}
