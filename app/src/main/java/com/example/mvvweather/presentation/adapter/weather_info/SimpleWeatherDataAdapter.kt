package com.example.mvvweather.presentation.adapter.weather_info

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvweather.R
import com.example.mvvweather.data.weather.mapping.WeatherData

class SimpleWeatherDataAdapter(val weatherList: List<WeatherData>):
    RecyclerView.Adapter<SimpleWeatherDataAdapter.SimpleWeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleWeatherViewHolder {
        return SimpleWeatherViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.forecast_item, parent, false))
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: SimpleWeatherViewHolder, position: Int) {
        holder.currentItem = weatherList.get(position)
        holder.tempMax.text = holder.currentItem.maxTemp
        holder.tempMin.text = holder.currentItem.minTemp
        holder.condition.text = holder.currentItem.condition
    }

    class SimpleWeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        lateinit var currentItem: WeatherData
        val tempMax: TextView = itemView.findViewById(0)
        val tempMin: TextView = itemView.findViewById(0)
        val condition: TextView = itemView.findViewById(0)

    }
}