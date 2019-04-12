package com.example.eventsmanagment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import junit.framework.Test;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShowEvents extends AppCompatActivity {

    String Type, name;
    TextView tv2;
    Button b;
    Spinner spinner;
    public static String event;
    public ShowEvents(String Item) {
        event = Item;

    }

    public void setEvent(String item)
    {
        event = item;
    }

    public String getEvent()
    {
        return event;
    }

    public ShowEvents() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_events);


        tv2 = (TextView) findViewById(R.id.name);

        // Spinner element
        spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(new custom());


        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Get a reference to our posts

        DatabaseReference ref = database.getInstance().getReference();

        final HashMap<String,String> ev = new HashMap<>();
// Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> categories = new ArrayList<String>();
                for (DataSnapshot t : dataSnapshot.getChildren()) {
                    String x = String.valueOf(t.getChildrenCount());
                   for(DataSnapshot tt : t.getChildren())
                   {
                        String q = "";
                        if (tt.getKey().toString().equals("Name")) {
                            q = tt.getValue().toString();
                            Toast.makeText(getApplicationContext(), "Name " + q, Toast.LENGTH_SHORT);
                            String s = q;
                            categories.add(s);
                        }
                    }
                    }
                set(categories);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        b = (Button) findViewById(R.id.details);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = getEvent();
                Toast.makeText(getApplicationContext(), "redirecting : " + s, Toast.LENGTH_LONG).show();
                System.out.print("redirecting   " + s);
                Intent i = new Intent(ShowEvents.this, EventDetails.class);
                i.putExtra("Event",s);
                startActivity(i);


            }
        });


    }

    public void set(List<String> categories) {

          // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }
}

class custom  implements AdapterView.OnItemSelectedListener
    {


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        ShowEvents ob = new ShowEvents(item);
        ob.setEvent(item);
        ShowEvents.event = item;
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }



}

