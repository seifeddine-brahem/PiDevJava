/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Commande;
import Entities.Produit;
import Entities.ProduitHerbo;
import Presentation.Items.ListViewPHerboItemCell;
import Services.ProduitHerboService;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import testesbe.ProduitHerboTest;

/**
 * FXML Controller class
 *
 * @author elbrh
 */
public class ProduitHerboController {

    @FXML
    private JFXListView<ProduitHerbo> ListeView;

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
    @FXML
    private Label lblbio;

    @FXML
    private ImageView pic;

    private ProduitHerboTest mainApp;
    private ObservableList<ProduitHerbo> data;
    ProduitHerboService ps = new ProduitHerboService();
    ProduitHerbo p;
    public static List<Commande> commande = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        List<ProduitHerbo> ls = ps.searchAllProduit();
        data = FXCollections.observableArrayList();
        ls.stream().forEach((j) -> {
            data.add(j);
        });

        ListeView.setItems(data);
        ListeView.setCellFactory((ListView<ProduitHerbo> param) -> new ListViewPHerboItemCell());
        showProduitDetails(null);
        ListeView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showProduitDetails(newValue));

    }

    public void showProduitDetails(ProduitHerbo p) {
        if (p != null) {
            lblNom.setText(p.getNom());
            lblDesc.setText(p.getDescription());
            lblPrix.setText(Float.toString(p.getPrix()));
            lblCategorie.setText(p.getCategorie());
            lblMarque.setText(p.getMarque());
            lblbio.setText(Boolean.toString(p.getBio()));
            Image image = new Image("http://localhost/" + p.getImage());
            pic.setImage(image);
        } else {
            lblNom.setText("");
            lblDesc.setText("");
            lblCategorie.setText("");
            lblPrix.setText("");
            lblMarque.setText("");
            lblbio.setText("");
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewProduit() {
        ProduitHerbo tempProduit = new ProduitHerbo();
        boolean okClicked = showProduitEditDialog(tempProduit);
        System.out.println(okClicked);
        if (okClicked) {
            ps.addProduit(tempProduit);
            initialize();
        }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteProduit() {
        int selectedIndex = ListeView.getSelectionModel().getSelectedIndex();
        ProduitHerbo selectedPerson = ListeView.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Confirmation Dialog");
        a.setHeaderText("Really ?");
        a.setContentText("Are you ok with this?");

        Optional<ButtonType> result = a.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (selectedIndex >= 0) {
                ListeView.getItems().remove(selectedIndex);
                ps.deleteProduit(selectedPerson);
            } else {
                // Nothing selected.
                Alert alert = new Alert(Alert.AlertType.WARNING);
                //alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("No Selection");
                alert.setHeaderText("No Product Selected");
                alert.setContentText("Please select a Product in the table.");
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
        ProduitHerbo selectedPerson = ListeView.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = showProduitEditDialog(selectedPerson);
            if (okClicked) {
                ps.editProduit(selectedPerson);
                initialize();
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            //alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Product Selected");
            alert.setContentText("Please select a Product in the table.");

            alert.showAndWait();

        }
    }

    public boolean showProduitEditDialog(ProduitHerbo p) {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(ProduitHerboTest.class.getResource("../Presentation/EditProduitHerbo.fxml"));
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            AnchorPane page = (AnchorPane) loader2.load();
            dialogStage.setTitle("Edit Pdoduit para");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            // dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the person into the controller.
            EditProduitHerboController controller = loader2.getController();
            controller.setDialogStage(dialogStage);
            controller.setProduit(p);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            System.err.println(e.toString());
            return false;
        }
    }

    void setProduit(Produit p) {
        List<ProduitHerbo> ls = ps.searchProduitByEtab(p);
        data = FXCollections.observableArrayList();
        ls.stream().forEach((j) -> {
            data.add(j);
        });
        ListeView.setItems(data);
        ListeView.setCellFactory(new Callback<ListView<ProduitHerbo>, ListCell<ProduitHerbo>>() {

            @Override
            public ListCell<ProduitHerbo> call(ListView<ProduitHerbo> param) {
                return new ListViewPHerboItemCell();
            }
        });
        showProduitDetails(null);
        ListeView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showProduitDetails(newValue));
    }

    
    
    public void AjouterAuxPanier() {
        ProduitHerbo selectedPerson = ListeView.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            if (!commande.stream().anyMatch(e -> e.getProduit().equals(selectedPerson))) {
                Commande c = new Commande();
                c.setProduit(selectedPerson);
                boolean test = false;
                while (test != true) {
                    TextInputDialog dialog = new TextInputDialog("1");
                    dialog.setTitle("Quantite");
                    dialog.setHeaderText(selectedPerson.getNom());
                    dialog.setContentText("how much you need:");
                    Optional<String> result = dialog.showAndWait();
                    result.ifPresent(name -> System.out.println("Quantite: " + name));
                    try {
                        result.ifPresent(q -> c.setQuantite(Integer.parseInt(q)));
                        System.out.println(c.getQuantite());
                        test = true;
                    } catch (NumberFormatException ex) {
                        test = false;
                    }
                }
                commande.add(c);
                c.setId_user(1);
                System.out.println(commande.size());
            } else {
                //Product already choosed
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("info");
                a.setHeaderText("you are already choose that");
                a.showAndWait();
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

    @FXML
    public void ShowPanier() {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(getClass().getResource("../Presentation/ShowCommande.fxml"));
            Stage dialogStage = (Stage) ListeView.getScene().getWindow();
            dialogStage.setTitle("Produit Sale De Sport");
            AnchorPane page = (AnchorPane) loader2.load();
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the Commande into the controller.
            ShowCommandeController controller = loader2.getController();
            controller.setPanier(commande);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
