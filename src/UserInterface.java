import javax.swing.*;

import java.awt.*;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;

public class UserInterface extends JFrame implements ActionListener {
	
	JLabel LB_email;
	JTextField TF_email;
	JLabel LB_password;
	JPasswordField PF_password;
	JButton BT_login;
	JLabel LB_incorrect_password;
	GridBagConstraints gbc;
	JMenuBar MB_menu;
	JMenu MN_vistas;
	JMenuItem MI_vista1, MI_vista2;
	
	public UserInterface() {
		super("Task Manager");
	}
	
	public void ShowLoginScreen(){
		
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		GridBagLayout grid = new GridBagLayout();
		gbc = new GridBagConstraints();
		setLayout(grid);
		
		LB_email = new JLabel("Ingresar Email: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		add(LB_email,gbc);
		
		TF_email = new JTextField(30);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		add(TF_email,gbc);
		
		LB_password = new JLabel("Ingresar Password: ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		add(LB_password,gbc);
		
		PF_password = new JPasswordField(30);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.WEST;
		add(PF_password,gbc);

		BT_login = new JButton("Iniciar Sesion");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;
		add(BT_login,gbc);
		
		BT_login.addActionListener(this);
		
		LB_incorrect_password = new JLabel("*Usuario y/o contraseña inválidos, intente de nuevo.");
		LB_incorrect_password.setForeground(Color.red);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.gridheight = 2;
		gbc.weightx = 0.0;
		gbc.weighty = 0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		
		setVisible(true);
	}
	
	public void ShowTasksByProjectScreen(List<Project> userProjects) {
		
		/* Primero borramos todos los elementos de la vista de login */
		remove(LB_email);
		remove(TF_email);
		remove(LB_password);
		remove(PF_password);
		remove(BT_login);
		remove(LB_incorrect_password);
		revalidate();
		repaint();
		
		/*Ahora cargamos los elementos de esta vista */
		
		/* Primero cargamos el menu superior */
		JMenuBar MB_menu = new JMenuBar();
		setJMenuBar(MB_menu);
		JMenu MN_vistas = new JMenu("Vistas");
		MB_menu.add(MN_vistas);
		JMenuItem MI_vista1 = new JMenuItem("Ver tareas por proyecto");
		MI_vista1.addActionListener(this);
		MN_vistas.add(MI_vista1);
		JMenuItem MI_vista2 = new JMenuItem("Ver");
		MI_vista2.addActionListener(this);
		MN_vistas.add(MI_vista2);
		
		/* Ahora creamos una tabla y un scroll para mostrar las tareas */
		Object[][] data = {
				{"Hola", 1},
				{"Chao", 2}
		};
		String[] columnNames = {"Palabra", "Numero"};
		
		JTable TB_table = new JTable(data, columnNames);
		TB_table.setPreferredScrollableViewportSize(new Dimension(600, 70));
		
		JScrollPane SP_scroll = new JScrollPane(TB_table);
		add(SP_scroll);
		
		setVisible(true);
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		/* Handler del boton de log in */
		if(e.getSource() == BT_login) {
			remove(LB_incorrect_password);
			revalidate();
			repaint();
			
			/* Si el usuario y contraseña son correctos entonces logueamos al usuario */
			if(Main.searcher.ValidateUser(TF_email.getText(), PF_password.getPassword())){
				Main.LogInUser(TF_email.getText(), new String(PF_password.getPassword()));
			}
			
			/* Si el usuario y/o contraseña son incorrectos entonces se muestra un mensaje en pantalla */
			else{
				add(LB_incorrect_password,gbc);
				setVisible(true);
			}
		}
		
		/* Handler del primer item de la pestaña vistas del menu superior */
		if(e.getSource() == MI_vista1) {
			
		}
		
		/* Handler del segundo item de la pestaña vistas del menu superior */
		if(e.getSource() == MI_vista2) {
			
		}
	}

}
