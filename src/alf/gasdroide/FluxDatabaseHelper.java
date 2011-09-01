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


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FluxDatabaseHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "applicationdata";

	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table flux_data (_id integer primary key autoincrement, "
			+ "clock int not null, value_ppm float not null);";

	public FluxDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Method is called during creation of the database
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	// Method is called during an upgrade of the database, e.g. if you increase
	// the database version
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		Log.w(FluxDatabaseHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS todo");
		onCreate(database);
	}
}