/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.ProduitPharmaceutique;
import java.util.List;

/**
 *
 * @author elbrh
 */
public interface IProduitPharmacie {
    public void addProduit(ProduitPharmaceutique p);
    public void editProduit(ProduitPharmaceutique p);
    public void deleteProduit(ProduitPharmaceutique p);
    public List<ProduitPharmaceutique>  showProduit(ProduitPharmaceutique p);
    public List<ProduitPharmaceutique>  searchProduit(String nom);
    
}
