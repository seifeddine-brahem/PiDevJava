/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author user
 */
public class Fiche_Client {
    private int CIN;
    private String nom;
    private String prenom;
    private String email;
    private int id_user;
    private int id_etablissement;

    @Override
    public String toString() {
        return "Fiche_Client{" + "CIN=" + CIN + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", id_user=" + id_user + ", id_etablissement=" + id_etablissement + '}';
    }

    public Fiche_Client() 
    {
    
    }

    public Fiche_Client(int CIN, String nom, String prenom, String email, int id_user, int id_etablissement) {
        this.CIN = CIN;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.id_user = id_user;
        this.id_etablissement = id_etablissement;
    }

    public int getCIN() {
        return CIN;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public int getId_user() {
        return id_user;
    }
    
    public int getId_etablissement() {
        return id_etablissement;
    }

    public void setCIN(int CIN) {
        this.CIN = CIN;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    
    public void setId_etablissement(int id_etablissement) {
        this.id_etablissement = id_etablissement;
    }
}
