package com.example.eskomsevc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    Spinner spnDays;
    ListView lstTimes;
    String TAG = "Firebase";
    ArrayAdapter<String> adapter;

    Spinner sItems;
    Spinner sDays;
    DataSnapshot dataSnapshot;

    Button btnSearch;







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
        spnDays = (Spinner) findViewById(R.id.spnDay);
        lstTimes = (ListView) findViewById(R.id.lstTimes);
        btnSearch = (Button) findViewById(R.id.btnSearch);

        adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, loadshedding);
        lstTimes.setAdapter(adapter);

        loadSpinner();




    }

    public void search(View v)
    {
        listenForItemSelected(sItems, sDays, dataSnapshot);
    }



    //region loadSpinner method
    //Populating the spinner with the locations
    public void loadSpinner()
    {
        List<String> spinnerArrayLocation =  new ArrayList<String>();
        List<String> spinnerArrayDays =  new ArrayList<String>();

        spinnerArrayLocation.add("Home");
        spinnerArrayLocation.add("Varsity");
        spinnerArrayLocation.add("Work");
        spinnerArrayLocation.add("Gym");

        spinnerArrayDays.add("Monday");
        spinnerArrayDays.add("Tuesday");
        spinnerArrayDays.add("Wednesday");
        spinnerArrayDays.add("Thursday");
        spinnerArrayDays.add("Friday");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, spinnerArrayLocation);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.spnLocation);
        sItems.setAdapter(adapter);

        ArrayAdapter<String> adapterDays = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, spinnerArrayDays);
        adapterDays.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sDays = (Spinner) findViewById(R.id.spnDay);
        sDays.setAdapter(adapterDays);


    }
    //endregion

    public void listenForItemSelected(Spinner sItems, Spinner sDays, DataSnapshot dataSnapshot)
    {


        //Home
        //Monday to Friday
        if (spnLocation.getSelectedItem().toString().equals("Home") && spnDays.getSelectedItem().toString().equals("Monday"))
        {
            Log.d(TAG, "Home + Monday");
            pullInfo("Home", "Monday");
        }
        else if (spnLocation.getSelectedItem().toString().equals("Home") && spnDays.getSelectedItem().toString().equals("Tuesday"))
        {
            Log.d(TAG, "Home + Tuesday");
            pullInfo("Home", "Tuesday");
        }
        else if (spnLocation.getSelectedItem().toString().equals("Home") && spnDays.getSelectedItem().toString().equals("Wednesday"))
        {
            Log.d(TAG, "Home + Wednesday");
            pullInfo("Home", "Wednesday");
        }
        else if (spnLocation.getSelectedItem().toString().equals("Home") && spnDays.getSelectedItem().toString().equals("Thursday"))
        {
            Log.d(TAG, "Home + Thursday");
            pullInfo("Home", "Thursday");
        }
        else if (spnLocation.getSelectedItem().toString().equals("Home") && spnDays.getSelectedItem().toString().equals("Friday"))
        {
            Log.d(TAG, "Home + Friday");
            pullInfo("Home", "Friday");
        }
        //VC
        //Monday to Friday
        else if (spnLocation.getSelectedItem().toString().equals("Varsity") && spnDays.getSelectedItem().toString().equals("Monday"))
        {
            Log.d(TAG, "Varsity + Monday");
            pullInfo("Varsity", "Monday");
        }
        else if (spnLocation.getSelectedItem().toString().equals("Varsity") && spnDays.getSelectedItem().toString().equals("Tuesday"))
        {
            Log.d(TAG, "Varsity + Tuesday");
            pullInfo("Varsity", "Tuesday");
        }
        else if (spnLocation.getSelectedItem().toString().equals("Varsity") && spnDays.getSelectedItem().toString().equals("Wednesday"))
        {
            Log.d(TAG, "Varsity + Wednesday");
            pullInfo("Varsity", "Wednesday");
        }
        else if (spnLocation.getSelectedItem().toString().equals("Varsity") && spnDays.getSelectedItem().toString().equals("Thursday"))
        {
            Log.d(TAG, "Varsity + Thursday");
            pullInfo("Varsity", "Thursday");
        }
        else if (spnLocation.getSelectedItem().toString().equals("Varsity") && spnDays.getSelectedItem().toString().equals("Friday"))
        {
            Log.d(TAG, "Varsity + Friday");
            pullInfo("Varsity", "Friday");
        }
        //Work
        //Monday to Friday
        else if (spnLocation.getSelectedItem().toString().equals("Work") && spnDays.getSelectedItem().toString().equals("Monday"))
        {
            Log.d(TAG, "Work + Monday");
            pullInfo("Work", "Monday");
        }
        else if (spnLocation.getSelectedItem().toString().equals("Work") && spnDays.getSelectedItem().toString().equals("Tuesday"))
        {
            Log.d(TAG, "Work + Tuesday");
            pullInfo("Work", "Tuesday");
        }
        else if (spnLocation.getSelectedItem().toString().equals("Work") && spnDays.getSelectedItem().toString().equals("Wednesday"))
        {
            Log.d(TAG, "Work + Wednesday");
            pullInfo("Work", "Wednesday");
        }
        else if (spnLocation.getSelectedItem().toString().equals("Work") && spnDays.getSelectedItem().toString().equals("Thursday"))
        {
            Log.d(TAG, "Work + Thursday");
            pullInfo("Work", "Thursday");
        }
        else if (spnLocation.getSelectedItem().toString().equals("Work") && spnDays.getSelectedItem().toString().equals("Friday"))
        {
            Log.d(TAG, "Work + Friday");
            pullInfo("Work", "Friday");
        }
        //Gym
        //Monday to Friday
        else if (spnLocation.getSelectedItem().toString().equals("Gym") && spnDays.getSelectedItem().toString().equals("Monday"))
        {
            Log.d(TAG, "Gym + Monday");
            pullInfo("Gym", "Monday");
        }
        else if (spnLocation.getSelectedItem().toString().equals("Gym") && spnDays.getSelectedItem().toString().equals("Tuesday"))
        {
            Log.d(TAG, "Gym + Tuesday");
            pullInfo("Gym", "Tuesday");
        }
        else if (spnLocation.getSelectedItem().toString().equals("Gym") && spnDays.getSelectedItem().toString().equals("Wednesday"))
        {
            Log.d(TAG, "Gym + Wednesday");
            pullInfo("Gym", "Wednesday");
        }
        else if (spnLocation.getSelectedItem().toString().equals("Gym") && spnDays.getSelectedItem().toString().equals("Thursday"))
        {
            Log.d(TAG, "Gym + Thursday");
            pullInfo("Gym", "Thursday");
        }
        else if (spnLocation.getSelectedItem().toString().equals("Gym") && spnDays.getSelectedItem().toString().equals("Friday"))
        {
            Log.d(TAG, "Gym + Friday");
            pullInfo("Gym", "Friday");
        }
    }

    //region PullHomeInfo method
    public void pullInfo(String location, String day)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("EskomSeVc/" + location);

        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                String value1 = dataSnapshot.child(day + "/Stage1").getValue().toString();
                String value2 = dataSnapshot.child(day + "/Stage2").getValue().toString();
                String value3 = dataSnapshot.child(day + "/Stage3").getValue().toString();
                String value4 = dataSnapshot.child(day + "/Stage4").getValue().toString();

                loadshedding.clear();
                loadshedding.add(value1);
                loadshedding.add(value2);
                loadshedding.add(value3);
                loadshedding.add(value4);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });



    }
    //endregion

    //region pullVCInfo method
    public void pullVCInfo()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("EskomSeVc/VC");

        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String VCMondayStage1 = dataSnapshot.child("Monday/Stage1").getValue().toString();
                String VCMondayStage2 = dataSnapshot.child("Monday/Stage2").getValue().toString();
                String VCMondayStage3 = dataSnapshot.child("Monday/Stage3").getValue().toString();
                String VCMondayStage4 = dataSnapshot.child("Monday/Stage4").getValue().toString();

                loadshedding.clear();
                loadshedding.add(VCMondayStage1);
                loadshedding.add(VCMondayStage2);
                loadshedding.add(VCMondayStage3);
                loadshedding.add(VCMondayStage4);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
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