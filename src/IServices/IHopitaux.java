/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Etablissement;
import Entities.Hopitaux;

/**
 *
 * @author Ellouze Skander
 */
public interface IHopitaux {
    
            public void ajouterHopitaux(Hopitaux c);
        public void supprimerHopitaux(Hopitaux c);
        public void modifierHopitaux(Hopitaux c); 
}
