package com.ejemplo.botones;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.ejemplo.botones.R.id;
import com.ejemplo.botones.R.layout;
import com.ejemplo.botones.R.raw;
import com.ejemplo.botones.util.Generador;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;

public class GameActivity extends Activity implements Runnable, OnClickListener {
	private static final int[] ids = { id.button1, id.button2, id.button3,
			id.button4, id.button5, id.button6, id.button7, id.button8,
			id.button9, id.button10, id.button11, id.button12, id.button13,
			id.button14, id.button15, id.button16 };
	public static Uri uri;
	public static Bitmap foto;
	public static boolean DIFICIL = false;
	public static boolean EPICMODE = false;
	public static boolean CUADRICULA = true;
	public static int N_BOTON = 4;
	public static int TIEMPO = 30;
	public static int PUNTMAX = 10;
	private static final Random rnd = new Random();
	private long tiempoActual;
	private int puntActual;
	private boolean fin = true;
	private Handler hand;
	private ImageButton boton;
	private CountDownTimer timer;
	private MediaPlayer mp;
	private ImageButton[] botones;
	private Integer colorEscogido;
	private List<Integer> colores;
	private int dificultad;
	private int nBotones;
	private boolean esMision;
	private boolean esContrarreloj;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		puntActual = 0;
		dificultad = 50;
		hand = new Handler();
		esMision = getIntent().getIntExtra("mision", -1) != -1;
		esContrarreloj = getIntent().getBooleanExtra("t", false);
		if (esMision) {
			TableLayout tb = Generador.crearLayoutFichero(this, getIntent()
					.getExtras().getInt("mision"));
			nBotones = (Integer) tb.getTag();
			setContentView(tb);
		} else {
			if (N_BOTON == 4)
				if (CUADRICULA)
					setContentView(R.layout.activity_game_cuadricula);
				else
					setContentView(R.layout.activity_game);
			else if (N_BOTON == 8)
				setContentView(R.layout.activity_game_8);
			else if (N_BOTON == 16)
				setContentView(layout.activity_game_16);
			nBotones = N_BOTON;
		}
		crearColores();
		escogerColor();

		crearArray();
		colorea();

		if (uri == null)
			mp = MediaPlayer.create(this, R.raw.song);
		else
			mp = MediaPlayer.create(this, uri);
		mp.start();
		if (esContrarreloj)
			tiempoActual = SystemClock.uptimeMillis();
		else if (!esMision && TIEMPO > 0) {
			timer = new CountDownTimer(TIEMPO * 1000, 1000) {
				@Override
				public void onTick(long millisUntilFinished) {
					((TextView) findViewById(id.temp)).setText("Tiempo: "
							+ millisUntilFinished / 1000);
				}

				@Override
				public void onFinish() {
					fin = true;
					terminar();
				}
			}.start();
		}
		fin = false;
	}

	@Override
	protected void onPause() {
		super.onPause();
		try {
			mp.stop();
			mp.release();
			mp = null;
		} catch (NullPointerException e) {
		}
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
		finish();
	}

	private void crearColores() {
		colores = new ArrayList<Integer>();
		colores.add(Color.RED);
		colores.add(Color.BLUE);
		colores.add(Color.GREEN);
		colores.add(Color.WHITE);
		colores.add(Color.GRAY);
		colores.add(Color.CYAN);
		colores.add(Color.MAGENTA);
		colores.add(Color.YELLOW);
		colores.add(Color.LTGRAY);
		colores.add(Color.TRANSPARENT);
	}

	private void crearArray() {
		botones = new ImageButton[nBotones];

		if (esMision) {
			for (int i = 0; i < nBotones; i++) {
				botones[i] = (ImageButton) findViewById(i + 1);
				botones[i].setOnClickListener(this);
			}
		} else {
			for (int i = 0; i < nBotones; i++)
				botones[i] = (ImageButton) findViewById(ids[i]);
		}
	}

	private void escogerColor() {
		colorEscogido = colores.get(rnd.nextInt(colores.size()));
		colores.remove(colorEscogido);
		((TextView) findViewById(id.temp)).setBackgroundColor(colorEscogido);
	}

	private void colorea() {
		boton = botones[rnd.nextInt(nBotones)];
		boton.setBackgroundColor(colorEscogido);
	}

	@Override
	public void onClick(View v) {
		if (!fin) {
			ImageButton b = (ImageButton) v;
			if (b.equals(boton)) {
				puntActual++;
				b.setBackgroundColor(Color.rgb(rnd.nextInt(256),
						rnd.nextInt(256), rnd.nextInt(256)));
				if (DIFICIL && rnd.nextInt(50) > dificultad--) {
					colores.add(colorEscogido);
					escogerColor();
				}
				colorea();

				if (esContrarreloj) {
					((TextView) findViewById(id.temp)).setText("Quedan: "
							+ (PUNTMAX - puntActual));
					if (puntActual == PUNTMAX) {
						fin = true;
						tiempoActual = SystemClock.uptimeMillis()
								- tiempoActual;
						terminar();
					}
				}
			} else {
				fin = true;
				if (EPICMODE)
					boton.setImageBitmap(foto);
				findViewById(android.R.id.content).startAnimation(
						AnimationUtils.loadAnimation(this, R.anim.animacion));
				terminar();
			}
		}
	}

	public void check_punt(View view) {
		if (!fin) {
			ImageButton b = (ImageButton) view;
			if (b.equals(boton)) {
				puntActual++;
				b.setBackgroundColor(Color.rgb(rnd.nextInt(256),
						rnd.nextInt(256), rnd.nextInt(256)));
				if (DIFICIL && rnd.nextInt(50) > dificultad--) {
					colores.add(colorEscogido);
					escogerColor();
				}
				colorea();

				if (esContrarreloj) {
					((TextView) findViewById(id.temp)).setText("Quedan: "
							+ (PUNTMAX - puntActual));
					if (puntActual == PUNTMAX) {
						fin = true;
						tiempoActual = SystemClock.uptimeMillis()
								- tiempoActual;
						terminar();
					}
				}
			} else {
				fin = true;
				if (EPICMODE)
					boton.setImageBitmap(foto);
				findViewById(android.R.id.content).startAnimation(
						AnimationUtils.loadAnimation(this, R.anim.animacion));
				terminar();
			}
		}
	}

	private void terminar() {
		((TextView) findViewById(id.temp)).setText("¡Se acabó!");
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
		SharedPreferences set = getSharedPreferences("puntos", 0);

		if (esMision)
			set.edit()
					.putString(getIntent().getIntExtra("mision", -1) + "",
							puntActual + "").commit();

		else if (esContrarreloj
				&& puntActual == PUNTMAX
				&& set.getLong(N_BOTON + "-" + PUNTMAX, Long.MAX_VALUE) > tiempoActual)
			set.edit().putLong(N_BOTON + "-" + PUNTMAX, tiempoActual).commit();

		else if (set.getInt(N_BOTON + "-" + TIEMPO, 0) < puntActual)
			set.edit().putInt(N_BOTON + "-" + TIEMPO, puntActual).commit();
		checkRetos();
		hand.postDelayed(this, 2000);
	}

	private void checkRetos() {
		if (esContrarreloj && puntActual == PUNTMAX && tiempoActual < 6000)
			getSharedPreferences("retos", 0).edit().putInt("nMisiones", 4)
					.commit();
		if (esMision && getIntent().getIntExtra("mision", -1) == raw.m4
				&& puntActual > 20)
			getSharedPreferences("retos", 0).edit().putInt("nMisiones", 5)
					.commit();
	}

	@Override
	public void run() {
		finish();
	}
}
