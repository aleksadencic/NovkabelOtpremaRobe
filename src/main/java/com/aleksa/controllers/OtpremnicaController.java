/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksa.controllers;

import com.aleksa.db.DBBroker;
import com.aleksa.domain.Magacin;
import com.aleksa.domain.Nalog;
import com.aleksa.domain.Otpremnica;
import com.aleksa.domain.StavkaOtpremnice;
import com.aleksa.domain.Vozilo;
import com.aleksa.util.IOperation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Aleksa Dencic
 */
@Controller
@RequestMapping(value = "/otpremnica")
public class OtpremnicaController {

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ModelAndView all_delivery_notes() {
        DBBroker.getInstance().connect();
        List<Otpremnica> otpremnice = DBBroker.getInstance().vratiSveOtpremnice();
        //sredi podatke ukoliko korisnik ne zali da sacuva izmene
        for (Otpremnica otpremnica : otpremnice) {
            for (StavkaOtpremnice stavkaOtpremnice : otpremnica.getStavkeOtpremnice()) {
                DBBroker.getInstance().connect();
                //one koji su za brisanje otkazi
                if (stavkaOtpremnice.getStatus() == IOperation.DELETE
                        && stavkaOtpremnice.getUpdateID() == 0) {
                    DBBroker.getInstance().izmeniStatusStavkeOtpremnice(stavkaOtpremnice, 0);
                }
                //one koji su za insert ili update obrisi
                if (stavkaOtpremnice.getStatus() == IOperation.INSERT
                        || stavkaOtpremnice.getStatus() == IOperation.UPDATE) {
                    boolean result = DBBroker.getInstance().obrisiStavkuOtpremnice(stavkaOtpremnice);
                    if (result) {
                        DBBroker.getInstance().potvrdiDBTransakciju();
                    } else {
                        DBBroker.getInstance().ponistiDBTransakciju();
                    }
                }
            }
            //sredi redne brojeve
            DBBroker.getInstance().connect();
            boolean result = DBBroker.getInstance().srediRBStavkiOtpremnice(otpremnica.getBrojOtpremnice());
            if (result) {
                DBBroker.getInstance().potvrdiDBTransakciju();
            } else {
                DBBroker.getInstance().ponistiDBTransakciju();
            }
        }

        DBBroker.getInstance().disconnect();
        ModelAndView mv = new ModelAndView("otpremnica/find");
        mv.addObject("otpremnicaList", otpremnice);
        return mv;
    }

    @RequestMapping(value = "/details/{brojOtpremnice}", method = RequestMethod.GET)
    public ModelAndView details(@PathVariable int brojOtpremnice) {
        DBBroker.getInstance().connect();
        Otpremnica otpremnica = DBBroker.getInstance().vratiOtpremnicu(brojOtpremnice);
        DBBroker.getInstance().disconnect();
        ModelAndView mv = new ModelAndView("otpremnica/details");
        mv.addObject("otpremnica", otpremnica);
        return mv;
    }

    @RequestMapping(value = "/delete/{brojOtpremnice}", method = RequestMethod.GET)
    public ModelAndView delete_get(@PathVariable int brojOtpremnice) {
        DBBroker.getInstance().connect();
        Otpremnica otpremnica = DBBroker.getInstance().vratiOtpremnicu(brojOtpremnice);
        DBBroker.getInstance().disconnect();
        ModelAndView mv = new ModelAndView("otpremnica/delete");
        mv.addObject("otpremnica", otpremnica);
        return mv;
    }

    @RequestMapping(value = "/delete/{brojOtpremnice}", method = RequestMethod.POST)
    public String delete_post(@PathVariable int brojOtpremnice) {
        DBBroker.getInstance().connect();
        Otpremnica otpremnica = DBBroker.getInstance().vratiOtpremnicu(brojOtpremnice);
        DBBroker.getInstance().disconnect();
        //otpremnica.setOperation(IOperation.DELETE);
        DBBroker.getInstance().pokreniDBTransakciju();
        int res = DBBroker.getInstance().obrisiOtpremnicu(otpremnica);
        if (res > 0) {
            DBBroker.getInstance().potvrdiDBTransakciju();
        } else {
            DBBroker.getInstance().ponistiDBTransakciju();
        }
        return "redirect:/otpremnica/find";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String insert_get() throws ParseException {
        DBBroker.getInstance().connect();
        List<Magacin> magacini = DBBroker.getInstance().vratiSveMagacine();
        List<Nalog> nalozi = DBBroker.getInstance().vratiSveNaloge();
        List<Otpremnica> otpremnice = DBBroker.getInstance().vratiSveOtpremnice();
        int maxBrojOtpremnice = 0;
        for (Otpremnica otpremnica : otpremnice) {
            if (maxBrojOtpremnice < otpremnica.getBrojOtpremnice()) {
                maxBrojOtpremnice = otpremnica.getBrojOtpremnice();
            }
        }
        int brojOtpremnice = maxBrojOtpremnice + 1;
        Otpremnica otpremnica = new Otpremnica();
        otpremnica.setBrojOtpremnice(brojOtpremnice);
        Nalog nalog = new Nalog();
        nalog.setSifraNaloga(1);
        otpremnica.setNalog(nalog);
        Magacin magacin = new Magacin();
        magacin.setSifraMagacina(1);
        otpremnica.setMagacin(magacin);
        otpremnica.setNapomena("");

        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM DD HH:mm:ss zzzz yyyy");
        String dateString = String.valueOf(new Date());
        java.util.Date dateStr = formatter.parse(dateString);
        java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
        otpremnica.setDatumPro(dateDB);

        dateString = String.valueOf(new Date());
        dateStr = formatter.parse(dateString);
        dateDB = new java.sql.Date(dateStr.getTime());

        otpremnica.setDatumIzd(dateDB);
        boolean result = DBBroker.getInstance().ubaciOtpremnicu(otpremnica);
        if (result) {
            DBBroker.getInstance().potvrdiDBTransakciju();
        } else {
            DBBroker.getInstance().ponistiDBTransakciju();
        }
        return "redirect:/otpremnica/update/" + brojOtpremnice;
    }

    /*
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert_post(@ModelAttribute("otpremnica") Otpremnica otpremnica) {        
        //sredi osnovne podatke
        DBBroker.getInstance().pokreniDBTransakciju();
        boolean result = DBBroker.getInstance().srediOsnovnePodatkeOtpremnice(otpremnica);
        if (result) {
            DBBroker.getInstance().potvrdiDBTransakciju();
        } else {
            DBBroker.getInstance().ponistiDBTransakciju();
        }
        return "redirect:/otpremnica/update/" + 4;
    }
     */
    @RequestMapping(value = "/update/{brojOtpremnice}", method = RequestMethod.GET)
    public ModelAndView update_get(@PathVariable int brojOtpremnice) {
        DBBroker.getInstance().connect();
        Otpremnica otpremnica = DBBroker.getInstance().vratiOtpremnicu(brojOtpremnice);
        List<Magacin> magacini = DBBroker.getInstance().vratiSveMagacine();
        List<Nalog> nalozi = DBBroker.getInstance().vratiSveNaloge();

        //stavke za prikaz        
        List<StavkaOtpremnice> stavkeOtpremniceSlanje = new ArrayList<>();
        for (StavkaOtpremnice stavkaOtpremnice : otpremnica.getStavkeOtpremnice()) {
            if (stavkaOtpremnice.getStatus() == IOperation.UPDATE) {
                stavkeOtpremniceSlanje.add(stavkaOtpremnice);
            }
            if ((stavkaOtpremnice.getStatus() == 0 || stavkaOtpremnice.getStatus() == IOperation.INSERT)
                    && stavkaOtpremnice.getUpdateID() == 0) {
                stavkeOtpremniceSlanje.add(stavkaOtpremnice);
            }
        }
        otpremnica.setStavkeOtpremnice(stavkeOtpremniceSlanje);

        DBBroker.getInstance().disconnect();
        ModelAndView mv = new ModelAndView("otpremnica/update");
        mv.addObject("otpremnica", otpremnica);
        mv.addObject("magacinList", magacini);
        mv.addObject("nalogList", nalozi);
        return mv;
    }

    @RequestMapping(value = "/update/{brojOtpremnice}", method = RequestMethod.POST)
    public String update_post(@PathVariable int brojOtpremnice, @ModelAttribute("otpremnica") Otpremnica otpremnicaForma) throws ParseException {

        DBBroker.getInstance().connect();
        Otpremnica otpremnica = DBBroker.getInstance().vratiOtpremnicu(brojOtpremnice);
        otpremnica.setNalog(otpremnicaForma.getNalog());
        otpremnica.setMagacin(otpremnicaForma.getMagacin());

        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM DD HH:mm:ss zzzz yyyy");

        String dateString = String.valueOf(otpremnicaForma.getDatumPro());
        java.util.Date dateStr = formatter.parse(dateString);
        java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
        otpremnica.setDatumPro(dateDB);

        dateString = String.valueOf(otpremnicaForma.getDatumIzd());
        dateStr = formatter.parse(dateString);
        dateDB = new java.sql.Date(dateStr.getTime());
        otpremnica.setDatumIzd(dateDB);

        otpremnica.setNapomena(otpremnicaForma.getNapomena());
        List<StavkaOtpremnice> stavkeOtpremnice = DBBroker.getInstance().vratiStavkeOtpremnice(brojOtpremnice);
        otpremnica.setStavkeOtpremnice(stavkeOtpremnice);
        DBBroker.getInstance().disconnect();

        //sredi osnovne podatke
        DBBroker.getInstance().connect();
        boolean result = DBBroker.getInstance().srediOsnovnePodatkeOtpremnice(otpremnica);
        if (result) {
            DBBroker.getInstance().potvrdiDBTransakciju();
        } else {
            DBBroker.getInstance().ponistiDBTransakciju();
        }

        //Delete items
        for (StavkaOtpremnice stavkaOtpremnice : stavkeOtpremnice) {
            if (stavkaOtpremnice.getStatus() == IOperation.DELETE) {
                if (stavkaOtpremnice.getUpdateID() != 0) {
                    DBBroker.getInstance().connect();
                    int rbrStavke = stavkaOtpremnice.getUpdateID();
                    StavkaOtpremnice s = DBBroker.getInstance().vratiStavkuOtpremnice(brojOtpremnice, rbrStavke);
                    result = DBBroker.getInstance().obrisiStavkuOtpremnice(s);
                    if (result) {
                        DBBroker.getInstance().potvrdiDBTransakciju();
                    } else {
                        DBBroker.getInstance().ponistiDBTransakciju();
                    }
                }
                DBBroker.getInstance().connect();
                result = DBBroker.getInstance().obrisiStavkuOtpremnice(stavkaOtpremnice);
                if (result) {
                    DBBroker.getInstance().potvrdiDBTransakciju();
                } else {
                    DBBroker.getInstance().ponistiDBTransakciju();
                }

            }
        }

        //Update items
        for (StavkaOtpremnice stavkaOtpremnice : stavkeOtpremnice) {
            if (stavkaOtpremnice.getStatus() == IOperation.UPDATE) {
                int rbrUpdate = stavkaOtpremnice.getUpdateID();
                stavkaOtpremnice.setStatus(0);
                DBBroker.getInstance().connect();
                result = DBBroker.getInstance().izmeniStavkuOtpremnice(stavkaOtpremnice, rbrUpdate);
                DBBroker.getInstance().obrisiStavkuOtpremnice(stavkaOtpremnice);
                if (result) {
                    DBBroker.getInstance().potvrdiDBTransakciju();
                } else {
                    DBBroker.getInstance().ponistiDBTransakciju();
                }
            }
        }

        //Insert items
        for (StavkaOtpremnice stavkaOtpremnice : stavkeOtpremnice) {
            if (stavkaOtpremnice.getStatus() == IOperation.INSERT) {
                stavkaOtpremnice.setStatus(0);
                DBBroker.getInstance().connect();
                result = DBBroker.getInstance().promeniStatusStavkeOtpremnice(brojOtpremnice, stavkaOtpremnice);
                if (result) {
                    DBBroker.getInstance().potvrdiDBTransakciju();
                } else {
                    DBBroker.getInstance().ponistiDBTransakciju();
                }
            }
        }

        //obrisi stavke koje nemaju status 0
        DBBroker.getInstance().connect();
        result = DBBroker.getInstance().obrisiVisakStavkiOtpremnice(brojOtpremnice);
        if (result) {
            DBBroker.getInstance().potvrdiDBTransakciju();
        } else {
            DBBroker.getInstance().ponistiDBTransakciju();
        }
        //sredi redne brojeve
        DBBroker.getInstance().connect();
        result = DBBroker.getInstance().srediRBStavkiOtpremnice(brojOtpremnice);
        if (result) {
            DBBroker.getInstance().potvrdiDBTransakciju();
        } else {
            DBBroker.getInstance().ponistiDBTransakciju();
        }
        //postavi updateID na 0 svim preostalim stavkama
        DBBroker.getInstance().connect();
        result = DBBroker.getInstance().postviUpdateIDSvimStavkama(brojOtpremnice, 0);
        if (result) {
            DBBroker.getInstance().potvrdiDBTransakciju();
        } else {
            DBBroker.getInstance().ponistiDBTransakciju();
        }

        return "redirect:/otpremnica/find";
    }
/*
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
*/
}
