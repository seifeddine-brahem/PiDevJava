/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import DataStorage.MyDB;
import java.sql.Connection;
import java.sql.Date;

/**
 *
 * @author anis
 */
public class Notification {
    Connection connexion=MyDB.getinstance().getConnexion();
    private int idsender;
    private int idreceiver;
    private String type;
    private Date date;
    private int view;
    private Utilisateur user;
    private String username;
    private RendezVous r;
    private int idrdv;
    

    public Notification() {
        r=new RendezVous();
    }

    public Notification(int idsender, int idreceiver, String type, Date date, int view) {
        this.idsender = idsender;
        this.idreceiver = idreceiver;
        this.type = type;
        this.date = date;
        this.view = view;
    }

   

    public Notification(int idsender, int idreceiver, String type, int idr) {
        this.idsender = idsender;
        this.idreceiver = idreceiver;
        this.type = type;
        this.idrdv = idr;
    }

    public Notification(int idsender, int idreceiver, String type, Date date, int view, int idrdv) {
        this.idsender = idsender;
        this.idreceiver = idreceiver;
        this.type = type;
        this.date = date;
        this.view = view;
        this.idrdv = idrdv;
    }



  

    public Utilisateur getUser() {
        return user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

   
    
    public int getIdsender() {
        return idsender;
    }

    public void setIdsender(int iduser) {
        this.idsender = iduser;
    }

    public int getIdreceiver() {
        return idreceiver;
    }

    public void setIdreceiver(int idreceiver) {
        this.idreceiver = idreceiver;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public RendezVous getR() {
        return r;
    }

    public void setR(RendezVous r) {
        this.r = r;
    }

    public int getIdrdv() {
        return idrdv;
    }

    public void setIdrdv(int idrdv) {
        this.idrdv = idrdv;
    }
    
    public String toString(){
        return "Demandeur: "+this.username+"\n "+this.type+"\n "+this.date; 
    }
    
}
