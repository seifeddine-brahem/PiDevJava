/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DataStorage.MyDB;
import Entities.Demande;
import Presentation.ListViewDemandeCell;
import Services.DemandeService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Maha
 */
public class ListerDemandesAdminController implements Initializable {

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
    private ListView<Demande> listDemandes;
    @FXML
    private Label lb_title;
    @FXML
    private VBox vbox_user_account;
    @FXML
    private Button btn_change_password;
    @FXML
    private Button btn_logout;

    /**
     * Initializes the controller class.
     */
    
    
    DemandeService es= new DemandeService();
    private ObservableList<Demande> data;
    
    Connection conn;
    
     public ListerDemandesAdminController()
    {
        this.conn=MyDB.getinstance().getConnexion();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        List<Demande> LE = es.afficherDemandes();
        data = FXCollections.observableArrayList();
        Demande demande;
        LE.stream().forEach((j) -> {
            data.add(j);
        });
        System.out.println(data.size());
        listDemandes.setItems(data);
        listDemandes.setCellFactory(new Callback<ListView<Demande>, ListCell<Demande>>() {
            @Override
                public ListCell<Demande> call(ListView<Demande> param) {
                return new ListViewDemandeCell();
            }
        } );
        
        listDemandes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
            try {
                showEventDetails(newValue);
            } catch (IOException ex) {
                //Logger.getLogger(EventUSerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }    
    
    
    
    
    
    public void showEventDetails(Demande demande) throws IOException{
        
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(getClass().getResource("/Presentation/EtatDemande.fxml"));
            // Create the dialog Stage.
            Stage dialogStage = new Stage();            
            AnchorPane page = (AnchorPane) loader2.load();
          //  dialogStage.setTitle("Edit Service");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the person into the controller.
            EtatDemandeController controller = loader2.getController();
            //controller.setDialogStage(dialogStage);
            controller.setDemande(demande);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
    }  


    
    
}
