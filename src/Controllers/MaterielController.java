/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Materiel;
import Services.MaterielService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import testesbe.MaterielTest;

/**
 * FXML Controller class
 *
 * @author elbrh
 */
public class MaterielController  {
    
    
   @FXML
    private TableView<Materiel> materielTable;

    @FXML
    private TableColumn<Materiel, String> nomColumn;

    @FXML
    private TableColumn<Materiel, String> descColumn;

    @FXML
    private Label lblNom;

    @FXML
    private Label lblDesc;

    ObservableList<Materiel> data ;
    MaterielService ms = new MaterielService();
    

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        // TODO
        data = FXCollections.observableArrayList();
        List<Materiel> ls = ms.searchAllMateriel();
        ls.stream().forEach((j)->{
            data.add(j);
        });
        System.out.println(data.size());
        materielTable.setItems(data);
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        showMatrielDetails(null);
        materielTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showMatrielDetails(newValue));
        
    }    
    
    
    public void showMatrielDetails(Materiel m){
        if(m != null ){
            lblNom.setText(m.getNom());
            lblDesc.setText(m.getDescription());
        }
        else{
            lblNom.setText("");
            lblDesc.setText("");
        }        
    }
         /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewMateriel() {
        Materiel tempMateriel = new Materiel();
            boolean okClicked =showMaterielEditDialog(tempMateriel);
            System.out.println(okClicked);
            if (okClicked) {
                ms.addMateriel(tempMateriel);
                materielTable.getItems().add(tempMateriel);
                initialize();
            }
        
        
    }
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteMateriel() {
        int selectedIndex = materielTable.getSelectionModel().getSelectedIndex();
         Materiel selectedPerson = materielTable.getSelectionModel().getSelectedItem();
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Really ?");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (selectedIndex >= 0) {
                materielTable.getItems().remove(selectedIndex);
                ms.deleteMateriel(selectedPerson);
            } else {
            // Nothing selected.
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setTitle("No Selection");
                a.setHeaderText("No Product Selected");
                a.setContentText("Please select a Product in the table.");
                a.showAndWait();
            }
        }
    }
    
    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditMateriel() {
        Materiel selectedPerson = materielTable.getSelectionModel().getSelectedItem();
        if ( selectedPerson != null) {
            
            boolean okClicked = showMaterielEditDialog(selectedPerson);
            if (okClicked) {
                ms.editMateriel(selectedPerson);
               // materielTable.getItems().add(selectedPerson);
                initialize();
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Product Selected");
            alert.setContentText("Please select a Product in the table.");

            alert.showAndWait();


        }
    }
    
        public boolean showMaterielEditDialog(Materiel p) {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(getClass().getResource("../Presentation/EditMateriel.fxml"));
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            AnchorPane page = (AnchorPane) loader2.load();
            dialogStage.setTitle("Edit Materiel");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the person into the controller.
            EditMaterielController controller = loader2.getController();
            controller.setMateriel(p);
            controller.setDialogStage(dialogStage);
            // Show the dialog and wait until the user closes it
           dialogStage.showAndWait();
           return controller.isOkClicked();
        } catch (IOException e) {
            System.err.println(e.toString());
            e.printStackTrace();
            return false;
        }
    }
   
    
}
