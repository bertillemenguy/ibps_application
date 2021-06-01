package com.example.endpointapp;

import android.graphics.Path;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Incident implements Serializable {

    private final String Date;
    private final String Operateur;
    private final String Etat;
    private final String eaudeville;
    private final String electricite;
    private final String aircomprime;
    private final String climatisation;
    private final String eaudusysteme;
    private final String systemeaquatique;
    private final String travaux;
    private final String nourrissage;
    private final String key;


    private boolean active;


    public Incident(String Date, String Operateur, String Etat, String eaudeville, String electricite, String aircomprime, String climatisation, String eaudusysteme, String systemeaquatique, String travaux, String nourrissage, String key){
        this.Date=Date;
        this.Operateur=Operateur;
        this.Etat=Etat;
        this.eaudeville=eaudeville;
        this.electricite=electricite;
        this.aircomprime=aircomprime;
        this.climatisation= climatisation;
        this.eaudusysteme= eaudusysteme;
        this.systemeaquatique= systemeaquatique;
        this.travaux= travaux;
        this.nourrissage= nourrissage;
        this.key= key;

    }



    @Override
    public String toString() {
        return "{"+"Date='"+Date+"'"+", Operateur='"+Operateur+"'"+", Etat='"+Etat+"'}";
    }

    public String getDate(){
        return Date;
    }


    public String getOperateur(){
        return Operateur;
    }
    public String getEtat(){
        return Etat;
    }
    public String getEaudeville(){
        return eaudeville;
    }
    public String getElectricite(){ return electricite; }
    public String getAircomprime(){
        return aircomprime;
    }
    public String getClimatisation(){
        return climatisation;
    }
    public String getEaudusysteme(){
        return eaudusysteme;
    }
    public String getSystemeaquatique(){ return systemeaquatique; }
    public String getTravaux(){ return travaux; }
    public String getNourrissage(){ return nourrissage; }
    public String getKey(){ return key; }


    public boolean isActive(boolean active){
        return active;
    }

    public void setActive(boolean active){
        this.active=active;
    }

}
