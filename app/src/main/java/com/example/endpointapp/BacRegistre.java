package com.example.endpointapp;

public class BacRegistre {

    private String num;
    private String operateur;
    private String date_naissance;
    private String Key;
    private String date_lavage;
    private boolean isSelected = false;


    public BacRegistre(String num, String operateur, String date_naissance, String key, String date_lavage) {
        this.num = num;
        this.operateur = operateur;
        this.date_lavage = date_lavage;
        this.Key = key;
        this.date_naissance = date_naissance;

    }

    public String getNum() {
        return num;
    }

    public String getOperateur() {
        return operateur;
    }

    public String getDateNaissance() {
        return date_naissance;
    }

    public String getKey() {
        return Key;
    }

    public String getDateLavage() {
        return date_lavage;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSelected() {
        return isSelected;
    }

}
