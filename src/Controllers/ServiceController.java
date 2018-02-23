/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Entities.Service;
import Services.ServiceService ;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import testesbe.ServiceTest;

/**
 * FXML Controller class
 *
 * @author elbrh
 */
public class ServiceController {
    
    @FXML
    private TextField txtsearch;
    @FXML
    private TableView<Service> serviceTable;
    @FXML
    private TableColumn<Service,String> nom_service;
    @FXML
    private TableColumn<Service,String> description;
    @FXML
    private TableColumn<Service,Float> tarif;
    @FXML
    private TableColumn<Service,Integer> id_etab;
    @FXML
    private Label lblTitre;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblTarif;

                
    private ObservableList<Service> data;
    ServiceService service = new ServiceService();
    Service s ;
    private ServiceTest mainApp;
                
                

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        List<Service> lsa = service.showAllService();
        data = FXCollections.observableArrayList();
        System.out.println("ggiiiirrrrr");
        
        lsa.stream().forEach((j) -> {
        data.add(j); });
        
        serviceTable.setItems(data);
        nom_service.setCellValueFactory( new PropertyValueFactory<>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        tarif.setCellValueFactory(new PropertyValueFactory<>("tarif"));        
        // Clear person details.
        showServiceDetails(null);
        // Listen for selection changes and show the person details when changed.
        serviceTable.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> showServiceDetails(newValue));
    }    
    
    
    public void showServiceDetails(Service s){
        if(s != null){
            lblTitre.setText(s.getNom());
            lblDescription.setText(s.getDescription());
            lblTarif.setText(Float.toString(s.getTarif()));
        }else {
            lblTitre.setText("");
            lblDescription.setText("");
            lblTarif.setText("0");
        }
    }
    
    
    
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = serviceTable.getSelectionModel().getSelectedIndex();
         Service selectedPerson = serviceTable.getSelectionModel().getSelectedItem();
                  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Really ?");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            if (selectedIndex >= 0) {
                serviceTable.getItems().remove(selectedIndex);
                service.deleteService(selectedPerson);
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
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() {
        Service tempService = new Service();
            boolean okClicked = showServiceEditDialog(tempService);
            if (okClicked) {
                service.addService(tempService);
        }
        else{
            System.out.println("fuck me");
        }
        
    }
    
    
    
    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Service selectedPerson = serviceTable.getSelectionModel().getSelectedItem();
        if ( selectedPerson != null) {
            boolean okClicked = showServiceEditDialog(selectedPerson);
            if (okClicked) {
                service.editService(selectedPerson);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Service Selected");
            alert.setContentText("Please select a Service in the table.");

            alert.showAndWait();


        }
    }
    
        @FXML
    void HandelSearch() {
//        List<Service> lsa = service.searchService(txtsearch.getText());
//        data = FXCollections.observableArrayList();
//        System.out.println("ggiiiirrrrr");
//        
//        lsa.stream().forEach((j) -> {
//        data.add(j); });
//        
//        serviceTable.setItems(data);
//        nom_service.setCellValueFactory( new PropertyValueFactory<>("nom"));
//        description.setCellValueFactory(new PropertyValueFactory<>("description"));
//        tarif.setCellValueFactory(new PropertyValueFactory<>("tarif"));        
//        // Clear person details.
//        showServiceDetails(null);
//        // Listen for selection changes and show the person details when changed.
//        serviceTable.getSelectionModel().selectedItemProperty().addListener(
//        (observable, oldValue, newValue) -> showServiceDetails(newValue));

    }

    void setService(Service s) {
                List<Service> lsa = service.searchServicebyEtab(s);
        data = FXCollections.observableArrayList();
        //System.out.println("ggiiiirrrrr");
        
        lsa.stream().forEach((j) -> {
        data.add(j); });
        System.out.println(data.size());
        serviceTable.setItems(data);
        nom_service.setCellValueFactory( new PropertyValueFactory<>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        tarif.setCellValueFactory(new PropertyValueFactory<>("tarif"));        
        // Clear person details.
        showServiceDetails(null);
        // Listen for selection changes and show the person details when changed.
        serviceTable.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> showServiceDetails(newValue));
    
    }
    
    
        public boolean showServiceEditDialog(Service s) {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(ServiceTest.class.getResource("../Presentation/EditService.fxml"));
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            AnchorPane page = (AnchorPane) loader2.load();
            dialogStage.setTitle("Edit Service");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the person into the controller.
            EditServiceController controller = loader2.getController();            
            controller.setDialogStage(dialogStage);
            controller.setService(s);            
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
           // controller.isOkClicked();
            return controller.isOkClicked();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
