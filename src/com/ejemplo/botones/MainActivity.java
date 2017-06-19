package com.ejemplo.botones;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
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

	public void abrirOpciones(View view) {
		startActivity(new Intent(this, OptionsActivity.class));
	}

	public void abrirJuego(View view) {
		startActivity(new Intent(this, GameActivity.class).putExtra("t", false));
	}

	public void abrirExtras(View view) {
		startActivity(new Intent(this, ExtrasActivity.class));
	}

	public void abrirContrarreloj(View view) {
		startActivity(new Intent(this, GameActivity.class).putExtra("t", true));
	}

	public void abrirAyuda(View view) {
		startActivity(new Intent(this, AyudaActivity.class));
	}

	public void abrirMisiones(View view) {
		startActivity(new Intent(this, MisionesActivity.class));
	}
}
