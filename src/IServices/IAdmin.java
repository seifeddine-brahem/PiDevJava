/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Utilisateur;
import Entities.fos_user;
import java.util.List;

/**
 *
 * @author Maha
 */
public interface IAdmin 
{
    public List<Utilisateur> afficherUtilisateur();
    public List <Utilisateur> rechercherUtilisateurParId(int id); 
    public List <Utilisateur> rechercherUtilisateurParNom(String username); 
    public void activerCompteParAdmin(int id);
    public void BanirCompteParAdmin(int id);
    public int CalculerFemme() ;
    public int CalculerHomme() ;
    public int CalculerFrance();
    public int CalculerTunisie();
    public int CalculerAmsterdam();
    public int CalculerCanada();
    public int CalculerAllemagne();
    public int CalculerTurquie();
    public int CalculerJapon();
    public int CalculerChine(); 
    
    
    
        public int CalculerDemandeAccepte();
        public int CalculerDemandeRefuse();
        public int CalculerDemandeEnAttente();

        
    
}
