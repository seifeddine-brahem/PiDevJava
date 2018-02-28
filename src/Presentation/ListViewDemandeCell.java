/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Controllers.DemandeItemController;
import Entities.Demande;
import javafx.scene.control.ListCell;

/**
 *
 * @author Maha
 */
public class ListViewDemandeCell extends ListCell<Demande>
{
            public void updateItem(Demande p, boolean empty)
    {
        super.updateItem(p,empty);
        if(p != null)
        {
            DemandeItemController data = new DemandeItemController();
            data.setInfo(p);
            setGraphic(data.getBox());
        }
    } 
}
