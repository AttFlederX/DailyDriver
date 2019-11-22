package com.attflederx.dailydriver.utils

import android.content.Context
import android.content.SharedPreferences
import com.attflederx.dailydriver.models.enums.TempUnits

object SettingsHelper {

    private val settingsKey = "com.attflederx.dailydriver.PREFERENCES_FILE_KEY"

    val temperatureUnitsKey = "TEMPERATURE_UNITS"

    lateinit var context: Context

    var tempUnits: TempUnits
        get() = TempUnits.values()[getPreferences().getInt(temperatureUnitsKey, TempUnits.CELSIUS.value)]
        set(newVal) {
            with (getPreferences().edit()) {
                putInt(temperatureUnitsKey, newVal.value)
                commit()
            }
        }

    private fun getPreferences(): SharedPreferences = context.getSharedPreferences(settingsKey, Context.MODE_PRIVATE)
}