/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DataStorage.MyDB;
import Entities.CabinetMedical;
import Entities.Demande;
import Entities.Herboriseterie;
import Entities.Hopitaux;
import Entities.Laboratoire;
import Entities.Parapharmacie;
import Entities.Pharmacie;
import Entities.SalledeSport;
import Entities.Utilisateur;
import Services.CabinetMedicalService;
import Services.HerbosristerieService;
import Services.HopitauxService;
import Services.LaboratoireService;
import Services.ParapharmacieService;
import Services.PharmacieService;
import Services.SalledeSportService;
import Utils.GetConnectedUser;
import Utils.PostFile;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import esbe.User;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ellouze Skander
 */
public class AjouterEtablissementController implements Initializable {

     String f;
     



    File fi ;
    



    /**
     * Initializes the controller class.
     */
    Connection connexion;
    Utilisateur u=GetConnectedUser.GetConnectedUser();
    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXTextField txtAdresse;
    @FXML
    private JFXTextField txtDO;
    @FXML
    private JFXTextField txtDF;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtNum;
    @FXML
    private JFXTextField txtFax;
    @FXML
    private JFXTextField txtFB;
    @FXML
    private JFXTextField txtSW;
    @FXML
    private JFXTextField txtHO;
    @FXML
    private JFXTextField txtHF;
    @FXML
    private JFXButton btnBackUp1;
    @FXML
    private JFXComboBox<String> combo;
    @FXML
    private JFXComboBox<String> combobox4;
    @FXML
    private JFXTextField txt4;
    @FXML
    private JFXCheckBox checkbox2;
    @FXML
    private JFXComboBox<String> combobox2;
    @FXML
    private JFXComboBox<String> combobox3;
    @FXML
    private JFXCheckBox checkbox3;
    @FXML
    private JFXButton btnValider;
    @FXML
    private JFXCheckBox checkbox1;
    @FXML
    private JFXComboBox<String> combobox1;
    @FXML
    private JFXTextField txt2;
    public AjouterEtablissementController() {
        connexion = MyDB.getinstance().getConnexion();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> options = FXCollections.observableArrayList(
                "Cabinet Medical", "Centre de Beaute", "Herboriseterie", "Hopitale", "Laboratoire", "Parapharmacie", "Pharmacie", "Salle de sport");
        combo.setItems(options);

        ObservableList<String> options2 = FXCollections.observableArrayList(
                "publique", "prive");
        combobox1.setItems(options2);

        ObservableList<String> options3 = FXCollections.observableArrayList(
                "radio", "biologie_medical", "sang");
        combobox3.setItems(options3);
        
        ObservableList<String> options4 = FXCollections.observableArrayList(
                "nuit", "jour", "garde");
        combobox4.setItems(options4);

        checkbox1.setVisible(false);
        checkbox2.setVisible(false);
        checkbox3.setVisible(false);
        combobox1.setVisible(false);
        combobox2.setVisible(false);
        combobox3.setVisible(false);
        combobox4.setVisible(false);
        txt2.setVisible(false);
        txt4.setVisible(false);

    }

    
    @FXML
    public void typeEtab(ActionEvent event) {
        checkbox1.setText("");
        checkbox3.setText("");
        checkbox2.setText("");
        checkbox1.setVisible(false);
        checkbox2.setVisible(false);
        checkbox3.setVisible(false);
        txt4.setVisible(false);
        combobox1.setVisible(false);
        combobox2.setVisible(false);
        combobox3.setVisible(false);
        combobox4.setVisible(false);
        txt2.setVisible(false);
        if (combo.getSelectionModel().getSelectedItem() == "Cabinet Medical") {
            checkbox1.setText("");
        checkbox3.setText("");
        checkbox2.setText("");
            combobox4.setVisible(false);
            checkbox1.setVisible(false);
            checkbox2.setVisible(false);
            checkbox3.setVisible(false);
            txt4.setVisible(false);
            combobox1.setVisible(false);
            combobox2.setVisible(false);
            combobox3.setVisible(false);
            checkbox1.setText("CNAM");
            checkbox1.setVisible(true);
        }
        if (combo.getSelectionModel().getSelectedItem() == "Centre de Beaute") {
        }
        if (combo.getSelectionModel().getSelectedItem() == "Herboriseterie") {
            checkbox1.setText("");
        checkbox3.setText("");
        checkbox2.setText("");
            checkbox1.setVisible(false);
            checkbox2.setVisible(false);
            checkbox3.setVisible(false);
            combobox4.setVisible(false);
            combobox1.setVisible(false);
            combobox2.setVisible(false);
            combobox3.setVisible(false);
            checkbox1.setText("livraison");
            
            checkbox1.setVisible(true);
            txt4.setVisible(false);
        }
        if (combo.getSelectionModel().getSelectedItem() == "Hopitale") {
            checkbox1.setText("");
        checkbox3.setText("");
        checkbox2.setText("");
            txt4.setVisible(false);
            checkbox1.setVisible(false);
            checkbox2.setVisible(false);
            checkbox3.setVisible(false);
            combobox4.setVisible(false);
            combobox1.setVisible(false);
            combobox2.setVisible(false);
            combobox3.setVisible(false);
            //label1.setText("type");
            combobox1.setVisible(true);
            checkbox2.setText("urgence");
            //checkbox2.setText("");
            checkbox2.setVisible(true);
            checkbox3.setText("CNAM");
            //checkbox3.setText("");
            checkbox3.setVisible(true);

        }
        if (combo.getSelectionModel().getSelectedItem() == "Laboratoire") {
            checkbox1.setText("");
        checkbox3.setText("");
        checkbox2.setText("");
            checkbox1.setVisible(false);
            checkbox2.setVisible(false);
            checkbox3.setVisible(false);
            combobox4.setVisible(false);
            txt4.setVisible(false);
            combobox1.setVisible(false);
            combobox2.setVisible(false);
            combobox3.setVisible(false);
            checkbox1.setText("CNAM");
            checkbox1.setVisible(true);
            txt2.setPromptText("Equipe composé de");
            txt2.setVisible(true);
            //label3.setText("type");
            combobox3.setVisible(true);

        }
        if (combo.getSelectionModel().getSelectedItem() == "Parapharmacie") {
            checkbox1.setText("");
        checkbox3.setText("");
        checkbox2.setText("");
            checkbox1.setVisible(false);
            checkbox2.setVisible(false);
            checkbox3.setVisible(false);
            combobox4.setVisible(false);
            combobox1.setVisible(false);
            combobox2.setVisible(false);
            combobox3.setVisible(false);
            checkbox1.setText("livraison");
           // checkbox1.setText("");
            checkbox1.setVisible(true);
        }
        if (combo.getSelectionModel().getSelectedItem() == "Pharmacie") {
            checkbox1.setText("");
        checkbox3.setText("");
        checkbox2.setText("");
            txt4.setVisible(false);
            checkbox1.setVisible(false);
            checkbox2.setVisible(false);
            checkbox3.setVisible(false);
            combobox4.setVisible(true);
            combobox1.setVisible(false);
            combobox2.setVisible(false);
            combobox3.setVisible(false);
            //label1.setText("Type");
            checkbox1.setText("");
           // checkbox1.setVisible(true);
        }
        if (combo.getSelectionModel().getSelectedItem() == "Salle de sport") {
        checkbox1.setText("");
        checkbox3.setText("");
        checkbox2.setText("");
        checkbox1.setVisible(false);
        checkbox2.setVisible(false);
        checkbox3.setVisible(false);
        txt4.setVisible(true);
        combobox1.setVisible(false);
        combobox2.setVisible(false);
        combobox3.setVisible(false);
        combobox4.setVisible(false);
        txt2.setVisible(false);
        txt4.setPromptText("nombre d'entraineur");
        
        }
    }

    @FXML
    void CreerEtab(ActionEvent event) throws IOException {
        if (combo.getSelectionModel().getSelectedItem() == "Cabinet Medical") {
            CabinetMedicalService cms = new CabinetMedicalService();

            CabinetMedical c = new CabinetMedical();

            c.setNom(txtNom.getText());
            c.setAdresse(txtAdresse.getText());
            c.setDate_ouverture(txtDO.getText());
            c.setDate_fermeture(txtDF.getText());
            c.setEmail(txtEmail.getText());
            c.setNum(Integer.parseInt(txtNum.getText()));
            c.setFax(Integer.parseInt(txtFax.getText()));
            c.setPage_fb(txtFB.getText());
            c.setSite_web(txtSW.getText());
            c.setHeure_ouverture(Integer.parseInt(txtHO.getText()));
            c.setHeure_fermeture(Integer.parseInt(txtHF.getText()));
            c.setImage(f);
            c.setUser(u);
            
            
            
            if (checkbox1.isSelected()) {
                c.setCnam(1);
            } else {
                c.setCnam(0);
            }
            
             if(fi!= null ){
                try {
                    c.setImage(PostFile.upload(fi.getAbsolutePath()));
                } catch (Exception ex) {
                   // Logger.getLogger(Etablissement3Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
            
            cms.ajouterCabinet(c);
        }
        if (combo.getSelectionModel().getSelectedItem() == "Centre de Beaute") {
        }
        if (combo.getSelectionModel().getSelectedItem() == "Herboriseterie") {
            HerbosristerieService hs = new HerbosristerieService();
 
            Herboriseterie e = new Herboriseterie();

            e.setNom(txtNom.getText());
            e.setAdresse(txtAdresse.getText());
            e.setDate_ouverture(txtDO.getText());
            e.setDate_fermeture(txtDF.getText());
            e.setEmail(txtEmail.getText());
            e.setNum(Integer.parseInt(txtNum.getText()));
            e.setFax(Integer.parseInt(txtFax.getText()));
            e.setPage_fb(txtFB.getText());
            e.setSite_web(txtSW.getText());
            e.setHeure_ouverture(Integer.parseInt(txtHO.getText()));
            e.setHeure_fermeture(Integer.parseInt(txtHF.getText()));
            e.setImage(f);
            e.setUser(u);
            
            if (checkbox1.isSelected()) {
                e.setLivraison(1);
            } else {
                e.setLivraison(0);
            }
             if(fi!= null ){
                try {
                    e.setImage(PostFile.upload(fi.getAbsolutePath()));
                } catch (Exception ex) {
                   // Logger.getLogger(Etablissement3Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
            hs.ajouterHerbosristerie(e);
        }
        if (combo.getSelectionModel().getSelectedItem() == "Hopitale") {
            HopitauxService hs = new HopitauxService();
            
            Hopitaux e = new Hopitaux();
            e.setNom(txtNom.getText());
            e.setAdresse(txtAdresse.getText());
            e.setDate_ouverture(txtDO.getText());
            e.setDate_fermeture(txtDF.getText());
            e.setEmail(txtEmail.getText());
            e.setNum(Integer.parseInt(txtNum.getText()));
            e.setFax(Integer.parseInt(txtFax.getText()));
            e.setPage_fb(txtFB.getText());
            e.setSite_web(txtSW.getText());
            e.setHeure_ouverture(Integer.parseInt(txtHO.getText()));
            e.setHeure_fermeture(Integer.parseInt(txtHF.getText()));
            e.setImage(f);
            e.setUser(u);
            
            e.setType(combobox1.getSelectionModel().getSelectedItem().toString());
            if (checkbox2.isSelected()) {
                e.setUrgence(1);
            } else {
                e.setUrgence(0);
            }
            if (checkbox3.isSelected()) {
                e.setCnam(1);
            } else {
                e.setCnam(0);
            }
            if(fi!= null ){
                try {
                    e.setImage(PostFile.upload(fi.getAbsolutePath()));
                } catch (Exception ex) {
                   // Logger.getLogger(Etablissement3Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
            hs.ajouterHopitaux(e);
        }
        if (combo.getSelectionModel().getSelectedItem() == "Laboratoire") {
            LaboratoireService hs = new LaboratoireService();
            Laboratoire e = new Laboratoire();
            e.setNom(txtNom.getText());
            e.setAdresse(txtAdresse.getText());
            e.setDate_ouverture(txtDO.getText());
            e.setDate_fermeture(txtDF.getText());
            e.setEmail(txtEmail.getText());
            e.setNum(Integer.parseInt(txtNum.getText()));
            e.setFax(Integer.parseInt(txtFax.getText()));
            e.setPage_fb(txtFB.getText());
            e.setSite_web(txtSW.getText());
            e.setHeure_ouverture(Integer.parseInt(txtHO.getText()));
            e.setHeure_fermeture(Integer.parseInt(txtHF.getText()));
            e.setImage(f);
            e.setUser(u);
            
            if (checkbox1.isSelected()) {
                e.setCnam(1);
            } else {
                e.setCnam(0);
            }
            e.setNb_equipe(Integer.parseInt(txt2.getText()));
            e.setType(combobox3.getSelectionModel().getSelectedItem());
            if(fi!= null ){
                try {
                    e.setImage(PostFile.upload(fi.getAbsolutePath()));
                } catch (Exception ex) {
                  //  Logger.getLogger(Etablissement3Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
            hs.ajouterLaboratoire(e);
        }

        if (combo.getSelectionModel().getSelectedItem() == "Parapharmacie") {
            ParapharmacieService hs = new ParapharmacieService();
            Parapharmacie e = new Parapharmacie();
            e.setNom(txtNom.getText());
            e.setAdresse(txtAdresse.getText());
            e.setDate_ouverture(txtDO.getText());
            e.setDate_fermeture(txtDF.getText());
            e.setEmail(txtEmail.getText());
            e.setNum(Integer.parseInt(txtNum.getText()));
            e.setFax(Integer.parseInt(txtFax.getText()));
            e.setPage_fb(txtFB.getText());
            e.setSite_web(txtSW.getText());
            e.setHeure_ouverture(Integer.parseInt(txtHO.getText()));
            e.setHeure_fermeture(Integer.parseInt(txtHF.getText()));
            e.setImage(f);
            e.setUser(u);
            
            if (checkbox1.isSelected()) {
                e.setLivraison(1);
            } else {
                e.setLivraison(0);
            }
            if(fi!= null ){
                try {
                    e.setImage(PostFile.upload(fi.getAbsolutePath()));
                } catch (Exception ex) {
                    //Logger.getLogger(Etablissement3Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
            hs.ajouterParapharmacie(e);
        } 
        if (combo.getSelectionModel().getSelectedItem() == "Pharmacie") {
            PharmacieService hs = new PharmacieService();
            Pharmacie e = new Pharmacie();
            e.setNom(txtNom.getText());
            e.setAdresse(txtAdresse.getText());
            e.setDate_ouverture(txtDO.getText());
            e.setDate_fermeture(txtDF.getText());
            e.setEmail(txtEmail.getText());
            e.setNum(Integer.parseInt(txtNum.getText()));
            e.setFax(Integer.parseInt(txtFax.getText()));
            e.setPage_fb(txtFB.getText());
            e.setSite_web(txtSW.getText());
            e.setHeure_ouverture(Integer.parseInt(txtHO.getText()));
            e.setHeure_fermeture(Integer.parseInt(txtHF.getText()));
            e.setImage(f);
            e.setUser(u);
            
            
                e.setType(combobox4.getSelectionModel().getSelectedItem().toString());
            
                if(fi!= null ){
                try {
                    e.setImage(PostFile.upload(fi.getAbsolutePath()));
                } catch (Exception ex) {
                   // Logger.getLogger(Etablissement3Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
            hs.ajouterPharmacie(e);
        }
        if (combo.getSelectionModel().getSelectedItem() == "Salle de sport") {
            SalledeSportService hs = new SalledeSportService();
            SalledeSport e = new SalledeSport();
            e.setNom(txtNom.getText());
            e.setAdresse(txtAdresse.getText());
            e.setDate_ouverture(txtDO.getText());
            e.setDate_fermeture(txtDF.getText());
            e.setEmail(txtEmail.getText());
            e.setNum(Integer.parseInt(txtNum.getText()));
            e.setFax(Integer.parseInt(txtFax.getText()));
            e.setPage_fb(txtFB.getText());
            e.setSite_web(txtSW.getText());
            e.setHeure_ouverture(Integer.parseInt(txtHO.getText()));
            e.setHeure_fermeture(Integer.parseInt(txtHF.getText()));
            e.setImage(f);
            e.setUser(u);
            
            e.setNb_entraineur(Integer.parseInt(txt4.getText()));
            
            if(fi!= null ){
                try {
                    e.setImage(PostFile.upload(fi.getAbsolutePath()));
                } catch (Exception ex) {
                   // Logger.getLogger(Etablissement3Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
            hs.ajouterSalle(e);
        }

//        Stage stage = (Stage) btnValider.getScene().getWindow();
//        stage.close();
         Parent root = FXMLLoader.load(getClass().getResource("/Presentation/Profile.fxml"));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Presentation/DemandePartenariat.fxml"));
        AnchorPane name = (AnchorPane) loader.load();          
        ProfileController profilController = new ProfileController();
        profilController.getMainMain().getChildren().add(name);
        profilController.getMainMain().autosize();
        User u1 = new User();
        Scene scene = new Scene(root);
        u1.getStageUser().setScene(scene);
        u1.getStageUser().show();
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        
        
        
        
    }

    public void modifEtab(ActionEvent event)
    {
        
    }
    
    @FXML
    public void uploadpic() throws MalformedURLException{
         FileChooser.ExtensionFilter imageFilter
        = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(imageFilter);
        File selectedFile = fc.showOpenDialog(null);
        if(selectedFile != null){
            
            String imageFile = selectedFile.toURI().toURL().toString();
            this.fi =selectedFile;
            System.out.println(imageFile);
            System.out.println(selectedFile.getAbsolutePath());
           // PostFile.upload(selectedFile.getAbsolutePath());
            
//            Image image = new Image(imageFile);
//            pic.setImage(image); 
    }}
    
  
    
    
    
    
    
    
}
