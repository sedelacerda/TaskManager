Al iniciar el programa, se debe ingresar con usuario: sedelacerda@uc.cl 
y password: qwerty


EL programa tiene las siguientes clases:
-Task: Clase de las tareas, donde estan los datos y métodos de las tareas. Tiene distintos constructores para darle 
felxibilidad a las tareas.
-Proyect: Clase de los proyectos. A cada proyecto se le asignan tareas con el metodo AddTask()
-User: Clase del usuario, la que recive un mail y un password para agregarlo a la base de datos y que exista el usuario.
-State: Un enum. Clase auxiliar para darle los estados a las tareas. 
-Main: clase main
-Searcher: Busca al usuario en la base de datos y lo valida o rechaza. También asocia los proyectos de cada usario a sus 
tareas.
-Userinteface: Interfaz de usuario, con la que el usuario maneja la aplicación

ESQUELETO:

public class Main {
	public static User user;
	public static Searcher searcher = new Searcher();//conversar esto, tal vez no es la mejor opcion
	public static UserInterface ui;
	public static void main(String[] args) {
		ui = new UserInterface();
		ui.ShowLoginScreen();
						}
	
	public static void LogInUser(String user_email, String user_password) {}

		}



public class Searcher {

	public boolean ValidateUser(String user_email, char[] user_password){}
	
	/* El siguiente metodo se usa para instanciar elementos de clase project y clase task
	 * dependiendo de la informacion contenida en los archivos */
	
	public void LoadUserData(User user){}
			
}




public static enum State {
	 ACTIVE, CLOSED, FROZEN 
}



public class User {

	private String userEmail, userPassword;
	private List<Project> projects;
	private List<Task> tasks;
	private HashMap<int, Project> projectByID;
	private HashMap<int, Task> taskByID;
	private boolean isLoggedIn;

	public User (String user_email, String user_password) { }	//constructor

	public String getEmail ( ) { }

	public String getPassword ( ) { }

	public void LogIn ( ) { (revisar que no haya ningun usuario logeado, cargar data en parametros) }

	public void LogOut ( ) { (guardar data y despues borrar de los parametros) }

	public void createNewProject ( ) {}

	public void createNewTask ( ) {} //agrega la tarea a un proyecto miscelaneo

	public List getProjects ( ) { }

	public List getTasks ( ) { }

	public boolean isLoggedIn ( ) { }

	private void LoadData () { }

			}


public class Project { 

	private int PID;
	private State currentState;
	
	public Project () { }

	public void AddTask () { }

	public void DeleteTask (int task_ID) { }

	public State getState ( ) { }

	public void setState (State new_state) { }

			}


public class Task {

	private int TID;
	private Project project;
	private Date deadline;
	private State currentState;
	private String description;
	private String context;
	private User currentResponsible;
	private List<User> currentExecutors;

	//el programa debe ser flexible en cuanto a los datos necesarios para crear una tarea

	public Task (string task_description, string task_context, Project tasks_project, Date task_deadline) { }

	public Task (string task_description, string task_context, Project tasks_project) { }

	public Task (string task_description, string task_context,Date Task_deadline) { }

	public Task (string task_description, string task_context) { }

	public void setProject (Project tasks_project) { }

	public Project getProject ( ) { }

	public void setDeadline (Date task_deadline) { }

	public Date getDeadline ( ) { }

	public void setState (State new_state) { }

	public State getState ( ) { }
	
	public void setDescription (string new_description) { }

	public string getDescription ( ) { }

	public void setContext (string new_context) { }

	public string getContext ( ) { }
	
	public void setResponsible (User new_responsible) { }

	public User getResponsible ( ) { }     
	
	public void addExecutor (User new_executor) { }

	public void removeExecutor (User new_executor) { }

		}



