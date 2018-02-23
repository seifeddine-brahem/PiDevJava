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
public class Fiche_Client {
    private int id;
    private String suivie;
    private int id_user;
    private int id_etab;
    private String nomUser;
    private String nomEtab;

    public Fiche_Client(int id, String suivie, int id_user, int id_etablissement) {
        this.id = id;
        this.suivie = suivie;
        this.id_user = id_user;
        this.id_etab = id_etablissement;
    }

    public Fiche_Client() {
    }

    public Fiche_Client(String suivie, int id_user, int id_etablissement) {
        this.suivie = suivie;
        this.id_user = id_user;
        this.id_etab = id_etablissement;
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

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_etablissement() {
        return id_etab;
    }

    public void setId_etablissement(int id_etablissement) {
        this.id_etab = id_etablissement;
    }

    public void setId_etab(int id_etab) {
        this.id_etab = id_etab;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public void setNomEtab(String nomEtab) {
        this.nomEtab = nomEtab;
    }

    
    public int getId_etab() {
        return id_etab;
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
        return "Fiche_Client{" + "id=" + id + ", suivie=" + suivie + ", id_user=" + id_user + ", id_etab=" + id_etab + '}';
    }

    
    
    

    
    
    


    
}
