/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Ellouze Skander
 */
public class Etablissement { 
    
    private int id;
    private String nom;
    private String image;
    private String adresse;
    private String date_ouverture;
    private String date_fermeture;
    private String email;
    private int num;
    private int fax;
    private String page_fb;
    private String site_web;
    private int heure_ouverture;
    private int heure_fermeture;
    private int idUser;
    
    public Etablissement() 
    {
    }

    public Etablissement(String nom, String adresse, String  date_ouverture, String date_fermeture, String email, int num, int fax, String page_fb, String site_web, int heure_ouverture, int heure_fermeture,String image,int idUser) 
    {

        this.nom = nom;
        this.adresse = adresse;
        this.date_ouverture = date_ouverture;
        this.date_fermeture =  date_fermeture;
        this.email = email;
        this.num = num;
        this.fax = fax;
        this.page_fb = page_fb;
        this.site_web = site_web;
        this.heure_ouverture = heure_ouverture;
        this.heure_fermeture = heure_fermeture;
        this.image=image;
        this.idUser=idUser;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

   
   

    public int getId() 
    {
        return id;
    }

    public String getNom() 
    {
        return nom;
    }

    public String getAdresse() 
    {
        return adresse;
    }

    public String getDate_ouverture() 
    {
        return date_ouverture;
    }

    public String getDate_fermeture()
    {
        return date_fermeture;
    }

    public String getEmail()
    {
        return email;
    }

    public int getNum()
    {
        return num;
    }

    public int getFax()
    {
        return fax;
    }

    public String getPage_fb() 
    {
        return page_fb;
    }

    public String getSite_web()
    {
        return site_web;
    }

    public int getHeure_ouverture()
    {
        return heure_ouverture;
    }

    public int getHeure_fermeture() 
    {
        return heure_fermeture;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public void setAdresse(String adresse)
    {
        this.adresse = adresse;
    }

    public void setDate_ouverture(String date_ouverture)
    {
        this.date_ouverture = date_ouverture;
    }

    public void setDate_fermeture(String date_fermeture)
    {
        this.date_fermeture = date_fermeture;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setNum(int num)
    {
        this.num = num;
    }

    public void setFax(int fax)
    {
        this.fax = fax;
    }

    public void setPage_fb(String page_fb)
    {
        this.page_fb = page_fb;
    }

    public void setSite_web(String site_web)
    {
        this.site_web = site_web;
    }

    public void setHeure_ouverture(int heure_ouverture)
    {
        this.heure_ouverture = heure_ouverture;
    }

    public void setHeure_fermeture(int heure_fermeture)
    {
        this.heure_fermeture = heure_fermeture;
    }
    

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Etablissement{" + "id=" + id + ", nom=" + nom + ", image=" + image + ", adresse=" + adresse + ", date_ouverture=" + date_ouverture + ", date_fermeture=" + date_fermeture + ", email=" + email + ", num=" + num + ", fax=" + fax + ", page_fb=" + page_fb + ", site_web=" + site_web + ", heure_ouverture=" + heure_ouverture + ", heure_fermeture=" + heure_fermeture + ", idUser=" + idUser + '}';
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
