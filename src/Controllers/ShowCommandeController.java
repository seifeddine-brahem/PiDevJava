/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Commande;
import Entities.Produit;
import Services.CommandeService;
import Utils.FacturePDF;
import java.io.IOException;
import java.util.List;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author elbrh
 */
public class ShowCommandeController {

    @FXML
    private TableView<Commande> produits;

    @FXML
    private TableColumn<Commande, String> nomProduitColumn;

    @FXML
    private TableColumn<Commande, String> quantiteColumn;

    @FXML
    private Label lblproduitNom;

    @FXML
    private Label lblProduitDesc;

    @FXML
    private Label lblProduitPrix;

    @FXML
    private Label lblProduitImage;

    private ObservableList<Commande> data;
    CommandeService cs = new CommandeService();
    Commande cmd;
    Produit pr;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        // TODO
    }

    void setPanier(List<Commande> p) {
        commande = p;
        System.out.println("in ;D  commande interface :");
        System.out.println(p.size());
        p.stream().forEach(e -> System.out.println(e.toString()));

        data = FXCollections.observableArrayList();
        p.stream().forEach((j) -> {
            data.add(j);
        });
        produits.setItems(data);
        nomProduitColumn.setCellValueFactory(celldata -> new ReadOnlyStringWrapper(celldata.getValue().getProduit().getNom()));
        quantiteColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
    }

    List<Commande> commande;

    @FXML
    void AnnulerCommande() {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(getClass().getResource("../Presentation/Search.fxml"));
            Stage dialogStage = (Stage) produits.getScene().getWindow();
            dialogStage.setTitle("Search");
            AnchorPane page = (AnchorPane) loader2.load();
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    void PassCommande() throws IOException {
        if (commande.size() > 0) {
            CommandeService cs = new CommandeService();
           int res =  cs.passCommande(commande);   
           Commande c = new Commande();
            System.out.println(res);
           c.setId_commande(res);
            FacturePDF.premierTableau(cs.showProductParCommande(c));
            FacturePDF.openpdf();
           
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("no Prodcut");
            a.setContentText("you have to select at least 1 product");
            a.showAndWait();
        }
    }

    @FXML
    void removeProduit() {
        Commande c = produits.getSelectionModel().getSelectedItem();
        commande.remove(c);
        System.out.println(c.toString() + "deleted ");
    }

}
