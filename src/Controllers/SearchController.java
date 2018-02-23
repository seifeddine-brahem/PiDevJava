/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Produit;
import Entities.Service;
import Presentation.Items.ListViewSearchProduitCell;
import Presentation.Items.ListViewSearchServiceCell;
import Services.ProduitService;
import Services.ServiceService;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import testesbe.ProuitSalleDesSportTest;

/**
 * FXML Controller class
 *
 * @author elbrh
 */
public class SearchController {

    @FXML
    private JFXTextField txtnomProduit;
//
//    @FXML
//    private JFXTextField LocationEtabProduit;

    @FXML
    private JFXTextField NomService;

    @FXML
    private JFXTextField LocationEtabService;

    @FXML
    private JFXListView<Produit> ListViewProduit;

    @FXML
    private JFXListView<Service> ListViewService;

    ObservableList<Produit> dataP;
    ObservableList<Service> dataS;
    //ProduitSer
    ServiceService s = new ServiceService();
    ProduitService ps = new ProduitService();

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        dataS = FXCollections.observableArrayList();
        List<Service> lservice = s.showAllService();
        lservice.stream().forEach((j) -> {
            dataS.add(j);
        });
        List<Produit> lproduit = ps.showAllProduit();
        dataP = FXCollections.observableArrayList();
        lproduit.stream().forEach((j) -> {
            dataP.add(j);
        });
        ListViewProduit.setItems(dataP);
        ListViewProduit.setCellFactory(new Callback<ListView<Produit>, ListCell<Produit>>() {
            @Override
            public ListCell<Produit> call(ListView<Produit> param) {
                return new ListViewSearchProduitCell();
            }
        });
        ListViewService.setItems(dataS);
        ListViewService.setCellFactory(new Callback<ListView<Service>, ListCell<Service>>() {

            @Override
            public ListCell<Service> call(ListView<Service> param) {
                return new ListViewSearchServiceCell();
            }
        });
        ListViewProduit.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showOtherProducts(newValue));

        ListViewService.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showOtherServices(newValue));

    }

    public void searchProduit() {
        List<Produit> lsa = ps.searchProduit(txtnomProduit.getText());
        dataP = FXCollections.observableArrayList();
        System.out.println("spoooooooort");
        lsa.stream().forEach((j) -> {
            dataP.add(j);
        });
        System.out.println(dataP.size());
        ListViewProduit.setItems(dataP);
        ListViewProduit.setCellFactory(new Callback<ListView<Produit>, ListCell<Produit>>() {

            @Override
            public ListCell<Produit> call(ListView<Produit> param) {
                return new ListViewSearchProduitCell();
            }
        });
    }

    public void searchService() {
        List<Service> lsa = s.searchService(NomService.getText(), LocationEtabService.getText());
        dataS = FXCollections.observableArrayList();
        lsa.stream().forEach((j) -> {
            dataS.add(j);
        });
        System.out.println(dataP.size());
        ListViewService.setItems(dataS);
        ListViewService.setCellFactory(new Callback<ListView<Service>, ListCell<Service>>() {

            @Override
            public ListCell<Service> call(ListView<Service> param) {
                return new ListViewSearchServiceCell();
            }
        });
    }

    private void showOtherServices(Service s) {

        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(ProuitSalleDesSportTest.class.getResource("../Presentation/Service.fxml"));
            // Create the dialog Stage.
            Stage dialogStage = (Stage) ListViewProduit.getScene().getWindow();
            dialogStage.setTitle("Edit Service");
            // dialogStage.initModality(Modality.WINDOW_MODAL);
            AnchorPane page = (AnchorPane) loader2.load();
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the person into the controller.
            ServiceController controller = loader2.getController();
            controller.setService(s);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            System.err.println(e.toString());
            e.printStackTrace();
        }
    }

    private void showOtherProducts(Produit p) {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            if (p.getType().equals("herbo")) {
                loader2.setLocation(getClass().getResource("../Presentation/ProduitHerbo.fxml"));
                Stage dialogStage = (Stage) ListViewProduit.getScene().getWindow();
                dialogStage.setTitle("Produits Herbo");
                AnchorPane page = (AnchorPane) loader2.load();
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);
                // Set the person into the controller.
                ProduitHerboController controller = loader2.getController();
                controller.setProduit(p);
            } else if (p.getType().equals("Para")) {
                loader2.setLocation(getClass().getResource("../Presentation/ProduitPara.fxml"));
                Stage dialogStage = (Stage) ListViewProduit.getScene().getWindow();
                dialogStage.setTitle("Produits Para");
                AnchorPane page = (AnchorPane) loader2.load();
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);
                // Set the person into the controller.
                ProduitParaController controller = loader2.getController();
                controller.setProduit(p);                
            } else if (p.getType().equals("Pharma")) {
                loader2.setLocation(getClass().getResource("../Presentation/ProduitPharma.fxml"));
                Stage dialogStage = (Stage) ListViewProduit.getScene().getWindow();
                dialogStage.setTitle("Produit Pharma");
                // dialogStage.initModality(Modality.WINDOW_MODAL);
                AnchorPane page = (AnchorPane) loader2.load();
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);
                // Set the person into the controller.
                ProduitPharmaController controller = loader2.getController();
                controller.setProduit(p);
            }
            else{
                loader2.setLocation(getClass().getResource("../Presentation/ProduitSalleDeSport.fxml"));
                Stage dialogStage = (Stage) ListViewProduit.getScene().getWindow();
                dialogStage.setTitle("Produit Sale De Sport");
                // dialogStage.initModality(Modality.WINDOW_MODAL);
                AnchorPane page = (AnchorPane) loader2.load();
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);
                // Set the person into the controller.
                ProduitSalleDesportController controller = loader2.getController();
                controller.setProduit(p);
                
            }
        } catch (IOException e) {
            System.err.println(e.toString());
            e.printStackTrace();
        }
    }

}
