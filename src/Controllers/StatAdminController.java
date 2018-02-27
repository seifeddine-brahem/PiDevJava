/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Utilisateur;
import Services.AdminService;
import Services.UtilisateurService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Maha
 */
public class StatAdminController implements Initializable {

    @FXML
    private PieChart PieChart;
    @FXML
    private BarChart<?, ?> pays;
    @FXML
    private LineChart<?, ?> LineChart;
    @FXML
    private Group groupLadies;
    @FXML
    private Label nb_femme;
    @FXML
    private Group groupGents;
    @FXML
    private Group groupRegistered;
    @FXML
    private Group groupGents1;
    @FXML
    private Group groupLadies2;
    @FXML
    private Label nb_homme;
    @FXML
    private Group groupLadies3;
    @FXML
    private Label nb_comptes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
         
        AdminService us=  new AdminService();
        
    ObservableList<PieChart.Data> pieChartData= FXCollections.observableArrayList(
    new PieChart.Data("Femme", us.CalculerFemme()),
    new PieChart.Data("Homme",us.CalculerHomme())
    );
    PieChart.setData(pieChartData);
    PieChart.setData(pieChartData);

     XYChart.Series set1 = new XYChart.Series<>();
     set1.getData().add(new XYChart.Data("Allemagne",us.CalculerAllemagne()));
     set1.getData().add(new XYChart.Data("Amsterdam", us.CalculerAmsterdam()));
     set1.getData().add(new XYChart.Data("Canada", us.CalculerCanada()));
     set1.getData().add(new XYChart.Data("France", us.CalculerFrance()));
     set1.getData().add(new XYChart.Data("Tunisie", us.CalculerTunisie()));
     set1.getData().add(new XYChart.Data("Chine", us.CalculerChine()));
     set1.getData().add(new XYChart.Data("Japon", us.CalculerJapon()));
     set1.getData().add(new XYChart.Data("Turquie", us.CalculerTurquie()));
     
     
     pays.getData().addAll(set1);
     
     
     
     


    AdminService des= new AdminService();
    XYChart.Series series = new XYChart.Series(); //Make a new XYChart object
    //Add Data
    series.getData().add(new XYChart.Data("Demande En Attente", des.CalculerDemandeEnAttente()));
    series.getData().add(new XYChart.Data("Demande Acceptée", des.CalculerDemandeAccepte()));
    series.getData().add(new XYChart.Data("Demande Refusée", des.CalculerDemandeRefuse()));

    LineChart.getData().addAll(series);

        }
    
    
    
}
