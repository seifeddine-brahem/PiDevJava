/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Materiel;
import Entities.ProduitHerbo;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.MalformedURLException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author elbrh
 */
public class EditMaterielController  {
    
    @FXML
    private JFXTextField nomtxt;

    @FXML
    private JFXTextField desctxt;
    
    private Stage dialogStage;
    private Materiel p;
    private boolean okClicked = false;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        // TODO
        if(p != null){
        nomtxt.setText(p.getNom());
        desctxt.setText(p.getDescription());
        }
    }    
    
    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        //System.out.println("her");
    }
    
    
    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            p.setNom(nomtxt.getText());
            p.setDescription(desctxt.getText());
            okClicked = true;
            System.out.println(okClicked);
            dialogStage.close();
        }
    }
    
     /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
       
        return okClicked;
    }
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
        /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setMateriel(Materiel pr) {
        this.p =pr ;
        if(p != null){
        nomtxt.setText(pr.getNom());
        desctxt.setText(pr.getDescription());
        }
    }
        /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (nomtxt.getText() == null || nomtxt.getText().length() == 0) {
            errorMessage += "No valid  name!\n"; 
        }
        if (desctxt.getText() == null || desctxt.getText().length() == 0) {
            errorMessage += "No valid description !\n"; 
        }


        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    } 
    
    

}