/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Ellouze Skander
 */
public class Herboriseterie extends Etablissement {
    private int id;
    private int id_etab;
    private int livraison;

    public Herboriseterie() {

    }

    public Herboriseterie(int id_etab, int livraison, String nom, String adresse, String date_ouverture, String date_fermeture, String email, int num, int fax, String page_fb, String site_web, int heure_ouverture, int heure_fermeture, String image, int idUser) {
        super(nom, adresse, date_ouverture, date_fermeture, email, num, fax, page_fb, site_web, heure_ouverture, heure_fermeture, image, idUser);
        this.id_etab = id_etab;
        this.livraison = livraison;
    }

    public int getId_etab() {
        return id_etab;
    }
        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_etab(int id_etab) {
        this.id_etab = id_etab;
    }

    public int getLivraison() {
        return livraison;
    }

    public void setLivraison(int livraison) {
        this.livraison = livraison;
    }

    

  
}
