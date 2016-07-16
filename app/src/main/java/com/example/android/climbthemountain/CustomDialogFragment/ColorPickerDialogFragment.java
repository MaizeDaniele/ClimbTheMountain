package com.example.android.climbthemountain.CustomDialogFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.android.climbthemountain.CustomAdapter.ColorPickerAdapter;
import com.example.android.climbthemountain.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by maizedaniele on 14/07/16.
 */
public class ColorPickerDialogFragment extends DialogFragment {

    private GridView colorPickerGrid;
    private ColorPickerAdapter adattatore;
    private ArrayList<String> listaColori = new ArrayList<>();
    private ImageView immagine;
    private String colore;
    private LayoutInflater inflater;


    public static ColorPickerDialogFragment newInstance(){
        ColorPickerDialogFragment colorPicker = new ColorPickerDialogFragment();

        return colorPicker;
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){



        //Inizializza l'arrayList dei colori
        inizializzaListaColori();




        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());

        inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.color_picker_layout, null);


        dialogBuilder.setView(v);
        dialogBuilder.setPositiveButton("conferma", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if(colore == null){
                    //imposto il nero come colore base
                    interfaccia.onPositiveButtonClick("#000000");
                }else {
                    interfaccia.onPositiveButtonClick(colore);
                }
            }
        });
        dialogBuilder.setNegativeButton("annulla", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if(immagine != null){
                    immagine.setVisibility(View.INVISIBLE);
                }
            }
        });
        dialogBuilder.setMessage("Seleziona Colore:");


        colorPickerGrid = (GridView) v.findViewById(R.id.ColorPickerGridView);

        adattatore = new ColorPickerAdapter(v.getContext(), listaColori);

        colorPickerGrid.setAdapter(adattatore);

        colorPickerGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(immagine != null){
                    immagine.setVisibility(View.INVISIBLE);
                }

                immagine = (ImageView) view.findViewById(R.id.confermaColorPickerSelection);
                immagine.setVisibility(View.VISIBLE);


                colore = listaColori.get(position);
            }
        });

        return dialogBuilder.create();
    }





    public interface interazioneColorPicker{
        public void onPositiveButtonClick(String colore);
    }

    interazioneColorPicker interfaccia;

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        //Verifica che l'activity implementi l'interfaccia
        try{
            interfaccia = (interazioneColorPicker) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }

    }

    private void inizializzaListaColori(){
        this.listaColori.add("#F44336");
        this.listaColori.add("#E91E63");
        this.listaColori.add("#9C27B0");
        this.listaColori.add("#673AB7");
        this.listaColori.add("#3F51B5");
        this.listaColori.add("#2196F3");
        this.listaColori.add("#03A9F4");
        this.listaColori.add("#00BCD4");
        this.listaColori.add("#009688");
        this.listaColori.add("#4CAF50");
        this.listaColori.add("#8BC34A");
        this.listaColori.add("#CDDC39");
        this.listaColori.add("#FFEB3B");
        this.listaColori.add("#FFC107");
        this.listaColori.add("#FF9800");
        this.listaColori.add("#FF5722");
        this.listaColori.add("#795548");
        this.listaColori.add("#9E9E9E");
        this.listaColori.add("#607D8B");

    }


}
