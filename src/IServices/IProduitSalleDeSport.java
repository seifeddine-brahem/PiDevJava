/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.ProduitSalleDeSport;
import java.util.List;

/**
 *
 * @author elbrh
 */
public interface IProduitSalleDeSport {
    public void addProduit(ProduitSalleDeSport p);
    public void editProduit(ProduitSalleDeSport p);
    public void deleteProduit(ProduitSalleDeSport p);
    public List<ProduitSalleDeSport>  showProduit(ProduitSalleDeSport p);
    public List<ProduitSalleDeSport>  searchProduit(String nom);
    
}
