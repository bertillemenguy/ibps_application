package com.example.appbertille;

public class Accouplement {

    private final String Date;
    private final String Operateur;
    private final String NombreAccouplements;
    private final String Couleur1;
    private final String Couleur2;
    private final String NbMal;
    private final String LotMal;
    private final String BacMal;
    private final String LigneeMal;
    private final String AgeMal;
    private final String NbFemelle;
    private final String LotFemelle;
    private final String BacFemelle;
    private final String LigneeFemelle;
    private final String AgeFemelle;
    private final String Id;
    private final String KeyMale;
    private final String KeyFemelle;



    public Accouplement(String Date, String Operateur, String NombreAccouplements, String Couleur1, String Couleur2, String NbMal, String LotMal, String BacMal, String LigneeMal, String AgeMal, String NbFemelle, String LotFemelle, String BacFemelle, String LigneeFemelle, String AgeFemelle, String Id, String KeyMale, String KeyFemelle){
        this.Date=Date;
        this.Operateur=Operateur;
        this.NombreAccouplements=NombreAccouplements;
        this.Couleur1=Couleur1;
        this.Couleur2=Couleur2;
        this.NbMal=NbMal;
        this.LotMal=LotMal;
        this.BacMal=BacMal;
        this.LigneeMal=LigneeMal;
        this.AgeMal=AgeMal;
        this.NbFemelle=NbFemelle;
        this.LotFemelle=LotFemelle;
        this.BacFemelle=BacFemelle;
        this.LigneeFemelle=LigneeFemelle;
        this.AgeFemelle=AgeFemelle;
        this.Id=Id;
        this.KeyMale=KeyMale;
        this.KeyFemelle=KeyFemelle;
    }

    @Override
    public String toString() {
        return "Accouplement{" +
                "Date='" + Date + '\'' +
                ", Operateur='" + Operateur + '\'' +
                ", NombreAccouplements='" + NombreAccouplements + '\'' +
                ", Couleur1='" + Couleur1 + '\'' +
                ", Couleur2='" + Couleur2 + '\'' +
                ", NbMal='" + NbMal + '\'' +
                ", LotMal='" + LotMal + '\'' +
                ", BacMal='" + BacMal + '\'' +
                ", LigneeMal='" + LigneeMal + '\'' +
                ", AgeMal='" + AgeMal + '\'' +
                ", NbFemelle='" + NbFemelle + '\'' +
                ", LotFemelle='" + LotFemelle + '\'' +
                ", BacFemelle='" + BacFemelle + '\'' +
                ", LigneeFemelle='" + LigneeFemelle + '\'' +
                ", AgeFemelle='" + AgeFemelle + '\'' +
                ", Id='" + Id + '\'' +
                '}';
    }

    public String getDate() {
        return Date;
    }

    public String getOperateur() {
        return Operateur;
    }

    public String getNombreAccouplements() {
        return NombreAccouplements;
    }

    public String getCouleur1() {
        return Couleur1;
    }

    public String getCouleur2() {
        return Couleur2;
    }

    public String getNbMal() {
        return NbMal;
    }

    public String getLotMal() {
        return LotMal;
    }

    public String getBacMal() {
        return BacMal;
    }

    public String getLigneeMal() {
        return LigneeMal;
    }

    public String getAgeMal() {
        return AgeMal;
    }


    public String getNbFemelle() {
        return NbFemelle;
    }

    public String getLotFemelle() {
        return LotFemelle;
    }

    public String getBacFemelle() {
        return BacFemelle;
    }

    public String getLigneeFemelle() {
        return LigneeFemelle;
    }

    public String getAgeFemelle() {
        return AgeFemelle;
    }

    public String getId() {
        return Id;
    }

    public String getKeyMale() {
        return KeyMale;
    }

    public String getKeyFemelle() {
        return KeyFemelle;
    }


}
