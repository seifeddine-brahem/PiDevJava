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
    private int id_categorie;
    private int id_user;

    public Conseil()
    {
    }

    public Conseil(String description, int id_categorie, int id_user)
    {
        //this.id_conseil = id_conseil;
        this.description = description;
        this.id_categorie = id_categorie;
        this.id_user = id_user;
    }

    public int getId_conseil()
    {
        return id_conseil;
    }

    public String getDescription()
    {
        return description;
    }

    public int getId_categorie()
    {
        return id_categorie;
    }

    public int getId_user()
    {
        return id_user;
    }

    public void setId_conseil(int id_conseil)
    {
        this.id_conseil = id_conseil;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setId_categorie(int id_categorie)
    {
        this.id_categorie = id_categorie;
    }

    public void setId_user(int id_user)
    {
        this.id_user = id_user;
    }

    @Override
    public String toString()
    {
        return "Conseil{" + "id_conseil=" + id_conseil + ", description=" + description + ", id_categorie=" + id_categorie + ", id_user=" + id_user + '}';
    }
    
    
    
    
    
    
    
    
    
    
    

    
}
