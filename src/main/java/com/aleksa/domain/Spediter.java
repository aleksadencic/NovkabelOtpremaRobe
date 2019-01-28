/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksa.domain;

import java.util.Objects;

/**
 *
 * @author Aleksa Dencic
 */
public class Spediter extends Model {
    
    private int sifraSpeditera;
    private String nazivSpeditera;

    public Spediter() {
    }

    public Spediter(int sifraSpeditera, String nazivSpeditera) {
        this.sifraSpeditera = sifraSpeditera;
        this.nazivSpeditera = nazivSpeditera;
    }

    public String getNazivSpeditera() {
        return nazivSpeditera;
    }

    public void setNazivSpeditera(String nazivSpeditera) {
        this.nazivSpeditera = nazivSpeditera;
    }

    public int getSifraSpeditera() {
        return sifraSpeditera;
    }

    public void setSifraSpeditera(int sifraSpeditera) {
        this.sifraSpeditera = sifraSpeditera;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + this.sifraSpeditera;
        hash = 13 * hash + Objects.hashCode(this.nazivSpeditera);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Spediter other = (Spediter) obj;
        if (this.sifraSpeditera != other.sifraSpeditera) {
            return false;
        }
        if (!Objects.equals(this.nazivSpeditera, other.nazivSpeditera)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return sifraSpeditera + ". " + nazivSpeditera;
    }

    @Override
    public String insert() {
        return "INSERT INTO spediter(nazivSpeditera) VALUES ('" + getNazivSpeditera() + "')";
    }

    @Override
    public String update() {
        return "UPDATE spediter SET nazivSpeditera = '" + getNazivSpeditera() + "' WHERE  sifraSpeditera = " + getSifraSpeditera();
    }

    @Override
    public String delete() {
        return "DELETE FROM spediter WHERE sifraSpeditera = " + getSifraSpeditera();
    }

    @Override
    public String findAll() {
        return "SELECT * FROM spediter";
    }

    @Override
    public String details() {
        return "SELECT * FROM spediter WHERE sifraSpeditera = " + getSifraSpeditera();
    }
    
    
    
}
