package com.ejemplo.botones.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.ejemplo.botones.R.color;
import com.ejemplo.botones.R.layout;

public class Generador {
	public static void crearToast(Context c, String s) {
		Toast.makeText(c, s, Toast.LENGTH_SHORT).show();
	}

	public static TableLayout crearLayoutPrueba(Context c) {
		TableLayout.LayoutParams t = new TableLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1);
		TableRow.LayoutParams t2 = new TableRow.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		TableLayout tb = new TableLayout(c);

		TableRow tr = new TableRow(c);
		tr.setLayoutParams(t);
		tb.addView(tr);

		ImageButton b = new ImageButton(c);
		b.setLayoutParams(t2);
		tr.addView(b);
		View v = new View(c);
		v.setLayoutParams(t2);
		tr.addView(v);

		TableRow tr2 = new TableRow(c);
		tr2.setLayoutParams(t);
		tb.addView(tr2);

		ImageButton b2 = new ImageButton(c);
		b2.setLayoutParams(t2);
		tr2.addView(b2);
		View v2 = new View(c);
		v2.setLayoutParams(t2);
		tr2.addView(v2);

		tb.setStretchAllColumns(true);
		return tb;
	}

	public static TableLayout crearLayoutFichero(Context c, int rawFile) {
		BufferedReader bf = new BufferedReader(new InputStreamReader(c
				.getResources().openRawResource(rawFile)));
		String linea = "";
		String[] tipoView;
		TableRow tr;
		ImageButton ib;
		TextView v;
		TableLayout.LayoutParams t = new TableLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1);
		TableRow.LayoutParams t2 = new TableRow.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		TableLayout tb = new TableLayout(c);
		tb.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		TableLayout.inflate(c, layout.tiempo, tb);
		int nBotones = 0;
		try {
			while (linea != null) {
				linea = bf.readLine();
				if (linea == null)
					break;
				tr = new TableRow(c);
				tr.setLayoutParams(t);
				tb.addView(tr);
				tipoView = linea.split(",");
				for (String vista : tipoView) {
					if (vista.trim().equals("1")) {
						ib = new ImageButton(c);
						ib.setLayoutParams(t2);
						ib.setBackgroundColor(color.material_blue_grey_800);
						tr.addView(ib);
						nBotones++;
						ib.setId(nBotones);
					} else if (vista.trim().equals("0")) {
						v = new TextView(c);
						v.setLayoutParams(t2);
						tr.addView(v);
					}

				}
			}
			bf.close();
		} catch (IOException e) {
		}
		tb.setTag(nBotones);
		tb.setStretchAllColumns(true);
		return tb;
	}

}
