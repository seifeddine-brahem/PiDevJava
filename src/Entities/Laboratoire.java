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
public class Laboratoire extends Etablissement {
    private int id;
    private int id_etab;
    private int cnam;
    private int nb_equipe;
    private String type;

    public Laboratoire(int id_etab, int cnam, int nb_equipe, String type, String nom, String adresse, String date_ouverture, String date_fermeture, String email, int num, int fax, String page_fb, String site_web, int heure_ouverture, int heure_fermeture, String image, int idUser) {
        super(nom, adresse, date_ouverture, date_fermeture, email, num, fax, page_fb, site_web, heure_ouverture, heure_fermeture, image, idUser);
        this.id_etab = id_etab;
        this.cnam = cnam;
        this.nb_equipe = nb_equipe;
        this.type = type;
    }

    public Laboratoire() {
    }

    public int getId_etab() {
        return id_etab;
    }

    public void setId_etab(int id_etab) {
        this.id_etab = id_etab;
    }

        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getCnam() {
        return cnam;
    }

    public void setCnam(int cnam) {
        this.cnam = cnam;
    }

    public int getNb_equipe() {
        return nb_equipe;
    }

    public void setNb_equipe(int nb_equipe) {
        this.nb_equipe = nb_equipe;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    
}
