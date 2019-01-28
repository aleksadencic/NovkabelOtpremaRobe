/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksa.db;

import com.aleksa.domain.Magacin;
import com.aleksa.domain.Model;
import com.aleksa.domain.Nalog;
import com.aleksa.domain.Otpremnica;
import com.aleksa.domain.Proizvod;
import com.aleksa.domain.Spediter;
import com.aleksa.domain.StavkaOtpremnice;
import com.aleksa.domain.Vozilo;
import com.aleksa.util.IOperation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Aleksa Dencic
 */
public class DBBroker {

    private Connection connection;
    private static DBBroker instance;

    private DBBroker() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DBBroker getInstance() {
        if (instance == null) {
            instance = new DBBroker();
        }
        return instance;
    }

    public boolean connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/novkabel";
            String username = "root";
            String password = "";
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean pokreniDBTransakciju() {
        return connect();
    }

    public int zapamtiDBTransakciju(Model model) {
        try {
            String sql = "";
            switch (model.getOperation()) {
                case IOperation.INSERT:
                    sql = model.insert();
                    break;
                case IOperation.UPDATE:
                    sql = model.update();
                    break;
                case IOperation.DELETE:
                    sql = model.delete();
                    break;
                default:
                    return 0;
            }
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql); //, Statement.RETURN_GENERATED_KEYS); 

            statement.close();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean potvrdiDBTransakciju() {
        try {
            connection.commit();
            disconnect();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean ponistiDBTransakciju() {
        try {
            connection.rollback();
            disconnect();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Vozilo> vratiSvaVozila() {
        try {
            List<Vozilo> voziloList = new ArrayList<>();
            Vozilo vozilo = new Vozilo();
            String sql = vozilo.findAll();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                vozilo = new Vozilo();
                vozilo.setRegistarskiBroj(rs.getString("registarskiBroj"));
                vozilo.setNazivVozila(rs.getString("nazivVozila"));
                vozilo.setSpediter(vratiSpeditera(rs.getInt("sifraSpeditera")));
                voziloList.add(vozilo);
            }
            statement.close();
            return voziloList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Spediter vratiSpeditera(int sifraSpeditera) {
        try {
            Spediter spediter = new Spediter(sifraSpeditera, "");
            String sql = spediter.details();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                spediter.setSifraSpeditera(rs.getInt("sifraSpeditera"));
                spediter.setNazivSpeditera(rs.getString("nazivSpeditera"));
            }
            statement.close();
            return spediter;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Spediter> vratiSveSpeditere() {
        try {
            List<Spediter> spediterList = new ArrayList<>();
            Spediter spediter = new Spediter();
            String sql = spediter.findAll();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                spediter = new Spediter();
                spediter.setSifraSpeditera(rs.getInt("sifraSpeditera"));
                spediter.setNazivSpeditera(rs.getString("nazivSpeditera"));
                spediterList.add(spediter);
            }
            statement.close();
            return spediterList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Vozilo vratiVozilo(String registarskiBroj) {
        try {
            Vozilo vozilo = new Vozilo(registarskiBroj, "", new Spediter());
            String sql = vozilo.details();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                vozilo = new Vozilo();
                vozilo.setRegistarskiBroj(rs.getString("registarskiBroj"));
                vozilo.setNazivVozila(rs.getString("nazivVozila"));
                vozilo.setSpediter(vratiSpeditera(rs.getInt("sifraSpeditera")));
            }
            statement.close();
            return vozilo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Otpremnica> vratiSveOtpremnice() {
        try {
            List<Otpremnica> otpremnicaList = new ArrayList<>();
            Otpremnica otpremnica = new Otpremnica();
            String sql = otpremnica.findAll();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                otpremnica = new Otpremnica();
                otpremnica.setBrojOtpremnice(rs.getInt("brojOtpremnice"));
                otpremnica.setNalog(vratiNalog(rs.getInt("sifraNaloga")));
                otpremnica.setMagacin(vratiMagacin(rs.getInt("sifraMagacina")));
                otpremnica.setDatumPro(rs.getDate("datumPro"));
                otpremnica.setDatumIzd(rs.getDate("datumIzd"));
                otpremnica.setStavkeOtpremnice(vratiStavkeOtpremnice(rs.getInt("brojOtpremnice")));
                otpremnica.setNapomena(rs.getString("napomena"));
                otpremnicaList.add(otpremnica);
            }
            statement.close();
            return otpremnicaList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Nalog vratiNalog(int sifraNaloga) {
        try {
            Nalog nalog = new Nalog(sifraNaloga, new Date());
            String sql = nalog.details();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                nalog = new Nalog();
                nalog.setSifraNaloga(rs.getInt("sifraNaloga"));
                nalog.setDatum(rs.getDate("datum"));
            }
            statement.close();
            return nalog;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Magacin vratiMagacin(int sifraMagacina) {
        try {
            Magacin magacin = new Magacin(sifraMagacina, "");
            String sql = magacin.details();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                magacin = new Magacin();
                magacin.setSifraMagacina(rs.getInt("sifraMagacina"));
                magacin.setNazivMagacina(rs.getString("nazivMagacina"));
            }
            statement.close();
            return magacin;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<StavkaOtpremnice> vratiStavkeOtpremnice(int brojOtpremnice) {
        try {
            List<StavkaOtpremnice> stavkeOtpremniceList = new ArrayList<>();
            Otpremnica otpremnica = new Otpremnica();
            otpremnica.setBrojOtpremnice(brojOtpremnice);
            StavkaOtpremnice stavkaOtpremnice = new StavkaOtpremnice();
            stavkaOtpremnice.setOtpremnica(otpremnica);
            String sql = stavkaOtpremnice.details();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                stavkaOtpremnice = new StavkaOtpremnice();
                stavkaOtpremnice.setRbr(rs.getInt("rbr"));
                stavkaOtpremnice.setProizvod(vratiProizvod(rs.getInt("sifraProizvoda")));
                stavkaOtpremnice.setKolicina(rs.getDouble("kolicina"));
                stavkaOtpremnice.setStatus(rs.getInt("status"));
                stavkaOtpremnice.setOtpremnica(otpremnica);
                stavkaOtpremnice.setUpdateID(rs.getInt("updateID"));
                stavkeOtpremniceList.add(stavkaOtpremnice);
            }
            statement.close();
            return stavkeOtpremniceList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Proizvod vratiProizvod(int sifraProizvoda) {
        try {
            Proizvod proizvod = new Proizvod(sifraProizvoda, "");
            String sql = proizvod.details();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                proizvod = new Proizvod();
                proizvod.setSifraProizvoda(rs.getInt("sifraProizvoda"));
                proizvod.setNazivProizvoda(rs.getString("nazivProizvoda"));
            }
            statement.close();
            return proizvod;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Otpremnica vratiOtpremnicu(int brojOtpremnice) {
        try {
            Otpremnica otpremnica = new Otpremnica();
            otpremnica.setBrojOtpremnice(brojOtpremnice);
            String sql = otpremnica.details();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                otpremnica = new Otpremnica();
                otpremnica.setBrojOtpremnice(rs.getInt("brojOtpremnice"));
                otpremnica.setNalog(vratiNalog(rs.getInt("sifraNaloga")));
                otpremnica.setMagacin(vratiMagacin(rs.getInt("sifraMagacina")));
                otpremnica.setDatumPro(rs.getDate("datumPro"));
                otpremnica.setDatumIzd(rs.getDate("datumIzd"));
                otpremnica.setStavkeOtpremnice(vratiStavkeOtpremnice(brojOtpremnice));
                otpremnica.setNapomena(rs.getString("napomena"));
            }
            statement.close();
            return otpremnica;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int obrisiOtpremnicu(Otpremnica otpremnica) {
        try {
            for (StavkaOtpremnice stavkaOtpremnice : otpremnica.getStavkeOtpremnice()) {
                obrisiStavkuOtpremnice(stavkaOtpremnice);
            }
            String sql = otpremnica.delete();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean obrisiStavkuOtpremnice(StavkaOtpremnice stavkaOtpremnice) {
        try {
            String sql = stavkaOtpremnice.delete();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Magacin> vratiSveMagacine() {
        try {
            List<Magacin> magacinList = new ArrayList<>();
            Magacin magacin = new Magacin();
            String sql = magacin.findAll();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                magacin = new Magacin();
                magacin.setSifraMagacina(rs.getInt("sifraMagacina"));
                magacin.setNazivMagacina(rs.getString("nazivMagacina"));
                magacinList.add(magacin);
            }
            statement.close();
            return magacinList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Nalog> vratiSveNaloge() {
        try {
            List<Nalog> nalogList = new ArrayList<>();
            Nalog nalog = new Nalog();
            String sql = nalog.findAll();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                nalog = new Nalog();
                nalog.setSifraNaloga(rs.getInt("sifraNaloga"));
                nalog.setDatum(rs.getDate("datum"));
                nalogList.add(nalog);
            }
            statement.close();
            return nalogList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void unesiStavkuOtpremnice(StavkaOtpremnice stavkaOtpremnice) {
        try {
            String sql = stavkaOtpremnice.insert();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void izmeniStavkuOtpremnice(StavkaOtpremnice stavkaOtpremnice) {
        try {
            String sql = stavkaOtpremnice.update();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StavkaOtpremnice vratiStavkuOtpremnice(int brojOtpremnice, int rbrStavke) {
        try {
            StavkaOtpremnice stavkaOtpremnice = new StavkaOtpremnice();
            String sql = "SELECT * FROM stavka_otpremnice WHERE brojOtpremnice = " + brojOtpremnice + " AND rbr = " + rbrStavke;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                stavkaOtpremnice = new StavkaOtpremnice();
                stavkaOtpremnice.setRbr(rs.getInt("rbr"));
                stavkaOtpremnice.setProizvod(vratiProizvod(rs.getInt("sifraProizvoda")));
                stavkaOtpremnice.setKolicina(rs.getDouble("kolicina"));
                stavkaOtpremnice.setStatus(rs.getInt("status"));
                stavkaOtpremnice.setOtpremnica(vratiOtpremnicu(brojOtpremnice));
            }
            statement.close();
            return stavkaOtpremnice;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Proizvod> vratiSveProizvode() {
        try {
            List<Proizvod> proizvodList = new ArrayList<>();
            Proizvod proizvod = new Proizvod();
            String sql = proizvod.findAll();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                proizvod = new Proizvod();
                proizvod.setSifraProizvoda(rs.getInt("sifraProizvoda"));
                proizvod.setNazivProizvoda(rs.getString("nazivProizvoda"));
                proizvodList.add(proizvod);
            }
            statement.close();
            return proizvodList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean postaviStatusStavke(int brojOtpremnice, int rbrStavke, int status) {
        try {
            String sql = "UPDATE stavka_otpremnice SET status = " + status
                    + " WHERE  rbr = " + rbrStavke + " AND brojOtpremnice = " + brojOtpremnice;
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean ubaciStavkuOtpremnice(int brojOtpremnice, StavkaOtpremnice stavkaOtpremnice) {
        try {
            Otpremnica otpremnica = new Otpremnica();
            otpremnica.setBrojOtpremnice(brojOtpremnice);
            stavkaOtpremnice.setOtpremnica(otpremnica);
            String sql = stavkaOtpremnice.insert();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean izmeniStavkuOtpremnice(StavkaOtpremnice stavkaOtpremnice, int rbr) {
        try {
            String sql = "UPDATE stavka_otpremnice SET sifraProizvoda = " + stavkaOtpremnice.getProizvod().getSifraProizvoda()
                    + ", kolicina = " + stavkaOtpremnice.getKolicina()
                    + ", status = " + stavkaOtpremnice.getStatus()
                    + " WHERE  rbr = " + rbr + " AND brojOtpremnice = " + stavkaOtpremnice.getOtpremnica().getBrojOtpremnice();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean obrisiVisakStavkiOtpremnice(int brojOtpremnice) {
        try {
            Otpremnica otpremnica = vratiOtpremnicu(brojOtpremnice);
            String sql = "DELETE FROM stavka_otpremnice WHERE status <> 0 AND brojOtpremnice = " + otpremnica.getBrojOtpremnice();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean srediRBStavkiOtpremnice(int brojOtpremnice) {
        try {
            List<StavkaOtpremnice> stavkeOtpremnice = vratiStavkeOtpremnice(brojOtpremnice);
            int i = 1;
            for (StavkaOtpremnice s : stavkeOtpremnice) {
                String sql = "UPDATE stavka_otpremnice SET rbr = " + i
                        + " WHERE  rbr = " + s.getRbr() + " AND brojOtpremnice = " + s.getOtpremnica().getBrojOtpremnice();
                i = i + 1;
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
                statement.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean srediOsnovnePodatkeOtpremnice(Otpremnica otpremnica) {
        try {
            /*
            String sql = "UPDATE otpremnica SET sifraNaloga = " + otpremnica.getNalog().getSifraNaloga()
                    + ", sifraMagacina = " + otpremnica.getMagacin().getSifraMagacina()
                    + ", datumPro = '" + otpremnica.getDatumPro() + "'"
                    + ", datumIzd = '" + otpremnica.getDatumIzd() + "'"
                    + ", napomena = '" + otpremnica.getNapomena() + "'"
                    + " WHERE  brojOtpremnice = " + otpremnica.getBrojOtpremnice();
             */
            String sql = otpremnica.update();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean promeniStatusStavkeOtpremnice(int brojOtpremnice, StavkaOtpremnice stavkaOtpremnice) {
        try {

            String sql = "UPDATE stavka_otpremnice SET status = " + stavkaOtpremnice.getStatus()
                    + " WHERE  rbr = " + stavkaOtpremnice.getRbr() + " AND brojOtpremnice = " + brojOtpremnice;
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setUpdateIDStavkeOtpremnice(int brojOtpremnice, int rbrStavke, int updateID) {
        try {
            String sql = "UPDATE stavka_otpremnice SET updateID = " + updateID
                    + " WHERE  rbr = " + rbrStavke + " AND brojOtpremnice = " + brojOtpremnice;
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean postviUpdateIDSvimStavkama(int brojOtpremnice, int updateID) {
        try {
            String sql = "UPDATE stavka_otpremnice SET updateID = " + updateID
                    + " WHERE brojOtpremnice = " + brojOtpremnice;
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void izmeniStatusStavkeOtpremnice(StavkaOtpremnice stavkaOtpremnice, int status) {
        try {
            String sql = "UPDATE stavka_otpremnice SET status = " + status
                    + " WHERE  rbr = " + stavkaOtpremnice.getRbr()
                    + " AND brojOtpremnice = " + stavkaOtpremnice.getOtpremnica().getBrojOtpremnice();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean ubaciOtpremnicu(Otpremnica otpremnica) {
        try {
            String sql = "INSERT INTO otpremnica(brojOtpremnice, sifraNaloga, sifraMagacina, datumPro, datumIzd, napomena) "
                    + "VALUES (" + otpremnica.getBrojOtpremnice() + ", "
                    + otpremnica.getNalog().getSifraNaloga() + ", "
                    + otpremnica.getMagacin().getSifraMagacina() + ", '"
                    + otpremnica.getDatumPro() + "', '"
                    + otpremnica.getDatumIzd() + "', '"
                    + otpremnica.getNapomena() + "')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println(sql);
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
