/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Categorie;
import Entities.Conseil;
import Entities.fos_user;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Maha
 */
public interface IConseil
{
    
    public void ajouterConseil(Conseil c);
    public void supprimerConseil(Conseil c);
    public void modifierConseil(Conseil c);
    public List<Conseil>afficherConseils();
    public List<Conseil> chercherConseilParCategorie(String nomCategorie);
    public ObservableList<Conseil> chercherConseilParPartenaire(String nomPart);
    public  List<String> listerNomsPartanaire() ;
    public List<String> listerNomsCategorie();
    public fos_user getUser(int idUser ) ;
    public Categorie getCategorie(int idCategorie ) ; 
    public Categorie getCategorie(String nomCategorie ) ;

}
