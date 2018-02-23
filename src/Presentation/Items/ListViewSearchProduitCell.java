/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Items;

import Entities.Produit;
import javafx.scene.control.ListCell;

/**
 *
 * @author elbrh
 */
public class ListViewSearchProduitCell extends ListCell<Produit>{
        @Override
    public void updateItem(Produit p, boolean empty)
    {
        super.updateItem(p,empty);
        if(p != null)
        {
            ProduitSearchItemController data = new ProduitSearchItemController();
            data.setInfo(p);
            setGraphic(data.getBox());
        }
    }
    
}
