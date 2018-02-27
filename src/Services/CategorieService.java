/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.MyDB;
import Entities.Categorie;
import Entities.Conseil;
import Entities.Utilisateur;
import Entities.fos_user;
import IServices.ICategorie;
import Utils.GetConnectedUser;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Maha
 */
public class CategorieService implements ICategorie
        
{
    
    Connection conn;
            private ObservableList<Categorie>data;
    
    public CategorieService()
    {
        this.conn=MyDB.getinstance().getConnexion();
    }

    @Override
    public void ajouterCategorie(Categorie cat) 
    {
      // Utilisateur u = GetConnectedUser.GetConnectedUser();
        String sql= "Insert into categorie(nom,type,id_user) values ('"+cat.getNom()+"','"+cat.getType()+"',"+cat.getUser().getId()+");";
         try {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Add categorie Done");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ajout catégorie confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Catégorie ajouté!");
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
    public void supprimerCategorie(Categorie cat)
            
    {
         Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Alerte suppression");
        alert.setHeaderText("Suppression d'un conseil");
        alert.setContentText("Êtes-vous sûr(e) de vouloir supprimer?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        try
            {
            String sql ="DELETE FROM categorie WHERE id_categorie= "+cat.getId_categorie()+";";
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Delete Categorie Done");
            } 
        catch (SQLException ex) 
            {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            }
        
    }

    @Override
    public void modifierCategorie(Categorie cat)
    {
         String sql ="UPDATE categorie SET nom = '"+cat.getNom()+"', type = '"+cat.getType()+"' WHERE id_categorie ="+ cat.getId_categorie()+";";
        try 
        {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Update categorie done");
        } 
        catch (SQLException ex) 
        {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }
        
    }

    @Override
    public List<Categorie> afficherCategorie()
    {
        List<Categorie> ListCategorie=new ArrayList <Categorie>();
        
        ResultSet rs;
        
        String sql ="SELECT ca.*,user.username  FROM categorie ca ,fos_user user  WHERE (ca.id_user = USER.id)";
        try 
        {
            Statement stl = conn.createStatement();
            rs=stl.executeQuery(sql);          
            System.out.println("Affichage Done");
            while(rs.next())
            {
                Categorie cat = new Categorie();
                cat.setId_categorie(rs.getInt("id_categorie"));
                cat.setNom(rs.getString("nom"));
                //cat.setId_user(rs.getInt("id_user"));
                cat.setNom_user(rs.getString("username"));
                cat.setType(rs.getString("type"));
                ListCategorie.add(cat);
                System.out.println(cat.toString());

                
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
       return ListCategorie;
    }
    
    
    

    public List<String> afficherCategorieEvt()
    {
        List<String> ListCategorie=new ArrayList <String>();
        
        ResultSet rs;
        
        String sql ="SELECT *  FROM categorie ca  WHERE ca.type='Evenement'";
        try 
        {
            Statement stl = conn.createStatement();
            rs=stl.executeQuery(sql);          
            System.out.println("Affichage Done");
            while(rs.next())
            {
                Categorie cat = new Categorie();
                String s;
                //cat.setId_categorie(rs.getInt("id_categorie"));
                s=rs.getString("nom");
               // cat.setId_user(rs.getInt("id_user"));
                //cat.setNom_user(rs.getString("username"));
                //cat.setType(rs.getString("type"));
                ListCategorie.add(s);
                System.out.println(cat.toString1());

                
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
       return ListCategorie;
    }
    
    
    

    @Override
    public List<Categorie> chercherCategorieParNom(String nomUti) 
    {
       List<Categorie>ListCategorie =new ArrayList <Categorie>();              
       ResultSet rs;       
       String sql ="select id from fos_user where username='"+nomUti+"';";

        try 
        {
            Statement stl = conn.createStatement();
            rs=stl.executeQuery(sql);    
            rs.next();
            int idUser= rs.getInt("id");
            System.out.println(idUser);
            String sql2="select * from Categorie where id_user ="+idUser+";";
            Statement st2 = conn.createStatement();
            ResultSet  rs2=st2.executeQuery(sql2);       
            System.out.println("Affichage Done");
            while(rs2.next())
            {
                Categorie categorie = new Categorie();
                categorie.setId_categorie(rs2.getInt("id_categorie"));
                categorie.setNom(rs2.getString("nom"));
                //categorie.setId_user(rs2.getInt("id_user"));
                

                ListCategorie.add(categorie);
                System.out.println(categorie.toString());               
            }
        } 
        
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return ListCategorie;
    }

    @Override
    public List<String> listerNomsPartanaire()
    {
       List<String> ListPart=new ArrayList <String>();              
        ResultSet rs;
        String sql ="select username from fos_user where roles = 'partenaire';";

          try 
        {
            Statement stl = conn.createStatement();
            rs=stl.executeQuery(sql);          
            System.out.println("Affichage Done");
            while(rs.next())
            {
             ListPart.add(rs.getString("username"));                
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return ListPart;
    }
/***
    @Override
    public Categorie ChercherCategorieParId(int id_categorie)
    {
        Categorie categorie=null;
        ResultSet rs ;
        String sql="select * from categorie where id_categorie ="+id_categorie+";";
         try 
         {
            Statement stl = conn.createStatement();
            rs = stl.executeQuery(sql);
            categorie= new Categorie(rs.getString("nom"));
            
         }
         catch (SQLException ex)
         {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
          return categorie;
    }
   
    **/
    
         @Override
    public Utilisateur getUser(int idUser )
    {
       Utilisateur user =new Utilisateur();
        
                      
        ResultSet rs;
        String sql ="select * from fos_user where id='"+idUser+"';";

          try 
        {
            Statement stl = conn.createStatement();
            rs=stl.executeQuery(sql);          
            System.out.println("Affichage Done");
            while(rs.next())
            {
              user.setId(idUser);
              user.setUsername(rs.getString("username"));
               
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return user;
    }
    
    
    

    @Override
    public ObservableList<Categorie> chercherConseilParPartenaire(String nomPart)
    {
        data = FXCollections.observableArrayList();         
        ResultSet rs;
        String sql ="select id from fos_user where (roles = 'partenaire') AND (username = '"+nomPart+"');";

        try 
        {
            Statement stl = conn.createStatement();
            rs=stl.executeQuery(sql);    
            rs.next();
            int idPart= rs.getInt("id");
            System.out.println(idPart);
            String sql2="select * from categorie where id_user ="+idPart+";";
            Statement st2 = conn.createStatement();
            ResultSet  rs2=st2.executeQuery(sql2);       
            System.out.println("Affichage Done");
            while(rs2.next())
            {
                Categorie cat = new Categorie();
                cat.setId_categorie(rs2.getInt("id_categorie"));
                cat.setNom(rs2.getString("nom"));
                //cat.setId_user(rs2.getInt("id_user"));
                cat.setType(rs2.getString("type"));
                String nomUser = (getUser(rs2.getInt("id_user"))).getUsername();                   
                cat.setNom_user(nomUser);
                data.add(cat);
              
            }
        } 
        
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return data;   
    }
    
}
