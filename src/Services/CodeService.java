/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;



import DataStorage.MyDB;
import IServices.ICode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author user
 */
public class CodeService implements ICode {
    private int code;
    Connection conn;

    public CodeService() {
        this.conn=MyDB.getinstance().getConnexion();
    }



    @Override
    public void ajouterCode(String code) {
        try {
            String query = "INSERT INTO code (code) "
                    + "values ( '"+code+"')";
            Statement stm= conn.createStatement();
            stm.executeUpdate(query);
            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }  
    }

    @Override
    public void supprimerCode(String code) {
        try {
            String query = "DELETE FROM code WHERE code='"+code+"'";
            Statement stm= conn.createStatement();
            stm.executeUpdate(query);
            System.out.println("Suppression effectué");
        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        } 
    }

    @Override
    public String rechercheCode(String code) {
     try {
            
            String query = "Select * FROM code WHERE code='"+code+"'";
            Statement stm= conn.createStatement();
            ResultSet resultat=stm.executeQuery(query);
            while ( resultat.next() ) {            
            String code2 = resultat.getString("code");
                System.out.println(code2+ " ////"+code);
         return code2;
            }
            
        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }
       return null;
    }    
    }
   
