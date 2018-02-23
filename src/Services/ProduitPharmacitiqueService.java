/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.MyDB;
import Entities.Produit;
import IServices.IProduitPharmacie;
import Utils.Forme;
import Utils.ModeAdministration;
import Utils.PourQui;
import Entities.ProduitPharmaceutique;
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
public class ProduitPharmacitiqueService implements IProduitPharmacie {

    Connection conn;

    public ProduitPharmacitiqueService() {
        this.conn = MyDB.getInstance().getConnexion();
    }

    @Override
    public void addProduit(ProduitPharmaceutique p) {
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
            String sqlpara = "Insert into produit_pharmaceutique(id_produit,marque,mode_administration,forme,PourQui) values (" + generatedKeys.getInt(1) + ",'"
                    + p.getMarque() + "','" + p.getMode_administration() + "','" + p.getForme() + "','" + p.getPourqui() + "');";
            Statement stm2 = conn.createStatement();
            stm2.executeUpdate(sqlpara);
            System.out.println("Add Done");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    /**
     * update product first after that update into product_pharma
     */
    @Override
    public void editProduit(ProduitPharmaceutique p) {
        try {

            String sqlp = "UPDATE produits SET nom = '" + p.getNom() + "',description = '" + p.getDescription() + "',"
                    + "image = '" + p.getImage() + "',prix =" + p.getPrix() + "WHERE id_produit = " + p.getId() + ";";
            Statement stl = conn.createStatement();
            stl.addBatch(sqlp);
            String sqlpharma = "UPDATE produit_pharmaceutique SET marque ='" + p.getMarque()
                    + "',mode_administration ='" + p.getMode_administration() + "',forme ='" + p.getForme()
                    + "',PourQui ='" + p.getPourqui() + "' WHERE id_produit = " + p.getId() + ";";
            stl.addBatch(sqlpharma);
            int[] executeBatch = stl.executeBatch();
            //Statement stm2 = conn.createStatement();
            //stm2.executeUpdate(sqlpharma);

            System.out.println("Update Produit Pharmacitique Done");
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }
    }

    @Override
    public void deleteProduit(ProduitPharmaceutique p) {
        String sql = "DELETE FROM produit_pharmaceutique WHERE id_produit = " + p.getId() + ";";
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
    public List<ProduitPharmaceutique> showProduit(ProduitPharmaceutique p) {
        List<ProduitPharmaceutique> ls = new ArrayList<ProduitPharmaceutique>();
        try {
            String sql = "select * from  produits p  INNER JOIN produit_pharmaceutique h on p.id_produit = h.id_produit where p.id_produit = " + p.getId() + ";";
            Statement stl = conn.createStatement();
            ResultSet rs = stl.executeQuery(sql);
            while (rs.next()) {
                ProduitPharmaceutique para = new ProduitPharmaceutique();
                para.setId(rs.getInt("id_produit"));
                para.setMode_administration(ModeAdministration.valueOf(rs.getString("mode_administration")));
                para.setForme(Forme.valueOf(rs.getString("forme")));
                para.setPourqui(PourQui.valueOf(rs.getString("PourQui")));
                para.setMarque(rs.getString("marque"));
                para.setNom(rs.getString("nom"));
                para.setDescription(rs.getString("description"));
                para.setImage(rs.getString("image"));
                para.setPrix(rs.getFloat("prix"));
                System.out.println(para.toString());
                ls.add(para);

            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }
        return ls;
    }

    @Override
    public List<ProduitPharmaceutique> searchProduit(String nom) {
        List<ProduitPharmaceutique> ls = new ArrayList<>();
        ResultSet rs;
        String sql = "select * from  produits p  INNER JOIN produit_pharmaceutique h on p.id_produit = h.id_produit where p.nom = '" + nom + "';";
        try {
            Statement stl = conn.createStatement();
            rs = stl.executeQuery(sql);
            while (rs.next()) {
                ProduitPharmaceutique para = new ProduitPharmaceutique();
                para.setId(rs.getInt("id_produit"));
                para.setMode_administration(ModeAdministration.valueOf(rs.getString("mode_administration")));
                para.setForme(Forme.valueOf(rs.getString("forme")));
                para.setPourqui(PourQui.valueOf(rs.getString("PourQui")));
                para.setMarque(rs.getString("marque"));
                para.setNom(rs.getString("nom"));
                para.setDescription(rs.getString("description"));
                para.setImage(rs.getString("image"));
                para.setPrix(rs.getFloat("prix"));
                System.out.println(para.toString());
                ls.add(para);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return ls;
    }

    public List<ProduitPharmaceutique> searchAllProduit() {
        List<ProduitPharmaceutique> ls = new ArrayList<>();
        ResultSet rs;
        String sql = "select * from  produits p  INNER JOIN produit_pharmaceutique h on p.id_produit = h.id_produit ;";
        try {
            Statement stl = conn.createStatement();
            rs = stl.executeQuery(sql);
            while (rs.next()) {
                ProduitPharmaceutique para = new ProduitPharmaceutique();
                para.setId(rs.getInt("id_produit"));
                para.setMode_administration(ModeAdministration.valueOf(rs.getString("mode_administration")));
                para.setForme(Forme.valueOf(rs.getString("forme")));
                para.setPourqui(PourQui.valueOf(rs.getString("PourQui")));
                para.setMarque(rs.getString("marque"));
                para.setNom(rs.getString("nom"));
                para.setDescription(rs.getString("description"));
                para.setImage(rs.getString("image"));
                para.setPrix(rs.getFloat("prix"));
                System.out.println(para.toString());
                ls.add(para);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return ls;
    }

    public List<ProduitPharmaceutique> searchProduitByEtab(Produit p) {
        List<ProduitPharmaceutique> ls = new ArrayList<>();
        ResultSet rs;
        String sql = "select * from  produits p  INNER JOIN produit_pharmaceutique h on "
                + "p.id_produit = h.id_produit WHERE  id_etab = "+p.getEtab().getId()+" ;";
        try {
            Statement stl = conn.createStatement();
            rs = stl.executeQuery(sql);
            while (rs.next()) {
                ProduitPharmaceutique para = new ProduitPharmaceutique();
                para.setId(rs.getInt("id_produit"));
                para.setMode_administration(ModeAdministration.valueOf(rs.getString("mode_administration")));
                para.setForme(Forme.valueOf(rs.getString("forme")));
                para.setPourqui(PourQui.valueOf(rs.getString("PourQui")));
                para.setMarque(rs.getString("marque"));
                para.setNom(rs.getString("nom"));
                para.setDescription(rs.getString("description"));
                para.setImage(rs.getString("image"));
                para.setPrix(rs.getFloat("prix"));
                System.out.println(para.toString());
                ls.add(para);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return ls;
    }

}
