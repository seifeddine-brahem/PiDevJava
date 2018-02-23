/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.FichePatient;
import Entities.fos_user;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public interface IFichePatient {
    public void ajouterFicheClient(FichePatient fc);
    public void supprimerFicheClient(FichePatient fc);
    public void modifierFicheClient(FichePatient fc);
    public  ObservableList<FichePatient>  rechercherFiche_Client (String nom);
    public FichePatient afficherFicheClient(FichePatient fc);
    public List <FichePatient> afficherToutFicheClient(); 
    public  List<String> listerNomsPartanaire() ;
    public List<String> listerNomsEtab();
    public fos_user getUser(int idUser);
     
}
