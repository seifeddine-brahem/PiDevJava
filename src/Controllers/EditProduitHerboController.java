/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.ProduitHerbo;
import Utils.CategorieProduitHerbo;
import Utils.PostFile;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.MalformedURLException;
import javafx.fxml.FXML;
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
public class EditProduitHerboController {

    @FXML
    private JFXTextField nomtxt;

    @FXML
    private JFXTextField desctxt;

    @FXML
    private JFXTextField imagetxt;

    @FXML
    private JFXTextField prixtxt;

    @FXML
    private JFXComboBox<CategorieProduitHerbo> categorie;

    @FXML
    private JFXTextField marque;

    @FXML
    private JFXRadioButton yes;

    @FXML
    private JFXRadioButton no;

    @FXML
    private ImageView pic;

    private Stage dialogStage;
    private ProduitHerbo p;
    private boolean okClicked = false;
    public File f;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        // TODO
        categorie.getItems().setAll(CategorieProduitHerbo.values());
        if (p != null) {
            nomtxt.setText(p.getNom());
            desctxt.setText(p.getDescription());
            prixtxt.setText(Float.toString(p.getPrix()));
            marque.setText(p.getMarque());
            categorie.getSelectionModel().select(CategorieProduitHerbo.valueOf(p.getCategorie()));
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
    private void handleOk() throws Exception {
        if (isInputValid()) {
            p.setNom(nomtxt.getText());
            p.setDescription(desctxt.getText());
            p.setPrix(Float.parseFloat(prixtxt.getText()));
            p.setMarque(marque.getText());
            p.setCategorie(categorie.getSelectionModel().getSelectedItem());
            if (f != null) {
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
    public void setProduit(ProduitHerbo pr) {
        this.p = pr;
        nomtxt.setText(pr.getNom());
        desctxt.setText(pr.getDescription());
        prixtxt.setText(Float.toString(pr.getPrix()));
        marque.setText(pr.getMarque());

        if (p.getBio()) {
            yes.setSelected(true);
        } else {
            no.setSelected(true);
        }
        try {
            categorie.getSelectionModel().select(CategorieProduitHerbo.valueOf(p.getCategorie()));

        } catch (NullPointerException z) {

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
        if (categorie.getSelectionModel().getSelectedIndex() == -1) {
            errorMessage += "No valid categorie ( select an item !)\n";
        }

        if (!yes.isSelected() && !no.isSelected()) {
            errorMessage += "No valid Bio ( select an item !)\n";
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

    public void uploadPhoto() throws MalformedURLException, Exception {
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(imageFilter);
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            String imageFile = selectedFile.toURI().toURL().toString();
            this.f = selectedFile;
            System.out.println(imageFile);
            System.out.println(selectedFile.getAbsolutePath());
            //PostFile.upload(selectedFile.getAbsolutePath());         
            Image image = new Image(imageFile);
            pic.setImage(image);
            p.setImage(selectedFile.getName());
        }
    }


}
