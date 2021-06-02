package com.example.justgo

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.justgo.Entitys.*
import com.example.justgo.singleTrip.ActivitySingleTrip
import com.google.android.material.floatingactionbutton.FloatingActionButton



class ActivitiesActivity : AppCompatActivity() {


    private lateinit var backbutton : FloatingActionButton
    private lateinit var addActivityButton : FloatingActionButton
    private lateinit var activityListView : ListView
    private lateinit var trip : Trip

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activities)

        trip = intent.getSerializableExtra("trip") as Trip
        activityListView = findViewById(R.id.activity_listview)
        val activities = trip.activities.activities

        val titles : Array<String> = emptyArray()
        val description : Array<String> = emptyArray()
        val imageId : Array<Int> = emptyArray()

        var index : Int = 0
        for (i in activities) {
            titles[index] = i._acitivityName
            description[index] = i._description
            imageId[index] = i._image.toString().toInt()
        }
        val myListAdapter = CustomAdapter(this, titles, description, imageId, activities)
        activityListView.adapter = myListAdapter
        /*
        val arrayAdapter: ArrayAdapter<Activity>
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, activities)
        activityListView.adapter = arrayAdapter
        */

        addActivityButton = findViewById(R.id.add_activity_button)
        addActivityButton.setOnClickListener {
            val intent = Intent(this, AddNewActivityActivity::class.java)
            intent.putExtra("trip", trip)
            startActivity(intent)
        }

        backbutton = findViewById(R.id.activity_back_button)
        backbutton.setOnClickListener {
            val intent = Intent(this, ActivitySingleTrip::class.java)
            intent.putExtra("trip", trip)
            startActivity(intent)
        }
    }
}
