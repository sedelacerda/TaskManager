import java.util.ArrayList;


public class Main {

	public static User user;
	public static Searcher searcher = new Searcher();	//conversar esto, tal vez no es la mejor opcion
	public static UserInterface ui;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		searcher.loadMainData();
		
		ui = new UserInterface();
		ui.ShowLoginScreen();
		LogInUser("jmleighton@uc.cl","098765");
	}
	
	public static void LogInUser(String user_email, String user_password) {
		user = new User(user_email, user_password);
		
		user.LogIn();
		ui.ShowHome();
	}

}
