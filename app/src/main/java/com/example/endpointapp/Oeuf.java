package com.example.endpointapp;

public class Oeuf {

    String Date;
    String operateur;
    String LigneeM;
    String LigneeF;
    String BacM;
    String BacF;
    String NbBac;
    String NbMale;
    String NbFemelle;
    String AgeM;
    String AgeF;
    String quantite;
    String qualite;
    String LotM;
    String LotF;
    String NbMalesFeconde;
    String NbfemellesFeconde;
    String Id;

    public Oeuf(String Date, String operateur, String ligneeM, String ligneeF, String bacM, String bacF, String nbBac, String nbMale, String nbFemelle, String ageM, String ageF, String quantite, String qualite, String lotM, String lotF, String nbMalesFeconde, String nbfemellesFeconde, String Id) {
        this.Id=Id;
        this.Date=Date;
        this.operateur = operateur;
        LigneeM = ligneeM;
        LigneeF = ligneeF;
        BacM = bacM;
        BacF = bacF;
        NbBac = nbBac;
        NbMale = nbMale;
        NbFemelle = nbFemelle;
        AgeM = ageM;
        AgeF = ageF;
        this.quantite = quantite;
        this.qualite = qualite;
        LotM = lotM;
        LotF = lotF;
        NbMalesFeconde = nbMalesFeconde;
        NbfemellesFeconde = nbfemellesFeconde;
    }

    public String getDate() {
        return Date;
    }

    public String getId() {
        return Id;
    }

    public String getLotF() {
        return LotF;
    }

    public String getNbMalesFeconde() {
        return NbMalesFeconde;
    }

    public String getNbfemellesFeconde() {
        return NbfemellesFeconde;
    }

    public String getOperateur() {
        return operateur;
    }

    public String getLigneeM() {
        return LigneeM;
    }

    public String getLigneeF() {
        return LigneeF;
    }

    public String getBacM() {
        return BacM;
    }

    public String getBacF() {
        return BacF;
    }

    public String getNbBac() {
        return NbBac;
    }

    public String getNbMale() {
        return NbMale;
    }

    public String getNbFemelle() {
        return NbFemelle;
    }

    public String getAgeM() {
        return AgeM;
    }

    public String getAgeF() {
        return AgeF;
    }

    public String getQuantite() {
        return quantite;
    }

    public String getQualite() {
        return qualite;
    }

    public String getLotM() {
        return LotM;
    }
}
