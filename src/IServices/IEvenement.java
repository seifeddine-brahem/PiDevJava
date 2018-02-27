/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Categorie;
import Entities.Evenement;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Maha
 */
public interface IEvenement 
{
    public void ajouterEvenement(Evenement e);
    public void supprimerEvenement(Evenement e);
    public void modifierEvenement(Evenement e);
    public List<Evenement>afficherEvenements();
    public List<Evenement> chercherEvenementParCategorie(String nomCategorie); 
    public List<Evenement> chercherEvenementParNomUtilisateur(String nomUti);   
    public List<Evenement> chercherEvenementParDate(LocalDate date); 
    public void supprimmerEvenement(LocalDate date);
    public List<String> listerNomsCategorie();
    public Categorie getCategorie(int idCategorie );
    public Categorie getCategorie(String nomCategorie );
    public  List<Evenement> chercherEvenementParCategoriee(String nomCategorie); 
    public List<Evenement> chercherEvenementAujourdhui();
    public List<Evenement> chercherEvenementDemain();
    public List<Evenement> chercherEvenementSemaine();


}
