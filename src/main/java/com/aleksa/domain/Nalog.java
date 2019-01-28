/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksa.domain;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Aleksa Dencic
 */
public class Nalog extends Model {
    
    private int sifraNaloga;
    private Date datum;

    public Nalog() {
    }

    public Nalog(int sifraNaloga, Date datum) {
        this.sifraNaloga = sifraNaloga;
        this.datum = datum;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getSifraNaloga() {
        return sifraNaloga;
    }

    public void setSifraNaloga(int sifraNaloga) {
        this.sifraNaloga = sifraNaloga;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.sifraNaloga;
        hash = 17 * hash + Objects.hashCode(this.datum);
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
        final Nalog other = (Nalog) obj;
        if (this.sifraNaloga != other.sifraNaloga) {
            return false;
        }
        if (!Objects.equals(this.datum, other.datum)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return sifraNaloga + "";
    }

    @Override
    public String insert() {
        return "INSERT INTO nalog(datum) VALUES ('" + getDatum()+ "')";
    }

    @Override
    public String update() {
        return "UPDATE nalog SET datum = '" + getDatum()+ "' WHERE  sifraNaloga = " + getSifraNaloga();
    }

    @Override
    public String delete() {
        return "DELETE FROM nalog WHERE sifraNaloga = " + getSifraNaloga();
    }

    @Override
    public String findAll() {
        return "SELECT * FROM nalog";
    }

    @Override
    public String details() {
        return "SELECT * FROM nalog WHERE sifraNaloga = " + getSifraNaloga();
    }
    
    
    
}
