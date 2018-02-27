/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Evenement;
import Services.EvenementService;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Maha
 */
public class ListerEventsAdminController implements Initializable {

    @FXML
    private AnchorPane anchorpane_main;
    @FXML
    private Label lb_window_title;
    @FXML
    private HBox layout_menu_hbox;
    @FXML
    private FlowPane flowpane_avatar;
    @FXML
    private ImageView profil_image;
    @FXML
    private HBox hbox_search_box;
    @FXML
    private ImageView imgview_search;
    @FXML
    private TextField txf_search_box;
    @FXML
    private Button btn_settings;
    @FXML
    private VBox vbox_right_menu;
    @FXML
    private AnchorPane listeU;
    @FXML
    private TableView<Evenement> tableEvent;
    @FXML
    private JFXListView<String> listCat;
    @FXML
    private TableColumn<Evenement, LocalDate> date_ouverture;
    @FXML
    private TableColumn<Evenement, LocalDate> date_fermeture;
    @FXML
    private TableColumn<Evenement, String> horaire1;
    @FXML
    private TableColumn<Evenement, String> horaire2;
    @FXML
    private TableColumn<Evenement, String> description;
    @FXML
    private TableColumn<Evenement, String> image;
    @FXML
    private TableColumn<Evenement, String> categorie;
    
    EvenementService es= new EvenementService();
    private ObservableList<Evenement> data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 List<Evenement> LE = es.afficherEvenements();
        data = FXCollections.observableArrayList();
        Evenement event;
        
        
        

        LE.stream().forEach((j) -> {
            data.add(j);
        });
        System.out.println(data.size());
        tableEvent.setItems(data);
        date_ouverture.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        date_fermeture.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        horaire1.setCellValueFactory(new PropertyValueFactory<>("horaire_com"));
        horaire2.setCellValueFactory(new PropertyValueFactory<>("horaire_fin"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        //categorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
  
            }    

    @FXML
    private void lister(ActionEvent event) {
    }

    @FXML
    private void stat(ActionEvent event) {
    }

    @FXML
    private void demande(ActionEvent event) {
    }
    
}
