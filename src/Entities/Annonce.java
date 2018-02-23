/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalDate;

/**
 *
 * @author user
 */
public class Annonce {
    private int id;
    private String domaine;
    private String description;
    private int id_partenaire;
    private LocalDate date_creation;
    private LocalDate date_expiration;

    public Annonce() {
    }

    public Annonce(int id_partenaire, String domaine, String description, LocalDate date_creation, LocalDate date_expiration) {
        this.id_partenaire = id_partenaire;
        this.domaine = domaine;
        this.description = description;
        this.date_creation = date_creation;
        this.date_expiration = date_expiration;
    }

    public int getId() {
        return id;
    }

    public String getDomaine() {
        return domaine;
    }

    public String getDescription() {
        return description;
    }

    public int getId_partenaire() {
        return id_partenaire;
    }

    public LocalDate getDate_creation() {
        return date_creation;
    }

    public LocalDate getDate_expiration() {
        return date_expiration;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId_partenaire(int id_partenaire) {
        this.id_partenaire = id_partenaire;
    }

    public void setDate_creation(LocalDate date_creation) {
        this.date_creation = date_creation;
    }

    public void setDate_expiration(LocalDate date_expiration) {
        this.date_expiration = date_expiration;
    }

    @Override
    public String toString() {
        return "Annonce{" + "id=" + id + ", domaine=" + domaine + ", description=" + description + ", id_partenaire=" + id_partenaire + ", date_creation=" + date_creation + ", date_expiration=" + date_expiration + '}';
    }

}
