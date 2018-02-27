/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DataStorage.MyDB;
import Entities.Categorie;
import Entities.Conseil;
import Services.ConseilService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//import java.awt.event.MouseEvent;
//import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Application;
//import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
//import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Maha
 */
public class Conseil2Controller extends Application implements Initializable {

    @FXML
    private TableColumn<Conseil, String> categorie;

    @FXML
    private Button RechercherNomUti;

    @FXML
    private TableColumn<Conseil, String> description;

    @FXML
    private ComboBox<String> NomUtilisateur;

    @FXML
    private TableColumn<Conseil, Integer> id_conseil;

    @FXML
    private TableColumn<Conseil, String> proprietaire;

    @FXML
    private Button RechercherNom;

    @FXML
    private Button AjouterButton;

    @FXML
    private Button ModifierButton;

    @FXML
    private TableView<Conseil> TableConseil;

    @FXML
    private ComboBox<String> NomCategorie;

    ConseilService cs = new ConseilService();
    private ObservableList<Conseil> data;
    Conseil conseil = new Conseil();
    //Conseil2Controller c = new Conseil2Controller();

    /**
     * Initializes the controller class.
     */
    /**
     * Initializes the controller class.
     *
     * @param <error>
     */
    Connection conn;
    @FXML
    private AnchorPane event;
    @FXML
    private AnchorPane ajout;
    @FXML
    private AnchorPane modif;
    @FXML
    private AnchorPane supprimer;
    @FXML
    private JFXButton supprimerConseil;
    @FXML
    private Button raffraichir;
    @FXML
    private JFXComboBox<String> categorie_modif;
    @FXML
    private TextArea description_modif;
    @FXML
    private Label cat;
    @FXML
    private Label desc;
    @FXML
    private JFXButton valider;
    @FXML
    private AnchorPane hedha;
    @FXML
    private Color x21;
    @FXML
    private Font x11;
    @FXML
    private Color x4;
    @FXML
    private Font x3;
    @FXML
    private TableColumn<Conseil, String> image;

    public Conseil2Controller() {
        this.conn = MyDB.getinstance().getConnexion();
    }

    @FXML
    public void Ajout(ActionEvent event) throws IOException {
        Stage Stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/AjouterConseil.fxml"));
        Scene scene = new Scene(root);
        Stage.setScene(scene);
        Stage.show();
    }

    /* private void setCellValueFromTableToText() 
    {
        
        System.out.println("messageee");
        TableConseil.setOnMouseClicked(new EventHandler<MouseEvent>() 
        {
            @Override
                public void handle(MouseEvent event)
                {
                Conseil c = (Conseil) TableConseil.getItems().get(TableConseil.getSelectionModel().getSelectedIndex());
                System.out.println(c);
                selectedAnn = TableConseil.getSelectionModel().getSelectedIndex();
                System.out.println(selectedAnn);
               
                Adressedepart_txt.setText(c.getId_conseil());
                Adressearrivee_txt.setText(c.getDescription());
                Tarif_txt.setText(Float.toString(c.getId_categorie()));
                txidann.setText(Integer.toString(c.getId_user()));               
                }
        }
        );
    }
     */

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Conseil> LC = cs.afficherConseils();
        data = FXCollections.observableArrayList();
        Conseil c;

        LC.stream().forEach((j) -> {
            data.add(j);
        });
        TableConseil.setItems(data);
        id_conseil.setCellValueFactory(new PropertyValueFactory<>("id_conseil"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
        proprietaire.setCellValueFactory(new PropertyValueFactory<>("nom_user"));

        cat.setVisible(false);
        desc.setVisible(false);
        categorie_modif.setVisible(false);
        description_modif.setVisible(false);
        valider.setVisible(false);

        //List<Categorie> lc= cs.listerNomsCategorie();
        List<String> listCategorie = new ArrayList<>();
        listCategorie = cs.listerNomsCategorie();
        NomCategorie.getItems().addAll(listCategorie);

        List<String> listPartenaire = new ArrayList<>();
        listPartenaire = cs.listerNomsPartanaire();
        NomUtilisateur.getItems().addAll(listPartenaire);
        
        setCellValueFromTableToText();
        
        List<String> listCat = new ArrayList<>();
        listCat = cs.listerNomsCategorie();
        categorie_modif.getItems().addAll(listCat);

    }

    @FXML
    public void afficher() {
        List<Conseil> LC = cs.afficherConseils();
        data = FXCollections.observableArrayList();
        Conseil c;

        LC.stream().forEach((j) -> {
            data.add(j);
        });
        TableConseil.setItems(data);
        id_conseil.setCellValueFactory(new PropertyValueFactory<>("id_conseil"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
        proprietaire.setCellValueFactory(new PropertyValueFactory<>("nom_user"));
    }

    @FXML
    public void supprimerConseil() {
        Conseil c1 = TableConseil.getSelectionModel().getSelectedItem();
        ConseilService cs = new ConseilService();
        cs.supprimerConseil(c1);
        //dialog(Alert.AlertType.INFORMATION, "Supprimé!");
        /*Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("Effaçé!");
        alert.showAndWait();*/
        afficher();

    }

    private void setCellValueFromTableToText() {
        TableConseil.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) 
            {
                List<String> listCategorie = new ArrayList<>();
                listCategorie = cs.listerNomsCategorie();
                NomCategorie.getItems().addAll(listCategorie);
                
                cat.setVisible(true);
                desc.setVisible(true);
                categorie_modif.setVisible(true);
                description_modif.setVisible(true);
                valider.setVisible(true);


                conseil = TableConseil.getItems().get(TableConseil.getSelectionModel().getSelectedIndex());
                categorie_modif.setValue(conseil.getNom_categorie());
                description_modif.setText(conseil.getDescription());
            }
        });

    }

    @FXML
    public void modifierConseil(ActionEvent event) 
    {   
       
        conseil.getCategorie().setId_categorie((cs.getCategorie(categorie_modif.getSelectionModel().getSelectedItem())).getId_categorie());
        conseil.setDescription(description_modif.getText());
        cs.modifierConseil(conseil);
        cat.setVisible(false);
        desc.setVisible(false);
        categorie_modif.setVisible(false);
        description_modif.setVisible(false);
        valider.setVisible(false);
        
        TableConseil.setItems(data);
        afficher();
        
    }

//    public void chercherConseilParCategorie()
//    {
//       // Conseil c2= TableConseil.getSelectionModel().getSelectedItem();
//      String s=  NomCategorie.getSelectionModel().getSelectedItem();
//        System.out.println(s);
//        ConseilService cs = new ConseilService();
//      
//        data=cs.chercherConseilParCategorie(s);
//        
//        
//        id_conseil.setCellValueFactory(new PropertyValueFactory<>("id_conseil"));
//        description.setCellValueFactory(new PropertyValueFactory<>("description"));
//        categorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
//        proprietaire.setCellValueFactory(new PropertyValueFactory<>("nom_user"));
//        
//         TableConseil.setItems(data);
//    }
//    
//    
    @FXML
    public void chercherConseilParPartenaire() {
        //Conseil c2= TableConseil.getSelectionModel().getSelectedItem();
        ConseilService cs = new ConseilService();
        String s = NomUtilisateur.getSelectionModel().getSelectedItem();
        data = cs.chercherConseilParPartenaire(s);

        id_conseil.setCellValueFactory(new PropertyValueFactory<>("id_conseil"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        categorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
        proprietaire.setCellValueFactory(new PropertyValueFactory<>("nom_user"));
        TableConseil.setItems(data);
        

    }

    /*
        private void dialog(Alert.AlertType alertType,String s)
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Confirmation");
        alert.setContentText("Vous êtes sûr de vouloir supprimer ce conseil ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
             supprimerConseil();
             afficher();
        }
        else 
        {
             afficher();  
        }
       
    }

     */
    @FXML
    private void chercherConseil(ActionEvent event) {
        String s = NomCategorie.getSelectionModel().getSelectedItem();
        System.out.println(s);
        ConseilService cs = new ConseilService();

        data = cs.chercherConseilParCategorie(s);
        id_conseil.setCellValueFactory(new PropertyValueFactory<>("id_conseil"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
        proprietaire.setCellValueFactory(new PropertyValueFactory<>("nom_user"));

        TableConseil.setItems(data);
    }

}
