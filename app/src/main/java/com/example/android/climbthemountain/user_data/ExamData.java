package com.example.android.climbthemountain.user_data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Marco on 05/04/16.
 */
public class ExamData implements Parcelable{

    private String nome;
    private int cfu;
    private int giorno;
    private int mese;
    private int anno;
    private String colore;


    public ExamData(String nome, int cfu, int giorno, int mese, int anno, String colore){
        this.setNome(nome);
        this.setCfu(cfu);
        this.setGiorno(giorno);
        this.setMese(mese);
        this.setAnno(anno);
        this.setColore(colore);
    }

    public ExamData(){
        this.setNome(null);
        this.setCfu(0);
        this.setGiorno(0);
        this.setMese(0);
        this.setAnno(0);
        this.setColore(null);
    }


    protected ExamData(Parcel in) {
        nome = in.readString();
        cfu = in.readInt();
        giorno = in.readInt();
        mese = in.readInt();
        anno = in.readInt();
        colore = in.readString();
    }

    public static final Creator<ExamData> CREATOR = new Creator<ExamData>() {
        @Override
        public ExamData createFromParcel(Parcel in) {
            return new ExamData(in);
        }

        @Override
        public ExamData[] newArray(int size) {
            return new ExamData[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCfu() {
        return cfu;
    }

    public void setCfu(int cfu) {
        this.cfu = cfu;
    }

    public int getGiorno() {
        return giorno;
    }

    public void setGiorno(int giorno) {
        this.giorno = giorno;
    }

    public int getMese() {
        return mese;
    }

    public void setMese(int mese) {
        this.mese = mese;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeInt(cfu);
        dest.writeInt(giorno);
        dest.writeInt(mese);
        dest.writeInt(anno);
        dest.writeString(colore);
    }
}
