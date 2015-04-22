import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Searcher {

	public boolean ValidateUser(String user_email, char[] user_password){
		
		boolean output = false;
		try{
			
			File fXmlFile = new File("../TaskManager/Data Files/Users.xml");
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
	
	/* El siguiente metodo se usa para instanciar elementos de clase project y clase task
	 * dependiendo de la informacion contenida en los archivos */
	
	public void LoadUserData(User user){
		try{
			List<String> PIDlist = new ArrayList<String>();
			List<List<Integer>> TIDlist = new ArrayList<List<Integer>>();
			
			/* Primero buscamos los proyectos asociados al usuario loggeado */
			File fXmlFile = new File("../TaskManager/Data Files/Users.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("User");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
				 
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
					
					if(user.getEmail().equals(eElement.getElementsByTagName("Email").item(0).getTextContent())){
						
						NodeList nList2 = doc.getElementsByTagName("Projects");
						Node nNode2 = nList2.item(temp);
						
						if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
							
							Element eElement2 = (Element) nNode2;
							
							for(int i=0; i<eElement2.getElementsByTagName("PID").getLength(); i++){
								
								PIDlist.add(eElement2.getElementsByTagName("PID").item(i).getTextContent());
								/* Ya tenemos los PID*/							
							}	
						}
					}
				}
			}
			
			/* Ahora buscamos la descripcion y el estado de cada proyecto */
			fXmlFile = new File("../TaskManager/Data Files/Projects.xml");
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
			
			doc.getDocumentElement().normalize();
			
			nList = doc.getElementsByTagName("Project");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
				 
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
					
					for(int i = 0; i<PIDlist.size(); i++){
						if(PIDlist.get(i).equals(eElement.getElementsByTagName("PID").item(0).getTextContent())){
							String auxDescription = eElement.getElementsByTagName("Description").item(0).getTextContent();
							State auxState;
							if(eElement.getElementsByTagName("State").item(0).getTextContent().toLowerCase().equals("closed"))
								auxState = State.CLOSED;
							if(eElement.getElementsByTagName("State").item(0).getTextContent().toLowerCase().equals("frozen"))
								auxState = State.FROZEN;
							else
								auxState = State.ACTIVE;
							/* Finalmente creamos el proyecto */
							Project p = new Project(Integer.parseInt(PIDlist.get(i)), auxDescription, auxState);
							Main.user.AddProject(p);
							System.out.println("PID="+PIDlist.get(i)+" Desc="+auxDescription+" State="+auxState.toString());
						}
					}
				}
			}
			
			/* Ahora debemos buscar las tareas asociadas a los proyectos encontrados */
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
