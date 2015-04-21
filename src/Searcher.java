import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Searcher {

	public boolean ValidateUser(String user_email, char[] user_password){
		
		boolean output = false;
		try{
			
			File fXmlFile = new File("../TaskManager/Data Files/Users.xml");
			System.out.println(fXmlFile.getCanonicalPath());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("User");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
				 
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
					String email = eElement.getElementsByTagName("Email").item(0).getTextContent();
					String password = eElement.getElementsByTagName("Password").item(0).getTextContent();
					String passwordIn = new String(user_password);
					
					if(user_email.equals(email) && passwordIn.equals(password)){
						output = true;
						break;
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return output;
	}
}
