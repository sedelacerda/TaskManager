import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Project {
	
	private int PID;
	private String description;
	private State currentState;
	private List<Task> tasks = new ArrayList<Task>();
	private HashMap<Integer,Task> taskByID;
	
	public Project(int project_id, String project_description, State project_state) {
		this.PID = project_id;
		this.description = project_description;
		this.currentState = project_state;
	}
	
	public void AddTask(Task new_task) {
		
	}
	
	public void RemoveTask(int task_id) {
		
	}
	
	public State getState() {
		return currentState;
	}
	
	public void setState(State new_state) {
		
	}
	
	public int getPID(){
		return PID;
	}

}
