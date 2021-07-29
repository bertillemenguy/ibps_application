package com.example.appbertille;

import java.io.Serializable;

public class Bac implements Serializable {

    private final String date;
    private final String Id;
    private final String Actions;
    private final String NouveauBac;
    private final String Lignee;
    private final String Bac;
    private final String Lignee2;
    private final String Bac2;
    private final String remarque;
    private final String remarque2;
    private final String Bac2b;
    private final String remarque3;
    private final String Bac3;
    private final String Remarque4;
    private final String Bac4;
    private final String Bac1b;
    private final String Lot;
    private final String Lot2;
    private final String SiTraite;


    private boolean active;


    public Bac(String Date, String Id,String Actions,String NouveauBac,String Lignee,String Bac,String Lignee2,String Bac2, String Bac1b, String remarque,String remarque2,String Bac2b,String remarque3,String Bac3,String remarque4,String Bac4,String Lot,String Lot2,String SItraite){
        this.date=Date;
        this.Id=Id;
        this.Actions=Actions;
        this.NouveauBac=NouveauBac;
        this.Lignee=Lignee;
        this.Bac=Bac;
        this.Bac1b=Bac1b;
        this.Lignee2= Lignee2;
        this.Bac2= Bac2;
        this.remarque= remarque;
        this.remarque2= remarque2;
        this.Bac2b= Bac2b;
        this.remarque3= remarque3;
        this.Bac3= Bac3;
        this.Remarque4= remarque4;
        this.Bac4= Bac4;
        this.Lot= Lot;
        this.Lot2= Lot2;
        this.SiTraite= SItraite;

    }



    @Override
    public String toString() {
        return "{"+"Date='"+date+"'"+", Actions='"+Actions+"'"+", Lignee='"+Lignee+"'}";
    }

    public String getDate(){
        return date;
    }
    public String getId(){
        return Id;
    }
    public String getActions(){
        return Actions;
    }
    public String getNouveauBac(){ return NouveauBac; }
    public String getLignee(){ return Lignee; }
    public String getBac(){
        return Bac;
    }
    public String getLignee2(){
        return Lignee2;
    }
    public String getBac2(){
        return Bac2;
    }
    public String getRemarque(){ return remarque; }
    public String getRemarque2(){ return remarque2; }
    public String getBac1b(){ return Bac1b; }
    public String getBac2b(){ return Bac2b; }
    public String getRemarque3(){ return remarque3; }
    public String getBac3(){ return Bac3; }
    public String getRemarque4(){ return Remarque4; }
    public String getBac4(){ return Bac4; }
    public String getLot(){ return Lot; }
    public String getLot2(){ return Lot2; }
    public String getSiTraite(){ return SiTraite; }


    public boolean isActive(boolean active){
        return active;
    }

    public void setActive(boolean active){
        this.active=active;
    }

}
