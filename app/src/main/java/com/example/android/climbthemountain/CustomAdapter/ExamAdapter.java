package com.example.android.climbthemountain.CustomAdapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.climbthemountain.R;
import com.example.android.climbthemountain.RegisterSessionExamModify;
import com.example.android.climbthemountain.user_data.ExamData;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by maizedaniele on 13/07/16.
 */
public class ExamAdapter extends BaseAdapter {

    ArrayList<ExamData> listaEsami;
    Context context;

    public ExamAdapter(ArrayList<ExamData> listaEsami, Context context){
        this.listaEsami = listaEsami;
        this.context = context;
    }

    @Override
    public int getCount(){
        return listaEsami.size();
    }

    @Override
    public ExamData getItem(int position){
        return listaEsami.get(position);
    }

    @Override
    public long getItemId(int position){
        return listaEsami.get(position).hashCode();
    }

    @Override
    public View getView(int position, View v, ViewGroup vg){


        //Se v esiste già non devo rieseguire l'inflate
        if(v == null){
            v = LayoutInflater.from(context).inflate(R.layout.row_model_exam, null);
        }

        //Recupero dalla listaEsami l'esame da raffigurare
        final ExamData esame = listaEsami.get(position);




        //Riempio la View con i dati passati nella listaEsami
        TextView txt = (TextView) v.findViewById(R.id.nomeEsame_TextView);
        txt.setText(esame.getNome());

        txt = (TextView) v.findViewById(R.id.cfuEsame_TextView);
        txt.setText(String.format("%d", esame.getCfu()));

        txt = (TextView) v.findViewById(R.id.dataEsame_TextView);
        txt.setText(String.format("%d / %d / %d", esame.getGiorno(), esame.getMese(), esame.getAnno()));

        txt = (TextView) v.findViewById(R.id.coloreEsame_TextView);
        txt.setBackgroundColor(Color.parseColor(esame.getColore()));


        //Imposto l'evento OnClick del bottone
        //Avvierà l'activity RegisterSessionExamModify in cui sarà possibile anche rimuovere l'esame oltre a modificarlo
        Button bottone = (Button) v.findViewById(R.id.modifica_Button);


        final Intent intent = new Intent(v.getContext(), RegisterSessionExamModify.class);
        intent.putExtra("position", position);
        intent.putExtra("esame", esame);

        bottone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Devo avviare l'activity RegisterSessionExamModify
                //Devo passare i dati dell'esame e anche la position
                context.startActivity(intent);
            }
        });

        return v;
    }

}
