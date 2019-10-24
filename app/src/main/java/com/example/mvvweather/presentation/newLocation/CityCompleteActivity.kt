package com.example.mvvweather.presentation.newLocation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvweather.R
import com.example.mvvweather.data.location.response.LocationData
import com.example.mvvweather.presentation.adapter.AutocompleteAdapter
import kotlinx.android.synthetic.main.city_auto_complete_screen.*

class CityCompleteActivity: AppCompatActivity() {

    private val suggestions = ArrayList<String>()
    private lateinit var suggestionAdapter: AutocompleteAdapter
    private lateinit var viewModel: AddLocationViewModel
    private val TAG = CityCompleteActivity::class.java.simpleName
    private lateinit var inputHandler: Handler
    private lateinit var inputRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.city_auto_complete_screen)

        suggestionAdapter = AutocompleteAdapter(this,
            R.layout.auto_complete_list_item,
            suggestions)

        cityAutoComplete.setAdapter(suggestionAdapter)

        cityAutoComplete.threshold = 3

        viewModel = ViewModelProviders.of(this).get(AddLocationViewModel::class.java)

        addViewModelObservers()

        addSuggestionListener()
    }

    private fun addViewModelObservers() {
        viewModel.citySuggestions.observe(this, Observer<List<String>> {suggestionList ->

            Log.i(TAG, "Full list of suggestions: $suggestionList")

            val acquiredSuggestions = ArrayList<String>()

            suggestionList.forEach {suggestion ->
                acquiredSuggestions.add(suggestion)
            }

            suggestionAdapter.addAll(acquiredSuggestions)
            suggestionAdapter.notifyDataSetChanged()
        })

        viewModel.cityDetail.observe(this, Observer<LocationData> {cityData ->

            val resultString = String.format("Location is: %s, " +
            "with country code: %s, with latitude %.2f and longitude %.2f", cityData.city,
                cityData.countryCode, cityData.lat, cityData.long)

            Toast.makeText(this, resultString, Toast.LENGTH_LONG).show()
        })
    }

    private fun addSuggestionListener() {

        inputHandler = Handler(Looper.getMainLooper())

        inputRunnable = Runnable {
            Log.e(TAG, "Setting runnable for input")
        }

        cityAutoComplete.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(query: Editable) {
                if(cityAutoComplete.isPerformingCompletion) {
                    return
                }

                if(query.toString().trim().isNotEmpty() &&
                    query.toString().length > 2) {
                    getCitySuggestions(query.toString())
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })

        cityAutoComplete.setOnItemClickListener { adapterView, view, position, l ->
            Log.e(TAG, "First item: ${suggestionAdapter.getItem(0)}")
            viewModel.getCityDetail(suggestionAdapter.getItem(position) as String)
        }

    }

    private fun getCitySuggestions(query: String) {

        suggestionAdapter.clear()
        suggestionAdapter.notifyDataSetChanged()

        inputHandler.removeCallbacks(inputRunnable)
        inputRunnable = Runnable {
            Log.i(TAG, "Getting suggestions for $query")
            viewModel.getCityByName(query)
        }

        inputHandler.postDelayed(inputRunnable, 1000)
    }
}