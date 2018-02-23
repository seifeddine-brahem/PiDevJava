/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Items;

import Entities.ProduitParapharmacie;
import javafx.scene.control.ListCell;

/**
 *
 * @author elbrh
 */
public class ListViewPParaCell extends ListCell<ProduitParapharmacie> {
    
    
    @Override
    public void updateItem(ProduitParapharmacie p, boolean empty)
    {
        super.updateItem(p,empty);
        if(p != null)
        {
            ProduitParaItemController data = new ProduitParaItemController
        ();
            data.setInfo(p);
            setGraphic(data.getBox());
        }
    }
    
}
