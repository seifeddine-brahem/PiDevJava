/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.MyDB;
import Entities.Notification;
import Entities.RendezVous;
import Entities.Service;
import Entities.Utilisateur;
import IServices.INotification;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author anis
 */
public class NotificationService implements INotification {

    Connection connexion;

    public NotificationService() {
        connexion = MyDB.getinstance().getConnexion();
    }

    @Override
    public void ajouterNotification(Notification n) {
        try {
            
            String query = "INSERT INTO notification ( id_sender, id_receiver, type, date, id_rdv)"
                    + "values ( " + n.getIdsender() + ", " + n.getIdreceiver() + ", '" + n.getType() + "', '" + LocalDate.now() + "', " + n.getIdrdv() + ");";
            Statement state = connexion.createStatement();
            state.executeUpdate(query);
            System.out.println(query);
            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());

        }

    }

    @Override
    public List<Notification> afficherNotifications(int iduser) {
        List<Notification> lsn = new ArrayList<Notification>();
        try {
            Statement state = connexion.createStatement();
            Statement state2 = connexion.createStatement();
            ResultSet rs = state.executeQuery("select * from notification where (id_receiver=" + iduser + ") and (view=" + 0 + ");");

            while (rs.next()) {
                ResultSet rs2 = state2.executeQuery("select f.username from fos_user f where (f.id=" + rs.getInt("id_sender") + ") ;");
                while (rs2.next()) {
                    Notification n = new Notification();
                   Utilisateur f = new Utilisateur();

                    n.setUser(f);

                    f.setUsername(rs2.getString("username"));
                    n.setUsername(n.getUser().getUsername());
                    n.setDate(rs.getDate("date"));
                    n.setIdreceiver(rs.getInt("id_receiver"));
                    n.setIdsender(rs.getInt("id_sender"));
                    n.setType(rs.getString("type"));
                    n.setView(rs.getInt("view"));

                    lsn.add(n);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Affichage echoué");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return lsn;
    }

    @Override
    public int calculernotifications(int iduser) {
        int i = 0;
        try {
            Statement state = connexion.createStatement();
            ResultSet rs = state.executeQuery("select * from notification where (id_user=" + iduser + ") and (view=" + 0 + ")");

            while (rs.next()) {

                Notification n = new Notification();
                n.setDate(rs.getDate("date"));
                n.setIdreceiver(rs.getInt("id_receiver"));
                n.setIdsender(rs.getInt("id_sender"));
                n.setType(rs.getString("type"));
                n.setView(rs.getInt("view"));

                i++;
            }
        } catch (SQLException ex) {
            System.out.println("Affichage echoué");
        }
        return i;
    }

    @Override
    public void updatenotif(int iduser) {

        try {
            int v=1;
            String query = "UPDATE rdv SET view="+v+"  where id_user=" + iduser + ";";

            Statement stm = connexion.createStatement();
            stm.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println("Echec de modification");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

}
