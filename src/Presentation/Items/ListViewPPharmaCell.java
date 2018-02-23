/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Items;

import Entities.ProduitParapharmacie;
import Entities.ProduitPharmaceutique;
import javafx.scene.control.ListCell;

/**
 *
 * @author elbrh
 */
public class ListViewPPharmaCell  extends ListCell<ProduitPharmaceutique>{
    
    @Override
    public void updateItem(ProduitPharmaceutique p, boolean empty)
    {
        super.updateItem(p,empty);
        if(p != null)
        {
            ProduitPharmaItemController data = new ProduitPharmaItemController();
            data.setInfo(p);
            setGraphic(data.getBox());
        }
    }
    
}
