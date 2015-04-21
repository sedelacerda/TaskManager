import java.util.HashMap;
import java.util.List;


public class Project {
	
	private int PID;
	private String description;
	private State currentState;
	private List<Task> tasks;
	private HashMap<Integer,Task> taskByID;
	
	public Project(String project_description) {
		this.description = project_description;
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

}
