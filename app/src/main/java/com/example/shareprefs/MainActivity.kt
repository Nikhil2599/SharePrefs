package com.example.shareprefs

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    lateinit var etPaswd: EditText
    lateinit var etName: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etName = findViewById(R.id.etName)
        etPaswd = findViewById(R.id.etPassword)
    }
    override fun onResume() {
        super.onResume()
        Log.i(TAG,"resuming-restore state")
        restoreData()

    }

    private fun restoreData() {
        //get the file and open it
        var sharedprefs = getSharedPreferences("preferencesSync", MODE_PRIVATE)
        //read from the file
        var name = sharedprefs.getString("namekey","")
        var pass = sharedprefs.getString("passkey","")
        //restore the data into edittexts

        etName.setText(name)
        etPaswd.setText(pass)
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG,"pausing-save state")
        saveData()
    }

    private fun saveData() {
        //get the data from the edittexts
        var name = etName.text.toString()
        var pass = etPaswd.text.toString()
        //create a file
        var sharedprefs = getSharedPreferences("preferencesSync", MODE_PRIVATE)
        //open the file in edit mode
        var editor = sharedprefs.edit()
        //write to the file
        editor.putString("namekey",name)
        editor.putString("passkey",pass)
        //save the file
        editor.apply()
    }

}