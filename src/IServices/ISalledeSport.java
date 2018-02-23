/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.CabinetMedical;
import Entities.Etablissement;
import Entities.SalledeSport;

/**
 *
 * @author anis
 */
public interface ISalledeSport {
    public void ajouterSalle(SalledeSport s);
public void supprimerSalle(SalledeSport s);
public void modifierSalle(SalledeSport s); 
}
