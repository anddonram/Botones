package com.ejemplo.botones;

import com.ejemplo.botones.R.id;
import com.ejemplo.botones.R.raw;
import com.ejemplo.botones.R.string;
import com.ejemplo.botones.fragments.MisionFragment;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class MisionesActivity extends ActionBarActivity {
	private static final int[] TEXTOS = new int[] { string.m1, string.m2,
			string.m3, string.m4, string.m5 };
	private static final int[] MISIONES = new int[] { raw.m1, raw.m2, raw.m3,
			raw.m4 ,raw.m5};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_pager);
		((ViewPager) findViewById(id.pager)).setAdapter(new Adaptador(
				getSupportFragmentManager()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.misiones, menu);
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

	private class Adaptador extends FragmentStatePagerAdapter {

		public Adaptador(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int i) {
			MisionFragment fg = new MisionFragment();
			Bundle b = new Bundle();
			b.putInt("texto", TEXTOS[i]);
			b.putInt("mision", MISIONES[i]);
			fg.setArguments(b);
			return fg;
		}

		@Override
		public int getCount() {
			return getSharedPreferences("retos", 0).getInt("nMisiones", 3);
		}

	}
}
