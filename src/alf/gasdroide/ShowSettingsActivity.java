//
// Copyright 2010,2011 Alessandro Frigeri
//
// This file is part of GasDroide.
//
// GasDroide is free software: you can redistribute it and/or modify it
// under the terms of the GNU General Public License as published by the
// Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// GasDroide is distributed in the hope that it will be useful, but
// WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
// See the GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License along
// with GasDroide. If not, see http://www.gnu.org/licenses/.
//
//
//

package alf.gasdroide;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class ShowSettingsActivity extends Activity {

 @Override
 protected void onCreate(Bundle savedInstanceState) {

  super.onCreate(savedInstanceState);
  setContentView(R.layout.show_settings);

  SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

  StringBuilder builder = new StringBuilder();

  //builder.append("\n" + sharedPrefs.getBoolean("perform_updates", false));
  builder.append("\n" + sharedPrefs.getString("update_interval", "-1"));
  builder.append("\n" + sharedPrefs.getString("username", "NULL"));

  TextView settingsTextView = (TextView) findViewById(R.id.settings_text_view);
  settingsTextView.setText(builder.toString());

 }

}
