package com.ejemplo.botones;

import java.io.FileNotFoundException;

import com.ejemplo.botones.R.id;
import com.ejemplo.botones.fragments.BorrarImagenDialogFragment;
import com.ejemplo.botones.fragments.BorrarMusicaDialogFragment;
import com.ejemplo.botones.fragments.BorrarRecordsDialogFragment;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ExtrasActivity extends ActionBarActivity implements
		OnCheckedChangeListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_extras);
		
				((ImageView) findViewById(R.id.foto)).setImageBitmap(GameActivity.foto);
		((CheckBox) findViewById(id.epic)).setChecked(GameActivity.EPICMODE);

		((RadioGroup) findViewById(id.rdg1)).setOnCheckedChangeListener(this);
		((RadioGroup) findViewById(id.rdg2)).setOnCheckedChangeListener(this);
		((RadioGroup) findViewById(id.rdg3)).setOnCheckedChangeListener(this);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		int a = ((RadioGroup) findViewById(id.rdg1)).getCheckedRadioButtonId();
		int b = ((RadioGroup) findViewById(id.rdg2)).getCheckedRadioButtonId();
		int c = ((RadioGroup) findViewById(id.rdg3)).getCheckedRadioButtonId();
		int d = 0;
		long e = 0;
		SharedPreferences set = getSharedPreferences("puntos", 0);
		if (c == id.radioButton10) {
			if (a == id.radioButton1) {
				if (b == id.radioButton4)
					d = set.getInt("4-30", 0);
				else if (b == id.radioButton5)
					d = set.getInt("4-60", 0);
				else if (b == id.radioButton6)
					d = set.getInt("4-120", 0);
				else if (b == id.radioButton9)
					d = set.getInt("4-0", 0);
			} else if (a == id.radioButton2) {
				if (b == id.radioButton4)
					d = set.getInt("8-30", 0);
				else if (b == id.radioButton5)
					d = set.getInt("8-60", 0);
				else if (b == id.radioButton6)
					d = set.getInt("8-120", 0);
				else if (b == id.radioButton9)
					d = set.getInt("8-0", 0);
			} else if (a == id.radioButton3) {
				if (b == id.radioButton4)
					d = set.getInt("16-30", 0);
				else if (b == id.radioButton5)
					d = set.getInt("16-60", 0);
				else if (b == id.radioButton6)
					d = set.getInt("16-120", 0);
				else if (b == id.radioButton9)
					d = set.getInt("16-0", 0);
			}
			((TextView) findViewById(id.textView1)).setText("Puntuacion: " + d);
		} else {
			if (a == id.radioButton1) {
				if (b == id.radioButton4)
					e = set.getLong("4-10", 0);
				else if (b == id.radioButton5)
					e = set.getLong("4-50", 0);
				else if (b == id.radioButton6)
					e = set.getLong("4-100", 0);
				else if (b == id.radioButton9)
					e = set.getLong("4-200", 0);
			} else if (a == id.radioButton2) {
				if (b == id.radioButton4)
					e = set.getLong("8-10", 0);
				else if (b == id.radioButton5)
					e = set.getLong("8-50", 0);
				else if (b == id.radioButton6)
					e = set.getLong("8-100", 0);
				else if (b == id.radioButton9)
					e = set.getLong("8-200", 0);
			} else if (a == id.radioButton3) {
				if (b == id.radioButton4)
					e = set.getLong("16-10", 0);
				else if (b == id.radioButton5)
					e = set.getLong("16-50", 0);
				else if (b == id.radioButton6)
					e = set.getLong("16-100", 0);
				else if (b == id.radioButton9)
					e = set.getLong("16-200", 0);
			}
			((TextView) findViewById(id.textView1)).setText("Tiempo: " + (float)e
					/ 1000);
		}

	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.extras, menu);
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

	public void borrar(View view) {
		new BorrarRecordsDialogFragment().show(getSupportFragmentManager(),
				"records");
	}

	public void activaEpic(View view) {
		GameActivity.EPICMODE = ((CheckBox) findViewById(id.epic)).isChecked();
	}

	public void buscarMusica(View view) {
		new BorrarMusicaDialogFragment().show(getSupportFragmentManager(),
				"musica");
	}

	public void buscarImagen(View view) {
		new BorrarImagenDialogFragment().show(getSupportFragmentManager(),
				"imagen");
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		super.onActivityResult(arg0, arg1, arg2);

		if (arg0 == 1 && arg1 == RESULT_OK)
			GameActivity.uri = arg2.getData();
		else if (arg0 == 2 && arg1 == RESULT_OK) {
			try {
				BitmapFactory.Options op = new BitmapFactory.Options();
				op.inJustDecodeBounds = true;
				BitmapFactory.decodeStream(getContentResolver()
						.openInputStream(arg2.getData()), null, op);
				int w = op.outWidth;
				int h = op.outHeight;
				int i = 1;
				RadioGroup b = (RadioGroup) findViewById(R.id.rdg2);
				while (b.getWidth() < w || b.getHeight() < h) {
					w /= 2;
					h /= 2;
					i *= 2;
				}
				op.inSampleSize = i;
				op.inJustDecodeBounds = false;
				GameActivity.foto = BitmapFactory.decodeStream(
						getContentResolver().openInputStream(arg2.getData()),
						null, op);
				((ImageView) findViewById(R.id.foto))
						.setImageBitmap(GameActivity.foto);
			} catch (FileNotFoundException e) {
				Toast.makeText(getApplicationContext(),
						"No se ha podido guardar la foto", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (GameActivity.EPICMODE && GameActivity.foto == null) {
			BitmapFactory.Options op = new BitmapFactory.Options();
			op.inJustDecodeBounds = true;
			BitmapFactory.decodeResource(getResources(), R.drawable.epic2, op);
			int w = op.outWidth;
			int h = op.outHeight;
			int i = 1;
			RadioGroup b = (RadioGroup) findViewById(R.id.rdg2);
			while (b.getWidth() < w || b.getHeight() < h) {
				w /= 2;
				h /= 2;
				i *= 2;
			}
			op.inSampleSize = i;
			op.inJustDecodeBounds = false;
			GameActivity.foto = BitmapFactory.decodeResource(getResources(),
					R.drawable.epic2, op);
		}
	}
}
