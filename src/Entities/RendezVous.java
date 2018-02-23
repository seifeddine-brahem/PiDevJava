/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import DataStorage.MyDB;
import java.sql.Connection;

/**
 *
 * @author acer
 */
public class RendezVous {
    
    
    Connection connexion=MyDB.getinstance().getConnexion();
    
    private int id_rdv;
    
    private String date;
    private int id_etab;
    private int id_user;
    private int id_service;
    private String username;
    private Utilisateur user;
    private Service service;
    private String nometab;
    private Etablissement etab;
    private String time;
    private String nomservice;
    private Service serv;
    private String etat;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Etablissement getEtab() {
        return etab;
    }

    public void setEtab(Etablissement etab) {
        this.etab = etab;
    }

    
    
    public int getId_etab() {
        return id_etab;
    }

    public void setId_etab(int id_etab) {
        this.id_etab = id_etab;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNomservice() {
        return nomservice;
    }

    public void setNomservice(String nomservice) {
        this.nomservice = nomservice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
   
    
    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public RendezVous(String date, String username, String nometab, String nomservice, String etat) {
       
        this.date = date;
        this.username = username;
        this.nometab = nometab;
        this.nomservice = nomservice;
        this.etat = etat;
    }

    public RendezVous(String date, int id_etab, int id_user, int id_service, String time) {
        this.date = date;
        this.id_etab = id_etab;
        this.id_user = id_user;
        this.id_service = id_service;
        this.time = time;
    }

  

    
    
    
    public RendezVous( String date, String time, int id_etab,int id_service, String nometab, String etat) {
        
        this.date = date;
        this.time=time;
        this.id_etab=id_etab;
        this.id_service = id_service;
        this.nometab = nometab;
        this.etat = etat;
        
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }
    
    public Service getServ() {
        return serv;
    }

    public void setServ(Service serv) {
        this.serv = serv;
    }
    
    public RendezVous() {
         serv=new Service();
        user = new Utilisateur();
        etab=new Etablissement();
    }
  

    public int getId_rdv() {
        return id_rdv;
    }

    public void setId_rdv(int id_rdv) {
        this.id_rdv = id_rdv;
    }

   

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

   

    public String getNometab() {
        return nometab;
    }

    public void setNometab(String nometab) {
        this.nometab = nometab;
    }

    public void setId_etab(String id_etab) {
        this.nometab = id_etab;
    }

    public int getId_service() {
        return id_service;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    
    @Override
    public String toString() {
        return "RendezVous{" + " id_rdv=" + id_rdv + ", date=" + date + ", id_service=" + id_service + ", user=" + user + ", service=" + service + ",  Etablissement=" + nometab + ", time=" + time + ", etat=" + etat + '}';
    }

   
   
    
    

}
