/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Etablissement;
import Entities.Laboratoire;


/**
 *
 * @author Ellouze Skander
 */
public interface ILaboratoire {
    
        public void ajouterLaboratoire(Laboratoire c);
        public void supprimerLaboratoire(Laboratoire c);
        public void modifierLaboratoire(Laboratoire c); 
}
