package com.example.mvvweather.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.example.mvvweather.R

/**
 * Created by Your name on 2019-10-23.
 */
class AutocompleteAdapter(private val mContext: Context,
                          private val layoutRes: Int,
                          private val suggestions: ArrayList<String>
):
    ArrayAdapter<String>(mContext, layoutRes, suggestions), Filterable {

    private val TAG = AutocompleteAdapter::class.java.simpleName

    private var _suggestions: ArrayList<String> = suggestions

    override fun getItem(position: Int): String? {
        Log.i(TAG, "Added following item to list, ${suggestions[position]}")
        return suggestions[position]
    }

    override fun getCount(): Int {
        return suggestions.size
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater
            .from(context)
            .inflate(layoutRes, parent, false)

        val autoCompleteText = view.findViewById<TextView>(R.id.autoCompleteText)
        autoCompleteText.text = suggestions[position]

        return view
    }

    override fun getFilter(): Filter {
      return object: Filter() {
          override fun performFiltering(constraint: CharSequence?): FilterResults {
              val query = constraint?.toString()?.toLowerCase()
              val filterResults = FilterResults()

              if(query == null || query.isEmpty()) {
                  filterResults.values = suggestions
              } else {
                  filterResults.values = suggestions.filter { autoCompleteItem ->
                      autoCompleteItem.contains(constraint)
                  }
              }

              Log.i(TAG, "Filtered results on performingFilter(): ${filterResults.values}")

              return filterResults
          }

          override fun publishResults(constraint: CharSequence?, results: FilterResults) {
              Log.i(TAG, "Results to show to user on publishResults: ${results.values}")
              _suggestions = results.values as ArrayList<String>
              notifyDataSetChanged()
          }
      }
    }
}