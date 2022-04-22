package com.example.mypetsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.app.NavUtils
import com.example.mypetsapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    
    private var binding : ActivitySecondBinding? = null
    private lateinit var mGenderSpinner : Spinner
    private var mGender = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setupSpinner()
    }


    private fun setupSpinner(){

        mGenderSpinner = binding?.spinnerGender!!

        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        val genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
            R.array.array_gender_options, android.R.layout.simple_spinner_item)

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        //Apply the adapter to the spinner
        mGenderSpinner.adapter = genderSpinnerAdapter

        mGenderSpinner.onItemSelectedListener = object :

            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val selection = parent?.getItemAtPosition(position)
                if (!TextUtils.isEmpty(selection?.toString())) {
                    mGender = when (selection) {
                        getString(R.string.gender_male) -> {
                            1 // Male
                        }
                        getString(R.string.gender_female) -> {
                            2 // Female
                        }
                        else -> {
                            0 // Unknown
                        }
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                mGender = 0
            }

        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_save -> return true
            R.id.action_delete -> return true
            android.R.id.home ->{ NavUtils.navigateUpFromSameTask(this)
                return true}
        }
        return super.onOptionsItemSelected(item)
    }


}