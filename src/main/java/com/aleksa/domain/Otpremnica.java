/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksa.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Aleksa Dencic
 */
public class Otpremnica extends Model {
    
    private int brojOtpremnice;
    private Nalog nalog;
    private Magacin magacin;
    private Date datumPro;
    private Date datumIzd;
    private List<StavkaOtpremnice> stavkeOtpremnice;
    private String napomena;

    public Otpremnica() {
        nalog = new Nalog();
        magacin = new Magacin();
        stavkeOtpremnice = new ArrayList<>();
    }

    public Otpremnica(int brojOtpremnice, Nalog nalog, Magacin magacin, Date datumPro, Date datumIzd, List<StavkaOtpremnice> stavkeOtpremnice, String napomena) {
        this.brojOtpremnice = brojOtpremnice;
        this.nalog = nalog;
        this.magacin = magacin;
        this.datumPro = datumPro;
        this.datumIzd = datumIzd;
        this.stavkeOtpremnice = stavkeOtpremnice;
        this.napomena = napomena;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public int getBrojOtpremnice() {
        return brojOtpremnice;
    }

    public void setBrojOtpremnice(int brojOtpremnice) {
        this.brojOtpremnice = brojOtpremnice;
    }

    public Nalog getNalog() {
        return nalog;
    }

    public void setNalog(Nalog nalog) {
        this.nalog = nalog;
    }

    public Magacin getMagacin() {
        return magacin;
    }

    public void setMagacin(Magacin magacin) {
        this.magacin = magacin;
    }

    public Date getDatumPro() {
        return datumPro;
    }

    public void setDatumPro(Date datumPro) {
        this.datumPro = datumPro;
    }

    public Date getDatumIzd() {
        return datumIzd;
    }

    public void setDatumIzd(Date datumIzd) {
        this.datumIzd = datumIzd;
    }

    public List<StavkaOtpremnice> getStavkeOtpremnice() {
        return stavkeOtpremnice;
    }

    public void setStavkeOtpremnice(List<StavkaOtpremnice> stavkeOtpremnice) {
        this.stavkeOtpremnice = stavkeOtpremnice;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.brojOtpremnice;
        hash = 13 * hash + Objects.hashCode(this.nalog);
        hash = 13 * hash + Objects.hashCode(this.magacin);
        hash = 13 * hash + Objects.hashCode(this.datumPro);
        hash = 13 * hash + Objects.hashCode(this.datumIzd);
        hash = 13 * hash + Objects.hashCode(this.stavkeOtpremnice);
        hash = 13 * hash + Objects.hashCode(this.napomena);
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
        final Otpremnica other = (Otpremnica) obj;
        if (this.brojOtpremnice != other.brojOtpremnice) {
            return false;
        }
        if (!Objects.equals(this.napomena, other.napomena)) {
            return false;
        }
        if (!Objects.equals(this.nalog, other.nalog)) {
            return false;
        }
        if (!Objects.equals(this.magacin, other.magacin)) {
            return false;
        }
        if (!Objects.equals(this.datumPro, other.datumPro)) {
            return false;
        }
        if (!Objects.equals(this.datumIzd, other.datumIzd)) {
            return false;
        }
        if (!Objects.equals(this.stavkeOtpremnice, other.stavkeOtpremnice)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return brojOtpremnice + "";
    }     

    @Override
    public String insert() {
        return "INSERT INTO otpremnica(sifraNaloga, sifraMagacina, datumPro, datumIzd, napomena) "
                + "VALUES (" + getNalog().getSifraNaloga() + ", " +
                               getMagacin().getSifraMagacina() + ", '" +
                               getDatumPro() + "', '" +
                               getDatumIzd() + "', '" +
                               getNapomena() + "')";
    }

    @Override
    public String update() {
        return "UPDATE otpremnica SET sifraNaloga = " + getNalog().getSifraNaloga() +
                                   ", sifraMagacina = " + getMagacin().getSifraMagacina() + 
                                   ", datumPro = '" + getDatumPro() + "'" +
                                   ", datumIzd = '" + getDatumIzd() + "'" +
                                   ", napomena = '" + getNapomena() + "'" +
               " WHERE  brojOtpremnice = " + getBrojOtpremnice();
    }

    @Override
    public String delete() {
        return "DELETE FROM otpremnica WHERE brojOtpremnice = " + getBrojOtpremnice();
    }

    @Override
    public String findAll() {
        return "SELECT * FROM otpremnica";
    }

    @Override
    public String details() {
        return "SELECT * FROM otpremnica WHERE brojOtpremnice = " + getBrojOtpremnice();
    }
    
    
}
