/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.MyDB;
import IServices.ICommande;
import Entities.Commande;
import Entities.DetailCommande;
import Entities.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elbrh
 */
public class CommandeService implements ICommande{
    Connection conn ;

    public CommandeService() {
        this.conn = MyDB.getInstance().getConnexion();
    }

    @Override
    public int passCommande(List<Commande> p) {
        try{
            System.out.println(java.sql.Date.valueOf(LocalDate.now()));
   
            String sql = "insert into commande(date,type) Values('"+java.sql.Date.valueOf(LocalDate.now())+"','demande');"; 
            System.out.println(sql);
            PreparedStatement stl = conn.prepareStatement(sql,
                                    Statement.RETURN_GENERATED_KEYS);
                stl.executeUpdate();
                ResultSet generatedKeys = stl.getGeneratedKeys();
                generatedKeys.next();
                int id = generatedKeys.getInt(1);
                System.out.println(p.size());
                 Statement stm = conn.createStatement() ;
            for (Commande pr : p) {
                String sql2 = "insert into detail_commande(id_commande,id_produit,quantite) values("+id+","+pr.getProduit().getId()+","
                                +pr.getQuantite()+");";
                System.out.println(sql2);;
                stm.addBatch(sql2);                
                System.out.println("commande succes");
            }
            stm.executeBatch();
            return id ;
        }catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
        } 
       return 0 ;
    }

    @Override
    public void annulerCommande(Commande c) {
        try{            
            String sql = "UPDATE detail_commande SET statu = 'annule' WHERE id_commande ="+c.getId_commande()+";";
            Statement stm = conn.createStatement();
            int res = stm.executeUpdate(sql);
 
        }catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
        } 
        
    } 

    @Override
    public List<Commande> showCommandebyEtab(int id_etab) {
        List<Commande> ls = new ArrayList<>();
        try{            
            String sql = "Select * from detail_commande dc  INNER JOIN produits p on p.id_produit = dc.id_produit where p.id_etab = id_etab taa connected user";
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            while(res.next()) {
                Commande c = new Commande();
                c.setQuantite(res.getInt("quantite"));
                c.getProduit().setId(res.getInt("id_produit")); 
                c.getProduit().setDescription(res.getString("description"));
                c.getProduit().setNom("nom");
                c.getProduit().setPrix(res.getFloat("prix"));  
                System.out.println(c.toString());
                ls.add(c);
            } 
        }catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
        } 
        return ls ;
    }

    @Override
    public List<Commande> showMyCommandes(int id_user) {
        List<Commande> ls = new ArrayList<>();
        try{            
            System.out.println("aaaa");
            String sql = "SELECT * from detail_commande dc INNER JOIN commande c on"
                    + " dc.id_commande = c.id_commande INNER join produits p on"
                    + " dc.id_produit = p.id_produit INNER JOIN etablissements e on"
                    + " e.id = p.id_etab Where c.id_user = 1;";
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            while(res.next()) {
                System.out.println("aaaa");
                Commande c = new Commande();
                c.setId_commande(res.getInt("c.id_commande"));
                c.setQuantite(res.getInt("quantite"));
                c.getProduit().setId(res.getInt("id_produit")); 
                c.getProduit().setDescription(res.getString("description"));
                c.getProduit().setNom("nom");
                c.getProduit().setPrix(res.getFloat("prix"));  
                c.getProduit().getEtab().setNom(res.getString("e.nom"));
                    java.sql.Date date = res.getDate("c.date");
                c.setDate(date.toLocalDate());
                System.out.println(c.toString());
                ls.add(c);
            }
 
        }catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
        } 
        return ls ;
    } 

    @Override
    public List<DetailCommande> showProductParCommande(Commande c) {
        List<DetailCommande> ls = new ArrayList<>();
        try{            
            String sql = "Select * from detail_commande dc  INNER JOIN produits p on "
                    + "p.id_produit = dc.id_produit where dc.id_commande ="+c.getId_commande()+";";
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);
            while(res.next()) {
                DetailCommande p = new DetailCommande();
                p.getP().setId(res.getInt("id_produit"));
                p.getP().setNom(res.getString("nom"));
                p.getP().setDescription(res.getString("description"));
                p.getP().setPrix(res.getFloat("prix"));
                p.getP().setImage(res.getString("image"));
                p.setQuantite(res.getInt("quantite"));
                
                
                System.out.println(c.toString());
                ls.add(p);
            }
        }catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
        } 
        return ls ;
    }



 
    
    
    
    
    
    
    
}
