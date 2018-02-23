/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Notification;
import java.util.List;

/**
 *
 * @author anis
 */
public interface INotification {
    public void ajouterNotification(Notification n);
   public List<Notification> afficherNotifications(int iduser);
    public int calculernotifications(int iduser);
    public void updatenotif(int id);
    
}
