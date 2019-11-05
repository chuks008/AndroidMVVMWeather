package com.example.mvvweather.presentation.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mvvweather.R
import com.example.mvvweather.presentation.adapter.weather_info.WeatherDataView
import kotlinx.android.synthetic.main.forecast_preview_layout.*
import kotlinx.android.synthetic.main.weather_pager_item.*

class WeatherItemFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weather_pager_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val forecastList = ArrayList<WeatherDataView>()

        forecastList.add(
            WeatherDataView("30",
            "24",
            "32",
            "Clear",
            "",
            "Lagos",
            "Nigeria")
        )

        forecastList.add(
            WeatherDataView("30",
                "18",
                "27",
                "ThunderStorms",
                "",
                "Lagos",
                "Nigeria")
        )

        forecastList.add(
            WeatherDataView("30",
                "19",
                "31",
                "Cloudy",
                "",
                "Lagos",
                "Nigeria")
        )

        forecastList.let {

            weatherPagerItemTempText.text = String.format("%s°", it[0].currentTemp)
            weatherPagerItemUnitText.text = "C"
            weatherPagerItemConditionText.text = it[0].condition

            day1Forecast.findViewById<TextView>(R.id.forecastCondition).text = it[0].condition
            day1Forecast.findViewById<TextView>(R.id.forecastHighLowText).text = String.format("%s° / %s°", it[0].maxTemp, it[0].minTemp)

            day2Forecast.findViewById<TextView>(R.id.forecastCondition).text = it[1].condition
            day2Forecast.findViewById<TextView>(R.id.forecastHighLowText).text = String.format("%s° / %s°", it[1].maxTemp, it[1].minTemp)

            day3Forecast.findViewById<TextView>(R.id.forecastCondition).text = it[2].condition
            day3Forecast.findViewById<TextView>(R.id.forecastHighLowText).text = String.format("%s° / %s°", it[2].maxTemp, it[2].minTemp)
        }
    }
}