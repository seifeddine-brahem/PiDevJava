/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Items;

import Presentation.Items.ProduitParatemController;
import Entities.ProduitHerbo;
import Entities.ProduitSalleDeSport;
import javafx.scene.control.ListCell;

/**
 *
 * @author elbrh
 */
public class ListViewPSAlleCell extends ListCell<ProduitSalleDeSport>{
    
        @Override
    public void updateItem(ProduitSalleDeSport p, boolean empty)
    {
        super.updateItem(p,empty);
        if(p != null)
        {
            ProduitSalleDeSportItemController data = new ProduitSalleDeSportItemController();
            data.setInfo(p);
            setGraphic(data.getBox());
        }
    }


}
