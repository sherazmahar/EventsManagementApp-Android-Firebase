package com.example.eventsmanagment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView welcome;
    TextView showType, tv2;

    Button add,show;
    EditText n,entertype;
    DatabaseReference rootref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        welcome = (TextView) findViewById(R.id.welcome);
        add = (Button) findViewById(R.id.add);
        show = (Button) findViewById(R.id.show);
        n = (EditText) findViewById(R.id.n);
        entertype = (EditText) findViewById(R.id.Type);
        rootref= FirebaseDatabase.getInstance().getReference();

        add.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {

                Intent myIntent = new Intent(MainActivity.this,AddEvents.class);

                startActivity(myIntent);
            }
        });


        showType = (TextView) findViewById(R.id.showType);

        tv2 = (TextView) findViewById(R.id.name);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a =entertype.getText().toString();
//                String b = tv2.getText().toString();
                // Intent intent = new Intent(MainActivity.this, new ShowEvents(a,b).getClass());
                Intent intent = new Intent(MainActivity.this, new ShowEvents(a).getClass());
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
