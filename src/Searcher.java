import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class Searcher {

	/* Las siguientes 3 colecciones contendran toda la data que hay en la base de datos */
	ArrayList<ArrayList<ArrayList<String>>> Users = new ArrayList<ArrayList<ArrayList<String>>>();
	ArrayList<ArrayList<ArrayList<String>>> Projects = new ArrayList<ArrayList<ArrayList<String>>>();
	ArrayList<ArrayList<ArrayList<String>>> Tasks = new ArrayList<ArrayList<ArrayList<String>>>();
	ArrayList<ArrayList<ArrayList<String>>> Notifications = new ArrayList<ArrayList<ArrayList<String>>>();
	
	public void loadMainData(){
		loadUsers();
		loadProjects();
		loadTasks();
	}
	
	public void saveAllDataToXML(){
		saveUsersToXML();
		saveProjectsToXML();
		saveTasksToXML();
	}

	public void loadUsers(){
		
		Users = new ArrayList<ArrayList<ArrayList<String>>>();
		
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
					
					//Primero sacamos los PIDs del user			
					ArrayList<String> projects = new ArrayList<String>();
					NodeList nList2 = doc.getElementsByTagName("Projects");
					Node nNode2 = nList2.item(temp);
					
					if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
						
						Element eElement2 = (Element) nNode2;
						
						for(int i=0; i<eElement2.getElementsByTagName("PID").getLength(); i++){
							
							projects.add(eElement2.getElementsByTagName("PID").item(i).getTextContent());
							/* Ya tenemos los PID*/							
						}
					}
					
					//Ahora sacamos el email
					ArrayList<ArrayList<String>> userData = new ArrayList<ArrayList<String>>();
					userData.add(new ArrayList<String>(){{add(eElement.getElementsByTagName("Email").item(0).getTextContent());}});
					//Ahora la password
					userData.add(new ArrayList<String>(){{add(eElement.getElementsByTagName("Password").item(0).getTextContent());}});
					//Ahora le agregamos la lista de los proyectos
					userData.add(projects);
					
					//Finalmente agregamos el nuevo user al arraylist Users
					Users.add(userData);
				}
			}
			
		}
		
		catch(Exception e){
			e.printStackTrace();
		}		
	}

	public void loadProjects(){
		
		Projects = new ArrayList<ArrayList<ArrayList<String>>>();
		
		try{
			File fXmlFile = new File("../TaskManager/Data Files/Projects.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("Project");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
				 
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
					
					//Primero sacamos las tasks del project			
					ArrayList<String> tasks = new ArrayList<String>();
					NodeList nList2 = doc.getElementsByTagName("Tasks");
					Node nNode2 = nList2.item(temp);
					
					if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
						
						Element eElement2 = (Element) nNode2;
						
						for(int i=0; i<eElement2.getElementsByTagName("TID").getLength(); i++){
							
							tasks.add(eElement2.getElementsByTagName("TID").item(i).getTextContent());
							/* Ya tenemos las tasks*/							
						}
					}
					
					//Ahora sacamos el PID
					ArrayList<ArrayList<String>> projectData = new ArrayList<ArrayList<String>>();
					projectData.add(new ArrayList<String>(){{add(eElement.getElementsByTagName("PID").item(0).getTextContent());}});
					//Ahora sacamos la description
					projectData.add(new ArrayList<String>(){{add(eElement.getElementsByTagName("Description").item(0).getTextContent());}});
					//Ahora sacamos el state
					projectData.add(new ArrayList<String>(){{add(eElement.getElementsByTagName("State").item(0).getTextContent());}});
					
					//Ahora le agregamos la lista de las tasks
					projectData.add(tasks);
					
					//Finalmente agregamos el nuevo project al arraylist Projects
					Projects.add(projectData);
				}
			}
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void loadTasks(){
		
		Tasks = new ArrayList<ArrayList<ArrayList<String>>>();
		
		try{
			File fXmlFile = new File("../TaskManager/Data Files/Tasks.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("Task");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
				 
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
					
					//Primero sacamos los executors de la task		
					ArrayList<String> executors = new ArrayList<String>();
					NodeList nList2 = doc.getElementsByTagName("Executors");
					Node nNode2 = nList2.item(temp);
					
					if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
						
						Element eElement2 = (Element) nNode2;
						
						for(int i=0; i<eElement2.getElementsByTagName("Email").getLength(); i++){
							
							executors.add(eElement2.getElementsByTagName("Email").item(i).getTextContent());
							/* Ya tenemos los executors*/							
						}
					}
					
					//Ahora sacamos el TID
					ArrayList<ArrayList<String>> taskData = new ArrayList<ArrayList<String>>();
					taskData.add(new ArrayList<String>(){{add(eElement.getElementsByTagName("TID").item(0).getTextContent());}});
					//Ahora la description
					taskData.add(new ArrayList<String>(){{add(eElement.getElementsByTagName("Description").item(0).getTextContent());}});
					//Ahora el context
					taskData.add(new ArrayList<String>(){{add(eElement.getElementsByTagName("Context").item(0).getTextContent());}});
					//Ahora el state
					taskData.add(new ArrayList<String>(){{add(eElement.getElementsByTagName("State").item(0).getTextContent());}});
					//Ahora el deadline
					taskData.add(new ArrayList<String>(){{add(eElement.getElementsByTagName("Deadline").item(0).getTextContent());}});
					//Ahora el responsible
					taskData.add(new ArrayList<String>(){{add(eElement.getElementsByTagName("Responsible").item(0).getTextContent());}});

					//Ahora le agregamos la lista de los executors
					taskData.add(executors);
					
					//Finalmente agregamos la nueva task al arraylist Tasks
					Tasks.add(taskData);
				}
			}		
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void loadNotifications(){


		Notifications = new ArrayList<ArrayList<ArrayList<String>>>();
		System.out.println("Ëntró a LoadNotiication");
		
		try{
			File fXmlFile = new File("../TaskManager/Data Files/Notifications.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("User");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
				 
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
					
					//Primero sacamos las notificaciones		
					ArrayList<String> notificaciones = new ArrayList<String>();
					NodeList nList2 = doc.getElementsByTagName("Notificaciones");
					Node nNode2 = nList2.item(temp);
					
					if (nNode2.getNodeType() == Node.ELEMENT_NODE) {
						
						Element eElement2 = (Element) nNode2;
						
						for(int i=0; i<eElement2.getElementsByTagName("Notificacion").getLength(); i++){
							
							notificaciones.add(eElement2.getElementsByTagName("Notificacion").item(i).getTextContent());
							/* Ya tenemos las notificaciones*/							
						}
					}
					
					//Ahora sacamos el Email
					ArrayList<ArrayList<String>> notificationData = new ArrayList<ArrayList<String>>();
					notificationData.add(new ArrayList<String>(){{add(eElement.getElementsByTagName("Email").item(0).getTextContent());}});
					//Ahora sacamos la password
					notificationData.add(new ArrayList<String>(){{add(eElement.getElementsByTagName("Password").item(0).getTextContent());}});
				
					//Ahora le agregamos la lista de notificaicones
					notificationData.add(notificaciones);
					
					//Finalmente agregamos el nuevo project al arraylist Users
					Notifications.add(notificationData);
				
				}
			}
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void saveUsersToXML(){
		try{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			
			Element rootElement = doc.createElement("Users");
			doc.appendChild(rootElement);
			
			for(int i=0; i<Users.size(); i++){	//for para recorrer cada user
				Element elem2 = doc.createElement("User");
				
				Element elem3 = doc.createElement("Email");
				elem3.appendChild(doc.createTextNode(""+Users.get(i).get(0).get(0)));
				Element elem4 = doc.createElement("Password");
				elem4.appendChild(doc.createTextNode(""+Users.get(i).get(1).get(0)));
				
				Element elem5 = doc.createElement("Projects");
				
				for(int k=0; k<Users.get(i).get(2).size(); k++){
					Element elem6 = doc.createElement("PID");
					elem6.appendChild(doc.createTextNode(""+Users.get(i).get(2).get(k)));
					elem5.appendChild(elem6);
				}
				
				elem2.appendChild(elem3);		//agregamos email al user
				elem2.appendChild(elem4);		//agregamos password al user
				elem2.appendChild(elem5);		//agregamos projects al user
				rootElement.appendChild(elem2);	//agregamos user a users
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("../TaskManager/Data Files/Users.xml"));
			transformer.transform(source, result);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void saveProjectsToXML(){
		try{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			
			Element rootElement = doc.createElement("Projects");
			
			doc.appendChild(rootElement);
			
			for(int i=0; i<Projects.size(); i++){	//for para recorrer cada project
				Element elem2 = doc.createElement("Project");
				
				Element elem3 = doc.createElement("PID");
				elem3.appendChild(doc.createTextNode(""+Projects.get(i).get(0).get(0)));
				Element elem4 = doc.createElement("Description");
				elem4.appendChild(doc.createTextNode(""+Projects.get(i).get(1).get(0)));
				Element elem5 = doc.createElement("State");
				elem5.appendChild(doc.createTextNode(""+Projects.get(i).get(2).get(0)));
								
				Element elem6 = doc.createElement("Tasks");
				
				for(int k=0; k<Projects.get(i).get(3).size(); k++){	//for para recorrer las tasks
					Element elem7 = doc.createElement("TID");
					elem7.appendChild(doc.createTextNode(""+Projects.get(i).get(3).get(k)));
					elem6.appendChild(elem7);
				}
				
				elem2.appendChild(elem3);		//agregamos PID a Project
				elem2.appendChild(elem4);		//agregamos Description a Project
				elem2.appendChild(elem5);		//agregamos State a Project
				elem2.appendChild(elem6);		//agregamos Tasks a Project
				rootElement.appendChild(elem2);	//agregamos Project a Projects
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("../TaskManager/Data Files/Projects.xml"));
			transformer.transform(source, result);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void saveTasksToXML(){
		try{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			
			Element rootElement = doc.createElement("Tasks");
			
			doc.appendChild(rootElement);
			
			for(int i=0; i<Tasks.size(); i++){	//for para recorrer cada project
				Element elem2 = doc.createElement("Task");
				
				Element elem3 = doc.createElement("TID");
				elem3.appendChild(doc.createTextNode(""+Tasks.get(i).get(0).get(0)));
				Element elem4 = doc.createElement("Description");
				elem4.appendChild(doc.createTextNode(""+Tasks.get(i).get(1).get(0)));
				Element elem5 = doc.createElement("Context");
				elem5.appendChild(doc.createTextNode(""+Tasks.get(i).get(2).get(0)));
				Element elem6 = doc.createElement("State");
				elem6.appendChild(doc.createTextNode(""+Tasks.get(i).get(3).get(0)));
				Element elem7 = doc.createElement("Deadline");
				elem7.appendChild(doc.createTextNode(""+Tasks.get(i).get(4).get(0)));
				Element elem8 = doc.createElement("Responsible");
				elem8.appendChild(doc.createTextNode(""+Tasks.get(i).get(5).get(0)));
												
				Element elem9 = doc.createElement("Executors");
				
				for(int k=0; k<Tasks.get(i).get(6).size(); k++){	//for para recorrer los executors
					Element elem10 = doc.createElement("Email");
					elem10.appendChild(doc.createTextNode(""+Tasks.get(i).get(6).get(k)));
					elem9.appendChild(elem10);
				}
				
				elem2.appendChild(elem3);		//agregamos TID a Task
				elem2.appendChild(elem4);		//agregamos Description a Task
				elem2.appendChild(elem5);		//agregamos Context a Task
				elem2.appendChild(elem6);		//agregamos State a Task
				elem2.appendChild(elem7);		//agregamos Deadline a Task
				elem2.appendChild(elem8);		//agregamos Responsible a Task
				elem2.appendChild(elem9);		//agregamos Executors a Task
				rootElement.appendChild(elem2);	//agregamos Task a Tasks
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("../TaskManager/Data Files/Tasks.xml"));
			transformer.transform(source, result);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void saveNotificationsToXML(){
		try{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			
			Element rootElement = doc.createElement("Notifications");
			
			doc.appendChild(rootElement);
			
			for(int i=0; i<Notifications.size(); i++){	//for para recorrer cada project
				Element elem2 = doc.createElement("User");
				
				Element elem3 = doc.createElement("Email");
				elem3.appendChild(doc.createTextNode(""+Notifications.get(i).get(0).get(0)));
				Element elem4 = doc.createElement("Password");
				elem4.appendChild(doc.createTextNode(""+Notifications.get(i).get(1).get(0)));
				
								
				Element elem6 = doc.createElement("Notificaciones");
				
				for(int k=0; k<Notifications.get(i).get(2).size(); k++){	//for para recorrer las tasks
					Element elem7 = doc.createElement("Notificacion");
					elem7.appendChild(doc.createTextNode(""+Notifications.get(i).get(2).get(k)));
					elem6.appendChild(elem7);
				}
				
				elem2.appendChild(elem3);		//agregamos Mail
				elem2.appendChild(elem4);		//agregamos Password
				elem2.appendChild(elem6);		//agregamos notificaciones
				rootElement.appendChild(elem2);	//agregamos usuarios a Notifications
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("../TaskManager/Data Files/Notifications.xml"));
			transformer.transform(source, result);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean ValidateUser(String user_email, char[] user_password){
		
		boolean output = false;
		
		for(ArrayList<ArrayList<String>> userl : Users){
			if(userl.get(0).get(0).equals(user_email) && userl.get(1).get(0).equals(new String(user_password))){
				output = true;
				break;
			}
		}
		
		return output;
	}
	
	public boolean ValidateUser(String user_email){

		boolean output = false;
		
		for(ArrayList<ArrayList<String>> userl : Users){
			if(userl.get(0).get(0).equals(user_email)){
				output = true;
				break;
			}
		}
		
		return output;
	}
	
	/* El siguiente metodo se usa para instanciar elementos de clase project y clase task
	 * dependiendo de la informacion contenida en los archivos */
	public void LoadUserData(User user){
			
		for(ArrayList<ArrayList<String>> userl : Users){
			if(user.getEmail().equals(userl.get(0).get(0))){
				for(String pid : userl.get(2)){	//para cada pid
					for(ArrayList<ArrayList<String>> project : Projects){	//verificamos para cada project
						if(project.get(0).get(0).equals(pid)){
							//creamos un project
							Project proj = new Project(Integer.parseInt(project.get(0).get(0)), project.get(1).get(0), State.valueOf(project.get(2).get(0)));
							
							//ahora buscamos las tareas de este proyecto
							for(String tid : project.get(3)){				//verificamos para cada TID contenido en el project
								for(ArrayList<ArrayList<String>> task : Tasks){
									if(tid.equals(task.get(0).get(0))){		//vemos si coincide con el tid de alguna tarea
										//creamos una task y la agregamos a la lista de tasks del project									
										DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
										Date date = new Date();
										try{
											date = df.parse(task.get(4).get(0));
										}catch(ParseException e){}
										
										Task tas = new Task(Integer.parseInt(task.get(0).get(0)), task.get(1).get(0), task.get(2).get(0), State.valueOf(task.get(3).get(0)), date, proj);
										tas.setResponsible(new User(task.get(5).get(0),""));
										for(String exec : task.get(6)){
											tas.AddExecutor(new User(exec, ""));
										}
										proj.AddTask(tas);
									}
								}
							}
							//agregamos el project a la lista de projects del usuario
							Main.user.AddProject(proj);
						}
					}
				}
				break;	//solo puede calzar con un usuario
			}
		}
	}

	public void MarcarComoLeidaNotifications(){
		Main.searcher.loadNotifications();
		ArrayList<String> leido= new ArrayList<>();
		
		for (int i=0 ; i< Main.searcher.Notifications.size(); i++){
				if(Main.searcher.Notifications.get(i).get(0).get(0).equals(Main.user.getEmail())){
					for(int j = 0; j < Main.searcher.Notifications.get(i).get(2).size(); j++){
						if(Main.searcher.Notifications.get(i).get(2).get(j).contains(",Leido")){
						leido.add(Main.searcher.Notifications.get(i).get(2).get(j));
						}
						else{leido.add(Main.searcher.Notifications.get(i).get(2).get(j)+",Leido");
							
						}
					}
				
				}
			
			}
			
			System.out.println(leido);
			ArrayList<ArrayList<String>> usuario = new ArrayList<ArrayList<String>>();
			ArrayList<String> email = new ArrayList<String>();
			email.add(Main.user.getEmail());
			ArrayList<String> password= new ArrayList<String>();
			password.add(Main.user.getPassword());
			usuario.add(email);
			usuario.add(password);
			usuario.add(leido);
			for (int i=0 ; i< Main.searcher.Notifications.size(); i++){
				if(Main.searcher.Notifications.get(i).get(0).get(0).equals(Main.user.getEmail())){
				
					Main.searcher.Notifications.remove(i);
					Main.searcher.Notifications.add(i, usuario);
				
				}
			
			}
			Main.searcher.saveNotificationsToXML();	
			Main.searcher.loadNotifications();
	}
		
	public void addNewUser(User user){
		ArrayList<ArrayList<String>> newUser = new ArrayList<ArrayList<String>>();
		ArrayList<String> pids = new ArrayList<String>();
		
		for(Project proj : user.getProjects()){
			pids.add(""+proj.getPID());
		}
		
		newUser.add(new ArrayList<String>(){{add(""+user.getEmail());}});
		newUser.add(new ArrayList<String>(){{add(""+user.getPassword());}});
		newUser.add(pids);
		
		Users.add(newUser);
				
		saveUsersToXML();	//actualizamos la base
		loadUsers();
		
		//Ahora lo agregamos al txt de notificaciones.
				Main.searcher.loadNotifications();
				ArrayList<ArrayList<String>> usuario = new ArrayList<ArrayList<String>>();
				ArrayList<String> notificaciones = new ArrayList<String>();
				ArrayList<String> email = new ArrayList<String>();
				email.add(user.getEmail());
				ArrayList<String> password= new ArrayList<String>();
				password.add(user.getPassword());
				notificaciones.add(" ");
				usuario.add(email);
				usuario.add(password);
				usuario.add(notificaciones);
				Main.searcher.Notifications.add(usuario);
				Main.searcher.saveNotificationsToXML();
				String clave = "moktiiopqxxydcii";
				String mensaje = "El equipo de TaskManager le da la Bienvenida!!\nSu contraseña es "+user.getPassword()  ;
				String asunto = "Bienvenido a esta nueva experiencia";
				Main.mail = new Email("TaskManager.Adm@gmail.com", clave, user.getEmail(), asunto , mensaje);
				
				if(Main.mail.sendMail()){
						JOptionPane.showMessageDialog(null, "se le ha enviado un email con los datos de su cuenta");
					}
					else{
						JOptionPane.showMessageDialog(null, "Direccion de correo inválida");
						
					}
	}
	
	public void addNewProject(Project project){
		ArrayList<ArrayList<String>> newProject = new ArrayList<ArrayList<String>>();
		ArrayList<String> tids = new ArrayList<String>();
		
		for(Task tas : project.getTasks()){
			tids.add(""+tas.getTID());
		}
		
		newProject.add(new ArrayList<String>(){{add(""+project.getPID());}});
		newProject.add(new ArrayList<String>(){{add(project.getDescription());}});
		newProject.add(new ArrayList<String>(){{add(project.getState().toString().toUpperCase());}});
		newProject.add(tids);
		
		Projects.add(newProject);
		
		saveProjectsToXML();	//actualizamos la base
		loadProjects();
	
	}
	
	/**El siguiente metodo guarda la nueva tarea en el archivo tasks y ademas guarda una referencia
	 * de esta en el project entregado
	 * @param proj
	 * @param task
	 */
	public void addNewTask(Project proj, Task task){
		ArrayList<ArrayList<String>> newTask = new ArrayList<ArrayList<String>>();
		ArrayList<String> executors = new ArrayList<String>();
		
		for(User exec : task.getExecutors()){
			executors.add(exec.getEmail());
		}
		
		newTask.add(new ArrayList<String>(){{add(""+task.getTID());}});
		newTask.add(new ArrayList<String>(){{add(task.getDescription());}});
		newTask.add(new ArrayList<String>(){{add(task.getContext());}});
		newTask.add(new ArrayList<String>(){{add(task.getState().toString().toUpperCase());}});
		newTask.add(new ArrayList<String>(){{add(task.getDeadline().toString());}});
		newTask.add(new ArrayList<String>(){{add(task.getResponsible().getEmail());}});
		newTask.add(executors);
		
		Tasks.add(newTask);
		saveTasksToXML();
		loadTasks();
		
		
		updateProject(proj);
		
	}
	
	/**El siguiente metodo guarda la tarea en si en el xml de tasks, solo la tarea sin la referencia al project */
	private void addNewTask(Task task){
		ArrayList<ArrayList<String>> newTask = new ArrayList<ArrayList<String>>();
		ArrayList<String> executors = new ArrayList<String>();
		
		for(User exec : task.getExecutors()){
			executors.add(exec.getEmail());
		}
		
		newTask.add(new ArrayList<String>(){{add(""+task.getTID());}});
		newTask.add(new ArrayList<String>(){{add(task.getDescription());}});
		newTask.add(new ArrayList<String>(){{add(task.getContext());}});
		newTask.add(new ArrayList<String>(){{add(task.getState().toString().toUpperCase());}});
		newTask.add(new ArrayList<String>(){{add(task.getDeadline().toString());}});
		newTask.add(new ArrayList<String>(){{add(task.getResponsible().getEmail());}});
		newTask.add(executors);
		
		Tasks.add(newTask);
		
		saveTasksToXML();
		loadTasks();
	}
	
	public void removeProject(Project project){
		
		for(int i=0; i<Projects.size(); i++){
			if(Projects.get(i).get(0).get(0).equals(""+project.getPID())){
				Projects.remove(i);
			}
		}
		
		saveProjectsToXML();
		loadProjects();
	}

	public void removeTask(Task task){
		for(int i=0; i<Tasks.size(); i++){
			if(Tasks.get(i).get(0).get(0).equals(""+task.getTID())){
				Tasks.remove(i);
			}
		}
		
		saveTasksToXML();
		loadTasks();
	}

	public void updateProject(Project project){	//no se pueden modificar los PID
		
		removeProject(project);	//lo borramos
		addNewProject(project);	//lo volvemos a crear
	}
	
	public void updateTask(Task task){
		removeTask(task);	//la borramos
		addNewTask(task);	//la volvemos a crear
	}
	
	public void addNewNotification(String notificacion, ArrayList<String> Email){
		Main.searcher.loadNotifications();
		ArrayList<String> auxiliar = new ArrayList<>();

		for(int i = 0; i < Main.searcher.Notifications.size(); i++){
			for(int j = 0; j < Email.size(); j++){
				if(Main.searcher.Notifications.get(i).get(0).get(0).equals(Email.get(j))){
					Main.searcher.Notifications.get(i).get(2).add(notificacion);
				}
			}
		}
		
		
	
		saveNotificationsToXML();
		loadNotifications();
}
	public ArrayList<ArrayList<ArrayList<String>>> getAllUsers(){
		return Users;
	}
}
