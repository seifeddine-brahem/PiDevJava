/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.ProduitPharmaceutique;
import Utils.*;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.MalformedURLException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage ;

/**
 * FXML Controller class
 *
 * @author elbrh
 */
public class EditProduitPharmaController  {
        @FXML
    private JFXTextField nomtxt;

    @FXML
    private JFXTextField desctxt;

    @FXML
    private JFXTextField imagetxt;

    @FXML
    private JFXTextField prixtxt;

    @FXML
    private JFXComboBox<PourQui> Pourqui;

    @FXML
    private JFXComboBox<ModeAdministration> Mode;

    @FXML
    private JFXComboBox<Forme> forme;
    
    @FXML
    private JFXTextField marque;
    
    @FXML
    private ImageView img;
    File f ;
    
    
    
    
        private Stage dialogStage;
    private ProduitPharmaceutique p;
    private boolean okClicked = false;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        Pourqui.getItems().setAll(PourQui.values());
        Mode.getItems().setAll(ModeAdministration.values());
        forme.getItems().setAll(Forme.values());
        if(p != null){
        nomtxt.setText(p.getNom());
        desctxt.setText(p.getDescription());
        prixtxt.setText(Float.toString(p.getPrix()));
        marque.setText(p.getMarque());
        Pourqui.getSelectionModel().select(PourQui.valueOf(p.getPourqui()));
        }
        
        
        // TODO
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
    private void handleOk() throws Exception {
        if (isInputValid()) {
            p.setNom(nomtxt.getText());
            p.setDescription(desctxt.getText());
            p.setPrix(Float.parseFloat(prixtxt.getText()));
            p.setMarque(marque.getText());
            p.setForme(forme.getSelectionModel().getSelectedItem());
            p.setMode_administration(Mode.getSelectionModel().getSelectedItem());
            p.setPourqui(Pourqui.getSelectionModel().getSelectedItem());
            p.setImage(PostFile.upload(f.getAbsolutePath()));
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
    public void setProduit(ProduitPharmaceutique pr) {
        this.p =pr ;
        nomtxt.setText(pr.getNom());
        desctxt.setText(pr.getDescription());
        prixtxt.setText(Float.toString(pr.getPrix()));
//        this.p.setType(TypeProduitSalleDeSport.valueOf(pr.getType()));
        try{
            forme.getSelectionModel().select(Forme.valueOf(p.getForme()));
            Mode.getSelectionModel().select(ModeAdministration.valueOf(p.getMode_administration()));
            Pourqui.getSelectionModel().select(PourQui.valueOf(p.getPourqui()));
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
        if(Mode.getSelectionModel().getSelectedIndex() == -1){
            errorMessage += "No valid Mode ( select an item !)\n";
        }
        if(forme.getSelectionModel().getSelectedIndex() == -1){
            errorMessage += "No valid Forme ( select an item !)\n";
        }
        if(Pourqui.getSelectionModel().getSelectedIndex() == -1){
            errorMessage += "No valid PourQui ( select an item !)\n";
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
            //PostFile.upload(selectedFile.getAbsolutePath());         
            Image image = new Image(imageFile);
            img.setImage(image); 
            p.setImage(selectedFile.getName());
        }
    }
}
