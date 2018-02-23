/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Items;

import Entities.ProduitHerbo;
import Entities.ProduitSalleDeSport;
import javafx.scene.control.ListCell;

/**
 *
 * @author elbrh
 */
public class ListViewPHerboItemCell extends ListCell<ProduitHerbo>{
    
    
        
        @Override
    public void updateItem(ProduitHerbo p, boolean empty)
    {
        super.updateItem(p,empty);
        if(p != null)
        {
            ProduitParatemController data = new ProduitParatemController();
            data.setInfo(p);
            setGraphic(data.getBox());
        }
    }
}
