/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.MyDB;
import Entities.Categorie;
import Entities.Conseil;
import Entities.fos_user;
import IServices.IConseil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Maha
 */
public class ConseilService implements IConseil 
        
{
        private ObservableList<Conseil>data;
        Connection conn;
    
    public ConseilService()
    {
        this.conn=MyDB.getinstance().getConnexion();
    }

    @Override
    public void ajouterConseil(Conseil c) 
    {
        String sql ="Insert into conseil(description,image,id_categorie,id_user) values ('"+c.getDescription()+"','"+c.getImage()+"',"+c.getCategorie().getId_categorie()+","+c.getUser().getId()+");";
        try 
        {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Add Done");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ajout conseil confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Conseil ajouté!");

            alert.showAndWait();
        }
        catch (SQLException ex)
        {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }
    }

    @Override
    public void supprimerConseil(Conseil c) 
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Alerte suppression");
        alert.setHeaderText("Suppression d'un conseil");
        alert.setContentText("Êtes-vous sûr(e) de vouloir supprimer?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
        try
        {
            String sql ="DELETE FROM conseil WHERE id_conseil= "+c.getId_conseil()+";";
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Delete Conseil Done");
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
    public void modifierConseil(Conseil c) 
    {
        String sql ="UPDATE conseil SET description = '"+c.getDescription()+"',id_categorie = "+c.getCategorie().getId_categorie()+" WHERE id_conseil ="+ c.getId_conseil()+";";
        System.out.println(sql);
        try 
        {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Update Conseil done");
        } 
        catch (SQLException ex) 
        {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }
    }

    @Override
    public List<Conseil> afficherConseils()
    {
        List<Conseil> ListConseils=new ArrayList <Conseil>();
        
        ResultSet rs;
        
        String sql ="SELECT c.*,ca.nom ,user.username  FROM conseil c, categorie ca ,fos_user user  WHERE (c.id_categorie = ca.id_categorie) and (c.id_user = USER.id)";
        try 
        {
            Statement stl = conn.createStatement();
            rs=stl.executeQuery(sql);          
            System.out.println("Affichage Done");
            while(rs.next())
            {
                Conseil cons = new Conseil();
                cons.setId_conseil(rs.getInt("id_conseil"));
                cons.setDescription(rs.getString("description"));
                cons.setImage(rs.getString("image"));
//                cons(rs.getInt("id_categorie"));
//                cons.setId_user(rs.getInt("id_user"));
                cons.setNom_categorie(rs.getString("nom"));
                cons.setNom_user(rs.getString("username"));
                ListConseils.add(cons);
                System.out.println(cons.toString());                
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
       return ListConseils ;
               
    }
    
    
        @Override
    public List<Conseil> afficherConseils(int idPartenaire)
    {
        List<Conseil> ListConseils=new ArrayList <Conseil>();
        
        ResultSet rs;
        
        String sql ="SELECT c.*,ca.nom ,user.username  FROM conseil c, categorie ca ,fos_user user  WHERE (c.id_categorie = ca.id_categorie) and (c.id_user = USER.id) and (c.id_user='"+idPartenaire+"')";
        try 
        {
            Statement stl = conn.createStatement();
            rs=stl.executeQuery(sql);          
            System.out.println("Affichage Done");
            while(rs.next())
            {
                Conseil cons = new Conseil();
                cons.setId_conseil(rs.getInt("id_conseil"));
                cons.setDescription(rs.getString("description"));
                cons.setImage(rs.getString("image"));
//                cons(rs.getInt("id_categorie"));
//                cons.setId_user(rs.getInt("id_user"));
                cons.setNom_categorie(rs.getString("nom"));
                cons.setNom_user(rs.getString("username"));
                ListConseils.add(cons);
                System.out.println(cons.toString());                
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
       return ListConseils ;
    }
    

    @Override
    public  ObservableList<Conseil> chercherConseilParCategorie(String nomCategorie) 
            
    {    data = FXCollections.observableArrayList();
                  
       ResultSet rs;
        Categorie categorie = new Categorie();
        categorie=getCategorie(nomCategorie);
       int idcateg= categorie.getId_categorie();

        try 
        {
                      
            System.out.println(idcateg);
            String sql2="select * from conseil where id_categorie ="+idcateg+";";
            Statement st2 = conn.createStatement();
            ResultSet  rs2=st2.executeQuery(sql2);       
            System.out.println("Affichage Done");
            while(rs2.next())
            {
                Conseil conseil = new Conseil();
                conseil.setId_conseil(rs2.getInt("id_conseil"));
                conseil.setDescription(rs2.getString("description"));
                conseil.setImage(rs2.getString("image"));
                //conseil.setId_categorie(rs2.getInt("id_categorie"));
                //conseil.setId_user(rs2.getInt("id_user"));
                
                conseil.setNom_categorie(nomCategorie);
                
                  String nomUser = (getUser(rs2.getInt("id_user"))).getUsername();
                  
                   
                conseil.setNom_user(nomUser);
                  

                data.add(conseil);
                System.out.println(conseil.toString());               
            }
        } 
        
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return data;
    }

    
    
    @Override
    public ObservableList<Conseil> chercherConseilParPartenaire(String nomPart)
    {
        data = FXCollections.observableArrayList();         
        ResultSet rs;
        String sql ="select id from fos_user where (roles = 'partenaire') AND (username = '"+nomPart+"');";

        try 
        {
            Statement stl = conn.createStatement();
            rs=stl.executeQuery(sql);    
            rs.next();
            int idPart= rs.getInt("id");
            System.out.println(idPart);
            String sql2="select * from conseil where id_user ="+idPart+";";
            Statement st2 = conn.createStatement();
            ResultSet  rs2=st2.executeQuery(sql2);       
            System.out.println("Affichage Done");
            while(rs2.next())
            {
                Conseil conseil = new Conseil();
                conseil.setId_conseil(rs2.getInt("id_conseil"));
                conseil.setDescription(rs2.getString("description"));
                conseil.setImage(rs2.getString("image"));
//                conseil.setId_categorie(rs2.getInt("id_categorie"));
//                conseil.setId_user(rs2.getInt("id_user"));
                
            String nomUser = (getUser(rs2.getInt("id_user"))).getUsername();
                    String nomCategoriee = (getCategorie(rs2.getInt("id_categorie"))).getNom();
                   
                conseil.setNom_user(nomUser);
                conseil.setNom_categorie(nomCategoriee);

                data.add(conseil);
                System.out.println(conseil.toString());               
            }
        } 
        
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return data;   
    }

    
    /// Liste qui retourne les username de fos_user
    
    
    
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
    
    
    ///// liste qui retourne le nom des categories
    
    
    
        @Override
    public List<String> listerNomsCategorie() 
    {
        List<String> ListCat=new ArrayList <String>();              
        ResultSet rs;
        String sql ="select nom from categorie where type='Conseil';";

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
    
    
    //////retourne une categorie d'apres un id
    
     @Override
    public Categorie getCategorie(int idCategorie ) 
    {
        Categorie categorie = new Categorie();
                      
        ResultSet rs;
        String sql ="select * from categorie where id_categorie='"+idCategorie+"' and type='Conseil';";

          try 
        {
            Statement stl = conn.createStatement();
            rs=stl.executeQuery(sql);          
            System.out.println("Affichage Done");
            while(rs.next())
            {
                categorie.setId_categorie(idCategorie);
                categorie.setNom(rs.getString("nom"));
               
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return categorie;
    }
    
    //retourne categorie d'apres un nom
    
    
     @Override
    public Categorie getCategorie(String nomCategorie ) 
    {
        Categorie categorie = new Categorie();
                      
        ResultSet rs;
        String sql ="select * from categorie where nom='"+nomCategorie+"' and type='Conseil';";

          try 
        {
            Statement stl = conn.createStatement();
            rs=stl.executeQuery(sql);          
            System.out.println("Affichage Done");
            while(rs.next())
            {
                categorie.setId_categorie(rs.getInt("id_categorie"));
                categorie.setNom(nomCategorie);
               
            }
        } 
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return categorie;
    }
    
    ///// retourne un user de fos_user d'apres un id
    
    
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
    
    
}