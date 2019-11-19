package com.tanvirnoor.smartattendence;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AllStudents extends AppCompatActivity {

    ListView listView;
    String[] listValue = new String[5];
    String value;
    String Course;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_students);

        setTitle("Students");


        listValue = getResources().getStringArray(R.array.id_of_students);

        listView = (ListView)findViewById(R.id.listViewAllStudentsId);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            value = bundle.getString("course");
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.sample_layout_all_students, R.id.allStudentsID,listValue );

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String id_ = listValue[i];
                //Toast.makeText(AllStudents.this,"ID: "+id_,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AllStudents.this,StudentDetailsOfEachID.class);
                intent.putExtra("ID",id_) ;
                intent.putExtra("course",value);
                startActivity(intent);
            }
        });
    }

}
