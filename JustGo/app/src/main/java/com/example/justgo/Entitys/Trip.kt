package com.example.justgo.Entitys

import android.net.Uri
import java.io.Serializable

class Trip(name:String,tripType: TripType) : Serializable {
    private var tripID:Int = 0
    var tripInformations: ArrayList<TripInformation> = ArrayList()
    var possibleFields = mutableListOf<String>("Dates", "Locations", "Pictures and Videos", "Transportation", "Accommodation", "Activities", "Foods", "Co-Travellers")
    var nameofTrip:String = name
    var tripType:TripType = tripType
    var foods: ArrayList<Food> = ArrayList()
    lateinit var activities: TripActivity

    var picturesAndVideosBefore: ArrayList<Uri> = ArrayList()
    var picturesAndVideosFrom: ArrayList<Uri> = ArrayList()
    var destinations:ArrayList<Destination> = ArrayList()

    fun addFood(foodName: String, location: String, foodType: FoodType){
        foods.add(Food(foodName, location, foodType))
    }


    fun getFood(foodType: FoodType): ArrayList<Food> {
        var returnList: ArrayList<Food> = ArrayList()
        println("testtesttest")
        foods.forEach {
            println(it.toString())
            if (it._foodType == foodType) {
                returnList.add(it)
            }
        }
        return returnList
    }
    fun addPictureVideo(pictureOrVideo: Uri, type: PictureVideoType){
        if(type == PictureVideoType.taken_before_trip)
        {
            picturesAndVideosBefore.add(pictureOrVideo)
        }
        else
        {
            picturesAndVideosFrom.add(pictureOrVideo)
        }
    }

    fun getPicturesVideosList(beforeOrFromType: PictureVideoType): ArrayList<Uri>{
        var returnList: ArrayList<Uri> = ArrayList()
        if(beforeOrFromType == PictureVideoType.taken_before_trip)
        {
            returnList = picturesAndVideosBefore
        }
        else
        {
            returnList = picturesAndVideosFrom
        }
        return returnList
    }

    fun addTripInformation(tripInformation:TripInformation){
        this.tripInformations.add(tripInformation)
    }

    fun getTripInformationbyName(name:String):TripInformation?{
        tripInformations.forEach {
            if(it.name == name){
                return it
            }
        }
        return null
    }

    fun getTripInformationLNameist(): ArrayList<String>{
        var tripInfoNames: ArrayList<String> = ArrayList()
        tripInformations.forEach{
            tripInfoNames.add(it.name)
        }
        return tripInfoNames
    }
/*
    override fun toString(): String {
        return "$nameofTrip"
    }*/

    fun addDestination(destination: Destination) {
        /*var bool: Boolean = false
        DestinationManager.map.keys.forEach {
            if (it.equals(DestinationManager.actualOpenTrip)) {
                bool = true
            }
        }
        if (bool) {
            DestinationManager.map[DestinationManager.actualOpenTrip]?.add(destination)
        } else {
            val list: ArrayList<Destination> = ArrayList()
            list.add(destination)
            DestinationManager.map.put(DestinationManager.actualOpenTrip, list)
        }*/
        destinations.add(destination)
    }

    fun getDestinationsForActualTrip(): ArrayList<Destination> {
        return destinations
    }
    fun clearDestinations(){
        destinations.clear()
    }
    fun addID(ID:Int){
        tripID=ID
    }
    fun getID():Int{
        return tripID
    }
}