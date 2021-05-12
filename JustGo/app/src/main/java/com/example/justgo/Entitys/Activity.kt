package com.example.justgo.Entitys

import java.io.Serializable



class Activity(activityName: String, description: String) : Serializable {
    var _acitivityName: String = activityName
    var _description: String = description

    override fun toString(): String {
        return "$_acitivityName\n\n$_description"
    }
}