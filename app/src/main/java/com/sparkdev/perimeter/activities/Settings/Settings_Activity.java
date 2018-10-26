package com.sparkdev.perimeter.activities.Settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.widget.SwitchCompat;

import com.sparkdev.perimeter.R;


public class Settings_Activity extends PreferenceActivity {

  SwitchCompat switch_1, switch_2;
  SharedPreferences preferences;

  boolean stateSwitch1, stateSwitch2;

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getFragmentManager().beginTransaction().replace(android.R.id.content, new MainSettingsFragment()).commit();

  }

  public static class MainSettingsFragment extends PreferenceFragment {
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      addPreferencesFromResource(R.xml.preferences);

      bindSummaryValue(findPreference("key_email"));

    }
  }

  private static void bindSummaryValue(Preference preference) {
    preference.setOnPreferenceChangeListener(listener);
    listener.onPreferenceChange(preference, PreferenceManager.getDefaultSharedPreferences(preference.getContext())
        .getString(preference.getKey(), ""));
  }

  private static Preference.OnPreferenceChangeListener listener = new Preference.OnPreferenceChangeListener() {
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {

      String stringValue = newValue.toString();
      if (preference instanceof ListPreference) {
        ListPreference listPreference = (ListPreference) preference;
        int index = listPreference.findIndexOfValue(stringValue);
        preference.setSummary(index > 0 ? listPreference.getEntries()[index] : null);

      } else if (preference instanceof EditTextPreference) {
        preference.setSummary(stringValue);
      }

      return true;
    }
  };

}
