/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.ProduitParaController.commande;
import Entities.Commande;
import Entities.Produit;
import Entities.ProduitParapharmacie;
import Entities.ProduitPharmaceutique;
import Presentation.Items.ListViewPPharmaCell;
import Services.ProduitPharmacitiqueService;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import testesbe.ProduitPharmaTest;

/**
 * FXML Controller class
 *
 * @author elbrh
 */
public class ProduitPharmaController {

    @FXML
    private TextField txtsearch;

    @FXML
    private JFXListView<ProduitPharmaceutique> ListView;

    @FXML
    private Label lblNom;

    @FXML
    private Label lblPrix;

    @FXML
    private Label lblMarque;

    @FXML
    private Label lblDesc;

    @FXML
    private Label lblPourQui;

    @FXML
    private Label lblModeAdministration;

    @FXML
    private Label lblForme;

    private ProduitPharmaTest mainApp;
    private ObservableList<ProduitPharmaceutique> data;
    ProduitPharmacitiqueService ps = new ProduitPharmacitiqueService();
    ProduitPharmaceutique p;
    public static List<Commande> commande = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        List<ProduitPharmaceutique> ls = ps.searchAllProduit();
        data = FXCollections.observableArrayList();
        ls.stream().forEach((j) -> {
            data.add(j);
        });

        ListView.setItems(data);
        ListView.setCellFactory(new Callback<ListView<ProduitPharmaceutique>, ListCell<ProduitPharmaceutique>>() {

            @Override
            public ListCell<ProduitPharmaceutique> call(ListView<ProduitPharmaceutique> param) {
                return new ListViewPPharmaCell();
            }
        });
        showProduitDetails(null);
        ListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showProduitDetails(newValue));

    }

    public void showProduitDetails(ProduitPharmaceutique p) {
        if (p != null) {
            lblNom.setText(p.getNom());
            lblDesc.setText(p.getDescription());
            lblPrix.setText(Float.toString(p.getPrix()));
            lblForme.setText(p.getForme());
            lblModeAdministration.setText(p.getMode_administration());
            lblPourQui.setText(p.getPourqui());
            lblMarque.setText(p.getMarque());
        } else {
            // GrifPane.setVisible(false);
            lblNom.setText("");
            lblDesc.setText("");
            lblForme.setText("");
            lblPourQui.setText("");
            lblModeAdministration.setText("");
            lblPrix.setText("");
            lblMarque.setText("");

        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewProduit() {
        ProduitPharmaceutique tempProduit = new ProduitPharmaceutique();

        boolean okClicked = showProduitEditDialog(tempProduit);
        System.out.println(okClicked);
        if (okClicked) {
            ps.addProduit(tempProduit);

        } else {
            System.out.println("foook me");
        }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteProduit() {
        int selectedIndex = ListView.getSelectionModel().getSelectedIndex();
        ProduitPharmaceutique selectedPerson = ListView.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Confirmation Dialog");
        a.setHeaderText("Really ?");
        a.setContentText("Are you ok with this?");

        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == ButtonType.OK) {

            if (selectedIndex >= 0) {
                ListView.getItems().remove(selectedIndex);
                ps.deleteProduit(selectedPerson);
            } else {
                // Nothing selected.
                Alert alert = new Alert(Alert.AlertType.WARNING);
//                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("No Selection");
                alert.setHeaderText("No Service Selected");
                alert.setContentText("Please select a Service in the table.");
                alert.showAndWait();
            }
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditProduit() {
        ProduitPharmaceutique selectedPerson = ListView.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = showProduitEditDialog(selectedPerson);
            if (okClicked) {
                ps.editProduit(selectedPerson);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            //alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Service Selected");
            alert.setContentText("Please select a Service in the table.");

            alert.showAndWait();

        }
    }

    @FXML
    void HandelSearch() {
        List<ProduitPharmaceutique> ls = ps.searchProduit(txtsearch.getText());
        data = FXCollections.observableArrayList();
        ls.stream().forEach((j) -> {
            data.add(j);
        });

        ListView.setItems(data);
        ListView.setCellFactory(new Callback<ListView<ProduitPharmaceutique>, ListCell<ProduitPharmaceutique>>() {

            @Override
            public ListCell<ProduitPharmaceutique> call(ListView<ProduitPharmaceutique> param) {
                return new ListViewPPharmaCell();
            }
        });
        showProduitDetails(null);
        ListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showProduitDetails(newValue));

    }

    public boolean showProduitEditDialog(ProduitPharmaceutique p) {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(ProduitPharmaTest.class.getResource("../Presentation/EditProduitPharma.fxml"));
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            AnchorPane page = (AnchorPane) loader2.load();
            dialogStage.setTitle("Edit Pdoduit Pharma");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the person into the controller.
            EditProduitPharmaController controller = loader2.getController();
            controller.setDialogStage(dialogStage);
            controller.setProduit(p);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            System.err.println(e.toString());
            e.printStackTrace();
            return false;
        }
    }

    void setProduit(Produit p) {
        List<ProduitPharmaceutique> ls = ps.searchProduitByEtab(p);
        data = FXCollections.observableArrayList();
        ls.stream().forEach((j) -> {
            data.add(j);
        });

        ListView.setItems(data);
        ListView.setCellFactory(new Callback<ListView<ProduitPharmaceutique>, ListCell<ProduitPharmaceutique>>() {

            @Override
            public ListCell<ProduitPharmaceutique> call(ListView<ProduitPharmaceutique> param) {
                return new ListViewPPharmaCell();
            }
        });
        showProduitDetails(null);
        ListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showProduitDetails(newValue));

    }
    
    
        

    public void AjouterAuxPanier() {
        ProduitPharmaceutique selectedPerson = ListView.getSelectionModel().getSelectedItem();
        if (selectedPerson != null  && !commande.stream().anyMatch(e->e.getProduit().equals(selectedPerson))) {
            Commande c = new Commande();
            c.setProduit(selectedPerson);
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("1");
            dialog.setHeaderText(selectedPerson.getNom());
            dialog.setContentText("how much you need:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(name -> System.out.println("Your name: " + name));
            result.ifPresent(q -> c.setQuantite(Integer.parseInt(q)));
            //System.out.println(c.getQuantite());
            c.setId_user(1);
            commande.add(c);
            System.out.println(commande.size());

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            // alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Product Selected");
            alert.setContentText("Please select a Product in the table.");
            alert.showAndWait();
        }

    }

}
