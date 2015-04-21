import java.util.*;

public class User {
	
	private String userEmail;
	private String userPassword;
	private List<Project> projects;
	private HashMap<Integer,Project> projectByID;
	private boolean isLoggedIn;
	
	/* Constructor */
	public User(String user_email, String user_password){
		
	}
	
	/* Public methods */
	public String getEmail() {
		return userEmail;
	}
	
	public String getPassword() {
		return userPassword;
	}
	
	public void LogIn() {
		
	}
	
	public void LogOut() {
		
	}
	
	public void CreateNewProject() {
		
	}
	
	public void CreateNewTask() {
		
	}
	
	public List<Project> getProjects() {
		return projects;
	}
	
	public boolean isLogged() {
		return isLoggedIn;
	}
	
	/* Private methods */
	private void LoadData() {
		
	}

}
