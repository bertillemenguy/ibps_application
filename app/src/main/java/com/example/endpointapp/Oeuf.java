package com.example.endpointapp;

public class Oeuf {

    private final String Lot;
    private final String Bac;
    private final String Responsable;
    private final String Lignee;
    private final String Age;
    private final String Key;
    private final Integer Image;
    private Integer Liseret;


    public Poisson(String Lot, String Bac, String Responsable, String Lignee, String Age, String Key, Integer Image, Integer Liseret){
        this.Lot=Lot;
        this.Responsable=Responsable;
        this.Bac=Bac;
        this.Lignee=Lignee;
        this.Age=Age;
        this.Key=Key;
        this.Image=Image;
        this.Liseret=Liseret;

    }


    @Override
    public String toString() {
        return "{"+"Lot='"+Lot+"'"+", Responsable='"+Responsable+"'"+", Bac='"+Bac+"'"+", Lignee='"+Lignee+"'"+", Age='"+Age+"'"+", Key='"+Key+"'}";
    }

    public String getLot(){
        return Lot;
    }
    public String getResponsable(){
        return Responsable;
    }
    public String getBac(){
        return Bac;
    }
    public String getLignee(){
        return Lignee;
    }
    public String getAge(){
        return Age;
    }
    public String getKey(){
        return Key;
    }
    public Integer getImage(){ return Image; }
    public Integer getColor(){ return Liseret; }


    public void setLiseret(Integer Liseret){ this.Liseret=Liseret; }


}
