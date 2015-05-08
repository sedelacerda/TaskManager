import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
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
			List<List<String>> TIDlist = new ArrayList<List<String>>();
			
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
							/* Creamos el proyecto */
							Project p = new Project(Integer.parseInt(PIDlist.get(i)), auxDescription, auxState);
							Main.user.AddProject(p);
							
							/* Ahora rellenamos la lista TIDlist */
							NodeList nList2 = doc.getElementsByTagName("Tasks");
							Node nNode2 = nList2.item(temp);
							
							if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
								
								Element eElement2 = (Element) nNode2;
								List<String> auxTIDlist = new ArrayList<String>();
								
								/* Con el siguiente for recogemos todos los TID de un mismo proyecto */
								for(int j=0; j<eElement2.getElementsByTagName("TID").getLength(); j++){
									
									auxTIDlist.add(eElement2.getElementsByTagName("TID").item(j).getTextContent());
								}	
								
								/* Finalmente agregamos la lista de TIDs del proyecto a una lista que contiene todas estas listas */
								TIDlist.add(auxTIDlist);
							}
						}
					}
					
				}
			}
			
			/* Ahora debemos buscar las tareas asociadas a los proyectos encontrados */
			fXmlFile = new File("../TaskManager/Data Files/Tasks.xml");
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
			
			doc.getDocumentElement().normalize();
			
			nList = doc.getElementsByTagName("Task");
			
			for (int i = 0;i<TIDlist.size();i++){				//cada proyecto
				
				for (int j = 0;j<TIDlist.get(i).size();j++){	//cada tarea
					
					for (int temp = 0; temp < nList.getLength(); temp++) {
				 
						Node nNode = nList.item(temp);
						
						if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					
							Element eElement = (Element) nNode;
									
							if (eElement.getElementsByTagName("TID").item(0).getTextContent().equals(TIDlist.get(i).get(j))){
								
								String description = eElement.getElementsByTagName("Description").item(0).getTextContent();
								String context = eElement.getElementsByTagName("Context").item(0).getTextContent();
								State state;
								if(eElement.getElementsByTagName("State").item(0).getTextContent().toLowerCase().equals("closed"))
									state = State.CLOSED;
								if(eElement.getElementsByTagName("State").item(0).getTextContent().toLowerCase().equals("frozen"))
									state = State.FROZEN;
								else
									state = State.ACTIVE;
								DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
								Date deadline = new Date();
								deadline = df.parse(eElement.getElementsByTagName("Deadline").item(0).getTextContent());
								Task task = new Task(Integer.parseInt(TIDlist.get(i).get(j)),description, context, user.getProjectByID(Integer.parseInt(PIDlist.get(i))), deadline, state);
								user.getProjectByID(Integer.parseInt(PIDlist.get(i))).AddTask(task);								
							}
						}
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
