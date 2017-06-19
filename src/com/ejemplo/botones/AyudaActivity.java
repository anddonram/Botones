package com.ejemplo.botones;

import com.ejemplo.botones.fragments.AyudaFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AyudaActivity extends ActionBarActivity {
	private static final int[] TEXTOS = new int[] { R.string.comojugar1,
			R.string.comojugar2, R.string.comojugar3, R.string.comojugar4 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_pager);
		((ViewPager) findViewById(R.id.pager)).setAdapter(new Adaptador(
				getSupportFragmentManager()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ayuda, menu);
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
			AyudaFragment fg = new AyudaFragment();
			Bundle b = new Bundle();
			b.putInt("i", TEXTOS[i]);
			fg.setArguments(b);
			return fg;
		}

		@Override
		public int getCount() {
			return TEXTOS.length;
		}
	}
}
