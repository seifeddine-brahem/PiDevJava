/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.MyDB;
import Entities.Etablissement;
import Entities.Hopitaux;
import IServices.IHerbosristerie;
import IServices.IHopitaux;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ellouze Skander
 */
public class HopitauxService implements IHopitaux{
    
        Connection connexion;
   
    public HopitauxService() {
        connexion = MyDB.getinstance().getConnexion();
    }

    @Override
    public void ajouterHopitaux(Hopitaux c) {
     try {

         String query1 = "INSERT INTO Etablissements (nom, adresse, date_ouverture, date_fermeture, email, numero,fax,page_facebook,site_web,heure_ouverture,heure_fermeture,image,id_user) "
                    + "values ( '"+c.getNom()+"','"+c.getAdresse()+"','"+c.getDate_ouverture()+"','"+c.getDate_fermeture()+"','"+c.getEmail()+"',"+c.getNum()+","+c.getFax()+",'"+c.getPage_fb()+"','"+c.getSite_web()+"','"+c.getHeure_ouverture()+"','"+c.getHeure_fermeture()+"','"+c.getImage()+"',1 );";
              PreparedStatement stl= connexion.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
            stl.executeUpdate();
              ResultSet  generatedKeys = stl.getGeneratedKeys();
              generatedKeys.next();
            String query = "INSERT INTO hopitaux (type,urgence,cnam,id_etab) "
                    + "values ('"+c.getType()+"','"+c.getUrgence()+"','"+c.getCnam()+"',"+generatedKeys.getInt(1)+" );";
            Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void supprimerHopitaux(Hopitaux c) {
     try {
            
            String sql = "DELETE FROM hopitaux WHERE id=?";
            
            PreparedStatement statement = null;
            statement = connexion.prepareStatement(sql);
            statement.setInt(1, c.getId());
            
            int rowsDeleted = 0;
       
                rowsDeleted = statement.executeUpdate();
           
            if (rowsDeleted > 0) {
                System.out.println("successfully!");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EtablissementService.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public void modifierHopitaux(Hopitaux c) {
      String sql ="UPDATE hopitaux SET type ='"+c.getType()+"','"+c.getUrgence()+"','"+c.getCnam()+"' WHERE id ="+ c.getId()+";";
       String sql2="UPDATE etablissements SET nom='"+c.getNom()+"',adresse='"+c.getAdresse()+"',date_ouverture='"+c.getDate_ouverture()+"',date_fermeture='"+c.getDate_fermeture()+"',email='"+c.getEmail()+"',numero="+c.getNum()+",fax="+c.getFax()+",page_facebook='"+c.getPage_fb()+"',site_web='"+c.getPage_fb()+"',heure_ouverture="+c.getHeure_ouverture()+",heure_fermeture="+c.getHeure_fermeture()+",image='"+c.getImage()+"' where id='"+ c.getId_etab()+"';";
          try {
            Statement st2=connexion.createStatement();
            Statement stl = connexion.createStatement();
            stl.executeUpdate(sql);
            st2.executeUpdate(sql2);
            System.out.println("Update done"); 
        } catch (SQLException ex) {
           System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
