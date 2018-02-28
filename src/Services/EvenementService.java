/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.MyDB;
import Entities.Categorie;
import Entities.Evenement;
import Entities.Utilisateur;
import IServices.IEvenement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Maha
 */
public class EvenementService implements IEvenement
{
     Connection conn;
     private ObservableList<Evenement>data;

    
    public EvenementService()
    {
        this.conn=MyDB.getinstance().getConnexion();
    }

    @Override
    public void ajouterEvenement(Evenement e)           
    {         
       
        String sql ="Insert into evenements (date_debut,date_fin,horaire_com,horaire_fin,description,image,id_categorie,id_user,archive) values ('"+e.getDate_debut()+"','"+e.getDate_fin()+"','"
                    +e.getHoraire_com()+"','"+e.getHoraire_fin()+"','"+e.getDescription()+"','"+e.getImage()+"',"+e.getCategorie().getId_categorie()+","+e.getUser().getId()+",0);";
        try 
        {
            System.out.println(sql);
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Add Done");
                        Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ajout évenement confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Evénement ajouté!");
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
    public void supprimerEvenement(Evenement e)
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Alerte suppression");
        alert.setHeaderText("Suppression d'un évènement");
        alert.setContentText("Êtes-vous sûr(e) de vouloir supprimer?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
        try
        {
            String sql5="select COUNT(id_event) AS cmp from action where id_event="+e.getId_event()+" ;";
            String sql ="DELETE FROM evenements  WHERE id_event="+e.getId_event()+";";
            Statement stl = conn.createStatement();
            Statement st2 = conn.createStatement();
            
             ResultSet rs2=st2.executeQuery(sql5);
             while (rs2.next())
             {
            System.out.println(sql);
            int i=rs2.getInt("cmp");
            if(i<1)
                 {
            stl.executeUpdate(sql);
            rs2.close();
            Alert alert7 = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Evenement supprimé!");
            alert.showAndWait();
                     
                 }
                 else
                 {
            Alert alert5 = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Il existe au moins un participant dans cet évenement!");
            alert.setContentText("Vous ne pouvez pas supprimer cet événement");

            alert.showAndWait();
                 }
             }
            System.out.println("Delete EvenementDone");
        } 
        catch (SQLException ex) 
            {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            }
    }}

    @Override
    public void modifierEvenement(Evenement e)
    {
        String sql ="UPDATE evenements SET date_debut = '"+e.getDate_debut()+"',date_fin = '"
                    +e.getDate_fin()+"',horaire_com = '"+e.getHoraire_com()+"',horaire_fin = '"
                    +e.getHoraire_fin()+"',description = '"+e.getDescription()+"',image ='"+e.getImage()+"',id_categorie = '"+e.getCategorie().getId_categorie()+"' WHERE id_event ="+ e.getId_event()+";";
        try 
        {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Update Evenement done");
        } 
        catch (SQLException ex) 
        {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }    }

    @Override
    public List<Evenement> afficherEvenements()
    {
        List<Evenement> ListEvent=new ArrayList <Evenement>();              
        ResultSet rs;
        try 
        {
            String sql2="SELECT c.*,ca.nom FROM evenements c, categorie ca WHERE (c.id_categorie = ca.id_categorie) and (c.archive=0)";
            Statement st2 = conn.createStatement();
            ResultSet  rs2=st2.executeQuery(sql2);       
            System.out.println("Affichage Done");
            
            while(rs2.next())
            {                
                Evenement event = new Evenement();
                Timestamp input = rs2.getTimestamp("date_debut");              
                Timestamp input2 = rs2.getTimestamp("date_fin");
                LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate date2 = input2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDateTime newD = rs2.getTimestamp("date_debut").toLocalDateTime();
                LocalDateTime newG = rs2.getTimestamp("date_fin").toLocalDateTime();
                java.util.Date ddd = new java.util.Date();
                java.sql.Date ddd2= new java.sql.Date(input.getTime());
                event.setId_event(rs2.getInt("id_event"));
                event.setDate_debut(date);
                event.setDate_fin(date2);
                event.setHoraire_com(rs2.getString("horaire_com"));
                event.setHoraire_fin(rs2.getString("horaire_fin"));
                event.setDescription(rs2.getString("description"));
                event.setImage(rs2.getString("image"));             
                event.setNom_categorie(rs2.getString("nom"));
                
                ListEvent.add(event);
                System.out.println(event.toString());                             
            }
        } 
        
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return ListEvent;
        
    }
    
     @Override
    public List<Evenement> afficherToutEvenements()
    {
        List<Evenement> ListEvent=new ArrayList <Evenement>();              
        ResultSet rs;
        try 
        {
            String sql2="SELECT c.*,ca.nom FROM evenements c, categorie ca WHERE (c.id_categorie = ca.id_categorie)";
            Statement st2 = conn.createStatement();
            ResultSet  rs2=st2.executeQuery(sql2);       
            System.out.println("Affichage Done");
            
            while(rs2.next())
            {                
                Evenement event = new Evenement();
                Timestamp input = rs2.getTimestamp("date_debut");              
                Timestamp input2 = rs2.getTimestamp("date_fin");
                LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate date2 = input2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDateTime newD = rs2.getTimestamp("date_debut").toLocalDateTime();
                LocalDateTime newG = rs2.getTimestamp("date_fin").toLocalDateTime();
                java.util.Date ddd = new java.util.Date();
                java.sql.Date ddd2= new java.sql.Date(input.getTime());
                event.setId_event(rs2.getInt("id_event"));
                event.setDate_debut(date);
                event.setDate_fin(date2);
                event.setHoraire_com(rs2.getString("horaire_com"));
                event.setHoraire_fin(rs2.getString("horaire_fin"));
                event.setDescription(rs2.getString("description"));
                event.setImage(rs2.getString("image"));             
                event.setNom_categorie(rs2.getString("nom"));
                
                ListEvent.add(event);
                System.out.println(event.toString());                             
            }
        } 
        
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return ListEvent;
        
    }
    
    
    
    public Categorie getCategorieID (int idCategorie)
    {
    Categorie categorie = new Categorie();
   CategorieService categorieService = new CategorieService();
    
            String sql2="SELECT * from categorie where id_categorie='"+idCategorie+"'";
            Statement st2;
         try {
             st2 = conn.createStatement();
                ResultSet  rs2=st2.executeQuery(sql2);       
            System.out.println("Affichage Done");
            
             while(rs2.next())
            {
              categorie.setId_categorie(idCategorie);
              categorie.setNom(rs2.getString("nom"));
              categorie.setType(rs2.getString("type"));
              categorie.setUser(categorieService.getUser(rs2.getInt("id_user")));
            }
            
         } catch (SQLException ex) {
             Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
         }
         
           
    
    return categorie;
    
    }
    
    public List<Evenement> afficherEvenementsPartenaire(int idPartenaire)
    {
        List<Evenement> ListEvent=new ArrayList <Evenement>();              
        ResultSet rs;
        try 
        {
            String sql2="SELECT * FROM evenements  WHERE id_user='"+idPartenaire+"' and archive=0";
            Statement st2 = conn.createStatement();
            ResultSet  rs2=st2.executeQuery(sql2);       
            System.out.println("Affichage Done");
            
            while(rs2.next())
            {                
                Evenement event = new Evenement();
                Timestamp input = rs2.getTimestamp("date_debut");              
                Timestamp input2 = rs2.getTimestamp("date_fin");
                LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate date2 = input2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDateTime newD = rs2.getTimestamp("date_debut").toLocalDateTime();
                LocalDateTime newG = rs2.getTimestamp("date_fin").toLocalDateTime();
                java.util.Date ddd = new java.util.Date();
                java.sql.Date ddd2= new java.sql.Date(input.getTime());
                event.setId_event(rs2.getInt("id_event"));
                event.setDate_debut(date);
                event.setDate_fin(date2);
                event.setHoraire_com(rs2.getString("horaire_com"));
                event.setHoraire_fin(rs2.getString("horaire_fin"));
                event.setDescription(rs2.getString("description"));
                event.setImage(rs2.getString("image"));             
                event.setNom_categorie(getCategorieID(rs2.getInt("id_categorie")).getNom());
                
                ListEvent.add(event);
                System.out.println(event.toString());                             
            }
        } 
        
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return ListEvent;
        
    }
    
     @Override
    public List<Evenement> chercherEvenementParCategoriee(String nomCategorie) 
    {
        List<Evenement> ListEvent=new ArrayList <Evenement>();
               
        ResultSet rs;
        try 
        {   String sql2="SELECT  * from evenements INNER JOIN categorie c on evenements.id_categorie = c.id_categorie where c.nom = '"+nomCategorie+"';";
                 Statement st2 = conn.createStatement();
            ResultSet  rs2=st2.executeQuery(sql2);       
            System.out.println("Affichage Done");
            
            while(rs2.next())
            {                
                Evenement event = new Evenement();
                Timestamp input = rs2.getTimestamp("date_debut");              
                Timestamp input2 = rs2.getTimestamp("date_fin");
                LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate date2 = input2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDateTime newD = rs2.getTimestamp("date_debut").toLocalDateTime();
                LocalDateTime newG = rs2.getTimestamp("date_fin").toLocalDateTime();
                java.util.Date ddd = new java.util.Date();
                java.sql.Date ddd2= new java.sql.Date(input.getTime());
                event.setId_event(rs2.getInt("id_event"));
                event.setDate_debut(date);
                event.setDate_fin(date2);
                event.setHoraire_com(rs2.getString("horaire_com"));
                event.setHoraire_fin(rs2.getString("horaire_fin"));
                event.setDescription(rs2.getString("description"));
                event.setImage(rs2.getString("image"));
                event.setNom_categorie(rs2.getString("nom"));
                //event.setId_user(rs2.getInt("id_user"));

                ListEvent.add(event);
                System.out.println(event.toString());                             
            }
        } 
        
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return ListEvent;
        
    }


    @Override
    public List<Evenement> chercherEvenementParCategorie(String nomCategorie)
    {
        List<Evenement> ListEvent=new ArrayList <Evenement>();    
         try {
             
             LocalDate DateJour=LocalDate.now();
             System.out.println(DateJour);
             
              String sql="SELECT  * from evenements INNER JOIN categorie c on evenements.id_categorie = c.id_categorie where c.nom = '"+nomCategorie+"';";
     
             Statement st2 = conn.createStatement();
             ResultSet  rs2=st2.executeQuery(sql);
             System.out.println("Affichage Done");
             
             while(rs2.next())
             {
                Evenement event = new Evenement();
                Timestamp input = rs2.getTimestamp("date_debut");              
                Timestamp input2 = rs2.getTimestamp("date_fin");
                LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate date2 = input2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDateTime newD = rs2.getTimestamp("date_debut").toLocalDateTime();
                LocalDateTime newG = rs2.getTimestamp("date_fin").toLocalDateTime();
                java.util.Date ddd = new java.util.Date();
                java.sql.Date ddd2= new java.sql.Date(input.getTime());
                event.setId_event(rs2.getInt("id_event"));
                event.setDate_debut(date);
                event.setDate_fin(date2);
                event.setHoraire_com(rs2.getString("horaire_com"));
                event.setHoraire_fin(rs2.getString("horaire_fin"));
                event.setDescription(rs2.getString("description"));
                event.setImage(rs2.getString("image"));
                //event.setId_categorie(rs2.getInt("id_categorie"));
                event.setNom_categorie(rs2.getString("nom"));
                //event.setId_user(rs2.getInt("id_user"));
                 ListEvent.add(event);
                 System.out.println(event.toString());
             }
         }
             
             catch (SQLException ex)
                     
                     {
                     System.out.println("SQLException: " + ex.getMessage());
                     System.out.println("SQLState: " + ex.getSQLState());
                     System.out.println("VendorError: " + ex.getErrorCode());
                     }
             
             return ListEvent;
        
        
    }  
    



    @Override
    public List<Evenement> chercherEvenementParNomUtilisateur(String nomUti)
    {
     List<Evenement> ListEvent=new ArrayList <Evenement>();              
       ResultSet rs;
        try 
        {
            String sql2="SELECT  * from evenements INNER JOIN fos_user u on evenements.id_user = u.id where u.username = '"+nomUti+"';";
            Statement st2 = conn.createStatement();
            ResultSet  rs2=st2.executeQuery(sql2);       
            System.out.println("Affichage Done");
            
            while(rs2.next())
            {                
                Evenement event = new Evenement();
                Timestamp input = rs2.getTimestamp("date_debut");              
                Timestamp input2 = rs2.getTimestamp("date_fin");
                LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate date2 = input2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDateTime newD = rs2.getTimestamp("date_debut").toLocalDateTime();
                LocalDateTime newG = rs2.getTimestamp("date_fin").toLocalDateTime();
                java.util.Date ddd = new java.util.Date();
                java.sql.Date ddd2= new java.sql.Date(input.getTime());
                event.setId_event(rs2.getInt("id_event"));
                event.setDate_debut(date);
                event.setDate_fin(date2);
                event.setHoraire_com(rs2.getString("horaire_com"));
                event.setHoraire_fin(rs2.getString("horaire_fin"));
                event.setDescription(rs2.getString("description"));
                event.setImage(rs2.getString("image"));
//                event.setId_categorie(rs2.getInt("id_categorie"));
//                event.setId_user(rs2.getInt("id_user"));


                ListEvent.add(event);
                System.out.println(event.toString());                             
            }
        } 
        
        catch (SQLException ex) 
            
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return ListEvent;   
        
    }
  

    @Override
    public void supprimmerEvenement(LocalDate date) 
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Alerte suppression");
        alert.setHeaderText("Suppression d'un évènement");
        alert.setContentText("Êtes-vous sûr(e) de vouloir supprimer?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {

        try {
            String sql ="DELETE FROM evenements WHERE date_debut = '"+date+"';";
            System.out.println(sql);
            Statement stl = conn.createStatement();
            int k = stl.executeUpdate(sql);
            if (k > 0)
            {
                System.out.println("Delete evenement Done");
            }
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
    public List<String> listerNomsCategorie() 
    {
        List<String> ListCat=new ArrayList <String>();              
        ResultSet rs;
        String sql ="select nom from categorie where type='Evenement';";

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
    public Categorie getCategorie(int idCategorie ) 
    {
        Categorie categorie = new Categorie();
                      
        ResultSet rs;
        String sql ="select * from categorie where id_categorie='"+idCategorie+"' and type = 'Evenement';";

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
    
     @Override
    public Categorie getCategorie(String nomCategorie ) 
    {
        Categorie categorie = new Categorie();
                      
        ResultSet rs;
        String sql ="select * from categorie where nom='"+nomCategorie+"' and type= 'Evenement';";

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
    
    
    
    
    
    
    
    
    public List<Categorie> getCategorieEvt() 
    {
        List<Categorie> ListCat=new ArrayList <Categorie>(); 
        Categorie categorie = new Categorie();
                      
        ResultSet rs;
        String sql ="select * from categorie where type= 'Evenement';";

          try 
        {
            Statement stl = conn.createStatement();
            rs=stl.executeQuery(sql);          
            System.out.println("Affichage Done");
            while(rs.next())
            {
                categorie.setId_categorie(rs.getInt("id_categorie"));
               ListCat.add(categorie);
               
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
    public List<Evenement> chercherEvenementAujourdhui()
    {
        List<Evenement> ListEvent=new ArrayList <Evenement>();    
         try {
             
             LocalDate DateJour=LocalDate.now();
             System.out.println(DateJour);
             
             String sql ="select * from evenements where date_debut = '"+DateJour+"';";
             Statement st2 = conn.createStatement();
             ResultSet  rs2=st2.executeQuery(sql);
             System.out.println("Affichage Done");
             
             while(rs2.next())
             {
                 Evenement event = new Evenement();
                Timestamp input = rs2.getTimestamp("date_debut");              
                Timestamp input2 = rs2.getTimestamp("date_fin");
                LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate date2 = input2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDateTime newD = rs2.getTimestamp("date_debut").toLocalDateTime();
                LocalDateTime newG = rs2.getTimestamp("date_fin").toLocalDateTime();
                java.util.Date ddd = new java.util.Date();
                java.sql.Date ddd2= new java.sql.Date(input.getTime());
                event.setId_event(rs2.getInt("id_event"));
                event.setDate_debut(date);
                event.setDate_fin(date2);
                event.setHoraire_com(rs2.getString("horaire_com"));
                event.setHoraire_fin(rs2.getString("horaire_fin"));
                event.setDescription(rs2.getString("description"));
                event.setImage(rs2.getString("image"));
        
                 ListEvent.add(event);
                 System.out.println(event.toString());
             }
         }
             
             catch (SQLException ex)
                     
                     {
                     System.out.println("SQLException: " + ex.getMessage());
                     System.out.println("SQLState: " + ex.getSQLState());
                     System.out.println("VendorError: " + ex.getErrorCode());
                     }
             
             return ListEvent;
        
        
    }  
    
    @Override
    public List<Evenement> chercherEvenementParDate(LocalDate datee)
    {
        List<Evenement> ListEvent=new ArrayList <Evenement>();
         try {
             
             //  data = FXCollections.observableArrayList();
             //List<Evenement> ListEvent=new ArrayList <Evenement>();
             ResultSet rs;
             String sql ="select * from evenements where date_debut = '"+datee+"';";
             
             Statement st2 = conn.createStatement();
             ResultSet  rs2=st2.executeQuery(sql);
             System.out.println("Affichage Done");
             
             while(rs2.next())
             {
                 Evenement event = new Evenement();
                 Timestamp input = rs2.getTimestamp("date_debut");
                 Timestamp input2 = rs2.getTimestamp("date_fin");
                 LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                 LocalDate date2 = input2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                 LocalDateTime newD = rs2.getTimestamp("date_debut").toLocalDateTime();
                 LocalDateTime newG = rs2.getTimestamp("date_fin").toLocalDateTime();
                 java.util.Date ddd = new java.util.Date();
                 java.sql.Date ddd2= new java.sql.Date(input.getTime());
                 event.setId_event(rs2.getInt("id_event"));
                 event.setDate_debut(date);
                 event.setDate_fin(date2);
                 event.setHoraire_com(rs2.getString("horaire_com"));
                 event.setHoraire_fin(rs2.getString("horaire_fin"));
                 event.setDescription(rs2.getString("description"));
                 event.setImage(rs2.getString("image"));
//                 event.setId_categorie(rs2.getInt("id_categorie"));
//                 event.setId_user(rs2.getInt("id_user"));
                 
                 ListEvent.add(event);
                 System.out.println(event.toString());
                 
             }
             return ListEvent;
         }
                      
             catch (SQLException ex)
                     
                     {
                     System.out.println("SQLException: " + ex.getMessage());
                     System.out.println("SQLState: " + ex.getSQLState());
                     System.out.println("VendorError: " + ex.getErrorCode());
                     }
           return ListEvent;
    }  


       @Override
    public List<Evenement> chercherEvenementDemain()
    {LocalDate tomorrow = LocalDate.now().plusDays(1);
        List<Evenement> ListEvent=new ArrayList <Evenement>();
         try {
             
             //  data = FXCollections.observableArrayList();
             //List<Evenement> ListEvent=new ArrayList <Evenement>();
             ResultSet rs;
        String sql ="select * from evenements where date_debut = '"+tomorrow+"';";
        Statement st2 = conn.createStatement();
             ResultSet  rs2=st2.executeQuery(sql);
             System.out.println("Affichage Done");
             
             while(rs2.next())
             {
                 Evenement event = new Evenement();
                 Timestamp input = rs2.getTimestamp("date_debut");
                 Timestamp input2 = rs2.getTimestamp("date_fin");
                 LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                 LocalDate date2 = input2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                 LocalDateTime newD = rs2.getTimestamp("date_debut").toLocalDateTime();
                 LocalDateTime newG = rs2.getTimestamp("date_fin").toLocalDateTime();
                 java.util.Date ddd = new java.util.Date();
                 java.sql.Date ddd2= new java.sql.Date(input.getTime());
                 event.setId_event(rs2.getInt("id_event"));
                 event.setDate_debut(date);
                 event.setDate_fin(date2);
                 event.setHoraire_com(rs2.getString("horaire_com"));
                 event.setHoraire_fin(rs2.getString("horaire_fin"));
                 event.setDescription(rs2.getString("description"));
                 event.setImage(rs2.getString("image"));
//                 event.setId_categorie(rs2.getInt("id_categorie"));
//                 event.setId_user(rs2.getInt("id_user"));
                 
                 ListEvent.add(event);
                 System.out.println(event.toString());
                 
             }
             return ListEvent;
         }
                      
             catch (SQLException ex)
                     
                     {
                     System.out.println("SQLException: " + ex.getMessage());
                     System.out.println("SQLState: " + ex.getSQLState());
                     System.out.println("VendorError: " + ex.getErrorCode());
                     }
           return ListEvent;
    }  

    @Override
    public List<Evenement> chercherEvenementSemaine() 
    {
        List<Evenement> ListEvent=new ArrayList <Evenement>();
         try {
             
             //  data = FXCollections.observableArrayList();
             //List<Evenement> ListEvent=new ArrayList <Evenement>();
             ResultSet rs;
        data = FXCollections.observableArrayList();
        LocalDate nextWeek = LocalDate.now().plusWeeks(1);
        System.out.println(nextWeek);
        LocalDate today = LocalDate.now();
        System.out.println(today);
        String sql ="select * from evenements where (date_debut<'"+nextWeek+"') AND (date_debut='"+today+"');";
        Statement st2 = conn.createStatement();
             ResultSet  rs2=st2.executeQuery(sql);
             System.out.println("Affichage Done");
             
             System.out.println("Affichage Done");
             
             while(rs2.next())
             {
                 Evenement event = new Evenement();
                 Timestamp input = rs2.getTimestamp("date_debut");
                 Timestamp input2 = rs2.getTimestamp("date_fin");
                 LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                 LocalDate date2 = input2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                 LocalDateTime newD = rs2.getTimestamp("date_debut").toLocalDateTime();
                 LocalDateTime newG = rs2.getTimestamp("date_fin").toLocalDateTime();
                 java.util.Date ddd = new java.util.Date();
                 java.sql.Date ddd2= new java.sql.Date(input.getTime());
                 event.setId_event(rs2.getInt("id_event"));
                 event.setDate_debut(date);
                 event.setDate_fin(date2);
                 event.setHoraire_com(rs2.getString("horaire_com"));
                 event.setHoraire_fin(rs2.getString("horaire_fin"));
                 event.setDescription(rs2.getString("description"));
                 event.setImage(rs2.getString("image"));
//                 event.setId_categorie(rs2.getInt("id_categorie"));
//                 event.setId_user(rs2.getInt("id_user"));
                 
                 ListEvent.add(event);
                 System.out.println(event.toString());
                 
             }
             return ListEvent;
         }
                      
             catch (SQLException ex)
                     
                     {
                     System.out.println("SQLException: " + ex.getMessage());
                     System.out.println("SQLState: " + ex.getSQLState());
                     System.out.println("VendorError: " + ex.getErrorCode());
                     }
           return ListEvent;
    }  

    public boolean checkisPartcipant(Utilisateur u, Evenement e)
    {
        
        // String sql = "Select * from action WHERE id_user= "+u.getId()+" and id_event ="+e.getId_event()+";";
         String sql = "Select * from action WHERE id_user= 1 and id_event ="+e.getId_event()+";";
         try{
             Statement stm = conn.createStatement();
             ResultSet res = stm.executeQuery(sql);
             while(res.next()){
                 if(res.getString("type").equals("Participe")){
                     return true;
                 }
             }  
         }catch(SQLException ex){
             
         }  
        return false ;
    }
    
        public boolean checkisInteresse(Utilisateur u, Evenement e) {
//        String sql = "Select * from action WHERE id_user= "+u.getId()+"and id_event ="+e.getId_event()+";";
         String sql = "Select * from action WHERE id_user= 1 and id_event ="+e.getId_event()+";";
         try{
             Statement stm = conn.createStatement();
             ResultSet res = stm.executeQuery(sql);
             while(res.next()){
                 if(res.getString("type").equals("Interesse")){
                     return true;
                 }
             }  
         }catch(SQLException ex){
             
         }  
        return false ;
    }
        
        
        public void setParticipe(Utilisateur u, Evenement e){
           String sql = "INSERT INTO action  Values("+u.getId()+","+e.getId_event()+",'Participe');";
             //String sql = "INSERT INTO action  Values(1,"+e.getId_event()+",'Participe');";
         try{
             Statement stm = conn.createStatement();
             stm.executeUpdate(sql);             
         }catch(SQLException ex){
                     System.out.println("SQLException: " + ex.getMessage());
                     System.out.println("SQLState: " + ex.getSQLState());
                     System.out.println("VendorError: " + ex.getErrorCode());             
         }  
        }
        
        public void setInteresse(Utilisateur u, Evenement e){
             String sql = "INSERT INTO action  Values("+u.getId()+","+e.getId_event()+",'Interesse');";
             //String sql = "INSERT INTO action  Values(1,"+e.getId_event()+",'Interesse');";
         try{
             Statement stm = conn.createStatement();
             stm.executeUpdate(sql);             
         }catch(SQLException ex){
                     System.out.println("SQLException: " + ex.getMessage());
                     System.out.println("SQLState: " + ex.getSQLState());
                     System.out.println("VendorError: " + ex.getErrorCode());             
         }  
        }
        
        public void updateParticipe(Utilisateur u, Evenement e){
             String sql = "UPDATE  action  SET type= Participe   WHERE id_user="+u.getId()+"and id_event"+e.getId_event()+";";
             //String sql = "UPDATE  action  SET type= 'Participe'   WHERE id_user= 1 and id_event = "+e.getId_event()+";";
         try{
             Statement stm = conn.createStatement();
             stm.executeUpdate(sql);             
         }catch(SQLException ex){
                     System.out.println("SQLException: " + ex.getMessage());
                     System.out.println("SQLState: " + ex.getSQLState());
                     System.out.println("VendorError: " + ex.getErrorCode());             
         }  
        }
        
        public void updateInteresse(Utilisateur u, Evenement e){
             String sql = "UPDATE  action  SET type= Interesse   WHERE id_user="+u.getId()+"and id_event"+e.getId_event()+";";
            //String sql = "UPDATE  action  SET type= 'Interesse'   WHERE id_user=1 and id_event = "+e.getId_event()+";";
         try{
             Statement stm = conn.createStatement();
             stm.executeUpdate(sql);             
         }catch(SQLException ex){
                     System.out.println("SQLException: " + ex.getMessage());
                     System.out.println("SQLState: " + ex.getSQLState());
                     System.out.println("VendorError: " + ex.getErrorCode());             
         }  
        }
        
               public void AnnulerInteresse(Utilisateur u, Evenement e){
            String sql = "Delete * from   action  WHERE id_user="+u.getId()+"and id_event = "+e.getId_event()+";";
             //String sql = "Delete * from   action  WHERE id_user= 1 and id_event = "+e.getId_event()+";";
         try{
             Statement stm = conn.createStatement();
             stm.executeUpdate(sql);             
         }catch(SQLException ex){
                     System.out.println("SQLException: " + ex.getMessage());
                     System.out.println("SQLState: " + ex.getSQLState());
                     System.out.println("VendorError: " + ex.getErrorCode());             
         }  
        }
                      public void AnnulerParticipant(Utilisateur u, Evenement e){
            // String sql = "Delete *  action   WHERE id_user=1 and id_event= "+e.getId_event()+";";
            String sql = "Delete *  action   WHERE id_user="+u.getId()+"and id_event = "+e.getId_event()+";";
         try{
             Statement stm = conn.createStatement();
             stm.executeUpdate(sql);             
         }catch(SQLException ex){
                     System.out.println("SQLException: " + ex.getMessage());
                     System.out.println("SQLState: " + ex.getSQLState());
                     System.out.println("VendorError: " + ex.getErrorCode());             
         }  
        }


    
    
    public void modifierEvenementArchive()
    {
        String sql5="select * from evenements where date_fin<now();";
        
        try 
        {
            Statement st2 = conn.createStatement();
            Statement stl = conn.createStatement();
            
           
            ResultSet rs=stl.executeQuery(sql5); 
            while(rs.next())
            {
                int i=rs.getInt("id_event");
                
                String sql ="UPDATE evenements SET archive = 1 WHERE id_event ="+ i+";";
                st2.executeUpdate(sql);
            }
            
            
            System.out.println("Update Evenement done");
        } 
        catch (SQLException ex) 
        {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }   
    }  
}


   