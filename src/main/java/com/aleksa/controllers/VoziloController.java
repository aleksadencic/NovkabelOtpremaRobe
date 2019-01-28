/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksa.controllers;

import com.aleksa.db.DBBroker;
import com.aleksa.domain.Spediter;
import com.aleksa.domain.Vozilo;
import com.aleksa.util.IOperation;
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
@RequestMapping(value = "/vozilo")
public class VoziloController {
    
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ModelAndView all_vehicles() {
        DBBroker.getInstance().connect();
        List<Vozilo> vozila = DBBroker.getInstance().vratiSvaVozila();        
        DBBroker.getInstance().disconnect();
        ModelAndView mv = new ModelAndView("vozilo/find");
        mv.addObject("voziloList", vozila);
        return mv;
    }
    
    @RequestMapping(value = "/details/{registarskiBroj}", method = RequestMethod.GET)
    public ModelAndView details(@PathVariable String registarskiBroj) {
        DBBroker.getInstance().connect();
        Vozilo vozilo = DBBroker.getInstance().vratiVozilo(registarskiBroj);        
        DBBroker.getInstance().disconnect();
        ModelAndView mv = new ModelAndView("vozilo/details");
        mv.addObject("vozilo", vozilo);
        return mv;
    }
    
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public ModelAndView insert_get() {
        DBBroker.getInstance().connect();      
        List<Spediter> spediteri = DBBroker.getInstance().vratiSveSpeditere();   
        DBBroker.getInstance().disconnect(); 
        ModelAndView mv = new ModelAndView("vozilo/insert");
        mv.addObject("vozilo", new Vozilo());
        mv.addObject("spediterList", spediteri);
        
        return mv;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert_post(@ModelAttribute("vozilo") Vozilo vozilo) {
        vozilo.setOperation(IOperation.INSERT);
        DBBroker.getInstance().pokreniDBTransakciju();
        int result = DBBroker.getInstance().zapamtiDBTransakciju(vozilo);
        if (result > 0) {
            DBBroker.getInstance().potvrdiDBTransakciju();
        } else {
            DBBroker.getInstance().ponistiDBTransakciju();
        }
        return "redirect:/vozilo/find";
    }
    
    @RequestMapping(value = "/update/{registarskiBroj}", method = RequestMethod.GET)
    public ModelAndView update_get(@PathVariable String registarskiBroj) {
        DBBroker.getInstance().connect();
        Vozilo vozilo = DBBroker.getInstance().vratiVozilo(registarskiBroj);   
        List<Spediter> spediteri = DBBroker.getInstance().vratiSveSpeditere();        
        DBBroker.getInstance().disconnect(); 
        ModelAndView mv = new ModelAndView("vozilo/update");
        mv.addObject("vozilo", vozilo);
        mv.addObject("spediterList", spediteri);
        return mv;
    }

    @RequestMapping(value = "/update/{registarskiBroj}", method = RequestMethod.POST)
    public String update_post(@PathVariable String registarskiBroj, @ModelAttribute("vozilo") Vozilo vozilo) {
        vozilo.setRegistarskiBroj(registarskiBroj);
        vozilo.setOperation(IOperation.UPDATE);
        DBBroker.getInstance().pokreniDBTransakciju();
        int res = DBBroker.getInstance().zapamtiDBTransakciju(vozilo);
        if (res > 0) {
            DBBroker.getInstance().potvrdiDBTransakciju();
        } else {
            DBBroker.getInstance().ponistiDBTransakciju();
        }
        return "redirect:/vozilo/find";
    }
    
    @RequestMapping(value = "/delete/{registarskiBroj}", method = RequestMethod.GET)
    public ModelAndView delete_get(@PathVariable String registarskiBroj) {
        DBBroker.getInstance().connect();
        Vozilo vozilo = DBBroker.getInstance().vratiVozilo(registarskiBroj);               
        DBBroker.getInstance().disconnect();
        ModelAndView mv = new ModelAndView("vozilo/delete");
        mv.addObject("vozilo", vozilo);
        return mv;
    }

    @RequestMapping(value = "/delete/{registarskiBroj}", method = RequestMethod.POST)
    public String delete_post(@PathVariable String registarskiBroj) {
        DBBroker.getInstance().connect();
        Vozilo vozilo = DBBroker.getInstance().vratiVozilo(registarskiBroj); 
        DBBroker.getInstance().disconnect();        
        vozilo.setOperation(IOperation.DELETE);
        DBBroker.getInstance().pokreniDBTransakciju();
        int res = DBBroker.getInstance().zapamtiDBTransakciju(vozilo);
        if (res > 0) {
            DBBroker.getInstance().potvrdiDBTransakciju();
        } else {
            DBBroker.getInstance().ponistiDBTransakciju();
        }        
        return "redirect:/vozilo/find";
    }
 
}
