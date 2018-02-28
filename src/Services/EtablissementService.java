/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.MyDB;
import Entities.Etablissement;
import IServices.IEtablissement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ellouze Skander
 */
public class EtablissementService implements IEtablissement 
{

    Connection connexion;
    private ObservableList<Etablissement> data;
   
    public EtablissementService()
    {
        connexion = MyDB.getinstance().getConnexion();
    }
    
    
   
    @Override
    public ObservableList<Etablissement> chercherEtablissementParNom(String nom) {
        data = FXCollections.observableArrayList();
        List<Etablissement> ListEtablissement=new ArrayList <Etablissement>();              
        ResultSet rs;
        String sql ="select * from etablissements where nom LIKE '%"+nom+"%';";

        try 
        {
            Statement stl = connexion.createStatement();

            ResultSet  rs2=stl.executeQuery(sql);       
            System.out.println("Affichage Done");
            while(rs2.next())
            {
                Etablissement e = new Etablissement();
                e.setId(rs2.getInt("id"));
                e.setNom(rs2.getString("nom"));
                e.setAdresse(rs2.getString("adresse"));
                e.setDate_ouverture(rs2.getString("date_ouverture"));
                e.setDate_fermeture(rs2.getString("date_fermeture"));
                e.setEmail(rs2.getString("email"));
                e.setNum(rs2.getInt("numero"));
                e.setFax(rs2.getInt("fax"));
                e.setPage_fb(rs2.getString("page_facebook"));
                e.setSite_web(rs2.getString("site_web"));
                e.setHeure_ouverture(rs2.getInt("heure_ouverture"));
                e.setHeure_fermeture(rs2.getInt("heure_fermeture"));
              

                data.add(e);
                System.out.println(e);               
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
    public List<Etablissement> chercherEtablissementParAdresse(String adresse) {
     List<Etablissement> ListEtablissement=new ArrayList <Etablissement>();              
        ResultSet rs;
        String sql ="select * from etablissements where adresse='"+adresse+"';";

        try 
        {
            Statement stl = connexion.createStatement();

            ResultSet  rs2=stl.executeQuery(sql);       
            System.out.println("Affichage Done");
            while(rs2.next())
            {
                Etablissement e = new Etablissement();
                e.setId(rs2.getInt("id"));
                e.setNom(rs2.getString("nom"));
                e.setAdresse(rs2.getString("adresse"));
                e.setDate_ouverture(rs2.getString("date_ouverture"));
                e.setDate_fermeture(rs2.getString("date_fermeture"));
                e.setEmail(rs2.getString("email"));
                e.setNum(rs2.getInt("numero"));
                e.setFax(rs2.getInt("fax"));
                e.setPage_fb(rs2.getString("page_facebook"));
                e.setSite_web(rs2.getString("site_web"));
                e.setHeure_ouverture(rs2.getInt("heure_ouverture"));
                e.setHeure_fermeture(rs2.getInt("heure_fermeture"));
                e.getUser().setId(rs2.getInt(1));

                ListEtablissement.add(e);
                System.out.println(e);               
            }
     }  catch (SQLException ex) {
             System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    
            return ListEtablissement;
        
    }

    @Override
    public List<Etablissement> afficherEtablissement() {
    List<Etablissement> ListEtablissement=new ArrayList <Etablissement>();              
        ResultSet rs;
        String sql ="select * from etablissements;";

        try 
        {
            Statement stl = connexion.createStatement();

            ResultSet  rs2=stl.executeQuery(sql);       
            System.out.println("Affichage Done");
            while(rs2.next())
            {
                Etablissement e = new Etablissement();
                e.setId(rs2.getInt("id"));
                e.setNom(rs2.getString("nom"));
                e.setAdresse(rs2.getString("adresse"));
                e.setDate_ouverture(rs2.getString("date_ouverture"));
                e.setDate_fermeture(rs2.getString("date_fermeture"));
                e.setEmail(rs2.getString("email"));
                e.setNum(rs2.getInt("numero"));
                e.setFax(rs2.getInt("fax"));
                e.setPage_fb(rs2.getString("page_facebook"));
                e.setSite_web(rs2.getString("site_web"));
                e.setHeure_ouverture(rs2.getInt("heure_ouverture"));
                e.setHeure_fermeture(rs2.getInt("heure_fermeture"));
                e.setImage(rs2.getString("image"));
//                e.getUser().setId(rs2.getInt(1));

                ListEtablissement.add(e);
                System.out.println(e);               
            }
     }  catch (SQLException ex) {
            Logger.getLogger(EtablissementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
            return ListEtablissement;
}
        public void ajouterEtablissement(Etablissement e) {
        
         try {
             
            String query1 = "INSERT INTO Etablissements (nom, adresse, date_ouverture, date_fermeture, email, numero,fax,page_facebook,site_web,heure_ouverture,heure_fermeture,image,id_user) "
                     + "values ( '"+e.getNom()+"','"+e.getAdresse()+"','"+e.getDate_ouverture()+"','"+e.getDate_fermeture()+"','"+e.getEmail()+"',"+e.getNum()+","+e.getFax()+",'"+e.getPage_fb()+"','"+e.getSite_web()+"','"+e.getHeure_ouverture()+"','"+e.getHeure_fermeture()+"','"+e.getImage()+"',1 );";
            Statement stm1= connexion.createStatement();
            stm1.executeUpdate(query1);
            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
         }
        
        
        
         @Override
    public void supprimerEtablissement(Etablissement e) 
    {
        String sql ="DELETE FROM Etablissements WHERE id= "+e.getId()+";";
        try 
        {
            Statement stl = connexion.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Delete Etablissement Done");
        } 
        catch (SQLException ex) 
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }
    
    public String findNameById(int id)
    {
        ResultSet rs;
        String sql ="select * from etablissements where id ="+id+";";
        String nom = null;
        try 
        {
            Statement stl = connexion.createStatement();

            ResultSet  rs2=stl.executeQuery(sql);       
            System.out.println("Affichage Done");
            while(rs2.next())
            {
                
                nom=(rs2.getString("nom"));
            }
            return nom;
        } 
        catch (SQLException ex) 
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return nom;
    }
        
    
    public String retourNom(Etablissement e)
    {
        return e.getNom();
    }
        
    public Etablissement etabById(int id)
    {
        
         ResultSet rs;
        String sql ="select * from etablissements where id_user ="+id+";";
        Etablissement e= new Etablissement();
        try 
        {
            Statement stl = connexion.createStatement();

            ResultSet  rs2=stl.executeQuery(sql);       
          
            while(rs2.next())
            {
                e.setId(rs2.getInt("id"));
                e.setNom(rs2.getString("nom"));
                e.setAdresse(rs2.getString("adresse"));
                e.setDate_ouverture(rs2.getString("date_ouverture"));
                e.setDate_fermeture(rs2.getString("date_fermeture"));
                e.setEmail(rs2.getString("email"));
                e.setNum(rs2.getInt("numero"));
                e.setFax(rs2.getInt("fax"));
                e.setPage_fb(rs2.getString("page_facebook"));
                e.setSite_web(rs2.getString("site_web"));
                e.setHeure_ouverture(rs2.getInt("heure_ouverture"));
                e.setHeure_fermeture(rs2.getInt("heure_fermeture"));
                e.setImage(rs2.getString("image"));
            }
            
               } 
        catch (SQLException ex) 
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return e;
    }

}