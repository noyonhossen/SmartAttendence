package com.tanvirnoor.smartattendence;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

public class StudentActivity2 extends AppCompatActivity implements View.OnClickListener{

    DatabaseReference databaseReference;
    DatabaseReference databaseReferenceAttendence;
    FusedLocationProviderClient fusedLocationProviderClient;
    Location currentLocation;
    private static final int REQUEST_CODE = 101;
    String latitude_txt,longitude_txt;
    Button button;
    String value;
    EditText editText;

    SurfaceView surfaceView;
    CameraSource cameraSource;
    TextView textView;
    BarcodeDetector barcodeDetector;
    String id;
    String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student2);

        setTitle("Courses 2");

        button = (Button)findViewById(R.id.buttonSendID2);
        editText=(EditText)findViewById(R.id.edittextPasswordIDStudent2);
        button.setOnClickListener(this);

        //barcode start
        surfaceView = (SurfaceView)findViewById(R.id.camerapreview2);
        textView = (TextView)findViewById(R.id.textview2);
        barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();
        cameraSource = new CameraSource.Builder(this,barcodeDetector).setRequestedPreviewSize(640,480).build();
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                    return;
                }
                try {
                    cameraSource.start(surfaceHolder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                cameraSource.stop();
            }
        });
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrCodes = detections.getDetectedItems();

                if(qrCodes.size() != 0){
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(1000);
                            id = qrCodes.valueAt(0).displayValue;
                            textView.setText(id);
                        }
                    });
                }
            }
        });
        //barcode end

        databaseReference = FirebaseDatabase.getInstance().getReference("Password2");
        databaseReferenceAttendence = FirebaseDatabase.getInstance().getReference("Attendence2");

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                value = dataSnapshot.getValue(String.class);

                //Toast.makeText(getApplicationContext(), "Password: " + value, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getApplicationContext(), "Failed to read value. " + error.toException(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null){
                    currentLocation=location;
                    //Toast.makeText(getApplicationContext(),currentLocation.getLatitude()+" "+currentLocation.getLongitude(),Toast.LENGTH_SHORT).show();
                    latitude_txt = ""+currentLocation.getLatitude() ;
                    longitude_txt = ""+currentLocation.getLongitude();
                }
            }
        });
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case  REQUEST_CODE :
                if(grantResults.length>0 && grantResults[0] ==PackageManager.PERMISSION_GRANTED){
                    fetchLastLocation();
                }
                break;
        }
    }
    @Override
    protected void onStart() {
        fetchLastLocation();
        super.onStart();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.buttonSendID2){
            String pass = editText.getText().toString();

            if(pass.equals("")){
                Toast.makeText(StudentActivity2.this,"Enter Password",Toast.LENGTH_SHORT).show();
            }
            else if(id==null){
                Toast.makeText(StudentActivity2.this,"Scan your ID",Toast.LENGTH_SHORT).show();
            }
            else if(pass.equals(value)){
                String key = databaseReferenceAttendence.push().getKey();

                TimePicker timePicker = new TimePicker(this);


                int hour;
                int minute;
                hour = timePicker.getCurrentHour();
                minute = timePicker.getCurrentMinute();
                String time = hour+":"+minute;

                DatePicker datePicker = new DatePicker(this);
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                month++;
                int year =  datePicker.getYear();
                String date = day+"/"+month+"/"+year;

                StudentInfo data = new StudentInfo(id,Name,date,time,"Y",latitude_txt,longitude_txt);
                /*databaseReferenceLatitude.setValue(""+latitude_txt);
                databaseReferenceLongitude.setValue(""+longitude_txt);
                databaseReferenceTime.setValue("12:12");
                databaseReferenceID.setValue(id);
                databaseReferenceAttendence.setValue("Yes");*/

                databaseReferenceAttendence.child(key).setValue(data);

                /*String method = "login";
                BackgroundTask backgroundTask = new BackgroundTask(this);
                backgroundTask.execute(method,"144",latitude_txt);*/

                button.setClickable(false);

                Toast.makeText(StudentActivity2.this,"Attendence Send Successfully",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(StudentActivity2.this,"Incorrect Password",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
