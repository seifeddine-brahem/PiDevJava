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
public class Parapharmacie extends Etablissement { 
    private int id;
    private Etablissement etab;
    private int livraison;

    public Parapharmacie() {
    }

    public Parapharmacie(Etablissement etab, int livraison, String nom, String adresse, String date_ouverture, String date_fermeture, String email, int num, int fax, String page_fb, String site_web, int heure_ouverture, int heure_fermeture, String image, Utilisateur user) {
        super(nom, adresse, date_ouverture, date_fermeture, email, num, fax, page_fb, site_web, heure_ouverture, heure_fermeture, image, user);

        this.etab = etab;
        this.livraison = livraison;
    }



        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Etablissement getEtab() {
        return etab;
    }

    public void setEtab(Etablissement etab) {
        this.etab = etab;
    }
    


    public int getLivraison() {
        return livraison;
    }

    public void setLivraison(int livraison) {
        this.livraison = livraison;
    }



 
    
    
    
}
