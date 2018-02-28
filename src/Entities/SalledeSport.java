/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author anis
 */
public class SalledeSport extends Etablissement {
    private int id;
    private Etablissement etab;
    private int nb_entraineur;

    public SalledeSport() {
    }

    public SalledeSport(Etablissement etab, int nb_entraineur, String nom, String adresse, String date_ouverture, String date_fermeture, String email, int num, int fax, String page_fb, String site_web, int heure_ouverture, int heure_fermeture, String image, Utilisateur user) {
        super(nom, adresse, date_ouverture, date_fermeture, email, num, fax, page_fb, site_web, heure_ouverture, heure_fermeture, image, user);
        this.etab = etab;
        this.nb_entraineur = nb_entraineur;
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
    

    public int getNb_entraineur() {
        return nb_entraineur;
    }

    public void setNb_entraineur(int nb_entraineur) {
        this.nb_entraineur = nb_entraineur;
    }

   
}
