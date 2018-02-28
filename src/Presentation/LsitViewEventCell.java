/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Controllers.EvenementItemController;
import Entities.Evenement;
import javafx.scene.control.ListCell;

/**
 *
 * @author Maha
 */
public class LsitViewEventCell extends ListCell<Evenement>{
    
        @Override
    public void updateItem(Evenement p, boolean empty)
    {
        super.updateItem(p,empty);
        if(p != null)
        {
            EvenementItemController data = new EvenementItemController();
            data.setInfo(p);
            setGraphic(data.getBox());
        }
    } 

    
    
}
