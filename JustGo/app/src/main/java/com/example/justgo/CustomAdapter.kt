package com.example.justgo

import android.content.Context
import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.*
import com.example.justgo.Entitys.Activity


class CustomAdapter(context : Context, private val title: Array<String>, private val description: Array<String>,
                    private val imgid: Array<Int>, private val activities : MutableList<Activity>) :
    ArrayAdapter<String>(context, R.layout.custom_list, title)  {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {

        val inflater = LayoutInflater.from(context)
        val rowView = inflater.inflate(R.layout.custom_list, null, true)


        val titleText = rowView.findViewById(R.id.title) as TextView
        val imageView = rowView.findViewById(R.id.icon) as ImageView
        val subtitleText = rowView.findViewById(R.id.description) as TextView

        titleText.text = title[position]
        //imageView.setImageResource(imgid[position])
        imageView.setImageURI(activities.get(position)._image)
        subtitleText.text = description[position]

        return rowView
    }

}