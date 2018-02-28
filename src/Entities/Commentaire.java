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
    private Utilisateur user;
    private String contenu;
    private Evenement evenement ;
    
    
    public Commentaire() 
    {
        user = new Utilisateur();
        evenement= new Evenement();
    }

    public Commentaire(Utilisateur user, String contenu, Evenement evenement) {
        this.user = user;
        this.contenu = contenu;
        this.evenement = evenement;
    }

    public int getId_commentaire() {
        return id_commentaire;
    }

    public Utilisateur getUser() {
        return user;
    }

    public String getContenu() {
        return contenu;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id_commentaire=" + id_commentaire + ", contenu=" + contenu + '}';
    }


    
    


   
    
    
    
    
    
    
}
