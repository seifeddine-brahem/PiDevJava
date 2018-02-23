/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Annonce;
import java.util.List;

/**
 *
 * @author user
 */
public interface IAnnonce {
    public void ajouterAnnonce(Annonce a);
    public void supprimerAnnonce(Annonce a);
   // public Annonce rechercherAnnonce (String domaine);
   // public void afficherAnnonce ();
    public List <Annonce> afficherToutLesAnnonces ();
    public void modifierAnnonce (Annonce a);
    
    public List <Annonce> afficherAnnonceParPartenaire (int id_partenaire);
    
    public List<Annonce> chercherAnnonceParDomaine(String domaine);
    public List<Annonce> rechercherAnnonceParDomaine(String domaine);
    public List<Annonce> rechercherAnnonceParDescription(String mot);
    
}
