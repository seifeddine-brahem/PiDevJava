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
public class Conseil 
{

    private int id_conseil;
    private String description;
    private String image;
    private String nom_categorie;
    private String nom_user ;
    private Utilisateur user;
    private Categorie categorie;
 
    public Conseil()
    {
        user= new Utilisateur();
        categorie = new Categorie();
    }

    public Conseil(String description, String image, Utilisateur user, Categorie categorie)
    {
        this.description = description;
        this.image = image;
        this.user = user;
        this.categorie = categorie;
    }

    public int getId_conseil() {
        return id_conseil;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public Utilisateur getUser() {
        return user;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setId_conseil(int id_conseil) {
        this.id_conseil = id_conseil;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }


    public String getNom_categorie() {
        return nom_categorie;
    }

    public String getNom_user() {
        return nom_user;
    }



    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    @Override
    public String toString() {
        return "Conseil{" + "id_conseil=" + id_conseil + ", description=" + description + ", image=" + image + '}';
    }

    
    
    
    
    


    
    
    
 
 
    
    
    
    
    
    
    
    
    
    
    

    
}
