/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.MyDB;
import Entities.Utilisateur;
import IServices.IUtilisateur;
import Utils.mail;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class UtilisateurService implements IUtilisateur {

    Connection conn;

    public UtilisateurService() {
        this.conn = MyDB.getinstance().getConnexion();
    }

    @Override
    public void ajouterUtilisateur(Utilisateur u) {
        String sql = "INSERT INTO fos_user (nom, prenom, username,username_canonical, email,email_canonical, password, adresse, date_naissance, code_postal, sexe, num_tel, photo_profil, pays, roles, status ,type, specialite,enabled, salt, locked, expired, confirmation_token,credentials_expired) values('" + u.getNom() + "','" + u.getPrenom() + "','" + u.getUsername() + "','" + u.getUsername_canonical() + "','"
                + u.getEmail() + "','" + u.getEmail_canonical() + "','" + u.getPassword() + "','" + u.getAdresse() + "','" + u.getDate_naissance() + "',"
                + u.getCode_postal() + ",'" + u.getSexe() + "'," + u.getNum_tel() + ",'" + u.getPhoto_profil() + "','"
                + u.getPays() + "' ,'" + u.getRoles() + "', 'false' , '" + u.getType_partenaire() + "','" + u.getSpecialite_partenaire() + "'," + u.getEnabled() + ",'" + u.getSalt() + "'," + u.getLocked() + "," + u.getExpired() + " , '" + u.getConfirmation_token() + "'," + u.getCredentials_expired() + ");";

        try {
            Statement stl = conn.createStatement();

            int res = stl.executeUpdate(sql);

            //if (res > 0) {

                int b;
                b = (int) (Math.random() * 9999);

                String code = "" + b;

                CodeService c = new CodeService();
                c.ajouterCode(code);
                mail m = new mail();
                System.out.println(code+ "  "+u.getEmail());
                m.send(u.getEmail(), "Validation", code);
                System.out.println(u.getNom() + " " + u.getPrenom() + " " + " est ajouté(e) ");
//            } else {
//                System.err.println("Vérifiez vos données");
//            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
            System.err.println("sql: " + sql);
        }

    }

    @Override
    public void supprimerUtilisateur(Utilisateur u) {

        String sql = "UPDATE fos_user SET enabled = 0 WHERE id= " + u.getId() + ";";
        try {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Ce compte est désactivé");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.out.println("Introuvable");
        }
    }

    @Override
    public void modifierMotDePasse(String password, int id) {
        String sql = "UPDATE fos_user SET password = '" + password + "' WHERE id =" + id + ";";

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
        }
    }

    @Override
    public void modifierUtilisateur(Utilisateur u) {
        String sql = "UPDATE fos_user SET nom = '" + u.getNom() + "', prenom = '" + u.getPrenom() + "', adresse = '" + u.getAdresse() + "',code_postal ="
                + u.getCode_postal() + ",num_tel =" + u.getNum_tel() + ",roles ='" + u.getRoles() + "' WHERE id =" + u.getId() + ";";

        System.out.println(sql);
        try {
            Statement stl = conn.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Modifié");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    @Override
    public List<Utilisateur> afficherUtilisateurs() {
        List<Utilisateur> ListUsers = new ArrayList<Utilisateur>();

        ResultSet rs;

        String sql = "select * from fos_user";
        try {
            Statement stl = conn.createStatement();
            rs = stl.executeQuery(sql);
            System.out.println("Affichés");
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setUsername(rs.getString("username"));
                user.setAdresse(rs.getString("adresse"));

                Timestamp input = rs.getTimestamp("date_naissance");
                LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//                java.util.Date ddd = new java.util.Date();
//                java.sql.Date ddd2= new java.sql.Date(input.getTime());
                user.setDate_naissance(date);

                //user.setDate_naissance(date));
                user.setSexe(rs.getString("sexe"));
                user.setNum_tel(rs.getInt("num_tel"));

                ListUsers.add(user);
                System.out.println(user.toString());
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return ListUsers;
    }

//    @Override
//    public Utilisateur afficherUtilisateur() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    @Override
    public Utilisateur Authentifier(String login, String password) {
        Utilisateur user = new Utilisateur();
        if (Authentification(login, password)) {

            String sql = "SELECT * FROM fos_user WHERE (email='" + login + "'  and password='" + password + "') or (username='" + login + "' and password='" + password + "');";

            try {
                Statement stl = conn.createStatement();
                ResultSet rs = stl.executeQuery(sql);

                while (rs.next()) {
                    user.setId(rs.getInt("id"));
                    user.setNom(rs.getString("nom"));
                    user.setUsername(rs.getString("username"));
                    user.setPrenom(rs.getString("prenom"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setAdresse(rs.getString("adresse"));
                    user.setCode_postal(rs.getInt("code_postal"));
                    user.setSexe(rs.getString("sexe"));
                    user.setNum_tel(rs.getInt("num_tel"));
                    user.setPhoto_profil(rs.getString("photo_profil"));
                    user.setPays(rs.getString("pays"));
                    user.setType_partenaire(rs.getString("type"));
                    user.setSpecialite_partenaire(rs.getString("specialite"));
                    user.setEnabled(rs.getInt("enabled"));
                    user.setRoles(rs.getString("roles"));
                    user.setStatus(rs.getString("status"));
                }

            } catch (SQLException ex) {
                Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println(user.toString());

        }
        return user;
    }

    @Override
    public boolean Authentification(String login, String password) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(login);

        if (m.matches()) {

            try {
                /// login == email donc je veux selecter selon email
                System.out.println("Tentative avec email");

                String sql = "Select * from fos_user WHERE email = '" + login + "' and password = '" + password + "';";
                Statement stl = conn.createStatement();
                ResultSet rs = stl.executeQuery(sql);
                if (rs.next()) {
                    System.out.println(rs.getInt("id") + " is connected ");
                } else {
                    System.err.println("check your email or password");
                    return true;
                }
            } catch (SQLException ex) {
                System.err.println("check your email or password");
                return false;
            }

        } else { /// login == username donc je veux selecter selon username 
            System.err.println("Tentative avec username");
            try {

                String sql = "Select * from fos_user WHERE username = '" + login + "' and password = '" + password + "';";
                Statement stl = conn.createStatement();
                ResultSet rs = stl.executeQuery(sql);
                if (rs.next()) {
                    System.out.println(rs.getInt("id") + " is connected ");
                    return true;
                } else {
                    System.err.println("check your username or password");
                    return false;
                }
            } catch (SQLException ex) {
                System.err.println("check your username or password");
            }
        }
        return false;
    }

//    @Override
//    public Utilisateur rehercherUtilisateur(String username) {
//        Utilisateur user = new Utilisateur();
//                      
//        ResultSet rs;
//        String sql ="select * from fos_user where username='"+username+"';";
//
//          try 
//        {
//            Statement stl = conn.createStatement();
//            rs=stl.executeQuery(sql);          
//            System.out.println("Affiché");
//            while(rs.next())
//            {
//                user.setUsername("username");
//                user.setNom(rs.getString("nom"));
//                user.setPrenom("prenom");
//                user.setAdresse("adresse");
//                user.setEmail("email");
//                System.out.println(user.toString());                
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
//        return user;
//    }
    @Override
    public List<Utilisateur> rechercherUtilisateur(String username) {
        List<Utilisateur> ListUsers = new ArrayList<Utilisateur>();

        ResultSet rs;

        String sql = "select * from fos_user where username='" + username + "';";
        try {
            Statement stl = conn.createStatement();
            rs = stl.executeQuery(sql);
            System.out.println("Affichés");
            while (rs.next()) {
                Utilisateur user = new Utilisateur();
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setUsername(rs.getString("username"));
                user.setAdresse(rs.getString("adresse"));

                Timestamp input = rs.getTimestamp("date_naissance");
                LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//                java.util.Date ddd = new java.util.Date();
//                java.sql.Date ddd2= new java.sql.Date(input.getTime());
                user.setDate_naissance(date);

                //user.setDate_naissance(date));
                user.setSexe(rs.getString("sexe"));
                user.setNum_tel(rs.getInt("num_tel"));

                ListUsers.add(user);
                System.out.println(user.toString());
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return ListUsers;
    }

    @Override
    public void validerCompte(String code, Utilisateur u) {
        CodeService c = new CodeService();
        String code2 = c.rechercheCode(code);
        System.out.println(code2);
        if (code2.equals(code)) {
            try {
                String query = "UPDATE fos_user SET status='true' where username ='" + u.getUsername() + "' ";
                System.err.println(query);

                Statement stm = conn.createStatement();
                c.supprimerCode(code);
                stm.executeUpdate(query);
                System.out.println("Ajout effectué");
            } catch (SQLException ex) {
                System.out.println("Echec d'ajout");
            }
        }

    }

}
