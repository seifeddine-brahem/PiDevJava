/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author acer
 */
public class RendezVous {

    private int id_rdv;
    private String type;
    private LocalDateTime date;
    private int id_user;
    private int id_service;
    private int id_partenaire;
    private String etat;
   

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public RendezVous(String type, LocalDateTime date, int id_user, int id_service, int id_partenaire, String etat) {
        this.type = type;
        this.date = date;
        this.id_user = id_user;
        this.id_service = id_service;
        this.id_partenaire = id_partenaire;
        this.etat = etat;
    }

    public RendezVous() {
    }
  

    public int getId_rdv() {
        return id_rdv;
    }

    public void setId_rdv(int id_rdv) {
        this.id_rdv = id_rdv;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_partenaire() {
        return id_partenaire;
    }

    public void setId_partenaire(int id_partenaire) {
        this.id_partenaire = id_partenaire;
    }

    public int getId_service() {
        return id_service;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    @Override
    public String toString() {
        return "RendezVous{" + "id_rdv=" + id_rdv + ", type=" + type + ", date=" + date + ", id_user=" + id_user + ", id_service=" + id_service + ", id_partenaire=" + id_partenaire + ", etat=" + etat +  '}';
    }
    

}
