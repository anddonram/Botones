package com.ejemplo.botones.fragments;

import com.ejemplo.botones.GameActivity;
import com.ejemplo.botones.R.id;
import com.ejemplo.botones.R.layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MisionFragment extends Fragment implements OnClickListener {
	private int texto;
	private int mision;
	private String record;

	public MisionFragment() {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		texto = getArguments().getInt("texto");
		mision = getArguments().getInt("mision");
		record = getActivity().getSharedPreferences("puntos", 0).getString(
				mision + "", "Nunca");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(layout.mision, container, false);
		((TextView) v.findViewById(id.mision)).setText(texto);
		v.findViewById(id.empezarMision).setOnClickListener(this);
		((TextView) v.findViewById(id.records)).setText("Último intento: "
				+ record);
		return v;
	}

	@Override
	public void onClick(View v) {
		startActivity(new Intent(getActivity(), GameActivity.class).putExtra(
				"mision", mision));
		getActivity().finish();
	}
}
