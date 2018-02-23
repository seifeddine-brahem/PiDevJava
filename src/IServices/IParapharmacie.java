/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Etablissement;
import Entities.Parapharmacie;


/**
 *
 * @author Ellouze Skander
 */
public interface IParapharmacie {
   
        public void ajouterParapharmacie(Parapharmacie c);
        public void supprimerParapharmacie(Parapharmacie c);
        public void modifierParapharmacie(Parapharmacie c); 
}
