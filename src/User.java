import java.util.*;
import java.util.List;

public class User {
	
	private String userEmail;
	private String userPassword;
	private List<Project> projects = new ArrayList<Project>();
	private HashMap<Integer,Project> projectByID = new HashMap<Integer,Project>();
	private boolean isLoggedIn;
	
	/* Constructor */
	public User(String user_email, String user_password){
		this.userEmail = "" + user_email;
		this.userPassword = "" + user_password;
	}
	
	/* Public methods */
	public String getEmail() {
		return userEmail;
	}
	
	public String getPassword() {
		return userPassword;
	}
	
	public void LogIn() {
		/* Cuando se hace log in se debe cargar todos los datos del usuario adentro del programa */
		Main.searcher.LoadUserData(this);
	}
	
	public void LogOut() {
		
	}
	
	public void AddProject(Project new_project) {
		projects.add(new_project);
		projectByID.put(new_project.getPID(), new_project);
	}
	
	public void CreateNewTask() {
		
	}
	
	public List<Project> getProjects() {
		return projects;
	}
	
	public boolean isLogged() {
		return isLoggedIn;
	}
	
	public Project getProjectByID(int project_id){
		return projectByID.get(project_id);
	}

}
