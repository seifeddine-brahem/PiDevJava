/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DataStorage.MyDB;
import Entities.Utilisateur;
import Services.AdminService;
import Services.PrintReport;
import Services.UtilisateurService;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


/**
 * FXML Controller class
 *
 * @author Maha
 */
public class ListeUtilisateursController implements Initializable {

    @FXML
    private AnchorPane anchorpane_main;
    @FXML
    private Label lb_window_title;
    @FXML
    private HBox layout_menu_hbox;
    @FXML
    private FlowPane flowpane_avatar;
    @FXML
    private ImageView profil_image;
    @FXML
    private HBox hbox_search_box;
    @FXML
    private ImageView imgview_search;
    @FXML
    private TextField txf_search_box;
    @FXML
    private Button btn_settings;
    @FXML
    private VBox vbox_right_menu;
    @FXML
    private AnchorPane listeU;
    @FXML
    private JFXButton export;
    @FXML
    private TableView<Utilisateur> tblview_profiles_list;
    @FXML
    private TableColumn<Utilisateur, String> Nom;
    @FXML
    private TableColumn<Utilisateur, String> Prenom;
    @FXML
    private TableColumn<Utilisateur, String> username;
    @FXML
    private TableColumn<Utilisateur, String> email;
    @FXML
    private TableColumn<Utilisateur, String> sexe;
    @FXML
    private TableColumn<Utilisateur, String> roles;
    @FXML
    private TableColumn<Utilisateur,Integer> num_tel;
    @FXML
    private TableColumn<Utilisateur, String> pays;
    @FXML
    private Label lb_title;
    @FXML
    private VBox vbox_user_account;
    @FXML
    private Button btn_change_password;
    @FXML
    private Button btn_logout;
    
    
        
    AdminService as= new AdminService();
    private ObservableList<Utilisateur> data;
    Utilisateur u = new Utilisateur();
    Connection conn;
    @FXML
    private TableColumn<Utilisateur,Integer> enabled;
    
        public ListeUtilisateursController()
    {
        this.conn=MyDB.getinstance().getConnexion();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)    
    {
          List<Utilisateur> LE = as.afficherUtilisateur();
        data = FXCollections.observableArrayList();
        Utilisateur user ;

        LE.stream().forEach((j) -> {
            data.add(j);
        }); 
        
        
        System.out.println(data.size());
        tblview_profiles_list.setItems(data);
        Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        roles.setCellValueFactory(new PropertyValueFactory<>("roles"));
        num_tel.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        enabled.setCellValueFactory(new PropertyValueFactory<>("enabled"));
         
        
    }    

    public void afficher()
    {
           List<Utilisateur> LE = as.afficherUtilisateur();
        data = FXCollections.observableArrayList();
        Utilisateur user ;

        LE.stream().forEach((j) -> {
            data.add(j);
        }); 
        
        
        System.out.println(data.size());
        tblview_profiles_list.setItems(data);
        Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        roles.setCellValueFactory(new PropertyValueFactory<>("roles"));
        num_tel.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        enabled.setCellValueFactory(new PropertyValueFactory<>("enabled"));
    }
    
    @FXML
    public void activer()
    {
        int i=tblview_profiles_list.getSelectionModel().getSelectedItem().getId();
        as.activerCompteParAdmin(i);
        afficher();
    }
    
    @FXML
    public void desactiver()
    {
        int i=tblview_profiles_list.getSelectionModel().getSelectedItem().getId();
        as.BanirCompteParAdmin(i);
        afficher();
    }

    @FXML
    public void stat(ActionEvent event) throws IOException 
    {
        Stage Stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/stat.fxml"));
        Scene scene = new Scene(root);
        Stage.setScene(scene);
        Stage.show();
    }
    
    
      public void saveXLSFile(File file) throws SQLException, IOException 
      {
        try {

            UtilisateurService rec = new UtilisateurService();
            FileOutputStream fileOut;
            fileOut = new FileOutputStream(file);
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet workSheet = workbook.createSheet("sheet 0");
            HSSFRow row1 = workSheet.createRow((short) 0);
            row1.createCell(0).setCellValue("id");
            row1.createCell(1).setCellValue("username");
            row1.createCell(2).setCellValue("nom");
            row1.createCell(3).setCellValue("prenom");
            row1.createCell(4).setCellValue("adresse");
            row1.createCell(5).setCellValue("email");
            row1.createCell(6).setCellValue("enabled");
            row1.createCell(7).setCellValue("roles");
            //row1.createCell(6).setCellValue("date_naissance");
//            row1.createCell(6).setCellValue("code_postal");
//            row1.createCell(6).setCellValue("sexe");
//            row1.createCell(6).setCellValue("num_tel");
//            row1.createCell(6).setCellValue("type");
//            row1.createCell(6).setCellValue("specialite");
            

            HSSFRow row2;

            ResultSet rs = rec.getAllRecExcel();
            while (rs.next()) {
                int a = rs.getRow();
                row2 = workSheet.createRow((short) a);
                row2.createCell(0).setCellValue(rs.getInt(1));
                row2.createCell(1).setCellValue(rs.getString(2));
                row2.createCell(2).setCellValue(rs.getString(3));
                row2.createCell(3).setCellValue(rs.getString(4));
                row2.createCell(4).setCellValue(rs.getString(5));
                row2.createCell(5).setCellValue(rs.getString(6));
                row2.createCell(6).setCellValue(rs.getInt(7));
//                row2.createCell(7).setCellValue(rs.getString(8));
//                //row2.createCell(8).setCellValue(rs.getString(9));
//                row2.createCell(9).setCellValue(rs.getInt(10));
//                row2.createCell(10).setCellValue(rs.getString(11));
//                row2.createCell(11).setCellValue(rs.getInt(12));
//                row2.createCell(12).setCellValue(rs.getString(13));
//                row2.createCell(13).setCellValue(rs.getString(14));
            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();

            TrayNotification tn = new TrayNotification("NEW EXCEL FILE", "Specified excel file successfully generated", NotificationType.SUCCESS);
            tn.showAndDismiss(Duration.seconds(3));
     } catch (SQLException | IOException e)
     {
            TrayNotification tn = new TrayNotification("NEW EXCEL FILE", "Could not generate specified file", NotificationType.ERROR);
            tn.showAndDismiss(Duration.seconds(3));
             System.err.println(e);

        }
    }
    
      
      
             @FXML
    public void generate_excel(ActionEvent event) throws SQLException, IOException {
       
        FileChooser chooser = new FileChooser();
        // Set extension filter
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Excel Files(*.xls)", "*.xls");
        chooser.getExtensionFilters().add(filter);
        // Show save dialog
        File file = chooser.showSaveDialog(export.getScene().getWindow());
        if (file != null) 
        {
            saveXLSFile(file);

        }
    }

    @FXML
    public void ShowRapport(ActionEvent event)
    {
        PrintReport report = new PrintReport();
        report.showReport();
        
    }

    
    
    
    
    
    
    
}
