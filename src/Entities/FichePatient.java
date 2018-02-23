/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author user
 */
public class FichePatient {
    private int id;
    private String suivie;
    private String suivieHTML;
    private int idPatient;
    private int idEtab;
    private String nomUser;
    private String nomEtab;

    public FichePatient(int id, String suivie, int id_user, int id_etablissement) {
        this.id = id;
        this.suivie = suivie;
        this.idPatient = id_user;
        this.idEtab = id_etablissement;
    }

    public FichePatient() {
    }

    public FichePatient(String suivie, int id_user, int id_etablissement) {
        this.suivie = suivie;
        this.idPatient = id_user;
        this.idEtab = id_etablissement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSuivie() {
        return suivie;
    }

    public void setSuivie(String suivie) {
        this.suivie = suivie;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public int getIdEtab() {
        return idEtab;
    }

    public void setId_etablissement(int idEtab) {
        this.idEtab = idEtab;
    }

    public void setIdEtab(int idEtab) {
        this.idEtab = idEtab;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public void setNomEtab(String nomEtab) {
        this.nomEtab = nomEtab;
    }

    


    public String getNomUser() {
        return nomUser;
    }

    public String getNomEtab() {
        return nomEtab;
    }

    @Override
    public String toString() 
    {
        return "Fiche_Client{" + "id=" + id + ", suivie=" + suivie + ", idPatient=" + idPatient + ", idEtab=" + idEtab + '}';
    }

    public String getSuivieHTML() {
        return suivieHTML;
    }

    public void setSuivieHTML(String suivieHTML) {
        this.suivieHTML = suivieHTML;
    }

    public FichePatient(String suivie, String suivieHTML, int idPatient, int idEtab) {
        this.suivie = suivie;
        this.suivieHTML = suivieHTML;
        this.idPatient = idPatient;
        this.idEtab = idEtab;
    }

    
    
    

    
    
    


    
}
