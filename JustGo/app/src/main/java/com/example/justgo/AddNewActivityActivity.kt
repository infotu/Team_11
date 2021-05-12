package com.example.justgo

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.justgo.Entitys.FoodType
import com.example.justgo.Entitys.Trip
import com.example.justgo.Logic.TripManager
import com.google.android.material.floatingactionbutton.FloatingActionButton


class AddNewActivityActivity : AppCompatActivity() {

    private lateinit var trip : Trip

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_activity)

        trip = intent.getSerializableExtra("trip") as Trip

        val discard : FloatingActionButton
        discard=findViewById(R.id.discardActivity_floatActionButton)
        discard.setOnClickListener {
            val intent = Intent(this, ActivitiesActivity::class.java)
            intent.putExtra("trip", trip)
            startActivity(intent)
        }
        val save : FloatingActionButton
        save=findViewById(R.id.saveActivity_floatActionButton)
        save.setOnClickListener {
            val name: EditText
            name=findViewById(R.id.activityName_EditText)
            var description: EditText
            description = findViewById(R.id.activityDescription_EditText)

            if(!(name.text.toString().equals("")) && !(description.text.toString().equals(""))) {

                trip.addActivity(name.text.toString(), description.text.toString())

                TripManager.replaceTrip(
                    TripManager.getTripbyName(trip.nameofTrip).first(),
                    trip
                )

                val intent = Intent(this, ActivitiesActivity::class.java)
                intent.putExtra("trip", trip)
                startActivity(intent)

            }
        }
    }
}