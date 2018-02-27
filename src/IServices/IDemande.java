/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Demande;
import Entities.Utilisateur;
import java.util.List;

/**
 *
 * @author Maha
 */
public interface IDemande
{
    public void ajouterDemande(Demande d);
    public void supprimerDemande(Demande d);
    public void modifierDemande(Demande d);
    public List<Demande>afficherDemandes();
    public List<Demande> chercherDemandeParNom(String nomUti);
    public boolean CheckDemande(int id_user);
    public void decliner (Demande d);
    public void accepter (Demande d) ;
    
}
