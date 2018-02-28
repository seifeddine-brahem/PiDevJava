/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DataStorage.MyDB;
import Entities.Categorie;
import Entities.Utilisateur;
import Services.CategorieService;
import Utils.GetConnectedUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Maha
 */
public class CategorieePartController implements Initializable {

    @FXML
    private TableView<Categorie> TableCat;

    @FXML
    private TableColumn<Categorie, String> NomCat;
    @FXML
    private TableColumn<Categorie, String> Prop;
    @FXML
    private TableColumn<Categorie, String> Type;
    @FXML
    private JFXComboBox<String> NomUtilisateur;
    
    CategorieService cs = new CategorieService();
    private ObservableList<Categorie> data;
    Categorie conseil = new Categorie();
    Connection conn;
   
    @FXML
    private Label nomLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private JFXTextField nomCat;
    @FXML
    private JFXComboBox<String> typeCat;
    @FXML
    private Button valider;

    Categorie categorie = new Categorie();
    @FXML
    private TableColumn<?, ?> id_conseil;
    @FXML
    private JFXButton btnSupp;

    /**
     * Initializes the controller class.
     */
    
    
     public CategorieePartController() {
        this.conn = MyDB.getinstance().getConnexion();
    }

    @FXML
    public void Ajout(ActionEvent event) throws IOException {
        Stage Stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/AjoutCategoriee.fxml"));
        Scene scene = new Scene(root);
        Stage.setScene(scene);
        Stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
         Utilisateur u = GetConnectedUser.GetConnectedUser();        
         List<Categorie> LC = cs.afficherCategorie(u.getId());
        data = FXCollections.observableArrayList();
        Categorie c;

        LC.stream().forEach((j) -> {
            data.add(j);
        });
        TableCat.setItems(data);
        NomCat.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Prop.setCellValueFactory(new PropertyValueFactory<>("nom_user"));
        Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        
        nomLabel.setVisible(false);
        typeLabel.setVisible(false);
        valider.setVisible(false);
        typeCat.setVisible(false);
        nomCat.setVisible(false);
        
        setCellValueFromTableToText();
        
        
        List<String> listPartenaire = new ArrayList<>();
        listPartenaire = cs.listerNomsPartanaire();
        NomUtilisateur.getItems().addAll(listPartenaire);

        btnSupp.setVisible(false);

    }    
    
    
        private void setCellValueFromTableToText() 
      {
         TableCat.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) 
            { 
                typeCat.getItems().clear();
                String tableType[]= {"Evenement","Conseil"};
                typeCat.getItems().addAll(tableType);
        
                nomLabel.setVisible(true);
                typeLabel.setVisible(true);
                valider.setVisible(true);
                typeCat.setVisible(true);
                nomCat.setVisible(true);
                
                categorie = TableCat.getItems().get(TableCat.getSelectionModel().getSelectedIndex());
                nomCat.setText(categorie.getNom());
                typeCat.setValue(categorie.getType());
                
                btnSupp.setVisible(true);
            }
        });
    }
    
    @FXML
    public void afficher()
    {
         List<Categorie> LC = cs.afficherToutCategorie();
        data = FXCollections.observableArrayList();
        Categorie c;

        LC.stream().forEach((j) -> {
            data.add(j);
        });
        TableCat.setItems(data);
        NomCat.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Prop.setCellValueFactory(new PropertyValueFactory<>("nom_user"));
        Type.setCellValueFactory(new PropertyValueFactory<>("type"));
    }
    
        @FXML
    public void supprimerCategorie(ActionEvent event)
    {
        Categorie c1 = TableCat.getSelectionModel().getSelectedItem();
        CategorieService cs = new CategorieService();
        cs.supprimerCategorie(c1);
        afficher();
    }
    
        @FXML
   public void modifierCategorie(ActionEvent event)
    {
        categorie.setNom(nomCat.getText());
        categorie.setType(typeCat.getValue());
        cs.modifierCategorie(categorie);
        nomLabel.setVisible(false);
        typeLabel.setVisible(false);
        valider.setVisible(false);
        typeCat.setVisible(false);
        nomCat.setVisible(false);
        
        TableCat.setItems(data);
        afficher();

    }
    
    @FXML
    public void chercherCategorieParPartenaire() 
    {
        //Conseil c2= TableConseil.getSelectionModel().getSelectedItem();
        CategorieService cs = new CategorieService();
        String s = NomUtilisateur.getSelectionModel().getSelectedItem();
        data = (ObservableList<Categorie>) cs.chercherConseilParPartenaire(s);


        NomCat.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Prop.setCellValueFactory(new PropertyValueFactory<>("nom_user"));
        Type.setCellValueFactory(new PropertyValueFactory<>("type"));
                TableCat.setItems(data);

    }

    

}

