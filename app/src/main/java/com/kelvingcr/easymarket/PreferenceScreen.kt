package com.kelvingcr.easymarket

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreference

class PreferenceScreen : PreferenceFragmentCompat()  {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference_app, rootKey)


        val preference = findPreference<SwitchPreference>("onModeNight")
        val preferenceManager = PreferenceManager.getDefaultSharedPreferences(requireContext())

        preference?.let {
            preference?.setOnPreferenceClickListener {
                if(preferenceManager.getBoolean("onModeNight", false)) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                true
            }
        }

    }
}