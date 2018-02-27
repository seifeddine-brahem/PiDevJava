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
public class CommentaireConseill 
{
    private int id_commentaire;
    private Utilisateur user ;
    private String contenu;
    private Conseil conseil;

    public CommentaireConseill() 
    {
        user= new Utilisateur();
        conseil = new Conseil();
    }

    public CommentaireConseill(Utilisateur user, String contenu, Conseil conseil) {
        this.user = user;
        this.contenu = contenu;
        this.conseil = conseil;
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

    public Conseil getConseil() {
        return conseil;
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

    public void setConseil(Conseil conseil) {
        this.conseil = conseil;
    }

    @Override
    public String toString() {
        return "CommentaireConseill{" + "id_commentaire=" + id_commentaire + ", user=" + user + ", contenu=" + contenu + ", conseil=" + conseil + '}';
    }

    
    
    
    
    
}
