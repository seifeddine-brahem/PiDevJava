/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.MyDB;
import Entities.Demande;
import Entities.Utilisateur;
import IServices.IDemande;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author Maha
 */
public class DemandeService implements IDemande
{
      Connection conn;
    
    public DemandeService()
    {
        this.conn=MyDB.getinstance().getConnexion();
    }

    
    @Override
    public void ajouterDemande(Demande d)
    {
        if (CheckDemande(d.getUser().getId()))
        {
            modifierDemande(d);
            System.out.println("yeaaah");
        }
        else {
        String sql ="Insert into demande(nom,prenom,CIN,date_naissance,CIN_image_recto,CIN_image_verso,diplome,photo_etab,num_identifiant,patente,id_user,etat) values ('"+d.getNom()+"','"+d.getPrenom()+"',"
                    +d.getCIN()+",'"+d.getDate_naissance()+"','"+d.getCIN_image_recto()+"','"+d.getCIN_image_verso()+"','"+d.getDiplome()+"','"+d.getPhoto_etab()+"',"+d.getNum_identifiant()+",'"+d.getPatente()+"',"+d.getUser().getId()+",'en attente');";
        try 
        {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Add Done");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout Demande confirmation");
            alert.setHeaderText(null);
            alert.setContentText("demande ajoutée!");
            alert.showAndWait();
        }
        catch (SQLException ex)
        {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }
        }
        
    }

    @Override
    public void supprimerDemande(Demande d)
    {
         String sql ="DELETE FROM demande WHERE id_demande= "+d.getId_demande()+";";
        try 
        {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Delete Demande Done");
        } 
        catch (SQLException ex) 
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }

    @Override
    public void modifierDemande(Demande d)
    {
        String sql ="UPDATE demande SET nom = '"+d.getNom()+"',prenom = '"+d.getPrenom()+
                "',CIN= '"+d.getCIN()+"',date_naissance= '"+d.getDate_naissance()+"',CIN_image_recto= '"+
                d.getCIN_image_recto()+"',CIN_image_verso= '"+d.getCIN_image_verso()+"',diplome= '"+d.getDiplome()+
                "',photo_etab= '"+d.getPhoto_etab()+"',num_identifiant= "+d.getNum_identifiant()+",patente='"+
                d.getPatente()+"'  WHERE id_demande ="+d.getId_demande()+";";
        try 
        {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Update demande done");
        } 
        catch (SQLException ex) 
        {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }
    }

    @Override
    public List<Demande> afficherDemandes()
    {
        List<Demande> ListDemande=new ArrayList <Demande>();
        
        ResultSet rs;
        
        String sql ="select * from demande";
        try 
        {
            Statement stl = conn.createStatement();
            rs=stl.executeQuery(sql); 
            
            System.out.println("Affichage Done");
            while(rs.next())
            {
                Demande demande = new Demande();
                 Timestamp input = rs.getTimestamp("date_naissance");              
                LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDateTime newD = rs.getTimestamp("date_naissance").toLocalDateTime();
                demande.setId_demande(rs.getInt("id_demande"));
                demande.setNom(rs.getString("nom"));
                demande.setPrenom(rs.getString("prenom"));
                demande.setCIN(rs.getInt("CIN"));
                demande.setDate_naissance(date);
                demande.setCIN_image_recto(rs.getString("CIN_image_recto"));
                demande.setCIN_image_verso(rs.getString("CIN_image_verso"));
                demande.setDiplome(rs.getString("diplome"));
                demande.setPhoto_etab(rs.getString("photo_etab"));
                demande.setNum_identifiant(rs.getInt("num_identifiant"));
                demande.setPatente(rs.getString("patente"));
                //demande.setId_user(rs.getInt("id_user"));
                ListDemande.add(demande);
                System.out.println(demande.toString());                
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
       return ListDemande ;
    }

    
    @Override
    public List<Demande> chercherDemandeParNom(String nomUti) 
    {
         List<Demande> ListDemande=new ArrayList <Demande>();              
        ResultSet rs;
        String sql ="select id from fos_user where username = '"+nomUti+"';";

        try 
        {
            Statement stl = conn.createStatement();
            rs=stl.executeQuery(sql);    
            rs.next();
            int iduti= rs.getInt("id");
            System.out.println(iduti);
            String sql2="select * from demande where id_user ="+iduti+";";
            Statement st2 = conn.createStatement();
            ResultSet  rs2=st2.executeQuery(sql2);       
            System.out.println("Affichage Done");
            while(rs.next())
            {
                Demande demande = new Demande();
                 Timestamp input = rs.getTimestamp("date_naissance");              
                LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDateTime newD = rs.getTimestamp("date_naissance").toLocalDateTime();
                demande.setId_demande(rs.getInt("id_demande"));
                demande.setNom(rs.getString("nom"));
                demande.setPrenom(rs.getString("prenom"));
                demande.setCIN(rs.getInt("CIN"));
                demande.setDate_naissance(date);
                demande.setCIN_image_recto(rs.getString("CIN_image_recto"));
                demande.setCIN_image_verso(rs.getString("CIN_image_verso"));
                demande.setDiplome(rs.getString("diplome"));
                demande.setPhoto_etab(rs.getString("photo_etab"));
                demande.setNum_identifiant(rs.getInt("num_identifiant"));
                demande.setPatente(rs.getString("patente"));
                //demande.setId_user(rs.getInt("id_user"));
                ListDemande.add(demande);
                System.out.println(demande.toString());                
            }
        } 
        
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return ListDemande;
    }

    @Override
    public boolean CheckDemande(int id_user)
    {
        String sql = "select * from demande where id_user = "+id_user +";";
        Statement stl;
          try {
              stl = conn.createStatement();
               ResultSet rs = stl.executeQuery(sql);
               if (rs.next()){
                   return true ;
               }
          } catch (SQLException ex) {
              System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
          }
        return false ;
        
    }
    
    
    
    
    @Override
    public void decliner(Demande d) 
    {
        String sql ="UPDATE demande SET etat = 'Refusée' WHERE id_user ="+ d.getId_demande()+";";
        try 
        {
            Statement st2 = conn.createStatement();
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Demande Refusée");
        } 
        catch (SQLException ex) 
        {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }   
    }
    
    
        @Override
    public void accepter (Demande d) 
    {
        String sql ="UPDATE demande SET etat = 'accéptée' WHERE id_demande ="+d.getId_demande()+";";
        String sql2="UPDATE fos_user set roles= 'partenaire'  WHERE id ="+d.getUser().getId()+";";
        try 
        {
            Statement st2 = conn.createStatement();
            st2.executeUpdate(sql2);
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Demande Accéptée");
        } 
        catch (SQLException ex) 
        {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }  
    }
}

