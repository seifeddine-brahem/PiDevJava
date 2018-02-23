/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Utils.TypeCommande;
import java.time.LocalDate;

/**
 *
 * @author elbrh
 */
public class Commande {
    
    private int id_commande;
    private Produit produit ;
    private int id_user ;
    private int quantite;
    private LocalDate date ;
    public  TypeCommande statu ; // 0 non pret , 1 pret 
    
    
    
    /******* Constructor ****/
    public Commande() {
        produit = new Produit();
    }

    public Commande(Produit id_produit, int id_user, int quantite) {
        this.produit = id_produit;
        this.id_user = id_user;
        this.quantite = quantite;
    }

    
    
    
    
     public int getId_commande() {    
        return id_commande;    
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    
    
    

    public String getStatu() {
        return statu.name();
    }

    /****** Getter & Setter *****/
    public void setStatu(TypeCommande statu) {
        this.statu = statu;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    
    

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    


    
       /**
     * toString();
     */

    @Override
    public String toString() {
        return "Commande{" + "produit=" + produit.toString() + ", id_user=" + id_user + ", quantite=" + quantite + '}';
    }
    
}
