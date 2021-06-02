package com.example.justgo

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.justgo.Entitys.FoodType
import com.example.justgo.Entitys.PictureVideoType
import com.example.justgo.Entitys.Trip
import com.example.justgo.Logic.TripManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.File
import java.io.FileNotFoundException


class AddNewActivityActivity : AppCompatActivity() {

    private lateinit var trip : Trip
    private lateinit var addButton: FloatingActionButton
    private var openGallery: Int = 100
    private var imageUri : Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_activity)

        trip = intent.getSerializableExtra("trip") as Trip
        addButton = findViewById(R.id.picture_button)

        addButton.setOnClickListener {
            openGalleryForImage()
        }

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

                trip.activities.addActivity(name.text.toString(), description.text.toString(), imageUri)

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

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "*/*"
        startActivityForResult(intent, openGallery)
    }

    private fun getDestPath() : String{
        var path = "pictures_videos/" + trip.nameofTrip + "/"
        path += ".jpg"

        return path
    }

    fun getPath(uri: Uri?, activity: Activity): String {
        var cursor: Cursor? = null
        try {
            val projection = arrayOf(MediaStore.MediaColumns.DATA)
            cursor = activity.contentResolver.query(uri!!, projection, null, null, null)
            if (cursor!!.moveToFirst()) {
                val column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
                return cursor.getString(column_index)
            }
        } catch (e: Exception) {
        } finally {
            cursor!!.close()
        }
        return ""
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == openGallery){
            if(data?.data != null)
            {

                val selectedImageUri = data.data!!
                var imgPath = getPath(selectedImageUri, this);
                val url = data.data.toString();

                val isPicture = !imgPath.endsWith(".mp4")
                val file: File = File("", imgPath)
                val destFile = File(this.filesDir, getDestPath())

                if (url.startsWith("content://com.google.android.apps.photos.content")){
                    // Photo is only available google photos cloud

                    try {
                        val inputStream = this.contentResolver.openInputStream(selectedImageUri);
                        if (inputStream != null) {
                            this.contentResolver.openOutputStream(destFile.toUri()).use { fileOut ->
                                inputStream.copyTo(fileOut!!)
                            }
                        }
                    }
                    catch (e: FileNotFoundException) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                else{
                    file.copyTo(destFile, overwrite = true)
                }
                imageUri = destFile.toUri()
                //pictureVideoInformation.addPictureVideo(destFile.toUri(), selectedType)

                //currentPictureVideoList= pictureVideoInformation.getPicturesVideosList(selectedType)
                //(choosenGridView.adapter as PictureVideoAdapter).notifyDataSetChanged()
                //choosenGridView.invalidateViews()
            }
        }
    }

}