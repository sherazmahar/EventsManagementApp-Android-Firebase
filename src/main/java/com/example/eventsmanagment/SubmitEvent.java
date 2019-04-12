package com.example.eventsmanagment;

import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SubmitEvent extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_event);
        Intent i = getIntent();
        String d = i.getDataString();

//        String date = i.getStringExtra("date");
//        String duration = i.getStringExtra("duration");
//
//        System.out.print("GOT MESSAGE  "+ d + " date " + date + "  duration  " + duration);
//        tv = (TextView) findViewById(R.id.tv);
//        tv.setText("Your Event has been set for " + date + " FOR " + duration);
//

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                System.out.print("value  is " + value);
                //Log.d(TAG, "Value is: " + value);
            }



            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
               // Log.w(TAG, "Failed to read value.", error.toException());
                System.out.println ("Failed t oread the value ");
            }
        });



    }
}
