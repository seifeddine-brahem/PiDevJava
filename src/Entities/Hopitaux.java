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
public class Hopitaux extends Etablissement { 
    private int id;
    private Etablissement  etab;
    private String type;
    private int urgence;
    private int cnam;

    public Hopitaux(Etablissement etab, String type, int urgence, int cnam, String nom, String adresse, String date_ouverture, String date_fermeture, String email, int num, int fax, String page_fb, String site_web, int heure_ouverture, int heure_fermeture, String image, Utilisateur user) {
        super(nom, adresse, date_ouverture, date_fermeture, email, num, fax, page_fb, site_web, heure_ouverture, heure_fermeture, image, user);
        this.etab = etab;
        this.type = type;
        this.urgence = urgence;
        this.cnam = cnam;
    }
    public Hopitaux() {
       
    }

    public Etablissement getEtab() {
        return etab;
    }

    public void setEtab(Etablissement etab) {
        this.etab = etab;
    }



        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUrgence() {
        return urgence;
    }

    public void setUrgence(int urgence) {
        this.urgence = urgence;
    }

    public int getCnam() {
        return cnam;
    }

    public void setCnam(int cnam) {
        this.cnam = cnam;
    }
    


   
    
}
