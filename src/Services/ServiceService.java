/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.MyDB;
import Entities.Service;
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
public class ServiceService implements IServices.IService{
    
    Connection conn ;

    public ServiceService() {
        this.conn = MyDB.getInstance().getConnexion();
    }
    
    
    @Override
    public void addService(Service s) {
        
//        String sql ="Insert into services(nom,description,tarif,id_etab) values ('"+s.getNom()+"','"
//                    +s.getDescription()+"',"+s.getTarif()+","+s.getEtab().getId()+");";
        
                String sql ="Insert into services(nom,description,tarif,id_etab) values ('"+s.getNom()+"','"
                    +s.getDescription()+"',"+s.getTarif()+","+3+");";
        try {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Add Done");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
    }

    @Override
    public void editService(Service s) {
        String sql ="UPDATE services SET nom = '"+s.getNom()+"',description = '"
                    +s.getDescription()+"',tarif ="+s.getTarif()+" WHERE id ="+ s.getId()+";";
        try {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Update done");
        } catch (SQLException ex) {
           System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    @Override
    public void deleteService(Service s) {
       String sql ="DELETE FROM services WHERE id = "+s.getId()+";";
        try {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Delete Done");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    @Override
    public Service showService(Service s) {
        Service service = new Service() ;
        ResultSet rs = null ;
        String sql ="SELECT * FROM services WHERE id = "+s.getId()+";";
        try {
            Statement stl = conn.createStatement();
            rs = stl.executeQuery(sql);
            rs.next();
            service = new Service(rs.getString("nom"),rs.getString("description") , rs.getFloat("tarif"));
            System.out.println(service.toString());
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return service;
    }

    @Override
    public List<Service> searchService(String nom,String location) {
        List<Service> ls = new ArrayList<>();
        ResultSet rs ;
        Service service ;
        String sql ="SELECT * FROM services s INNER JOIN etablissements e on "
                + "s.id_etab = e.id WHERE s.nom LIKE '%"+nom+"%' and e.adresse LIKE '%"+location+"%';";
        try {
            Statement stl = conn.createStatement();
            rs = stl.executeQuery(sql);
            while(rs.next()){
                 service = new Service();
                 service.setId(rs.getInt("s.id"));
                 service.setNom(rs.getString("s.nom"));
                 service.setDescription(rs.getString("s.description"));
                 service.setTarif(rs.getFloat("s.tarif"));
                // service.setId(rs.getInt("id"));
                 service.getEtab().setNom(rs.getString("e.nom"));
                 service.getEtab().setAdresse(rs.getString("e.adresse"));
                 service.getEtab().setNum(rs.getInt("e.numero"));
                 service.getEtab().setId(rs.getInt("e.id"));
                 System.out.println(service.toString());
                 ls.add(service);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return ls;

    }
    public List<Service> showAllService() {
        List<Service> ls = new ArrayList<>();
        ResultSet rs ;
        Service service ;
        String sql ="SELECT * FROM services s INNER JOIN etablissements e ON s.id_etab = e.id;";
        try {
            Statement stl = conn.createStatement();
            rs = stl.executeQuery(sql);
            while(rs.next()){
                service = new Service();
                 //service = new Service(rs.getString("nom"),rs.getString("description") , rs.getFloat("tarif"));
                 service.setNom(rs.getString("nom"));
                 service.setDescription(rs.getString("description"));
                 service.setTarif(rs.getFloat("tarif"));
                 service.setId(rs.getInt("s.id"));
                 service.getEtab().setNom(rs.getString("e.nom"));
                 service.getEtab().setAdresse(rs.getString("e.adresse"));
                 service.getEtab().setNum(rs.getInt("e.numero"));
                 service.getEtab().setId(rs.getInt("e.id"));
                 
                 
                 System.out.println(service.toString());
                 ls.add(service);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return ls;
    
    
    }

    @Override
    public List<Service> searchServicebyEtab(Service s) {
        List<Service> ls = new ArrayList<>();
        ResultSet rs ;
        Service service ;
        String sql ="SELECT * FROM services s INNER JOIN etablissements e ON s.id_etab = e.id Where s.id_etab ="+s.getEtab().getId()+"; ";
        try {
            Statement stl = conn.createStatement();
            rs = stl.executeQuery(sql);
            while(rs.next()){
                service = new Service();
                 //service = new Service(rs.getString("nom"),rs.getString("description") , rs.getFloat("tarif"));
                 service.setNom(rs.getString("nom"));
                 service.setDescription(rs.getString("description"));
                 service.setTarif(rs.getFloat("tarif"));
                 service.setId(rs.getInt("s.id"));
                 service.getEtab().setNom(rs.getString("e.nom"));
                 service.getEtab().setAdresse(rs.getString("e.adresse"));
                 service.getEtab().setNum(rs.getInt("e.numero"));
                 service.getEtab().setId(rs.getInt("e.id"));
                 
                 
                 
                 
                 System.out.println(service.toString());
                 ls.add(service);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return ls;
    }
    
    
    
    
}
