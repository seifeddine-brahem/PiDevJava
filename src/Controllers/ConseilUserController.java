/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Conseil;
import Entities.Evenement;
import Presentation.ListViewConseilCell;
import Presentation.LsitViewEventCell;
import Services.ConseilService;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Maha
 */
public class ConseilUserController implements Initializable
{

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
    private JFXListView<Conseil> ListView;
      @FXML
    private TableColumn<Conseil, String> image;
    @FXML
    private AnchorPane hedha;
    @FXML
    private Color x21;
    @FXML
    private Font x11;
    @FXML
    private JFXComboBox<String> NomCategorie;
    @FXML
    private JFXComboBox<String> NomUtilisateur;
    @FXML
    private Button RechercherNom;
    @FXML
    private Button RechercherNomUti;
    @FXML
    private Color x4;
    @FXML
    private Font x3;
    
    
    ConseilService cs = new ConseilService();
    private ObservableList<Conseil> data;
    Conseil conseil = new Conseil();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        TableConseil.setVisible(false);
         List<Conseil> LE = cs.afficherConseils();
        data = FXCollections.observableArrayList();
        Conseil conseil;
        LE.stream().forEach((j) -> {
            data.add(j);
        });
        System.out.println(data.size());
        ListView.setItems(data);
        ListView.setCellFactory(new Callback<ListView<Conseil>, ListCell<Conseil>>() {
            @Override
                public ListCell<Conseil> call(ListView<Conseil> param) {
                return new ListViewConseilCell();
            }
        } );
        
        ListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
            try 
            {
                showConseilDetails(newValue);
            } catch (IOException ex) {
                //Logger.getLogger(EventUSerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
  
        
        
        
        List<String> listCategorie = new ArrayList<>();
        listCategorie = cs.listerNomsCategorie();
        NomCategorie.getItems().addAll(listCategorie);

        List<String> listPartenaire = new ArrayList<>();
        listPartenaire = cs.listerNomsPartanaire();
        NomUtilisateur.getItems().addAll(listPartenaire);
        

    }    

    
    
    public void showConseilDetails(Conseil conseil) throws IOException
    {
        
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(getClass().getResource("../Presentation/AjouterCommentaireConseil.fxml"));
            // Create the dialog Stage.
            Stage dialogStage = new Stage();            
            AnchorPane page = (AnchorPane) loader2.load();
          //  dialogStage.setTitle("Edit Service");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the person into the controller.
            AjouterCommentaireConseilController controller = loader2.getController();
            //controller.setDialogStage(dialogStage);
            controller.setConseil(conseil);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
    }
    @FXML
    private void chercherConseil(ActionEvent event) 
    {
        TableConseil.setVisible(true);
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

    @FXML
    private void chercherConseilParPartenaire(ActionEvent event) 
    {
               TableConseil.setVisible(true);
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
    
}
