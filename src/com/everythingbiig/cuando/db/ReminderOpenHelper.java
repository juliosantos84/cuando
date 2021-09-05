package com.everythingbiig.cuando.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ReminderOpenHelper extends SQLiteOpenHelper {

	private static final String LOG_TAG = "ReminderOpenHelper";

	public static final int VERSION = 1;

	public static final String TABLE_NAME = "REMINDER";

	public static final String COLUMN_ID = "ID";
	public static final String COLUMN_NAME = "NAME";
	public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
	public static final String COLUMN_TYPE = "TYPE";
	public static final String COLUMN_COMPLETED = "COMPLETED";

	private static final String CREATE_REMINDER_TABLE =
		new StringBuilder("create table ")
						.append(TABLE_NAME)
						.append(" (")
						.append(COLUMN_ID)
						.append(" integer primary key autoincrement,")
						.append(COLUMN_NAME)
						.append(" text not null,")
						.append(COLUMN_DESCRIPTION)
						.append(" text,")
						.append(COLUMN_TYPE)
						.append(" int not null,")
						.append(COLUMN_COMPLETED)
						.append(" int")
						.append(");").toString();
	
	public ReminderOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d(LOG_TAG, "Creating database table...");
		db.execSQL(CREATE_REMINDER_TABLE);
		Log.d(LOG_TAG, "Finished creating database table");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//TODO
		Log.d(LOG_TAG, "Upgrading database...");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
		Log.d(LOG_TAG, "Finished upgrading database");
	}

}
