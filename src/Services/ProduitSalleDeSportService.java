/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.MyDB;
import Entities.Produit;
import IServices.IProduitSalleDeSport;
import Utils.TypeProduitSalleDeSport;
import Entities.ProduitSalleDeSport;
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
public class ProduitSalleDeSportService implements IProduitSalleDeSport {

    Connection conn;

    public ProduitSalleDeSportService() {
        this.conn = MyDB.getInstance().getConnexion();
    }

    @Override
    public void addProduit(ProduitSalleDeSport p) {
        try {
            System.out.println("in add");
//            String sqlp = "Insert into produits(nom,description,image,prix,id_etab) values ('" + p.getNom() + "','"
//                    + p.getDescription() + "','" + p.getImage() + "'," + p.getPrix() +","+p.getEtab().getId()+");";
            String sqlp = "Insert into produits(nom,description,image,prix,id_etab) values ('" + p.getNom() + "','"
                    + p.getDescription() + "','" + p.getImage() + "'," + p.getPrix() + "," + 1 + ");";
            PreparedStatement stl = conn.prepareStatement(sqlp,
                    Statement.RETURN_GENERATED_KEYS);
            stl.executeUpdate();
            ResultSet generatedKeys = stl.getGeneratedKeys();
            generatedKeys.next();
            String sqlpara = "Insert into produit_salledesport(id_produit,type) values (" + generatedKeys.getInt(1) + ",'"
                    + p.getType() + "');";
            Statement stm2 = conn.createStatement();
            stm2.executeUpdate(sqlpara);
            System.out.println("Add Produit Salle De Sport Done");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    @Override
    public void editProduit(Entities.ProduitSalleDeSport p) {
        try {
            String sqlp = "UPDATE produits SET nom = '" + p.getNom() + "',description = '" + p.getDescription() + "',"
                    + "image = '" + p.getImage() + "',prix =" + p.getPrix() + "WHERE  id_produit =" + p.getId() + ";";
            Statement stl = conn.createStatement();
            stl.addBatch(sqlp);
            String sqlpharma = "UPDATE produit_salledesport SET type ='" + p.getType()
                    + "' WHERE id_produit = " + p.getId() + ";";
            stl.addBatch(sqlpharma);
            int[] executeBatch = stl.executeBatch();
            System.out.println("Update Produit Salle De Sport Done");
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }
    }

    @Override
    public void deleteProduit(Entities.ProduitSalleDeSport p) {
        String sql = "DELETE FROM produit_salledesport WHERE id_produit = " + p.getId() + ";";
        String sql2 = "DELETE FROM produits WHERE id_produit = " + p.getId() + ";";
        try {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            Statement stl2 = conn.createStatement();
            stl2.executeUpdate(sql2);
            System.out.println("Delete Produit Salle De Sport Done");
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }
    }

    @Override
    public List<Entities.ProduitSalleDeSport> showProduit(ProduitSalleDeSport p) {
        List<ProduitSalleDeSport> ls = new ArrayList<ProduitSalleDeSport>();
        try {
            String sql = "select * from  produits p  INNER JOIN produit_salledesport h on p.id_produit = h.id_produit where p.id_produit = '" + p.getId() + "';";
            Statement stl = conn.createStatement();
            ResultSet rs = stl.executeQuery(sql);
            while (rs.next()) {
                ProduitSalleDeSport product = new ProduitSalleDeSport();
                product.setId(rs.getInt("id_produit"));
                String sql2 = "select * from produits WHERE id_produit=" + product.getId();
                Statement stl2 = conn.createStatement();
                ResultSet rs2 = stl2.executeQuery(sql2);
                rs2.next();
                product.setType(TypeProduitSalleDeSport.valueOf(rs.getString("type")));
                product.setNom(rs2.getString("nom"));
                product.setDescription(rs2.getString("description"));
                product.setImage(rs2.getString("image"));
                product.setPrix(rs2.getFloat("prix"));
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

    @Override
    public List<ProduitSalleDeSport> searchProduit(String nom) {
        List<ProduitSalleDeSport> ls = new ArrayList<>();
        String sql = "select * from  produits p  INNER JOIN produit_salledesport h on p.id_produit = h.id_produit where p.nom Like '%" + nom + "%';";

        try {
            Statement stl = conn.createStatement();
            ResultSet rs = stl.executeQuery(sql);
            while (rs.next()) {
                ProduitSalleDeSport product = new ProduitSalleDeSport();
                product.setId(rs.getInt("id_produit"));
                String sql2 = "select * from produits WHERE id_produit=" + product.getId();
                Statement stl2 = conn.createStatement();
                ResultSet rs2 = stl2.executeQuery(sql2);
                rs2.next();
                product.setType(TypeProduitSalleDeSport.valueOf(rs.getString("type")));
                product.setNom(rs2.getString("nom"));
                product.setDescription(rs2.getString("description"));
                product.setImage(rs2.getString("image"));
                product.setPrix(rs2.getFloat("prix"));
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

    public List<Entities.ProduitSalleDeSport> showAllProduit() {
        List<ProduitSalleDeSport> ls = new ArrayList<ProduitSalleDeSport>();
        try {
            String sql = "select * from  produits p  INNER JOIN produit_salledesport h on p.id_produit = h.id_produit ;";
            Statement stl = conn.createStatement();
            ResultSet rs = stl.executeQuery(sql);
            while (rs.next()) {
                ProduitSalleDeSport product = new ProduitSalleDeSport();
                product.setId(rs.getInt("id_produit"));
                String sql2 = "select * from produits WHERE id_produit=" + product.getId();
                Statement stl2 = conn.createStatement();
                ResultSet rs2 = stl2.executeQuery(sql2);
                rs2.next();
                product.setType(TypeProduitSalleDeSport.valueOf(rs.getString("type")));
                product.setNom(rs2.getString("nom"));
                product.setDescription(rs2.getString("description"));
                product.setImage(rs2.getString("image"));
                product.setPrix(rs2.getFloat("prix"));
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

    public List<ProduitSalleDeSport> showProduitByEtab(Produit p) {
        List<ProduitSalleDeSport> ls = new ArrayList<ProduitSalleDeSport>();
        try {
            String sql = "select * from  produits p  INNER JOIN produit_salledesport h on "
                    + "p.id_produit = h.id_produit id_etab = "+p.getEtab().getId()+" ;";
            Statement stl = conn.createStatement();
            ResultSet rs = stl.executeQuery(sql);
            while (rs.next()) {
                ProduitSalleDeSport product = new ProduitSalleDeSport();
                product.setId(rs.getInt("id_produit"));
                String sql2 = "select * from produits WHERE id_produit=" + product.getId();
                Statement stl2 = conn.createStatement();
                ResultSet rs2 = stl2.executeQuery(sql2);
                rs2.next();
                product.setType(TypeProduitSalleDeSport.valueOf(rs.getString("type")));
                product.setNom(rs2.getString("nom"));
                product.setDescription(rs2.getString("description"));
                product.setImage(rs2.getString("image"));
                product.setPrix(rs2.getFloat("prix"));
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

}
