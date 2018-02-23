/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 *
 * @author Maha
 */
public class Evenement 
{
    private int id_event;
    private LocalDate date_debut;
    private LocalDate date_fin;
    private String horaire_com;
    private String horaire_fin;
    private String description;
    private String image;
    private int id_categorie;
    private int id_user;


    public Evenement()
    {
    }

    public Evenement(LocalDate date_debut, LocalDate date_fin, String horaire_com, String horaire_fin, String description, String image, int id_categorie, int id_user)
    {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.horaire_com = horaire_com;
        this.horaire_fin = horaire_fin;
        this.description = description;
        this.image = image;
        this.id_categorie = id_categorie;
        this.id_user = id_user;
    }

    
   

    public int getId_event() {
        return id_event;
    }

    public LocalDate getDate_debut() {
        return date_debut;
    }

    public LocalDate getDate_fin() {
        return date_fin;
    }

    public String getHoraire_com() {
        return horaire_com;
    }

    public String getHoraire_fin() {
        return horaire_fin;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public void setDate_debut(LocalDate date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(LocalDate date_fin) {
        this.date_fin = date_fin;
    }

    public void setHoraire_com(String horaire_com) {
        this.horaire_com = horaire_com;
    }

    public void setHoraire_fin(String horaire_fin) {
        this.horaire_fin = horaire_fin;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString()
    {
        return "Evenement{" + "id_event=" + id_event + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", horaire_com=" + horaire_com + ", horaire_fin=" + horaire_fin + ", description=" + description + ", image=" + image + ", id_categorie=" + id_categorie + ", id_user=" + id_user + '}';
    }

   
    
}
