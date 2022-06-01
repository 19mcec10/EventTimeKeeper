package karan.example.eventtimekeeper


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import androidx.annotation.RequiresApi

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime



class saveEvent : AppCompatActivity() {
    lateinit var EventText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_event)


    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onSaveEvent(view: View) {

        var spinner:Spinner = findViewById(R.id.spinner)

        MainActivity.spinValue = Integer.parseInt(spinner.selectedItem.toString())

        MainActivity.hourList.add(MainActivity.spinValue)
        MainActivity.order = MainActivity.order + MainActivity.spinValue

        MainActivity.date = LocalDate.now().toString()
        MainActivity.time = LocalTime.now().toString()
        MainActivity.eventList.add(Event(
            MainActivity.spinValue,
            MainActivity.date,
            MainActivity.time
        ))

       // alert box ix here


        val alertDialog = AlertDialog.Builder(this)
            //set icon
            .setIcon(android.R.drawable.ic_dialog_alert)
            //set title
            .setTitle("Alert Box")
            //set message
            .setMessage("Your Update Has been saved")
            //set positive button
            .setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, i ->
                //set what would happen when positive button is clicked
                finish()
            })
            //set negative button

            .show()


        // alert box ends here

       // finish()
    }
}