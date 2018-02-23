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
public class Candidature {
    private int id;
    private int id_utilisateur;
    private int id_partenaire;
    private LocalDate date_postulation;
    private String etat;
    private String niveau_etude;
    private String cv;

    public Candidature() {
    }

    public Candidature(int id_utilisateur, int id_partenaire, LocalDate date_postulation, String niveau_etude, String cv) {
        this.id_utilisateur = id_utilisateur;
        this.id_partenaire = id_partenaire;
        this.date_postulation = date_postulation;
        //this.etat = etat;
        this.niveau_etude = niveau_etude;
        this.cv = cv;
    }

    public int getId() {
        return id;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }
    
    public int getId_partenaire() {
        return id_partenaire;
    }

    public LocalDate getDate_postulation() {
        return date_postulation;
    }

    public String getEtat() {
        return etat;
    }

    public String getNiveau_etude() {
        return niveau_etude;
    }

    public String getCv() {
        return cv;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public void setId_partenaire(int id_partenaire) {
        this.id_partenaire = id_partenaire;
    }
    
    public void setDate_postulation(LocalDate date_postulation) {
        this.date_postulation = date_postulation;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setNiveau_etude(String niveau_etude) {
        this.niveau_etude = niveau_etude;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    @Override
    public String toString() {
        return "Candidature{" + "id_utilisateur=" + id_utilisateur + "id_partenaire=" + id_partenaire + ", date_postulation=" + date_postulation + ", etat=" + etat + ", niveau_etude=" + niveau_etude + ", cv=" + cv + '}';
    }

   
    
}
