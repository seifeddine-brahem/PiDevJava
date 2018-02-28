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
public class CabinetMedical extends Etablissement {
    private int id;
    private Etablissement etab;
    private int cnam; 

    public CabinetMedical(Etablissement etab, int cnam) {
        this.etab = etab;
        this.cnam = cnam;
    }

    public CabinetMedical() {
    }

    public CabinetMedical(String nom, String adresse, String date_ouverture, String date_fermeture, String email, int num, int fax, String page_fb, String site_web, int heure_ouverture, int heure_fermeture, String image, Utilisateur user,Etablissement etab, int cnam) {
        super(nom, adresse, date_ouverture, date_fermeture, email, num, fax, page_fb, site_web, heure_ouverture, heure_fermeture, image, user);
        this.etab = etab;
        this.cnam = cnam;
    }

    public Etablissement getEtab() {
        return etab;
    }

    public void setEtab(Etablissement etab) {
        this.etab = etab;
    }



    public int getCnam() {
        return cnam;
    }

    public void setCnam(int cnam) {
        this.cnam = cnam;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   
    
    
}
