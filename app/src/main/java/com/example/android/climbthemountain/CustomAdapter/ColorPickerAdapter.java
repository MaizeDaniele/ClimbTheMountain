package com.example.android.climbthemountain.CustomAdapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import com.example.android.climbthemountain.CustomDialogFragment.ColorPickerDialogFragment;
import com.example.android.climbthemountain.R;

import java.util.ArrayList;

/**
 * Created by maizedaniele on 14/07/16.
 */
public class ColorPickerAdapter extends BaseAdapter {

    ArrayList<String> listColori;
    Context context;

    public ColorPickerAdapter(Context context, ArrayList<String> listaColori){
        this.listColori = listaColori;
        this.context = context;
    }

    @Override
    public int getCount(){
        return listColori.size();
    }

    @Override
    public String getItem(int position){
        return listColori.get(position);
    }

    @Override
    public long getItemId(int position){
        return listColori.get(position).hashCode();
    }

    @Override
    public View getView(int position, View v, ViewGroup vg){

        if(v == null){
            v = LayoutInflater.from(context).inflate(R.layout.color_picker_element, null);
        }

        RelativeLayout elementGridLayout = (RelativeLayout) v.findViewById(R.id.element_grid_layout);
        elementGridLayout.setBackgroundColor(Color.parseColor(listColori.get(position)));

        return v;
    }

}
