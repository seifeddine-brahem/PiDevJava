/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Commentaire;
import Entities.CommentaireConseill;
import Entities.Conseil;
import Entities.Evenement;
import esbe.CommentaireConseil;
import java.util.List;

/**
 *
 * @author Maha
 */
public interface ICommentaireConseil
{
    public void ajouterCommentaireConseil(CommentaireConseill c);
    public List<CommentaireConseill> chercherCommentaire(Conseil e);


    
}
