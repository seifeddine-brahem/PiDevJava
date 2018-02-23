/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.MyDB;
import Entities.Produit;
import IServices.IProduitPara;
import Utils.CategorieProduitParapharmacie;
import Entities.ProduitParapharmacie;
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
public class ProduitParapharmacieService implements IProduitPara{

    
    Connection conn ;

    public ProduitParapharmacieService() {
        this.conn = MyDB.getInstance().getConnexion();
    }

    @Override
    public void addProduit(ProduitParapharmacie p) {
           
        try {
//            String sqlp = "Insert into produits(nom,description,image,prix,id_etab) values ('" + p.getNom() + "','"
//                    + p.getDescription() + "','" + p.getImage() + "'," + p.getPrix() +","+p.getEtab().getId()+");";
            String sqlp = "Insert into produits(nom,description,image,prix,id_etab) values ('" + p.getNom() + "','"
                    + p.getDescription() + "','" + p.getImage() + "'," + p.getPrix() +","+1+");";
            PreparedStatement stl = conn.prepareStatement(sqlp,
                                      Statement.RETURN_GENERATED_KEYS);
            stl.executeUpdate();
            ResultSet generatedKeys = stl.getGeneratedKeys();
            generatedKeys.next();
            String sqlpara ="Insert into produit_parapharmacie(id_produit,marque,categorie) values ("+generatedKeys.getInt(1)+",'"
                    +p.getMarque()+"','"+p.getCategorie()+"');";
            Statement stm2 = conn.createStatement();
            stm2.executeUpdate(sqlpara);
            System.out.println("Add Done");
        } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }        
    }

    @Override
    public void editProduit(ProduitParapharmacie p) {
                   
        try {         
            String sqlp ="UPDATE produits SET nom = '"+p.getNom()+"',description = '"+p.getDescription()+"',"
                    + "image = '"+p.getImage()+"',prix ="+p.getPrix()+" WHERE  id_produit ="+p.getId()+";";
            Statement stl = conn.createStatement();
            stl.executeUpdate(sqlp);
            String sqlpara ="UPDATE  produit_parapharmacie SET marque ='"+p.getMarque()+"',categorie ='"+
                    p.getCategorie()+"'WHERE id_produit ="+p.getId()+";";
            Statement stm2 = conn.createStatement();
            stm2.executeUpdate(sqlpara);
            System.out.println("Update Produit Pharmacitique Done");
        } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
        }        
    }
    

    @Override
    public void deleteProduit(ProduitParapharmacie p) {
        String sql ="DELETE FROM produit_parapharmacie WHERE id_produit = "+p.getId()+";";
        String sql2 ="DELETE FROM produits WHERE id_produit = "+p.getId()+";";
        try {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            Statement stl2 = conn.createStatement();
            stl2.executeUpdate(sql2);
            System.out.println("Delete Produit Parapharmacitique Done");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    

    @Override
    public List<ProduitParapharmacie> showProduit(ProduitParapharmacie p) {
       List<ProduitParapharmacie> ls = new ArrayList<ProduitParapharmacie>();
        try {
            String sql = "select * from  produits p  INNER JOIN produit_parapharmacie h on p.id_produit = h.id_produit where h.id_produit = "+p.getId() ;            
            Statement stl = conn.createStatement();
            ResultSet rs =  stl.executeQuery(sql);
            while(rs.next()){
                ProduitParapharmacie para = new ProduitParapharmacie();
                para.setId(rs.getInt("id_produit"));
                para.setCategorie(CategorieProduitParapharmacie.valueOf(rs.getString("categorie")));
                System.out.println(para.getCategorie());
                para.setMarque(rs.getString("marque"));
                para.setNom(rs.getString("nom"));
                para.setDescription(rs.getString("description"));
                para.setImage(rs.getString("image"));
                para.setPrix(rs.getFloat("prix"));
                System.out.println(para.toString());
                ls.add(para);
            }
            if(ls.isEmpty()){
                System.err.println("viiiiide");
            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }
        return ls;
           
            
    }

    @Override
    public List<ProduitParapharmacie> searchProduit(String nom) {
        List<ProduitParapharmacie> ls = new ArrayList<>();
        ResultSet rs ;
        String sql = "select * from  produits p  INNER JOIN produit_parapharmacie h on p.id_produit = h.id_produit where p.nom = '"+nom+ "';";
        try{
            Statement stl = conn.createStatement();
            rs = stl.executeQuery(sql);
            while (rs.next()) {
                ProduitParapharmacie para = new ProduitParapharmacie();
                para.setId(rs.getInt("id_produit"));
                para.setNom(rs.getString("nom"));
                para.setDescription(rs.getString("description"));
                para.setImage(rs.getString("image"));
                para.setPrix(rs.getFloat("prix"));
                para.setCategorie(CategorieProduitParapharmacie.valueOf(rs.getString("categorie")));
                para.setMarque(rs.getString("marque"));
                System.out.println(para.toString());
                ls.add(para);
            }
            if (ls.isEmpty()) {
                System.err.println("nothing");
            }
        }catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } 
        return ls ;
    }

    public List<ProduitParapharmacie> searchAllProduit() {
        List<ProduitParapharmacie> ls = new ArrayList<>();
        ResultSet rs ;
        String sql = "select * from  produits p  INNER JOIN produit_parapharmacie h on p.id_produit = h.id_produit ;";
        try{
            Statement stl = conn.createStatement();
            rs = stl.executeQuery(sql);
            while (rs.next()) {
                ProduitParapharmacie para = new ProduitParapharmacie();
                para.setId(rs.getInt("id_produit"));
                para.setNom(rs.getString("nom"));
                para.setDescription(rs.getString("description"));
                para.setImage(rs.getString("image"));
                para.setPrix(rs.getFloat("prix"));
                para.setCategorie(CategorieProduitParapharmacie.valueOf(rs.getString("categorie")));
                para.setMarque(rs.getString("marque"));
                System.out.println(para.toString());
                ls.add(para);
            }
            if (ls.isEmpty()) {
                System.err.println("nothing");
            }
        }catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } 
        return ls ;
    }

    public List<ProduitParapharmacie> searchProduitByEtab(Produit p) {
                List<ProduitParapharmacie> ls = new ArrayList<>();
        ResultSet rs ;
        String sql = "select * from  produits p  INNER JOIN produit_parapharmacie h on p.id_produit = "
                + "     h.id_produit WHERE id_etab = "+p.getEtab().getId()+" ;";
        try{
            Statement stl = conn.createStatement();
            rs = stl.executeQuery(sql);
            while (rs.next()) {
                ProduitParapharmacie para = new ProduitParapharmacie();
                para.setId(rs.getInt("id_produit"));
                para.setNom(rs.getString("nom"));
                para.setDescription(rs.getString("description"));
                para.setImage(rs.getString("image"));
                para.setPrix(rs.getFloat("prix"));
                para.setCategorie(CategorieProduitParapharmacie.valueOf(rs.getString("categorie")));
                para.setMarque(rs.getString("marque"));
                System.out.println(para.toString());
                ls.add(para);
            }
            if (ls.isEmpty()) {
                System.err.println("nothing");
            }
        }catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } 
        return ls ;
    }
 
    
    
    
    
    
}
