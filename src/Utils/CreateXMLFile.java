/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entities.Utilisateur;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author user
 */
public class CreateXMLFile {

    
    public static void setUserFile(Utilisateur u){
        try {
                if(u == null){
                    System.err.println("shit on the table");
                }
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("ESBE");
		doc.appendChild(rootElement);

		// staff elements
		Element user = doc.createElement("user");
		rootElement.appendChild(user);

		// set attribute to staff element
		Attr id = doc.createAttribute("id");
		id.setValue((String.valueOf(u.getId())));
		user.setAttributeNode(id);

		// firstname elements
		Element firstname = doc.createElement("firstname");
		firstname.appendChild(doc.createTextNode(u.getNom()));
		user.appendChild(firstname);

		// lastname 
		Element lastname = doc.createElement("lastname");
		lastname.appendChild(doc.createTextNode(u.getPrenom()));
		user.appendChild(lastname);
                
                // photo 
		Element photo = doc.createElement("photo");
		photo.appendChild(doc.createTextNode(u.getPhoto_profil()));
		user.appendChild(photo);

		// nickname elements
		Element username = doc.createElement("username");
		username.appendChild(doc.createTextNode(u.getUsername()));
		user.appendChild(username);

		// salary elements
		Element email = doc.createElement("email");
		email.appendChild(doc.createTextNode(u.getEmail()));
		user.appendChild(email);
                
                Element adresse = doc.createElement("adresse");
		adresse.appendChild(doc.createTextNode(u.getAdresse()));
		user.appendChild(adresse);
                
//                Attr num_tel = doc.createAttribute("num_tel");
//		num_tel.setValue((String.valueOf(u.getNum_tel())));
//		user.setAttributeNode(num_tel);
                
                Element num_tel = doc.createElement("num_tel");
		num_tel.appendChild(doc.createTextNode(String.valueOf(u.getNum_tel())));
		user.appendChild(num_tel);
                
//                Element mdp = doc.createElement("mot de passe");
//		mdp.appendChild(doc.createTextNode(u.getPassword()));
//		user.appendChild(mdp);

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
                String homepath = System.getProperty("user.dir");
                System.out.println(homepath);
		StreamResult result = new StreamResult(new File(homepath+"/file.xml"));
		// Output to console for testing
		StreamResult result2 = new StreamResult(System.out);

		transformer.transform(source, result);
//                transformer.transform(source, result2);
		System.out.println("File saved!");

	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
	}
        
    }
    

