package com.example.endpointapp;

import java.io.Serializable;

public class Tache implements Serializable {

    private final String Jour;
    private final String ID;
    private final String Tache;
    private final String SiFait;


    private boolean active;


    public Tache(String Jour, String Id, String Tache, String SiFait){
        this.Jour=Jour;
        this.ID=Id;
        this.Tache=Tache;
        this.SiFait=SiFait;
        this.active=true;
    }




    public Tache(String Jour, String Id, String Tache, String SiFait, boolean active){
        this.Jour=Jour;
        this.ID=Id;
        this.Tache=Tache;
        this.SiFait=SiFait;
        this.active=active;
    }


    @Override
    public String toString() {
        return "{"+"Jour='"+Jour+"'"+", ID='"+ID+"'"+", Tache='"+Tache+"'"+", Etat='"+SiFait+"'}";
    }

    public String getJour(){
        return Jour;
    }
    public String getID(){
        return ID;
    }
    public String getTache(){
        return Tache;
    }
    public String getSiFait(){
        return SiFait;
    }


    public boolean isActive(boolean active){
        return active;
    }

    public void setActive(boolean active){
        this.active=active;
    }


}
