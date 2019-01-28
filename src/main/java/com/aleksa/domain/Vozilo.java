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
public class Vozilo extends Model {
    
    private String registarskiBroj;
    private String nazivVozila;
    private Spediter spediter;

    public Vozilo() {
        spediter = new Spediter();
    }

    public Vozilo(String registarskiBroj, String nazivVozila, Spediter spediter) {
        this.registarskiBroj = registarskiBroj;
        this.nazivVozila = nazivVozila;
        this.spediter = spediter;
    }

    public Spediter getSpediter() {
        return spediter;
    }

    public void setSpediter(Spediter spediter) {
        this.spediter = spediter;
    }

    public String getRegistarskiBroj() {
        return registarskiBroj;
    }

    public void setRegistarskiBroj(String registarskiBroj) {
        this.registarskiBroj = registarskiBroj;
    }

    public String getNazivVozila() {
        return nazivVozila;
    }

    public void setNazivVozila(String nazivVozila) {
        this.nazivVozila = nazivVozila;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.registarskiBroj);
        hash = 71 * hash + Objects.hashCode(this.nazivVozila);
        hash = 71 * hash + Objects.hashCode(this.spediter);
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
        final Vozilo other = (Vozilo) obj;
        if (!Objects.equals(this.registarskiBroj, other.registarskiBroj)) {
            return false;
        }
        if (!Objects.equals(this.nazivVozila, other.nazivVozila)) {
            return false;
        }
        if (!Objects.equals(this.spediter, other.spediter)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return registarskiBroj;
    }

    @Override
    public String insert() {
        return "INSERT INTO vozilo VALUES ('" + getRegistarskiBroj() + "', '" + getNazivVozila() + "', " + getSpediter().getSifraSpeditera() + ")";
    }

    @Override
    public String update() {
        return "UPDATE vozilo SET nazivVozila = '" + getNazivVozila() + "', sifraSpeditera = " + getSpediter().getSifraSpeditera() + " WHERE registarskiBroj = '" + getRegistarskiBroj() + "'";
    }

    @Override
    public String delete() {
        return "DELETE FROM vozilo WHERE registarskiBroj = '" + getRegistarskiBroj() + "'";
    }

    @Override
    public String findAll() {
        return "SELECT * FROM vozilo v JOIN spediter s ON (v.sifraSpeditera = s.sifraSpeditera)";
    }

    @Override
    public String details() {
        return "SELECT * FROM vozilo v JOIN spediter s ON (v.sifraSpeditera = s.sifraSpeditera) WHERE registarskiBroj = '" + getRegistarskiBroj() + "'";
    }
    
    
    
}
