package com.ejemplo.botones.fragments;

import com.ejemplo.botones.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AyudaFragment extends Fragment {
	private int i;

	public AyudaFragment() {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		i = getArguments().getInt("i", 0);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.tiempo, container, false);
		((TextView) v.findViewById(R.id.temp)).setText(i);
		((TextView) v.findViewById(R.id.temp)).setTextSize(20f);

		return v;
	}
}