package com.example.endpointapp;

public class PoissonMort {

    private final String Date;
    private final String Age;
    private final String Id;
    private final String Bac;
    private final String Lignee;
    private final String Lot;
    private final String Responsable;
    private final String SiAccouplement;
    private final String SumMorts;
    private final String Key;


    public PoissonMort(String Id,String Date, String Age, String Bac, String Lignee, String Lot,String Responsable, String SiAccouplement, String SumMorts, String Key){
        this.Id=Id;
        this.Date=Date;
        this.Responsable=Responsable;
        this.Bac=Bac;
        this.Lignee=Lignee;
        this.Age=Age;
        this.Key=Key;
        this.Lot=Lot;
        this.SiAccouplement=SiAccouplement;
        this.SumMorts=SumMorts;

    }


    @Override
    public String toString() {
        return "{"+"Lot='"+Lot+"'"+", Responsable='"+Responsable+"'"+", Bac='"+Bac+"'"+", Lignee='"+Lignee+"'"+", Age='"+Age+"'"+", Key='"+Key+"'}";
    }

    public String getId(){
        return Id;
    }
    public String getDate(){
        return Date;
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
    public String getSiAccouplement(){
        return SiAccouplement;
    }
    public String getSumMorts(){
        return SumMorts;
    }



}
