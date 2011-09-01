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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class FluxDbAdapter {

	// Database fields
	public static final String KEY_ROWID = "_id";
	public static final String KEY_FLUXID = "fluxid";
	public static final String KEY_CLOCK = "clock";
	public static final String KEY_VALUE_PPM = "value";
	private static final String DATABASE_TABLE = "gasflux";
	private Context context;
	private SQLiteDatabase database;
	private FluxDatabaseHelper dbHelper;

	public FluxDbAdapter(Context context) {
		this.context = context;
	}

	public FluxDbAdapter open() throws SQLException {
		dbHelper = new FluxDatabaseHelper(context);
		database = dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		dbHelper.close();
	}

	/**
	 * Create a new flux record If the table is successfully created return the new
	 * rowId for that note, otherwise return a -1 to indicate failure.
	 */
	public long createFluxRecord(String fluxid, long clock,
			String value_ppm) {
		ContentValues initialValues = createContentValues(fluxid, clock, value_ppm);

		return database.insert(DATABASE_TABLE, null, initialValues);
	}

	/**
	 * Update the flux
	 */
	public boolean updateTodo(long rowId, String fluxid, long clock,
			String value_ppm) {
		ContentValues updateValues = createContentValues(fluxid, clock,
				value_ppm);

		return database.update(DATABASE_TABLE, updateValues, KEY_ROWID + "="
				+ rowId, null) > 0;
	}

	/**
	 * Deletes fluxes
	 */
	public boolean deleteFlux(long rowId) {
		return database.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}

	/**
	 * Return a Cursor over the list of all Flux readings in the database
	 * 
	 * @return Cursor over all notes
	 */
	public Cursor fetchAllFluxes() {
		return database.query(DATABASE_TABLE, new String[] { KEY_ROWID,
				KEY_FLUXID, KEY_CLOCK, KEY_VALUE_PPM }, null, null, null,
				null, null);
	}

	/**
	 * Return a Cursor positioned at the defined rowId
	 */
	public Cursor fetchFlux(long rowId) throws SQLException {
		Cursor mCursor = database.query(true, DATABASE_TABLE, new String[] {
				KEY_ROWID, KEY_FLUXID, KEY_CLOCK, KEY_VALUE_PPM  },
				KEY_ROWID + "=" + rowId, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	private ContentValues createContentValues(String fluxid, long clock,
			String value_ppm) {
		ContentValues values = new ContentValues();
		values.put(KEY_FLUXID, fluxid);
		values.put(KEY_CLOCK, clock);
		values.put(KEY_VALUE_PPM, value_ppm);
		return values;
	}
}