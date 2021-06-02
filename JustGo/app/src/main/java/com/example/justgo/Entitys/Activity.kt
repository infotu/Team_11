package com.example.justgo.Entitys

import android.net.Uri
import java.io.Serializable



class Activity(activityName: String, description: String, image : Uri?) : Serializable {
    var _acitivityName: String = activityName
    var _description: String = description
    var _image : Uri? = image

    override fun toString(): String {
        return "$_acitivityName\n\n$_description"
    }
}