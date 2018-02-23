/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Commande;
import Entities.DetailCommande;
import Entities.Produit;
import Services.CommandeService;
import java.time.LocalDate;
import java.util.List;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author elbrh
 */
public class ShowMyCommandeController  {
    @FXML
    private TableView<Commande> Commande;

    @FXML
    private TableColumn<Commande, LocalDate> dateCommandeColumn;

    @FXML
    private TableColumn<Commande, String> NomEtablissementColumn;

    @FXML
    private TableView<DetailCommande> produits;

    @FXML
    private TableColumn<DetailCommande, String> nomProduitColumn;

    @FXML
    private TableColumn<DetailCommande, String> quantiteColumn;

    @FXML
    private Label lblproduitNom;

    @FXML
    private Label lblProduitDesc;

    @FXML
    private Label lblProduitPrix;

    @FXML
    private Label lblProduitImage;
    
    ObservableList<Commande> data ;
    ObservableList<DetailCommande> dataP ;
    CommandeService cs = new CommandeService();
    Commande cmd ;
    Produit pr ;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        data =  FXCollections.observableArrayList();
        List<Commande> ls = cs.showMyCommandes(1);
        ls.stream().forEach((e)->{
            data.add(e);
        });
        Commande.setItems(data);
        NomEtablissementColumn.setCellValueFactory(celldata-> 
                new ReadOnlyStringWrapper(celldata.getValue().getProduit().getEtab().getNom()));
        dateCommandeColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        Commande.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                    showCommandeDetails(newValue));
        
        
    }    

    private void showCommandeDetails(Commande newValue) {
        List<DetailCommande> ls = cs.showProductParCommande(newValue);
        dataP = FXCollections.observableArrayList();
        ls.stream().forEach((j)->{
            dataP.add(j);
        });
        produits.setItems(dataP);
        nomProduitColumn.setCellValueFactory(celldata-> new ReadOnlyStringWrapper(celldata.getValue().getP().getNom()));
        quantiteColumn.setCellValueFactory(celldata->
                new ReadOnlyStringWrapper(String.valueOf(celldata.getValue().getQuantite())));
        
    
    
    }
    
}
