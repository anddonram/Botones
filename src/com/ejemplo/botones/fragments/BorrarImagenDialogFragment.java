package com.ejemplo.botones.fragments;

import com.ejemplo.botones.GameActivity;
import com.ejemplo.botones.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.ImageView;

public class BorrarImagenDialogFragment extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new AlertDialog.Builder(getActivity())
				.setMessage("¿Buscar imagen?")
				.setPositiveButton("Buscar",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Intent intent = new Intent(
										Intent.ACTION_GET_CONTENT);
								intent.setType("image/*");
								if (intent.resolveActivity(getActivity()
										.getPackageManager()) != null)
									getActivity().startActivityForResult(
											intent, 2);

							}
						})
				.setNegativeButton("Borrar actual",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								GameActivity.foto = null;
								((ImageView) getActivity().findViewById(
										R.id.foto)).setImageBitmap(null);

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
