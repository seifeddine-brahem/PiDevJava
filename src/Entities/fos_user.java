/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;


import java.sql.Date;

/**
 *
 * @author Maha
 */

public class fos_user 
{
    

   private int id;
   private String username;
   private String username_canonical;
   private String email;
   private String email_canonical;
   private int enabled;
   private String salt;
   private String password;
   private Date last_login;
   private int locked;
   private int expired;
   private Date expires_at;
   private String confirmation_token;
   private Date password_requested_at;
   private String roles = "user";

   //this.roles = "user" ;

   private int credentials_expired;
   private Date credentials_expire_at;
   private String adresse;
   private String date_naissance;
   private int code_postal;
   private String sexe;
   private int num_tel;
   private String photo_profil;
   private String pays;


    public fos_user()
    {
        
    }

    public fos_user(String username, String email,String pass, String adresse, String date_naissance, int code_postal, String sexe, int num_tel, String photo_profil, String pays)
            {
        this.username = username;
        this.email = email;
        this.adresse = adresse;
        this.date_naissance = date_naissance;
        this.code_postal = code_postal;
        this.sexe = sexe;
        this.num_tel = num_tel;
        this.photo_profil = photo_profil;
        this.pays = pays;
        this.username = username;
        this.username_canonical = username;
        this.email = email;
        this.email_canonical = email;
        this.enabled = 1;
        this.salt = "";
        this.password = pass;
        this.last_login = null;
        this.locked = 1;
        this.expired = 0;
        this.expires_at = null;
        this.confirmation_token = null;
        this.password_requested_at = null;
        
        this.credentials_expired  =0;
        this.credentials_expire_at = null;

    }
    
    public int getId() {
        return id;
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

    public String getDate_naissance() {
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

    public void setId(int id) {
        this.id = id;
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

    public void setDate_de_naissance(String date_naissance) {
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

    public void setPdp(String photo_profil) {
        this.photo_profil = photo_profil;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public String toString() {
        return "fos_user{" + "id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", roles=" + roles + ", adresse=" + adresse + ", date_naissance=" + date_naissance + ", code_postal=" + code_postal + ", sexe=" + sexe + ", num_tel=" + num_tel + ", photo_profil=" + photo_profil + ", pays=" + pays + '}';
    }
    
    public String toString1()
    {
        return ("Username:"+username);
    }
    
}


