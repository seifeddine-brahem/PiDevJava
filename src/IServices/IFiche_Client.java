/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Fiche_Client;
import java.util.List;

/**
 *
 * @author user
 */
public interface IFiche_Client {
    public void ajouterFicheClient(Fiche_Client fc);
    public void supprimerFicheClient(Fiche_Client fc);
    public void modifierFicheClient(Fiche_Client fc);
    public  Fiche_Client  rechercherFiche_Client (String nom);
    public Fiche_Client afficherFicheClient(Fiche_Client fc);
    public List <Fiche_Client> afficherToutFicheClient();
}
