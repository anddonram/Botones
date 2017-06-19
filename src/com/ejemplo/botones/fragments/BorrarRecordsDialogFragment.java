package com.ejemplo.botones.fragments;

import com.ejemplo.botones.R.id;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TextView;

public class BorrarRecordsDialogFragment extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new AlertDialog.Builder(getActivity())
				.setMessage("¿Borrar records?")
				.setPositiveButton("Borrar",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int i) {
								getActivity().getSharedPreferences("puntos", 0)
										.edit().clear().commit();
								((TextView) getActivity().findViewById(
										id.textView1))
										.setText("Puntuacion: " + 0);
								getActivity().getSharedPreferences("retos", 0)
								.edit().clear().commit();
						
							}
						})
				.setNegativeButton("Cancelar",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
							}
						}).create();
	}

}
