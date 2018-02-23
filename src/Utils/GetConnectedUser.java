/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entities.Utilisateur;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
/**
 *
 * @author user
 */
public class GetConnectedUser { 
    public static void main(String[] args) {
        GetConnectedUser();
    }

    public static Utilisateur GetConnectedUser() {
        Utilisateur u = new Utilisateur() ;
      try  {
          
        String homepath = System.getProperty("user.dir");
	File fXmlFile = new File(homepath+"/file.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
	doc.getDocumentElement().normalize();

	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

	NodeList nList = doc.getElementsByTagName("user");

	System.out.println("----------------------------");

	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);

		System.out.println("\nCurrent Element :" + nNode.getNodeName());

		if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			Element eElement = (Element) nNode;
                        u.setId(Integer.parseInt(eElement.getAttribute("id")));
                        u.setNom(eElement.getElementsByTagName("firstname").item(0).getTextContent());
                        u.setPrenom(eElement.getElementsByTagName("lastname").item(0).getTextContent());
                        u.setAdresse(eElement.getElementsByTagName("adresse").item(0).getTextContent());
                        u.setNum_tel(Integer.parseInt(eElement.getElementsByTagName("num_tel").item(0).getTextContent()));
//                        u.setNum_tel(Integer.parseInt(eElement.getAttribute("num_tel")));
                        u.setUsername(eElement.getElementsByTagName("username").item(0).getTextContent());
                        u.setEmail(eElement.getElementsByTagName("email").item(0).getTextContent());
                        u.setPhoto_profil(eElement.getElementsByTagName("photo").item(0).getTextContent());

			System.out.println("Id : " + eElement.getAttribute("id"));
			System.out.println("Nom : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
			System.out.println("Prenom : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
			System.out.println("Login : " + eElement.getElementsByTagName("username").item(0).getTextContent());
			System.out.println("Email : " + eElement.getElementsByTagName("email").item(0).getTextContent());
			
		}
	}
        } catch (Exception e) {
            e.printStackTrace();
        }
      return u ;
    }
                
        
  
}
