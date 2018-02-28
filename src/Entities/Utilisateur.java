/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author user
 */
public class Utilisateur {
   private int id;
   private String nom;
   private String prenom;
   private String username;
   private String username_canonical;
   private String email;
   private String email_canonical;
   private String password;
   private String adresse;
   private LocalDate date_naissance;
   private int code_postal;
   private String sexe;
   private String roles;
   private int num_tel;
   private String photo_profil;
   private String pays;
   private String type_partenaire;
   private String specialite_partenaire;
   private int enabled = 1;
   private String salt = null;
   private Date last_login = null;
   private int locked = 0;
   private int expired;
   private Date expires_at = null;
   private String confirmation_token = null;
   private Date password_requested_at = null;
   //this.roles = "user" ;
   private int credentials_expired = 0;
   private Date credentials_expire_at = null;
   

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getUsername() {
        return username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public int getEnabled() {
        return enabled;
    }

    public String getSalt() {
        return salt;
    }

    public String getPassword() {
        return password;
    }

    public Date getLast_login() {
        return last_login;
    }

    public int getLocked() {
        return locked;
    }

    public int getExpired() {
        return expired;
    }

    public Date getExpires_at() {
        return expires_at;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public Date getPassword_requested_at() {
        return password_requested_at;
    }

    public String getRoles() {
        return roles;
    }

    public int getCredentials_expired() {
        return credentials_expired;
    }

    public Date getCredentials_expire_at() {
        return credentials_expire_at;
    }

    public String getAdresse() {
        return adresse;
    }

    public LocalDate getDate_naissance() {
        return date_naissance;
    }

    public int getCode_postal() {
        return code_postal;
    }

    public String getSexe() {
        return sexe;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public String getPhoto_profil() {
        return photo_profil;
    }

    public String getPays() {
        return pays;
    }

    public String getType_partenaire() {
        return type_partenaire;
    }

    public String getSpecialite_partenaire() {
        return specialite_partenaire;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    public void setExpired(int expired) {
        this.expired = expired;
    }

    public void setExpires_at(Date expires_at) {
        this.expires_at = expires_at;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public void setPassword_requested_at(Date password_requested_at) {
        this.password_requested_at = password_requested_at;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setCredentials_expired(int credentials_expired) {
        this.credentials_expired = credentials_expired;
    }

    public void setCredentials_expire_at(Date credentials_expire_at) {
        this.credentials_expire_at = credentials_expire_at;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setDate_naissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }

    public void setCode_postal(int code_postal) {
        this.code_postal = code_postal;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public void setPhoto_profil(String photo_profil) {
        this.photo_profil = photo_profil;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public void setType_partenaire(String type_partenaire) {
        this.type_partenaire = type_partenaire;
    }

    public void setSpecialite_partenaire(String specialite_partenaire) {
        this.specialite_partenaire = specialite_partenaire;
    }

    public Utilisateur(String nom, String prenom, String username, String email, String password, String roles, String adresse, LocalDate date_naissance, int code_postal, String sexe, int num_tel, String photo_profil, String pays, String type_partenaire, String specialite_partenaire) {
       // this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.username_canonical = username;
        this.email = email;
        this.email_canonical = email;
        this.password = password;
        this.adresse = adresse;
        this.date_naissance = date_naissance;
        this.code_postal = code_postal;
        this.sexe = sexe;
        this.num_tel = num_tel;
        this.photo_profil = photo_profil;
        this.pays = pays;
        this.type_partenaire = type_partenaire;
        this.specialite_partenaire = specialite_partenaire;
        this.roles = roles;
        this.enabled=enabled;
        this.salt = null;
        this.last_login = null;
        this.locked = 0;
        this.expired = 0;
        this.expires_at = null;
        this.confirmation_token = null;
        this.password_requested_at = null;
        this.credentials_expired = 0;
        this.credentials_expire_at = null;
    }
    
    
public Utilisateur(String nom, String prenom, String username, String email, String password, String roles, String adresse, LocalDate date_naissance, int code_postal, String sexe, int num_tel, String pays, String type_partenaire, String specialite_partenaire) {
       // this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.username_canonical = username;
        this.email = email;
        this.email_canonical = email;
        this.password = password;
        this.adresse = adresse;
        this.date_naissance = date_naissance;
        this.code_postal = code_postal;
        this.sexe = sexe;
        this.num_tel = num_tel;
        this.photo_profil = photo_profil;
        this.pays = pays;
        this.type_partenaire = type_partenaire;
        this.specialite_partenaire = specialite_partenaire;
        this.roles = roles;
        this.enabled = enabled;
        this.salt = null;
        this.last_login = null;
        this.locked = 0;
        this.expired = 0;
        this.expires_at = null;
        this.confirmation_token = null;
        this.password_requested_at = null;
        this.credentials_expired = 0;
        this.credentials_expire_at = null;
    }
    

    public Utilisateur() {
    }

    public Utilisateur(int id, String nom, String prenom, String username, String username_canonical, String email, String email_canonical, int enabled, String salt, String password, Date last_login, int locked, int expired, Date expires_at, String confirmation_token, Date password_requested_at, String roles, int credentials_expired, Date credentials_expire_at, String adresse, LocalDate date_naissance, int code_postal, String sexe, int num_tel, String photo_profil, String pays, String type_partenaire, String specialite_partenaire) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.last_login = last_login;
        this.locked = locked;
        this.expired = expired;
        this.expires_at = expires_at;
        this.confirmation_token = confirmation_token;
        this.password_requested_at = password_requested_at;
        this.roles = roles;
        this.credentials_expired = credentials_expired;
        this.credentials_expire_at = credentials_expire_at;
        this.adresse = adresse;
        this.date_naissance = date_naissance;
        this.code_postal = code_postal;
        this.sexe = sexe;
        this.num_tel = num_tel;
        this.photo_profil = photo_profil;
        this.pays = pays;
        this.type_partenaire = type_partenaire;
        this.specialite_partenaire = specialite_partenaire;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", username=" + username + ", email=" + email + ", password=" + password + ", adresse=" + adresse + ", date_naissance=" + date_naissance + ", code_postal=" + code_postal + ", sexe=" + sexe + ", roles=" + roles + ", num_tel=" + num_tel + ", pays=" + pays + ", type_partenaire=" + type_partenaire + ", specialite_partenaire=" + specialite_partenaire + '}';
    }
   
   
   
}
