package com.example.appbertille;

public class Souffrance {

    String Date;

    String Age;
    String Bac;
    String Lignee;
    String Lot;
    String Responsable;
    String Euthanasie;
    String Isolement;
    String Surveillance;
    String Aucune_action_a_mener;
    String PoissonSouffrance;
    String Id;

    @Override
    public String toString() {
        return "Souffrance{" +
                "Date='" + Date + '\'' +
                ", Age='" + Age + '\'' +
                ", Bac='" + Bac + '\'' +
                ", Lignee='" + Lignee + '\'' +
                ", Lot='" + Lot + '\'' +
                ", Responsable='" + Responsable + '\'' +
                ", Euthanasie='" + Euthanasie + '\'' +
                ", Isolement='" + Isolement + '\'' +
                ", Surveillance='" + Surveillance + '\'' +
                ", Aucune_action_a_mener='" + Aucune_action_a_mener + '\'' +
                ", PoissonSouffrance='" + PoissonSouffrance + '\'' +
                ", Id='" + Id + '\'' +
                '}';
    }

    public Souffrance(String date, String age, String bac, String lignee, String lot, String responsable, String euthanasie, String isolement, String surveillance, String aucune_action_a_mener, String poissonSouffrance, String id) {
        Date = date;
        Age = age;
        Bac = bac;
        Lignee = lignee;
        Lot = lot;
        Responsable = responsable;
        Euthanasie = euthanasie;
        Isolement = isolement;
        Surveillance = surveillance;
        Aucune_action_a_mener = aucune_action_a_mener;
        PoissonSouffrance = poissonSouffrance;
        Id = id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getBac() {
        return Bac;
    }

    public void setBac(String bac) {
        Bac = bac;
    }

    public String getLignee() {
        return Lignee;
    }

    public void setLignee(String lignee) {
        Lignee = lignee;
    }

    public String getLot() {
        return Lot;
    }

    public void setLot(String lot) {
        Lot = lot;
    }

    public String getResponsable() {
        return Responsable;
    }

    public void setResponsable(String responsable) {
        Responsable = responsable;
    }

    public String getEuthanasie() {
        return Euthanasie;
    }

    public void setEuthanasie(String euthanasie) {
        Euthanasie = euthanasie;
    }

    public String getIsolement() {
        return Isolement;
    }

    public void setIsolement(String isolement) {
        Isolement = isolement;
    }

    public String getSurveillance() {
        return Surveillance;
    }

    public void setSurveillance(String surveillance) {
        Surveillance = surveillance;
    }

    public String getAucune_action_a_mener() {
        return Aucune_action_a_mener;
    }

    public void setAucune_action_a_mener(String aucune_action_a_mener) {
        Aucune_action_a_mener = aucune_action_a_mener;
    }

    public String getPoissonSouffrance() {
        return PoissonSouffrance;
    }

    public void setPoissonSouffrance(String poissonSouffrance) {
        PoissonSouffrance = poissonSouffrance;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
