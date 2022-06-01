package karan.example.eventtimekeeper
import RecyclerAdapter
import android.content.Context


import android.content.Intent
import android.icu.text.AlphabeticIndex
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.*
import java.nio.ByteOrder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MainActivity : AppCompatActivity() {

    var PROG_NAME:String = "ValueHere"


    companion object MainActivityData {

     //   var HeightParam:Int = 0;

        var order:Int = 0;
        var spinValue:Int = 0;
        var date:String = ""
        var time:String = ""
        val hourList = mutableListOf<Int>()
        var eventList:MutableList<Event> = mutableListOf<Event>()

    }
   // private var eventList:MutableList<Event> = mutableListOf<Event>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var totalHourSleepCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         totalHourSleepCount = findViewById<TextView>(R.id.totalHourNumber)

        //setting up recycler view


        viewManager = LinearLayoutManager(this)

        recyclerAdapter = RecyclerAdapter(eventList)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = recyclerAdapter

        }
        readToFile()
     //   totalHourSleepCount.setText(order)


        // shared pref code starts



        //shared pref code ends
    }

    //shared pref


    fun writeToFile() {
        try {
            // to save to file "test.json" in data/data/packagename/File
            val ofile: FileOutputStream = openFileOutput("test.json", MODE_PRIVATE)
            val osw = OutputStreamWriter(ofile)
            val jsonList = Gson().toJson(eventList)




            for (record in jsonList) {
                osw.write(record.toString())
            }

            osw.flush()
            osw.close()
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        }
    }

    fun readToFile() {
        try {
            // reading from data/data/packageName/File
            val fin: FileInputStream = openFileInput("test.json")
            val isr = InputStreamReader(fin)
            val inputBuffer = CharArray(100)
            var str: String? = ""
            var charRead: Int
            while (isr.read(inputBuffer).also { charRead = it } > 0) {
                val readString = String(inputBuffer, 0, charRead)
                str += readString
            }
            val listRecordsType = object : TypeToken<List<Event>>() {}.type
            eventList.clear()
            eventList.addAll(Gson().fromJson(str, listRecordsType))
       for ( e in eventList)
            {
                order = order + Integer.parseInt(e.hour.toString())
                hourList.add(Integer.parseInt(e.hour.toString()))

            }
//            var listis= eventList[0]
//            Log.d(PROG_NAME,listis.toString())


        } catch (ioe: IOException) {
            ioe.printStackTrace()
        }
    }



    //shared pref ends

    override fun onResume() {
        super.onResume()
      //  Log.d(PROG_NAME, eventName)
       // Log.d(PROG_NAME, date)
       // Log.d(PROG_NAME, eventList.toString())
        totalHourSleepCount.setText(order.toString())
        Log.d(PROG_NAME, order.toString())


       recyclerAdapter.notifyDataSetChanged()



//HEY ALERT IS HERE



        // ALER IS GONNA GONE







    }

    override fun onStop() {
        writeToFile()

        super.onStop()

    }

    fun onaddEvent(view: View) {

        val intent: Intent = Intent(this,saveEvent::class.java)
        startActivity(intent)
    }

    fun ondelete(view: View) {


        if(order >0) {
            var deleter: Int = hourList[hourList.size - 1]
            order = order?.minus(deleter)
            totalHourSleepCount.setText(order.toString())
            Log.d(PROG_NAME, order.toString())
            eventList.removeLast()
            hourList.removeLast()
            recyclerAdapter.notifyDataSetChanged()
        }
        else{
            order = 0

        }

    }

    fun onDeleteAll(view: View) {
        order = 0
        hourList.removeAll(hourList)
        totalHourSleepCount.setText(order.toString())
        eventList.removeAll(eventList)
        recyclerAdapter.notifyDataSetChanged()




    }

    fun onSettingClick(view: View) {

        val intent: Intent = Intent(this,setting::class.java)
        startActivity(intent)

    }

}