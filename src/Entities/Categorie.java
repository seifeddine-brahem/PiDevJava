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
public class Categorie 
{
    private int id_categorie;
    private String nom;
    private String type;
    private String nom_user;
    //private int id_user;
    private Utilisateur user;

    public Categorie()
    {
        user=new Utilisateur();
    }

    public Categorie(String nom, String type, Utilisateur user) {
        this.nom = nom;
        this.type = type;
        this.user = user;
    }


    public int getId_categorie()
    {
        return id_categorie;
    }

    public String getNom() 
    {
        return nom;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    
    
    public String getType() 
    {
        return type;
    }

    public String getNom_user() {
        return nom_user;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    
    public void setId_categorie(int id_categorie) 
    {
        this.id_categorie = id_categorie;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id_categorie=" + id_categorie + ", nom=" + nom + ", type=" + type + '}';
    }

    public String toString1()
    {
        return ("Nom: "+nom);
    }
       
}
