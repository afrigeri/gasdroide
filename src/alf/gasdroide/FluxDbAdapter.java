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
	 * Create a new flux record If the todo is successfully created return the new
	 * rowId for that note, otherwise return a -1 to indicate failure.
	 */
	public long createFluxRecord(String fluxid, String clock,
			String value_ppm) {
		ContentValues initialValues = createContentValues(fluxid, clock,
				value_ppm);

		return database.insert(DATABASE_TABLE, null, initialValues);
	}

	/**
	 * Update the flux
	 */
	public boolean updateTodo(long rowId, String fluxid, String clock,
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
	 * Return a Cursor positioned at the defined todo
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

	private ContentValues createContentValues(String fluxid, String clock,
			String value_ppm) {
		ContentValues values = new ContentValues();
		values.put(KEY_FLUXID, fluxid);
		values.put(KEY_CLOCK, clock);
		values.put(KEY_VALUE_PPM, value_ppm);
		return values;
	}
}