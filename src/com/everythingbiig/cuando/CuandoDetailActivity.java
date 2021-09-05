package com.everythingbiig.cuando;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.everythingbiig.cuando.model.Reminder;
import com.everythingbiig.cuando.model.ReminderType;

public class CuandoDetailActivity extends Activity {

	private static final String LOG_TAG = "CuandoDetailActivity";

	private EditText name = null;
	private EditText description = null;
	private Spinner type = null;
	private CheckBox completed = null;

	private Reminder reminder = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cuando_detail);
		// FIXME Check if we are creating a new one, or opening an existing one
		reminder = new Reminder();
		configureInputs();
	}

	private void configureInputs() {
		name = (EditText)findViewById(R.id.reminder_name_text);
		name.setText(reminder.getName());
		name.addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable editable) {
				reminder.setName(editable.toString());
				saveReminder();
			}
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
			}
		});
//		description = (EditText)findViewById(R.id.reminder_description_text);
//		description.setText(reminder.getDescription());
//		description.addTextChangedListener(new TextWatcher() {
//			@Override
//			public void afterTextChanged(Editable editable) {
//				reminder.setDescription(editable.toString());
//				saveReminder();
//			}
//			@Override
//			public void beforeTextChanged(CharSequence arg0, int arg1,
//					int arg2, int arg3) {
//				// TODO Auto-generated method stub
//			}
//			@Override
//			public void onTextChanged(CharSequence s, int start, int before,
//					int count) {
//				// TODO Auto-generated method stub
//			}
//		});
		type = (Spinner)findViewById(R.id.reminder_type_spinner);
		type.setSelection(reminder.getType().getPosition());
		type.setAdapter(new ArrayAdapter<ReminderType>(this, android.R.layout.simple_spinner_item, ReminderType.values()){
			@Override
			public long getItemId(int pos) {
				return getItem(pos).getId();
			}
			@Override 
			public View getDropDownView(int pos, View convertView, ViewGroup parentView) {
				Log.d(LOG_TAG, "Getting drop down view...");
				ReminderType type = getItem(pos);
				int id = R.string.reminder_type_none_label;
				if(ReminderType.BeforeArriving.equals(type)) {
					id = R.string.reminder_type_before_arriving_label;
				} else if(ReminderType.OnArrival.equals(type)) {
					id = R.string.reminder_type_on_arrival_label;
				} else if(ReminderType.AfterArrival.equals(type)) {
					id = R.string.reminder_type_after_arrival_label;
				}
				Log.d(LOG_TAG, "Got id="+id);
				String label = getResources().getString(id);
				Log.d(LOG_TAG, "Label for dropdown is " + label);
				TextView textView = (TextView)convertView;
				if(textView != null) {
					textView.setText(label);
				} else {
					Log.d(LOG_TAG, "The view is null...");
					// TODO
					// textView = (TextView)getLayoutInflater().inflate(android.R.layout.simple_spinner_dropdown_item, parentView);
				}
				return convertView;
			}
		});
		type.setOnItemSelectedListener(new OnItemSelectedListener () {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View parentView,
					int pos, long id) {
				reminder.setType(ReminderType.getById((int)id));
				saveReminder();
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// Do nothing
			}
		});
		completed = (CheckBox)findViewById(R.id.reminder_completed);
		completed.setChecked(reminder.isCompleted());
		completed.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				reminder.setCompleted(isChecked);
				saveReminder();
			}
		});
	}

	private void saveReminder() {
		// TODO Auto-generated method stub
		Log.d(LOG_TAG, "Called saveReminder()");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cuando_detail, menu);
		return true;
	}

}
