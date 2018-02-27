/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Conseil;
import Services.ConseilService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Maha
 */
public class ListConseilAdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    
    ConseilService cs = new ConseilService();
    private ObservableList<Conseil> data;
    Conseil conseil = new Conseil();
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
    private TableView<Conseil> TableConseil;
    @FXML
    private TableColumn<Conseil, Integer> id_conseil;
    @FXML
    private TableColumn<Conseil, String> description;
    @FXML
    private TableColumn<Conseil, String> categorie;
    @FXML
    private TableColumn<Conseil, String> proprietaire;
    @FXML
    private TableColumn<Conseil, String> image;
    @FXML
    private VBox vbox_user_account;
    @FXML
    private Button btn_change_password;
    @FXML
    private Button btn_logout;
    
    
    
    
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
    
}

    @FXML
    private void stat(ActionEvent event) throws IOException
    {
        Stage Stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/stat.fxml"));
        Scene scene = new Scene(root);
        Stage.setScene(scene);
        Stage.show();
    }
    
        @FXML
    private void lister(ActionEvent event) throws IOException
    {
        Stage Stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ListeUtilisateurs.fxml"));
        Scene scene = new Scene(root);
        Stage.setScene(scene);
        Stage.show();
    }
    
            @FXML
    private void demande(ActionEvent event) throws IOException
    {
        Stage Stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ListerDemandesAdmin.fxml"));
        Scene scene = new Scene(root);
        Stage.setScene(scene);
        Stage.show();
    }
    
    
        
            @FXML
    private void event(ActionEvent event) throws IOException
    {
        Stage Stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ListerEventsAdmin.fxml"));
        Scene scene = new Scene(root);
        Stage.setScene(scene);
        Stage.show();
    }
}
