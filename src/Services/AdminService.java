/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.fos_user;
import IServices.IAdmin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import DataStorage.MyDB;
import Entities.Utilisateur;
import java.sql.Connection;
import java.util.Observable;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;


/**
 *
 * @author Maha
 */
public class AdminService implements IAdmin
{
    
    Connection conn;
    
    public AdminService()
    {
        this.conn=MyDB.getinstance().getConnexion();
    }

    
    

         @Override
    public List<Utilisateur> afficherUtilisateur() 
    {
         List<Utilisateur> ListUsers=new ArrayList <Utilisateur>();
        ResultSet rs;
        
        String sql ="select * from fos_user";
        try 
        {
            Statement stl = conn.createStatement();
            rs=stl.executeQuery(sql);          
            System.out.println("Display Done");
            while(rs.next())
            {
                Utilisateur user = new Utilisateur();
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setSexe(rs.getString("sexe"));
                user.setRoles(rs.getString("roles"));
                user.setNum_tel(rs.getInt("num_tel"));
                user.setPays(rs.getString("pays"));
                user.setEnabled(rs.getInt("enabled"));
                ListUsers.add(user);
                System.out.println(user.toString());                
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
       return ListUsers ;   
    }

    @Override
    public List<Utilisateur> rechercherUtilisateurParId(int idUser) 
    {
            List<Utilisateur> ListUsers=new ArrayList <Utilisateur>();
        
        ResultSet rs;

        String sql="select * from fos_user where id ="+idUser+";";

        try 
        {
            Statement stl = conn.createStatement();
            rs=stl.executeQuery(sql);          
            System.out.println("Display Done");
            while(rs.next())
            {
                Utilisateur user = new Utilisateur();
                user.setNom(rs.getString("nom"));
                user.setNom(rs.getString("prenom"));
                user.setUsername(rs.getString("username"));
                user.setAdresse(rs.getString("email"));
                user.setSexe(rs.getString("sexe"));
                user.setRoles(rs.getString("roles"));
                user.setNum_tel(rs.getInt("num_tel"));
                user.setPays(rs.getString("pays"));
                ListUsers.add(user);
                System.out.println(user.toString());                
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
       return ListUsers ;
    }

    @Override
    public List<Utilisateur> rechercherUtilisateurParNom(String nomUti) 
    {
        List<Utilisateur> ListUsers=new ArrayList <Utilisateur>();
        
        ResultSet rs;
        
        String sql ="select * from fos_user where username='"+nomUti+"';";

        try 
        {
            Statement stl = conn.createStatement();
            rs=stl.executeQuery(sql);          
            System.out.println("Display Done");
            while(rs.next())
            {
               
                Utilisateur user = new Utilisateur();
                user.setNom(rs.getString("nom"));
                user.setNom(rs.getString("prenom"));
                user.setUsername(rs.getString("username"));
                user.setAdresse(rs.getString("email"));
                user.setSexe(rs.getString("sexe"));
                user.setRoles(rs.getString("roles"));
                user.setNum_tel(rs.getInt("num_tel"));
                user.setPays(rs.getString("pays"));
                ListUsers.add(user);
                System.out.println(user.toString());          
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
       return ListUsers ;
    }

    @Override
    public void activerCompteParAdmin(int id) 
    {
        String sql = "UPDATE fos_user SET enabled = '"+1+"' WHERE id ="+id+";";
        try
        {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Modification effectuée");
        } catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }    
    }

    @Override
    public void BanirCompteParAdmin(int id)
    {
        String sql = "UPDATE fos_user SET enabled = '"+0+"' WHERE id ="+id+";";
        try
        {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Modification effectuée");
        }
        catch (SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }    
    }
    
 
    
    
    
    
    
    
    
    
           @Override
    public int CalculerFemme() 
    {
        ResultSet rs;       
        String sql= "SELECT count(sexe) FROM `fos_user` WHERE fos_user.`sexe`='Femme'";
        int nb = 0 ; 
        try
        {
         Statement stl = conn.createStatement();
         rs=stl.executeQuery(sql);
         while (rs.next())
        {
            nb = rs.getInt("COUNT(sexe)");
        }
      
        }
        catch (SQLException ex)       
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    return (nb);  
    
}
    
      @Override
    public int CalculerHomme() 
    {
        ResultSet rs;       
        String sql= "SELECT count(sexe) FROM `fos_user` WHERE fos_user.`sexe`='Homme'";
        int nb = 0 ; 
        try
        {
         Statement stl = conn.createStatement();
         rs=stl.executeQuery(sql);
         while (rs.next())
        {
            nb = rs.getInt("COUNT(sexe)");
        }
      
        }
        catch (SQLException ex)       
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    return (nb);  
    
    }
    
    
          @Override
    public int CalculerTunisie() 
    {
        ResultSet rs;       
        String sql= "SELECT count(pays) FROM `fos_user` WHERE fos_user.`pays`='Tunisie'";
        int nb = 0 ; 
        try
        {
         Statement stl = conn.createStatement();
         rs=stl.executeQuery(sql);
         while (rs.next())
        {
            nb = rs.getInt("COUNT(pays)");
        }
      
        }
        catch (SQLException ex)       
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    return (nb);  
    
    }
    
    
             @Override
    public int CalculerFrance() 
    {
        ResultSet rs;       
        String sql= "SELECT count(pays) FROM `fos_user` WHERE fos_user.`pays`='France'";
        int nb = 0 ; 
        try
        {
         Statement stl = conn.createStatement();
         rs=stl.executeQuery(sql);
         while (rs.next())
        {
            nb = rs.getInt("COUNT(pays)");
        }
      
        }
        catch (SQLException ex)       
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    return (nb);  
    
    }
    
    
                 @Override
    public int CalculerAllemagne() 
    {
        ResultSet rs;       
        String sql= "SELECT count(pays) FROM `fos_user` WHERE fos_user.`pays`='Allemagne'";
        int nb = 0 ; 
        try
        {
         Statement stl = conn.createStatement();
         rs=stl.executeQuery(sql);
         while (rs.next())
        {
            nb = rs.getInt("COUNT(pays)");
        }
      
        }
        catch (SQLException ex)       
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    return (nb);  
    
    }
    
        
    
                 @Override
    public int CalculerCanada() 
    {
        ResultSet rs;       
        String sql= "SELECT count(pays) FROM `fos_user` WHERE fos_user.`pays`='Canada'";
        int nb = 0 ; 
        try
        {
         Statement stl = conn.createStatement();
         rs=stl.executeQuery(sql);
         while (rs.next())
        {
            nb = rs.getInt("COUNT(pays)");
        }
      
        }
        catch (SQLException ex)       
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    return (nb);  
    
    }
    
                     @Override
    public int CalculerAmsterdam() 
    {
        ResultSet rs;       
        String sql= "SELECT count(pays) FROM `fos_user` WHERE fos_user.`pays`='Amsterdam'";
        int nb = 0 ; 
        try
        {
         Statement stl = conn.createStatement();
         rs=stl.executeQuery(sql);
         while (rs.next())
        {
            nb = rs.getInt("COUNT(pays)");
        }
      
        }
        catch (SQLException ex)       
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    return (nb);  
    
    }
    
              @Override
    public int CalculerTurquie() 
    {
        ResultSet rs;       
        String sql= "SELECT count(pays) FROM `fos_user` WHERE fos_user.`pays`='Turquie'";
        int nb = 0 ; 
        try
        {
         Statement stl = conn.createStatement();
         rs=stl.executeQuery(sql);
         while (rs.next())
        {
            nb = rs.getInt("COUNT(pays)");
        }
      
        }
        catch (SQLException ex)       
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    return (nb);  
    
    }
    
                  @Override
    public int CalculerJapon() 
    {
        ResultSet rs;       
        String sql= "SELECT count(pays) FROM `fos_user` WHERE fos_user.`pays`='Japon'";
        int nb = 0 ; 
        try
        {
         Statement stl = conn.createStatement();
         rs=stl.executeQuery(sql);
         while (rs.next())
        {
            nb = rs.getInt("COUNT(pays)");
        }
      
        }
        catch (SQLException ex)       
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    return (nb);  
    
    }
    
    
    
                  @Override
    public int CalculerChine() 
    {
        ResultSet rs;       
        String sql= "SELECT count(pays) FROM `fos_user` WHERE fos_user.`pays`='Chine'";
        int nb = 0 ; 
        try
        {
         Statement stl = conn.createStatement();
         rs=stl.executeQuery(sql);
         while (rs.next())
        {
            nb = rs.getInt("COUNT(pays)");
        }
      
        }
        catch (SQLException ex)       
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    return (nb);  
    
    }
    
    
    
                      @Override
    public int CalculerDemandeEnAttente() 
    {
        ResultSet rs;       
        String sql= "SELECT count(etat) FROM `demande` WHERE demande.`etat`='En attente'";
        int nb = 0 ; 
        try
        {
         Statement stl = conn.createStatement();
         rs=stl.executeQuery(sql);
         while (rs.next())
        {
            nb = rs.getInt("COUNT(etat)");
        }
      
        }
        catch (SQLException ex)       
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    return (nb);  
    
    }
    
                      @Override
    public int CalculerDemandeRefuse() 
    {
        ResultSet rs;       
        String sql= "SELECT count(etat) FROM `demande` WHERE demande.`etat`='Refusée'";
        int nb = 0 ; 
        try
        {
         Statement stl = conn.createStatement();
         rs=stl.executeQuery(sql);
         while (rs.next())
        {
            nb = rs.getInt("COUNT(etat)");
        }
      
        }
        catch (SQLException ex)       
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    return (nb);  
    
    }
    

                  @Override
    public int CalculerDemandeAccepte() 
    {
        ResultSet rs;       
        String sql= "SELECT count(etat) FROM `demande` WHERE demande.`etat`='accéptée'";
        int nb = 0 ; 
        try
        {
         Statement stl = conn.createStatement();
         rs=stl.executeQuery(sql);
         while (rs.next())
        {
            nb = rs.getInt("COUNT(etat)");
        }
      
        }
        catch (SQLException ex)       
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    return (nb);  
    
    }
}

