/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.MyDB;
import Entities.Commentaire;
import Entities.Evenement;
import IServices.ICommentaire;
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
public class CommentaireService implements ICommentaire
{
     Connection conn;
    
    public CommentaireService()
    {
        this.conn=MyDB.getinstance().getConnexion();
    }

    @Override
    public void ajouterCommentaireEvent(Commentaire c)
    {
            String sql= "Insert into commentaire_event(contenu,id_user,id_event) values ('"+c.getContenu()+"','"+c.getUser().getId()+"','"+c.getEvenement().getId_event()+"');";
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
    public void supprimerCommentaire(Commentaire c)
    {
          String sql ="DELETE FROM commentaire WHERE id_commentaire= "+c.getId_commentaire()+";";
        try {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Delete Commentaire Done");
            } 
        catch (SQLException ex) 
            {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            }
    }

    @Override
    public void modifierCommentaire(Commentaire c)
    {
        String sql ="UPDATE commentaire SET contenu = '"+c.getContenu()+"'WHERE id_commentaire ="+ c.getId_commentaire()+";";
        try 
        {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Update commentaire done");
        } 
        catch (SQLException ex) 
        {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }
    }

    @Override
    public List<Commentaire> afficherCommentaires()
    {
         List<Commentaire> ListCommentaire=new ArrayList <Commentaire>();
        
        ResultSet rs;
        
        String sql ="select * from commentaire";
        try 
        {
            Statement stl = conn.createStatement();
            rs=stl.executeQuery(sql);          
            System.out.println("Affichage Done");
            while(rs.next())
            {
                Commentaire c = new Commentaire();
                c.setId_commentaire(rs.getInt("id_commentaire"));
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

    @Override
    public List<Commentaire> chercherCommentaireParNom(String nomUti)
    {
        List<Commentaire>ListCommentaire =new ArrayList <Commentaire>();              
       ResultSet rs;
        
        String sql ="select id from fos_user where username='"+nomUti+"';";

        try 
        {
            Statement stl = conn.createStatement();
            rs=stl.executeQuery(sql);    
            rs.next();
            int idUser= rs.getInt("id");
            System.out.println(idUser);
            String sql2="select * from commentaire where id_user ="+idUser+";";
            Statement st2 = conn.createStatement();
            ResultSet  rs2=st2.executeQuery(sql2);       
            System.out.println("Affichage Done");
            while(rs2.next())
            {
                Commentaire commentaire = new Commentaire();
                commentaire.setId_commentaire(rs2.getInt("id_commentaire"));
                commentaire.setContenu(rs2.getString("contenu"));

                ListCommentaire.add(commentaire);
                System.out.println(commentaire.toString());               
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

    @Override
    public List<Commentaire> chercherCommentaire(Evenement e)
    {
        List<Commentaire> ListCommentaire=new ArrayList <Commentaire>();
        
        ResultSet rs;
        
        String sql ="select c.*,f.username from commentaire_event c , fos_user f where (c.id_user = f.id) and (c.id_event = "+e.getId_event()+");";

        try 
        {
            Statement stl = conn.createStatement();
            rs=stl.executeQuery(sql);    
            System.out.println(sql);
            System.out.println("Affichage Done");
            while(rs.next())
            {
                Commentaire c = new Commentaire();
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

