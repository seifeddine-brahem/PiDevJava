/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.CabinetMedical;
import Entities.Etablissement;
import java.util.List;

/**
 *
 * @author Ellouze Skander
 */
public interface ICabinetMedical {
public void ajouterCabinet(CabinetMedical c);
public void supprimerCabinet(CabinetMedical c);
public void modifierCabinet(CabinetMedical c); 

}
