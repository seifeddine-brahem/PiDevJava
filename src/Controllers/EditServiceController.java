/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Service;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author elbrh
 */
public class EditServiceController implements Initializable {

    @FXML
    private TextField nom;

    @FXML
    private TextField description;

    @FXML
    private TextField tarif;

    @FXML
    private Button btnEdit;
     private Stage dialogStage;
    private Service s ;
    private boolean okClicked = false;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(s != null){
            nom.setText(s.getNom());
            description.setText(s.getDescription());
            tarif.setText(Float.toString(s.getTarif()));
        }
        else{
            System.out.println("fuuuck");
        }
        
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setService(Service s) {
        this.s =s ;
        nom.setText(s.getNom());
        description.setText(s.getDescription());
        tarif.setText(Float.toString(s.getTarif()));
       // System.out.println(s.toString());
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
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            s.setNom(nom.getText());
            s.setDescription(description.getText());
            s.setTarif(Float.parseFloat(tarif.getText()));
            okClicked = true;
            dialogStage.close();
        }
    }
     /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (nom.getText() == null || nom.getText().length() == 0) {
            errorMessage += "No valid  name!\n"; 
        }
        if (description.getText() == null || description.getText().length() == 0) {
            errorMessage += "No valid descriptio, !\n"; 
        }


        if (tarif.getText() == null || tarif.getText().length() == 0) {
            errorMessage += "No valid tarif !\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Float.parseFloat(tarif.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid  tarif (must be a Float)!\n"; 
            }
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
