/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Maha
 */
public class Demande 
{
    private int id_demande;
    private String nom;
    private String prenom;
    private int CIN;
    private String date_naissance;
    private String CIN_image_recto;
    private String CIN_image_verso;
    private String diplome;
    private String photo_etab;
    private int num_identifiant;
    private String patente;
    private int id_user;

    public Demande()
    {
        
    }

    public Demande(String nom, String prenom, int CIN, String date_naissance, String CIN_image_recto, String CIN_image_verso, String diplome,String photo_etab, int num_identifiant, String patente, int id_user)
    {
        this.nom = nom;
        this.prenom = prenom;
        this.CIN = CIN;
        this.date_naissance = date_naissance;
        this.CIN_image_recto = CIN_image_recto;
        this.CIN_image_verso = CIN_image_verso;
        this.diplome = diplome;
        this.photo_etab=photo_etab;
        this.num_identifiant = num_identifiant;
        this.patente = patente;
        this.id_user = id_user;
    }



    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getCIN() {
        return CIN;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public String getCIN_image_recto() {
        return CIN_image_recto;
    }

    public String getCIN_image_verso() {
        return CIN_image_verso;
    }

    public String getDiplome() {
        return diplome;
    }

    public int getNum_identifiant() {
        return num_identifiant;
    }

    public String getPatente() {
        return patente;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_demande() {
        return id_demande;
    }

    public void setPhoto_etab(String photo_etab) {
        this.photo_etab = photo_etab;
    }

    public String getPhoto_etab() {
        return photo_etab;
    }

    
    public void setId_demande(int id_demande) {
        this.id_demande = id_demande;
    }
 
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setCIN(int CIN) {
        this.CIN = CIN;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public void setCIN_image_recto(String CIN_image_recto) {
        this.CIN_image_recto = CIN_image_recto;
    }

    public void setCIN_image_verso(String CIN_image_verso) {
        this.CIN_image_verso = CIN_image_verso;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public void setNum_identifiant(int num_identifiant) {
        this.num_identifiant = num_identifiant;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Demande{" + "id_demande=" + id_demande + ", nom=" + nom + ", prenom=" + prenom + ", CIN=" + CIN + ", date_naissance=" + date_naissance + ", CIN_image_recto=" + CIN_image_recto + ", CIN_image_verso=" + CIN_image_verso + ", diplome=" + diplome + ", photo_etab=" + photo_etab + ", num_identifiant=" + num_identifiant + ", patente=" + patente + ", id_user=" + id_user + '}';
    }


    
}
