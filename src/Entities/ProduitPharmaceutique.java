/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Utils.Forme;
import Utils.ModeAdministration;
import Utils.PourQui;

/**
 *
 * @author elbrh
 */
public class ProduitPharmaceutique extends Produit {
    
    
    
    private String marque ;
    private ModeAdministration mode_administration;
    private Forme forme ;
    private PourQui pourqui ;
    

    ////////////////**** Constrctor *******///////////////////// 

    public ProduitPharmaceutique() {
        super();
    }

    public ProduitPharmaceutique(String marque, ModeAdministration mode_administration, Forme forme, PourQui pourqui, String nom, String description, String image, float prix) {
        super( nom, description, image, prix);
        this.marque = marque;
        this.mode_administration = mode_administration;
        this.forme = forme;
        this.pourqui = pourqui;
    }
    
    
    ////////*********************** Getter & Setter ****************/////////////


    
    
    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getMode_administration() {
        return mode_administration.name();
    }

    public void setMode_administration(ModeAdministration mode_administration) {
        this.mode_administration = mode_administration;
    }

    public String getForme() {
        return forme.name();
    }

    public void setForme(Forme forme) {
        this.forme = forme;
    }

    public String getPourqui() {
        return pourqui.name();
    }

    public void setPourqui(PourQui pourqui) {
        this.pourqui = pourqui;
    }
    
    
    
    
    
    
}
