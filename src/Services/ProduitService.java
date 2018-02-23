/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.MyDB;
import Entities.Produit;
import Entities.ProduitSalleDeSport;
import Utils.TypeProduitSalleDeSport;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elbrh
 */
public class ProduitService {
    
        
        
    Connection conn ;

    public ProduitService() {
        this.conn = MyDB.getInstance().getConnexion();
    }
    
    

    public List<Produit> searchProduit(String nom) {
        List<Produit> ls = new ArrayList<>();
        String sql = "select * from  produits where nom Like '%"+nom+ "%';";
     
        try {
            Statement stl = conn.createStatement();
            ResultSet rs =  stl.executeQuery(sql);
            while(rs.next()){
                Produit product = new Produit();
                product.setId(rs.getInt("id_produit"));
                product.setNom(rs.getString("nom"));
                product.setDescription(rs.getString("description"));
                product.setImage(rs.getString("image"));
                product.setPrix(rs.getFloat("prix"));
                System.out.println(product.toString());
                ls.add(product);     
            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }
        return ls;
    }
    
    public List<Produit> showAllProduit() {
                List<Produit> ls = new ArrayList<Produit>();
        try {
             String sql = "select * from  produits p INNER JOIN etablissements e ON p.id_etab = e.id ;";   
            Statement stl = conn.createStatement();
            ResultSet rs =  stl.executeQuery(sql);
            while(rs.next()){
                Produit product = new Produit();
                product.setId(rs.getInt("id_produit"));
                product.setNom(rs.getString("nom"));
                product.setDescription(rs.getString("description"));
                product.setImage(rs.getString("image"));
                product.setPrix(rs.getFloat("prix"));
                product.getEtab().setId(rs.getInt("e.id"));
                product.getEtab().setAdresse(rs.getString("e.adresse"));
                product.getEtab().setNom(rs.getString("e.nom"));
                product.getEtab().setNum(rs.getInt("e.numero"));
                System.out.println(product.toString());
                ls.add(product);     
            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }
        return ls;
    }
    
    public String checkType(Produit p){
       try {
             String sqlHerbo = "select * from  produit_herbo p WHERE p.id_produit ="+p.getId()+" ;";   
            Statement stl = conn.createStatement();
            ResultSet rs =  stl.executeQuery(sqlHerbo);
            if(rs.next()){
                return "herbo";
            }
            String sqlPara = "select * from  produit_parapharmacie p WHERE p.id_produit ="+p.getId()+" ;";   
             rs =  stl.executeQuery(sqlPara);
            if(rs.next()){
                return "Para";
            }
            String sqlPharma = "select * from  produit_pharmaceutique p WHERE p.id_produit ="+p.getId()+" ;";   
             rs =  stl.executeQuery(sqlPharma);
            if(rs.next()){
                return "Pharma";
            }
            String sqlSalee = "select * from  produit_salledesport p WHERE p.id_produit ="+p.getId()+" ;";   
             rs =  stl.executeQuery(sqlSalee);
            if(rs.next()){
                return "Salle";
            }           
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }
            return null ;
    }
    
}
