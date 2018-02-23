/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.MyDB;
import Entities.Annonce;
import Entities.Candidature;
import Entities.Utilisateur;
import IServices.ICandidature;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author user
 */
public class CandidatureService implements ICandidature{

     Connection conn;

   private ObservableList<Candidature> data;
    public CandidatureService() {
        this.conn=MyDB.getinstance().getConnexion();
    }
    
    @Override
    public void postuler(Candidature c) {
        String sql ="INSERT INTO candidature (id_utilisateur, id_partenaire, date_postulation,etat,niveau_etude,cv) values ("+c.getId_utilisateur()+","+c.getId_partenaire()+",'"+c.getDate_postulation()+"','en attente','"+c.getNiveau_etude()+"','"+c.getCv()+"');";
        try 
        {
            System.out.println(sql);
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Ajoutée avec succès!");
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Ajout candidature confirmation");
//            alert.setHeaderText(null);
//            alert.setContentText("Votre candidature a été bien effectuée!");
//            alert.showAndWait();
            
        }
       
        catch (SQLException ex)
        {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }              
    }

    @Override
    public void accepter(Candidature c) {
        String sql ="UPDATE candidature SET etat = 'acceptée' WHERE id ="+ c.getId()+";";
        try 
        {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Candidature acceptée");
        } 
        catch (SQLException ex) 
        {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }  
    }

    @Override
    public void decliner(Candidature c) {
        String sql ="UPDATE candidature SET etat = 'refusée' WHERE id ="+ c.getId()+";";
        try 
        {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Candidature refusée");
        } 
        catch (SQLException ex) 
        {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }  
    }

    @Override
    public List<Candidature> afficherToutesLesCandidatures(int id_partenaire) {
         List<Candidature> ListCandidature=new ArrayList <Candidature>();              
       ResultSet rs;
        try 
        {
            String sql="SELECT  * from candidature where id_partenaire = "+id_partenaire+";";
            Statement st2 = conn.createStatement();
            ResultSet  rs2=st2.executeQuery(sql);       
            System.out.println("Affichée");
            
            while(rs2.next())
            {                
                Candidature candidature = new Candidature();
                Timestamp input = rs2.getTimestamp("date_postulation");
                LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDateTime newD = rs2.getTimestamp("date_postulation").toLocalDateTime();
//                java.util.Date ddd = new java.util.Date();
//                java.sql.Date ddd2= new java.sql.Date(input.getTime());
                candidature.setId(rs2.getInt("id"));
                candidature.setId_utilisateur(rs2.getInt("id_utilisateur"));
                candidature.setId_partenaire(rs2.getInt("id_partenaire"));
                candidature.setDate_postulation(date);
                candidature.setEtat(rs2.getString("etat"));
                candidature.setNiveau_etude(rs2.getString("niveau_etude"));
                candidature.setCv(rs2.getString("cv"));

                ListCandidature.add(candidature);
                System.out.println(candidature.toString());                             
            }
        } 
        
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return ListCandidature;
    }

   
    
    @Override
    public String getUtilisateur(int id_utilisateur ) 
    {
       Utilisateur utilisateur =new Utilisateur();
       String s="" ;
                      
        ResultSet rs;
        String sql ="select nom, prenom from fos_user where id='"+id_utilisateur+"';";

          try 
        {
            Statement stl = conn.createStatement();
            rs=stl.executeQuery(sql);          
            System.out.println("Affichage Done");
            while(rs.next())
            {
              utilisateur.setId(id_utilisateur);
              utilisateur.setNom(rs.getString("nom"));
              utilisateur.setPrenom(rs.getString("prenom"));
              //System.out.println(utilisateur.toString());
              s= rs.getString("nom") + " " + rs.getString("prenom");
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return s;
    }

    
    @Override
    public String getUtilisateurAdresse(int id_utilisateur ) 
    {
       Utilisateur utilisateur =new Utilisateur();
       String s="" ;
                      
        ResultSet rs;
        String sql ="select adresse from fos_user where id='"+id_utilisateur+"';";

          try 
        {
            Statement stl = conn.createStatement();
            rs=stl.executeQuery(sql);          
            System.out.println("Affichage Done");
            while(rs.next())
            {
              utilisateur.setId(id_utilisateur);
              utilisateur.setAdresse(rs.getString("adresse"));
              //System.out.println(utilisateur.toString());
              s= rs.getString("adresse") ;
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return s;
    }

    @Override
    public ObservableList<Candidature> rechercherCandidatureParNiveauEtude(String niveau_etude) {
        data = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            String sql2 = "SELECT  * from candidature where niveau_etude = '" + niveau_etude + "' ;";
            Statement st2 = conn.createStatement();
            ResultSet rs2 = st2.executeQuery(sql2);
            System.out.println("Affichée");

            while (rs2.next()) {
                Candidature candidature = new Candidature();
               
                Timestamp input = rs2.getTimestamp("date_postulation");
                LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDateTime newD = rs2.getTimestamp("date_postulation").toLocalDateTime();
                candidature.setEtat(rs2.getString("etat"));
                candidature.setNiveau_etude(rs2.getString("niveau_etude"));
                candidature.setDate_postulation(date);

                data.add(candidature);
                System.out.println(candidature.toString());

            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return data;
    }

    @Override
    public List<Candidature> chercherCandidatureParNiveauEtude(String niveau_etude) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
