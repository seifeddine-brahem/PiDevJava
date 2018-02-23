/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Service;
import java.util.List;

/**
 *
 * @author elbrh
 */
public interface IService {
    public void addService(Service s);
    public void editService(Service s);
    public void deleteService(Service s);
    public Service showService(Service s);
    public List<Service> searchService(String nom ,String location);
    public List<Service> searchServicebyEtab(Service s);
}
