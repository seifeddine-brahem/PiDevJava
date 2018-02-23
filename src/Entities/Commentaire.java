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
public class Commentaire 
{
    private int id_commentaire;
    private int id_user;
    private String contenu;

    public Commentaire() 
    {
    }

    public Commentaire(int id_user,String contenu) 
    {
        this.id_user=id_user;
        this.contenu = contenu;
    }

    public int getId_commentaire() {
        return id_commentaire;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    
    public int getId_user() {
        return id_user;
    }

    public String getContenu() {
        return contenu;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id_user=" + id_user + ", contenu=" + contenu + '}';
    }

   
    
    
    
    
    
    
}
