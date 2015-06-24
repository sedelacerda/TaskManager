import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.*;
import java.text.DateFormat;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class UserInterface extends JFrame implements ActionListener, ListSelectionListener, KeyListener{
	//Vista LogIn
	JLabel LB_bienvenido;
	JLabel LB_email;
	JTextField TF_email;
	JLabel LB_password;
	JPasswordField PF_password;
	JButton BT_login;
	JButton BT_signUp;
	JLabel LB_emailSU;
	JTextField TF_emailSU;
	JLabel LB_passwordSU;
	JPasswordField PF_passwordSU;
	JButton BT_signUpSU;
	JLabel LB_incorrect_userSU;
	JLabel LB_userDoneSU;
	JButton BT_goToSignInScreen;
	JLabel LB_incorrect_password;
	JButton BT_LogOut;
	JButton BT_AddTask;
	//Menu de arriba
	JMenuBar MB_menu = new JMenuBar();
	JMenu MN_vistas;
	JMenuItem MI_vista1;
	JMenuItem MI_vista2;
	JButton BT_AddProject;
	JButton BT_RmvProject;
	JButton BT_notifications;
	//Home
	
	//Tareas por proyecto y proyectos por usuario
	GridBagConstraints gbc;
	JList LS_projects;
	JScrollPane SP_projects;
	GridBagLayout grid ;
	JTable TB_tasks ;
	JScrollPane SP_tasks ;
	int vista = 0;
	
	JButton BT_CreateNewTask;
	Project CurrentProject;
		
	public UserInterface() {
		super("Task Manager");
	}
	
	public void ShowLoginScreen(){
		
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		
		grid = new GridBagLayout();
		gbc = new GridBagConstraints();
		setLayout(grid);
		
		LB_bienvenido = new JLabel("Bienvenido");
		LB_bienvenido.setFont(new Font("Serif", Font.PLAIN, 25));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(LB_bienvenido,gbc);
		
		LB_email = new JLabel("Ingresar Email: ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		add(LB_email,gbc);
		
		TF_email = new JTextField(30);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		add(TF_email,gbc);
		TF_email.addKeyListener(this);
		
		LB_password = new JLabel("Ingresar Password: ");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		add(LB_password,gbc);
		
		PF_password = new JPasswordField(30);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		add(PF_password,gbc);
		PF_password.addKeyListener(this);

		BT_login = new JButton("Iniciar Sesion");
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		add(BT_login,gbc);
		
		BT_login.addActionListener(this);
		
		LB_incorrect_password = new JLabel("*Usuario y/o contraseña inválidos, intente de nuevo.");
		LB_incorrect_password.setForeground(Color.LIGHT_GRAY);
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		add(LB_incorrect_password,gbc);
		
		BT_signUp = new JButton("Registrarse");
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		add(BT_signUp,gbc);
		
		BT_signUp.addActionListener(this);
		
		setVisible(true);
	}
	
	public void ShowHome(){
		LimpiarVista();
		
		getContentPane().setBackground(Color.LIGHT_GRAY);
		
		// Menu		
		cargarMenuSuperior(true);
		
		//Home
		JLabel LB_Home= new JLabel("HOME");
		LB_Home.setFont(new Font("Serif", Font.ITALIC, 100));
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		LB_Home.setAlignmentX(TOP_ALIGNMENT);
		add(LB_Home,gbc);
		
		JLabel Bienvenida= new JLabel("Bienvenido "+ Main.user.getEmail());
		LB_Home.setFont(new Font("Serif", Font.ITALIC, 60));
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		LB_Home.setAlignmentX(TOP_ALIGNMENT);
		add(Bienvenida,gbc);
		
		setVisible(true);	
	}
	
	public void LimpiarVista(){
	    MB_menu.removeAll();
		getContentPane().removeAll();
		revalidate();
		repaint();
	}
	
	public void cargarMenuSuperior(boolean isHome){
		
		gbc.gridx = 3;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		setJMenuBar(MB_menu);
		MN_vistas = new JMenu("Vistas");
		MB_menu.add(MN_vistas);
		MI_vista1 = new JMenuItem("Ver tareas por proyecto");
		MI_vista1.addActionListener(this);
		MN_vistas.add(MI_vista1);
		MI_vista2 = new JMenuItem("Ver usuarios por proyecto");
		MI_vista2.addActionListener(this);
		MN_vistas.add(MI_vista2);	
		BT_notifications = new JButton("Notificaciones");
		BT_notifications.addActionListener(this);
		add(BT_notifications,gbc);
		MB_menu.add(BT_notifications,-1);
		BT_LogOut = new JButton("Cerrar Sesion");
		add(BT_LogOut,gbc);
		MB_menu.add(BT_LogOut, -1);
		BT_LogOut.addActionListener(this);
		
		if(isHome==false){
			BT_AddTask = new JButton("Agregar/eliminar/modificar Tarea");
			add(BT_AddTask,gbc);
			MB_menu.add(BT_AddTask,1);
			BT_AddTask.addActionListener(this);
			BT_AddTask.setEnabled(false);
			BT_AddProject = new JButton("Agregar projecto");
			add(BT_AddProject,gbc);
			MB_menu.add(BT_AddProject,3);
			BT_AddProject.addActionListener(this);
			BT_RmvProject = new JButton("Eliminar projecto");
			add(BT_RmvProject,gbc);
			MB_menu.add(BT_RmvProject,3);
			BT_RmvProject.addActionListener(this);
		}
		
	}
	
	public void ShowTasksByProjectScreen(List<Project> userProjects) {

		vista = 0;
		/* Primero borramos todos los elementos de la vista de login */
		LimpiarVista();		
		
		/* Ahora cargamos los elementos de esta vista */
		
		/* Primero cargamos el menu superior */
		getContentPane().setBackground(Color.LIGHT_GRAY);
		// Menu
		cargarMenuSuperior(false);
		
		List<Project> projects = Main.user.getProjects();
		String[] projectsDescription = new String[projects.size()];
		
		for(int i=0; i<projects.size();i++){
			projectsDescription[i] = ""+projects.get(i).getDescription();
		}		
		
		LS_projects = new JList(projectsDescription);
		LS_projects.addListSelectionListener(this);
		SP_projects = new JScrollPane(LS_projects);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.insets = new Insets(10,10,10,0);//arriba,izquierda,abajo,derecha
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.EAST;
		add(SP_projects,gbc);				
		
		TB_tasks = new JTable();
		SP_tasks = new JScrollPane(TB_tasks);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.insets = new Insets(10,10,10,10);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.WEST;
		add(SP_tasks,gbc);
		
		setVisible(true);		
	}
  
	public void ShowUsersByProjectScreen(List<Project> userProjects) {
		   	
		vista = 1;
		
		/* Primero borramos todos los elementos de la vista de login */
		LimpiarVista();		
		
		/*Ahora cargamos los elementos de esta vista */
		
		/* Primero cargamos el menu superior */
		getContentPane().setBackground(Color.LIGHT_GRAY);
		// Menu
		cargarMenuSuperior(false);
		
		List<Project> projects = Main.user.getProjects();
		String[] projectsDescription = new String[projects.size()];
		
		for(int i=0; i<projects.size();i++){
			projectsDescription[i] = ""+projects.get(i).getDescription();
		}	
		
		LS_projects = new JList(projectsDescription);
		LS_projects.addListSelectionListener(this);
		SP_projects = new JScrollPane(LS_projects);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.insets = new Insets(10,10,10,0);//arriba,izquierda,abajo,derecha
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.EAST;
		add(SP_projects,gbc);
				
		
		TB_tasks = new JTable();
		SP_tasks = new JScrollPane(TB_tasks);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.insets = new Insets(10,10,10,10);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.WEST;
		add(SP_tasks,gbc);
		
		setVisible(true);
		
	}
	
	public void showNotifications(){
		Main.searcher.loadNotifications();
		NotificationsWindow nt = new NotificationsWindow();
		nt.show();		
	}
	
	public void ShowSignUpScreen(){
		
		LB_emailSU = new JLabel("Ingresar Email");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		add(LB_emailSU,gbc);
				
		TF_emailSU = new JTextField(30);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		add(TF_emailSU,gbc);
		TF_emailSU.addKeyListener(this);
		
		LB_passwordSU = new JLabel("Ingresar Password: ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		add(LB_passwordSU,gbc);

		PF_passwordSU = new JPasswordField(30);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		add(PF_passwordSU,gbc);
		PF_passwordSU.addKeyListener(this);

		BT_signUpSU = new JButton("Registrarse");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		add(BT_signUpSU,gbc);
		
		BT_signUpSU.addActionListener(this);
		
		LB_incorrect_userSU = new JLabel("*Usuario ya existe o contraseña ingresada posee menos de 4 digitos, reintente.");
				
		LB_userDoneSU = new JLabel("Felicitaciones! El usuario ha sido creado correctamente.");
		
		BT_goToSignInScreen = new JButton("Ir al Inicio de Sesión");
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		add(BT_goToSignInScreen,gbc);
				
		BT_goToSignInScreen.addActionListener(this);		
		
		setVisible(true);
	}
    
    public void addNewTaskToGUI(Task task){
		CurrentProject= Main.user.getProjects().get(LS_projects.getSelectedIndex());
		
		Main.user.getProjects().get(LS_projects.getSelectedIndex()).AddTask(task);			
		Main.searcher.addNewTask(CurrentProject, task);
		ShowTasksByProjectScreen(Main.user.getProjects());
    }
    
    public void addNewProjectToGUI(Project project){	
		Main.user.getProjects().add(project);
		Main.searcher.addNewProject(project);
		ShowTasksByProjectScreen(Main.user.getProjects());
    }

	public void actionPerformed(ActionEvent e) {
		
		/* Handler del boton de log in */
		if(e.getSource() == BT_login) {
			LB_incorrect_password.setForeground(Color.LIGHT_GRAY);
			revalidate();
			repaint();
			
			/* Si el usuario y contraseña son correctos entonces logueamos al usuario */
			if(Main.searcher.ValidateUser(TF_email.getText(), PF_password.getPassword())){
				Main.LogInUser(TF_email.getText(), new String(PF_password.getPassword()));
			}
			
			/* Si el usuario y/o contraseña son incorrectos entonces se muestra un mensaje en pantalla */
			else{
				LB_incorrect_password.setForeground(Color.red);
				setVisible(true);
			}
		}
		
		/* Handler del boton de signup */
		if(e.getSource() == BT_signUp) {
			LimpiarVista();
			ShowSignUpScreen();
		}
			
		/* Handler del boton de signUp de la vista de registro */
		if(e.getSource() == BT_signUpSU){
			remove(LB_incorrect_userSU);
			revalidate();
			repaint();
			
			String pass = new String(PF_passwordSU.getPassword());
			
			/* Si el usuario no esta ocupado y la contraseña tiene mas de 3 caracteres 
			 * entonces se crea en la BDD y se muestra un mensaje y un boton para ir a loginScreen */
			if(Main.searcher.ValidateUser(TF_emailSU.getText()) == false && pass.length()>3){
				Main.searcher.addNewUser(new User(TF_emailSU.getText(), pass));
				
				LimpiarVista();
				grid = new GridBagLayout();
				gbc = new GridBagConstraints();
				setLayout(grid);
				
				gbc.gridx = 0;
				gbc.gridy = 0;
				gbc.gridwidth = 2;
				gbc.gridheight = 1;
				gbc.weightx = 0.0;
				gbc.weighty = 0.0;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				gbc.anchor = GridBagConstraints.WEST;
				add(LB_userDoneSU, gbc);
				
				gbc.gridx = 0;
				gbc.gridy = 1;
				gbc.gridwidth = 2;
				gbc.gridheight = 1;
				gbc.weightx = 0.0;
				gbc.weighty = 0.0;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				gbc.anchor = GridBagConstraints.CENTER;
				add(BT_goToSignInScreen, gbc);
				
				setVisible(true);
			}
			
			/* Si el usuario ya existe o la contraseña tiene menos de 4 caracteres entonces
			 * se muestra un mensaje */
			else{
				LB_incorrect_userSU.setForeground(Color.red);
				gbc.gridx = 0;
				gbc.gridy = 5;
				gbc.gridwidth = 2;
				gbc.gridheight = 1;
				gbc.weightx = 0.0;
				gbc.weighty = 0.0;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				gbc.anchor = GridBagConstraints.WEST;
				
				add(LB_incorrect_userSU,gbc);
				setVisible(true);
			}
		}
		
		if(e.getSource() == BT_goToSignInScreen){
			LimpiarVista();
			ShowLoginScreen();
		}
		
		/* Handler del primer item de la pestaña vistas del menu superior */
		if(e.getSource() == MI_vista1) {
			ShowTasksByProjectScreen(Main.user.getProjects());
		}
		
		/* Handler del segundo item de la pestaña vistas del menu superior */
		if(e.getSource() == MI_vista2) {
			ShowUsersByProjectScreen(Main.user.getProjects());
			
		}	
		
		if(e.getSource()==BT_AddTask){
			TaskEditor te = new TaskEditor();
			te.show();
			//ShowAddTaskScreen();
		}
		
		if(e.getSource()==BT_AddProject){
			AddProjectWindow pw = new AddProjectWindow();
			pw.show();			
		}
		
		if(e.getSource()==BT_RmvProject){
			Project p1 = Main.user.getProjects().get(LS_projects.getSelectedIndex());
			Main.user.getProjects().remove(p1);
			Main.searcher.removeProject(p1);
			ShowTasksByProjectScreen(Main.user.getProjects());		
		}	
				
		if(e.getSource()==BT_LogOut){
			LimpiarVista();
			ShowLoginScreen();
		}
		if(e.getSource()==BT_notifications){
			showNotifications();
		}
	}
	
	/* Handler para el JList de los proyectos */
	public void valueChanged(ListSelectionEvent e){
		if (vista == 0)
		{
			if(e.getSource() == LS_projects){
				BT_AddTask.setEnabled(true);
				String[] columnNames = {"Description", "Context", "State", "Deadline", "TID"};
				int ntasks = Main.user.getProjects().get(LS_projects.getSelectedIndex()).getTasks().size();
				Object[][] data = new Object[ntasks][];
				
				DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
				
				for(int i = 0;i<ntasks;i++){
					data[i] = new Object[5];
					data[i][0] = Main.user.getProjects().get(LS_projects.getSelectedIndex()).getTasks().get(i).getDescription();
					data[i][1] = Main.user.getProjects().get(LS_projects.getSelectedIndex()).getTasks().get(i).getContext();
					data[i][2] = Main.user.getProjects().get(LS_projects.getSelectedIndex()).getTasks().get(i).getState();
					data[i][3] = df.format(Main.user.getProjects().get(LS_projects.getSelectedIndex()).getTasks().get(i).getDeadline());
					data[i][4] = Main.user.getProjects().get(LS_projects.getSelectedIndex()).getTasks().get(i).getTID();
				}
			
				TB_tasks = new JTable(data, columnNames);
				SP_tasks = new JScrollPane(TB_tasks);
				gbc.gridx = 1;
				gbc.gridy = 0;
				gbc.gridwidth = 1;
				gbc.gridheight = 1;
				gbc.weightx = 1.0;
				gbc.weighty = 1.0;
				gbc.insets = new Insets(10,10,10,10);
				gbc.fill = GridBagConstraints.BOTH;
				gbc.anchor = GridBagConstraints.WEST;
				add(SP_tasks,gbc);
			
				setVisible(true);
				}	
			}
		if (vista == 1)
		{
			int datos = 0;
			int nTasks = Main.user.getProjects().get(LS_projects.getSelectedIndex()).getTasks().size();
			for(int i = 0;i<nTasks;i++){		
				for(int o = 0;o< Main.user.getProjects().get(LS_projects.getSelectedIndex()).getTasks().get(i).getNumberExecutors();o++)
				{
					datos++;
				}
			}
			
			if(e.getSource() == LS_projects){
				BT_AddTask.setEnabled(true);
				String[] columnNames = {"Tarea","Email Responsable", "Email Ejecutor"};
				Object[][] data = new Object[datos][];		
				
				int aux = 0;
				
				for(int i = 0;i<nTasks;i++){	
					for(int o = 0;o< Main.user.getProjects().get(LS_projects.getSelectedIndex()).getTasks().get(i).getNumberExecutors();o++)
					{	
						data[aux] = new Object[3];
						data[aux][0] = "";
						data[aux][1] = "";
						data[aux][2] = "";
						aux++;
					}
				}
				
				aux = 0;
				
				for(int i = 0;i<Main.user.getProjects().get(LS_projects.getSelectedIndex()).getTasks().size();i++){
					data[aux][0] = Main.user.getProjects().get(LS_projects.getSelectedIndex()).getTasks().get(i).getDescription();	
					data[aux][1] = Main.user.getProjects().get(LS_projects.getSelectedIndex()).getTasks().get(i).getResponsible().getEmail();	
					for(int o = 0;o< Main.user.getProjects().get(LS_projects.getSelectedIndex()).getTasks().get(i).getNumberExecutors();o++)
					{
						data[aux][2] = Main.user.getProjects().get(LS_projects.getSelectedIndex()).getTasks().get(i).getExecutors().get(o).getEmail();
						aux++;
					}
				}
			
				TB_tasks = new JTable(data, columnNames);
				SP_tasks = new JScrollPane(TB_tasks);
				TB_tasks.disable();
				gbc.gridx = 1;
				gbc.gridy = 0;
				gbc.gridwidth = 1;
				gbc.gridheight = 1;
				gbc.weightx = 1.0;
				gbc.weighty = 1.0;
				gbc.insets = new Insets(10,10,10,10);
				gbc.fill = GridBagConstraints.BOTH;
				gbc.anchor = GridBagConstraints.WEST;
				add(SP_tasks,gbc);
			
				setVisible(true);
				}	
			}
		}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()== KeyEvent.VK_ENTER){
			
			LB_incorrect_password.setForeground(Color.LIGHT_GRAY);
			revalidate();
			repaint();
			
			/* Si el usuario y contraseña son correctos entonces logueamos al usuario */
			if(Main.searcher.ValidateUser(TF_email.getText(), PF_password.getPassword())){
				Main.LogInUser(TF_email.getText(), new String(PF_password.getPassword()));
			}
			
			/* Si el usuario y/o contraseña son incorrectos entonces se muestra un mensaje en pantalla */
			else{
				LB_incorrect_password.setForeground(Color.red);
				setVisible(true);
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub		
	}

}