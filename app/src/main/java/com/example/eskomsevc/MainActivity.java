package com.example.eskomsevc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{
    // creating a variable for
    // our Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our
    // Database Reference for Firebase.
    DatabaseReference databaseReference;

    private ArrayList<String> loadshedding = new ArrayList<>();
    Spinner spnLocation;
    ListView lstTimes;
    String TAG = "Firebase";
    ArrayAdapter<String> adapter;

    Spinner sItems;
    DataSnapshot dataSnapshot;

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState)
    {
        // below line is used to get the instance
        // of our Firebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get
        // reference for our database.
        databaseReference = firebaseDatabase.getReference("EskomSeVc");
        DatabaseReference zone1Ref = databaseReference.child("Gym");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spnLocation = (Spinner) findViewById(R.id.spnLocation);
        lstTimes = (ListView) findViewById(R.id.lstTimes);

        adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, loadshedding);
        lstTimes.setAdapter(adapter);

        listenForItemSelected(sItems, dataSnapshot);

        loadSpinner();
    }

    //region loadSpinner method
    //Populating the spinner with the locations
    public void loadSpinner()
    {
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Home");
        spinnerArray.add("VC");
        spinnerArray.add("Work");
        spinnerArray.add("Gym");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.spnLocation);
        sItems.setAdapter(adapter);
    }
    //endregion

    public void listenForItemSelected(Spinner sItems, DataSnapshot dataSnapshot)
    {
        Log.d(TAG, "Program is running");


        spnLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                Log.d(TAG, (String) parentView.getItemAtPosition(position));

                if (parentView.getItemAtPosition(position) == "Home")
                {
                    Log.d(TAG, "Home method must be pulled");
                    pullHomeInfo();
                }
                else if (parentView.getItemAtPosition(position) == "VC")
                {
                    Log.d(TAG, "VC method must be pulled");
                    pullVCInfo();
                }
                else if (parentView.getItemAtPosition(position) == "Work")
                {
                    Log.d(TAG, "Work method must be pulled");
                    pullWorkInfo();
                }
                else if (parentView.getItemAtPosition(position) == "Gym")
                {
                    Log.d(TAG, "Gym method must be pulled");
                    pullGymInfo();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {
                // your code here
            }
        });
    }

    //region PullHomeInfo method
    public void pullHomeInfo()
    {
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("EskomSeVc");
        DatabaseReference zone1Ref = databaseReference.child("Home");
        // Read from the database
        zone1Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();

                String newMap = map.toString();
                newMap = newMap.replace("{", "");

                String newMap2 = newMap.toString();
                newMap2 = newMap2.replace("}", "");

                String newMap3 = newMap2.toString();
                newMap3 = newMap3.replace(",", "\n");

                String newMap4 = newMap3.toString();
                newMap4 = newMap4.replace("=", "\n");

                loadshedding.clear();
                // this line adds the data of your EditText and puts in your array
                loadshedding.add(newMap4);

                //String value = dataSnapshot.getValue(String.class);
                adapter.notifyDataSetChanged();
                Log.d(TAG, "Value is: " + map);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    //endregion

    //region pullVCInfo method
    public void pullVCInfo()
    {
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("EskomSeVc");
        DatabaseReference zone1Ref = databaseReference.child("Varsity");
        // Read from the database
        zone1Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();

                String newMap = map.toString();
                newMap = newMap.replace("{", "");

                String newMap2 = newMap.toString();
                newMap2 = newMap2.replace("}", "");

                String newMap3 = newMap2.toString();
                newMap3 = newMap3.replace(",", "\n");

                String newMap4 = newMap3.toString();
                newMap4 = newMap4.replace("=", "\n");

                loadshedding.clear();
                // this line adds the data of your EditText and puts in your array
                loadshedding.add(newMap4);
                //String value = dataSnapshot.getValue(String.class);
                adapter.notifyDataSetChanged();
                Log.d(TAG, "Value is: " + map);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    //endregion

    //region pullWorkInfo method
    public void pullWorkInfo()
    {
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("EskomSeVc");
        DatabaseReference zone1Ref = databaseReference.child("Work");
        // Read from the database
        zone1Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();

                String newMap = map.toString();
                newMap = newMap.replace("{", "");

                String newMap2 = newMap.toString();
                newMap2 = newMap2.replace("}", "");
                String newMap3 = newMap2.toString();

                newMap3 = newMap3.replace(",", "\n");

                String newMap4 = newMap3.toString();
                newMap4 = newMap4.replace("=", "\n");

                loadshedding.clear();
                // this line adds the data of your EditText and puts in your array
                loadshedding.add(newMap4);
                //String value = dataSnapshot.getValue(String.class);
                adapter.notifyDataSetChanged();
                Log.d(TAG, "Value is: " + map);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    //endregion

    //region pullGymInfo method
    public void pullGymInfo()
    {
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("EskomSeVc");
        DatabaseReference zone1Ref = databaseReference.child("Gym");
        // Read from the database
        zone1Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();

                String newMap = map.toString();
                newMap = newMap.replace("{", "");

                String newMap2 = newMap.toString();
                newMap2 = newMap2.replace("}", "");

                String newMap3 = newMap2.toString();
                newMap3 = newMap3.replace(",", "\n");

                String newMap4 = newMap3.toString();
                newMap4 = newMap4.replace("=", "\n");

                loadshedding.clear();
                // this line adds the data of your EditText and puts in your array
                loadshedding.add(newMap4);
                //String value = dataSnapshot.getValue(String.class);
                adapter.notifyDataSetChanged();
                Log.d(TAG, "Value is: " + map);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    ///endregion
}