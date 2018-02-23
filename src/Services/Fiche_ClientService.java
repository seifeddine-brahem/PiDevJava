/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.MyDB;
import Entities.Fiche_Client;
import IServices.IFiche_Client;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class Fiche_ClientService implements IFiche_Client{
    
    Connection conn;

    public Fiche_ClientService() {
        this.conn=MyDB.getinstance().getConnexion();
    }
    

    @Override
    public void ajouterFicheClient(Fiche_Client fc) {
        String sql ="Insert into fiche_client(CIN,nom,prenom,email,id_user,id_etablissement) values ('"+fc.getCIN()+"','"+fc.getNom()+"','"+fc.getPrenom()+"','"+fc.getEmail()+"','"+fc.getId_user()+"',"
                    +fc.getId_etablissement()+");";
        try 
        {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Ajout effectué");
        }
        catch (SQLException ex)
        {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }    }

    @Override
    public void supprimerFicheClient(Fiche_Client fc) {
        String sql ="DELETE FROM fiche_client WHERE id_user= "+fc.getId_user()+" AND id_etablissement= "+fc.getId_etablissement()+"; ";
        try 
        {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Suppression de la fiche effectuée");
        } 
        catch (SQLException ex) 
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    @Override
    public void modifierFicheClient(Fiche_Client fc) {
        String sql = "UPDATE fiche_client SET CIN = '"+fc.getCIN()+"',nom= '"
                    +fc.getNom()+"',prenom ='"+fc.getPrenom()+"',email= '"
                    +fc.getEmail()+"' WHERE id_user= "+fc.getId_user()+" AND id_etablissement= "+fc.getId_etablissement()+"; ";
        
        /*select id from user where roles='user' and id='id_partenaire';*/
          /*select * from fiche_client  where roles='user' and id='id_partenaire';*/
        try {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Modification effectuée");
        } catch (SQLException ex) {
           System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }    }
 
    @Override
    public Fiche_Client afficherFicheClient(Fiche_Client fc) {
        Fiche_Client fiche_client = new Fiche_Client() ;
        ResultSet rs ;
        String sql ="SELECT * FROM fiche_client WHERE id_user= "+fc.getId_user()+" AND id_etablissement= "+fc.getId_etablissement()+"; ";
        try {
            Statement stl = conn.createStatement();
            rs = stl.executeQuery(sql);
            rs.next();
            fiche_client = new Fiche_Client(rs.getInt("CIN"), rs.getString("nom"),rs.getString("prenom"),rs.getString("email") , rs.getInt("Id_user"), rs.getInt("Id_etablissement"));
            System.out.println(fiche_client.toString());
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return fiche_client;    
    }

    @Override
    public Fiche_Client rechercherFiche_Client(String nom) {
                Fiche_Client fiche_client = new Fiche_Client() ;
                        ResultSet rs ;
         String sql ="SELECT * FROM fiche_client WHERE nom = '"+nom+"';";
       try {
            Statement stl = conn.createStatement();
            rs = stl.executeQuery(sql);
            rs.next();
            fiche_client = new Fiche_Client(rs.getInt("CIN"), rs.getString("nom"),rs.getString("prenom"),rs.getString("email") , rs.getInt("Id_user"), rs.getInt("Id_etablissement"));
            System.out.println(fiche_client.toString());
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return fiche_client;     }

    @Override
    public List<Fiche_Client> afficherToutFicheClient() {
      List<Fiche_Client> ListFiche=new ArrayList <Fiche_Client>();
        
        //ResultSet rs;
        
        String sql ="select * from fiche_client";
        try 
        {
            Statement stl = conn.createStatement();
             ResultSet rs=stl.executeQuery(sql);          
            System.err.println("Display Done");
            while(rs.next())
            {
                Fiche_Client fiche = new Fiche_Client();
                fiche.setId_user(rs.getInt("id_user"));
                fiche.setId_etablissement(rs.getInt("id_etablissement"));
                fiche.setNom(rs.getString("nom"));
                fiche.setPrenom(rs.getString("prenom"));
                fiche.setEmail(rs.getString("email"));
                fiche.setCIN(rs.getInt("CIN"));
                ListFiche.add(fiche);
                System.out.println(fiche.toString());                
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
       return ListFiche ;
    }

    
}
