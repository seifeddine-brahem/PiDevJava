/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Produit;
import Entities.ProduitParapharmacie;
import java.util.List;

/**
 *
 * @author elbrh
 */
public interface IProduitPara {
    public void addProduit(ProduitParapharmacie p);
    public void editProduit(ProduitParapharmacie p);
    public void deleteProduit(ProduitParapharmacie p);
    public List<ProduitParapharmacie>  showProduit(ProduitParapharmacie p);
    public List<ProduitParapharmacie>  searchProduit(String nom);
    
    
    
    
    
}
