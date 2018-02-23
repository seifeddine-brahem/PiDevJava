
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.MyDB;
import Entities.Produit;
import IServices.IProduitHerbo;
import Utils.CategorieProduitHerbo;
import Entities.ProduitHerbo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elbrh
 */
public class ProduitHerboService implements IProduitHerbo {

    Connection conn;

    public ProduitHerboService() {
        this.conn = MyDB.getInstance().getConnexion();
    }

    @Override
    public void addProduit(ProduitHerbo p) {
        try {
//            String sqlp = "Insert into produits(nom,description,image,prix,id_etab) values ('" + p.getNom() + "','"
//                    + p.getDescription() + "','" + p.getImage() + "'," + p.getPrix() +","+p.getEtab().getId()+");";
            String sqlp = "Insert into produits(nom,description,image,prix,id_etab) values ('" + p.getNom() + "','"
                    + p.getDescription() + "','" + p.getImage() + "'," + p.getPrix() + "," + 1 + ");";
            PreparedStatement stl = conn.prepareStatement(sqlp,
                    Statement.RETURN_GENERATED_KEYS);
            stl.executeUpdate();
            ResultSet generatedKeys = stl.getGeneratedKeys();
            generatedKeys.next();
            String sqlpara = "Insert into produit_herbo(id_produit,bio,marque,categorie) values (" + generatedKeys.getInt(1) + ","
                    + p.getBio() + ",'" + p.getMarque() + "','" + p.getCategorie() + "');";
            Statement stm2 = conn.createStatement();
            stm2.executeUpdate(sqlpara);
            System.out.println("Add Produit Herbo Done");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    @Override
    public void editProduit(ProduitHerbo p) {
        try {
            String sqlp = "UPDATE produits SET nom = '" + p.getNom() + "',description = '" + p.getDescription() + "',"
                    + "image = '" + p.getImage() + "',prix =" + p.getPrix() + " WHERE  id_produit = " + p.getId() + ";";
            Statement stl = conn.createStatement();
            stl.addBatch(sqlp);
            String sqlpharma = "UPDATE produit_herbo SET marque ='" + p.getMarque() + "',bio =" + p.getBio()
                    + ",categorie ='" + p.getCategorie() + "' WHERE id_produit = " + p.getId() + ";";
            stl.addBatch(sqlpharma);
            int[] executeBatch = stl.executeBatch();
            System.out.println("Update Produit Pharmacitique Done");
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }
    }

    @Override
    public void deleteProduit(ProduitHerbo p) {
        String sql = "DELETE FROM produit_herbo WHERE id_produit = " + p.getId() + ";";
        String sql2 = "DELETE FROM produits WHERE id_produit = " + p.getId() + ";";
        try {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            Statement stl2 = conn.createStatement();
            stl2.executeUpdate(sql2);
            System.out.println("Delete Produit Parapharmacitique Done");
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }
    }

    @Override
    public List<ProduitHerbo> showProduit(ProduitHerbo p) {
        List<ProduitHerbo> ls = new ArrayList<ProduitHerbo>();
        try {
            String sql = "select * from produit_herbo p INNER JOIN produits h on p.id_produit ="
                    + " h.id_produit where p.id_produit = " + p.getId();
            Statement stl = conn.createStatement();
            ResultSet rs = stl.executeQuery(sql);
            while (rs.next()) {
                ProduitHerbo herbo = new ProduitHerbo();
                herbo.setId(rs.getInt("id_produit"));
                herbo.setBio(rs.getBoolean("bio"));
                herbo.setCategorie(CategorieProduitHerbo.valueOf(rs.getString("categorie")));
                herbo.setMarque(rs.getString("marque"));
                herbo.setNom(rs.getString("nom"));
                herbo.setDescription(rs.getString("description"));
                herbo.setImage(rs.getString("image"));
                herbo.setPrix(rs.getFloat("prix"));
                System.out.println(herbo.toString());
                ls.add(herbo);
            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }
        return ls;
    }

    @Override
    public List<ProduitHerbo> searchProduit(String nom) {
        List<ProduitHerbo> ls = new ArrayList<ProduitHerbo>();
        ResultSet rs;
        String sql = "select * from  produits p  INNER JOIN produit_herbo h on p.id_produit = h.id_produit"
                + " where p.nom = '" + nom + "';";
        try {
            Statement stl = conn.createStatement();
            rs = stl.executeQuery(sql);
            while (rs.next()) {
                ProduitHerbo herbo = new ProduitHerbo();
                herbo.setId(rs.getInt("id_produit"));
                herbo.setNom(rs.getString("nom"));
                herbo.setBio(rs.getBoolean("bio"));
                herbo.setCategorie(CategorieProduitHerbo.valueOf(rs.getString("categorie")));
                herbo.setDescription(rs.getString("description"));
                System.out.println(herbo.toString());
                ls.add(herbo);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return ls;
    }

    public List<ProduitHerbo> searchAllProduit() {
        List<ProduitHerbo> ls = new ArrayList<ProduitHerbo>();
        ResultSet rs;
        String sql = "select * from  produits p  INNER JOIN produit_herbo h on p.id_produit = h.id_produit;";
        try {
            Statement stl = conn.createStatement();
            rs = stl.executeQuery(sql);
            while (rs.next()) {
                ProduitHerbo herbo = new ProduitHerbo();
                herbo.setId(rs.getInt("id_produit"));
                herbo.setNom(rs.getString("nom"));
                herbo.setBio(rs.getBoolean("bio"));
                herbo.setCategorie(CategorieProduitHerbo.valueOf(rs.getString("categorie")));
                herbo.setDescription(rs.getString("description"));
                herbo.setMarque(rs.getString("marque"));
                herbo.setImage(rs.getString("image"));
                herbo.setPrix(rs.getFloat("prix"));
                System.out.println(herbo.toString());
                ls.add(herbo);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return ls;
    }

    public List<ProduitHerbo> searchProduitByEtab(Produit p) {
        List<ProduitHerbo> ls = new ArrayList<ProduitHerbo>();
        ResultSet rs;
        String sql = "select * from  produits p  INNER JOIN produit_herbo h on p.id_produit = h.id_produit Where id_etab ="+
                    p.getEtab().getId()+" ;";
        try {
            Statement stl = conn.createStatement();
            rs = stl.executeQuery(sql);
            while (rs.next()) {
                ProduitHerbo herbo = new ProduitHerbo();
                herbo.setId(rs.getInt("id_produit"));
                herbo.setNom(rs.getString("nom"));
                herbo.setBio(rs.getBoolean("bio"));
                herbo.setCategorie(CategorieProduitHerbo.valueOf(rs.getString("categorie")));
                herbo.setDescription(rs.getString("description"));
                herbo.setMarque(rs.getString("marque"));
                herbo.setImage(rs.getString("image"));
                herbo.setPrix(rs.getFloat("prix"));
                System.out.println(herbo.toString());
                ls.add(herbo);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return ls;
    }

}
