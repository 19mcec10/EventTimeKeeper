import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import karan.example.eventtimekeeper.Event
import karan.example.eventtimekeeper.R


class RecyclerAdapter(private val dataSet: MutableList<Event>):

    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val date: TextView
        val time: TextView
        //   val textViewName: TextView
        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.event_name_text)
            date = view.findViewById(R.id.event_date)
            time = view.findViewById(R.id.event_time)

            //  textViewName = view.findViewById(R.id.textViewRecyclerItemName)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_item_layout, viewGroup, false)

        // add the 3 lines of code below to show 5 recycler items in the activity at a time
        val lp = view.getLayoutParams()
        lp.height = viewGroup.measuredHeight/5
        view.setLayoutParams(lp)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        //   viewHolder.textView.text = dataSet[position].order.toString()
        viewHolder.textView.text = dataSet[position].hour.toString()
        viewHolder.date.text = dataSet[position].date
        viewHolder.time.text = dataSet[position].time

    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}