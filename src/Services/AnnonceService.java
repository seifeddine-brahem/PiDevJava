/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.MyDB;
import Entities.Annonce;
import IServices.IAnnonce;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
 * @author user
 */
public class AnnonceService implements IAnnonce {

    
    Connection conn;

   private ObservableList<Annonce> data;
    public AnnonceService() {
        this.conn=MyDB.getinstance().getConnexion();
    }
    
    
    @Override
    public void ajouterAnnonce(Annonce a) {
            String sql ="INSERT INTO annonce (id_partenaire,domaine,description,date_creation, date_expiration) "
                    + "values ("+a.getId_partenaire()+",'"+a.getDomaine()+"','"+a.getDescription()+"','"+a.getDate_creation()+"','"+a.getDate_expiration()+"');";
        try 
        {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Ajoutée");
        }
        catch (SQLException ex)
        {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }   
    }

    @Override
    public void supprimerAnnonce(Annonce a) 
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Supression");
        alert.setHeaderText("Supprimer cette annonce");
        alert.setContentText("Voulez vous vraiment supprimer cette annonce?");
        
        Optional<ButtonType>  result = alert.showAndWait();
        
        if(result.get()== ButtonType.OK)
        {
        
        try 
        {
            String sql ="DELETE FROM annonce WHERE id= "+a.getId()+" ; ";
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Effacée");
        } 
        catch (SQLException ex) 
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        }
    }
    
    @Override
    public List<Annonce> chercherAnnonceParDomaine(String domaine)
    {
       List<Annonce> ListAnnonce=new ArrayList <Annonce>();              
       ResultSet rs;
        try 
        {
            String sql2="SELECT  * from annonce a INNER JOIN fos_user f on a.id_partenaire = f.id where (a.domaine = '"+domaine+"') AND (f.roles= 'partenaire') ;";
            Statement st2 = conn.createStatement();
            ResultSet  rs2=st2.executeQuery(sql2);       
            System.out.println("Affichée");
            
            while(rs2.next())
            {                
                Annonce annonce = new Annonce();
                Timestamp input = rs2.getTimestamp("date_creation");              
                Timestamp input2 = rs2.getTimestamp("date_expiration");
                LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate date2 = input2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDateTime newD = rs2.getTimestamp("date_creation").toLocalDateTime();
                LocalDateTime newG = rs2.getTimestamp("date_expiration").toLocalDateTime();
                java.util.Date ddd = new java.util.Date();
                java.sql.Date ddd2= new java.sql.Date(input.getTime());
                annonce.setId(rs2.getInt("id"));
                annonce.setId_partenaire(rs2.getInt("id_partenaire"));
                annonce.setDomaine(rs2.getString("domaine"));
                annonce.setDescription(rs2.getString("description"));
                annonce.setDate_creation(date);
                annonce.setDate_expiration(date2);

                ListAnnonce.add(annonce);
                System.out.println(annonce.toString());
                                  
            }
        } 
        
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return ListAnnonce;
    }

    @Override
    public ObservableList<Annonce> afficherToutLesAnnonces() {
           data = FXCollections.observableArrayList();         
       ResultSet rs;
        try 
        {
            String sql="SELECT  * from annonce";
            Statement st2 = conn.createStatement();
            ResultSet  rs2=st2.executeQuery(sql);       
            System.out.println("Affichée");
            
            while(rs2.next())
            {                
                Annonce annonce = new Annonce();
                Timestamp input = rs2.getTimestamp("date_creation");              
                Timestamp input2 = rs2.getTimestamp("date_expiration");
                LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate date2 = input2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDateTime newD = rs2.getTimestamp("date_creation").toLocalDateTime();
                LocalDateTime newG = rs2.getTimestamp("date_expiration").toLocalDateTime();
                java.util.Date ddd = new java.util.Date();
                java.sql.Date ddd2= new java.sql.Date(input.getTime());
                annonce.setId(rs2.getInt("id"));
                annonce.setId_partenaire(rs2.getInt("id_partenaire"));
                annonce.setDomaine(rs2.getString("domaine"));
                annonce.setDescription(rs2.getString("description"));
                annonce.setDate_creation(date);
                annonce.setDate_expiration(date2);

                data.add(annonce);
                System.out.println(annonce.toString());                             
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

    @Override
    public void modifierAnnonce(Annonce a) {
        String sql ="UPDATE annonce SET date_expiration = '"
                    +a.getDate_expiration()+"', domaine= '"+a.getDomaine()+"', description = '"+a.getDescription()+"' WHERE id ="+ a.getId()+";";
        try 
        {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Modifiée");
        } 
        catch (SQLException ex) 
        {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }    }

    @Override
    public List<Annonce> afficherAnnonceParPartenaire(int id_partenaire) {
        List<Annonce> ListAnnonce=new ArrayList <Annonce>();              
       ResultSet rs;
        try 
        {
            String sql="SELECT  * from annonce where id_partenaire = "+id_partenaire+";";
            Statement st2 = conn.createStatement();
            ResultSet  rs2=st2.executeQuery(sql);       
            System.out.println("Affichée");
            
            while(rs2.next())
            {                
                Annonce annonce = new Annonce();
                Timestamp input = rs2.getTimestamp("date_creation");              
                Timestamp input2 = rs2.getTimestamp("date_expiration");
                LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate date2 = input2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDateTime newD = rs2.getTimestamp("date_creation").toLocalDateTime();
                LocalDateTime newG = rs2.getTimestamp("date_expiration").toLocalDateTime();
                java.util.Date ddd = new java.util.Date();
                java.sql.Date ddd2= new java.sql.Date(input.getTime());
                annonce.setId(rs2.getInt("id"));
                annonce.setId_partenaire(rs2.getInt("id_partenaire"));
                annonce.setDomaine(rs2.getString("domaine"));
                annonce.setDescription(rs2.getString("description"));
                annonce.setDate_creation(date);
                annonce.setDate_expiration(date2);

                ListAnnonce.add(annonce);
                System.out.println(annonce.toString());                             
            }
        } 
        
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return ListAnnonce;
    }

    @Override
    public ObservableList<Annonce> rechercherAnnonceParDomaine(String domaine) {
           data = FXCollections.observableArrayList();         
       ResultSet rs;
        try 
        {
            String sql2="SELECT  * from annonce where domaine = '"+domaine+"' ;";
            Statement st2 = conn.createStatement();
            ResultSet  rs2=st2.executeQuery(sql2);       
            System.out.println("Affichée");
            
            while(rs2.next())
            {                
                Annonce annonce = new Annonce();
                Timestamp input = rs2.getTimestamp("date_creation");              
                Timestamp input2 = rs2.getTimestamp("date_expiration");
                LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate date2 = input2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDateTime newD = rs2.getTimestamp("date_creation").toLocalDateTime();
                LocalDateTime newG = rs2.getTimestamp("date_expiration").toLocalDateTime();
                java.util.Date ddd = new java.util.Date();
                java.sql.Date ddd2= new java.sql.Date(input.getTime());
                annonce.setId(rs2.getInt("id"));
                annonce.setId_partenaire(rs2.getInt("id_partenaire"));
                annonce.setDomaine(rs2.getString("domaine"));
                annonce.setDescription(rs2.getString("description"));
                annonce.setDate_creation(date);
                annonce.setDate_expiration(date2);

                data.add(annonce);
                System.out.println(annonce.toString());
                                  
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
    
    @Override
    public ObservableList<Annonce> rechercherAnnonceParDescription(String mot) {
           data = FXCollections.observableArrayList();         
       ResultSet rs;
        try 
        {
            String sql2="select * from annonce where description like '%"+mot+"%' ";
            Statement st2 = conn.createStatement();
            ResultSet  rs2=st2.executeQuery(sql2);       
            System.out.println("Affichée");
            
            while(rs2.next())
            {                
                Annonce annonce = new Annonce();
                Timestamp input = rs2.getTimestamp("date_creation");              
                Timestamp input2 = rs2.getTimestamp("date_expiration");
                LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate date2 = input2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDateTime newD = rs2.getTimestamp("date_creation").toLocalDateTime();
                LocalDateTime newG = rs2.getTimestamp("date_expiration").toLocalDateTime();
                java.util.Date ddd = new java.util.Date();
                java.sql.Date ddd2= new java.sql.Date(input.getTime());
                annonce.setId(rs2.getInt("id"));
                annonce.setId_partenaire(rs2.getInt("id_partenaire"));
                annonce.setDomaine(rs2.getString("domaine"));
                annonce.setDescription(rs2.getString("description"));
                annonce.setDate_creation(date);
                annonce.setDate_expiration(date2);

                data.add(annonce);
                System.out.println(annonce.toString());
                                  
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
    

