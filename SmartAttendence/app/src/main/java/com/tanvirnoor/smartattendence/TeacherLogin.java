package com.tanvirnoor.smartattendence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TeacherLogin extends AppCompatActivity {
    EditText editText;
    EditText editTextUserName;
    DatabaseReference databaseReference_t1_user;
    DatabaseReference databaseReference_t1_pass;
    DatabaseReference databaseReference_t2_user;
    DatabaseReference databaseReference_t2_pass;
    String value_t1_user;
    String value_t1_pass;
    String value_t2_user;
    String value_t2_pass;
    String subject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        setTitle("Login");
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            subject = bundle.getString("subject");
        }
        //if(subject.equals("ECE 101")){
            databaseReference_t1_user = FirebaseDatabase.getInstance().getReference("UserNameTeacher_1");
            databaseReference_t1_pass = FirebaseDatabase.getInstance().getReference("PasswordTeacher_1");
        //}
        //else if(subject.equals("ECE 102")){
            databaseReference_t2_user = FirebaseDatabase.getInstance().getReference("UserNameTeacher_2");
            databaseReference_t2_pass = FirebaseDatabase.getInstance().getReference("PasswordTeacher_2");
        //}



        editText = (EditText)findViewById(R.id.teacherPasswordID);
        editTextUserName = (EditText)findViewById(R.id.teacherUserNameID);
        editText.setText("");
        editTextUserName.setText("");
        databaseReference_t1_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                value_t1_user = dataSnapshot.getValue(String.class);

                //Toast.makeText(getApplicationContext(), "Password: " + value, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getApplicationContext(), "Failed to read value. " + error.toException(), Toast.LENGTH_SHORT).show();
            }
        });
        databaseReference_t1_pass.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                value_t1_pass = dataSnapshot.getValue(String.class);

                //Toast.makeText(getApplicationContext(), "Password: " + value, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getApplicationContext(), "Failed to read value. " + error.toException(), Toast.LENGTH_SHORT).show();
            }
        });
        databaseReference_t2_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                value_t2_user = dataSnapshot.getValue(String.class);

                //Toast.makeText(getApplicationContext(), "Password: " + value, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getApplicationContext(), "Failed to read value. " + error.toException(), Toast.LENGTH_SHORT).show();
            }
        });
        databaseReference_t2_pass.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                value_t2_pass = dataSnapshot.getValue(String.class);

                //Toast.makeText(getApplicationContext(), "Password: " + value, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getApplicationContext(), "Failed to read value. " + error.toException(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void LogIn(View view) {
        String pass = editText.getText().toString().trim();
        String user_name = editTextUserName.getText().toString().trim();

        if(user_name.equals(value_t1_user)&&pass.equals(value_t1_pass)&&subject.equals("ECE 425")){
            Intent intent = new Intent(TeacherLogin.this,Course_1_Activity.class);
            startActivity(intent);

        }
        else if(user_name.equals(value_t1_user)&&pass.equals(value_t1_pass)&&subject.equals("ECE 437")){
            Intent intent = new Intent(TeacherLogin.this,Course_2_Activity.class);
            startActivity(intent);

        }

        else if(user_name.equals(value_t2_user)&&pass.equals(value_t2_pass)&&subject.equals("ECE 471")){
            Intent intent = new Intent(TeacherLogin.this,Course_3_Activity.class);
            startActivity(intent);

        }
        else if(user_name.equals(value_t2_user)&&pass.equals(value_t2_pass)&&subject.equals("ECE 475")){
            Intent intent = new Intent(TeacherLogin.this,Course_4_Activity.class);
            startActivity(intent);

        }
        else{
            if(user_name.isEmpty()){
                Toast.makeText(TeacherLogin.this,"Please Enter Username", Toast.LENGTH_SHORT).show();
            }
            else if(pass.isEmpty()){
                Toast.makeText(TeacherLogin.this,"Please Enter Password", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(TeacherLogin.this,"Incorrect Username or Password", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStart() {
        editText.setText("");
        editTextUserName.setText("");
        super.onStart();
    }
}
