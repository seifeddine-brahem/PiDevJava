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
public class Produit {
    
    private int id ;
    private String nom,description,image ;
    private float prix ;
    private Etablissement etab ;
    private String type ;
    
    
    

    public Produit() {
        etab = new Etablissement();
    }
    
    
    
    
    public Produit( String nom, String description, String image, float prix) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.prix = prix;
    }

    public Etablissement getEtab() {
        return etab;
    }

    public void setEtab(Etablissement etab) {
        this.etab = etab;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", image=" + image + ", prix=" + prix + ", etab=" + etab + '}';
    }


    
    
    
    
    
    
    
    
    
    
    
}
