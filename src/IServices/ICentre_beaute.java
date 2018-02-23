/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.CabinetMedical;
import Entities.Centre_beaute;
import Entities.Etablissement;
import java.util.List;

/**
 *
 * @author Ellouze Skander
 */
public interface ICentre_beaute {

public void ajouterCentre(Centre_beaute c);
public void supprimerCentre(Centre_beaute c);
public void modifierCentre(Centre_beaute c); 

}
