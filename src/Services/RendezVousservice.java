/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.MyDB;
import Entities.Etablissement;
import Entities.Notification;
import Entities.RendezVous;
import Entities.Service;
import Entities.Utilisateur;
import IServices.IRendezVous;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author acer
 */
public class RendezVousservice implements IRendezVous {

    Connection connexion;

    public RendezVousservice() {
        connexion = MyDB.getinstance().getConnexion();
    }

    @Override
    public int ajouterRendezvous(RendezVous rdv) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alerte Ajout");
        alert.setHeaderText("Ajout d'un Rendez-Vous");
        alert.setContentText("Êtes vous sûr de vouloir ajouter?");
        Optional<ButtonType> result = alert.showAndWait();
        rdv.getUser().setId(1);
        String eta="demandé";
        if (result.get() == ButtonType.OK ) {
            try {
                String query = "INSERT INTO rdv ( date,time, id_user, id_service, id_etab, etat )"
                        + "values ( '" + rdv.getDate() + "', '"+rdv.getTime()+"',  "+rdv.getUser().getId()+" , " + rdv.getServ().getId() + ", " + rdv.getEtab().getId() + ", '"+eta+"' );";
                PreparedStatement state = connexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                state.executeUpdate();
                ResultSet rs = state.getGeneratedKeys();
                System.out.println("Ajout effectué");
                 if(rs.next()){
                     return rs.getInt(1);
                 }
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());

            }
            

        }
        return 0 ;
    }

    @Override
    public void supprimerRendezvous(RendezVous rdv) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alerte Suppression");
        alert.setHeaderText("suppression d'un Rendez-Vous");
        alert.setContentText("Êtes vous sûr de vouloir supprimer?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {

                Statement state = connexion.createStatement();
                state.executeUpdate("DELETE from rdv where (date='" + rdv.getDate() + "') and (time='"+rdv.getTime()+"')");
                System.out.println("supression effectuée");
            } catch (SQLException ex) {
                System.out.println("supression echouee");
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());

            }
        }
    }
   
 
//    public List<FichePatient> afficherToutFicheClient() {
//      List<FichePatient> ListFiche=new ArrayList <FichePatient>();
//        
//        //ResultSet rs;
//        
//         String sql ="select s.nom ,   f.username ,fos_user.username FROM fichepatient f, etablissements etab ,fos_user fos_user  WHERE (f.idEtab = etab.id) and (f.idPatient = fos_user.id);";
//        try 
//        {
//            Statement stl = conn.createStatement();
//             ResultSet rs=stl.executeQuery(sql);          
//            System.err.println("Display Done");
//            while(rs.next())
//            {   
//                FichePatient fiche = new FichePatient();
//                //fiche.setId(rs.getInt("id"));
//                fiche.setIdPatient(rs.getInt("idPatient"));
//                fiche.setIdEtab(rs.getInt("idEtab"));
//                fiche.setSuivie(rs.getString("suivie"));
//                fiche.setNomEtab(rs.getString("nom"));
//                fiche.setNomUser(rs.getString("username"));
//                ListFiche.add(fiche);
//                System.out.println(fiche.toString());                
//            }
//        } 
//        catch (SQLException ex) 
//            
//        {
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());
//        }
//        
//       return ListFiche ;
//    }

    @Override
    public ObservableList<RendezVous> afficherRendezvous(int i) {
        //List<RendezVous> lsrdv = new ArrayList<RendezVous>();
          ObservableList<RendezVous> lsrdv = FXCollections.observableArrayList();
          
        try {
            Statement state = connexion.createStatement();
            Statement state2 = connexion.createStatement();
            ResultSet rs = state.executeQuery("select * from rdv where id_user="+i+"");

            while (rs.next()) {
               
                
                ResultSet rs2 = state2.executeQuery("select f.username, s.nom, e.noms from fos_user f,services s, etablissements e where (f.id="+rs.getInt("id_user")+")");
                while(rs2.next())
                {Etablissement e = new Etablissement();
                Service s = new Service();
                Utilisateur f=new Utilisateur();
                
                RendezVous rdv = new RendezVous();
                rdv.setId_rdv(rs.getInt("id_rdv"));
                rdv.setDate(rs.getString("date"));
                rdv.setTime(rs.getString("time"));
                rdv.setUser(f);
                rdv.setEtab(e);
                rdv.setService(s);
                
                s.setNom(rs2.getString("nom"));
                e.setNom(rs2.getString("noms"));
                f.setUsername(rs2.getString("username"));
                rdv.setNometab(rdv.getEtab().getNom());
                rdv.setNomservice(rdv.getService().getNom());
                rdv.setUsername(rdv.getUser().getUsername());
                rdv.setEtat(rs.getString("etat"));
              
                
                     
                lsrdv.add(rdv);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Affichage echoué");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return lsrdv;
    }

    @Override
    public void modifierRendezvous(RendezVous rdv) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alerte Modification");
        alert.setHeaderText("Modification d'un Rendez-Vous");
        alert.setContentText("Êtes vous sûr de vouloir modifier?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                String query = "UPDATE rdv SET  date='" + rdv.getDate() + "', time='"+rdv.getTime()+"', id_service=" + rdv.getServ().getId() + ",id_etab=" + rdv.getEtab().getId() + ",etat='" + rdv.getEtat() + "' where id_rdv=" + rdv.getId_rdv() + ";";

                Statement stm = connexion.createStatement();
                stm.executeUpdate(query);
                System.out.println("Modification effectué");
            } catch (SQLException ex) {
                System.out.println("Echec de modification");
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
    }

    @Override
    public ObservableList<RendezVous> chercherRendezvousDate(String dat, String heur, int id ) {
        ObservableList<RendezVous> lsrdv = FXCollections.observableArrayList();
        
        try {
            String sql = "select * from rdv where (date='"+dat+"') and (time='"+heur+"') and (id_user='"+id+"')";
            
            Statement state2 = connexion.createStatement();
            Statement state = connexion.createStatement();
            ResultSet rs = state.executeQuery(sql);
            
                
                

            while (rs.next()) {


                ResultSet rs2 = state2.executeQuery("select f.username, s.nom, e.noms from fos_user f,services s, etablissements e where (f.id="+rs.getInt("id_user")+")");
                while(rs2.next())
                {Etablissement e = new Etablissement();
                Service s = new Service();
                Utilisateur f=new Utilisateur();
                
                RendezVous rdv = new RendezVous();
                rdv.setId_rdv(rs.getInt("id_rdv"));
                rdv.setDate(rs.getString("date"));
                rdv.setTime(rs.getString("time"));
                rdv.setUser(f);
                rdv.setEtab(e);
                rdv.setService(s);
                
                s.setNom(rs2.getString("nom"));
                e.setNom(rs2.getString("noms"));
                f.setUsername(rs2.getString("username"));
                rdv.setNometab(rdv.getEtab().getNom());
                rdv.setNomservice(rdv.getService().getNom());
                rdv.setUsername(rdv.getUser().getUsername());
                rdv.setEtat(rs.getString("etat"));
              
                lsrdv.add(rdv);

            }}
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return lsrdv;
    }

    /**
     *
     * @throws SQLException
     */
    @Override
    public Notifications comparedate() {
Notifications notificationbuilder=Notifications.create()
                .title("RAPPEL")
                .text("Vous avez un rendez-vous dans deux jours!!")
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_LEFT);
              
        try {
            String sql = "select date from rdv ";
            Statement state = connexion.createStatement();
            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                Timestamp input = rs.getTimestamp("date");
                LocalDateTime datee = input.toLocalDateTime();
                LocalDateTime l = LocalDateTime.now();
                int year = l.getYear();
                int month = l.getMonthValue();
                int day = l.getDayOfYear();

                if ((datee.getYear() == year) && (datee.getMonthValue() == month) && (day + 2 == datee.getDayOfYear())) {
                            notificationbuilder.showInformation();
      
                   
                }

            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }
        return notificationbuilder;
    }
    
    @Override
    public void annulerRendezVous(RendezVous rdv){
       String s="annulée";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alerte Anulation");
        alert.setHeaderText("Annulation d'un Rendez-Vous");
        alert.setContentText("Êtes vous sûr de vouloir annuler?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                String query = "UPDATE rdv SET etat='"+s+"'  where id_rdv=" + rdv.getId_rdv() + ";";
                Statement stm = connexion.createStatement();
                stm.executeUpdate(query);
                
                System.out.println("Rendez-Vous annulée");
            } catch (SQLException ex) {
                System.out.println("Echec de l'annulation");
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        
    }

    @Override
    public void ajouterRendezvouspart(RendezVous rdv) {
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alerte Ajout");
        alert.setHeaderText("Ajout d'un Rendez-Vous");
        alert.setContentText("Êtes vous sûr de vouloir ajouter?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK ) {
            try {
                String query = "INSERT INTO rdv ( date,time, id_user, id_service, id_etab, etat)"
                        + "values ( '" + rdv.getDate() + "', '"+rdv.getTime()+"', " + rdv.getId_user() + ", " + rdv.getId_service() + ", " + rdv.getId_etab() + ", '" + rdv.getEtat() + "' )";
                Statement state = connexion.createStatement();
                state.executeUpdate(query);
                System.out.println("Ajout effectué");
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());

            }

        }
    }

    @Override
    public void supprimerRendezvouspart(RendezVous rdv) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alerte Suppression");
        alert.setHeaderText("suppression d'un Rendez-Vous");
        alert.setContentText("Êtes vous sûr de vouloir supprimer?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {

                Statement state = connexion.createStatement();
                state.executeUpdate("DELETE from rdv where (date='" + rdv.getDate() + "') and (time='"+rdv.getTime()+"')");
                System.out.println("supression effectuée");
            } catch (SQLException ex) {
                System.out.println("supression echouee");
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());

            }
        }
    }

    @Override
    public List<RendezVous> afficherRendezvouspart(int i) {
       ObservableList<RendezVous> lsrdv = FXCollections.observableArrayList();
          
        try {
            Statement state = connexion.createStatement();
            Statement state2 = connexion.createStatement();
            ResultSet rs = state.executeQuery("select * from rdv where id_user="+i+"");

            while (rs.next()) {
               
                
                ResultSet rs2 = state2.executeQuery("select f.username, s.nom, e.noms from fos_user f,services s, etablissements e where (f.id="+rs.getInt("id_user")+")");
                while(rs2.next())
                {Etablissement e = new Etablissement();
                Service s = new Service();
                Utilisateur f=new Utilisateur();
                
                RendezVous rdv = new RendezVous();
                rdv.setId_rdv(rs.getInt("id_rdv"));
                rdv.setDate(rs.getString("date"));
                rdv.setTime(rs.getString("time"));
                rdv.setUser(f);
                rdv.setEtab(e);
                rdv.setService(s);
                
                s.setNom(rs2.getString("nom"));
                e.setNom(rs2.getString("noms"));
                f.setUsername(rs2.getString("username"));
                rdv.setNometab(rdv.getEtab().getNom());
                rdv.setNomservice(rdv.getService().getNom());
                rdv.setUsername(rdv.getUser().getUsername());
                rdv.setEtat(rs.getString("etat"));
              
                
                     
                lsrdv.add(rdv);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Affichage echoué");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return lsrdv;
    }

    

    @Override
    public void modifierRendezvouspart(RendezVous rdv) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alerte Modification");
        alert.setHeaderText("Modification d'un Rendez-Vous");
        alert.setContentText("Êtes vous sûr de vouloir modifier?");
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == ButtonType.OK) {
            try {
                String e="demandé";
                String query = "UPDATE rdv SET  date='" + rdv.getDate() + "', time='"+rdv.getTime()+"', etat='"+e+"' where id_rdv=" + rdv.getId_rdv() + ";";

                Statement stm = connexion.createStatement();
                stm.executeUpdate(query);
                System.out.println("Modification effectué");
            } catch (SQLException ex) {
                System.out.println("Echec de modification");
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
    }

    @Override
    public ObservableList<RendezVous> chercherRendezvousDatepart(String dtt, String hrr,int ii) {
    ObservableList<RendezVous> lsrdvv = FXCollections.observableArrayList();
        
        try {
            String sql = "select * from rdv where (date='"+dtt+"') and (time='"+hrr+"') and (id_user='"+ii+"')";
            System.out.println(sql);

            Statement state = connexion.createStatement();
            ResultSet rs = state.executeQuery(sql);
            
                
                

            while (rs.next()) {

//                Timestamp input = rs.getTimestamp("date");
//                LocalDateTime date = input.toLocalDateTime();
                
                
                
                RendezVous rdv = new RendezVous();
                rdv.setId_rdv(rs.getInt("id_rdv"));
                rdv.setDate(rs.getString("date"));
                rdv.setTime(rs.getString("time"));
                rdv.setId_user(rs.getInt("id_user"));
                rdv.setId_etab(rs.getInt("id_etab"));
                rdv.setId_service(rs.getInt("id_service"));
                

                rdv.setEtat(rs.getString("etat"));
                
                System.out.println(rdv.toString());
                lsrdvv.add(rdv);

            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return lsrdvv;
    }

    @Override
    public void annulerRendezVouspart(RendezVous rdv) {
        String s="annulée";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alerte Anulation");
        alert.setHeaderText("Annulation d'un Rendez-Vous");
        alert.setContentText("Êtes vous sûr de vouloir annuler?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                String query = "UPDATE rdv SET etat='"+s+"' where id_rdv=" + rdv.getId_rdv() + ";";
                Statement stm = connexion.createStatement();
                stm.executeUpdate(query);
                
                System.out.println("Rendez-Vous annulée");
            } catch (SQLException ex) {
                System.out.println("Echec de l'annulation");
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
            
        }
    }
    
}
