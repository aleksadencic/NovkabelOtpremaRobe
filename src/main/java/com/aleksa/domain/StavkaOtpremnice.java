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
public class StavkaOtpremnice extends Model {

    private int rbr;
    private Proizvod proizvod;
    private double kolicina;
    private int status;
    private Otpremnica otpremnica;
    private int updateID;

    public StavkaOtpremnice() {
        proizvod = new Proizvod();
        otpremnica = new Otpremnica();
    }

    public StavkaOtpremnice(int rbr, Proizvod proizvod, double kolicina, int status, Otpremnica otpremnica) {
        this.rbr = rbr;
        this.proizvod = proizvod;
        this.kolicina = kolicina;
        this.status = status;
        this.otpremnica = otpremnica;
    }

    public Otpremnica getOtpremnica() {
        return otpremnica;
    }

    public void setOtpremnica(Otpremnica otpremnica) {
        this.otpremnica = otpremnica;
    }

    public int getRbr() {
        return rbr;
    }

    public void setRbr(int rbr) {
        this.rbr = rbr;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }

    public double getKolicina() {
        return kolicina;
    }

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUpdateID() {
        return updateID;
    }

    public void setUpdateID(int updateID) {
        this.updateID = updateID;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.rbr;
        hash = 53 * hash + Objects.hashCode(this.proizvod);
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.kolicina) ^ (Double.doubleToLongBits(this.kolicina) >>> 32));
        hash = 53 * hash + Objects.hashCode(this.status);
        hash = 53 * hash + Objects.hashCode(this.otpremnica);
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
        final StavkaOtpremnice other = (StavkaOtpremnice) obj;
        if (this.rbr != other.rbr) {
            return false;
        }
        if (Double.doubleToLongBits(this.kolicina) != Double.doubleToLongBits(other.kolicina)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.proizvod, other.proizvod)) {
            return false;
        }
        if (!Objects.equals(this.otpremnica, other.otpremnica)) {
            return false;
        }
        return true;
    }

    @Override
    public String insert() {
        return "INSERT INTO stavka_otpremnice"
                + " VALUES (" + getRbr() + ", "
                + getProizvod().getSifraProizvoda() + ", "
                + getKolicina() + ", "
                + getStatus() + ", "
                + getOtpremnica().getBrojOtpremnice() + ", "
                + getUpdateID() + ")";
    }

    @Override
    public String update() {
        return "UPDATE stavka_otpremnice SET sifraProizvoda = " + getProizvod().getSifraProizvoda()
                + ", kolicina = " + getKolicina()
                + ", status = " + getStatus() + " "
                + "WHERE  rbr = " + getRbr() + " AND brojOtpremnice = " + getOtpremnica().getBrojOtpremnice();
    }

    @Override
    public String delete() {
        return "DELETE FROM stavka_otpremnice WHERE rbr = " + getRbr() + " AND brojOtpremnice = " + getOtpremnica().getBrojOtpremnice();
    }

    @Override
    public String findAll() {
        return "SELECT * FROM stavka_otpremnice";
    }

    @Override
    public String details() {
        return "SELECT * FROM stavka_otpremnice WHERE brojOtpremnice = " + getOtpremnica().getBrojOtpremnice();
    }

}
