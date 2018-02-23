/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entities.DetailCommande;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elbrh
 */
public class FacturePDF {
    
    
        public static void premierTableau(List<DetailCommande> p) throws IOException {
        Document document = new Document();
        try {
            int r = (int) Math.random() * 100;
            PdfWriter.getInstance(document, new FileOutputStream(System.getProperty("user.dir") + "/file.pdf"));
            document.open();
            //On créer un objet table dans lequel on intialise ça taille.
            PdfPTable table = new PdfPTable(2);
            //On créer l'objet cellule.
            PdfPCell cell;
            cell = new PdfPCell(new Phrase("Commande"));
            cell.setColspan(2);
            table.addCell(cell);
            //contenu du tableau.
            p.stream().forEach((e) -> {
                table.addCell("Nom produit:" + e.getP().getNom());
                table.addCell("Quantite: " + e.getQuantite());
            });
            table.addCell("Total :");
            table.addCell("" + p.stream().mapToInt(e -> e.getQuantite()).sum());
            document.add(table);
            document.close();
            openpdf();
        } catch (DocumentException de) {

        } catch (IOException des) {
        }
             
;

    }
        
        public static void openpdf(){
                        File f = new File(System.getProperty("user.dir") + "/file.pdf");
            System.out.println(f.getAbsolutePath());
            try {
                Desktop.getDesktop().open(f);
            } catch (IOException ex) {
                Logger.getLogger(FacturePDF.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    
}
