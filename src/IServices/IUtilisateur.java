/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Utilisateur;
import java.util.List;

/**
 *
 * @author user
 */
public interface IUtilisateur {
   public void ajouterUtilisateur (Utilisateur u);
   public void supprimerUtilisateur (Utilisateur u);
public void modifierMotDePasse(String password, int id);   
public void modifierUtilisateur (Utilisateur u);
   public List <Utilisateur> afficherUtilisateurs();
   public List<Utilisateur> rechercherUtilisateur(String username);
   //public Utilisateur afficherUtilisateur();
   public Utilisateur Authentifier (String login,String password);
   public boolean Authentification (String login,String password);
   public void validerCompte(String code, Utilisateur u);
   //public Utilisateur rehercherUtilisateur (String username);
   //public void validerCompte(String code, FosUser u);
   //public void activerCompte (int id);
   //public void rechercherUtilisateur (String nom);
   //public boolean verifyAccountStatus(int id); //vérifier si le compte est activé ou désactivé
}
