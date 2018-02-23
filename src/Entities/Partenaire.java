/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Utils.SpecialitePartenaire;
import Utils.TypePartenaire;
import java.util.Date;

/**
 *
 * @author user
 */
public class Partenaire extends fos_user {
    
    //private String roles;
    private TypePartenaire type;
    private SpecialitePartenaire spécialité;



    public Partenaire(String username, String email,String pass, String adresse, String date_naissance, int code_postal,
            String sexe, int num_tel, String photo_profil, String pays, TypePartenaire type, SpecialitePartenaire spécialité) 
    {
        super(username, email, pass, adresse, date_naissance, code_postal, sexe, num_tel, photo_profil, pays); 
        
        super.setRoles("partenaire");
        
        this.type = type;
        this.spécialité = spécialité;
    }

    public Partenaire(TypePartenaire type, SpecialitePartenaire spécialité) {
        this.type = type;
        this.spécialité = spécialité;
    }
    
    

    public Partenaire() {
        
    }
    
  
    
    public String getType() {
        return type.name();
    }

    public String getSpecialite() {
        return spécialité.name();
    }
    
    
     
    public void setType(TypePartenaire type) {
        this.type = type;
    }
    

    public void setSpecialite(SpecialitePartenaire spécialité) {
        this.spécialité = spécialité;
    }

  
    
    
    
    
    
}
