package com.example.android.climbthemountain.CustomAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.climbthemountain.Login;
import com.example.android.climbthemountain.R;
import com.example.android.climbthemountain.RegisterSessionExamModify;
import com.example.android.climbthemountain.user_data.ExamData;
import com.example.android.climbthemountain.user_data.UserData;

import java.util.ArrayList;

/**
 * Created by maizedaniele on 13/07/16.
 */
public class ExamAdapter1 extends BaseAdapter {

    private ArrayList<ExamData> listaEsami;
    private Context context;
    private ExamData esame;
    private int position;
    private UserData userData;
    private boolean isSummary;

    public ExamAdapter1(UserData userData, Context context, boolean isSummary){
        this.userData = userData;
        this.context = context;
        this.isSummary = isSummary;
        this.listaEsami = this.userData.userExams;
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
        esame = listaEsami.get(position);

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
        bottone.setVisibility(View.GONE);


        return v;
    }



}
