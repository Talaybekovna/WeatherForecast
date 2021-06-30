package kg.tutorialapp.forecast.repo

import android.widget.Toast
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kg.tutorialapp.forecast.models.ForeCast
import kg.tutorialapp.forecast.network.WeatherApi
import kg.tutorialapp.forecast.storage.ForeCastDatabase

class WeatherRepo(
    private val db: ForeCastDatabase,
    private val weatherApi: WeatherApi
) {

    fun getWeatherFromApi(): Single<ForeCast> {
        return weatherApi.fetchWeather()
            .subscribeOn(Schedulers.io())
            .map {
                db.forecastDao().insert(it)
                it
            }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getForeCastFromDbAsLive() = db.forecastDao().getAll()
}