package karan.example.eventtimekeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class setting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
       var name:TextView = findViewById(R.id.nameOfCreater)
        var desc:TextView = findViewById(R.id.descriptionOfApp)

        var karan = "Karan Patel"
        var description = "This is the Event tracker application of baby sleep where parents has to just " +
                "enter the number of hours baby slept once baby wake up.Moreover, They can get records of " +
                "Total number of baby sleep hour. if user enter data mistakenly then can delete the data too." +
                "Even they can track the baby sleeping event by time and date too."

        name.setText(karan)
        desc.setText(description)

    }
}