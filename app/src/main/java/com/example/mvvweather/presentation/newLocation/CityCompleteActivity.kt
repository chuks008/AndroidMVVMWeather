package com.example.mvvweather.presentation.newLocation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvweather.R
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

        addSuggestionListener()
    }

    private fun addSuggestionListener() {

        inputHandler = Handler(Looper.getMainLooper())

        inputRunnable = Runnable {
            Log.e(TAG, "Setting runnable for input")
        }

        cityAutoComplete.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(query: Editable) {
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

        viewModel.citySuggestions.observe(this, Observer<List<String>> {suggestionList ->

            Log.i(TAG, "Full list of suggestions: $suggestionList")

            val acquiredSuggestions = ArrayList<String>()

            suggestionList.forEach {suggestion ->
                acquiredSuggestions.add(suggestion)
            }

            suggestionAdapter.addAll(acquiredSuggestions)
            suggestionAdapter.notifyDataSetChanged()
        })
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