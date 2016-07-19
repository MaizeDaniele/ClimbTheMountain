package com.example.android.climbthemountain.CustomDialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.android.climbthemountain.R;

/**
 * Created by maizedaniele on 19/07/16.
 */
public class TutorialDialogFragment extends DialogFragment {



    public static TutorialDialogFragment newInstance(){

        TutorialDialogFragment dialogFragment = new TutorialDialogFragment();
        return dialogFragment;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());

        dialogBuilder.setPositiveButton("ok", null);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.tutorial_dialogfragment_layout, null);
        dialogBuilder.setView(v);

        dialogBuilder.setPositiveButton("ok", null);

        return dialogBuilder.create();
    }
}