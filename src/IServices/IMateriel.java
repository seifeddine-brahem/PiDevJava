/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Materiel;
import java.util.List;

/**
 *
 * @author elbrh
 */
public interface IMateriel {
    public void addMateriel(Materiel m);
    public void editMateriel(Materiel m);
    public void deleteMateriel(Materiel m);
    public Materiel  showMateriel(Materiel m);
    public List<Materiel>  searchMateriel(String nom);
    
}
