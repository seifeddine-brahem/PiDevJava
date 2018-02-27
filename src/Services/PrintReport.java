/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.MyDB;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author Maha
 */
public class PrintReport extends JFrame 
{
 Connection conn;
    public PrintReport() {
       this.conn=MyDB.getinstance().getConnexion();
    }
    
    public void showReport()
    {
    
     try {
         JasperReport jasper = JasperCompileManager.compileReport("C:\\Users\\user\\Documents\\NetBeansProjects\\esbe\\src\\esbe\\maha.jrprint");
         JasperPrint print = JasperFillManager.fillReport(jasper,null,conn);
         JRViewer viewer = new JRViewer(print);
         viewer.setOpaque(true);
         this.add(viewer);
         this.setSize(900,500);
         this.setVisible(true);
         } 
     catch (JRException ex)
     {
         Logger.getLogger(PrintReport.class.getName()).log(Level.SEVERE, null, ex);
     }
     
    }
    
    }
    

    

