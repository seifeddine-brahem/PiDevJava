/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.ProduitSalleDeSport;
import Utils.PostFile;
import Utils.TypeProduitSalleDeSport;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author elbrh
 */
public class EditProduitSalleDeSportController implements Initializable {
    
    @FXML
    private JFXTextField nomtxt;

    @FXML
    private JFXTextField desctxt;


    @FXML
    private ImageView img;

    @FXML
    private JFXTextField prixtxt;

    @FXML
    private JFXComboBox<TypeProduitSalleDeSport> type;
    
    
    
    
    private Stage dialogStage;
    private ProduitSalleDeSport p;
    private boolean okClicked = false;
    public File f;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.getItems().setAll(TypeProduitSalleDeSport.values());
        if(p != null){
        nomtxt.setText(p.getNom());
        desctxt.setText(p.getDescription());
        prixtxt.setText(Float.toString(p.getPrix()));
        type.getSelectionModel().select(TypeProduitSalleDeSport.valueOf(p.getType()));
        
        }

        
        
        
    }    
    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        System.out.println("her");
    }
    
    
        /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() throws Exception {
        if (isInputValid()) {
            p.setNom(nomtxt.getText());
            p.setDescription(desctxt.getText());
            p.setPrix(Float.parseFloat(prixtxt.getText()));
            p.setType(type.getSelectionModel().getSelectedItem());
            if(f!= null ){
                p.setImage(PostFile.upload(f.getAbsolutePath()));
            }
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
    public void setProduit(ProduitSalleDeSport pr) {
        this.p =pr ;
        nomtxt.setText(pr.getNom());
        desctxt.setText(pr.getDescription());
        prixtxt.setText(Float.toString(pr.getPrix()));
        Image image = new Image("http://localhost/"+pr.getImage());
            img.setImage(image); 
        //img.setImage(pr.getImage());
//        this.p.setType(TypeProduitSalleDeSport.valueOf(pr.getType()));
        try{
            type.getSelectionModel().select(TypeProduitSalleDeSport.valueOf(p.getType()));
        }catch(NullPointerException z){
            
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
        if (prixtxt.getText() == null || prixtxt.getText().length() == 0) {
            errorMessage += "No valid prix !\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Float.parseFloat(prixtxt.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid  prix (must be a Float)!\n"; 
            }
        }
        if(type.getSelectionModel().getSelectedIndex() == -1){
            errorMessage += "No valid Type( select an item !)\n";
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
    
        public void uploadPhoto() throws MalformedURLException, Exception{                
        FileChooser.ExtensionFilter imageFilter
        = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(imageFilter);
        File selectedFile = fc.showOpenDialog(null);
        if(selectedFile != null){         
            String imageFile = selectedFile.toURI().toURL().toString();
            this.f =selectedFile;
            System.out.println(imageFile);
            System.out.println(selectedFile.getAbsolutePath());         
            Image image = new Image(imageFile);
            img.setImage(image); 
            p.setImage(selectedFile.getName());
        }
    }
        
        public File selectedFile(File f){
         return null ;
        }
    
}
