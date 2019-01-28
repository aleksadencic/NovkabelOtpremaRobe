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
public class Magacin extends Model {
    
    private int sifraMagacina;
    private String nazivMagacina;

    public Magacin() {
    }

    public Magacin(int sifraMagacina, String nazivMagacina) {
        this.sifraMagacina = sifraMagacina;
        this.nazivMagacina = nazivMagacina;
    }

    public String getNazivMagacina() {
        return nazivMagacina;
    }

    public void setNazivMagacina(String nazivMagacina) {
        this.nazivMagacina = nazivMagacina;
    }

    public int getSifraMagacina() {
        return sifraMagacina;
    }

    public void setSifraMagacina(int sifraMagacina) {
        this.sifraMagacina = sifraMagacina;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.sifraMagacina;
        hash = 67 * hash + Objects.hashCode(this.nazivMagacina);
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
        final Magacin other = (Magacin) obj;
        if (this.sifraMagacina != other.sifraMagacina) {
            return false;
        }
        if (!Objects.equals(this.nazivMagacina, other.nazivMagacina)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nazivMagacina;
    }

    @Override
    public String insert() {
        return "INSERT INTO magacin(nazivMagacina) VALUES ('" + getNazivMagacina()+ "')";
    }

    @Override
    public String update() {
        return "UPDATE magacin SET nazivMagacina = '" + getNazivMagacina() + "' WHERE  sifraMagacina = " + getSifraMagacina();
    }

    @Override
    public String delete() {
        return "DELETE FROM magacin WHERE sifraMagacina = " + getSifraMagacina();
    }

    @Override
    public String findAll() {
        return "SELECT * FROM magacin";
    }

    @Override
    public String details() {
        return "SELECT * FROM magacin WHERE sifraMagacina = " + getSifraMagacina();
    }
    
    
    
    
}
