/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.MyDB;
import Entities.Commentaire;
import Entities.CommentaireConseill;
import Entities.Conseil;
import Entities.Evenement;
import IServices.ICommentaireConseil;
import esbe.CommentaireConseil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

/**
 *
 * @author Maha
 */
public class CommentaireConseilService implements ICommentaireConseil
        
{
    
    Connection conn;
    
    public CommentaireConseilService()
    {
        this.conn=MyDB.getinstance().getConnexion();
    }


    @Override
    public void ajouterCommentaireConseil(CommentaireConseill c)
    {
       
            String sql= "Insert into commentaire_conseil(contenu,id_user,id_conseil) values ('"+c.getContenu()+"','"+c.getUser().getId()+"','"+c.getConseil().getId_conseil()+"');";
         try 
            {          
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Add Commentaire Done");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout commentaire confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Commentaire ajouté!");
            alert.showAndWait();
             }
         catch (SQLException ex) 
            {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
            }
    }

    @Override
    public List<CommentaireConseill> chercherCommentaire(Conseil e)
    {
       List<CommentaireConseill> ListCommentaire=new ArrayList <CommentaireConseill>();
        
        ResultSet rs;
        
        String sql ="select c.*,f.username from commentaire_conseil c , fos_user f where (c.id_user = f.id) and (c.id_conseil = "+e.getId_conseil()+");";
        try 
        {
            Statement stl = conn.createStatement();
            rs=stl.executeQuery(sql);  
            System.out.println(sql);
            System.out.println("Affichage Done");
            while(rs.next())
            {
                CommentaireConseill c = new CommentaireConseill();
                c.setId_commentaire(rs.getInt("id_commentaire"));
                c.getUser().setId(rs.getInt("id_user"));
                c.getUser().setUsername(rs.getString("username"));
                c.setContenu(rs.getString("contenu"));
                ListCommentaire.add(c);
                System.out.println(c.toString());
                
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
       return ListCommentaire;
    }

}



