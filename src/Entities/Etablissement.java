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
    private Utilisateur user;
    
    
    public Etablissement() 
    {
    }

    public Etablissement(String nom, String adresse, String  date_ouverture, String date_fermeture, String email, int num, int fax, String page_fb, String site_web, int heure_ouverture, int heure_fermeture,String image,Utilisateur user) 
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
        this.user=user;
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

    @Override
    public String toString() 
    {
        String mot1=   nom ; 
        String mot2="Adresse : " + adresse;
        String mot3="Date ouverture : " + date_ouverture;
        String mot4="Date fermeture : " + date_fermeture;
        String mot5="Email : " + email;
        String mot6="Numéro téléphone : " + num;
        String mot7="Fax : " + fax;
        String mot8="Page Facebok : " + page_fb;
        String mot9="Site Web : " + site_web;
        String mot10="Heure d'ouverture : " + heure_ouverture;
        String mot11="Heure fermeture : " + heure_fermeture;
        String Newligne=System.getProperty("line.separator"); 
        String resultat=mot1+Newligne+mot2+Newligne+mot3+Newligne+mot4+Newligne+mot5+Newligne+mot6+Newligne+mot7+Newligne+mot8+Newligne+mot9+Newligne+mot10+Newligne+mot11+Newligne; 
        
        
        return resultat;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }
    

  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
