/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author elbrh
 */
public class DetailCommande {
    
    
    private Commande c ;
    private Produit p ;
    private int quantite ;

    public DetailCommande() {
        c= new Commande();
        p= new Produit();
    }

    public Commande getC() {
        return c;
    }

    public void setC(Commande c) {
        this.c = c;
    }

    public Produit getP() {
        return p;
    }

    public void setP(Produit p) {
        this.p = p;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "DetailCommande{" + "c=" + c + ", p=" + p + ", quantite=" + quantite + '}';
    }
    
    
    
    
    
    
    
    
    
    
}
