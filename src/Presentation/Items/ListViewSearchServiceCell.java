package Presentation.Items;


import Entities.Service;
import javafx.scene.control.ListCell;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author elbrh
 */
public class ListViewSearchServiceCell extends ListCell<Service>{
    
    @Override
    public void updateItem(Service p, boolean empty)
    {
        super.updateItem(p,empty);
        if(p != null)
        {
            ServiceSearchItemController data = new ServiceSearchItemController();
            data.setInfo(p);
            setGraphic(data.getBox());
        }
    }
    
}
