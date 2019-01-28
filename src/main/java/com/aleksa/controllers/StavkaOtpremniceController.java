/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksa.controllers;

import com.aleksa.db.DBBroker;
import com.aleksa.domain.Otpremnica;
import com.aleksa.domain.Proizvod;
import com.aleksa.domain.Spediter;
import com.aleksa.domain.StavkaOtpremnice;
import com.aleksa.domain.Vozilo;
import com.aleksa.util.IOperation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
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
@RequestMapping(value = "/stavkaOtpremnice")
public class StavkaOtpremniceController {

    @RequestMapping(value = "/find/{brojOtpremnice}", method = RequestMethod.GET)
    public ModelAndView all_items_for_delivery_note(@PathVariable int brojOtpremnice) {
        DBBroker.getInstance().connect();
        List<StavkaOtpremnice> stavkeOtpremnice = DBBroker.getInstance().vratiStavkeOtpremnice(brojOtpremnice);
        DBBroker.getInstance().disconnect();
        
        //stavke za prikaz
        List<StavkaOtpremnice> stavkeOtpremniceSlanje = new ArrayList<>();
        for (StavkaOtpremnice stavkaOtpremnice : stavkeOtpremnice) {
            if (stavkaOtpremnice.getStatus() == IOperation.UPDATE) {
                stavkeOtpremniceSlanje.add(stavkaOtpremnice);
            }
            if ((stavkaOtpremnice.getStatus() == 0 || stavkaOtpremnice.getStatus() == IOperation.INSERT) &&
                    stavkaOtpremnice.getUpdateID() == 0) {
                stavkeOtpremniceSlanje.add(stavkaOtpremnice);
            }
        }

        ModelAndView mv = new ModelAndView("stavkaOtpremnice/find");

        mv.addObject("brojOtpremnice", brojOtpremnice);
        mv.addObject("stavkeOtpremniceList", stavkeOtpremniceSlanje);
        return mv;
    }

    @RequestMapping(value = "/details/{brojOtpremnice}/{rbrStavke}", method = RequestMethod.GET)
    public ModelAndView details(@PathVariable int brojOtpremnice, @PathVariable int rbrStavke) {
        DBBroker.getInstance().connect();
        StavkaOtpremnice stavkaOtpremnice = DBBroker.getInstance().vratiStavkuOtpremnice(brojOtpremnice, rbrStavke);
        DBBroker.getInstance().disconnect();
        ModelAndView mv = new ModelAndView("stavkaOtpremnice/details");
        mv.addObject("brojOtpremnice", brojOtpremnice);
        mv.addObject("stavkaOtpremnice", stavkaOtpremnice);
        return mv;
    }

    @RequestMapping(value = "/insert/{brojOtpremnice}", method = RequestMethod.GET)
    public ModelAndView insert_get(@PathVariable int brojOtpremnice) {
        DBBroker.getInstance().connect();
        List<Proizvod> proizvodi = DBBroker.getInstance().vratiSveProizvode();
        DBBroker.getInstance().disconnect();
        ModelAndView mv = new ModelAndView("stavkaOtpremnice/insert");
        mv.addObject("stavkaOtpremnice", new StavkaOtpremnice());
        mv.addObject("brojOtpremnice", brojOtpremnice);
        mv.addObject("proizvodList", proizvodi);
        return mv;
    }

    @RequestMapping(value = "/insert/{brojOtpremnice}", method = RequestMethod.POST)
    public String insert_post(@ModelAttribute("stavkaOtpremnice") StavkaOtpremnice stavkaOtpremnice, @PathVariable int brojOtpremnice) {
        stavkaOtpremnice.setStatus(IOperation.INSERT);
        DBBroker.getInstance().pokreniDBTransakciju();
        Otpremnica otpremnica = DBBroker.getInstance().vratiOtpremnicu(brojOtpremnice);
        stavkaOtpremnice.setOtpremnica(otpremnica);
        stavkaOtpremnice.setOperation(IOperation.INSERT);
        int maxRbrStavke = 0;
        for (StavkaOtpremnice s : otpremnica.getStavkeOtpremnice()) {
            if (maxRbrStavke < s.getRbr()) {
                maxRbrStavke = s.getRbr();
            }
        }
        stavkaOtpremnice.setRbr(maxRbrStavke + 1);

        int result = DBBroker.getInstance().zapamtiDBTransakciju(stavkaOtpremnice);
        if (result > 0) {
            DBBroker.getInstance().potvrdiDBTransakciju();
        } else {
            DBBroker.getInstance().ponistiDBTransakciju();
        }
        return "redirect:/stavkaOtpremnice/find/" + brojOtpremnice;
    }

    @RequestMapping(value = "/delete/{brojOtpremnice}/{rbrStavke}", method = RequestMethod.GET)
    public ModelAndView delete_get(@PathVariable int brojOtpremnice, @PathVariable int rbrStavke) {
        DBBroker.getInstance().connect();
        StavkaOtpremnice stavkaOtpremnice = DBBroker.getInstance().vratiStavkuOtpremnice(brojOtpremnice, rbrStavke);
        DBBroker.getInstance().disconnect();
        ModelAndView mv = new ModelAndView("stavkaOtpremnice/delete");
        mv.addObject("brojOtpremnice", brojOtpremnice);
        mv.addObject("stavkaOtpremnice", stavkaOtpremnice);
        return mv;
    }

    @RequestMapping(value = "/delete/{brojOtpremnice}/{rbrStavke}", method = RequestMethod.POST)
    public String delete_post(@PathVariable int brojOtpremnice, @PathVariable int rbrStavke) {
        DBBroker.getInstance().connect();
        StavkaOtpremnice stavkaOtpremnice = DBBroker.getInstance().vratiStavkuOtpremnice(brojOtpremnice, rbrStavke);
        boolean result = DBBroker.getInstance().postaviStatusStavke(brojOtpremnice, rbrStavke, IOperation.DELETE);
        if (result) {
            DBBroker.getInstance().potvrdiDBTransakciju();
        } else {
            DBBroker.getInstance().ponistiDBTransakciju();
        }

        DBBroker.getInstance().disconnect();
        return "redirect:/stavkaOtpremnice/find/" + brojOtpremnice;
    }

    @RequestMapping(value = "/update/{brojOtpremnice}/{rbrStavke}", method = RequestMethod.GET)
    public ModelAndView update_get(@PathVariable int brojOtpremnice, @PathVariable int rbrStavke) {
        DBBroker.getInstance().connect();
        StavkaOtpremnice stavkaOtpremnice = DBBroker.getInstance().vratiStavkuOtpremnice(brojOtpremnice, rbrStavke);
        List<Proizvod> proizvodi = DBBroker.getInstance().vratiSveProizvode();
        DBBroker.getInstance().disconnect();
        ModelAndView mv = new ModelAndView("stavkaOtpremnice/update");
        mv.addObject("stavkaOtpremnice", stavkaOtpremnice);
        mv.addObject("rbrStavke", rbrStavke);
        mv.addObject("brojOtpremnice", brojOtpremnice);
        mv.addObject("proizvodList", proizvodi);
        return mv;
    }

    @RequestMapping(value = "/update/{brojOtpremnice}/{rbrStavke}", method = RequestMethod.POST)
    public String update_post(@PathVariable int brojOtpremnice,
            @PathVariable int rbrStavke,
            @ModelAttribute("stavkaOtpremnice") StavkaOtpremnice stavkaOtpremnice) {
        stavkaOtpremnice.setUpdateID(rbrStavke);
        
        DBBroker.getInstance().pokreniDBTransakciju();
        DBBroker.getInstance().setUpdateIDStavkeOtpremnice(brojOtpremnice, rbrStavke, 5);
        Otpremnica otpremnica = DBBroker.getInstance().vratiOtpremnicu(brojOtpremnice);
        int maxRbrStavke = 0;
        for (StavkaOtpremnice s : otpremnica.getStavkeOtpremnice()) {
            if (maxRbrStavke < s.getRbr()) {
                maxRbrStavke = s.getRbr();
            }
        }
        stavkaOtpremnice.setRbr(maxRbrStavke + 1);
        boolean res = DBBroker.getInstance().ubaciStavkuOtpremnice(brojOtpremnice, stavkaOtpremnice);
        if (res) {
            DBBroker.getInstance().postaviStatusStavke(brojOtpremnice, stavkaOtpremnice.getRbr(), IOperation.UPDATE);
            DBBroker.getInstance().potvrdiDBTransakciju();
        } else {
            DBBroker.getInstance().ponistiDBTransakciju();
        }
        return "redirect:/stavkaOtpremnice/find/" + brojOtpremnice;
    }

}
