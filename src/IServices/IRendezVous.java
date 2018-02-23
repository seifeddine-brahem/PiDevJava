/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.RendezVous;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javafx.collections.ObservableList;
import org.controlsfx.control.Notifications;

/**
 *
 * @author acer
 */
public interface IRendezVous {
    
    public Notifications comparedate();
    public int ajouterRendezvous(RendezVous rdv);
    public void supprimerRendezvous(RendezVous rdv);
    public List<RendezVous> afficherRendezvous(int i);
    public void modifierRendezvous(RendezVous rdv);
    public  ObservableList<RendezVous> chercherRendezvousDate(String dt, String hr, int id);
    public void annulerRendezVous(RendezVous rdv);
    public void ajouterRendezvouspart(RendezVous rdv);
    public void supprimerRendezvouspart(RendezVous rdv);
    public List<RendezVous> afficherRendezvouspart(int i);
    public void modifierRendezvouspart(RendezVous rdv);
    public  ObservableList<RendezVous> chercherRendezvousDatepart(String dt, String hr, int i);
    public void annulerRendezVouspart(RendezVous rdv);
    
}
