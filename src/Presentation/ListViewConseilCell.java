/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Controllers.ConseilItemController;
import Entities.Conseil;
import javafx.scene.control.ListCell;

/**
 *
 * @author Maha
 */
public class ListViewConseilCell extends ListCell<Conseil>
{
        public void updateItem(Conseil p, boolean empty)
    {
        super.updateItem(p,empty);
        if(p != null)
        {
            ConseilItemController data = new ConseilItemController();
            data.setInfo(p);
            setGraphic(data.getBox());
        }
    } 

}
