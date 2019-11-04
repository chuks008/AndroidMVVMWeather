package com.example.mvvweather.presentation.adapter.weather_info

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvweather.R

class SimpleWeatherDataAdapter(val weatherList: List<WeatherDataView>):
    RecyclerView.Adapter<SimpleWeatherDataAdapter.SimpleWeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleWeatherViewHolder {
        return SimpleWeatherViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false))
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: SimpleWeatherViewHolder, position: Int) {
        holder.currentItem = weatherList[position]

        holder.apply {

            highLowText.text = String.format("%s / %s°",
                currentItem.maxTemp,
                currentItem.minTemp)

            higLowUnitText.text = "C"
            condition.text = currentItem.condition
            currentTemp.text = currentItem.currentTemp
            cityName.text = currentItem.city
            countryName.text = currentItem.country
        }

    }

    class SimpleWeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        lateinit var currentItem: WeatherDataView
        val currentTemp: TextView = itemView.findViewById(R.id.currentWeatherTempText)
        val highLowText: TextView = itemView.findViewById(R.id.highLowText)
        val higLowUnitText: TextView = itemView.findViewById(R.id.highLowUnitText)
        val condition: TextView = itemView.findViewById(R.id.weatherItemConditionText)
        val cityName: TextView = itemView.findViewById(R.id.cityNameText)
        val countryName: TextView = itemView.findViewById(R.id.countryNameText)

    }
}