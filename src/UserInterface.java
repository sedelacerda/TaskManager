import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.text.DateFormat;
import java.util.List;
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
	JLabel LB_incorrect_password;
	JButton BT_LogOut;
	//Menu de arriba
	JMenuBar MB_menu = new JMenuBar();
	JMenu MN_vistas;
	JMenuItem MI_vista1;
	JMenuItem MI_vista2;
	//Home
	
	//Tareas por proyecto y proyectos por usuario
	GridBagConstraints gbc;
	JList LS_projects;
	JScrollPane SP_projects;
	GridBagLayout grid ;
	JTable TB_tasks ;
	JScrollPane SP_tasks ;
	
	public UserInterface() {
		super("Task Manager");
		
	}
	
	public void ShowLoginScreen(){
		
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		grid = new GridBagLayout();
		gbc = new GridBagConstraints();
		setLayout(grid);
		
		LB_bienvenido = new JLabel("Bienvenido");
		LB_bienvenido.setFont(new Font("Serif", Font.PLAIN, 25));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
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
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		add(BT_login,gbc);
		
		BT_login.addActionListener(this);
		
		LB_incorrect_password = new JLabel("*Usuario y/o contrase�a inv�lidos, intente de nuevo.");
		LB_incorrect_password.setForeground(Color.red);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.gridheight = 2;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		
		setVisible(true);
	}
	
	public void ShowHome(){
		LimpiarVista();
		getContentPane().setBackground(Color.YELLOW);
		// Menu
		BT_LogOut = new JButton("Cerrar Sesion");
		gbc.gridx = 3;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		MB_menu = new JMenuBar();
		setJMenuBar(MB_menu);
		MN_vistas = new JMenu("Vistas");
		MB_menu.add(MN_vistas);
		MI_vista1 = new JMenuItem("Ver tareas por proyecto");
		MI_vista1.addActionListener(this);
		MN_vistas.add(MI_vista1);
		MI_vista2 = new JMenuItem("Ver usuarios por proyecto");
		MI_vista2.addActionListener(this);
		MN_vistas.add(MI_vista2);
		add(BT_LogOut,gbc);
		MB_menu.add(BT_LogOut);
		BT_LogOut.addActionListener(this);
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
	public void ShowTasksByProjectScreen(List<Project> userProjects) {
		LimpiarVista();
		/* Primero borramos todos los elementos de la vista de login */
		
		
		/*Ahora cargamos los elementos de esta vista */
		
		/* Primero cargamos el menu superior */
		BT_LogOut = new JButton("Cerrar Sesion");
		gbc.gridx = 3;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		add(BT_LogOut,gbc);
		BT_LogOut.addActionListener(this);
		MB_menu = new JMenuBar();
		setJMenuBar(MB_menu);
		MN_vistas = new JMenu("Vistas");
		MB_menu.add(BT_LogOut);
		MB_menu.add(MN_vistas);
		MI_vista1 = new JMenuItem("Ver tareas por proyecto");
		MI_vista1.addActionListener(this);
		MN_vistas.add(MI_vista1);
		MI_vista2 = new JMenuItem("Ver usuarios por proyecto");
		MI_vista2.addActionListener(this);
		MN_vistas.add(MI_vista2);
		
		List<Project> projects = Main.user.getProjects();
		String[] projectsDescription = new String[projects.size()];
		
		for(int i=0; i<projects.size();i++){
			projectsDescription[i] = ""+projects.get(i).getDescription();
		}
		
		
		LS_projects = new JList(projectsDescription);
		LS_projects.addListSelectionListener(this);
		LS_projects.setSelectedIndex(0);
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
		TB_tasks.disable();
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
	
	public void actionPerformed(ActionEvent e) {
		
		/* Handler del boton de log in */
		if(e.getSource() == BT_login) {
			remove(LB_incorrect_password);
			revalidate();
			repaint();
			
			/* Si el usuario y contrase�a son correctos entonces logueamos al usuario */
			if(Main.searcher.ValidateUser(TF_email.getText(), PF_password.getPassword())){
				Main.LogInUser(TF_email.getText(), new String(PF_password.getPassword()));
			}
			
			/* Si el usuario y/o contrase�a son incorrectos entonces se muestra un mensaje en pantalla */
			else{
				add(LB_incorrect_password,gbc);
				setVisible(true);
			}
		}
		
		/* Handler del primer item de la pesta�a vistas del menu superior */
		if(e.getSource() == MI_vista1) {
			ShowTasksByProjectScreen(Main.user.getProjects());
		}
		
		
		/* Handler del segundo item de la pesta�a vistas del menu superior */
		if(e.getSource() == MI_vista2) {
			
		}
		if(e.getSource()==BT_LogOut){
			LimpiarVista();
			ShowLoginScreen();
		}
	}
	
	/* Handler para el JList de los proyectos */
	public void valueChanged(ListSelectionEvent e){
		if(e.getSource() == LS_projects){
			
			String[] columnNames = {"Description", "Context", "State", "Deadline"};
			int ntasks = Main.user.getProjects().get(LS_projects.getSelectedIndex()).getTasks().size();
			Object[][] data = new Object[ntasks][];
			
			DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
			
			for(int i = 0;i<ntasks;i++){
				data[i] = new Object[4];
				data[i][0] = Main.user.getProjects().get(LS_projects.getSelectedIndex()).getTasks().get(i).getDescription();
				data[i][1] = Main.user.getProjects().get(LS_projects.getSelectedIndex()).getTasks().get(i).getContext();
				data[i][2] = Main.user.getProjects().get(LS_projects.getSelectedIndex()).getTasks().get(i).getState();
				data[i][3] = df.format(Main.user.getProjects().get(LS_projects.getSelectedIndex()).getTasks().get(i).getDeadline());
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()== KeyEvent.VK_ENTER){
			
			if(Main.searcher.ValidateUser(TF_email.getText(), PF_password.getPassword())){
				Main.LogInUser(TF_email.getText(), new String(PF_password.getPassword()));
			}
			else{
				add(LB_incorrect_password,gbc);
				setVisible(true);
			}
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
