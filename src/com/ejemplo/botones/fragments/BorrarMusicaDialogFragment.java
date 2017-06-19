package com.ejemplo.botones.fragments;

import com.ejemplo.botones.GameActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class BorrarMusicaDialogFragment extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new AlertDialog.Builder(getActivity())
				.setMessage("¿Buscar música?")
				.setPositiveButton("Buscar",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Intent intent = new Intent(
										Intent.ACTION_GET_CONTENT);
								intent.setType("audio/*");
								if (intent.resolveActivity(getActivity()
										.getPackageManager()) != null)
									getActivity().startActivityForResult(
											intent, 1);
							}
						})
				.setNegativeButton("Borrar actual",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								GameActivity.uri = null;
							}
						})
				.setNeutralButton("Cancelar",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
							}
						}).create();
	}

}
