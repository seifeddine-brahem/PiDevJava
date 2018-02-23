/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.File;

/**
 *
 * @author user
 */
public class DeleteXmlFile {

    public DeleteXmlFile() {
            	try{
                    String homepath = System.getProperty("user.dir");
    		File file = new File(homepath+"/file.xml");

    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}

                }catch(Exception e){

    		e.printStackTrace();

                }
    }
    
    
}
