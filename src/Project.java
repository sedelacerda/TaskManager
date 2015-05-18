import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Project {
	
	private int PID;
	private String description;
	private State currentState;
	private ArrayList<Task> tasks = new ArrayList<Task>();
	private ArrayList<User> users = new ArrayList<User>();
	private HashMap<Integer,Task> taskByID = new HashMap<Integer,Task>();
	
	public Project(int project_id, String project_description, State project_state) {
		this.PID = project_id;
		this.description = project_description;
		this.currentState = project_state;
		tasks = new ArrayList<Task>();
		users = new ArrayList<User>();
	}
	
	public void AddTask(Task new_task) {
		tasks.add(new_task);
	}
	
	public void RemoveTask(int task_id) {
		tasks.remove(taskByID.get(task_id));
	}
	
	public State getState() {
		return currentState;
	}
	
	public void setState(State new_state) {
		currentState = new_state;
	}
	
	public int getPID(){
		return PID;
	}
	
	public void setDescription(String new_description){
		this.description = new_description;
	}
	
	public String getDescription(){
		return description;
	}
	
	public List<Task> getTasks(){
		return tasks;
	}
	
	public List<User> getUsers(){
		return users;
	}
	
	public void AddUser(User new_user){
		users.add(new_user);
	}
	
	public void RemoveUser(User remove_user){
		users.remove(remove_user);
	}

}
