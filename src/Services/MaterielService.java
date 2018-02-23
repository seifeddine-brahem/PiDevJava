/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.MyDB;
import Entities.Materiel;
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
public class MaterielService implements IServices.IMateriel{
    
    Connection conn ;
    
    
    

    public MaterielService() {
        this.conn = MyDB.getInstance().getConnexion();
    }
    
    
    
    

    @Override
    public void addMateriel(Materiel m) { 
        String sql ="INSERT into materiels (nom,description) values ('"+m.getNom()+"','"
                    +m.getDescription()+"');";
        System.out.println(sql);
        try {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Add Done");
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }
    }

    @Override
    public void editMateriel(Materiel m) {
        
        String sql ="UPDATE materiels SET nom = '"+m.getNom()+"',description = '"
                    +m.getDescription()+"' WHERE id_materiel ="+ m.getId()+";";
        try {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Update Materiel done");
        } catch (SQLException ex) {
             System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }
    }

    @Override
    public void deleteMateriel(Materiel m) {
        String sql ="DELETE FROM materiels WHERE id_materiel = "+m.getId()+";";
        try {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Delete Materiel Done");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    
    }

    @Override
    public Materiel showMateriel(Materiel m) {
        Materiel mater = new Materiel() ;
        ResultSet rs ;
        String sql ="SELECT * FROM materiels WHERE id_materiel = "+m.getId()+";";
        try {
            Statement stl = conn.createStatement();
            rs = stl.executeQuery(sql);
            rs.next();
            
            mater = new Materiel( rs.getString("nom"), rs.getString("description"));
            mater.setId(rs.getInt("id_materiel"));
            System.out.println(mater.toString());
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return mater;
    }
    
    

    @Override
    public List<Materiel> searchMateriel(String  nom) {
        List<Materiel> ls = new ArrayList<>() ;
        Materiel mater = new Materiel();
        ResultSet rs ;
        String sql = "select * from  materiels  where nom = '"+nom+ "';";
        try{
            Statement stl = conn.createStatement();
            rs = stl.executeQuery(sql);
            while (rs.next()) {
                mater.setId(rs.getInt("id_materiel"));
                mater.setDescription(rs.getString("description"));
                mater.setNom(rs.getString("nom"));
                ls.add(mater);
                System.out.println(mater.toString());
            }
            
        }catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        
        
        return ls ;
     
    } 

    public List<Materiel> searchAllMateriel() {
        List<Materiel> ls = new ArrayList<>() ;
        
        ResultSet rs ;
        String sql = "select * from  materiels ;";
        try{
            Statement stl = conn.createStatement();
            rs = stl.executeQuery(sql);
            while (rs.next()) {
                Materiel mater = new Materiel();
                mater.setId(rs.getInt("id_materiel"));
                mater.setDescription(rs.getString("description"));
                mater.setNom(rs.getString("nom"));
                
                System.out.println(mater.toString());
                ls.add(mater);
            }
            
        }catch(SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        
        
        return ls ;
    }

    
    
    
    
}
