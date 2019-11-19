package com.tanvirnoor.smartattendence;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CustomAdapter2 extends ArrayAdapter<StudentInfo> {
    private Activity context;
    private List<StudentInfo> studentList;

    public CustomAdapter2(Activity context, List<StudentInfo> studentList) {
        super(context, R.layout.sample_layout, studentList);
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_layout_2,null,true);
        StudentInfo student = studentList.get(position);

        TextView id = view.findViewById(R.id.idID2);
        TextView date = view.findViewById(R.id.dateID2);
        TextView time= view.findViewById(R.id.timeID2);
        TextView latitude = view.findViewById(R.id.latID2);
        TextView longitude = view.findViewById(R.id.lonID2);

        id.setText(student.getId());
        date.setText(student.getDate());
        time.setText(student.getTime());
        latitude.setText(student.getLatitude_txt());
        longitude.setText(student.getLongitude_txt());

        return view;
    }
}
