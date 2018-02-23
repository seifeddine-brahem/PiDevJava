/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.ProduitHerbo;
import java.util.List;

/**
 *
 * @author elbrh
 */
public interface IProduitHerbo {
    public void addProduit(ProduitHerbo p);
    public void editProduit(ProduitHerbo p);
    public void deleteProduit(ProduitHerbo p);
    public List<ProduitHerbo>  showProduit(ProduitHerbo p);
    public List<ProduitHerbo>  searchProduit(String nom);
}
