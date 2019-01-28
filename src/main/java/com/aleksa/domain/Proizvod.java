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
public class Proizvod extends Model {
    
    private int sifraProizvoda;
    private String nazivProizvoda;

    public Proizvod() {
    }

    public Proizvod(int sifraProizvoda, String nazivProizvoda) {
        this.sifraProizvoda = sifraProizvoda;
        this.nazivProizvoda = nazivProizvoda;
    }

    public String getNazivProizvoda() {
        return nazivProizvoda;
    }

    public void setNazivProizvoda(String nazivProizvoda) {
        this.nazivProizvoda = nazivProizvoda;
    }

    public int getSifraProizvoda() {
        return sifraProizvoda;
    }

    public void setSifraProizvoda(int sifraProizvoda) {
        this.sifraProizvoda = sifraProizvoda;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.sifraProizvoda;
        hash = 23 * hash + Objects.hashCode(this.nazivProizvoda);
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
        final Proizvod other = (Proizvod) obj;
        if (this.sifraProizvoda != other.sifraProizvoda) {
            return false;
        }
        if (!Objects.equals(this.nazivProizvoda, other.nazivProizvoda)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nazivProizvoda;
    }
    
    @Override
    public String insert() {
        return "INSERT INTO proizvod(nazivProizvoda) VALUES ('" + getNazivProizvoda()+ "')";
    }

    @Override
    public String update() {
        return "UPDATE proizvod SET nazivProizvoda = '" + getNazivProizvoda() + "' WHERE  sifraProizvoda = " + getSifraProizvoda();
    }

    @Override
    public String delete() {
        return "DELETE FROM proizvod WHERE sifraProizvoda = " + getSifraProizvoda();
    }

    @Override
    public String findAll() {
        return "SELECT * FROM proizvod";
    }

    @Override
    public String details() {
        return "SELECT * FROM proizvod WHERE sifraProizvoda = " + getSifraProizvoda();
    }
    
    
    
}
