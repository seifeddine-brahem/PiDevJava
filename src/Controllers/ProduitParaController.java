/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Commande;
import Entities.Produit;
import Entities.ProduitParapharmacie;
import Presentation.Items.ListViewPParaCell;
import Services.ProduitParapharmacieService;
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
import testesbe.ProduitParaTest;

/**
 * FXML Controller class
 *
 * @author elbrh
 */
public class ProduitParaController {

    @FXML
    private TextField txtsearch;

    @FXML
    private JFXListView<ProduitParapharmacie> ListView;

    @FXML
    private Label lblNom;

    @FXML
    private Label lblPrix;

    @FXML
    private Label lblMarque;

    @FXML
    private Label lblDesc;

    @FXML
    private Label lblCategorie;
    private ObservableList<ProduitParapharmacie> data;
    ProduitParapharmacieService ps = new ProduitParapharmacieService();
    ProduitParapharmacie p;

    public static List<Commande> commande = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        List<ProduitParapharmacie> ls = ps.searchAllProduit();
        data = FXCollections.observableArrayList();
        ls.stream().forEach((j) -> {
            data.add(j);
        });

        ListView.setItems(data);
        ListView.setCellFactory(new Callback<ListView<ProduitParapharmacie>, ListCell<ProduitParapharmacie>>() {

            @Override
            public ListCell<ProduitParapharmacie> call(ListView<ProduitParapharmacie> param) {
                return new ListViewPParaCell();
            }
        });
        showProduitDetails(null);
        ListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showProduitDetails(newValue));

    }

    public void showProduitDetails(ProduitParapharmacie p) {
        if (p != null) {
            lblNom.setText(p.getNom());
            lblDesc.setText(p.getDescription());
            lblPrix.setText(Float.toString(p.getPrix()));
            lblCategorie.setText(p.getCategorie());
            lblMarque.setText(p.getMarque());
        } else {
            // GrifPane.setVisible(false);
            lblNom.setText("");
            lblDesc.setText("");
            lblCategorie.setText("");
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
        ProduitParapharmacie tempProduit = new ProduitParapharmacie();
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
        ProduitParapharmacie selectedPerson = ListView.getSelectionModel().getSelectedItem();
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
                //alert.initOwner(mainApp.getPrimaryStage());
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
        ProduitParapharmacie selectedPerson = ListView.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = showProduitEditDialog(selectedPerson);
            if (okClicked) {
                ps.editProduit(selectedPerson);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            // alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Service Selected");
            alert.setContentText("Please select a Service in the table.");
            alert.showAndWait();
        }
    }

    public boolean showProduitEditDialog(ProduitParapharmacie p) {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(ProduitParaTest.class.getResource("../Presentation/EditProduitPara.fxml"));
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            AnchorPane page = (AnchorPane) loader2.load();
            dialogStage.setTitle("Edit Pdoduit para");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the person into the controller.
            EditProduitParaController controller = loader2.getController();
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
        List<ProduitParapharmacie> ls = ps.searchProduitByEtab(p);
        data = FXCollections.observableArrayList();
        ls.stream().forEach((j) -> {
            data.add(j);
        });

        ListView.setItems(data);
        ListView.setCellFactory(new Callback<ListView<ProduitParapharmacie>, ListCell<ProduitParapharmacie>>() {

            @Override
            public ListCell<ProduitParapharmacie> call(ListView<ProduitParapharmacie> param) {
                return new ListViewPParaCell();
            }
        });
        showProduitDetails(null);
        ListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showProduitDetails(newValue));
    }

    Commande c = new Commande();

    public void AjouterAuxPanier() {
        ProduitParapharmacie selectedPerson = ListView.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            c.setProduit(selectedPerson);
            TextInputDialog dialog = new TextInputDialog("Quantite");
            dialog.setTitle("Quantite");
            dialog.setHeaderText(selectedPerson.getNom());
            dialog.setContentText("how much you need:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(name -> System.out.println("Your name: " + name));
            result.ifPresent(q -> c.setQuantite(Integer.parseInt(q)));
            System.out.println(c.getQuantite());
            commande.add(c);
            c.setId_user(1);
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
