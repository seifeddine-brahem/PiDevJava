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
import Presentation.Items.ListViewPSAlleCell;
import Entities.ProduitSalleDeSport;
import Services.ProduitSalleDeSportService;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.sql.SQLException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import testesbe.ProuitSalleDesSportTest;

/**
 * FXML Controller class
 *
 * @author elbrh
 */
public class ProduitSalleDesportController {

    @FXML
    private TextField txtsearch;
    @FXML
    private JFXListView<ProduitSalleDeSport> ListeView;

    @FXML
    private Label nom;

    @FXML
    private Label desc;

    @FXML
    private Label prix;

    @FXML
    private Label type;
    @FXML
    private ImageView img;

    private ObservableList<ProduitSalleDeSport> data;
    ProduitSalleDeSport p;
    ProduitSalleDeSportService ps = new ProduitSalleDeSportService();

    Stage primaryStage;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        List<ProduitSalleDeSport> lp = ps.showAllProduit();
        data = FXCollections.observableArrayList();
        System.out.println("spoooooooort");
        lp.stream().forEach((j) -> {
            data.add(j);
        });
        //System.out.println(data.size());
        ListeView.setItems(data);
        ListeView.setCellFactory(new Callback<ListView<ProduitSalleDeSport>, ListCell<ProduitSalleDeSport>>() {

            @Override
            public ListCell<ProduitSalleDeSport> call(ListView<ProduitSalleDeSport> param) {
                return new ListViewPSAlleCell();
            }
        });
        showProduitDetails(null);
        ListeView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showProduitDetails(newValue));
    }

    public void showProduitDetails(ProduitSalleDeSport p) {
        if (p != null) {
            nom.setText(p.getNom());
            desc.setText(p.getDescription());
            prix.setText(Float.toString(p.getPrix()));
            type.setText(p.getType());
            Image image = new Image("http://localhost/" + p.getImage());
            img.setImage(image);

        } else {
            nom.setText("");
            desc.setText("");
            prix.setText("");
            type.setText("");
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewProduit() {
        ProduitSalleDeSport tempProduit = new ProduitSalleDeSport();
        boolean okClicked = showProduitEditDialog(tempProduit);
        primaryStage.setTitle("New Prodect");
        System.out.println(okClicked);
        if (okClicked) {

            ps.addProduit(tempProduit);
        }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteProduit() {
        int selectedIndex = ListeView.getSelectionModel().getSelectedIndex();
        ProduitSalleDeSport selectedPerson = ListeView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Really ?");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (selectedIndex >= 0) {
                ListeView.getItems().remove(selectedIndex);
                ps.deleteProduit(selectedPerson);
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
    private void handleEditProduit() {
        ProduitSalleDeSport selectedPerson = ListeView.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {

            boolean okClicked = showProduitEditDialog(selectedPerson);
            this.primaryStage.setTitle("Edit Product");
            if (okClicked) {
                ps.editProduit(selectedPerson);
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Service Selected");
            alert.setContentText("Please select a Service in the table.");

            alert.showAndWait();

        }
    }

    @FXML
    void HandelSearch() {
        List<ProduitSalleDeSport> lsa = ps.searchProduit(txtsearch.getText());
        data = FXCollections.observableArrayList();
        System.out.println("spoooooooort");
        lsa.stream().forEach((j) -> {
            data.add(j);
        });
        System.out.println(data.size());
        ListeView.setItems(data);
        ListeView.setCellFactory(new Callback<ListView<ProduitSalleDeSport>, ListCell<ProduitSalleDeSport>>() {
            @Override
            public ListCell<ProduitSalleDeSport> call(ListView<ProduitSalleDeSport> param) {
                return new ListViewPSAlleCell();
            }
        });

    }

    public boolean showProduitEditDialog(ProduitSalleDeSport p) {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(ProuitSalleDesSportTest.class.getResource("../Presentation/EditProduitSalleDeSport.fxml"));
            // Create the dialog Stage.
            Stage dialogStage = new Stage();

            AnchorPane page = (AnchorPane) loader2.load();
            //  dialogStage.setTitle("Edit Service");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            this.primaryStage = dialogStage;
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the person into the controller.
            EditProduitSalleDeSportController controller = loader2.getController();
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
        List<ProduitSalleDeSport> lp = ps.showProduitByEtab(p);
        data = FXCollections.observableArrayList();
        System.out.println("spoooooooort");
        lp.stream().forEach((j) -> {
            data.add(j);
        });
        //System.out.println(data.size());
        ListeView.setItems(data);
        ListeView.setCellFactory(new Callback<ListView<ProduitSalleDeSport>, ListCell<ProduitSalleDeSport>>() {

            @Override
            public ListCell<ProduitSalleDeSport> call(ListView<ProduitSalleDeSport> param) {
                return new ListViewPSAlleCell();
            }
        });
        showProduitDetails(null);
        ListeView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showProduitDetails(newValue));
    }

    Commande c = new Commande();
    public static List<Commande> commande = new ArrayList<>();

    public void AjouterAuxPanier() {
        ProduitSalleDeSport selectedPerson = ListeView.getSelectionModel().getSelectedItem();
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

    public void showPanier()  {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(getClass().getResource("../Presentation/ShowCommande.fxml"));
            Stage dialogStage = (Stage) ListeView.getScene().getWindow();
            dialogStage.setTitle("Produit Sale De Sport");
            // dialogStage.initModality(Modality.WINDOW_MODAL);
            AnchorPane page = (AnchorPane) loader2.load();
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the person into the controller.
            ShowCommandeController controller = loader2.getController();
            controller.setPanier(commande);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
