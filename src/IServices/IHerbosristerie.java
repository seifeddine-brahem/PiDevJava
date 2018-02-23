/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Etablissement;
import Entities.Herboriseterie;
import java.util.List;

/**
 *
 * @author Ellouze Skander
 */
public interface IHerbosristerie {
 
        public void ajouterHerbosristerie(Herboriseterie c);
        public void supprimerHerbosristerie(Herboriseterie c);
        public void modifierHerbosristerie(Herboriseterie c); 

    
}
