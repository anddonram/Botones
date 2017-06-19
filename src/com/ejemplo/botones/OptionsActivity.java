package com.ejemplo.botones;

import com.ejemplo.botones.R.id;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class OptionsActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options);
		((CheckBox) findViewById(id.dificil)).setChecked(GameActivity.DIFICIL);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.options, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onPause() {
		super.onPause();
		if (((RadioButton) findViewById(id.radioButton1)).isChecked())
			GameActivity.N_BOTON = 4;
		else if (((RadioButton) findViewById(id.radioButton2)).isChecked())
			GameActivity.N_BOTON = 8;
		else if (((RadioButton) findViewById(id.radioButton3)).isChecked())
			GameActivity.N_BOTON = 16;
		if (((RadioButton) findViewById(id.radioButton4)).isChecked()) {
			GameActivity.TIEMPO = 30;
			GameActivity.PUNTMAX = 10;
		} else if (((RadioButton) findViewById(id.radioButton5)).isChecked()) {
			GameActivity.TIEMPO = 60;
			GameActivity.PUNTMAX = 50;
		} else if (((RadioButton) findViewById(id.radioButton6)).isChecked()) {
			GameActivity.TIEMPO = 120;
			GameActivity.PUNTMAX = 100;
		} else if (((RadioButton) findViewById(id.radioButton9)).isChecked()) {
			GameActivity.TIEMPO = 0;
			GameActivity.PUNTMAX = 200;
		}
		if (((RadioButton) findViewById(id.radioButton7)).isChecked())
			GameActivity.CUADRICULA = true;
		else if (((RadioButton) findViewById(id.radioButton8)).isChecked())
			GameActivity.CUADRICULA = false;
		GameActivity.DIFICIL = ((CheckBox) findViewById(id.dificil))
				.isChecked();
	}
}
