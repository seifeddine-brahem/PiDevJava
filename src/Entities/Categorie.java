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
    private int id_user; 

    public Categorie()
    {
    }

    public Categorie(String nom,int id_user)
    {
        //this.id_categorie = id_categorie;
        this.nom = nom;
        this.id_user=id_user;
    }

    public int getId_categorie()
    {
        return id_categorie;
    }

    public String getNom() 
    {
        return nom;
    }

    public int getId_user()
    {
        return id_user;
    }

    
    public void setId_categorie(int id_categorie) 
    {
        this.id_categorie = id_categorie;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public void setId_user(int id_user)
    {
        this.id_user = id_user;
    }

    @Override
    public String toString()
    {
        return "Categorie{" + "id_categorie=" + id_categorie + ", nom=" + nom + ", id_user=" + id_user + '}';
    }
       

    
    
    
    
    
    
    
    
    
    
}
