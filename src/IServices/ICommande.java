/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Commande;
import Entities.DetailCommande;
import Entities.Produit;
import java.util.List;

/**
 *
 * @author elbrh
 */
public interface ICommande {
    public int passCommande(List<Commande> p); // product contains in one etab 
    public void annulerCommande(Commande c);
    public List<Commande> showMyCommandes(int id_user);
    public List<Commande> showCommandebyEtab(int id_etab);
    public List<DetailCommande> showProductParCommande(Commande c);

}
