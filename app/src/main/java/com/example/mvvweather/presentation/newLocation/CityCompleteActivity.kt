package com.example.mvvweather.presentation.newLocation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvweather.R
import com.example.mvvweather.data.autocomplete.response.PlaceData
import kotlinx.android.synthetic.main.city_auto_complete_screen.*

class CityCompleteActivity: AppCompatActivity() {

    private val suggestions = ArrayList<String>()
    private lateinit var suggestionAdapter: ArrayAdapter<String>
    private lateinit var viewModel: AddLocationViewModel
    private val TAG = CityCompleteActivity::class.java.simpleName
    private lateinit var inputHandler: Handler
    private lateinit var inputRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.city_auto_complete_screen)

        suggestionAdapter = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1,
            suggestions)

        cityAutoComplete.setAdapter(suggestionAdapter)

        viewModel = ViewModelProviders.of(this).get(AddLocationViewModel::class.java)

        addSuggestionListener()
    }

    private fun addSuggestionListener() {

        inputHandler = Handler(Looper.getMainLooper())

        inputRunnable = Runnable {
            Log.e(TAG, "Setting runnable for input")
        }

        cityAutoComplete.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                getCitySuggestions(p0.toString())
            }
        })

        viewModel.citySuggestions.observe(this, Observer<List<PlaceData>> {suggestionList ->

            Log.i(TAG, "Full list of suggestions: $suggestionList")

            val suggestions = ArrayList<String>()

            suggestionList.forEach { place ->
                suggestions.add(place.description)
            }

            suggestionAdapter.addAll(suggestions)
            suggestionAdapter.notifyDataSetChanged()
        })
    }

    private fun getCitySuggestions(query: String) {
        inputHandler.removeCallbacks(inputRunnable)
        inputRunnable = Runnable {
            Log.i(TAG, "Getting suggestions for $query")
            viewModel.getCityByName(query)
        }

        inputHandler.postDelayed(inputRunnable, 1000)
    }
}