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
    private int id_etab;
    private int cnam; 

    public CabinetMedical(int id_etab, int cnam) {
        this.id_etab = id_etab;
        this.cnam = cnam;
    }

    public CabinetMedical() {
    }

    public CabinetMedical(String nom, String adresse, String date_ouverture, String date_fermeture, String email, int num, int fax, String page_fb, String site_web, int heure_ouverture, int heure_fermeture, String image, int idUser,int id_etab, int cnam) {
        super(nom, adresse, date_ouverture, date_fermeture, email, num, fax, page_fb, site_web, heure_ouverture, heure_fermeture, image, idUser);
        this.id_etab = id_etab;
        this.cnam = cnam;
    }

    public int getId_etab() {
        return id_etab;
    }

    public void setId_etab(int id_etab) {
        this.id_etab = id_etab;
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
