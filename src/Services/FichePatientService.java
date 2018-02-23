/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.MyDB;
import Entities.FichePatient;
import Entities.fos_user;
import IServices.IFichePatient;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author user
 */
public class FichePatientService implements IFichePatient{
    
    Connection conn;
private ObservableList<FichePatient> data;
    public FichePatientService() {
        this.conn=MyDB.getinstance().getConnexion();
    }
    

    @Override
    public void ajouterFicheClient(FichePatient fc) {
        try 
        {
          String sql ="Insert into fichepatient(suivie,suivieHTML,idPatient,idEtab) values ('"+fc.getSuivie()+"','"+fc.getSuivieHTML()+"','"+fc.getIdPatient()+"',"+fc.getIdEtab()+");";
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Ajout effectué");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ajout fiche confirmation");
            alert.setHeaderText(null);
            alert.setContentText("fiche ajoutée!");

            alert.showAndWait();
        }
        catch (SQLException ex)
        {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }    }

    @Override
    public void supprimerFicheClient(FichePatient fc) {
        
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Alerte suppression");
        alert.setHeaderText("Suppression d'un conseil");
        alert.setContentText("Êtes-vous sûr(e) de vouloir supprimer?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
        try 
        {
            String sql ="DELETE FROM fichepatient WHERE id= "+fc.getId()+";";
            System.out.println(sql);
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
    }

    @Override
    public void modifierFicheClient(FichePatient fc) {
        String sql = "UPDATE fichepatient SET suivie = '"+fc.getSuivie()+"', suivieHTML='"+fc.getSuivieHTML()+"' WHERE idPatient= "+fc.getIdPatient()+" AND idEtab= "+fc.getIdEtab()+"; ";
        
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
    public FichePatient afficherFicheClient(FichePatient fc) {
        FichePatient fiche_client = new FichePatient() ;
        ResultSet rs ;
        String sql ="SELECT * FROM fichepatient WHERE idPatient= "+fc.getIdPatient()+" AND idEtab= "+fc.getIdEtab()+"; ";
        try {
            Statement stl = conn.createStatement();
            rs = stl.executeQuery(sql);
            rs.next();
            fiche_client = new FichePatient(rs.getString("suivie"), rs.getInt("IdPatient"), rs.getInt("IdEtab"));
            fiche_client.setSuivieHTML(rs.getString("suivieHTML"));
            System.out.println(fiche_client.toString());
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return fiche_client;    
    }
    
   
    @Override
    public List<FichePatient> afficherToutFicheClient() {
      List<FichePatient> ListFiche=new ArrayList <FichePatient>();
        
        //ResultSet rs;
        
         String sql ="select f.*,etab.nom ,fos_user.username FROM fichepatient f, etablissements etab ,fos_user fos_user  WHERE (f.idEtab = etab.id) and (f.idPatient = fos_user.id);";
        try 
        {
            Statement stl = conn.createStatement();
             ResultSet rs=stl.executeQuery(sql);          
            System.err.println("Display Done");
            while(rs.next())
            {   
                FichePatient fiche = new FichePatient();
                //fiche.setId(rs.getInt("id"));
                fiche.setIdPatient(rs.getInt("idPatient"));
                fiche.setIdEtab(rs.getInt("idEtab"));
                fiche.setSuivie(rs.getString("suivie"));
                fiche.setNomEtab(rs.getString("nom"));
                fiche.setNomUser(rs.getString("username"));
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

    @Override
    public ObservableList<FichePatient> rechercherFiche_Client(String nom) {
     data = FXCollections.observableArrayList();
        List<FichePatient> ListFicheClient=new ArrayList <FichePatient>();              
        ResultSet rs;
       
                String sql1="select f.*,etab.nom ,fos_user.username FROM fichepatient f, etablissements etab ,fos_user fos_user  WHERE (f.idEtab = etab.id) and ('"+nom+"' = fos_user.username);";
           // String sql2="select username from user where id="+rs.getInt("id_user")+";";
                FichePatient fiche_client = new FichePatient() ;
                List <FichePatient> e = new ArrayList <FichePatient>();
  
       // String sql2="select username from user where id="+rs.getInt("id_user")+";";
        try 
        {
            Statement stl = conn.createStatement();

            ResultSet  rs1=stl.executeQuery(sql1);       
            System.out.println("Affichage Done");
            while(rs1.next()) 
            {
                String sql2="select username from fos_user where id="+rs1.getInt("idPatient")+";";
                Statement st2 = conn.createStatement();
                ResultSet  rs2=st2.executeQuery(sql2);
                while(rs2.next())
                    {
                      FichePatient  FicheClient = new FichePatient(rs1.getString("suivie"), rs1.getInt("idPatient"), rs1.getInt("idEtab"));
                        data.add(FicheClient);
                        System.out.println(e);               
                    }
        }} 
        
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return data;
    
    }
    
    
       @Override
    public  List<String> listerNomsPartanaire() 
    {
       // data= FXCollections.observableArrayList();
        List<String> ListPart=new ArrayList <String>();              
        ResultSet rs;
        String sql ="select username from fos_user where roles = 'partenaire';";

          try 
        {
            Statement stl = conn.createStatement();
            rs=stl.executeQuery(sql);          
            System.out.println("Affichage Done");
            while(rs.next())
            {
             ListPart.add(rs.getString("username"));                
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return ListPart;
    }
    
    
    
        
        @Override
    public List<String> listerNomsEtab() 
    {
        List<String> ListCat=new ArrayList <String>();              
        ResultSet rs;
        String sql ="select nom from etablissements;";

          try 
        {
            Statement stl = conn.createStatement();
            rs=stl.executeQuery(sql);          
            System.out.println("Affichage Done");
            while(rs.next())
            {
                //Categorie c = new Categorie();
                //c.setNom(rs.getString("nom"));

                ListCat.add(rs.getString("nom"));
                //System.out.println(c.toString1());                
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return ListCat;
    }
    
    
     @Override
    public fos_user getUser(int idUser ) 
    {
       fos_user user =new fos_user();
        
                      
        ResultSet rs;
        String sql ="select * from fos_user where id='"+idUser+"';";

          try 
        {
            Statement stl = conn.createStatement();
            rs=stl.executeQuery(sql);          
            System.out.println("Affichage Done");
            while(rs.next())
            {
              user.setId(idUser);
              user.setUsername(rs.getString("username"));
               
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return user;
    }
    
    
    
    
    public List<FichePatient> listeNomPatient() {
     
        List<FichePatient> listeNomPatient=new ArrayList <FichePatient>();              
        ResultSet rs;
       
                String sql1="select f.*,etab.nom ,fos_user.username FROM fichepatient f, etablissements etab ,fos_user fos_user  WHERE (f.idEtab = etab.id);";
           // String sql2="select username from user where id="+rs.getInt("id_user")+";";
                FichePatient fiche_client = new FichePatient() ;
                List <FichePatient> e = new ArrayList <FichePatient>();
  
       // String sql2="select username from user where id="+rs.getInt("id_user")+";";
        try 
        {
            Statement stl = conn.createStatement();

            ResultSet  rs1=stl.executeQuery(sql1);       
            System.out.println("Affichage Done");
            while(rs1.next()) 
            {
                String sql2="select username from fos_user where id="+rs1.getInt("idPatient")+";";
                Statement st2 = conn.createStatement();
                ResultSet  rs2=st2.executeQuery(sql2);
                while(rs2.next())
                    {
                      FichePatient  FicheClient = new FichePatient(rs1.getString("suivie"), rs1.getInt("idPatient"), rs1.getInt("idEtab"));
                        listeNomPatient.add(FicheClient);
                        System.out.println(e);               
                    }
        }} 
        
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return listeNomPatient;
    
    }
    
    public ObservableList<String> getListNewUser()
    {
        ObservableList<String> listeNewUser = null;
        //String sql="select idPatient FROM fos_user  WHERE ( fos_user.id != fichepatient.idPatient );";
          int i=0;
        String sql1="select idPatient FROM fichepatient ;";
        
          
                Statement st1,st2;
        try {
            st1 = conn.createStatement();
        
                ResultSet  rs1=st1.executeQuery(sql1);
                while(rs1.next())
                    {
                     i=rs1.getInt("idPatient");
                     String sql2="select username from fos_user where id<>"+i+";";
                     st2 = conn.createStatement();
                     ResultSet  rs2=st2.executeQuery(sql2);
                     while(rs2.next())
                     {
                         String s=rs2.getString("username");
                         System.out.println(s);
                        listeNewUser.add(s);
                     }
                     return listeNewUser;
                    }
        } catch (SQLException ex) {
            Logger.getLogger(FichePatientService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return listeNewUser;
        
    }
       
}
