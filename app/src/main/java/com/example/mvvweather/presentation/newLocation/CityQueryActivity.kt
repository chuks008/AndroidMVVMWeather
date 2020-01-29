package com.example.mvvweather.presentation.newLocation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvweather.R
import com.example.mvvweather.di.modules.viewModel.ViewModelFactory
import com.example.mvvweather.presentation.adapter.AutocompleteAdapter
import com.example.mvvweather.presentation.adapter.weather_info.SimpleWeatherDataAdapter
import com.example.mvvweather.presentation.adapter.weather_info.WeatherDataView
import com.example.mvvweather.presentation.weather.current.WeatherListActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.city_auto_complete_screen.*
import javax.inject.Inject

class CityQueryActivity: DaggerAppCompatActivity() {

    private val TAG = CityQueryActivity::class.java.simpleName

    private val suggestions = ArrayList<String>()
    private lateinit var suggestionAdapter: AutocompleteAdapter
    private lateinit var inputHandler: Handler
    private lateinit var inputRunnable: Runnable
    private lateinit var weatherDataAdapter: SimpleWeatherDataAdapter
    private val weatherDataViewList = ArrayList<WeatherDataView>()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: AddLocationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.city_auto_complete_screen)

        setSuggestionAdapter()

        weatherDataAdapter = SimpleWeatherDataAdapter(weatherDataViewList)
        cityWeatherRecyclerView.layoutManager = LinearLayoutManager(this)
        cityWeatherRecyclerView.adapter = weatherDataAdapter

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(AddLocationViewModel::class.java)

        addViewModelObservers()
        addSuggestionListener()
    }

    private fun setSuggestionAdapter() {
        suggestionAdapter = AutocompleteAdapter(
            this,
            R.layout.auto_complete_list_item,
            suggestions
        )

        cityAutoComplete.setAdapter(suggestionAdapter)
        cityAutoComplete.threshold = 3
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

        viewModel.isCurrentWeatherEntrySaved.observe(this, Observer<Boolean> { isCityAdded ->
            if(isCityAdded) {
                startActivity(Intent(this, WeatherListActivity::class.java))
            } else {
                Toast.makeText(this,
                    "Unable to add new city. Please try again",
                    Toast.LENGTH_SHORT).show()
            }
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
                    setCitySuggestions(query.toString())
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })

        cityAutoComplete.setOnItemClickListener { adapterView, view, position, l ->
            Log.e(TAG, "First item: ${suggestionAdapter.getItem(0)}")
            viewModel.setCityDetail(suggestionAdapter.getItem(position) as String)
        }

    }

    private fun setCitySuggestions(query: String) {

        suggestionAdapter.clear()
        suggestionAdapter.notifyDataSetChanged()

        inputHandler.removeCallbacks(inputRunnable)
        inputRunnable = Runnable {
            Log.i(TAG, "Getting suggestions for $query")
            viewModel.setCityName(query)
        }

        inputHandler.postDelayed(inputRunnable, 1000)
    }
}