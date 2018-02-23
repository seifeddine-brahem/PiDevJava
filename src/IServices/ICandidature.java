/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Annonce;
import Entities.Candidature;
import Entities.Utilisateur;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public interface ICandidature {
    public void postuler(Candidature c);
    public void accepter(Candidature c);
    public void decliner(Candidature c);
    public List <Candidature> afficherToutesLesCandidatures (int id_partenaire);
    public  List<Candidature> chercherCandidatureParNiveauEtude(String niveau_etude); 
    public String getUtilisateur(int id_utilisateur);
    public String getUtilisateurAdresse(int id_utilisateur);
    public ObservableList<Candidature> rechercherCandidatureParNiveauEtude(String niveau_etude);
}
