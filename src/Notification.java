import java.util.ArrayList;

public class Notification {
	
	private User creator;
	private ArrayList<User> receptors;
	private String message;
	
	public Notification(User creator_user, ArrayList<User> receptor_user, String message){
		this.creator = creator_user;
		this.receptors = receptor_user;
		this.message = message;
	}
	
	public Notification(ArrayList<User> receptor_user, String message){
		this.creator = new User("","");
		this.receptors = receptor_user;
		this.message = message;
	}
	
	public User getCreator(){
		return creator;
	}
	
	public ArrayList<User> getReceptors(){
		return receptors;
	}
	
	public String getMessage(){
		return message;
	}

}
